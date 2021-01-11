package moe.qbit.proxies.api.slots;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.container.ClickType;
import net.minecraft.inventory.container.Container;
import net.minecraft.item.ItemStack;

public interface ICustomSlot {
    ItemStack click(Container container, int button, ClickType mode, PlayerEntity player);
}
