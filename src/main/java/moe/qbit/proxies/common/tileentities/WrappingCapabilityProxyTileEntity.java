package moe.qbit.proxies.common.tileentities;

import com.google.common.collect.Maps;
import moe.qbit.proxies.api.CapabilityPointer;
import net.minecraft.block.DirectionalBlock;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;

import javax.annotation.Nullable;
import java.util.Map;
import java.util.function.Function;

public class WrappingCapabilityProxyTileEntity extends RegularCapabilityProxyTileEntity {
    private final Map<Capability<?>,Function<?,?>> wrapperFunctions = Maps.newHashMap();

    public WrappingCapabilityProxyTileEntity(TileEntityType<?> tileEntityTypeIn) {
        super(tileEntityTypeIn);
    }

    public <T> WrappingCapabilityProxyTileEntity addWrapperFunction(Capability<T> capability, Function<T,T> wrapperFunction){
        this.wrapperFunctions.put(capability, wrapperFunction);
        return this;
    }

    @Override
    public <T> CapabilityPointer<T> getProxyCapabilityPointer(Capability<T> capability, @Nullable Direction side, int chainIndex) {
        Direction facing = this.getBlockState().get(DirectionalBlock.FACING);
        if(!pointers.containsKey(capability)) {
            if(this.wrapperFunctions.containsKey(capability))
                // noinspection ConstantConditions,unchecked,unchecked
                pointers.put(capability, CapabilityPointer.of(
                        this.getWorld(),
                        this.getPos().offset(facing),
                        facing.getOpposite(),
                        (Function<T, T>) this.wrapperFunctions.get(capability)));
            else
                pointers.put(capability, CapabilityPointer.<T>empty());
        }
        //noinspection unchecked
        return (CapabilityPointer<T>)pointers.get(capability);
    }
}
