package moe.qbit.proxies.common.items;

import moe.qbit.proxies.Proxies;
import net.minecraft.item.Item;
import net.minecraft.tags.ITag;
import net.minecraft.tags.ItemTags;

public class ModItemTags {
    public static final ITag.INamedTag<Item> ITEM_PROXIES = ItemTags.makeWrapperTag(Proxies.MODID+":item_proxies");
    public static final ITag.INamedTag<Item> FLUID_PROXIES = ItemTags.makeWrapperTag(Proxies.MODID+":fluid_proxies");
    public static final ITag.INamedTag<Item> ENERGY_PROXIES = ItemTags.makeWrapperTag(Proxies.MODID+":energy_proxies");

    public static final ITag.INamedTag<Item> PROXIES = ItemTags.makeWrapperTag(Proxies.MODID+":proxies");
    public static final ITag.INamedTag<Item> REGULAR_PROXIES = ItemTags.makeWrapperTag(Proxies.MODID+":regular_proxies");
    public static final ITag.INamedTag<Item> SIDED_PROXIES = ItemTags.makeWrapperTag(Proxies.MODID+":sided_proxies");
    public static final ITag.INamedTag<Item> NULLSIDED_PROXIES = ItemTags.makeWrapperTag(Proxies.MODID+":nullsided_proxies");
    public static final ITag.INamedTag<Item> JUNCTION_PROXIES = ItemTags.makeWrapperTag(Proxies.MODID+":junction_proxies");
    public static final ITag.INamedTag<Item> FILTERED_PROXIES = ItemTags.makeWrapperTag(Proxies.MODID+":filtered_proxies");
    public static final ITag.INamedTag<Item> MERGER_PROXIES = ItemTags.makeWrapperTag(Proxies.MODID+":merger_proxies");
}
