package io.github.vicen621.loriath.common.item;

import io.github.vicen621.loriath.common.init.ModBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.block.TorchBlock;
import net.minecraft.block.WallTorchBlock;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;

//TODO Hacer el TorchBlock direccionable :)
public class InfiniteTorchItem /* extends BlockItem*/ {

   /* public InfiniteTorchItem(Settings settings) {
        super(ModBlocks.INFINITE_TORCH, settings);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        World world = context.getWorld();
        if (world instanceof ClientWorld || context.getSide() == Direction.DOWN)
            return ActionResult.PASS;

        if (context.getSide() == Direction.UP) {
            TorchBlock torch = (TorchBlock) ModBlocks.INFINITE_TORCH;
            BlockState torckBlockState = torch.getPlacementState(new ItemPlacementContext(context));
            BlockPos clickedPos = context.getBlockPos().add(context.getSide().getVector());

            if (!torch.canPlaceAt(torckBlockState, world, clickedPos))
                return ActionResult.PASS;
        } else {
            WallTorchBlock torch = (WallTorchBlock) ModBlocks.INFINITE_WALL_TORCH;
            BlockState torckBlockState = torch.getPlacementState(new ItemPlacementContext(context));
            BlockPos clickedPos = context.getBlockPos().add(context.getSide().getVector());

            if (!torch.canPlaceAt(torckBlockState, world, clickedPos))
                return ActionResult.PASS;
        }

        return super.useOnBlock(context);
    }*/
}
