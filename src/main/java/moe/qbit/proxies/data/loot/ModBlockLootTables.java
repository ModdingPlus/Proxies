package moe.qbit.proxies.data.loot;

import moe.qbit.proxies.common.blocks.ModBlocks;
import moe.qbit.proxies.data.BaseLootTableProvider;
import net.minecraft.data.DataGenerator;

public class ModBlockLootTables extends BaseLootTableProvider {

   public ModBlockLootTables(DataGenerator dataGeneratorIn) {
      super(dataGeneratorIn);
   }

   @Override
   protected void addTables() {
      lootTables.put(ModBlocks.ITEM_PROXY.get(), createStandardTable(ModBlocks.ITEM_PROXY.get()));
      lootTables.put(ModBlocks.SIDED_ITEM_PROXY.get(), createStandardTable(ModBlocks.SIDED_ITEM_PROXY.get()));
      lootTables.put(ModBlocks.NULLSIDED_ITEM_PROXY.get(), createStandardTable(ModBlocks.NULLSIDED_ITEM_PROXY.get()));
      lootTables.put(ModBlocks.JUNCTION_ITEM_PROXY.get(), createStandardTable(ModBlocks.JUNCTION_ITEM_PROXY.get()));
      lootTables.put(ModBlocks.SIDED_JUNCTION_ITEM_PROXY.get(), createStandardTable(ModBlocks.SIDED_JUNCTION_ITEM_PROXY.get()));
      lootTables.put(ModBlocks.FILTERED_ITEM_PROXY.get(), createStandardTable(ModBlocks.FILTERED_ITEM_PROXY.get()));
      lootTables.put(ModBlocks.MERGER_ITEM_PROXY.get(), createStandardTable(ModBlocks.MERGER_ITEM_PROXY.get()));

      lootTables.put(ModBlocks.FLUID_PROXY.get(), createStandardTable(ModBlocks.FLUID_PROXY.get()));
      lootTables.put(ModBlocks.SIDED_FLUID_PROXY.get(), createStandardTable(ModBlocks.SIDED_FLUID_PROXY.get()));
      lootTables.put(ModBlocks.NULLSIDED_FLUID_PROXY.get(), createStandardTable(ModBlocks.NULLSIDED_FLUID_PROXY.get()));
      lootTables.put(ModBlocks.JUNCTION_FLUID_PROXY.get(), createStandardTable(ModBlocks.JUNCTION_FLUID_PROXY.get()));
      lootTables.put(ModBlocks.SIDED_JUNCTION_FLUID_PROXY.get(), createStandardTable(ModBlocks.SIDED_JUNCTION_FLUID_PROXY.get()));
      lootTables.put(ModBlocks.FILTERED_FLUID_PROXY.get(), createStandardTable(ModBlocks.FILTERED_FLUID_PROXY.get()));
      lootTables.put(ModBlocks.MERGER_FLUID_PROXY.get(), createStandardTable(ModBlocks.MERGER_FLUID_PROXY.get()));

      lootTables.put(ModBlocks.ITEM_PROXY.get(), createStandardTable(ModBlocks.ENERGY_PROXY.get()));
      lootTables.put(ModBlocks.JUNCTION_ENERGY_PROXY.get(), createStandardTable(ModBlocks.JUNCTION_ENERGY_PROXY.get()));
      lootTables.put(ModBlocks.MERGER_ENERGY_PROXY.get(), createStandardTable(ModBlocks.MERGER_ENERGY_PROXY.get()));
   }
}