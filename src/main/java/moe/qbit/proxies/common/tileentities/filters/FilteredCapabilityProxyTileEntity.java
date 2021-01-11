package moe.qbit.proxies.common.tileentities.filters;

import moe.qbit.proxies.api.filtering.IConfigurableFilter;
import moe.qbit.proxies.common.tileentities.WrappingCapabilityProxyTileEntity;
import net.minecraft.block.BlockState;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;

import java.util.function.Function;

public class FilteredCapabilityProxyTileEntity<T,U> extends WrappingCapabilityProxyTileEntity {
    private final ItemStackHandler filterItemHandler = new ItemStackHandler(3){ protected void onContentsChanged(int slot){markDirty();} };
    private final IConfigurableFilter<U> filter;

    public FilteredCapabilityProxyTileEntity(TileEntityType<?> tileEntityTypeIn, Function<IItemHandler, IConfigurableFilter<U>> filterFactory) {
        super(tileEntityTypeIn);
        this.filter = filterFactory.apply(this.filterItemHandler);
    }

    public IItemHandler getFilterItemHandler() {
        return this.filterItemHandler;
    }

    public IConfigurableFilter<U> getFilter() {
        return this.filter;
    }

    @Override
    public void read(BlockState state, CompoundNBT tag) {
        this.filterItemHandler.deserializeNBT(tag.getCompound("filter"));
        this.filter.getFilterSettings().fromNumber(tag.getByte("filter_settings"));
        super.read(state, tag);
    }

    @Override
    public CompoundNBT write(CompoundNBT tag) {
        tag.put("filter", filterItemHandler.serializeNBT());
        tag.putByte("filter_settings", filter.getFilterSettings().toNumber());
        return super.write(tag);
    }


}
