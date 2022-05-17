package io.github.vicen621.loriath.utils;

import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;

public class WorldUtils {

    public static boolean isEntityOutside(Entity entity) {
        World level = entity.world;
        return level.isSkyVisible(new BlockPos(entity.getPos()));
    }

    public static boolean isRainingAtEntityBiome(Entity entity) {
        World level = entity.world;
        Biome biome = level.getBiome(new BlockPos(entity.getPos())).value();
        return level.isRaining() && biome.getPrecipitation() == Biome.Precipitation.RAIN && biome.doesNotSnow(new BlockPos(entity.getPos()));
    }

    public static boolean isEntityOutsideWhenItIsRaining(Entity entity) {
        return isEntityOutside(entity) && isRainingAtEntityBiome(entity);
    }
}
