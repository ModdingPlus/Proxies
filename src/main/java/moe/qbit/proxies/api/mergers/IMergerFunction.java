package moe.qbit.proxies.api.mergers;

import java.util.List;

@FunctionalInterface
public interface IMergerFunction<T> {
    T merge(List<T> inputs);
}
