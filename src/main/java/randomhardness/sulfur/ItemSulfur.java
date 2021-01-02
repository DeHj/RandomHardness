package randomhardness.sulfur;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class ItemSulfur extends Item {
    public ItemSulfur()
    {
        this.setHasSubtypes(false);
        this.setMaxDamage(0);
        this.setCreativeTab(CreativeTabs.MATERIALS);
    }
}
