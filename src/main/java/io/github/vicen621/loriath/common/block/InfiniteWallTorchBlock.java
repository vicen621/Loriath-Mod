package io.github.vicen621.loriath.common.block;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Blocks;
import net.minecraft.block.WallTorchBlock;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.BlockSoundGroup;

public class InfiniteWallTorchBlock extends WallTorchBlock {

    public InfiniteWallTorchBlock() {
        super(FabricBlockSettings.copyOf(Blocks.TORCH)
                .luminance((state) -> 14).sounds(BlockSoundGroup.WOOD).dropsNothing().nonOpaque(), ParticleTypes.FLAME);
    }
}
