package moe.qbit.proxies.api.mergers;

import net.minecraftforge.energy.IEnergyStorage;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.IFluidHandler;

import java.util.Arrays;
import java.util.List;

public class MergerEnergyStorage implements IEnergyStorage {
    public final List<IEnergyStorage> handlers;

    public MergerEnergyStorage(IEnergyStorage... handlers) {
        this.handlers = Arrays.asList(handlers);
    }

    public MergerEnergyStorage(List<IEnergyStorage> handlers) {
        this.handlers = handlers;
    }

    @Override
    public int receiveEnergy(int maxReceive, boolean simulate) {
        int receivedAmount = 0;

        for(IEnergyStorage h:handlers){
            receivedAmount += h.receiveEnergy(maxReceive-receivedAmount, simulate);
        }

        return receivedAmount;
    }

    @Override
    public int extractEnergy(int maxExtract, boolean simulate) {
        int extractedAmount = 0;

        for(IEnergyStorage h:handlers){
            extractedAmount += h.extractEnergy(maxExtract-extractedAmount, simulate);
        }

        return extractedAmount;
    }

    @Override
    public int getEnergyStored() {
        return handlers.stream().mapToInt(IEnergyStorage::getEnergyStored).sum();
    }

    @Override
    public int getMaxEnergyStored() {
        return handlers.stream().mapToInt(IEnergyStorage::getMaxEnergyStored).sum();
    }

    @Override
    public boolean canExtract() {
        return handlers.stream().anyMatch(IEnergyStorage::canExtract);
    }

    @Override
    public boolean canReceive() {
        return handlers.stream().anyMatch(IEnergyStorage::canReceive);
    }
}
