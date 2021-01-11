package moe.qbit.proxies.api.filtering;

import javax.annotation.Nonnull;

public interface IFilter<T> {
    boolean test(@Nonnull T stack);
}
