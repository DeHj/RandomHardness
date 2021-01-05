package randomhardness.events;

import net.minecraft.block.BlockAir;
import net.minecraft.block.BlockSandStone;
import net.minecraft.block.BlockStone;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.World;
import net.minecraftforge.event.terraingen.PopulateChunkEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import randomhardness.config.ConfigAgent;
import randomhardness.config.RandomHardnessConfig;
import randomhardness.custom_block_stone.BlockCustomStone;
import randomhardness.custom_block_stone.StoneFactory;

@Mod.EventBusSubscriber
public class WorldGeneration {

    private static final Vec3i[] OFFSETS = new Vec3i[6];
    static {
        OFFSETS[0] = new Vec3i(0, 0, 1);
        OFFSETS[1] = new Vec3i(0, 0, -1);
        OFFSETS[2] = new Vec3i(0, 1, 0);
        OFFSETS[3] = new Vec3i(0, -1, 0);
        OFFSETS[4] = new Vec3i(1, 0, 0);
        OFFSETS[5] = new Vec3i(-1, 0, 0);
    }

    private static final Vec3i[] AIR_ADJACENCY_CHECKS = new Vec3i[32];
    static {
        int index = 0;
        for (int x = -1; x <= 1; x++) for (int y = -1; y <= 1; y++) for (int z = -1; z <= 1; z++)
        {
            if (x == 0 && y == 0 && z == 0)
                continue;
            AIR_ADJACENCY_CHECKS[index] = new Vec3i(x, y, z);
            index++;
        }

        AIR_ADJACENCY_CHECKS[index++] = new Vec3i(0, 0, 2);
        AIR_ADJACENCY_CHECKS[index++] = new Vec3i(0, 0, -2);
        AIR_ADJACENCY_CHECKS[index++] = new Vec3i(0, 2, 0);
        AIR_ADJACENCY_CHECKS[index++] = new Vec3i(0, -2, 0);
        AIR_ADJACENCY_CHECKS[index++] = new Vec3i(2, 0, 0);
        AIR_ADJACENCY_CHECKS[index] = new Vec3i(-2, 0, 0);
    }

    private static boolean isSmoothStone(IBlockState state)
    {
        if (state.getBlock() instanceof BlockCustomStone)
            return (state.getValue(BlockStone.VARIANT) == BlockStone.EnumType.STONE &&
                    state.getValue(BlockCustomStone.HARDNESS) == 0);
        return false;
    }

    private static boolean isOxidizedSandstone(World w, BlockPos pos)
    {
        if (w.getBlockState(pos).getBlock() instanceof BlockSandStone)
        {
            for (Vec3i offset: AIR_ADJACENCY_CHECKS) {
                if (w.getBlockState(pos.add(offset)).getBlock() instanceof BlockAir)
                {
                    return true;
                }
            }
        }
        return false;
    }

    private static void createCracksVein(World w, BlockPos pos, int stoneState) {
        w.setBlockState(pos, StoneFactory.getStateByIndex(stoneState));

        if (stoneState <= 1)
            return;

        ChunkPos chunkPos = w.getChunkFromBlockCoords(pos).getPos();

        for (Vec3i offset: OFFSETS) {
            BlockPos newPos = pos.add(offset);
            if (isSmoothStone(w.getBlockState(newPos)) == false)
                continue;

            if (    newPos.getX() < chunkPos.x * 16 ||
                    newPos.getX() >= (chunkPos.x + 1) * 16 ||
                    newPos.getZ() < chunkPos.z * 16 ||
                    newPos.getZ() >= (chunkPos.z + 1) * 16) {
                continue;
            }

            int state = StoneFactory.randIndexFromRange(w.rand,
                    RandomHardnessConfig.stoneBlocksGenCat.probability,
                    stoneState - RandomHardnessConfig.stoneBlocksGenCat._fromState,
                    stoneState - RandomHardnessConfig.stoneBlocksGenCat._toState);

            if (state > 0)
                createCracksVein(w, newPos, state);
        }
    }

    private static void createSulfurVein(World w, BlockPos pos) {
        w.setBlockState(pos, randomhardness.sulfur.StartupCommon.SULFUR_ORE.getDefaultState());

        for (Vec3i offset : OFFSETS) {
            BlockPos newPos = pos.add(offset);
            if (isOxidizedSandstone(w, newPos))
            {
                float prob = w.rand.nextFloat();
                if (prob < 0.5F)
                    w.setBlockState(newPos, randomhardness.sulfur.StartupCommon.SULFUR_ORE.getDefaultState());
            }
        }
    }

    @SubscribeEvent
    public static void updateWholeBlocks(PopulateChunkEvent.Post event) {

        World w = event.getWorld();

        for (int x = event.getChunkX() * 16; x < (event.getChunkX() + 1) * 16; x++)
            for (int z = event.getChunkZ() * 16; z < (event.getChunkZ() + 1) * 16; z++)
            {
                int height = w.getHeight(x + 8, z + 8);
                for (int y = 1; y <= height; y++)
                {
                    BlockPos pos = new BlockPos(x + 8, y ,z + 8);

                    if (isSmoothStone(w.getBlockState(pos))) {
                        int newState = StoneFactory.randStateIndex(w.rand);
                        if (newState != 0)
                            createCracksVein(w, pos, newState);
                    }
                    else if (isOxidizedSandstone(w, pos))
                    {
                        int prob = w.rand.nextInt(1000);
                        if (prob < ConfigAgent.getSulfurChance())
                            createSulfurVein(w, pos);
                    }
                }
            }
    }
}
