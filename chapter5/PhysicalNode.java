package chapter5;

public class PhysicalNode {
    private Integer nodeId;
    private Integer virtualNodes;

    public PhysicalNode(Integer nodeId, Integer virtualNodes) {
        this.nodeId = nodeId;
        this.virtualNodes = virtualNodes;
    }

    public Integer getNodeId() {
        return nodeId;
    }

    public Integer getVirtualNodes() {
        return virtualNodes;
    }

}
