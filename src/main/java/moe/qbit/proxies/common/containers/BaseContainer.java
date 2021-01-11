package moe.qbit.proxies.common.containers;

import moe.qbit.proxies.api.slots.ICustomSlot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.ClickType;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IWorldPosCallable;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;
import net.minecraftforge.items.wrapper.InvWrapper;

import javax.annotation.Nullable;

public class BaseContainer extends Container {

    protected PlayerInventory playerInventory;
    protected InvWrapper playerInventoryHandler;
    protected TileEntity tileEntity;

    protected BaseContainer(@Nullable ContainerType<?> type, int id, PlayerInventory playerInventory, TileEntity tileEntity) {
        super(type, id);
        this.tileEntity = tileEntity;
        this.playerInventoryHandler = new InvWrapper(playerInventory);
        this.playerInventory = playerInventory;
    }

    @Override
    public ItemStack slotClick(int index, int button, ClickType mode, PlayerEntity player) {
        if(index>=0){
            Slot s=getSlot(index);
            if(s instanceof ICustomSlot){
                return ((ICustomSlot) s).click(this, button, mode, player);
            }
        }
        return super.slotClick(index, button, mode, player);
    }

    @Override
    public boolean canInteractWith(PlayerEntity playerIn) {
        //noinspection ConstantConditions
        return Container.isWithinUsableDistance(IWorldPosCallable.of(tileEntity.getWorld(), tileEntity.getPos()), playerIn, tileEntity.getWorld().getBlockState(tileEntity.getPos()).getBlock());
    }


    protected int addSlotRange(IItemHandler handler, int index, int x, int y, int amount, int dx) {
        return this.addSlotRange(handler, index, x, y, amount, dx, SlotItemHandler::new);
    }
    protected int addSlotRange(IItemHandler handler, int index, int x, int y, int amount, int dx, QuadFunction<IItemHandler, Integer, Integer, Integer, ? extends SlotItemHandler> factory) {
        for (int i = 0 ; i < amount ; i++) {
            addSlot(factory.apply(handler, index, x, y));
            x += dx;
            index++;
        }
        return index;
    }

    protected int addSlotBox(IItemHandler handler, int index, int x, int y, int horAmount, int dx, int verAmount, int dy) {
        for (int j = 0 ; j < verAmount ; j++) {
            index = addSlotRange(handler, index, x, y, horAmount, dx);
            y += dy;
        }
        return index;
    }

    protected void layoutPlayerInventorySlots(IItemHandler inventory, int leftCol, int topRow) {
        // Player inventory
        addSlotBox(inventory, 9, leftCol, topRow, 9, 18, 3, 18);

        // Hotbar
        topRow += 58;
        this.addSlotRange(inventory, 0, leftCol, topRow, 9, 18);
    }

    @FunctionalInterface
    public interface QuadFunction<T,U,V,W,RET>{
        public RET apply(T par1,U par2,V par3,W par4);
    }
}
