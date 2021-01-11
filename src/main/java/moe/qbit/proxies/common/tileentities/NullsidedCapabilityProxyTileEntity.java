package moe.qbit.proxies.common.tileentities;

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
import java.util.HashMap;

@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public class NullsidedCapabilityProxyTileEntity extends CapabilityProxyTileEntity {
    private final HashMap<Capability<?>, CapabilityPointer<?>> pointers = new HashMap<>();

    public NullsidedCapabilityProxyTileEntity(TileEntityType<?> tileEntityTypeIn, Capability<?>... supportedCapabilities) {
        super(tileEntityTypeIn, supportedCapabilities);
    }

    @Override
    public <T> CapabilityPointer<T> getProxyCapabilityPointer(Capability<T> capability, @Nullable Direction side, int chainIndex) {
        Direction facing = this.getBlockState().get(DirectionalBlock.FACING);
        if(!pointers.containsKey(capability)) {
            if(this.supportedCapabilities.contains(capability))
                pointers.put(capability, CapabilityPointer.<T>of(this.getWorld(), this.getPos().offset(facing), null));
            else
                pointers.put(capability, CapabilityPointer.<T>empty());
        }
        //noinspection unchecked
        return (CapabilityPointer<T>)pointers.get(capability);
    }

    @Override
    public int getMaxProxyChainLength(@Nonnull Capability<?> cap, @Nullable Direction side) {
        return ServerConfiguration.CONFIGURATION.chain_length_limit.get();
    }
}
