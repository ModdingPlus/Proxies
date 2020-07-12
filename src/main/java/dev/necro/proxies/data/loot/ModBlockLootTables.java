package dev.necro.proxies.data.loot;

import dev.necro.proxies.Proxies;
import dev.necro.proxies.common.blocks.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.data.loot.BlockLootTables;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.List;
import java.util.stream.Collectors;

public class ModBlockLootTables extends BlockLootTables {

   private List<Block> knownBlocks = ForgeRegistries.BLOCKS.getEntries().stream().filter(entry -> entry.getKey().getNamespace().equals(Proxies.MODID)).map(entry -> entry.getValue()).collect(Collectors.toList());

   @Override
   @SuppressWarnings("ConstantConditions")
   protected void addTables() {
      this.registerDropSelfLootTable(ModBlocks.ITEM_PROXY);
      this.registerDropSelfLootTable(ModBlocks.SIDED_ITEM_PROXY);
      this.registerDropSelfLootTable(ModBlocks.FLUID_PROXY);
      this.registerDropSelfLootTable(ModBlocks.SIDED_FLUID_PROXY);
   }

   @Override
   protected Iterable<Block> getKnownBlocks() {
      return this.knownBlocks;
   }
}