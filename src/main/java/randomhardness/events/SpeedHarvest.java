package randomhardness.events;

import net.minecraft.block.BlockStone;
import net.minecraft.block.BlockStoneSlab;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import randomhardness.config.ConfigAgent;
import randomhardness.custom_block_stone.BlockCustomStone;

@Mod.EventBusSubscriber
public class SpeedHarvest
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
}