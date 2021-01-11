package moe.qbit.proxies.common.tileentities.filters;

import moe.qbit.proxies.api.filtering.FilteredFluidHandlerWrapper;
import moe.qbit.proxies.api.filtering.ItemHandlerFluidFilter;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.IFluidHandler;

public class FilteredFluidProxyTileEntity extends FilteredCapabilityProxyTileEntity<IFluidHandler, FluidStack> {
    @CapabilityInject(IFluidHandler.class)
    static Capability<IFluidHandler> FLUID_HANDLER_CAPABILITY = null;

    public FilteredFluidProxyTileEntity(TileEntityType<?> tileEntityTypeIn) {
        super(tileEntityTypeIn, ItemHandlerFluidFilter::new);
        this.addWrapperFunction(FLUID_HANDLER_CAPABILITY, (handler)-> new FilteredFluidHandlerWrapper(this.getFilter(), handler));
    }
}
