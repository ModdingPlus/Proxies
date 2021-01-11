package moe.qbit.proxies;

import moe.qbit.proxies.client.guis.FilteredItemProxyScreen;
import moe.qbit.proxies.common.blocks.ModBlocks;
import moe.qbit.proxies.common.containers.ModContainers;
import moe.qbit.proxies.common.items.ModItems;
import moe.qbit.proxies.configuration.ServerConfiguration;
import moe.qbit.proxies.data.*;
import moe.qbit.proxies.data.loot.ModBlockLootTables;
import net.minecraft.client.gui.ScreenManager;
import net.minecraft.data.BlockTagsProvider;
import net.minecraft.data.DataGenerator;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.GatherDataEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(Proxies.MODID)
@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, modid = Proxies.MODID)
public class Proxies
{
    public static final String MODID = "proxies";

    public static final ItemGroup ITEM_GROUP = (new ItemGroup("proxies") {
        public ItemStack createIcon() {
            return new ItemStack(ModBlocks.ITEM_PROXY.get());
        }
    }).setTabPath("proxies");

    public Proxies(){
        ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, ServerConfiguration.CONFIGURATION_SPEC);

        ModItems.init();
        ModBlocks.init();
        ModContainers.init();
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::clientSetup);
    }

    public void clientSetup(final FMLClientSetupEvent event){
        ScreenManager.registerFactory(ModContainers.FILTERED_CAPABILITY_PROXY.get(), FilteredItemProxyScreen::new);
    }

    @SubscribeEvent
    public static void dataGen(final GatherDataEvent event){
        DataGenerator generator = event.getGenerator();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();

        if (event.includeServer()) {
            generator.addProvider(new ModRecipeProvider(generator));
            BlockTagsProvider blockTagsProvider = new ModBlockTagsProvider(generator, existingFileHelper);
            generator.addProvider(blockTagsProvider);
            generator.addProvider(new ModItemTagsProvider(generator, blockTagsProvider, existingFileHelper));
            generator.addProvider(new ModBlockLootTables(generator));
        }
        if (event.includeClient()) {
            generator.addProvider(new ModBlockStatesProvider(generator, existingFileHelper));
            generator.addProvider(new ModItemModelProvider(generator, existingFileHelper));
        }
    }
}
