package io.gearstack.models;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name = "GearType")
@Table(name = "gear_type")
public class GearType implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(table = "gear_type", name = "amazon_node_id", updatable = false, nullable = false, unique = true)
    private Long amazonNodeId;

    @Column(table = "gear_type", name = "cat_name", nullable = false)
    private String name;

    @OneToOne(mappedBy = "gear_type", fetch = FetchType.LAZY)
    @JoinColumn(table = "gear_type", name = "parent_node_id", referencedColumnName = "amazon_node_id")
    private GearType gearType;

    @Column(table = "gear_type", name = "is_leaf_node", nullable = false)
    private boolean leafNode;

    protected GearType() { }

    public Long getAmazonNodeId() { return amazonNodeId; }

    public void setAmazonNodeId(Long amazonNodeId) { this.amazonNodeId = amazonNodeId; }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public GearType getGearType() { return gearType; }

    public void setGearType(GearType gearType) { this.gearType = gearType; }

    public boolean isLeafNode() { return leafNode; }

    public void setLeafNode(boolean leafNode) { this.leafNode = leafNode; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GearType gearType = (GearType) o;

        if (!getAmazonNodeId().equals(gearType.getAmazonNodeId())) return false;
        if (!getName().equals(gearType.getName())) return false;
        return isLeafNode() == gearType.isLeafNode();
    }

    @Override
    public int hashCode() {
        int result = getName().hashCode();
        result = 31 * result + getAmazonNodeId().hashCode();
        result = 31 * result + (isLeafNode() ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        return new StringBuilder("Gear Type:\n")
                .append("Amazon Node Id: ").append(this.getAmazonNodeId()).append("\n")
                .append("Name: ").append(this.getName()).append("\n")
                .append("Leaf Node: ").append(this.isLeafNode())
                .toString();
    }
}
