package randomhardness.sulfur;

import net.minecraft.block.Block;
import net.minecraft.block.BlockSandStone;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.util.BlockRenderLayer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Random;

public class BlockSulfurOre extends Block {
    public BlockSulfurOre() {
        super(Material.ROCK);
        this.setCreativeTab(CreativeTabs.BUILDING_BLOCKS);
        this.setHardness(0.8F);
    }

    public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
        return StartupCommon.ITEM_SULFUR;
    }

    public int quantityDroppedWithBonus(int fortune, Random random)
    {
        if (fortune > 0)
        {
            int i = random.nextInt(fortune + 2) - 1;

            if (i < 0)
                i = 0;

            return (i + 1);
        }
        else
            return 1;
    }

    @SideOnly(Side.CLIENT)
    public BlockRenderLayer getBlockLayer()
    {
        return BlockRenderLayer.SOLID;
    }
}
