package moe.qbit.proxies.data;

import moe.qbit.proxies.Proxies;
import moe.qbit.proxies.common.blocks.ModBlocks;
import moe.qbit.proxies.common.items.ModItems;
import net.minecraft.data.*;
import net.minecraft.item.Items;
import net.minecraft.tags.ItemTags;
import net.minecraftforge.common.Tags;

import java.util.function.Consumer;

@SuppressWarnings("ConstantConditions")
public class ModRecipeProvider extends RecipeProvider {

   public ModRecipeProvider(DataGenerator generatorIn) {
      super(generatorIn);
   }

   /**
    * Registers all recipes to the given consumer.
    */
   @Override
   protected void registerRecipes(Consumer<IFinishedRecipe> consumer) {
      //ITEM
      ShapedRecipeBuilder.shapedRecipe(ModItems.ITEM_PROXY.get(), 3).key('#', ItemTags.PLANKS).key('F', ItemTags.FENCES).key('H', Items.HOPPER).patternLine("#F#").patternLine("FHF").patternLine("#F#").setGroup(ModBlocks.ITEM_PROXY.get().getRegistryName().toString()).addCriterion("has_hopper", hasItem(Items.HOPPER)).build(consumer);
      ShapelessRecipeBuilder.shapelessRecipe(ModItems.SIDED_ITEM_PROXY.get()).addIngredient(ModItems.ITEM_PROXY.get()).setGroup(ModBlocks.SIDED_ITEM_PROXY.get().getRegistryName().toString()).addCriterion("has_proxy", hasItem(ModItems.ITEM_PROXY.get())).build(consumer);
      ShapelessRecipeBuilder.shapelessRecipe(ModItems.ITEM_PROXY.get()).addIngredient(ModItems.SIDED_ITEM_PROXY.get()).setGroup(ModBlocks.ITEM_PROXY.get().getRegistryName().toString()).addCriterion("has_proxy", hasItem(ModItems.ITEM_PROXY.get())).build(consumer, Proxies.MODID + ":sided_item_proxy_to_regular");

      ShapelessRecipeBuilder.shapelessRecipe(ModItems.NULLSIDED_ITEM_PROXY.get()).addIngredient(ModItems.SIDED_ITEM_PROXY.get()).addIngredient(Tags.Items.ENDER_PEARLS).setGroup(ModBlocks.NULLSIDED_ITEM_PROXY.get().getRegistryName().toString()).addCriterion("has_sided_proxy", hasItem(ModItems.SIDED_ITEM_PROXY.get())).build(consumer);

      ShapelessRecipeBuilder.shapelessRecipe(ModItems.FILTERED_ITEM_PROXY.get()).addIngredient(ModItems.ITEM_PROXY.get()).addIngredient(Items.COMPARATOR).setGroup(ModBlocks.FILTERED_ITEM_PROXY.get().getRegistryName().toString()).addCriterion("has_proxy", hasItem(ModItems.ITEM_PROXY.get())).build(consumer);

      ShapedRecipeBuilder.shapedRecipe(ModItems.MERGER_ITEM_PROXY.get(), 3).key('#', ItemTags.PLANKS).key('F', ItemTags.FENCES).key('H', Items.HOPPER).patternLine("#F#").patternLine("H H").patternLine("#F#").setGroup(ModBlocks.MERGER_ITEM_PROXY.get().getRegistryName().toString()).addCriterion("has_hopper", hasItem(Items.HOPPER)).build(consumer);

      //FLUID
      ShapedRecipeBuilder.shapedRecipe(ModItems.FLUID_PROXY.get(), 3).key('#', ItemTags.PLANKS).key('F', ItemTags.FENCES).key('B', Items.BUCKET).patternLine("#F#").patternLine("FBF").patternLine("#F#").setGroup(ModBlocks.FLUID_PROXY.get().getRegistryName().toString()).addCriterion("has_bucket", hasItem(Items.BUCKET)).build(consumer);
      ShapelessRecipeBuilder.shapelessRecipe(ModItems.SIDED_FLUID_PROXY.get()).addIngredient(ModItems.FLUID_PROXY.get()).setGroup(ModBlocks.SIDED_FLUID_PROXY.get().getRegistryName().toString()).addCriterion("has_proxy", hasItem(ModItems.FLUID_PROXY.get())).build(consumer);
      ShapelessRecipeBuilder.shapelessRecipe(ModItems.FLUID_PROXY.get()).addIngredient(ModItems.SIDED_FLUID_PROXY.get()).setGroup(ModBlocks.FLUID_PROXY.get().getRegistryName().toString()).addCriterion("has_proxy", hasItem(ModItems.FLUID_PROXY.get())).build(consumer, Proxies.MODID + ":sided_fluid_proxy_to_regular");

      ShapelessRecipeBuilder.shapelessRecipe(ModItems.NULLSIDED_FLUID_PROXY.get()).addIngredient(ModItems.SIDED_FLUID_PROXY.get()).addIngredient(Tags.Items.ENDER_PEARLS).setGroup(ModBlocks.NULLSIDED_FLUID_PROXY.get().getRegistryName().toString()).addCriterion("has_sided_proxy", hasItem(ModItems.SIDED_FLUID_PROXY.get())).build(consumer);

      ShapelessRecipeBuilder.shapelessRecipe(ModItems.FILTERED_FLUID_PROXY.get()).addIngredient(ModItems.FLUID_PROXY.get()).addIngredient(Items.COMPARATOR).setGroup(ModBlocks.FILTERED_FLUID_PROXY.get().getRegistryName().toString()).addCriterion("has_proxy", hasItem(ModItems.FLUID_PROXY.get())).build(consumer);

      ShapedRecipeBuilder.shapedRecipe(ModItems.MERGER_FLUID_PROXY.get(), 3).key('#', ItemTags.PLANKS).key('F', ItemTags.FENCES).key('B', Items.BUCKET).patternLine("#F#").patternLine("B B").patternLine("#F#").setGroup(ModBlocks.MERGER_FLUID_PROXY.get().getRegistryName().toString()).addCriterion("has_bucket", hasItem(Items.BUCKET)).build(consumer);

      //ENERGY
      ShapedRecipeBuilder.shapedRecipe(ModItems.ENERGY_PROXY.get(), 3).key('#', ItemTags.PLANKS).key('F', ItemTags.FENCES).key('G', Tags.Items.INGOTS_GOLD).patternLine("#F#").patternLine("FGF").patternLine("#F#").setGroup(ModBlocks.ENERGY_PROXY.get().getRegistryName().toString()).addCriterion("has_gold", hasItem(Tags.Items.INGOTS_GOLD)).build(consumer);
      
      ShapedRecipeBuilder.shapedRecipe(ModItems.MERGER_ENERGY_PROXY.get(), 3).key('#', ItemTags.PLANKS).key('F', ItemTags.FENCES).key('G', Tags.Items.INGOTS_GOLD).patternLine("#F#").patternLine("G G").patternLine("#F#").setGroup(ModBlocks.MERGER_ENERGY_PROXY.get().getRegistryName().toString()).addCriterion("has_gold", hasItem(Tags.Items.INGOTS_GOLD)).build(consumer);
   }

   @Override
   public String getName() {
      return "Proxies Recipes";
   }
}