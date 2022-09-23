package io.github.vicen621.loriath.common.block;

import net.minecraft.block.Material;
import net.minecraft.block.WallTorchBlock;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.BlockSoundGroup;

public class InfiniteWallTorchBlock extends WallTorchBlock {

    public InfiniteWallTorchBlock() {
        super(Settings.of(Material.DECORATION).noCollision().breakInstantly()
                .luminance((state) -> 14).sounds(BlockSoundGroup.WOOD).dropsNothing().nonOpaque(), ParticleTypes.FLAME);
    }
}
