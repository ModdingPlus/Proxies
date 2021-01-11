package moe.qbit.proxies.api.filtering;

import net.minecraft.item.ItemStack;
import net.minecraftforge.items.IItemHandler;

import javax.annotation.Nonnull;

public class FilteredItemHandlerWrapper implements IItemHandler {
    IFilter<ItemStack> filter;
    IItemHandler target;

    public FilteredItemHandlerWrapper(IFilter<ItemStack> filter, IItemHandler target) {
        this.filter = filter;
        this.target = target;
    }

    @Override
    public int getSlots() {
        return target.getSlots();
    }

    @Override
    public ItemStack getStackInSlot(int slot) {
        return target.getStackInSlot(slot);
    }

    @Override
    public ItemStack insertItem(int slot, ItemStack stack, boolean simulate) {
        if (this.filter.test(stack))
            return target.insertItem(slot, stack, simulate);
        else
            return stack;
    }

    @Override
    public ItemStack extractItem(int slot, int amount, boolean simulate) {
        if (this.filter.test(target.getStackInSlot(slot)))
            return target.extractItem(slot, amount, simulate);
        else
            return ItemStack.EMPTY;
    }

    @Override
    public int getSlotLimit(int slot) {
        return target.getSlotLimit(slot);
    }

    @Override
    public boolean isItemValid(int slot, @Nonnull ItemStack stack) {
        return this.filter.test(stack) && target.isItemValid(slot, stack);
    }

}
