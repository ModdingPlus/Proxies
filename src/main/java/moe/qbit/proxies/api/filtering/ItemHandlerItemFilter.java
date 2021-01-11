package moe.qbit.proxies.api.filtering;

import net.minecraft.inventory.container.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraftforge.items.IItemHandler;

import javax.annotation.Nonnull;

public class ItemHandlerItemFilter implements IConfigurableFilter<ItemStack> {
    private IItemHandler handler;
    private FilterSettings filterSettings;

    public ItemHandlerItemFilter(@Nonnull IItemHandler handler) {
        this.handler = handler;
        this.filterSettings = new FilterSettings();
    }
    public ItemHandlerItemFilter(@Nonnull IItemHandler handler, @Nonnull FilterSettings filterSettings) {
        this.handler = handler;
        this.filterSettings = filterSettings;
    }

    @Override
    public boolean test(@Nonnull ItemStack stack) {
        NonNullList<ItemStack> filterStacks = getStacksFromItemHandler(handler, false);
        for(ItemStack filterStack : filterStacks){
            boolean hit = this.filterSettings.isIgnoreNBT() ? stack.getItem()==filterStack.getItem() : Container.areItemsAndTagsEqual(filterStack, stack);
            if(hit)
                return this.filterSettings.isWhitelist();
        }
        return !this.filterSettings.isWhitelist();
    }

    private static NonNullList<ItemStack> getStacksFromItemHandler(IItemHandler handler, boolean includeEmptyStacks){
        NonNullList<ItemStack> ret = NonNullList.create();
        for(int i=0; i < handler.getSlots(); i++){
            ItemStack stack = handler.getStackInSlot(i);
            if(includeEmptyStacks || !stack.isEmpty()){
                ret.add(stack);
            }
        }
        return ret;
    }

    @Override
    public FilterSettings getFilterSettings(){
        return this.filterSettings;
    }
}
