package moe.qbit.proxies.common.tileentities;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import mcp.MethodsReturnNonnullByDefault;
import moe.qbit.proxies.api.CapabilityPointer;
import moe.qbit.proxies.api.CapabilityProxyTileEntity;
import moe.qbit.proxies.api.NullableHashBasedTable;
import moe.qbit.proxies.common.blocks.CapabilityProxyBlock;
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
public class SidedCapabilityProxyTileEntity extends CommonCapabilityProxyTileEntity {
    private final NullableHashBasedTable<Capability<?>, Direction, CapabilityPointer<?>> pointers = new NullableHashBasedTable<>();

    public SidedCapabilityProxyTileEntity(TileEntityType<?> tileEntityTypeIn, Capability<?>... supportedCapabilities) {
        super(tileEntityTypeIn, supportedCapabilities);
    }

    @Override
    public <T> CapabilityPointer<T> getProxyCapabilityPointer(Capability<T> capability, @Nullable Direction accessedSide, @Nullable Direction actualSide, int chainIndex) {
        Direction facing = this.getBlockState().get(CapabilityProxyBlock.FACING);
        if(!pointers.contains(capability, accessedSide)) {
            if(this.supportedCapabilities.contains(capability))
                pointers.put(capability, accessedSide, CapabilityPointer.<T>of(this.getWorld(), this.getPos().offset(facing), accessedSide, facing.getOpposite()));
            else
                pointers.put(capability, accessedSide, CapabilityPointer.<T>empty());
        }
        //noinspection unchecked
        return (CapabilityPointer<T>)pointers.get(capability, accessedSide);
    }
}
