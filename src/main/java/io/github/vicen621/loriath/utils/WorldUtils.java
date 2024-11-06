package io.github.vicen621.loriath.utils;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;

public class WorldUtils {

    public static boolean isEntityOutside(Entity entity) {
        World level = entity.getWorld();
        return level.isSkyVisible(new BlockPos(entity.getBlockPos()));
    }

    public static boolean isRainingAtEntityBiome(Entity entity) {
        World level = entity.getWorld();
        BlockPos pos = entity.getBlockPos();
        Biome biome = level.getBiome(new BlockPos(pos)).value();
        return level.isRaining() && biome.getPrecipitation(pos) == Biome.Precipitation.RAIN && biome.doesNotSnow(new BlockPos(entity.getBlockPos()));
    }

    public static boolean isEntityOutsideWhenItIsRaining(Entity entity) {
        return isEntityOutside(entity) && isRainingAtEntityBiome(entity);
    }
}
