package dev.necro.proxies.common;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;

import javax.annotation.Nonnull;
import java.util.ListIterator;
import java.util.Optional;
import java.util.Stack;

public interface CapabilityProxy {
    /** Pointers should be static instances shared across calls wherever possible. **/
    @Nonnull
    <T> CapabilityPointer<T> getProxyCapabilityPointer(Capability<T> capability, Direction direction);

    @Nonnull
    default <T> LazyOptional<T> getProxyCapabilityHandler(Capability<T> capability, World world, Direction direction){
        final Stack<CapabilityPointer<T>> pointers = new Stack<>();
        CapabilityPointer<T> currentPointer = this.getProxyCapabilityPointer(capability, direction);

        int iterations=0;

        while(iterations++ < 256) {
            // return empty if there is a loop or the pointer is empty
            if (pointers.contains(currentPointer) || currentPointer.empty()) return LazyOptional.empty();
            pointers.push(currentPointer);

            TileEntity tileEntity = currentPointer.getTileEntity();
            if (tileEntity != null){
                if (tileEntity instanceof CapabilityProxy) {
                    currentPointer = ((CapabilityProxy) tileEntity).getProxyCapabilityPointer(capability, currentPointer.getDirection());
                } else {
                    T handler = tileEntity.getCapability(capability, currentPointer.getDirection()).orElse(null);
                    if (handler == null) return LazyOptional.empty();

                    // iterate stack of capability pointers in reverse to apply wrappers in right order
                    final ListIterator<CapabilityPointer<T>> iter = pointers.listIterator(pointers.size());
                    while(iter.hasPrevious()) {
                        // warp handler with wrapper provided by proxy (filters, etc)
                        handler = iter.previous().wrap(handler);
                    }

                    final T finalHandler = handler;
                    final LazyOptional<T> optionalHandler = LazyOptional.of(() -> finalHandler);

                    // add ha
                    for(CapabilityPointer<T> cachePointer : pointers) {
                        TileEntity cacheTileEntity = cachePointer.getTileEntity();
                        if (cacheTileEntity instanceof CapabilityProxy)
                            ((CapabilityProxy) cacheTileEntity).addCachedCapabilityHandler(capability, cachePointer.getDirection(), optionalHandler);
                    }

                    return optionalHandler;
                }
            }

        }
        return LazyOptional.empty();
    }

    <T> void addCachedCapabilityHandler(Capability<T> capability, Direction direction, LazyOptional<T> handler);
}
