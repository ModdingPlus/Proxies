package moe.qbit.proxies.common.network;

import moe.qbit.proxies.Proxies;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.simple.SimpleChannel;

@Mod.EventBusSubscriber(modid = Proxies.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class PacketHandler {
    private static final String PROTOCOL_VERSION = "1";
    public static final SimpleChannel INSTANCE = NetworkRegistry.newSimpleChannel(
            new ResourceLocation(Proxies.MODID, "main"),
            () -> PROTOCOL_VERSION,
            PROTOCOL_VERSION::equals,
            PROTOCOL_VERSION::equals
    );

    @SubscribeEvent
    @SuppressWarnings({"UnusedAssignment", "CommentedOutCode"})
    public static void setup(final FMLCommonSetupEvent event) {
        int id=0;
//        INSTANCE.registerMessage(id++,
//                FilteredProxySettingsPacket.class,
//                FilteredProxySettingsPacket::writePacketData,
//                FilteredProxySettingsPacket::new,
//                FilteredProxySettingsPacket::processPacket,
//                Optional.of(NetworkDirection.PLAY_TO_SERVER));
    }

}
