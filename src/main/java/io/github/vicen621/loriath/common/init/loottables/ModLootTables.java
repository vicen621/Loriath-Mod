package io.github.vicen621.loriath.common.init.loottables;

import io.github.vicen621.loriath.LoriathMod;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.entry.LootPoolEntry;
import net.minecraft.loot.entry.LootTableEntry;
import net.minecraft.util.Identifier;

import java.util.Arrays;
import java.util.List;

public class ModLootTables {
    public static final List<Identifier> INJECT_TABLE_IDS = Arrays.asList(
            new Identifier("chests/village/village_armorer"),
            new Identifier("chests/village/village_butcher"),
            new Identifier("chests/village/village_cartographer"),
            new Identifier("chests/village/village_fisher"),
            new Identifier("chests/village/village_fletcher"),
            new Identifier("chests/village/village_mason"),
            new Identifier("chests/village/village_shepherd"),
            new Identifier("chests/village/village_tannery"),
            new Identifier("chests/village/village_toolsmith"),
            new Identifier("chests/village/village_weaponsmith"),
            new Identifier("chests/abandoned_mineshaft"),
            new Identifier("chests/ancient_city"),
            new Identifier("chests/bastion_bridge"),
            new Identifier("chests/bastion_hoglin_stable"),
            new Identifier("chests/bastion_other"),
            new Identifier("chests/bastion_treasure"),
            new Identifier("chests/desert_pyramid"),
            new Identifier("chests/end_city_treasure"),
            new Identifier("chests/jungle_temple"),
            new Identifier("chests/nether_bridge"),
            new Identifier("chests/pillager_outpost"),
            new Identifier("chests/ruined_portal"),
            new Identifier("chests/shipwreck_map"),
            new Identifier("chests/shipwreck_supply"),
            new Identifier("chests/simple_dungeon"),
            new Identifier("chests/stronghold_corridor"),
            new Identifier("chests/stronghold_crossing"),
            new Identifier("chests/stronghold_library"),
            new Identifier("chests/underwater_ruin_big"),
            new Identifier("chests/underwater_ruin_small")
    );

    public static void onLootTableLoad(Identifier id, LootTable.Builder supplier) {
        if (INJECT_TABLE_IDS.contains(id)) {
            supplier.pool(LootPool.builder().with(getInjectEntry(id.getPath())));
        }

        if (id.getPath().contains("chests")) {
            Identifier table = LoriathMod.id("inject/maricoin_chests");
            supplier.pool(LootPool.builder().with(LootTableEntry.builder(table).weight(1)));
        }
    }

    private static LootPoolEntry.Builder<?> getInjectEntry(String name) {
        Identifier table = LoriathMod.id("inject/" + name);
        return LootTableEntry.builder(table).weight(1);
    }
}
