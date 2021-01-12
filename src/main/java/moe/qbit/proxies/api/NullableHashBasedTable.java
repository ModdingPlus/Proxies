package moe.qbit.proxies.api;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class NullableHashBasedTable<R, C, V> {
    private final HashMap<R,HashMap<C, V>> table = new HashMap<>();

    public NullableHashBasedTable() {}

    public boolean contains(@Nullable R rowKey, @Nullable C columnKey) {
        return this.table.containsKey(rowKey) && this.table.get(rowKey).containsKey(columnKey);
    }

    public boolean containsRow(@Nullable R rowKey) {
        return this.table.containsKey(rowKey);
    }

    public boolean containsColumn(@Nullable C columnKey) {
        return this.table.values().stream().anyMatch(row-> row.containsKey(columnKey));
    }

    public boolean containsValue(@Nullable V value) {
        return this.table.values().stream().anyMatch(row->row.containsValue(value));
    }

    public V get(@Nullable R rowKey, @Nullable C columnKey) {
        return this.table.get(rowKey).get(columnKey);
    }

    public boolean isEmpty() {
        return this.table.isEmpty() || this.table.values().stream().allMatch(HashMap::isEmpty);
    }

    public int size() {
        return this.table.size() == 0 ? 0 : this.table.values().stream().mapToInt(HashMap::size).sum();
    }

    public void clear() {
        this.table.clear();
    }

    @Nullable
    public V put(R rowKey, C columnKey, V value) {
        if(this.table.containsKey(rowKey)){
            return this.table.get(rowKey).put(columnKey, value);
        }else{
            HashMap<C, V> newMap = new HashMap<C,V>();
            this.table.put(rowKey,newMap);
            return newMap.put(columnKey, value);
        }
    }

    @Nullable
    public V remove(@Nullable R rowKey, @Nullable C columnKey) {
        HashMap<C, V> row = this.table.get(rowKey);
        V ret = row.remove(columnKey);
        if(row.isEmpty())
            this.table.remove(rowKey);
        return ret;
    }

    public Map<C, V> row(R rowKey) {
        return this.table.get(rowKey);
    }

    public Map<R, V> column(C columnKey) {
        HashMap<R,V> ret = new HashMap<>();
        for(R key : this.table.keySet()){
            HashMap<C, V> row = this.table.get(key);
            if(row.containsKey(columnKey)){
                ret.put(key, row.get(columnKey));
            }
        }
        return ret;
    }

    public Set<R> rowKeySet() {
        return this.table.keySet();
    }
}
