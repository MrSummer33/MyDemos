package j2se;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by BaoCai on 2019/2/1. Description:
 */
public class LRUCache {

    private int capacity;

    private Map<Object, Object> values = new LinkedHashMap();

    public LRUCache(int capacity) {
        this.capacity = capacity;
    }

    public Object get(Object key) {
        Object value = values.get(key);
        if (null != value) {
            values.put(key, value);
        }
        return value;
    }

    public void put(String key, Object value) {
        if (values.size() >= capacity) {
            Iterator iterator = values.entrySet().iterator();

            Set<Object> keys = values.keySet();

            //不符合O(1)
            for (int i = 1; i <= capacity; i++) {
                iterator.next();
                if (i == capacity) {
                    iterator.remove();
                }
            }
        }
        values.put(key, value);
    }
}
