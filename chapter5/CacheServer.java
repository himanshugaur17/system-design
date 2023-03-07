package chapter5;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* Cache server to mimick the real nodes */
public class CacheServer {
    private Integer cacheServerId;

    /*
     * Virtual nodes ensures that we can have servers with different infra
     * capabilities.
     * It also helps in efficient re-distribution of keys during scaling out/in.
     */

    private Integer virtualNodes;
    private Map<Integer, Employee> cache;
    private List<Range> keyRange = new ArrayList<Range>();

    public List<Range> getKeyRange() {
        return keyRange;
    }

    public void setKeyRange(List<Range> keyRange) {
        this.keyRange = keyRange;
    }

    public CacheServer(Integer virtualNodes, Integer cacheServerId) {
        this.virtualNodes = virtualNodes;
        this.cache = new HashMap<>();
        this.keyRange = new ArrayList<Range>();
        this.cacheServerId = cacheServerId;
    }

    public Employee getEmployee(Integer key) {
        if (!cache.containsKey(key))
            System.out.println(String.format("%s not found in cache %s", key, cacheServerId));
        return cache.get(key);
    }
}
