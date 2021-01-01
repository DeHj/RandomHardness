package randomhardness.custom_block_stone;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import randomhardness.config.ConfigAgent;

import java.util.Random;

public class StoneFactory {

    private static final IBlockState[] STONES = new IBlockState[BlockCustomStone.HARDNESS.getAllowedValues().size()];

    public static void factoryInit(Block block) {
        for (int i = 0; i < StoneFactory.STONES.length; i++) {
            StoneFactory.STONES[i] = block.getDefaultState().withProperty(BlockCustomStone.HARDNESS, i);
        }
    }

    public static IBlockState randState(Random rand) {
        int r = rand.nextInt(ConfigAgent.getStoneChanceSum());

        for (int i = 0; i < ConfigAgent.typesCount(); i++)
        {
            if (r < ConfigAgent.getStoneChance(i))
                return STONES[i];
            else
                r -= ConfigAgent.getStoneChance(i);
        }

        return STONES[0];
    }

    public static IBlockState fullRandStateFromRange(Random rand, int from, int to) {
        return STONES[rand.nextInt(to - from + 1) + from];
    }

    public static int randStateIndex(Random rand) {
        int r = rand.nextInt(Math.max(1000, ConfigAgent.getStoneChanceSum()));

        for (int i = 1; i < ConfigAgent.typesCount(); i++)
        {
            if (r < ConfigAgent.getStoneChance(i))
                return i;
            else
                r -= ConfigAgent.getStoneChance(i);
        }

        return 0;
    }

    public static IBlockState getStateByIndex(int index)
    {
        return STONES[index];
    }


    /** @return random stone state index from {@code fromState} state to {@code toState} state with probability {@code prob}
     */
    public static int randIndexFromRange(Random rand, float prob, int fromState, int toState)
    {
        fromState = Math.max(fromState, 0);
        toState = Math.min(toState, 9);

        float fr = rand.nextFloat();
        if (fr >= prob)
            return 0;

        return rand.nextInt(toState - fromState + 1) + fromState;
    }
}
