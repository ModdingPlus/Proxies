package moe.qbit.proxies.common.tileentities;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Maps;
import com.google.common.collect.Table;
import mcp.MethodsReturnNonnullByDefault;
import moe.qbit.proxies.api.CapabilityPointer;
import moe.qbit.proxies.api.SideMapping;
import moe.qbit.proxies.common.blocks.CapabilityProxyBlock;
import net.minecraft.block.BlockState;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;

import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public class GenericCapabilityProxyTileEntity extends CommonCapabilityProxyTileEntity {
    private final HashMap<Capability<?>, CapabilityPointer<?>> unsupportedPointers = new HashMap<>();
    private final Table<Capability<?>, Integer, CapabilityPointer<?>> pointers = HashBasedTable.create();
    private final SideMapping sideMapping;
    private final Map<Capability<?>, Function<?,?>> wrapperFunctions = Maps.newHashMap();

    public GenericCapabilityProxyTileEntity(TileEntityType<?> tileEntityTypeIn, SideMapping sideMapping, Capability<?>... supportedCapabilities) {
        super(tileEntityTypeIn, supportedCapabilities);
        this.sideMapping = sideMapping;
    }

    public <T> GenericCapabilityProxyTileEntity addWrapperFunction(Capability<T> capability, Function<T,T> wrapperFunction){
        this.supportedCapabilities.add(capability);
        this.wrapperFunctions.put(capability, wrapperFunction);
        return this;
    }
    public <T> Function<T,T> getWrapperFunction(Capability<T> capability){
        //noinspection unchecked
        return (Function<T, T>) this.wrapperFunctions.get(capability);
    }

    @Override
    public <T> CapabilityPointer<T> getProxyCapabilityPointer(Capability<T> capability, @Nullable Direction accessedSide, @Nullable Direction actualSide, int chainIndex) {
        if (this.supportedCapabilities.contains(capability)) {
            SideMapping.MappedSide mappedSides = this.mapSide(accessedSide,actualSide);
            int cachingKey = mappedSides.getCachingKey();
            if(!this.pointers.contains(capability, cachingKey)) {
                if(mappedSides.getTraversalDirection()!=null) {
                    //noinspection ConstantConditions
                    this.pointers.put(
                            capability,
                            cachingKey,
                            CapabilityPointer.<T>of(
                                    this.getWorld(),
                                    this.getPos().offset(mappedSides.getTraversalDirection()),
                                    mappedSides.getAccessDirection(),
                                    mappedSides.getTraversalDirection().getOpposite(),
                                    this.getWrapperFunction(capability)
                            )
                    );
                } else {
                    this.pointers.put(capability, cachingKey, CapabilityPointer.<T>empty());
                }
            }
            //noinspection unchecked
            return (CapabilityPointer<T>)this.pointers.get(capability, cachingKey);
        } else {
            if(!this.unsupportedPointers.containsKey(capability))
                this.unsupportedPointers.put(capability, CapabilityPointer.empty());
            //noinspection unchecked
            return (CapabilityPointer<T>)this.unsupportedPointers.get(capability);
        }
    }

    public SideMapping.MappedSide mapSide(@Nullable Direction accessedSide, @Nullable Direction actualSide){
        BlockState blockState = this.getBlockState();
        Direction facing = blockState.hasProperty(CapabilityProxyBlock.FACING) ? blockState.get(CapabilityProxyBlock.FACING) : null;
        return this.sideMapping.getMapping(facing, accessedSide, actualSide);
    }
}
