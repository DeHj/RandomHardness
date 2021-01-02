package randomhardness.sulfur;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraftforge.client.model.ModelLoader;

public class StartupClientOnly
{
    public static void preInitClientOnly()
    {
        ModelResourceLocation itemModelResourceLocation = new ModelResourceLocation("randomhardness:item_sulfur", "inventory");
        ModelLoader.setCustomModelResourceLocation(StartupCommon.ITEM_SULFUR, 0, itemModelResourceLocation);

        itemModelResourceLocation = new ModelResourceLocation("randomhardness:item_block_sulfur", "inventory");
        ModelLoader.setCustomModelResourceLocation(StartupCommon.ITEM_SULFUR_ORE, 0, itemModelResourceLocation);
    }

    public static void initClientOnly()
    {
    }

    public static void postInitClientOnly()
    {
    }
}
