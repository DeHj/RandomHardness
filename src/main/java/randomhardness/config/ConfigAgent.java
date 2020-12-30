package randomhardness.config;



public class ConfigAgent {

    private static final int TYPES_COUNT = 10;

    private static final int[] STONE_CHANCES = new int[TYPES_COUNT];
    private static final float[] STONE_HARDNESS = new float[TYPES_COUNT];
    private static int stoneChanceSum = 0;




    public static int typesCount() {
        return TYPES_COUNT;
    }

    public static void Init()
    {
        STONE_CHANCES[0] = 0;
        STONE_CHANCES[1] = RandomHardnessConfig.stoneBlocksGenCat.allTypesCat.level1chance;
        STONE_CHANCES[2] = RandomHardnessConfig.stoneBlocksGenCat.allTypesCat.level2chance;
        STONE_CHANCES[3] = RandomHardnessConfig.stoneBlocksGenCat.allTypesCat.level3chance;
        STONE_CHANCES[4] = RandomHardnessConfig.stoneBlocksGenCat.allTypesCat.level4chance;
        STONE_CHANCES[5] = RandomHardnessConfig.stoneBlocksGenCat.allTypesCat.level5chance;
        STONE_CHANCES[6] = RandomHardnessConfig.stoneBlocksGenCat.allTypesCat.level6chance;
        STONE_CHANCES[7] = RandomHardnessConfig.stoneBlocksGenCat.allTypesCat.level7chance;
        STONE_CHANCES[8] = RandomHardnessConfig.stoneBlocksGenCat.allTypesCat.level8chance;
        STONE_CHANCES[9] = RandomHardnessConfig.stoneBlocksGenCat.allTypesCat.level9chance;

        STONE_HARDNESS[0] = RandomHardnessConfig.blocksHarvestCat.allTypesCat.level0hardness;
        STONE_HARDNESS[1] = RandomHardnessConfig.blocksHarvestCat.allTypesCat.level1hardness;
        STONE_HARDNESS[2] = RandomHardnessConfig.blocksHarvestCat.allTypesCat.level2hardness;
        STONE_HARDNESS[3] = RandomHardnessConfig.blocksHarvestCat.allTypesCat.level3hardness;
        STONE_HARDNESS[4] = RandomHardnessConfig.blocksHarvestCat.allTypesCat.level4hardness;
        STONE_HARDNESS[5] = RandomHardnessConfig.blocksHarvestCat.allTypesCat.level5hardness;
        STONE_HARDNESS[6] = RandomHardnessConfig.blocksHarvestCat.allTypesCat.level6hardness;
        STONE_HARDNESS[7] = RandomHardnessConfig.blocksHarvestCat.allTypesCat.level7hardness;
        STONE_HARDNESS[8] = RandomHardnessConfig.blocksHarvestCat.allTypesCat.level8hardness;
        STONE_HARDNESS[9] = RandomHardnessConfig.blocksHarvestCat.allTypesCat.level9hardness;

        stoneChanceSum = 0;
        for (float chance : STONE_CHANCES)
            stoneChanceSum += chance;
    }



    public static int getStoneChance(int index) {
        return STONE_CHANCES[index];
    }

    public static int getStoneChanceSum()
    {
        return stoneChanceSum;
    }



    public static float getStoneHardness(int index)
    {
        return STONE_HARDNESS[index];
    }

    public static float getOreHardness()
    {
        return RandomHardnessConfig.blocksHarvestCat.hardnessOres;
    }

    public static float getCobblestoneHardness()
    {
        return STONE_HARDNESS[9];
    }

    public static float getBrickBlocksHardness()
    {
        return RandomHardnessConfig.blocksHarvestCat.hardnessBrickBlocks;
    }

    public static float getGlobalModifier()
    {
        return RandomHardnessConfig.blocksHarvestCat.globalModifier;
    }

    public static float getAndesiteModifier()
    {
        return RandomHardnessConfig.blocksHarvestCat.hardnessAndesite;
    }

    public static float getDioriteModifier()
    {
        return RandomHardnessConfig.blocksHarvestCat.hardnessDiorite;
    }

    public static float getGraniteModifier()
    {
        return RandomHardnessConfig.blocksHarvestCat.hardnessGranite;
    }



    public static int getMinGunpowderDrop() { return RandomHardnessConfig.gunpowderMiningCat.minGunpowderDrop; }
    public static int getMaxGunpowderDrop() { return RandomHardnessConfig.gunpowderMiningCat.maxGunpowderDrop; }
    public static String[] getGunpowderTraders() { return RandomHardnessConfig.gunpowderMiningCat.gunpowderTraders; }
}
