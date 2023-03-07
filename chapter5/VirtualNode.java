package chapter5;

import java.util.Random;

public class VirtualNode {
    private Integer id;
    private PhysicalNode physicalNode;

    public VirtualNode(Integer id, PhysicalNode physicalNode) {
        this.id = id;
        this.physicalNode = physicalNode;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode()) * getSomeRandomInteger();
        result = prime * result + ((physicalNode == null) ? 0 : physicalNode.hashCode()) * getSomeRandomInteger();
        return result;
    }

    private int getSomeRandomInteger() {
        return new Random().nextInt();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        VirtualNode other = (VirtualNode) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (physicalNode == null) {
            if (other.physicalNode != null)
                return false;
        } else if (!physicalNode.equals(other.physicalNode))
            return false;
        return true;
    }

    public Integer getId() {
        return id;
    }

    public PhysicalNode getPhysicalNode() {
        return physicalNode;
    }

}
