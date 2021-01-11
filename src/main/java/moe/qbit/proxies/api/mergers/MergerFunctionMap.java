package moe.qbit.proxies.api.mergers;

import net.minecraftforge.common.capabilities.Capability;

import java.util.HashMap;

public class MergerFunctionMap extends HashMap<Capability<?>, IMergerFunction<?>> {
    public <T> IMergerFunction<T> getMergerFunction(Capability<T> capability){
        //noinspection unchecked
        return (IMergerFunction<T>) this.get(capability);
    }

    public <T> void putMergerFunction(Capability<T> capability, IMergerFunction<T> mergerFunction){
        this.put(capability,mergerFunction);
    }
}
