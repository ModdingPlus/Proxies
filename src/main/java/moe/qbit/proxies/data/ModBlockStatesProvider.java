package moe.qbit.proxies.data;

import moe.qbit.proxies.Proxies;
import moe.qbit.proxies.common.blocks.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.data.DataGenerator;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.generators.BlockModelBuilder;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ModBlockStatesProvider extends BlockStateProvider {

    public ModBlockStatesProvider(DataGenerator gen, ExistingFileHelper exFileHelper) {
        super(gen, Proxies.MODID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        this.simpleDirectionalBlock(ModBlocks.ITEM_PROXY.get());
        this.simpleDirectionalBlock(ModBlocks.SIDED_ITEM_PROXY.get());
        this.simpleDirectionalBlock(ModBlocks.NULLSIDED_ITEM_PROXY.get());
        this.simpleBlock(ModBlocks.JUNCTION_ITEM_PROXY.get());
        this.simpleBlock(ModBlocks.SIDED_JUNCTION_ITEM_PROXY.get());
        this.simpleDirectionalBlock(ModBlocks.FILTERED_ITEM_PROXY.get());
        this.simpleDirectionalBlock(ModBlocks.MERGER_ITEM_PROXY.get());

        this.simpleDirectionalBlock(ModBlocks.FLUID_PROXY.get());
        this.simpleDirectionalBlock(ModBlocks.SIDED_FLUID_PROXY.get());
        this.simpleDirectionalBlock(ModBlocks.NULLSIDED_FLUID_PROXY.get());
        this.simpleBlock(ModBlocks.JUNCTION_FLUID_PROXY.get());
        this.simpleBlock(ModBlocks.SIDED_JUNCTION_FLUID_PROXY.get());
        this.simpleDirectionalBlock(ModBlocks.FILTERED_FLUID_PROXY.get());
        this.simpleDirectionalBlock(ModBlocks.MERGER_FLUID_PROXY.get());

        this.simpleDirectionalBlock(ModBlocks.ENERGY_PROXY.get());
        this.simpleBlock(ModBlocks.JUNCTION_ENERGY_PROXY.get());
        this.simpleDirectionalBlock(ModBlocks.MERGER_ENERGY_PROXY.get());
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
