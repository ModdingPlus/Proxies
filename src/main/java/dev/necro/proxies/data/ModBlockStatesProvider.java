package dev.necro.proxies.data;

import dev.necro.proxies.Proxies;
import dev.necro.proxies.common.blocks.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.data.DataGenerator;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.generators.BlockModelBuilder;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ExistingFileHelper;

public class ModBlockStatesProvider extends BlockStateProvider {

    public ModBlockStatesProvider(DataGenerator gen, ExistingFileHelper exFileHelper) {
        super(gen, Proxies.MODID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        this.simpleDirectionalBlock(ModBlocks.ITEM_PROXY);
        this.simpleDirectionalBlock(ModBlocks.SIDED_ITEM_PROXY);
        this.simpleDirectionalBlock(ModBlocks.FLUID_PROXY);
        this.simpleDirectionalBlock(ModBlocks.SIDED_FLUID_PROXY);
    }

    public void simpleDirectionalBlock(Block block){
        this.directionalBlock(block, this.simpleCubeBottomTop(block));
    }

    public BlockModelBuilder simpleCubeBottomTop(Block block){
        ResourceLocation registryName = block.getRegistryName();
        return this.models().cubeBottomTop(registryName.getPath(),
                new ResourceLocation(registryName.getNamespace(), "block/" + registryName.getPath() + "_side"),
                new ResourceLocation(registryName.getNamespace(), "block/" + registryName.getPath() + "_bottom"),
                new ResourceLocation(registryName.getNamespace(), "block/" + registryName.getPath() + "_top"));
    }
}
