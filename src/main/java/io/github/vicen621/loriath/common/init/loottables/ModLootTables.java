package io.github.vicen621.loriath.common.init.loottables;

import io.github.vicen621.loriath.Loriath;
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
            new Identifier("chests/underwater_ruin_small"),
            new Identifier("betterdungeons", "skeleton_dungeon/chests/common"),
            new Identifier("betterdungeons", "skeleton_dungeon/chests/special"),
            new Identifier("betterdungeons", "zombie_dungeon/chests/common"),
            new Identifier("betterdungeons", "zombie_dungeon/chests/special"),
            new Identifier("betterstrongholds", "chest/common"),
            new Identifier("betterstrongholds", "chest/special"),
            new Identifier("structory", "harvest/graveyard"),
            new Identifier("structory", "harvest/graveyard2"),
            new Identifier("structory", "harvest/manor2/loot"),
            new Identifier("structory", "harvest/manor2/treasure"),
            new Identifier("structory", "harvest/old_manor/common"),
            new Identifier("structory", "harvest/old_manor/treasure"),
            new Identifier("structory", "library/high"),
            new Identifier("structory", "library/junk"),
            new Identifier("structory", "library/low"),
            new Identifier("structory", "mood/badlands"),
            new Identifier("structory", "mood/cave"),
            new Identifier("structory", "mood/desert"),
            new Identifier("structory", "mood/farmer"),
            new Identifier("structory", "mood/fisherman"),
            new Identifier("structory", "mood/miner"),
            new Identifier("structory", "mood/mushroom"),
            new Identifier("structory", "mood/ocean"),
            new Identifier("structory", "mood/snowy"),
            new Identifier("structory", "mood/taiga"),
            new Identifier("structory", "outcast/bandit/desert"),
            new Identifier("structory", "outcast/boat/loot"),
            new Identifier("structory", "outcast/farm_ruin"),
            new Identifier("structory", "outcast/generic/bandit"),
            new Identifier("structory", "outcast/generic/miner"),
            new Identifier("structory", "outcast/low"),
            new Identifier("structory", "outcast/mine/loot"),
            new Identifier("structory", "outcast/ruin/ruin"),
            new Identifier("structory", "outcast/ruin/taiga/illager_high"),
            new Identifier("structory", "outcast/ruin/taiga/illager_low"),
            new Identifier("structory", "outcast/settlement"),
            new Identifier("structory", "ruin/swamp/loot"),
            new Identifier("structory", "ruin/taiga/illager_treasure"),
            new Identifier("structory", "ruin/taiga/loot"),
            new Identifier("erralith", "underground/chest")
    );

    public static void onLootTableLoad(Identifier id, LootTable.Builder supplier) {
        if (INJECT_TABLE_IDS.contains(id)) {
            supplier.pool(LootPool.builder().with(getInjectEntry(id)));
        }

        if ((id.getPath().contains("chests") && !id.getNamespace().equals("loriath")) || id.getNamespace().equals("structory") || (id.getNamespace().equals("terralith") && !id.getPath().contains("entities"))) {
            Identifier table = Loriath.id("inject/maricoin_chests");
            supplier.pool(LootPool.builder().with(LootTableEntry.builder(table).weight(1)));
        }
    }

    private static LootPoolEntry.Builder<?> getInjectEntry(Identifier id) {
        Identifier table = Loriath.id("inject/" + id.getNamespace() + "/" + id.getPath());
        return LootTableEntry.builder(table).weight(1);
    }
}
