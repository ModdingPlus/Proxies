package dev.necro.proxies.common.tileentities;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import dev.necro.proxies.CapabilityPointer;
import dev.necro.proxies.CapabilityProxyTileEntity;
import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.block.DirectionalBlock;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;

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
    public <T> CapabilityPointer<T> getProxyCapabilityPointer(Capability<T> capability, @Nullable Direction side) {
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
}
