package randomhardness.sulfur;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.crafting.CraftingHelper;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.common.registry.GameRegistry;
import randomhardness.RandomHardness;

public class StartupCommon {

    public static final BlockSulfurOre SULFUR_ORE = (BlockSulfurOre)(new BlockSulfurOre().setUnlocalizedName("sulfur_ore_unlocalised_name"));
    public static final ItemBlock ITEM_SULFUR_ORE = new ItemBlock(SULFUR_ORE);

    public static final ItemSulfur ITEM_SULFUR = (ItemSulfur)(new ItemSulfur().setUnlocalizedName("item_sulfur_unlocalised_name"));

    public static final Block SULFUR_BLOCK = (new Block(Material.IRON).setHardness(5.0F).setResistance(10.0F).setUnlocalizedName("sulfur_block_unlocalised_name"));
    public static final ItemBlock ITEM_SULFUR_BLOCK = new ItemBlock(SULFUR_BLOCK);


    public static void preInitCommon()
    {
        SULFUR_ORE.setRegistryName("sulfur_ore_registry_name");
        ForgeRegistries.BLOCKS.register(SULFUR_ORE);
        ITEM_SULFUR_ORE.setRegistryName(SULFUR_ORE.getRegistryName());
        ForgeRegistries.ITEMS.register(ITEM_SULFUR_ORE);

        ITEM_SULFUR.setRegistryName(new ResourceLocation("item_sulfur_registry_name"));
        ForgeRegistries.ITEMS.register(ITEM_SULFUR);

        SULFUR_BLOCK.setRegistryName("sulfur_block_registry_name");
        ForgeRegistries.BLOCKS.register(SULFUR_BLOCK);
        ITEM_SULFUR_BLOCK.setRegistryName(SULFUR_BLOCK.getRegistryName());
        ForgeRegistries.ITEMS.register(ITEM_SULFUR_BLOCK);


        ResourceLocation optionalGroup = new ResourceLocation("");

        GameRegistry.addShapelessRecipe(
                new ResourceLocation(RandomHardness.MODID, "block_to_sulfur"), optionalGroup,
                new ItemStack(ITEM_SULFUR, 9),
                CraftingHelper.getIngredient(ITEM_SULFUR_BLOCK));


        GameRegistry.addShapedRecipe(
                new ResourceLocation(RandomHardness.MODID, "sulfur_to_block"), optionalGroup,
                new ItemStack(ITEM_SULFUR_BLOCK, 1),
                new Object[]{
                        "SSS",
                        "SSS",
                        "SSS",
                        'S', ITEM_SULFUR
                });
    }

    public static void initCommon()
    {

    }

    public static void postInitCommon()
    {
    }

}
