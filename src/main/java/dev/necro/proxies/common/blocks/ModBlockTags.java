package dev.necro.proxies.common.blocks;

import dev.necro.proxies.Proxies;
import net.minecraft.block.Block;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.Tag;
import net.minecraft.util.ResourceLocation;

public class ModBlockTags {
    public static final Tag<Block> PROXIES = new BlockTags.Wrapper(new ResourceLocation(Proxies.MODID,"proxies"));
    public static final Tag<Block> REGULAR_PROXIES = new BlockTags.Wrapper(new ResourceLocation(Proxies.MODID,"regular_proxies"));
    public static final Tag<Block> SIDED_PROXIES = new BlockTags.Wrapper(new ResourceLocation(Proxies.MODID,"sided_proxies"));
    public static final Tag<Block> NULLSIDED_PROXIES = new BlockTags.Wrapper(new ResourceLocation(Proxies.MODID,"nullsided_proxies"));
    public static final Tag<Block> ITEM_PROXIES = new BlockTags.Wrapper(new ResourceLocation(Proxies.MODID,"item_proxies"));
    public static final Tag<Block> FLUID_PROXIES = new BlockTags.Wrapper(new ResourceLocation(Proxies.MODID,"fluid_proxies"));
}
