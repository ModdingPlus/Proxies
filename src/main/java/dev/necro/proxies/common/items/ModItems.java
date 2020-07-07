package dev.necro.proxies.common.items;

import dev.necro.proxies.Proxies;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ObjectHolder;

@ObjectHolder(Proxies.MODID)
@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, modid = Proxies.MODID)
public class ModItems {
    public static final Item ITEM_PROXY = null;
    public static final Item SIDED_ITEM_PROXY = null;
    public static final Item FLUID_PROXY = null;
    public static final Item SIDED_FLUID_PROXY = null;
}
