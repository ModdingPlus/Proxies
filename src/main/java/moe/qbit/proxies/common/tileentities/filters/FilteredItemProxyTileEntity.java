package moe.qbit.proxies.common.tileentities.filters;

import moe.qbit.proxies.api.filtering.FilteredItemHandlerWrapper;
import moe.qbit.proxies.api.filtering.ItemHandlerItemFilter;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.items.IItemHandler;

public class FilteredItemProxyTileEntity extends FilteredCapabilityProxyTileEntity<IItemHandler, ItemStack> {
    @CapabilityInject(IItemHandler.class)
    static Capability<IItemHandler> ITEM_HANDLER_CAPABILITY = null;

    public FilteredItemProxyTileEntity(TileEntityType<?> tileEntityTypeIn) {
        super(tileEntityTypeIn, ItemHandlerItemFilter::new);
        this.addWrapperFunction(ITEM_HANDLER_CAPABILITY, (handler)-> new FilteredItemHandlerWrapper(this.getFilter(), handler));
    }
}
