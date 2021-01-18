package moe.qbit.proxies.common.items;

import moe.qbit.proxies.Proxies;
import moe.qbit.proxies.common.blocks.ModBlocks;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Proxies.MODID);

    public static void init(){
        ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }

    public static final RegistryObject<Item> ITEM_PROXY = ITEMS.register("item_proxy", () -> new BlockItem(ModBlocks.ITEM_PROXY.get(), new Item.Properties().group(Proxies.ITEM_GROUP)));
    public static final RegistryObject<Item> SIDED_ITEM_PROXY = ITEMS.register("sided_item_proxy", () -> new BlockItem(ModBlocks.SIDED_ITEM_PROXY.get(), new Item.Properties().group(Proxies.ITEM_GROUP)));
    public static final RegistryObject<Item> NULLSIDED_ITEM_PROXY = ITEMS.register("nullsided_item_proxy", () -> new BlockItem(ModBlocks.NULLSIDED_ITEM_PROXY.get(), new Item.Properties().group(Proxies.ITEM_GROUP)));
    public static final RegistryObject<Item> JUNCTION_ITEM_PROXY = ITEMS.register("junction_item_proxy", () -> new BlockItem(ModBlocks.JUNCTION_ITEM_PROXY.get(), new Item.Properties().group(Proxies.ITEM_GROUP)));
    public static final RegistryObject<Item> FILTERED_ITEM_PROXY = ITEMS.register("filtered_item_proxy", () -> new BlockItem(ModBlocks.FILTERED_ITEM_PROXY.get(), new Item.Properties().group(Proxies.ITEM_GROUP)));
    public static final RegistryObject<Item> MERGER_ITEM_PROXY = ITEMS.register("merger_item_proxy", () -> new BlockItem(ModBlocks.MERGER_ITEM_PROXY.get(), new Item.Properties().group(Proxies.ITEM_GROUP)));

    public static final RegistryObject<Item> FLUID_PROXY = ITEMS.register("fluid_proxy", () -> new BlockItem(ModBlocks.FLUID_PROXY.get(), new Item.Properties().group(Proxies.ITEM_GROUP)));
    public static final RegistryObject<Item> SIDED_FLUID_PROXY = ITEMS.register("sided_fluid_proxy", () -> new BlockItem(ModBlocks.SIDED_FLUID_PROXY.get(), new Item.Properties().group(Proxies.ITEM_GROUP)));
    public static final RegistryObject<Item> NULLSIDED_FLUID_PROXY = ITEMS.register("nullsided_fluid_proxy", () -> new BlockItem(ModBlocks.NULLSIDED_FLUID_PROXY.get(), new Item.Properties().group(Proxies.ITEM_GROUP)));
    public static final RegistryObject<Item> JUNCTION_FLUID_PROXY = ITEMS.register("junction_fluid_proxy", () -> new BlockItem(ModBlocks.JUNCTION_FLUID_PROXY.get(), new Item.Properties().group(Proxies.ITEM_GROUP)));
    public static final RegistryObject<Item> FILTERED_FLUID_PROXY = ITEMS.register("filtered_fluid_proxy", () -> new BlockItem(ModBlocks.FILTERED_FLUID_PROXY.get(), new Item.Properties().group(Proxies.ITEM_GROUP)));
    public static final RegistryObject<Item> MERGER_FLUID_PROXY = ITEMS.register("merger_fluid_proxy", () -> new BlockItem(ModBlocks.MERGER_FLUID_PROXY.get(), new Item.Properties().group(Proxies.ITEM_GROUP)));

    public static final RegistryObject<Item> ENERGY_PROXY = ITEMS.register("energy_proxy", () -> new BlockItem(ModBlocks.ENERGY_PROXY.get(), new Item.Properties().group(Proxies.ITEM_GROUP)));
    public static final RegistryObject<Item> JUNCTION_ENERGY_PROXY = ITEMS.register("junction_energy_proxy", () -> new BlockItem(ModBlocks.JUNCTION_ENERGY_PROXY.get(), new Item.Properties().group(Proxies.ITEM_GROUP)));
    public static final RegistryObject<Item> MERGER_ENERGY_PROXY = ITEMS.register("merger_energy_proxy", () -> new BlockItem(ModBlocks.MERGER_ENERGY_PROXY.get(), new Item.Properties().group(Proxies.ITEM_GROUP)));
}
