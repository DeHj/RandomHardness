package randomhardness;

import net.minecraft.entity.player.EntityPlayer;
import randomhardness.config.ConfigAgent;

public abstract class CommonProxy {

    public void preInit()
    {
        randomhardness.custom_block_stone.StartupCommon.preInitCommon();
        randomhardness.gunpowder_mining.StartupCommon.preInitCommon();
    }

    public void init()
    {
        randomhardness.custom_block_stone.StartupCommon.initCommon();
        randomhardness.gunpowder_mining.StartupCommon.initCommon();

        ConfigAgent.Init();
    }

    public void postInit()
    {
        randomhardness.custom_block_stone.StartupCommon.postInitCommon();
        randomhardness.gunpowder_mining.StartupCommon.postInitCommon();
    }

    abstract public boolean playerIsInCreativeMode(EntityPlayer player);

    abstract public boolean isDedicatedServer();
}