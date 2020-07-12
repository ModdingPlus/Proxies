package dev.necro.proxies.data;

import dev.necro.proxies.Proxies;
import dev.necro.proxies.common.blocks.ModBlocks;
import dev.necro.proxies.common.items.ModItems;
import net.minecraft.data.*;
import net.minecraft.item.Items;
import net.minecraft.tags.ItemTags;

import java.util.function.Consumer;

public class ModRecipeProvider extends RecipeProvider {

   public ModRecipeProvider(DataGenerator generatorIn) {
      super(generatorIn);
   }

   /**
    * Registers all recipes to the given consumer.
    */
   @Override
   @SuppressWarnings("ConstantConditions")
   protected void registerRecipes(Consumer<IFinishedRecipe> consumer) {
      //ShapedRecipeBuilder.shapedRecipe(Blocks.BOOKSHELF).key('#', ItemTags.PLANKS).key('X', Items.BOOK).patternLine("###").patternLine("XXX").patternLine("###").addCriterion("has_book", this.hasItem(Items.BOOK)).build(consumer);
      //ShapelessRecipeBuilder.shapelessRecipe(Items.COAL, 9).addIngredient(Blocks.COAL_BLOCK).addCriterion("has_coal_block", this.hasItem(Blocks.COAL_BLOCK)).build(consumer);
      ShapedRecipeBuilder.shapedRecipe(ModItems.ITEM_PROXY, 3).key('#', ItemTags.PLANKS).key('F', ItemTags.FENCES).key('H', Items.HOPPER).patternLine("#F#").patternLine("FHF").patternLine("#F#").setGroup(ModBlocks.ITEM_PROXY.getRegistryName().toString()).addCriterion("has_hopper", this.hasItem(Items.HOPPER)).build(consumer);
      ShapelessRecipeBuilder.shapelessRecipe(ModItems.SIDED_ITEM_PROXY).addIngredient(ModItems.ITEM_PROXY).setGroup(ModBlocks.SIDED_ITEM_PROXY.getRegistryName().toString()).addCriterion("has_proxy", this.hasItem(ModItems.ITEM_PROXY)).build(consumer);
      ShapelessRecipeBuilder.shapelessRecipe(ModItems.ITEM_PROXY).addIngredient(ModItems.SIDED_ITEM_PROXY).setGroup(ModBlocks.ITEM_PROXY.getRegistryName().toString()).addCriterion("has_proxy", this.hasItem(ModItems.ITEM_PROXY)).build(consumer, Proxies.MODID + ":sided_item_proxy_to_regular");
      ShapedRecipeBuilder.shapedRecipe(ModItems.FLUID_PROXY, 3).key('#', ItemTags.PLANKS).key('F', ItemTags.FENCES).key('B', Items.BUCKET).patternLine("#F#").patternLine("FBF").patternLine("#F#").setGroup(ModBlocks.FLUID_PROXY.getRegistryName().toString()).addCriterion("has_bucket", this.hasItem(Items.BUCKET)).build(consumer);
      ShapelessRecipeBuilder.shapelessRecipe(ModItems.SIDED_FLUID_PROXY).addIngredient(ModItems.FLUID_PROXY).setGroup(ModBlocks.SIDED_FLUID_PROXY.getRegistryName().toString()).addCriterion("has_proxy", this.hasItem(ModItems.FLUID_PROXY)).build(consumer);
      ShapelessRecipeBuilder.shapelessRecipe(ModItems.FLUID_PROXY).addIngredient(ModItems.SIDED_FLUID_PROXY).setGroup(ModBlocks.FLUID_PROXY.getRegistryName().toString()).addCriterion("has_proxy", this.hasItem(ModItems.FLUID_PROXY)).build(consumer, Proxies.MODID + ":sided_fluid_proxy_to_regular");
   }

   @Override
   public String getName() {
      return "Proxies Recipes";
   }
}