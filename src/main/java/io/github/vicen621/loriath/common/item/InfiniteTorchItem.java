package io.github.vicen621.loriath.common.item;

import io.github.vicen621.loriath.common.init.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.item.Item;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.sound.SoundCategory;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;

public class InfiniteTorchItem extends Item {

    public InfiniteTorchItem(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        World world = context.getWorld();
        if (world instanceof ClientWorld || context.getSide() == Direction.DOWN)
            return ActionResult.PASS;

        Block torch = context.getSide() == Direction.UP ? ModBlocks.INFINITE_TORCH : ModBlocks.INFINITE_WALL_TORCH;
        BlockState torckBlockState = torch.getPlacementState(new ItemPlacementContext(context));
        BlockPos clickedPos = context.getBlockPos().add(context.getSide().getVector());

        if (!torch.canPlaceAt(torckBlockState, world, clickedPos))
            return ActionResult.PASS;

        if (world.setBlockState(clickedPos, torckBlockState)) {
            world.playSound(null, clickedPos, BlockSoundGroup.WOOD.getPlaceSound(), SoundCategory.BLOCKS, 1, 1);
            return ActionResult.SUCCESS;
        }

        return ActionResult.FAIL;
    }
}
