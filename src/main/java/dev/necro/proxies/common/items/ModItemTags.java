package dev.necro.proxies.common.items;

import dev.necro.proxies.Proxies;
import net.minecraft.item.Item;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.Tag;
import net.minecraft.util.ResourceLocation;

public class ModItemTags {
    public static final Tag<Item> PROXIES = new ItemTags.Wrapper(new ResourceLocation(Proxies.MODID,"proxies"));
    public static final Tag<Item> REGULAR_PROXIES = new ItemTags.Wrapper(new ResourceLocation(Proxies.MODID,"regular_proxies"));
    public static final Tag<Item> SIDED_PROXIES = new ItemTags.Wrapper(new ResourceLocation(Proxies.MODID,"sided_proxies"));
    public static final Tag<Item> NULLSIDED_PROXIES = new ItemTags.Wrapper(new ResourceLocation(Proxies.MODID,"nullsided_proxies"));
    public static final Tag<Item> ITEM_PROXIES = new ItemTags.Wrapper(new ResourceLocation(Proxies.MODID,"item_proxies"));
    public static final Tag<Item> FLUID_PROXIES = new ItemTags.Wrapper(new ResourceLocation(Proxies.MODID,"fluid_proxies"));
}
