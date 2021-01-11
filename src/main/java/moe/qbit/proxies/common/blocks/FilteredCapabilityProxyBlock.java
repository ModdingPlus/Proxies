package moe.qbit.proxies.common.blocks;

import moe.qbit.proxies.Proxies;
import moe.qbit.proxies.common.containers.FilteredCapabilityProxyContainer;
import moe.qbit.proxies.common.tileentities.filters.FilteredCapabilityProxyTileEntity;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

import java.util.function.Supplier;

public class FilteredCapabilityProxyBlock extends CapabilityProxyBlock {

    protected FilteredCapabilityProxyBlock(Properties builder, Supplier<TileEntity> tileEntityFactory) {
        super(builder, tileEntityFactory);
    }

    @SuppressWarnings("deprecation")
    @Override
    public ActionResultType onBlockActivated(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult trace) {
        if (!world.isRemote) {
            TileEntity tileEntity = world.getTileEntity(pos);
            if (tileEntity instanceof FilteredCapabilityProxyTileEntity) {
                INamedContainerProvider containerProvider = new INamedContainerProvider() {
                    @Override
                    public ITextComponent getDisplayName() {
                        return new TranslationTextComponent("screen." + Proxies.MODID + "." + getRegistryName().getPath());
                    }

                    @Override
                    public Container createMenu(int i, PlayerInventory playerInventory, PlayerEntity playerEntity) {
                        return new FilteredCapabilityProxyContainer(i, world, pos, playerInventory, playerEntity);
                    }
                };
                NetworkHooks.openGui((ServerPlayerEntity) player, containerProvider, tileEntity.getPos());
            } else {
                throw new IllegalStateException("Named container provider is missing!");
            }
        }
        return ActionResultType.SUCCESS;
    }
}
