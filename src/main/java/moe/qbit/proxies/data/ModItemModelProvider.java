package moe.qbit.proxies.data;

import moe.qbit.proxies.Proxies;
import moe.qbit.proxies.common.blocks.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.data.DataGenerator;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(DataGenerator generator, ExistingFileHelper existingFileHelper) {
        super(generator, Proxies.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        this.withExistingParentBlock(ModBlocks.ITEM_PROXY.get());
        this.withExistingParentBlock(ModBlocks.SIDED_ITEM_PROXY.get());
        this.withExistingParentBlock(ModBlocks.NULLSIDED_ITEM_PROXY.get());
        this.withExistingParentBlock(ModBlocks.JUNCTION_ITEM_PROXY.get());
        this.withExistingParentBlock(ModBlocks.FILTERED_ITEM_PROXY.get());
        this.withExistingParentBlock(ModBlocks.MERGER_ITEM_PROXY.get());

        this.withExistingParentBlock(ModBlocks.FLUID_PROXY.get());
        this.withExistingParentBlock(ModBlocks.SIDED_FLUID_PROXY.get());
        this.withExistingParentBlock(ModBlocks.NULLSIDED_FLUID_PROXY.get());
        this.withExistingParentBlock(ModBlocks.JUNCTION_FLUID_PROXY.get());
        this.withExistingParentBlock(ModBlocks.FILTERED_FLUID_PROXY.get());
        this.withExistingParentBlock(ModBlocks.MERGER_FLUID_PROXY.get());

        this.withExistingParentBlock(ModBlocks.ENERGY_PROXY.get());
        this.withExistingParentBlock(ModBlocks.JUNCTION_ENERGY_PROXY.get());
        this.withExistingParentBlock(ModBlocks.MERGER_ENERGY_PROXY.get());
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
