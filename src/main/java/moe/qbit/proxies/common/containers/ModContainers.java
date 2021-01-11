package moe.qbit.proxies.common.containers;

import moe.qbit.proxies.Proxies;
import net.minecraft.inventory.container.ContainerType;
import net.minecraftforge.common.extensions.IForgeContainerType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModContainers {
    public static final DeferredRegister<ContainerType<?>> CONTAINERS = DeferredRegister.create(ForgeRegistries.CONTAINERS, Proxies.MODID);

    public static void init(){
        CONTAINERS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }

    public static final RegistryObject<ContainerType<FilteredCapabilityProxyContainer>> FILTERED_CAPABILITY_PROXY = CONTAINERS.register(
            "filtered_capability_proxy",
            () -> IForgeContainerType.create((windowId, inv, data) -> {
                return new FilteredCapabilityProxyContainer(windowId, inv.player.getEntityWorld(), data.readBlockPos(), inv, inv.player);
            })
    );
}
