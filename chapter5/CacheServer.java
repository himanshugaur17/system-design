package chapter5;

import java.util.Map;

/* Cache server to mimick the real nodes */
public class CacheServer {
    private Integer virtualNodes;
    private Map<Integer,Employee> cache;
    public CacheServer(Integer virtualNodes, Map<Integer, Employee> cache) {
        this.virtualNodes = virtualNodes;
        this.cache = cache;
    }
}
