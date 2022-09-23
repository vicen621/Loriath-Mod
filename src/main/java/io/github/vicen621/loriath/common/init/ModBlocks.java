package io.github.vicen621.loriath.common.init;

import io.github.vicen621.loriath.LoriathMod;
import io.github.vicen621.loriath.common.block.InfiniteTorchBlock;
import io.github.vicen621.loriath.common.block.InfiniteWallTorchBlock;
import net.minecraft.block.Block;
import net.minecraft.util.registry.Registry;

public class ModBlocks {

    public static final Block INFINITE_TORCH = register("infinite_torch", new InfiniteTorchBlock());
    public static final Block INFINITE_WALL_TORCH = register("infinite_wall_torch", new InfiniteWallTorchBlock());

    private static Block register(String name, Block block) {
        return Registry.register(Registry.BLOCK, LoriathMod.id(name), block);
    }

    public static void registerModBlocks() {
        LoriathMod.LOGGER.info("Registering Blocks for " + LoriathMod.MODID);
    }
}
