package dev.necro.proxies.data.loot;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import dev.necro.proxies.Proxies;
import dev.necro.proxies.common.blocks.ModBlocks;
import net.minecraft.advancements.criterion.EnchantmentPredicate;
import net.minecraft.advancements.criterion.ItemPredicate;
import net.minecraft.advancements.criterion.MinMaxBounds;
import net.minecraft.advancements.criterion.StatePropertiesPredicate;
import net.minecraft.block.*;
import net.minecraft.data.loot.BlockLootTables;
import net.minecraft.data.loot.EntityLootTables;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.state.IProperty;
import net.minecraft.state.properties.BedPart;
import net.minecraft.state.properties.DoubleBlockHalf;
import net.minecraft.state.properties.SlabType;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.storage.loot.*;
import net.minecraft.world.storage.loot.conditions.*;
import net.minecraft.world.storage.loot.functions.*;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.ObjectHolder;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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