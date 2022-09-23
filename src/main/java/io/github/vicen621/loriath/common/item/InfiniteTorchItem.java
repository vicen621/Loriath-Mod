package io.github.vicen621.loriath.common.item;

import io.github.vicen621.loriath.common.init.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.item.Item;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;

public class InfiniteTorchItem extends Item {

    public InfiniteTorchItem(Settings settings) {
        super(settings);
    }

    /*
     * TODO:
     *  Hacer que el bloque que ponga no dropee la antorcha al romperse
     *  Agregarle textura
     *  Agregarle el sonido cuando la ponen
     *  Fijarse si el bloque donde se va a poner es aire
     */
    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        World world = context.getWorld();
        if (world instanceof ClientWorld || context.getSide() == Direction.DOWN)
            return ActionResult.PASS;

        Block torch = context.getSide() == Direction.UP ? ModBlocks.INFINITE_TORCH : ModBlocks.INFINITE_WALL_TORCH;
        BlockPos clickedPos = context.getBlockPos().add(context.getSide().getVector());

        if (!world.getBlockState(clickedPos).isAir())
            return ActionResult.PASS;

        world.setBlockState(clickedPos, torch.getPlacementState(new ItemPlacementContext(context)));
        return ActionResult.SUCCESS;
    }
}
