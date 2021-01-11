package moe.qbit.proxies.api.filtering;

public enum FilterMode {
    WHITELIST,
    BLACKLIST;

    public FilterMode invert(){
        if(this == FilterMode.WHITELIST)
            return FilterMode.BLACKLIST;
        else
            return FilterMode.WHITELIST;
    }
}
