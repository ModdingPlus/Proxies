package moe.qbit.proxies.api.filtering;

import javax.annotation.Nonnull;

public class FilterSettings {
    private FilterMode mode = FilterMode.WHITELIST;
    private boolean ignoreNBT = false;

    public FilterSettings() {}
    public FilterSettings(FilterMode mode, boolean ignoreNBT) {
        this.mode = mode;
        this.ignoreNBT = ignoreNBT;
    }

    public boolean isIgnoreNBT() {
        return ignoreNBT;
    }

    public void setIgnoreNBT(boolean ignoreNBT) {
        this.ignoreNBT = ignoreNBT;
    }
    public void toggleIgnoreTags() {
        this.ignoreNBT = !this.ignoreNBT;
    }

    public FilterMode getMode() {
        return mode;
    }
    public boolean isWhitelist() {
        return mode == FilterMode.WHITELIST;
    }
    public void setMode(FilterMode mode) {
        this.mode = mode;
    }
    public void toggleMode() {
        this.mode = this.mode.invert();
    }

    public byte toNumber(){
        return (byte) ((this.mode == FilterMode.WHITELIST ? 0 : 1) |
                        ((this.ignoreNBT ? 1 : 0) << 1));
    }
    public void fromNumber(byte num){
        this.mode = (num&1) == 0 ? FilterMode.WHITELIST : FilterMode.BLACKLIST;
        this.ignoreNBT = (num&2) == 2;
    }
    public void copy(FilterSettings other){
        this.setMode(other.getMode());
        this.setIgnoreNBT(other.isIgnoreNBT());
    }

    public static FilterSettings createFromNumber(byte num){
        return new FilterSettings((num&1) == 0 ? FilterMode.WHITELIST : FilterMode.BLACKLIST, (num&2) == 2);
    }

    public boolean equals(@Nonnull FilterSettings other){
        return other.getMode() == this.getMode() && other.isIgnoreNBT() == this.ignoreNBT;
    }
}
