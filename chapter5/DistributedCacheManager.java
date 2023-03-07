package chapter5;

import java.util.ArrayList;
import java.util.EventListener;
import java.util.List;
import java.util.Map;

public class DistributedCacheManager {

    private static class Range {
        private Integer lowerBound;
        private Integer upperBound;

        public Range(Integer lowerBound, Integer upperBound) {
            this.lowerBound = lowerBound;
            this.upperBound = upperBound;
        }

        public Integer getLowerBound() {
            return lowerBound;
        }

        public void setLowerBound(Integer lowerBound) {
            this.lowerBound = lowerBound;
        }

        public Integer getUpperBound() {
            return upperBound;
        }

        public void setUpperBound(Integer upperBound) {
            this.upperBound = upperBound;
        }
    }

    private List<CacheServer> cacheServers;
    private Map<List<Range>, CacheServer> cacheServersMap;

    public DistributedCacheManager() {
        this.cacheServers = new ArrayList<>();
    }

    public void initialize(int keyRange, int virtualNodes, int cacheServerCount) {

    }

    public Employee getEmployee(int key) {
            CacheServer cacheServer=findCacheServerByKey(key);
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
