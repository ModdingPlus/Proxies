package dev.necro.proxies.common.tileentities;

import dev.necro.proxies.CapabilityPointer;
import dev.necro.proxies.CapabilityProxyTileEntity;
import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.block.DirectionalBlock;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;

import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.HashMap;

@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public class RegularCapabilityProxyTileEntity extends CapabilityProxyTileEntity {
    private final HashMap<Capability<?>, CapabilityPointer<?>> pointers = new HashMap<>();

    public RegularCapabilityProxyTileEntity(TileEntityType<?> tileEntityTypeIn, Capability<?>... supportedCapabilities) {
        super(tileEntityTypeIn, supportedCapabilities);
    }

    @Override
    public <T> CapabilityPointer<T> getProxyCapabilityPointer(Capability<T> capability, @Nullable Direction side) {
        Direction facing = this.getBlockState().get(DirectionalBlock.FACING);
        if(!pointers.containsKey(capability)) {
            if(this.supportedCapabilities.contains(capability))
                pointers.put(capability, CapabilityPointer.<T>of(this.getWorld(), this.getPos().offset(facing), facing.getOpposite()));
            else
                pointers.put(capability, CapabilityPointer.<T>empty());
        }
        //noinspection unchecked
        return (CapabilityPointer<T>)pointers.get(capability);
    }
}
