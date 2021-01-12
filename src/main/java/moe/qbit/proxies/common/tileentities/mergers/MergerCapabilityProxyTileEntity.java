package moe.qbit.proxies.common.tileentities.mergers;

import mcp.MethodsReturnNonnullByDefault;
import moe.qbit.proxies.api.CapabilityPointer;
import moe.qbit.proxies.api.CapabilityProxy;
import moe.qbit.proxies.api.CapabilityProxyTileEntity;
import moe.qbit.proxies.api.mergers.IMergerFunction;
import moe.qbit.proxies.api.mergers.MergerFunctionMap;
import moe.qbit.proxies.common.blocks.CapabilityProxyBlock;
import moe.qbit.proxies.common.tileentities.CommonCapabilityProxyTileEntity;
import moe.qbit.proxies.configuration.ServerConfiguration;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public class MergerCapabilityProxyTileEntity extends CommonCapabilityProxyTileEntity {
    private final MergerFunctionMap mergerFunctions = new MergerFunctionMap();
    protected final HashMap<Capability<?>, LazyOptional<?>> optionals = new HashMap<>();

    private boolean resolving = false;

    public MergerCapabilityProxyTileEntity(TileEntityType<?> tileEntityTypeIn) {
        super(tileEntityTypeIn);
    }

    public <T> MergerCapabilityProxyTileEntity addMergerFunction(Capability<T> capability, IMergerFunction<T> mergerFunction){
        this.mergerFunctions.putMergerFunction(capability, mergerFunction);
        return this;
    }

    @Override
    public boolean canResolve(Capability<?> capability, @Nullable Direction accessedSide, @Nullable Direction actualSide, int chainIndex) {
        return true;
    }

    @Override
    public <T> LazyOptional<T> resolve(Capability<T> capability, @Nullable Direction accessedSide, @Nullable Direction actualSide, int chainIndex) {
        Direction facing = this.getBlockState().get(CapabilityProxyBlock.FACING);

        if(!this.optionals.containsKey(capability)){

            if(this.mergerFunctions.containsKey(capability)) {

                TileEntity target1 = this.getWorld().getTileEntity(this.getPos().offset(facing));
                TileEntity target2 = this.getWorld().getTileEntity(this.getPos().offset(facing.getOpposite()));
                if (target1 != null || target2 != null) {

                    List<LazyOptional<T>> targetOptionals = new ArrayList<>();
                    int remainingLength = this.getMaxProxyChainLength(capability, accessedSide, actualSide) - chainIndex;

                    if (target1 != null)
                        targetOptionals.add(this.resolveFor(target1, capability, facing.getOpposite(), remainingLength));
                    if (target2 != null)
                        targetOptionals.add(this.resolveFor(target2, capability, facing, remainingLength));

                    this.optionals.put(capability, LazyOptional.<T>of(() -> {
                        List<T> targetCaps = targetOptionals.stream().filter(LazyOptional::isPresent)
                                .map(o -> o.orElseThrow(() -> new IllegalStateException("Invalid capability optional")))
                                .collect(Collectors.toList());
                        return this.mergerFunctions.getMergerFunction(capability).merge(targetCaps);
                    }));

                    targetOptionals.forEach(o -> o.addListener(t -> this.invalidateCachedHandlers()));

                    //in case of an infinite loop the handler added may be invalidated already and will thus be instantly removed
                    if (!this.optionals.containsKey(capability))
                        this.optionals.put(capability, LazyOptional.<T>empty());

                } else {
                    this.optionals.put(capability, LazyOptional.<T>empty());
                }
            } else {
                this.optionals.put(capability, LazyOptional.<T>empty());
            }


        }
        //noinspection unchecked
        return (LazyOptional<T>)this.optionals.get(capability);
    }

    public <T> LazyOptional<T> resolveFor(TileEntity tileEntity, Capability<T> capability, @Nullable Direction side, int maxChainLength){
        try {
            //check for infinite loop
            if (!this.isResolving()) {
                this.setResolving();

                LazyOptional<T> ret;

                if (tileEntity instanceof CapabilityProxy) {
                    CapabilityProxy capabilityProxy = (CapabilityProxy) tileEntity;
                    if(capabilityProxy.canResolve(capability, side, side, maxChainLength)){
                        ret = capabilityProxy.resolve(capability, side, side, maxChainLength);
                    } else {
                        ret = capabilityProxy.getProxyCapabilityHandler(capability, side, side, maxChainLength);
                    }
                } else {
                    ret = tileEntity.getCapability(capability, side);
                }

                this.setResolved();
                return ret;
            } else {
                return LazyOptional.empty();
            }
        } catch (StackOverflowError stackOverflowError){
            //making really sure that it doesn't happen
            return LazyOptional.empty();
        }
    }

    private void setResolving() { this.resolving = true; }
    private boolean isResolving() { return this.resolving; }
    private void setResolved() { this.resolving = false; }

    @Override
    public void invalidateCachedHandlers() {
        super.invalidateCachedHandlers();
        this.optionals.clear();
    }

    @Override
    public <T> CapabilityPointer<T> getProxyCapabilityPointer(Capability<T> capability, @Nullable Direction accessedSide, @Nullable Direction actualSide, int chainIndex) {
        return CapabilityPointer.empty();
    }
}
