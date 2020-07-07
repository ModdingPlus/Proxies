package dev.necro.proxies.data;

import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Pair;
import dev.necro.proxies.data.loot.ModBlockLootTables;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.LootTableProvider;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.LootParameterSet;
import net.minecraft.world.storage.loot.LootParameterSets;
import net.minecraft.world.storage.loot.LootTable;
import net.minecraft.world.storage.loot.ValidationTracker;

import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class ModLootTableProvider extends LootTableProvider {
   final List<Pair<Supplier<Consumer<BiConsumer<ResourceLocation, LootTable.Builder>>>, LootParameterSet>> magicList = ImmutableList.of(Pair.of(ModBlockLootTables::new, LootParameterSets.BLOCK));

   public ModLootTableProvider(DataGenerator dataGeneratorIn) {
      super(dataGeneratorIn);
   }


   @Override
   protected List<Pair<Supplier<Consumer<BiConsumer<ResourceLocation, LootTable.Builder>>>, LootParameterSet>> getTables() {
      return magicList;
   }

   /**
    * Gets a name for this provider, to use in logging.
    */
   @Override
   public String getName() {
      return "Proxies LootTables";
   }


   @Override
   protected void validate(Map<ResourceLocation, LootTable> map, ValidationTracker validationtracker) {}
}