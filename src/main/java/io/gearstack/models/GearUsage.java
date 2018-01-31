package io.gearstack.models;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity(name = "GearUsage")
@Table(name = "gear_usage")
public class GearUsage implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(table = "gear_usage", name = "id", updatable = false, nullable = false, unique = true)
    private int id;

    @Column(table = "gear_usage", name = "gear_stack_id", nullable = false, updatable = false)
    private int gearStackId;

    @Column(table = "gear_usage", name = "gear_id", nullable = false, updatable = false)
    private int gearId;

    @Column(table = "gear_usage", name = "usage", nullable = false)
    private String usage;

    @Column(table = "gear_usage", name = "date_created", nullable = false)
    private Timestamp dateCreated;

    @Column(table = "gear_usage", name = "last_modified", nullable = false)
    private Timestamp lastModified;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(table = "gear_usage", name = "submitted_by", referencedColumnName = "username",
                nullable = false)
    private User submittedBy;

    protected GearUsage() { }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getGearStackId() {
        return gearStackId;
    }

    public void setGearStackId(int gearStackId) {
        this.gearStackId = gearStackId;
    }

    public int getGearId() {
        return gearId;
    }

    public void setGearId(int gearId) {
        this.gearId = gearId;
    }

    public String getUsage() {
        return usage;
    }

    public void setUsage(String usage) {
        this.usage = usage;
    }

    public Timestamp getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Timestamp dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Timestamp getLastModified() {
        return lastModified;
    }

    public void setLastModified(Timestamp lastModified) {
        this.lastModified = lastModified;
    }

    public User getSubmittedBy() {
        return submittedBy;
    }

    public void setSubmittedBy(User submittedBy) {
        this.submittedBy = submittedBy;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GearUsage gearUsage = (GearUsage) o;

        if (getId() != gearUsage.getId()) return false;
        if (getGearStackId() != gearUsage.getGearStackId()) return false;
        if (getGearId() != gearUsage.getGearId()) return false;
        if (!getUsage().equals(gearUsage.getUsage())) return false;
        if (!getDateCreated().equals(gearUsage.getDateCreated())) return false;
        if (!getLastModified().equals(gearUsage.getLastModified())) return false;
        return getSubmittedBy().equals(gearUsage.getSubmittedBy());
    }

    @Override
    public int hashCode() {
        int result = getId();
        result = 31 * result + getGearStackId();
        result = 31 * result + getGearId();
        result = 31 * result + getUsage().hashCode();
        result = 31 * result + getDateCreated().hashCode();
        result = 31 * result + getLastModified().hashCode();
        result = 31 * result + getSubmittedBy().hashCode();
        return result;
    }

    @Override
    public String toString() {
        return new StringBuilder("Gear Usage:\n")
                .append("Id: ").append(this.getId()).append("\n")
                .append("Submitted by: ").append(this.getSubmittedBy().getUsername()).append("\n")
                .append("GearStack Id: ").append(this.getGearStackId()).append("\n")
                .append("Gear Id: ").append(this.getGearId()).append("\n")
                .append("Date created: ").append(this.getDateCreated().toString())
                .toString();
    }
}
