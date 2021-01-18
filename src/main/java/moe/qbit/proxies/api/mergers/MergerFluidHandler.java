package moe.qbit.proxies.api.mergers;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.items.IItemHandler;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MergerFluidHandler implements IFluidHandler {
    public final List<IFluidHandler> handlers = new ArrayList<>();

    public MergerFluidHandler(IFluidHandler... handlers) {
        this(Arrays.asList(handlers));
    }

    public MergerFluidHandler(List<IFluidHandler> handlers) {
        //flattening for optimization
        for(IFluidHandler handler:handlers){
            if(handler instanceof MergerFluidHandler)
                this.handlers.addAll(((MergerFluidHandler) handler).handlers);
            else
                this.handlers.add(handler);
        }
    }

    @Override
    public int getTanks() {
        return handlers.stream().mapToInt(IFluidHandler::getTanks).sum();
    }

    @Nonnull
    @Override
    public FluidStack getFluidInTank(int tank) {
        int currentBase=0;
        //Map slots
        for(IFluidHandler h:handlers){
            if(currentBase+h.getTanks()<=tank)
                currentBase+=h.getTanks();
            else{
                return h.getFluidInTank(tank-currentBase);
            }
        }
        return FluidStack.EMPTY;
    }

    @Override
    public int getTankCapacity(int tank) {
        int currentBase=0;
        //Map slots
        for(IFluidHandler h:handlers){
            if(currentBase+h.getTanks()<=tank)
                currentBase+=h.getTanks();
            else{
                return h.getTankCapacity(tank-currentBase);
            }
        }
        return 0;
    }

    @Override
    public boolean isFluidValid(int tank, @Nonnull FluidStack stack) {
        int currentBase=0;
        //Map slots
        for(IFluidHandler h:handlers){
            if(currentBase+h.getTanks()<=tank)
                currentBase+=h.getTanks();
            else{
                return h.isFluidValid(tank-currentBase, stack);
            }
        }
        return false;
    }

    @Override
    public int fill(FluidStack resource, FluidAction action) {
        int filledAmount = 0;

        for(IFluidHandler h:handlers){
            FluidStack currentStack = resource.copy();
            currentStack.setAmount(currentStack.getAmount()-filledAmount);

            if(currentStack.isEmpty())
                break;

            filledAmount += h.fill(currentStack, action);
        }
        return filledAmount;
    }

    @Nonnull
    @Override
    public FluidStack drain(FluidStack resource, FluidAction action) {
        int drainedAmount = 0;

        for(IFluidHandler h:handlers){
            FluidStack currentStack = resource.copy();
            currentStack.setAmount(resource.getAmount()-drainedAmount);

            drainedAmount += h.drain(currentStack, action).getAmount();
        }

        FluidStack returnStack = resource.copy();
        returnStack.setAmount(drainedAmount);
        return returnStack;
    }

    @Nonnull
    @Override
    public FluidStack drain(int maxDrain, FluidAction action) {
        for(IFluidHandler h:handlers){
            FluidStack currentStack = h.drain(maxDrain, action);
            if(!currentStack.isEmpty())
                return currentStack;
        }
        return FluidStack.EMPTY;
    }
}
