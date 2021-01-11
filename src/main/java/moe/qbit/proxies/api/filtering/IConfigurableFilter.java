package moe.qbit.proxies.api.filtering;

public interface IConfigurableFilter<T> extends IFilter<T> {
    FilterSettings getFilterSettings();
}
