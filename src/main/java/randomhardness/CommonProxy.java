package randomhardness;

import net.minecraft.entity.player.EntityPlayer;
import randomhardness.config.ConfigAgent;

public abstract class CommonProxy {

    public void preInit()
    {
        randomhardness.custom_block_stone.StartupCommon.preInitCommon();
    }

    public void init()
    {
        randomhardness.custom_block_stone.StartupCommon.initCommon();

        ConfigAgent.Init();
    }

    public void postInit()
    {
        randomhardness.custom_block_stone.StartupCommon.postInitCommon();
    }

    abstract public boolean playerIsInCreativeMode(EntityPlayer player);

    abstract public boolean isDedicatedServer();
}