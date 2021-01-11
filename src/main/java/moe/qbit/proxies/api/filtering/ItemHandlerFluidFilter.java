package moe.qbit.proxies.api.filtering;

import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.BucketItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.IFluidTank;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.items.IItemHandler;

import javax.annotation.Nonnull;

public class ItemHandlerFluidFilter implements IConfigurableFilter<FluidStack> {
    @CapabilityInject(IFluidHandler.class)
    static Capability<IFluidHandler> FLUID_HANDLER_CAPABILITY = null;

    private IItemHandler handler;
    private FilterSettings filterSettings;

    public ItemHandlerFluidFilter(@Nonnull IItemHandler handler) {
        this.handler = handler;
        this.filterSettings = new FilterSettings();
    }
    public ItemHandlerFluidFilter(@Nonnull IItemHandler handler, @Nonnull FilterSettings filterSettings) {
        this.handler = handler;
        this.filterSettings = filterSettings;
    }

    @Override
    public boolean test(@Nonnull FluidStack stack) {
        NonNullList<FluidStack> filterStacks = getAllFluidStacksFromItemHandler(handler, false);
        for(FluidStack filterStack : filterStacks){
            boolean hit = this.filterSettings.isIgnoreNBT() ? stack.getFluid() == filterStack.getFluid() : stack.isFluidEqual(filterStack);
            if(hit)
                return this.filterSettings.isWhitelist();
        }
        return !this.filterSettings.isWhitelist();
    }

    private static NonNullList<FluidStack> getAllFluidStacksFromItemHandler(IItemHandler handler, boolean includeEmptyStacks){
        NonNullList<FluidStack> ret = NonNullList.create();
        for(int i=0; i < handler.getSlots(); i++){
            ItemStack stack = handler.getStackInSlot(i);

            if(!stack.isEmpty()) {
                final Item item = stack.getItem();
                final LazyOptional<IFluidHandler> capability = stack.getCapability(FLUID_HANDLER_CAPABILITY, null);
                if(capability.isPresent()) {
                    IFluidHandler fluidHandler = capability.orElseThrow(() -> new IllegalStateException("Capability missing exception."));
                    for(int tank=0; tank<fluidHandler.getTanks(); tank++){
                        FluidStack fluidInTank = fluidHandler.getFluidInTank(tank);
                        if(!fluidInTank.isEmpty()){
                            ret.add(fluidInTank);
                        }else if(includeEmptyStacks) {
                            ret.add(FluidStack.EMPTY);
                        }
                    }
                } else if(item instanceof IFluidTank) {
                    FluidStack fluidInTank = ((IFluidTank) item).getFluid();
                    if (!fluidInTank.isEmpty()) {
                        ret.add(fluidInTank);
                    } else if(includeEmptyStacks) {
                        ret.add(FluidStack.EMPTY);
                    }
                } else if(item instanceof BucketItem) {
                    Fluid bucketFluid = ((BucketItem) item).getFluid();
                    if (bucketFluid != Fluids.EMPTY) {
                        ret.add(new FluidStack(bucketFluid, 1000));
                    } else if(includeEmptyStacks) {
                        ret.add(FluidStack.EMPTY);
                    }
                }
            }else if(includeEmptyStacks){
                ret.add(FluidStack.EMPTY);
            }
        }
        return ret;
    }

    @Override
    public FilterSettings getFilterSettings(){
        return this.filterSettings;
    }
}
