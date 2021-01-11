package moe.qbit.proxies.api.filtering;

import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.IFluidHandler;

import javax.annotation.Nonnull;

public class FilteredFluidHandlerWrapper implements IFluidHandler {
    IFilter<FluidStack> filter;
    IFluidHandler target;

    public FilteredFluidHandlerWrapper(IFilter<FluidStack> filter, IFluidHandler target) {
        this.filter = filter;
        this.target = target;
    }

    @Override
    public int getTanks() {
        return target.getTanks();
    }

    @Nonnull
    @Override
    public FluidStack getFluidInTank(int tank) {
        return target.getFluidInTank(tank);
    }

    @Override
    public int getTankCapacity(int tank) {
        return target.getTankCapacity(tank);
    }

    @Override
    public boolean isFluidValid(int tank, @Nonnull FluidStack stack) {
        return target.isFluidValid(tank, stack);
    }

    @Override
    public int fill(FluidStack resource, FluidAction action) {
        if (this.filter.test(resource))
            return target.fill(resource, action);
        else
            return 0;
    }

    @Nonnull
    @Override
    public FluidStack drain(FluidStack resource, FluidAction action) {
        if (this.filter.test(resource))
            return target.drain(resource, action);
        else
            return FluidStack.EMPTY;
    }

    @Nonnull
    @Override
    public FluidStack drain(int maxDrain, FluidAction action) {
        FluidStack drainedSimulated = target.drain(maxDrain,FluidAction.SIMULATE);
        // TODO: maybe make this try to drain another fluid in the handler using IFluidHandler#drain(FluidStack,FluidAction)
        // currently returns empty when the simulated drained fluid doesn't match the filter
        if(this.filter.test(drainedSimulated)){
            if (action.simulate())
                return drainedSimulated;
            else
                return target.drain(maxDrain, action);
        } else {
            return FluidStack.EMPTY;
        }
    }
}
