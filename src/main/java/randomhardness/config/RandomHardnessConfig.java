package randomhardness.config;


import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import randomhardness.RandomHardness;

@Config(modid = RandomHardness.MODID, name = RandomHardness.NAME)
public class RandomHardnessConfig {

    @Config.Name("Harvest speed tab")
    public static BlockHarvestCategory blocksHarvestCat = new BlockHarvestCategory();

    @Config.Name("Cracked stone blocks generation tab")
    public static BlockGenerationCategory stoneBlocksGenCat = new BlockGenerationCategory();

    @Config.Name("Gunpowder mining changes tab")
    public static GunpowderMiningCategory gunpowderMiningCat = new GunpowderMiningCategory();


    public static class BlockHarvestCategory {
        @Config.Name("1. Global modifier")
        @Config.Comment("The global modifier determines how many times the time for " +
                "the harvest for all hard rock will increase.")
        @Config.RangeDouble(min=1, max=100)
        public float globalModifier = 1F;

        @Config.Name("Stone modifiers")
        @Config.Comment({"Modifiers that determines how many times the time for the harvest for stone will increase.",
                "These modifiers will be multiplied by the global modifier."})
        public final AllTypesStoneCategory allTypesCat = new AllTypesStoneCategory();

        public static class AllTypesStoneCategory {

            @Config.Name("Harvest time for level 0 cracked stone block")
            @Config.Comment("Level 0 cracked stone block has no cracks and has vanilla stone texture.")
            public float level0hardness = 20.0F;

            @Config.Name("Harvest time for level 1 cracked stone block")
            @Config.Comment("Level 0 cracked stone block has minimal cracks." +
                    "Each subsequent level cracked stone block has a little more cracks.")
            public float level1hardness = 9.0F;

            @Config.Name("Harvest time for level 2 cracked stone block") public float level2hardness = 8.0F;
            @Config.Name("Harvest time for level 3 cracked stone block") public float level3hardness = 7.0F;
            @Config.Name("Harvest time for level 4 cracked stone block") public float level4hardness = 6.0F;
            @Config.Name("Harvest time for level 5 cracked stone block") public float level5hardness = 5.0F;
            @Config.Name("Harvest time for level 6 cracked stone block") public float level6hardness = 4.0F;
            @Config.Name("Harvest time for level 7 cracked stone block") public float level7hardness = 3.0F;
            @Config.Name("Harvest time for level 8 cracked stone block") public float level8hardness = 2.0F;

            @Config.Name("Harvest time for level 9 cracked stone block")
            @Config.Comment("Level 9 cracked stone block are most cracked. These blocks are like vanilla cobblestone.")
            public float level9hardness = 1.0F;
        }


        @Config.Name("2. Andesite modifier")
        @Config.Comment("This modifier determines how many times the time for the harvest for andesite will increase.")
        @Config.RangeDouble(min=1, max=100)
        public float hardnessAndesite = 5;
        @Config.Name("3. Diorite modifier")
        @Config.Comment("This modifier determines how many times the time for the harvest for diorite will increase.")
        @Config.RangeDouble(min=1, max=100)
        public float hardnessDiorite = 6;
        @Config.Name("4. Granite modifier")
        @Config.Comment("This modifier determines how many times the time for the harvest for granite will increase.")
        @Config.RangeDouble(min=1, max=100)
        public float hardnessGranite = 7;

        @Config.Name("5. Ores modifier")
        @Config.Comment("This modifier determines how many times the time for the harvest for ores will increase.")
        @Config.RangeDouble(min=1, max=100)
        public float hardnessOres = 2;

        @Config.Name("6. Brick blocks modifier")
        @Config.Comment("This modifier determines how many times the time for the harvest for brick blocks will increase.")
        @Config.RangeDouble(min=1, max=100)
        public float hardnessBrickBlocks = 3;
    }

    public static class BlockGenerationCategory {

        @Config.Name("Chances of cracked blocks placing")
        @Config.Comment("Cracked blocks are not generating independently. " +
                "After cracked block placing an attempt of less level cracked" +
                "block placing is initiated near with probability entered below.")
        public final AllTypesStoneCategory allTypesCat = new AllTypesStoneCategory();

        @Config.Name("Probability of occurrence of a neighboring block")
        @Config.Comment("It describes the probability that generation of a cracked" +
                "block will cause generation one more cracked block near. " +
                "This creates clusters of cracks.")
        @Config.RangeDouble(min=0, max=1)
        public float probability = 0.2F;

        public int _fromState = 3;
        public int _toState = 1;

        public static class AllTypesStoneCategory {
            @Config.Name("Average amount of clusters of level 1 cracked per 1000 blocks") public int level1chance = 30;
            @Config.Name("Average amount of clusters of level 2 cracked per 1000 blocks") public int level2chance = 30;
            @Config.Name("Average amount of clusters of level 3 cracked per 1000 blocks") public int level3chance = 20;
            @Config.Name("Average amount of clusters of level 4 cracked per 1000 blocks") public int level4chance = 20;
            @Config.Name("Average amount of clusters of level 5 cracked per 1000 blocks") public int level5chance = 10;
            @Config.Name("Average amount of clusters of level 6 cracked per 1000 blocks") public int level6chance = 10;
            @Config.Name("Average amount of clusters of level 7 cracked per 1000 blocks") public int level7chance = 10;
            @Config.Name("Average amount of clusters of level 8 cracked per 1000 blocks") public int level8chance = 10;
            @Config.Name("Average amount of clusters of level 9 cracked per 1000 blocks") public int level9chance = 50;
        }
    }

    public static class GunpowderMiningCategory {
        @Config.Name("Min. creeper drop")
        @Config.Comment("Min. number of gunpowder that are dropping when killing a creeper.")
        @Config.RangeInt(min=0, max=6)
        public int minGunpowderDrop = 1;
        @Config.Name("Max. creeper drop")
        @Config.Comment("Max. number of gunpowder that are dropping when killing a creeper.")
        @Config.RangeInt(min=2, max=8)
        public int maxGunpowderDrop = 4;

        @Config.Name("Villager professions that are trading of gunpowder")
        @Config.Comment("Already generated villagers will not acquire new recipe")
        @Config.RequiresMcRestart
        public String[] gunpowderTraders = new String[] { "minecraft:priest" };

        @Config.Name("Average amount of sulfur ore veins per 1000 blocks")
        @Config.Comment("Sulfur ore vein contains from one to four blocks")
        public int sulfurChance = 30;
    }


    @Mod.EventBusSubscriber(modid = RandomHardness.MODID)
    public static class EventHandler {
        @SubscribeEvent
        public static void onConfigChanged(final ConfigChangedEvent.OnConfigChangedEvent event) {
            if (event.getModID().equals(RandomHardness.MODID)) {
                ConfigManager.sync(RandomHardness.MODID, Config.Type.INSTANCE);

                ConfigAgent.Init();
            }
        }
    }
}
