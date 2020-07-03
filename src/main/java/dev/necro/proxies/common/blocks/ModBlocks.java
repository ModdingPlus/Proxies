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

    @SubscribeEvent
    @SuppressWarnings({"ConstantConditions", "Convert2MethodRef"})
    public static void registerBlocks(RegistryEvent.Register<Block> event){
        IForgeRegistry<Block> registry = event.getRegistry();
        registry.register(new CapabilityProxyBlock(Block.Properties.from(Blocks.CRAFTING_TABLE), ()->ModTileEntities.ITEM_PROXY.create()).setRegistryName("item_proxy"));
    }

    @SubscribeEvent
    @SuppressWarnings("ConstantConditions")
    public static void registerBlockItems(RegistryEvent.Register<Item> event){
        IForgeRegistry<Item> registry = event.getRegistry();
        registry.register(new BlockItem(ITEM_PROXY, new Item.Properties().group(ItemGroup.INVENTORY)).setRegistryName("item_proxy"));
    }
}
