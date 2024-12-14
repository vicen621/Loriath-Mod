package io.github.vicen621.loriath.item;


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
