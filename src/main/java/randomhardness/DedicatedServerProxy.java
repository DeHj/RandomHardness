package randomhardness;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;

public class DedicatedServerProxy extends CommonProxy
{
    public void preInit() {
        super.preInit();
    }

    public void init() {
        super.init();
    }

    public void postInit() {
        super.postInit();
    }

    @Override
    public boolean playerIsInCreativeMode(EntityPlayer player) {
        if (player instanceof EntityPlayerMP) {
            EntityPlayerMP entityPlayerMP = (EntityPlayerMP) player;
            return entityPlayerMP.interactionManager.isCreative();
        }
        return false;
    }

    @Override
    public boolean isDedicatedServer() {return true;}

}
