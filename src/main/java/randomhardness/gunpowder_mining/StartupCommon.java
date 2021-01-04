package randomhardness.gunpowder_mining;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.crafting.CraftingHelper;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.common.registry.VillagerRegistry;
import net.minecraftforge.registries.GameData;
import randomhardness.RandomHardness;
import randomhardness.config.ConfigAgent;

public class StartupCommon {
    public static void preInitCommon()
    {
        ResourceLocation optionalGroup = new ResourceLocation("");

        GameRegistry.addShapelessRecipe(
                new ResourceLocation(RandomHardness.MODID, "gunpowder"), optionalGroup,
                new ItemStack(Items.GUNPOWDER, 2),
                CraftingHelper.getIngredient(Items.COAL),
                CraftingHelper.getIngredient(randomhardness.sulfur.StartupCommon.ITEM_SULFUR));


        GameRegistry.addShapedRecipe(
                new ResourceLocation("minecraft", "tnt"), optionalGroup,
                new ItemStack(Blocks.TNT, 2),
                new Object[]{
                        "X#X",
                        "#X#",
                        "X#X",
                        'X', Items.GUNPOWDER,
                        '#', Blocks.SAND
                });
    }

    public static void initCommon()
    {
        merchantRecipeInit();
    }

    public static void postInitCommon()
    {
    }
    
    
    
    private static void merchantRecipeInit()
    {
        for (String profString: ConfigAgent.getGunpowderTraders()) {
            VillagerRegistry.VillagerProfession profession = ForgeRegistries.VILLAGER_PROFESSIONS.getValue(new ResourceLocation(profString));
            if (profession == null) {
                System.out.println("Profession " + profString + " is not existing");
                continue;
            }
            int index = 0;
            do {
                profession.getCareer(index).addTrade(1, new GunpowderTrades());
                index++;
            }
            while (!profession.getCareer(index).equals(profession.getCareer(0)));
        }
    }
}
