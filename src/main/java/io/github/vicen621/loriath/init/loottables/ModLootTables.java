package io.github.vicen621.loriath.init.loottables;

import io.github.vicen621.loriath.Loriath;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.entry.LootPoolEntry;
import net.minecraft.loot.entry.LootTableEntry;
import net.minecraft.util.Identifier;

import java.util.Arrays;
import java.util.List;

public class ModLootTables {
    public static void onLootTableLoad(Identifier id, LootTable.Builder supplier) {
        if ((id.getPath().contains("chests") && !id.getNamespace().equals("loriath")) || id.getNamespace().equals("structory") || (id.getNamespace().equals("terralith") && !id.getPath().contains("entities"))) {
            Identifier table = Loriath.id("inject/draft");
            supplier.pool(LootPool.builder().with(LootTableEntry.builder(table).weight(1)));
        }
    }
}
