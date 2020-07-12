package dev.necro.proxies.data;

import dev.necro.proxies.common.blocks.ModBlockTags;
import dev.necro.proxies.common.items.ModItemTags;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.ItemTagsProvider;

public class ModItemTagsProvider extends ItemTagsProvider {

   public ModItemTagsProvider(DataGenerator generatorIn) {
      super(generatorIn);
   }

   protected void registerTags() {
      this.copy(ModBlockTags.PROXIES, ModItemTags.PROXIES);
      this.copy(ModBlockTags.ITEM_PROXIES, ModItemTags.ITEM_PROXIES);
      this.copy(ModBlockTags.FLUID_PROXIES, ModItemTags.FLUID_PROXIES);
      this.copy(ModBlockTags.REGULAR_PROXIES, ModItemTags.REGULAR_PROXIES);
      this.copy(ModBlockTags.SIDED_PROXIES, ModItemTags.SIDED_PROXIES);
   }

   @Override
   public String getName() {
      return "Proxies Item Tags";
   }
}