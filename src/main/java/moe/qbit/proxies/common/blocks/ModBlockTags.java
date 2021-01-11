package moe.qbit.proxies.common.blocks;

import moe.qbit.proxies.Proxies;
import net.minecraft.block.Block;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ITag;

public class ModBlockTags {
    public static final ITag.INamedTag<Block> ITEM_PROXIES = BlockTags.makeWrapperTag(Proxies.MODID+":item_proxies");
    public static final ITag.INamedTag<Block> FLUID_PROXIES = BlockTags.makeWrapperTag(Proxies.MODID+":fluid_proxies");
    public static final ITag.INamedTag<Block> ENERGY_PROXIES = BlockTags.makeWrapperTag(Proxies.MODID+":energy_proxies");

    public static final ITag.INamedTag<Block> PROXIES = BlockTags.makeWrapperTag(Proxies.MODID+":proxies");
    public static final ITag.INamedTag<Block> REGULAR_PROXIES = BlockTags.makeWrapperTag(Proxies.MODID+":regular_proxies");
    public static final ITag.INamedTag<Block> SIDED_PROXIES = BlockTags.makeWrapperTag(Proxies.MODID+":sided_proxies");
    public static final ITag.INamedTag<Block> NULLSIDED_PROXIES = BlockTags.makeWrapperTag(Proxies.MODID+":nullsided_proxies");
    public static final ITag.INamedTag<Block> FILTERED_PROXIES = BlockTags.makeWrapperTag(Proxies.MODID+":filtered_proxies");
    public static final ITag.INamedTag<Block> MERGER_PROXIES = BlockTags.makeWrapperTag(Proxies.MODID+":merger_proxies");
}
