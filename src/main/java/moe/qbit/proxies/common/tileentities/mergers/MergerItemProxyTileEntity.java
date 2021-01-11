package moe.qbit.proxies.common.tileentities.mergers;

import moe.qbit.proxies.api.mergers.MergerItemHandler;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.items.IItemHandler;

public class MergerItemProxyTileEntity extends MergerCapabilityProxyTileEntity {
    @CapabilityInject(IItemHandler.class)
    static Capability<IItemHandler> ITEM_HANDLER_CAPABILITY = null;

    public MergerItemProxyTileEntity(TileEntityType<?> tileEntityTypeIn) {
        super(tileEntityTypeIn);
        this.addMergerFunction(ITEM_HANDLER_CAPABILITY, MergerItemHandler::new);
    }

}
