package dev.necro.proxies;

import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;

import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Collection;
import java.util.Stack;

@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public interface CapabilityProxy {
    /** Pointers should be static instances shared across calls wherever possible. **/
    <T> CapabilityPointer<T> getProxyCapabilityPointer(Capability<T> capability, @Nullable Direction side);

    default <T> LazyOptional<T> getProxyCapabilityHandler(final Capability<T> capability, @Nullable final Direction side){
        final Stack<CapabilityPointer<T>> pointers = new Stack<>();
        CapabilityPointer<T> currentPointer = this.getProxyCapabilityPointer(capability, side);

        LazyOptional<T> returnHandler = null;

        int iterations=0;

        // go along a chain of proxies
        while(iterations++ < 256) {
            // return empty if there is a loop or the pointer is empty
            if (pointers.contains(currentPointer) || !currentPointer.isPresent()){
                returnHandler = LazyOptional.empty();
                break;
            }
            pointers.push(currentPointer);

            TileEntity tileEntity = currentPointer.getTileEntity();
            if (tileEntity != null){
                if (tileEntity instanceof dev.necro.proxies.CapabilityProxy) {
                    // if the tile entity is a proxy next code
                    currentPointer = ((dev.necro.proxies.CapabilityProxy) tileEntity).getProxyCapabilityPointer(capability, currentPointer.getSide());
                } else {
                    LazyOptional<T> handler = tileEntity.getCapability(capability, currentPointer.getSide());

                    if (!handler.isPresent()){
                        // return a new empty LazyOptional and not the tile entity's so it can be independently invalidated
                        returnHandler = LazyOptional.empty();
                    } else {
                        // wrap the handler when it is actually needed, this allows adding things such as filters to the handler
                        //noinspection ConstantConditions
                        returnHandler = LazyOptional.of(() -> wrapHandler(pointers, handler.orElse(capability.getDefaultInstance())));
                    }

                    // invalidate the wrapped handler if the original is invalidated
                    final LazyOptional<T> finalReturnHandler = returnHandler;
                    handler.addListener(h-> finalReturnHandler.invalidate());

                    break;
                }
            } else {
                returnHandler = LazyOptional.empty();
                break;
            }

        }

        if(returnHandler==null)
            returnHandler = LazyOptional.empty();

        // add the wrapped handler to the proxies so they can invalidate them when destroyed/rotated etc.
        addCachedCapabilityHandlers(capability, pointers, returnHandler);

        return returnHandler;
    }

    <T> void addCachedCapabilityHandler(Capability<T> capability, @Nullable Direction side, LazyOptional<T> handler);

    static <T> void addCachedCapabilityHandlers(Capability<T> capability, Collection<CapabilityPointer<T>> pointers, LazyOptional<T> handler){
        for(CapabilityPointer<T> cachePointer : pointers) {
            TileEntity cacheTileEntity = cachePointer.getTileEntity();
            if (cacheTileEntity instanceof dev.necro.proxies.CapabilityProxy)
                ((dev.necro.proxies.CapabilityProxy) cacheTileEntity).addCachedCapabilityHandler(capability, cachePointer.getSide(), handler);
        }
    }

    static <T> T wrapHandler(final Stack<CapabilityPointer<T>> pointers, T handler){
        // iterate(pop) stack of capability pointers in reverse to apply wrappers in right order
        while(!pointers.empty()) {
            // warp handler with wrapper provided by proxy (filters, etc)
            handler = pointers.pop().wrap(handler);
        }
        return handler;
    }
}
