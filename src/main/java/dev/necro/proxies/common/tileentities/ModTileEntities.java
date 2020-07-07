package dev.necro.proxies.common.tileentities;

import dev.necro.proxies.Proxies;
import dev.necro.proxies.common.blocks.ModBlocks;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.ObjectHolder;

@ObjectHolder(Proxies.MODID)
@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, modid = Proxies.MODID)
public class ModTileEntities {
    @CapabilityInject(IItemHandler.class)
    static Capability<IItemHandler> ITEM_HANDLER_CAPABILITY = null;
    @CapabilityInject(IFluidHandler.class)
    static Capability<IItemHandler> FLUID_HANDLER_CAPABILITY = null;

    @ObjectHolder("item_proxy")
    public static final TileEntityType<?> ITEM_PROXY = null;
    @ObjectHolder("sided_item_proxy")
    public static final TileEntityType<?> SIDED_ITEM_PROXY = null;
    @ObjectHolder("fluid_proxy")
    public static final TileEntityType<?> FLUID_PROXY = null;
    @ObjectHolder("sided_fluid_proxy")
    public static final TileEntityType<?> SIDED_FLUID_PROXY = null;

    @SubscribeEvent
    @SuppressWarnings("ConstantConditions")
    public static void registerTileEntityTypes(RegistryEvent.Register<TileEntityType<?>> event){
        IForgeRegistry<TileEntityType<?>> registry = event.getRegistry();
        registry.register(TileEntityType.Builder.create(()->new RegularCapabilityProxyTileEntity(ITEM_PROXY, ITEM_HANDLER_CAPABILITY), ModBlocks.ITEM_PROXY).build(null).setRegistryName("item_proxy"));
        registry.register(TileEntityType.Builder.create(()->new SidedCapabilityProxyTileEntity(SIDED_ITEM_PROXY, ITEM_HANDLER_CAPABILITY), ModBlocks.SIDED_ITEM_PROXY).build(null).setRegistryName("sided_item_proxy"));
        registry.register(TileEntityType.Builder.create(()->new RegularCapabilityProxyTileEntity(ITEM_PROXY, FLUID_HANDLER_CAPABILITY), ModBlocks.FLUID_PROXY).build(null).setRegistryName("fluid_proxy"));
        registry.register(TileEntityType.Builder.create(()->new SidedCapabilityProxyTileEntity(SIDED_ITEM_PROXY, FLUID_HANDLER_CAPABILITY), ModBlocks.SIDED_FLUID_PROXY).build(null).setRegistryName("sided_fluid_proxy"));
    }
}
