package randomhardness.custom_block_stone;

import net.minecraft.block.BlockStone;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import randomhardness.config.ConfigAgent;

import javax.annotation.Nullable;

public class BlockCustomStone extends BlockStone
{
    public static final PropertyInteger HARDNESS = PropertyInteger.create("hardness", 0, 9);

    public BlockCustomStone() {
        super();

        this.setDefaultState(this.blockState.getBaseState()
                .withProperty(HARDNESS, 0)
                .withProperty(VARIANT, BlockStone.EnumType.STONE));
        this.setHardness(3.0F);
        this.setResistance(1.0F);

        Blocks.COBBLESTONE.setResistance(1.0F);
    }

    @SideOnly(Side.CLIENT)
    public BlockRenderLayer getBlockLayer() {
        return BlockRenderLayer.SOLID;
    }


    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
        //playerIn.sendMessage(new TextComponentString("hardness=" + state.getValue(HARDNESS).toString()));

        return false;
    }


    @Override
    public IBlockState getStateFromMeta(int meta) {
        // meta values { 7, 8, ... , 15 } match a stone with hardness value from 1 to 9
        if (meta < 7)
            return this.getDefaultState().withProperty(VARIANT, BlockStone.EnumType.byMetadata(meta)).withProperty(HARDNESS, 0);
        else
            return this.getDefaultState().withProperty(VARIANT, EnumType.STONE).withProperty(HARDNESS,meta - 6);
    }


    @Override
    public int getMetaFromState(IBlockState state) {
        int meta = state.getValue(VARIANT).getMetadata();
        int hardness = state.getValue(HARDNESS);
        if (meta == 0 && hardness > 0)
            meta = hardness + 6;

        return meta;
    }

    @Override
    public IBlockState getStateForPlacement(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer, EnumHand hand)
    {
        if (meta == 0)
            return StoneFactory.fullRandStateFromRange(world.rand, 0, 4);
        else
            return this.getStateFromMeta(meta);
    }

    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, HARDNESS, VARIANT);
    }

    @Override
    public float getExplosionResistance(World world, BlockPos pos, @Nullable Entity exploder, Explosion explosion)
    {
        return blockResistance / 5.0F  * ConfigAgent.getStoneHardness(world.getBlockState(pos).getValue(HARDNESS));
    }

}
