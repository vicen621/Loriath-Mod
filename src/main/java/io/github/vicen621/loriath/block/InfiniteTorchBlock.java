package io.github.vicen621.loriath.block;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Blocks;
import net.minecraft.block.TorchBlock;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.BlockSoundGroup;

public class InfiniteTorchBlock extends TorchBlock {

    public InfiniteTorchBlock() {
        super(FabricBlockSettings.copyOf(Blocks.TORCH)
                .luminance((state) -> 14).dropsNothing().nonOpaque().sounds(BlockSoundGroup.WOOD), ParticleTypes.FLAME);
    }
}
