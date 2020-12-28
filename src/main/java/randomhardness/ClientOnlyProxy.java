package randomhardness;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;

public class ClientOnlyProxy extends CommonProxy
{
    public void preInit()
    {
        super.preInit();

        randomhardness.custom_block_stone.StartupClientOnly.preInitClientOnly();
    }

    public void init()
    {
        super.init();

        randomhardness.custom_block_stone.StartupClientOnly.initClientOnly();
    }

    public void postInit()
    {
        super.postInit();

        randomhardness.custom_block_stone.StartupClientOnly.postInitClientOnly();
    }

    @Override
    public boolean playerIsInCreativeMode(EntityPlayer player) {
        if (player instanceof EntityPlayerMP) {
            EntityPlayerMP entityPlayerMP = (EntityPlayerMP)player;
            return entityPlayerMP.interactionManager.isCreative();
        } else if (player instanceof EntityPlayerSP) {
            return Minecraft.getMinecraft().playerController.isInCreativeMode();
        }
        return false;
    }

    @Override
    public boolean isDedicatedServer() {return false;}
}
