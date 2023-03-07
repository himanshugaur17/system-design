package chapter5;

public class VirtualNode {
    private Integer id;
    private PhysicalNode physicalNode;

    public VirtualNode(Integer id, PhysicalNode physicalNode) {
        this.id = id;
        this.physicalNode = physicalNode;
    }

}
