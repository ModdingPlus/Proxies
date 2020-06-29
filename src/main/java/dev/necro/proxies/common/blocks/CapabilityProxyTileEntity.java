package dev.necro.proxies.common.blocks;

import com.google.common.base.Optional;
import dev.necro.proxies.common.CapabilityPointer;
import dev.necro.proxies.common.CapabilityProxy;
import net.minecraft.block.DirectionalBlock;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.common.util.NonNullSupplier;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class CapabilityProxyTileEntity extends TileEntity implements CapabilityProxy {
    private List<> cachedHandlers =

    public CapabilityProxyTileEntity(TileEntityType<?> tileEntityTypeIn) {
        super(tileEntityTypeIn);
    }

    @Nonnull
    @Override
    public <T> CapabilityPointer<T> getProxyCapabilityPointer(Capability<T> capability, Direction direction) {
        return new CapabilityPointer<>(this.getWorld(),this.getPos(),this.getBlockState().get(DirectionalBlock.FACING));
    }

    @Override
    public <T> void addCachedCapabilityHandler(Capability<T> capability, Direction direction, LazyOptional<T> handler) {

    }

    @Override
    @Nonnull
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side)
    {
        Optional<T> handler = this.getProxyCapabilityHandler(cap, this.getWorld());
        LazyOptional<T> lazyOptional = handler.isPresent() ? LazyOptional.of(handler::get) : LazyOptional.empty();
        return LazyOptional.of(lazyOptional);
    }
}
