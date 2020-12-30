package randomhardness.gunpowder_mining;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.common.registry.VillagerRegistry;
import randomhardness.custom_block_stone.StoneFactory;

public class StartupCommon {
    public static void preInitCommon()
    {
    }

    public static void initCommon()
    {
        ForgeRegistries.VILLAGER_PROFESSIONS.getValue(new ResourceLocation("minecraft:smith")).
                getCareer(2).addTrade(1, new GunpowderTrades());
    }

    public static void postInitCommon()
    {
    }
}
