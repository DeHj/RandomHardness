package randomhardness.gunpowder_mining;

import net.minecraft.init.Items;
import net.minecraft.world.storage.loot.LootEntryItem;
import net.minecraft.world.storage.loot.LootPool;
import net.minecraft.world.storage.loot.LootTableList;
import net.minecraft.world.storage.loot.RandomValueRange;
import net.minecraft.world.storage.loot.conditions.LootCondition;
import net.minecraft.world.storage.loot.conditions.RandomChance;
import net.minecraft.world.storage.loot.functions.LootFunction;
import net.minecraft.world.storage.loot.functions.SetCount;
import net.minecraftforge.event.LootTableLoadEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import randomhardness.config.ConfigAgent;

@Mod.EventBusSubscriber
public class EventsHandler {

    @SubscribeEvent
    public static void onLootTablesLoaded(LootTableLoadEvent event) {

        if (event.getName().equals(LootTableList.ENTITIES_CREEPER)) {

            LootPool main = event.getTable().getPool("main");

            main.addEntry(new LootEntryItem(Items.GUNPOWDER, 10000, 0,
                    new LootFunction[] { new SetCount(new LootCondition[0], new RandomValueRange(
                            ConfigAgent.getMinGunpowderDrop(),
                            ConfigAgent.getMaxGunpowderDrop() )) },
                    new LootCondition[] { new RandomChance(1)},
                    "minecraft:gunpowder2"));
        }
    }
}
