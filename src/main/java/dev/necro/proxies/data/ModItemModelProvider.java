package dev.necro.proxies.data;

import dev.necro.proxies.Proxies;
import dev.necro.proxies.common.blocks.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.data.DataGenerator;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.generators.BlockModelBuilder;
import net.minecraftforge.client.model.generators.ExistingFileHelper;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;

public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(DataGenerator generator, ExistingFileHelper existingFileHelper) {
        super(generator, Proxies.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        this.withExistingParentBlock(ModBlocks.ITEM_PROXY);
        this.withExistingParentBlock(ModBlocks.SIDED_ITEM_PROXY);
        this.withExistingParentBlock(ModBlocks.FLUID_PROXY);
        this.withExistingParentBlock(ModBlocks.SIDED_FLUID_PROXY);
    }

    public void withExistingParentBlock(Block block){
        ResourceLocation registryName = block.getRegistryName();
        this.withExistingParent(registryName.getPath(),
                new ResourceLocation(registryName.getNamespace(), "block/" + registryName.getPath()));
    }

    public ItemModelBuilder simpleCubeBottomTop(Block block){
        ResourceLocation registryName = block.getRegistryName();
        return this.cubeBottomTop(registryName.getPath(),
                new ResourceLocation(registryName.getNamespace(), "block/" + registryName.getPath() + "_side"),
                new ResourceLocation(registryName.getNamespace(), "block/" + registryName.getPath() + "_bottom"),
                new ResourceLocation(registryName.getNamespace(), "block/" + registryName.getPath() + "_top"));
    }
}
