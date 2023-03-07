package chapter5;

import java.util.List;
import java.util.TreeMap;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class DistributedCacheManager {
    private List<CacheServer> cacheServers;
    private TreeMap<Integer, CacheServer> cachingServersTreeMap;

    public void initialize(int keyRange, int virtualNodes, int cacheServerCount) {
        cacheServers = IntStream.range(1, cacheServerCount + 1)
                .mapToObj(i -> new CacheServer(virtualNodes, i))
                .collect(Collectors.toList());
        cachingServersTreeMap = assignHashPartition(keyRange, cacheServers, virtualNodes);
    }

    private TreeMap<Integer, CacheServer> assignHashPartition(int keyRange, List<CacheServer> cacheServerList,
            int virtualNodes) {
        int serverCount = cacheServerList.size();
        int partitionSize = keyRange / (virtualNodes * serverCount);
        int keyStart = 0;
        TreeMap<Integer, CacheServer> treeMap = new TreeMap<>();
        while (virtualNodes-- != 0) {
            serverCount = cacheServerList.size();
            while (serverCount-- != 0 && keyStart < keyRange) {
                treeMap.put(keyStart, cacheServerList.get(serverCount - 1));
                keyStart += partitionSize;
            }
        }
        return treeMap;
    }

    public Employee getEmployee(int key) {
        CacheServer cacheServer = findCacheServerByKey(key);
        return cacheServer.getEmployee(key);
    }

    private CacheServer findCacheServerByKey(int key) {
        return cachingServersTreeMap.floorEntry(key).getValue();
    }

    public void addNewCacheServer() {
        redistributeExistingCachedData();
    }

    private void redistributeExistingCachedData() {

    }

    public void addEmployeeToCorrectServerNode(Employee e){
        
    }

}
