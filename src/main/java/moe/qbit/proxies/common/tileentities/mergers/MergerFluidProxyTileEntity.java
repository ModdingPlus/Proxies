package moe.qbit.proxies.common.tileentities.mergers;

import moe.qbit.proxies.api.mergers.MergerFluidHandler;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.fluids.capability.IFluidHandler;

public class MergerFluidProxyTileEntity extends MergerCapabilityProxyTileEntity {
    @CapabilityInject(IFluidHandler.class)
    static Capability<IFluidHandler> FLUID_HANDLER_CAPABILITY = null;

    public MergerFluidProxyTileEntity(TileEntityType<?> tileEntityTypeIn) {
        super(tileEntityTypeIn);
        this.addMergerFunction(FLUID_HANDLER_CAPABILITY, MergerFluidHandler::new);
    }

}
