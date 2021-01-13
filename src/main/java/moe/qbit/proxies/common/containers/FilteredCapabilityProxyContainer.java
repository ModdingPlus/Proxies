package moe.qbit.proxies.common.containers;

import moe.qbit.proxies.api.filtering.FilterSettings;
import moe.qbit.proxies.api.slots.PhantomSlot;
import moe.qbit.proxies.common.tileentities.filters.FilteredCapabilityProxyTileEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IntReferenceHolder;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class FilteredCapabilityProxyContainer extends BaseContainer {
    private FilteredCapabilityProxyTileEntity<?,?> proxy;

    private final FilterSettings filterSettings = new FilterSettings();

    public FilteredCapabilityProxyContainer(int windowId, World world, BlockPos pos, PlayerInventory playerInventory, PlayerEntity player) {
        super(ModContainers.FILTERED_CAPABILITY_PROXY.get(), windowId, playerInventory, world.getTileEntity(pos));

        if(tileEntity instanceof FilteredCapabilityProxyTileEntity) {
            this.proxy = (FilteredCapabilityProxyTileEntity<?,?>) tileEntity;
            this.addSlotRange(proxy.getFilterItemHandler(), 0, 62, 18, 3, 18, PhantomSlot::new);
        }
        this.layoutPlayerInventorySlots(this.playerInventoryHandler,8, 50);

        trackInt(new IntReferenceHolder() {
            @Override
            public int get() {
                return proxy.getFilter().getFilterSettings().toNumber();
            }

            @Override
            public void set(int value) {
                filterSettings.fromNumber((byte) value);
            }
        });
    }

    @Override
    public boolean enchantItem(PlayerEntity playerIn, int id) {
        FilterSettings incomingFilterSettings = FilterSettings.createFromNumber((byte) id);

        if(!this.proxy.getFilter().getFilterSettings().equals(incomingFilterSettings)) {
            this.proxy.getFilter().getFilterSettings().copy(incomingFilterSettings);
            this.proxy.markDirty();
        }

        return true;
    }

    public FilterSettings getFilterSettings(){
        return this.filterSettings;
    }
    public FilteredCapabilityProxyTileEntity getTileEntity(){
        return this.proxy;
    }

    /**
     * Handle when the stack in slot {@code index} is shift-clicked. Normally this moves the stack between the player
     * inventory and the other inventory(s).
     */
    //FIXME: Fix this
    public ItemStack transferStackInSlot(PlayerEntity playerIn, int index) {
        ItemStack previous = null;
        Slot slot = this.inventorySlots.get(index);

        if (slot != null && slot.getHasStack()) {
            ItemStack current = slot.getStack();
            previous = current.copy();

            if (current.getCount() == previous.getCount())
                return ItemStack.EMPTY;
            slot.onTake(playerIn, current);
        }
        return previous;
    }

}
