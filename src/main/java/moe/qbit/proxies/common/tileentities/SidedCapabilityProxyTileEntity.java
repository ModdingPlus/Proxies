package moe.qbit.proxies.common.tileentities;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import mcp.MethodsReturnNonnullByDefault;
import moe.qbit.proxies.api.CapabilityPointer;
import moe.qbit.proxies.api.CapabilityProxyTileEntity;
import moe.qbit.proxies.configuration.ServerConfiguration;
import net.minecraft.block.DirectionalBlock;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public class SidedCapabilityProxyTileEntity extends CapabilityProxyTileEntity {
    private final Table<Capability<?>,Direction, CapabilityPointer<?>> pointers = HashBasedTable.create();

    public SidedCapabilityProxyTileEntity(TileEntityType<?> tileEntityTypeIn, Capability<?>... supportedCapabilities) {
        super(tileEntityTypeIn, supportedCapabilities);
    }

    @Override
    public <T> CapabilityPointer<T> getProxyCapabilityPointer(Capability<T> capability, @Nullable Direction side, int chainIndex) {
        Direction facing = this.getBlockState().get(DirectionalBlock.FACING);
        if(!pointers.contains(capability, side)) {
            if(this.supportedCapabilities.contains(capability))
                pointers.put(capability, side, CapabilityPointer.<T>of(this.getWorld(), this.getPos().offset(facing), side));
            else
                pointers.put(capability, side, CapabilityPointer.<T>empty());
        }
        //noinspection unchecked
        return (CapabilityPointer<T>)pointers.get(capability, side);
    }

    @Override
    public int getMaxProxyChainLength(@Nonnull Capability<?> cap, @Nullable Direction side) {
        return ServerConfiguration.CONFIGURATION.chain_length_limit.get();
    }
}
