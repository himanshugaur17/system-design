package chapter5;

import java.util.Map;

/* Cache server to mimick the real nodes */
public class CacheServer {
    private Integer cacheServerId;
    private Integer virtualNodes;
    private Map<Integer,Employee> cache;
    public CacheServer(Integer virtualNodes, Map<Integer, Employee> cache, Integer cacheServerId) {
        this.virtualNodes = virtualNodes;
        this.cacheServerId=cacheServerId;
        this.cache = cache;
    }
    public Employee getEmployee(Integer key){
        if(!cache.containsKey(key))
            System.out.println(String.format("%s not found in cache %s",key,cacheServerId));
        return cache.get(key);
    }
}
