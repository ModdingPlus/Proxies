package moe.qbit.proxies.api;

import mcp.MethodsReturnNonnullByDefault;
import moe.qbit.proxies.configuration.ServerConfiguration;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;

import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Collection;
import java.util.List;
import java.util.Stack;

@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public interface CapabilityProxy {
    /** Pointers should be static instances shared across calls wherever possible. **/
    <T> CapabilityPointer<T> getProxyCapabilityPointer(Capability<T> capability, @Nullable Direction accessedSide, @Nullable Direction actualSide, int chainIndex);

    default <T> LazyOptional<T> getProxyCapabilityHandler(final Capability<T> capability, @Nullable final Direction accessedSide, @Nullable final Direction actualSide, int maxChainLength){
        final Stack<CapabilityPointer<T>> pointers = new Stack<>();
        CapabilityPointer<T> currentPointer = this.getProxyCapabilityPointer(capability, accessedSide, actualSide, 0);

        LazyOptional<T> returnHandler = null;

        int chainIndex=0;
        //TODO: remove banned targets from default implementation
        List<? extends String> bannedTargets = ServerConfiguration.CONFIGURATION.banned_proxy_targets.get();

        // go along a chain of proxies
        while(chainIndex++ < maxChainLength) {
            // return empty if there is a loop or the pointer is empty

            if (!currentPointer.isPresent()){
                returnHandler = LazyOptional.empty();
                break;
            }

            pointers.push(currentPointer);

            TileEntity tileEntity = currentPointer.getTileEntity();
            if (tileEntity != null){

                //noinspection ConstantConditions
                if(bannedTargets.contains(tileEntity.getType().getRegistryName().toString())){
                    returnHandler = LazyOptional.empty();
                    break;
                }

                if (tileEntity instanceof CapabilityProxy) {
                    // if the tile entity is a proxy next code

                    CapabilityProxy capabilityProxy = (CapabilityProxy) tileEntity;

                    if(capabilityProxy.canResolve(capability, currentPointer.getAccessedSide(), currentPointer.getActualSide(), chainIndex)){
                        // if the proxy can resolve by itself, don't traverse further but resolve using proxy
                        //the code below is the same as for regular tile entities but using the resolve function
                        LazyOptional<T> handler = capabilityProxy.resolve(capability, currentPointer.getAccessedSide(), currentPointer.getActualSide(), chainIndex);

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
                    } else {
                        currentPointer = capabilityProxy.getProxyCapabilityPointer(capability, currentPointer.getAccessedSide(), currentPointer.getActualSide(), chainIndex);
                    }

                } else {
                    LazyOptional<T> handler = tileEntity.getCapability(capability, currentPointer.getAccessedSide());

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

    default boolean canResolve(final Capability<?> capability, @Nullable final Direction accessedSide, @Nullable final Direction actualSide, int chainIndex){
        return false;
    }
    default <T> LazyOptional<T> resolve(final Capability<T> capability, @Nullable final Direction accessedSide, @Nullable final Direction actualSide, int chainIndex){
        //TODO: this might be used to implement advanced caching in the future
        //for now this can be used for advanced routing for things like mergers
        return LazyOptional.empty();
    }

    <T> void addCachedCapabilityHandler(Capability<T> capability, @Nullable Direction accessedSide, @Nullable Direction actualSide, LazyOptional<T> handler);

    static <T> void addCachedCapabilityHandlers(Capability<T> capability, Collection<CapabilityPointer<T>> pointers, LazyOptional<T> handler){
        //TODO: maybe replace this with addInvalidationListener
        for(CapabilityPointer<T> cachePointer : pointers) {
            TileEntity cacheTileEntity = cachePointer.getTileEntity();
            if (cacheTileEntity instanceof CapabilityProxy)
                ((CapabilityProxy) cacheTileEntity).addCachedCapabilityHandler(capability, cachePointer.getAccessedSide(), cachePointer.getActualSide(), handler);
        }
    }

    void addInvalidationListener(Capability<?> capability, Runnable listener);

    static <T> T wrapHandler(final Stack<CapabilityPointer<T>> pointers, T handler){
        // iterate(pop) stack of capability pointers in reverse to apply wrappers in right order
        while(!pointers.empty()) {
            // warp handler with wrapper provided by proxy (filters, etc)
            handler = pointers.pop().wrap(handler);
        }
        return handler;
    }
}
