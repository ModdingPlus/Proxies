package moe.qbit.proxies.data;

import moe.qbit.proxies.Proxies;
import moe.qbit.proxies.common.blocks.ModBlockTags;
import moe.qbit.proxies.common.items.ModItemTags;
import net.minecraft.data.BlockTagsProvider;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.ItemTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ModItemTagsProvider extends ItemTagsProvider {

   public ModItemTagsProvider(DataGenerator dataGenerator, BlockTagsProvider blockTagProvider, ExistingFileHelper existingFileHelper) {
      super(dataGenerator, blockTagProvider, Proxies.MODID, existingFileHelper);
   }

   protected void registerTags() {
      this.copy(ModBlockTags.PROXIES, ModItemTags.PROXIES);

      this.copy(ModBlockTags.ITEM_PROXIES, ModItemTags.ITEM_PROXIES);
      this.copy(ModBlockTags.FLUID_PROXIES, ModItemTags.FLUID_PROXIES);
      this.copy(ModBlockTags.ENERGY_PROXIES, ModItemTags.ENERGY_PROXIES);

      this.copy(ModBlockTags.REGULAR_PROXIES, ModItemTags.REGULAR_PROXIES);
      this.copy(ModBlockTags.SIDED_PROXIES, ModItemTags.SIDED_PROXIES);
      this.copy(ModBlockTags.NULLSIDED_PROXIES, ModItemTags.NULLSIDED_PROXIES);
      this.copy(ModBlockTags.FILTERED_PROXIES, ModItemTags.FILTERED_PROXIES);
      this.copy(ModBlockTags.MERGER_PROXIES, ModItemTags.MERGER_PROXIES);
   }

   @Override
   public String getName() {
      return "Proxies Item Tags";
   }
}