package io.gearstack.models;

import javax.persistence.*;
import java.io.Serializable;

/**
 * GearQuestion
 *
 * Extends Question type. Question type that refers to
 * a specific gear reference.
 */
@Entity(name = "GearQuestion")
@Table(name = "gear_question")
public class GearQuestion extends Question implements Serializable {

    private static final long serialVersionUID = 1L;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(table = "gear_question", name = "gear_id", referencedColumnName = "id",
                nullable = false, updatable = false)
    private Gear gear;

    protected GearQuestion() { }

    public Gear getGear() {
        return gear;
    }

    public void setGear(Gear gear) {
        this.gear = gear;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof GearQuestion) {
            return super.equals(o) && this.getGear().equals(((GearQuestion) o).getGear());
        }
        return false;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + this.getGear().hashCode();
        return result;
    }

    @Override
    public String toString() {
        return new StringBuilder("Gear Question:\n")
                .append(super.toString()).append("\n")
                .append("Gear: ").append(this.getGear().getName())
                .toString();
    }
}
