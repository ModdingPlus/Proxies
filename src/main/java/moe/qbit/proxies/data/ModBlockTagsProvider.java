package moe.qbit.proxies.data;

import moe.qbit.proxies.Proxies;
import moe.qbit.proxies.common.blocks.ModBlockTags;
import moe.qbit.proxies.common.blocks.ModBlocks;
import net.minecraft.data.BlockTagsProvider;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ModBlockTagsProvider extends BlockTagsProvider {
   public ModBlockTagsProvider(DataGenerator generatorIn, ExistingFileHelper existingFileHelper) {
      super(generatorIn, Proxies.MODID, existingFileHelper);
   }

   @Override
   protected void registerTags() {
      this.getOrCreateBuilder(ModBlockTags.PROXIES).add(
              ModBlocks.ITEM_PROXY.get(), ModBlocks.SIDED_ITEM_PROXY.get(), ModBlocks.NULLSIDED_ITEM_PROXY.get(), ModBlocks.FILTERED_ITEM_PROXY.get(), ModBlocks.MERGER_ITEM_PROXY.get(),
              ModBlocks.FLUID_PROXY.get(), ModBlocks.SIDED_FLUID_PROXY.get(), ModBlocks.NULLSIDED_FLUID_PROXY.get(), ModBlocks.FILTERED_FLUID_PROXY.get(), ModBlocks.MERGER_FLUID_PROXY.get(),
              ModBlocks.ENERGY_PROXY.get(), ModBlocks.MERGER_ENERGY_PROXY.get());

      this.getOrCreateBuilder(ModBlockTags.ITEM_PROXIES).add(ModBlocks.ITEM_PROXY.get(), ModBlocks.SIDED_ITEM_PROXY.get(), ModBlocks.NULLSIDED_ITEM_PROXY.get(), ModBlocks.FILTERED_ITEM_PROXY.get(), ModBlocks.MERGER_ITEM_PROXY.get());
      this.getOrCreateBuilder(ModBlockTags.FLUID_PROXIES).add(ModBlocks.FLUID_PROXY.get(), ModBlocks.SIDED_FLUID_PROXY.get(), ModBlocks.NULLSIDED_FLUID_PROXY.get(), ModBlocks.FILTERED_FLUID_PROXY.get(), ModBlocks.MERGER_FLUID_PROXY.get());
      this.getOrCreateBuilder(ModBlockTags.ENERGY_PROXIES).add(ModBlocks.ENERGY_PROXY.get(), ModBlocks.MERGER_ENERGY_PROXY.get());

      this.getOrCreateBuilder(ModBlockTags.REGULAR_PROXIES).add(ModBlocks.ITEM_PROXY.get(), ModBlocks.FLUID_PROXY.get(), ModBlocks.ENERGY_PROXY.get());
      this.getOrCreateBuilder(ModBlockTags.SIDED_PROXIES).add(ModBlocks.SIDED_ITEM_PROXY.get(), ModBlocks.FLUID_PROXY.get());
      this.getOrCreateBuilder(ModBlockTags.NULLSIDED_PROXIES).add(ModBlocks.NULLSIDED_ITEM_PROXY.get(), ModBlocks.NULLSIDED_FLUID_PROXY.get());
      this.getOrCreateBuilder(ModBlockTags.FILTERED_PROXIES).add(ModBlocks.FILTERED_ITEM_PROXY.get(), ModBlocks.FILTERED_FLUID_PROXY.get());
      this.getOrCreateBuilder(ModBlockTags.MERGER_PROXIES).add(ModBlocks.MERGER_ITEM_PROXY.get(), ModBlocks.MERGER_FLUID_PROXY.get(), ModBlocks.MERGER_ENERGY_PROXY.get());
   }

   @Override
   public String getName()
   {
      return "Proxies Block Tags";
   }
}