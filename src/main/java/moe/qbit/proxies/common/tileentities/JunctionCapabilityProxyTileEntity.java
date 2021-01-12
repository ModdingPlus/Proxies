package moe.qbit.proxies.common.tileentities;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import mcp.MethodsReturnNonnullByDefault;
import moe.qbit.proxies.api.CapabilityPointer;
import moe.qbit.proxies.api.CapabilityProxyTileEntity;
import moe.qbit.proxies.api.NullableHashBasedTable;
import moe.qbit.proxies.configuration.ServerConfiguration;
import net.minecraft.block.DirectionalBlock;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.HashMap;
import java.util.Optional;

@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public class JunctionCapabilityProxyTileEntity extends CommonCapabilityProxyTileEntity {
    private final NullableHashBasedTable<Capability<?>, Direction, CapabilityPointer<?>> pointers = new NullableHashBasedTable<>();

    public JunctionCapabilityProxyTileEntity(TileEntityType<?> tileEntityTypeIn, Capability<?>... supportedCapabilities) {
        super(tileEntityTypeIn, supportedCapabilities);
    }

    @Override
    public <T> CapabilityPointer<T> getProxyCapabilityPointer(Capability<T> capability, @Nullable Direction accessedSide, @Nullable Direction actualSide, int chainIndex) {
        if(!pointers.contains(capability, accessedSide)) {
            if(this.supportedCapabilities.contains(capability) && actualSide!=null)
                pointers.put(capability, actualSide, CapabilityPointer.<T>of(this.getWorld(), this.getPos().offset(actualSide.getOpposite()), actualSide));
            else
                pointers.put(capability, actualSide, CapabilityPointer.<T>empty());
        }
        //noinspection unchecked
        return (CapabilityPointer<T>)pointers.get(capability, actualSide);
    }
}
