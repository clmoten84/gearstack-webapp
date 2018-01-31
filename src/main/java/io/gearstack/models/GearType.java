package io.gearstack.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity(name = "GearType")
@Table(name = "gear_type")
public class GearType implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(table = "gear_type", name = "name", updatable = false, nullable = false, unique = true)
    private String name;

    @Column(table = "gear_type", name = "desc")
    private String description;

    protected GearType() { }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GearType gearType = (GearType) o;

        if (!getName().equals(gearType.getName())) return false;
        return getDescription().equals(gearType.getDescription());
    }

    @Override
    public int hashCode() {
        int result = getName().hashCode();
        result = 31 * result + getDescription().hashCode();
        return result;
    }

    @Override
    public String toString() {
        return new StringBuilder("Gear Type:\n")
                .append("Name: ").append(this.getName()).append("\n")
                .append("Description: ").append(this.getDescription())
                .toString();
    }
}
