package randomhardness.sulfur;

import net.minecraft.item.ItemBlock;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class StartupCommon {

    public static final BlockSulfur SULFUR_ORE = (BlockSulfur)(new BlockSulfur().setUnlocalizedName("block_sulfur_unlocalised_name"));
    public static final ItemBlock ITEM_SULFUR_ORE = new ItemBlock(SULFUR_ORE);
    public static final ItemSulfur ITEM_SULFUR = (ItemSulfur)(new ItemSulfur().setUnlocalizedName("item_sulfur_unlocalised_name"));

    public static void preInitCommon()
    {
        SULFUR_ORE.setRegistryName("block_sulfur_registry_name");
        //SULFUR_ORE.setRegistryName(new ResourceLocation("randomhardness", "block_sulfur_registry_name"));//"hardness:block_sulfur_registry_name");
        ForgeRegistries.BLOCKS.register(SULFUR_ORE);

        ITEM_SULFUR_ORE.setRegistryName(SULFUR_ORE.getRegistryName());
        ForgeRegistries.ITEMS.register(ITEM_SULFUR_ORE);

        ITEM_SULFUR.setRegistryName(new ResourceLocation("randomhardness", "item_sulfur_registry_name"));
        ForgeRegistries.ITEMS.register(ITEM_SULFUR);
    }

    public static void initCommon()
    {

    }

    public static void postInitCommon()
    {
    }

}
