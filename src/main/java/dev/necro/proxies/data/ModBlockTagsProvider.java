package dev.necro.proxies.data;

import dev.necro.proxies.common.blocks.ModBlockTags;
import dev.necro.proxies.common.blocks.ModBlocks;
import net.minecraft.data.BlockTagsProvider;
import net.minecraft.data.DataGenerator;

public class ModBlockTagsProvider extends BlockTagsProvider {
   public ModBlockTagsProvider(DataGenerator generatorIn) {
      super(generatorIn);
   }

   @Override
   @SuppressWarnings("ConstantConditions")
   protected void registerTags() {
      this.getBuilder(ModBlockTags.PROXIES).add(ModBlocks.ITEM_PROXY, ModBlocks.SIDED_ITEM_PROXY, ModBlocks.FLUID_PROXY, ModBlocks.SIDED_FLUID_PROXY);
      this.getBuilder(ModBlockTags.ITEM_PROXIES).add(ModBlocks.ITEM_PROXY, ModBlocks.SIDED_ITEM_PROXY);
      this.getBuilder(ModBlockTags.FLUID_PROXIES).add(ModBlocks.FLUID_PROXY, ModBlocks.SIDED_FLUID_PROXY);
      this.getBuilder(ModBlockTags.REGULAR_PROXIES).add(ModBlocks.ITEM_PROXY, ModBlocks.FLUID_PROXY);
      this.getBuilder(ModBlockTags.SIDED_PROXIES).add(ModBlocks.SIDED_ITEM_PROXY, ModBlocks.FLUID_PROXY);
   }

   @Override
   public String getName()
   {
      return "Proxies Block Tags";
   }
}