package moe.qbit.proxies.api;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public abstract class CapabilityProxyTileEntity extends TileEntity implements CapabilityProxy {
    final protected Set<Capability<?>> supportedCapabilities;
    final private Set<LazyOptional<?>> cachedHandlers = new HashSet<>();

    public CapabilityProxyTileEntity(TileEntityType<?> tileEntityTypeIn, Capability<?> ...supportedCapabilities) {
        super(tileEntityTypeIn);
        this.supportedCapabilities = Arrays.stream(supportedCapabilities).collect(Collectors.toSet());
    }

    @Override
    protected void invalidateCaps() {
        this.invalidateCachedHandlers();
        super.invalidateCaps();
    }

    public void invalidateCachedHandlers(){
        for(LazyOptional<?> handler : new HashSet<>(cachedHandlers))
            handler.invalidate();
    }

    @Override
    public <T> void addCachedCapabilityHandler(Capability<T> capability, Direction side, LazyOptional<T> handler) {
        this.cachedHandlers.add(handler);
        handler.addListener((h)->this.cachedHandlers.remove(handler));
    }

    @Override
    @Nonnull
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side)
    {
        if(this.canResolve(cap, side, 0)) {
            LazyOptional<T> resolved = this.resolve(cap, side, 0);
            addCachedCapabilityHandler(cap, side, resolved);
            return resolved;
        } else {
            if (this.supportedCapabilities.contains(cap))
                return this.getProxyCapabilityHandler(cap, side, this.getMaxProxyChainLength(cap, side));
            else
                return super.getCapability(cap, side);
        }
    }

    public abstract int getMaxProxyChainLength(@Nonnull Capability<?> cap, @Nullable Direction side);
}
