package randomhardness.events;

import net.minecraft.block.BlockStone;
import net.minecraft.block.BlockStoneSlab;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.terraingen.PopulateChunkEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import randomhardness.config.ConfigAgent;
import randomhardness.config.RandomHardnessConfig;
import randomhardness.custom_block_stone.BlockCustomStone;
import randomhardness.custom_block_stone.StoneFactory;

@Mod.EventBusSubscriber
public class EventsHandler
{

    @SubscribeEvent
    public static void speed(PlayerEvent.BreakSpeed event) {
        if (event.getEntity() instanceof EntityPlayer)
        {
            IBlockState state = event.getState();
            String registryName = state.getBlock().getRegistryName().getResourcePath();
            BlockStoneSlab.EnumType slabVariant = null;
            if (registryName.equals("stone_slab"))
                slabVariant = state.getValue(BlockStoneSlab.VARIANT);

            //event.getEntityPlayer().sendMessage(new TextComponentString(registryName));

            float speedModifier = 1;

            if (registryName.contains("_ore"))
            {
                // ores
                speedModifier = ConfigAgent.getOreHardness() * ConfigAgent.getGlobalModifier();
            }
            else if (registryName.equals("cobblestone") ||
                    registryName.equals("mossy_cobblestone") ||
                    registryName.equals("stone_stairs") ||
                    slabVariant == BlockStoneSlab.EnumType.COBBLESTONE)
            {
                // cobblestones
                speedModifier = ConfigAgent.getCobblestoneHardness() * ConfigAgent.getGlobalModifier();
            }
            else if (registryName.equals("stonebrick") ||
                    registryName.equals("brick_block") ||
                    registryName.equals("brick_stairs") ||
                    registryName.equals("stone_brick_stairs") ||
                    slabVariant == BlockStoneSlab.EnumType.SMOOTHBRICK ||
                    slabVariant == BlockStoneSlab.EnumType.BRICK)
            {
                // bricks
                speedModifier = ConfigAgent.getBrickBlocksHardness() * ConfigAgent.getGlobalModifier();
            }
            else if (state.getBlock() instanceof BlockCustomStone)
            {
                BlockStone.EnumType variant = state.getValue(BlockStone.VARIANT);
                speedModifier = ConfigAgent.getGlobalModifier();

                if (variant == BlockStone.EnumType.ANDESITE || variant == BlockStone.EnumType.ANDESITE_SMOOTH)
                    speedModifier *= ConfigAgent.getAndesiteModifier();
                if (variant == BlockStone.EnumType.DIORITE || variant == BlockStone.EnumType.DIORITE_SMOOTH)
                    speedModifier *= ConfigAgent.getDioriteModifier();
                if (variant == BlockStone.EnumType.GRANITE || variant == BlockStone.EnumType.GRANITE_SMOOTH)
                    speedModifier *= ConfigAgent.getGraniteModifier();

                if (variant == BlockStone.EnumType.STONE)
                {
                    Integer hardnessIndex = state.getValue(BlockCustomStone.HARDNESS);
                    speedModifier *= ConfigAgent.getStoneHardness(hardnessIndex);
                }
            }

            event.setNewSpeed(event.getOriginalSpeed() / speedModifier);
            //event.getEntityPlayer().sendMessage(new TextComponentString(Float.toString(speedModifier)));
        }
    }


    private static final Vec3i[] OFFSETS = new Vec3i[6];
    static {
        OFFSETS[0] = new Vec3i(0, 0, 1);
        OFFSETS[1] = new Vec3i(0, 0, -1);
        OFFSETS[2] = new Vec3i(0, 1, 0);
        OFFSETS[3] = new Vec3i(0, -1, 0);
        OFFSETS[4] = new Vec3i(1, 0, 0);
        OFFSETS[5] = new Vec3i(-1, 0, 0);
    }

    private static boolean isSmoothStone(IBlockState state)
    {
        if (state.getBlock() instanceof BlockCustomStone)
            return (state.getValue(BlockStone.VARIANT) == BlockStone.EnumType.STONE &&
                    state.getValue(BlockCustomStone.HARDNESS) == 0);
        return false;
    }

    private static void createCracksVein(World w, BlockPos pos, int stoneState) {
        w.setBlockState(pos, StoneFactory.getStateByIndex(stoneState));

        if (stoneState <= 1)
            return;

        for (Vec3i offset: OFFSETS) {
            BlockPos newPos = pos.add(offset);
            if (isSmoothStone(w.getBlockState(newPos)) == false)
                continue;

            float prob = RandomHardnessConfig.stoneBlocksGenCat.probability;
            int from = RandomHardnessConfig.stoneBlocksGenCat._fromState;
            int to = RandomHardnessConfig.stoneBlocksGenCat._toState;

            int state = StoneFactory.randIndexFromRange(w.rand, prob, stoneState - from, stoneState - to);

            if (state > 0)
                createCracksVein(w, newPos, state);
        }
    }


    @SubscribeEvent
    public static void updateWholeStones(PopulateChunkEvent.Post event) {

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

                        //w.setBlockState(pos, StoneFactory.randState(w.rand));
                    }
                }
            }
    }
}