package randomhardness.gunpowder_mining;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.crafting.CraftingHelper;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.common.registry.VillagerRegistry;
import randomhardness.RandomHardness;
import randomhardness.config.ConfigAgent;

public class StartupCommon {
    public static void preInitCommon()
    {
        GameRegistry.addShapelessRecipe(
                new ResourceLocation(RandomHardness.MODID, "gunpowder"),
                new ResourceLocation(""),
                new ItemStack(Items.GUNPOWDER),
                CraftingHelper.getIngredient(Items.COAL),
                CraftingHelper.getIngredient(randomhardness.sulfur.StartupCommon.ITEM_SULFUR));
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
