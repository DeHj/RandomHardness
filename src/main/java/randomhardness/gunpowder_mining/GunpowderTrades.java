package randomhardness.gunpowder_mining;

import net.minecraft.entity.IMerchant;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.village.MerchantRecipe;
import net.minecraft.village.MerchantRecipeList;

import java.util.Random;

public class GunpowderTrades implements EntityVillager.ITradeList
{
    @Override
    public void addMerchantRecipe(IMerchant merchant, MerchantRecipeList recipeList, Random random)
    {
        recipeList.add(new MerchantRecipe(
                new ItemStack(Items.EMERALD,1),
                new ItemStack(Items.GUNPOWDER, random.nextInt(5) + 7)));
    }
}
