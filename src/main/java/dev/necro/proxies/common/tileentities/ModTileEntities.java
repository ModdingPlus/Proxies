package dev.necro.proxies.common.tileentities;

import dev.necro.proxies.Proxies;
import dev.necro.proxies.common.blocks.ModBlocks;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.ObjectHolder;

@ObjectHolder(Proxies.MODID)
@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, modid = Proxies.MODID)
public class ModTileEntities {
    @CapabilityInject(IItemHandler.class)
    static Capability<IItemHandler> ITEM_HANDLER_CAPABILITY = null;

    @ObjectHolder("item_proxy")
    public static final TileEntityType<?> ITEM_PROXY = null;

    @SubscribeEvent
    @SuppressWarnings("ConstantConditions")
    public static void registerTileEntityTypes(RegistryEvent.Register<TileEntityType<?>> event){
        IForgeRegistry<TileEntityType<?>> registry = event.getRegistry();
        registry.register(TileEntityType.Builder.create(()->new RegularCapabilityProxyTileEntity(ITEM_PROXY,ITEM_HANDLER_CAPABILITY), ModBlocks.ITEM_PROXY).build(null).setRegistryName("item_proxy"));
    }
}
