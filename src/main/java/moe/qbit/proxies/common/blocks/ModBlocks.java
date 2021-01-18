package moe.qbit.proxies.common.blocks;

import moe.qbit.proxies.Proxies;
import moe.qbit.proxies.common.tileentities.ModTileEntities;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

@SuppressWarnings({"Convert2MethodRef", "ConstantConditions"}) //converting lambdas into method ref will result in an NPE
public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Proxies.MODID);

    public static void init(){
        BLOCKS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }

    private static final AbstractBlock.Properties PROPS = Block.Properties.from(Blocks.CRAFTING_TABLE);

    public static final RegistryObject<Block> ITEM_PROXY = BLOCKS.register("item_proxy", () -> new CapabilityProxyBlock(PROPS, ()-> ModTileEntities.ITEM_PROXY.create()));
    public static final RegistryObject<Block> SIDED_ITEM_PROXY = BLOCKS.register("sided_item_proxy", () -> new CapabilityProxyBlock(PROPS, ()-> ModTileEntities.SIDED_ITEM_PROXY.create()));
    public static final RegistryObject<Block> NULLSIDED_ITEM_PROXY = BLOCKS.register("nullsided_item_proxy", () -> new CapabilityProxyBlock(PROPS, ()-> ModTileEntities.NULLSIDED_ITEM_PROXY.create()));
    public static final RegistryObject<Block> JUNCTION_ITEM_PROXY = BLOCKS.register("junction_item_proxy", () -> new JunctionCapabilityProxyBlock(PROPS, ()-> ModTileEntities.JUNCTION_ITEM_PROXY.create()));
    public static final RegistryObject<Block> FILTERED_ITEM_PROXY = BLOCKS.register("filtered_item_proxy", () -> new FilteredCapabilityProxyBlock(PROPS, ()-> ModTileEntities.FILTERED_ITEM_PROXY.create()));
    public static final RegistryObject<Block> MERGER_ITEM_PROXY = BLOCKS.register("merger_item_proxy", () -> new MergerCapabilityProxyBlock(PROPS, ()-> ModTileEntities.MERGER_ITEM_PROXY.create()));

    public static final RegistryObject<Block> FLUID_PROXY = BLOCKS.register("fluid_proxy", () -> new CapabilityProxyBlock(PROPS, ()-> ModTileEntities.FLUID_PROXY.create()));
    public static final RegistryObject<Block> SIDED_FLUID_PROXY = BLOCKS.register("sided_fluid_proxy", () -> new CapabilityProxyBlock(PROPS, ()-> ModTileEntities.SIDED_FLUID_PROXY.create()));
    public static final RegistryObject<Block> NULLSIDED_FLUID_PROXY = BLOCKS.register("nullsided_fluid_proxy", () -> new CapabilityProxyBlock(PROPS, ()-> ModTileEntities.NULLSIDED_FLUID_PROXY.create()));
    public static final RegistryObject<Block> JUNCTION_FLUID_PROXY = BLOCKS.register("junction_fluid_proxy", () -> new JunctionCapabilityProxyBlock(PROPS, ()-> ModTileEntities.JUNCTION_FLUID_PROXY.create()));
    public static final RegistryObject<Block> FILTERED_FLUID_PROXY = BLOCKS.register("filtered_fluid_proxy", () -> new FilteredCapabilityProxyBlock(PROPS, ()-> ModTileEntities.FILTERED_FLUID_PROXY.create()));
    public static final RegistryObject<Block> MERGER_FLUID_PROXY = BLOCKS.register("merger_fluid_proxy", () -> new MergerCapabilityProxyBlock(PROPS, ()-> ModTileEntities.MERGER_ITEM_PROXY.create()));

    public static final RegistryObject<Block> ENERGY_PROXY = BLOCKS.register("energy_proxy", () -> new CapabilityProxyBlock(PROPS, ()-> ModTileEntities.ENERGY_PROXY.create()));
    public static final RegistryObject<Block> JUNCTION_ENERGY_PROXY = BLOCKS.register("junction_energy_proxy", () -> new JunctionCapabilityProxyBlock(PROPS, ()-> ModTileEntities.JUNCTION_ENERGY_PROXY.create()));
    public static final RegistryObject<Block> MERGER_ENERGY_PROXY = BLOCKS.register("merger_energy_proxy", () -> new MergerCapabilityProxyBlock(PROPS, ()-> ModTileEntities.MERGER_ENERGY_PROXY.create()));
}
