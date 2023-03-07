package chapter5;

import java.util.ArrayList;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ConsistentHashRouter {
    private TreeMap<Integer,VirtualNode> hashRing=new TreeMap<>();
    private List<PhysicalNode> physicalNodes=new ArrayList<>();
    public ConsistentHashRouter(int physicalServers, int virtualNodesCount, int keyRangeUpperBound){
        int totalvNodes=physicalServers*virtualNodesCount;
        
        physicalNodes=IntStream.range(0, physicalServers)
        .mapToObj(i->new PhysicalNode(i,virtualNodesCount))
        .collect(Collectors.toList());

        List<VirtualNode> virtualNodes=createVirtualServers(physicalNodes,virtualNodesCount);
        assignVirtualNodesOnHashRing(virtualNodes,keyRangeUpperBound);
    }
    private void assignVirtualNodesOnHashRing(List<VirtualNode> virtualNodes, int keyRangeUpperBound) {
    }
    private List<VirtualNode> createVirtualServers(List<PhysicalNode> physicalNodes2, int virtualNodesCount) {
        return null;
    }
}
