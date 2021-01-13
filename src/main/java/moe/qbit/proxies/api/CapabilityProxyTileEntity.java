package moe.qbit.proxies.api;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.*;
import java.util.stream.Collectors;

public abstract class CapabilityProxyTileEntity extends TileEntity implements CapabilityProxy {
    final protected Set<Capability<?>> supportedCapabilities;
    final private Set<LazyOptional<?>> cachedHandlers = new HashSet<>();
    final private List<Runnable> invalidationListeners = new ArrayList<>();

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
        LazyOptional<?> handler;
        for (Iterator<LazyOptional<?>> iter = this.cachedHandlers.iterator();
             iter.hasNext();) {
            handler=iter.next();
            iter.remove();
            handler.invalidate();
        }
        Runnable listener;
        for (Iterator<Runnable> iter = this.invalidationListeners.iterator();
             iter.hasNext();) {
            listener=iter.next();
            iter.remove();
            listener.run();
        }
    }

    @Override
    public <T> void addCachedCapabilityHandler(Capability<T> capability, Direction accessedSide, Direction actualSide, LazyOptional<T> handler) {
        this.cachedHandlers.add(handler);
        handler.addListener((h)->this.cachedHandlers.remove(handler));
    }

    @Override
    public void addInvalidationListener(Capability<?> capability, Runnable listener) {
        this.invalidationListeners.add(listener);
    }

    @Override
    @Nonnull
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side)
    {
        if(this.canResolve(cap, side, side, 0)) {
            LazyOptional<T> resolved = this.resolve(cap, side, side, 0);
            return resolved;
        } else {
            if (this.supportedCapabilities.contains(cap))
                return this.getProxyCapabilityHandler(cap, side, side, this.getMaxProxyChainLength(cap, side, side));
            else
                return super.getCapability(cap, side);
        }
    }

    public abstract int getMaxProxyChainLength(@Nonnull Capability<?> cap, @Nullable Direction accessedSide, @Nullable Direction actualSide);
}
