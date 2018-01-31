package io.gearstack.models;

import io.gearstack.enums.GearStackType;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

/**
 * GearStack
 *
 * Models a gear stack. Gear stacks contain lists of gear. Gear stack
 * is the owner of the many to many relationship between Gear and Gear stacks.
 */
@Entity(name = "GearStack")
@Table(name = "gear_stack")
@Cacheable @org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE,
            region = "gearstack.stack.entities")
public class GearStack implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    @Column(table = "gear_stack", name = "id", updatable = false, nullable = false, unique = true)
    private int id;

    @Column(table = "gear_stack", name = "date_created", nullable = false)
    private Timestamp dateCreated;

    @Column(table = "gear_stack", name = "last_modified", nullable = false)
    private Timestamp lastModified;

    @OneToOne(optional = false, orphanRemoval = true, fetch = FetchType.EAGER)
    @JoinColumn(table = "gear_stack", name = "submitted_by", referencedColumnName = "username",
                nullable = false)
    private User user;

    @Enumerated(EnumType.STRING)
    @Column(table = "gear_stack", name = "stack_type", nullable = false,
            updatable = false, length = 10)
    private GearStackType gearStackType;

    @org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE,
        region = "gearstack.stack.entities.gearCollection")
    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(
            name = "gear_gearstack_junction",
            joinColumns = @JoinColumn(name = "gear_stack_id"),
            inverseJoinColumns = @JoinColumn(name = "gear_id")
    )
    private Set<Gear> gear = new HashSet<>();

    protected GearStack() { }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public GearStackType getGearStackType() { return gearStackType; }

    public void setGearStackType(GearStackType gearStackType) { this.gearStackType = gearStackType; }

    // Keeps relationship between GearStack and Gear in sync - by addition
    public void addGear(Gear gear) {
        this.gear.add(gear);
        gear.getGearStacks().add(this);
    }

    // Keeps relationship between GearStack and Gear in sync - by removal
    public void removeGear(Gear gear) {
        this.gear.remove(gear);
        gear.getGearStacks().remove(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GearStack gearStack = (GearStack) o;

        if (getUser().equals(gearStack.getUser())) return false;
        return getGearStackType().toString().equalsIgnoreCase(gearStack.getGearStackType()
                .toString());
    }

    @Override
    public int hashCode() {
        int result = getUser().hashCode();
        result = 31 * result + getGearStackType().hashCode();
        return result;
    }

    @Override
    public String toString() {
        return new StringBuilder("GearStack:\n")
                .append("Id: ").append(this.getId()).append("\n")
                .append("User: ").append(this.getUser().getUsername()).append("\n")
                .append("Stack type: ").append(this.getGearStackType().toString()).append("\n")
                .append("Date created: ").append(this.getDateCreated().toString())
                .toString();
    }
}
