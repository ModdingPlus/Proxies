package dev.necro.proxies;

import dev.necro.proxies.common.blocks.ModBlocks;
import dev.necro.proxies.data.*;
import net.minecraft.data.DataGenerator;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.model.generators.ExistingFileHelper;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.GatherDataEvent;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(Proxies.MODID)
@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, modid = Proxies.MODID)
public class Proxies
{
    public static final String MODID = "proxies";

    public static final ItemGroup ITEM_GROUP = (new ItemGroup("proxies") {
        public ItemStack createIcon() {
            return new ItemStack(ModBlocks.ITEM_PROXY);
        }
    }).setTabPath("proxies");

    public Proxies() {}

    @SubscribeEvent
    public static void dataGen(final GatherDataEvent event){
        DataGenerator generator = event.getGenerator();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();

        if (event.includeServer()) {
            generator.addProvider(new ModRecipeProvider(generator));
            generator.addProvider(new ModBlockTagsProvider(generator));
            generator.addProvider(new ModItemTagsProvider(generator));
            generator.addProvider(new ModLootTableProvider(generator));
        }
        if (event.includeClient()) {
            generator.addProvider(new ModBlockStatesProvider(generator, existingFileHelper));
            generator.addProvider(new ModItemModelProvider(generator, existingFileHelper));
        }
    }
}
