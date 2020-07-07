package dev.necro.proxies.common.blocks;

import dev.necro.proxies.Proxies;
import dev.necro.proxies.common.tileentities.ModTileEntities;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.ObjectHolder;

@ObjectHolder(Proxies.MODID)
@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, modid = Proxies.MODID)
public class ModBlocks {

    public static final Block ITEM_PROXY = null;
    public static final Block SIDED_ITEM_PROXY = null;
    public static final Block FLUID_PROXY = null;
    public static final Block SIDED_FLUID_PROXY = null;

    @SubscribeEvent
    @SuppressWarnings({"ConstantConditions", "Convert2MethodRef"})
    public static void registerBlocks(RegistryEvent.Register<Block> event){
        IForgeRegistry<Block> registry = event.getRegistry();
        registry.register(new CapabilityProxyBlock(Block.Properties.from(Blocks.CRAFTING_TABLE), ()->ModTileEntities.ITEM_PROXY.create()).setRegistryName("item_proxy"));
        registry.register(new CapabilityProxyBlock(Block.Properties.from(Blocks.CRAFTING_TABLE), ()->ModTileEntities.SIDED_ITEM_PROXY.create()).setRegistryName("sided_item_proxy"));
        registry.register(new CapabilityProxyBlock(Block.Properties.from(Blocks.CRAFTING_TABLE), ()->ModTileEntities.ITEM_PROXY.create()).setRegistryName("fluid_proxy"));
        registry.register(new CapabilityProxyBlock(Block.Properties.from(Blocks.CRAFTING_TABLE), ()->ModTileEntities.SIDED_ITEM_PROXY.create()).setRegistryName("sided_fluid_proxy"));
    }

    @SubscribeEvent
    @SuppressWarnings("ConstantConditions")
    public static void registerBlockItems(RegistryEvent.Register<Item> event){
        IForgeRegistry<Item> registry = event.getRegistry();
        registry.register(new BlockItem(ITEM_PROXY, new Item.Properties().group(Proxies.ITEM_GROUP)).setRegistryName("item_proxy"));
        registry.register(new BlockItem(SIDED_ITEM_PROXY, new Item.Properties().group(Proxies.ITEM_GROUP)).setRegistryName("sided_item_proxy"));
        registry.register(new BlockItem(FLUID_PROXY, new Item.Properties().group(Proxies.ITEM_GROUP)).setRegistryName("fluid_proxy"));
        registry.register(new BlockItem(SIDED_FLUID_PROXY, new Item.Properties().group(Proxies.ITEM_GROUP)).setRegistryName("sided_fluid_proxy"));
    }
}
