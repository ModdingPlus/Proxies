package moe.qbit.proxies.common.tileentities;

import mcp.MethodsReturnNonnullByDefault;
import moe.qbit.proxies.api.CapabilityPointer;
import moe.qbit.proxies.api.CapabilityProxyTileEntity;
import moe.qbit.proxies.common.blocks.CapabilityProxyBlock;
import moe.qbit.proxies.configuration.ServerConfiguration;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.HashMap;

@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public abstract class CommonCapabilityProxyTileEntity extends CapabilityProxyTileEntity {
    public CommonCapabilityProxyTileEntity(TileEntityType<?> tileEntityTypeIn, Capability<?>... supportedCapabilities) {
        super(tileEntityTypeIn, supportedCapabilities);
    }

    @Override
    public int getMaxProxyChainLength(@Nonnull Capability<?> cap, @Nullable Direction side) {
        return ServerConfiguration.CONFIGURATION.chain_length_limit.get();
    }
}
