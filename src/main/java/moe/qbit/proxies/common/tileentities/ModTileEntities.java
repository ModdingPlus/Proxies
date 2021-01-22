package moe.qbit.proxies.common.tileentities;

import moe.qbit.proxies.Proxies;
import moe.qbit.proxies.api.SideMapping;
import moe.qbit.proxies.common.blocks.ModBlocks;
import moe.qbit.proxies.common.tileentities.filters.FilteredFluidProxyTileEntity;
import moe.qbit.proxies.common.tileentities.filters.FilteredItemProxyTileEntity;
import moe.qbit.proxies.common.tileentities.mergers.MergerEnergyProxyTileEntity;
import moe.qbit.proxies.common.tileentities.mergers.MergerFluidProxyTileEntity;
import moe.qbit.proxies.common.tileentities.mergers.MergerItemProxyTileEntity;
import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.energy.IEnergyStorage;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.ObjectHolder;

import java.util.function.Supplier;

@ObjectHolder(Proxies.MODID)
@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, modid = Proxies.MODID)
public class ModTileEntities {
    @CapabilityInject(IItemHandler.class)
    static Capability<IItemHandler> ITEM_HANDLER_CAPABILITY = null;
    @CapabilityInject(IFluidHandler.class)
    static Capability<IItemHandler> FLUID_HANDLER_CAPABILITY = null;
    @CapabilityInject(IEnergyStorage.class)
    static Capability<IEnergyStorage> ENERGY_STORAGE_CAPABILITY = null;

    @ObjectHolder("item_proxy")
    public static final TileEntityType<?> ITEM_PROXY = null;
    @ObjectHolder("sided_item_proxy")
    public static final TileEntityType<?> SIDED_ITEM_PROXY = null;
    @ObjectHolder("nullsided_item_proxy")
    public static final TileEntityType<?> NULLSIDED_ITEM_PROXY = null;
    @ObjectHolder("junction_item_proxy")
    public static final TileEntityType<?> JUNCTION_ITEM_PROXY = null;
    @ObjectHolder("sided_junction_item_proxy")
    public static final TileEntityType<?> SIDED_JUNCTION_ITEM_PROXY = null;
    @ObjectHolder("filtered_item_proxy")
    public static final TileEntityType<?> FILTERED_ITEM_PROXY = null;
    @ObjectHolder("merger_item_proxy")
    public static final TileEntityType<?> MERGER_ITEM_PROXY = null;

    @ObjectHolder("fluid_proxy")
    public static final TileEntityType<?> FLUID_PROXY = null;
    @ObjectHolder("sided_fluid_proxy")
    public static final TileEntityType<?> SIDED_FLUID_PROXY = null;
    @ObjectHolder("nullsided_fluid_proxy")
    public static final TileEntityType<?> NULLSIDED_FLUID_PROXY = null;
    @ObjectHolder("junction_fluid_proxy")
    public static final TileEntityType<?> JUNCTION_FLUID_PROXY = null;
    @ObjectHolder("sided_junction_fluid_proxy")
    public static final TileEntityType<?> SIDED_JUNCTION_FLUID_PROXY = null;
    @ObjectHolder("filtered_fluid_proxy")
    public static final TileEntityType<?> FILTERED_FLUID_PROXY = null;
    @ObjectHolder("merger_fluid_proxy")
    public static final TileEntityType<?> MERGER_FLUID_PROXY = null;

    @ObjectHolder("energy_proxy")
    public static final TileEntityType<?> ENERGY_PROXY = null;
    @ObjectHolder("junction_energy_proxy")
    public static final TileEntityType<?> JUNCTION_ENERGY_PROXY = null;
    @ObjectHolder("merger_energy_proxy")
    public static final TileEntityType<?> MERGER_ENERGY_PROXY = null;

    @SubscribeEvent
    @SuppressWarnings("ConstantConditions")
    public static void registerTileEntityTypes(RegistryEvent.Register<TileEntityType<?>> event){
        IForgeRegistry<TileEntityType<?>> registry = event.getRegistry();
        ModTileEntities.registerProxy(registry, "item_proxy", ()->new GenericCapabilityProxyTileEntity(ITEM_PROXY, SideMapping.REGULAR, ITEM_HANDLER_CAPABILITY), ModBlocks.ITEM_PROXY.get());
        ModTileEntities.registerProxy(registry, "sided_item_proxy", ()->new GenericCapabilityProxyTileEntity(SIDED_ITEM_PROXY, SideMapping.SIDED, ITEM_HANDLER_CAPABILITY), ModBlocks.SIDED_ITEM_PROXY.get());
        ModTileEntities.registerProxy(registry, "nullsided_item_proxy", ()->new GenericCapabilityProxyTileEntity(NULLSIDED_ITEM_PROXY, SideMapping.NULLSIDED, ITEM_HANDLER_CAPABILITY), ModBlocks.NULLSIDED_ITEM_PROXY.get());
        ModTileEntities.registerProxy(registry, "junction_item_proxy", ()->new GenericCapabilityProxyTileEntity(JUNCTION_ITEM_PROXY, SideMapping.JUNCTION, ITEM_HANDLER_CAPABILITY), ModBlocks.JUNCTION_ITEM_PROXY.get());
        ModTileEntities.registerProxy(registry, "sided_junction_item_proxy", ()->new GenericCapabilityProxyTileEntity(SIDED_JUNCTION_ITEM_PROXY, SideMapping.SIDED_JUNCTION, ITEM_HANDLER_CAPABILITY), ModBlocks.SIDED_JUNCTION_ITEM_PROXY.get());
        ModTileEntities.registerProxy(registry, "filtered_item_proxy", ()->new FilteredItemProxyTileEntity(FILTERED_ITEM_PROXY), ModBlocks.FILTERED_ITEM_PROXY.get());
        ModTileEntities.registerProxy(registry, "merger_item_proxy", ()->new MergerItemProxyTileEntity(MERGER_ITEM_PROXY), ModBlocks.MERGER_ITEM_PROXY.get());

        ModTileEntities.registerProxy(registry, "fluid_proxy", ()->new GenericCapabilityProxyTileEntity(FLUID_PROXY, SideMapping.REGULAR, FLUID_HANDLER_CAPABILITY), ModBlocks.FLUID_PROXY.get());
        ModTileEntities.registerProxy(registry, "sided_fluid_proxy", ()->new GenericCapabilityProxyTileEntity(SIDED_FLUID_PROXY, SideMapping.SIDED, FLUID_HANDLER_CAPABILITY), ModBlocks.SIDED_FLUID_PROXY.get());
        ModTileEntities.registerProxy(registry, "nullsided_fluid_proxy", ()->new GenericCapabilityProxyTileEntity(NULLSIDED_FLUID_PROXY, SideMapping.NULLSIDED, FLUID_HANDLER_CAPABILITY), ModBlocks.NULLSIDED_FLUID_PROXY.get());
        ModTileEntities.registerProxy(registry, "junction_fluid_proxy", ()->new GenericCapabilityProxyTileEntity(JUNCTION_FLUID_PROXY, SideMapping.JUNCTION, FLUID_HANDLER_CAPABILITY), ModBlocks.JUNCTION_FLUID_PROXY.get());
        ModTileEntities.registerProxy(registry, "sided_junction_fluid_proxy", ()->new GenericCapabilityProxyTileEntity(SIDED_JUNCTION_FLUID_PROXY, SideMapping.SIDED_JUNCTION, FLUID_HANDLER_CAPABILITY), ModBlocks.SIDED_JUNCTION_FLUID_PROXY.get());
        ModTileEntities.registerProxy(registry, "filtered_fluid_proxy", ()->new FilteredFluidProxyTileEntity(FILTERED_FLUID_PROXY), ModBlocks.FILTERED_FLUID_PROXY.get());
        ModTileEntities.registerProxy(registry, "merger_fluid_proxy", ()->new MergerFluidProxyTileEntity(MERGER_FLUID_PROXY), ModBlocks.MERGER_FLUID_PROXY.get());

        ModTileEntities.registerProxy(registry, "energy_proxy", ()->new GenericCapabilityProxyTileEntity(ENERGY_PROXY, SideMapping.REGULAR, ENERGY_STORAGE_CAPABILITY), ModBlocks.ENERGY_PROXY.get());
        ModTileEntities.registerProxy(registry, "junction_energy_proxy", ()->new GenericCapabilityProxyTileEntity(JUNCTION_ENERGY_PROXY, SideMapping.JUNCTION, ENERGY_STORAGE_CAPABILITY), ModBlocks.JUNCTION_ENERGY_PROXY.get());
        ModTileEntities.registerProxy(registry, "merger_energy_proxy", ()->new MergerEnergyProxyTileEntity(MERGER_ENERGY_PROXY), ModBlocks.MERGER_ENERGY_PROXY.get());
    }

    public static void registerProxy(IForgeRegistry<TileEntityType<?>> registry, String id, Supplier<? extends TileEntity> factory, Block... blocks){
        //noinspection ConstantConditions
        registry.register(TileEntityType.Builder.create(factory, blocks).build(null).setRegistryName(id));
    }
}
