package moe.qbit.proxies.common.blocks;

import moe.qbit.proxies.api.CapabilityProxyTileEntity;
import net.minecraft.block.BlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;

import java.util.function.Supplier;

public class JunctionCapabilityProxyBlock extends CommonProxyBlock {

    protected JunctionCapabilityProxyBlock(Properties builder, Supplier<TileEntity> tileEntityFactory) {
        super(builder, tileEntityFactory);
    }

    @Override
    public BlockState updatePostPlacement(BlockState stateIn, Direction facing, BlockState facingState, IWorld worldIn, BlockPos currentPos, BlockPos facingPos) {
        TileEntity tileEntity = worldIn.getTileEntity(currentPos);
        if(tileEntity instanceof CapabilityProxyTileEntity){
            ((CapabilityProxyTileEntity)tileEntity).invalidateCachedHandlers();
        }
        return super.updatePostPlacement(stateIn, facing, facingState, worldIn, currentPos, facingPos);
    }
}
