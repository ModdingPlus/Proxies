package moe.qbit.proxies.api.mergers;

import net.minecraft.item.ItemStack;
import net.minecraftforge.items.IItemHandler;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MergerItemHandler implements IItemHandler {
    public final List<IItemHandler> handlers = new ArrayList<>();

    public MergerItemHandler(IItemHandler... handlers) {
        this(Arrays.asList(handlers));
    }

    public MergerItemHandler(List<IItemHandler> handlers) {
        //flattening for optimization
        for(IItemHandler handler:handlers){
            if(handler instanceof MergerItemHandler)
                this.handlers.addAll(((MergerItemHandler) handler).handlers);
            else
                this.handlers.add(handler);
        }
    }

    @Override
    public int getSlots() {
        return handlers.stream().mapToInt(IItemHandler::getSlots).sum();
    }

    @Override
    @Nonnull
    public ItemStack getStackInSlot(int slot) {
        int currentBase=0;
        //Map slots
        for(IItemHandler h:handlers){
            if(currentBase+h.getSlots()<=slot)
                currentBase+=h.getSlots();
            else{
                return h.getStackInSlot(slot-currentBase);
            }
        }
        return ItemStack.EMPTY;
    }

    @Override
    @Nonnull
    public ItemStack insertItem(int slot, ItemStack stack, boolean simulate) {
        int currentBase=0;
        //Map slots
        for(IItemHandler h:handlers){
            if(currentBase+h.getSlots()<=slot)
                currentBase+=h.getSlots();
            else{
                return h.insertItem(slot-currentBase, stack, simulate);
            }
        }
        return stack;
    }

    @Override
    @Nonnull
    public ItemStack extractItem(int slot, int amount, boolean simulate) {
        int currentBase=0;
        //Map slots
        for(IItemHandler h:handlers){
            if(currentBase+h.getSlots()<=slot)
                currentBase+=h.getSlots();
            else{
                return h.extractItem(slot-currentBase, amount, simulate);
            }
        }
        return ItemStack.EMPTY;
    }

    @Override
    public int getSlotLimit(int slot) {
        int currentBase=0;
        //Map slots
        for(IItemHandler h:handlers){
            if(currentBase+h.getSlots()<=slot)
                currentBase+=h.getSlots();
            else{
                return h.getSlotLimit(slot-currentBase);
            }
        }
        return 0;
    }

    @Override
    public boolean isItemValid(int slot, @Nonnull ItemStack stack) {
        int currentBase=0;
        //Map slots
        for(IItemHandler h:handlers){
            if(currentBase+h.getSlots()<=slot)
                currentBase+=h.getSlots();
            else{
                return h.isItemValid(slot-currentBase, stack);
            }
        }
        return false;
    }
}
