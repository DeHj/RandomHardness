package randomhardness.custom_block_stone;

import net.minecraft.client.Minecraft;
import net.minecraft.init.Blocks;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class StartupCommon
{
    public static final CustomBlockStone BLOCK_MY_STONE
            = (CustomBlockStone)(new CustomBlockStone().setUnlocalizedName(Blocks.STONE.getUnlocalizedName().replace("tile.", "")));

    public static void preInitCommon()
    {
        //BLOCK_MY_STONE = (CustomBlockStone)(new CustomBlockStone().setUnlocalizedName(Blocks.STONE.getUnlocalizedName().replace("tile.", "")));
        BLOCK_MY_STONE.setRegistryName(new ResourceLocation("minecraft", "stone"));
        ForgeRegistries.BLOCKS.register(BLOCK_MY_STONE);
    }

    public static void initCommon()
    {
        StoneFactory.factoryInit(StartupCommon.BLOCK_MY_STONE);
    }

    public static void postInitCommon()
    {
    }

}
