package chapter5;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ConsistentHashRouter {
    private TreeMap<Integer, VirtualNode> hashRing = new TreeMap<>();
    private List<PhysicalNode> physicalNodes = new ArrayList<>();

    public ConsistentHashRouter(int physicalServers, int virtualNodesCount, int keyRangeUpperBound) {
        physicalNodes = IntStream.range(0, physicalServers)
                .mapToObj(i -> new PhysicalNode(i, virtualNodesCount))
                .collect(Collectors.toList());

        List<VirtualNode> virtualNodes = createVirtualServers(physicalNodes);
        assignVirtualNodesOnHashRing(virtualNodes, keyRangeUpperBound);
    }

    private void assignVirtualNodesOnHashRing(List<VirtualNode> virtualNodes, int keyRangeUpperBound) {
        virtualNodes.stream()
                .forEach(vNode -> hashRing.put(vNode.hashCode() % keyRangeUpperBound, vNode));
    }

    private List<VirtualNode> createVirtualServers(List<PhysicalNode> physicalServers) {
        return physicalServers.stream()
                .map(node -> IntStream.range(0, node.getVirtualNodes()).mapToObj(id -> new VirtualNode(id, node))
                        .collect(Collectors.toList()))
                .flatMap(vNodes -> vNodes.stream())
                .collect(Collectors.toList());

    }

    public void analyzeDistribution() {
        hashRing.entrySet()
                .stream()
                .forEach(entry -> System.out.println(String.format("Key %s on hash ring assigned to pNode-> %s",
                        entry.getKey(), entry.getValue().getPhysicalNode())));
    }
}
