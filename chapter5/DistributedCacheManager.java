package chapter5;

import java.util.ArrayList;
import java.util.EventListener;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class DistributedCacheManager {
    private List<CacheServer> cacheServers;
    private Map<Range, CacheServer> cacheServersMap;

    public void initialize(int keyRange, int virtualNodes, int cacheServerCount) {
        cacheServers = IntStream.range(0, cacheServerCount)
                .mapToObj(i -> new CacheServer(5, i))
                .collect(Collectors.toList());
        cacheServersMap = assignHashKeyRange(keyRange, cacheServers, virtualNodes);

    }

    private Map<Range, CacheServer> assignHashKeyRange(int keyRange, List<CacheServer> cacheServerList,
            int virtualNodes) {
        int virtualResCount = cacheServerList.size() * virtualNodes;
        int perVirtualNode = keyRange / virtualResCount;
        int rem = keyRange % virtualResCount;
        int cycleCount = 0;
        if (rem != 0)
            cacheServerList.get(0).getKeyRange().add(new Range(-1, rem));
        while (cycleCount != virtualNodes) {
            for (int j = 0; j < cacheServerList.size(); j++) {
                int lowerBound = cycleCount * j * perVirtualNode - 1 + rem;
                cacheServerList.get(j).getKeyRange()
                        .add(new Range(lowerBound, Math.min(keyRange, lowerBound + perVirtualNode)));
            }
            cycleCount++;
        }
        return null;
    }

    public Employee getEmployee(int key) {
        CacheServer cacheServer = findCacheServerByKey(key);
        return cacheServer.getEmployee(key);
    }

    private CacheServer findCacheServerByKey(int key) {

        return null;
    }

    public void addNewCacheServer() {
        redistributeExistingCachedData();
    }

    private void redistributeExistingCachedData() {

    }

}
