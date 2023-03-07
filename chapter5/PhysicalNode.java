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

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((nodeId == null) ? 0 : nodeId.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        PhysicalNode other = (PhysicalNode) obj;
        if (nodeId == null) {
            if (other.nodeId != null)
                return false;
        } else if (!nodeId.equals(other.nodeId))
            return false;
        return true;
    }

}
