package moe.qbit.proxies.common.blocks;

import moe.qbit.proxies.api.CapabilityProxyTileEntity;
import moe.qbit.proxies.common.tileentities.CommonCapabilityProxyTileEntity;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.DirectionalBlock;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;

import java.util.function.Supplier;

public class CapabilityProxyBlock extends CommonProxyBlock {
    public static final DirectionProperty FACING = DirectionalBlock.FACING;

    protected CapabilityProxyBlock(Properties builder, Supplier<TileEntity> tileEntityFactory) {
        super(builder, tileEntityFactory);
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        super.fillStateContainer(builder.add(FACING));
    }

    @Override
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        return this.getDefaultState().with(FACING, context.getFace().getOpposite());
    }

    @Override
    public BlockState updatePostPlacement(BlockState stateIn, Direction facing, BlockState facingState, IWorld worldIn, BlockPos currentPos, BlockPos facingPos) {
        if(!worldIn.isRemote() && stateIn.get(FACING)==facing){
            TileEntity tileEntity = worldIn.getTileEntity(currentPos);
            if(tileEntity instanceof CommonCapabilityProxyTileEntity){
                ((CapabilityProxyTileEntity)tileEntity).invalidateCachedHandlers();
            }
        }
        return super.updatePostPlacement(stateIn, facing, facingState, worldIn, currentPos, facingPos);
    }
}
