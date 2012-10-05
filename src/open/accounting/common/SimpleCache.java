package open.accounting.common;

import java.util.LinkedHashMap;
import java.util.Map;

public class SimpleCache extends LinkedHashMap {
    private final int MAX_SIZE;
    
    public SimpleCache(int size) {
        super(size * 4 / 3 + 1);
        this.MAX_SIZE = size;
    }
    
    protected boolean removeEldestEntry(Map.Entry entry) {
        return size() > MAX_SIZE;
    }
    
    public synchronized Object put(Object key, Object object) {
        return super.put(key, object);
    }
}
