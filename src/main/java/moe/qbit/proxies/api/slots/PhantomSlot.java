package moe.qbit.proxies.api.slots;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.ClickType;
import net.minecraft.inventory.container.Container;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

import javax.annotation.Nonnull;

public class PhantomSlot extends SlotItemHandler implements ICustomSlot {
    public PhantomSlot(IItemHandler itemHandler, int index, int xPosition, int yPosition) {
        super(itemHandler, index, xPosition, yPosition);
    }

    @Override
    public ItemStack click(Container container, int button, ClickType mode, PlayerEntity player) {
        PlayerInventory inventory = player.inventory;
        ItemStack held=inventory.getItemStack().copy();
        if(!held.isEmpty())
            held.setCount(1);

        switch (mode) {
            case THROW:
                this.putStack(ItemStack.EMPTY);
                break;
            case SWAP:
                break;
            case PICKUP:
            case PICKUP_ALL:
                if (button == 0 || button == 1) {
                    if (!held.isEmpty()) {
                        this.putStack(held);
                    } else {
                        this.putStack(ItemStack.EMPTY);
                    }
                } else {
                    this.putStack(ItemStack.EMPTY);
                }
                break;
            case QUICK_MOVE:
            case QUICK_CRAFT:
                this.putStack(held);
                break;
        }
        container.detectAndSendChanges();
        return ItemStack.EMPTY;
    }

    @Override
    public boolean canTakeStack(PlayerEntity playerIn) {
        return false;
    }

    @Override
    @Nonnull
    public ItemStack decrStackSize(int amount)
    {
        //not strictly necessary but just to be on the safe side
        return ItemStack.EMPTY;
    }
}
