package io.github.vicen621.loriath.common.block;

import net.minecraft.block.Material;
import net.minecraft.block.TorchBlock;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.BlockSoundGroup;

public class InfiniteTorchBlock extends TorchBlock {

    public InfiniteTorchBlock() {
        super(Settings.of(Material.DECORATION).noCollision().breakInstantly()
                .luminance((state) -> 14).dropsNothing().nonOpaque().sounds(BlockSoundGroup.WOOD), ParticleTypes.FLAME);
    }
}
