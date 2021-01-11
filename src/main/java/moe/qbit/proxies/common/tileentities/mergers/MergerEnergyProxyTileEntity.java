package moe.qbit.proxies.common.tileentities.mergers;

import moe.qbit.proxies.api.mergers.MergerEnergyStorage;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.energy.IEnergyStorage;

public class MergerEnergyProxyTileEntity extends MergerCapabilityProxyTileEntity {
    @CapabilityInject(IEnergyStorage.class)
    static Capability<IEnergyStorage> ENERGY_STORAGE_CAPABILITY = null;

    public MergerEnergyProxyTileEntity(TileEntityType<?> tileEntityTypeIn) {
        super(tileEntityTypeIn);
        this.addMergerFunction(ENERGY_STORAGE_CAPABILITY, MergerEnergyStorage::new);
    }

}
