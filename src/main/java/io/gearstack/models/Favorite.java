package io.gearstack.models;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Favorite
 *
 * Models an item marked favorite
 */
@Entity(name = "Favorite")
@Table(name = "favorite")
public class Favorite implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(table = "favorite", name = "id", nullable = false, updatable = false, unique = true)
    private int id;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(table = "favorite", name = "submitted_by", referencedColumnName = "username",
                nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(table = "favorite", name = "gear_id", referencedColumnName = "id", nullable = false,
                updatable = false)
    private Gear gear;

    @Column(table = "favorite", name = "date_created", nullable = false)
    private Timestamp dateCreated;

    protected Favorite() { }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Gear getGear() {
        return gear;
    }

    public void setGear(Gear gear) {
        this.gear = gear;
    }

    public Timestamp getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Timestamp dateCreated) {
        this.dateCreated = dateCreated;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Favorite favorite = (Favorite) o;

        if (!getUser().equals(favorite.getUser())) return false;
        if (!getGear().equals(favorite.getGear())) return false;
        return getDateCreated().equals(favorite.getDateCreated());
    }

    @Override
    public int hashCode() {
        int result = getId();
        result = 31 * result + getUser().hashCode();
        result = 31 * result + getGear().hashCode();
        result = 31 * result + getDateCreated().hashCode();
        return result;
    }

    @Override
    public String toString() {
        return new StringBuilder()
                .append("Id: ").append(this.getId()).append("\n")
                .append("User: ").append(this.getUser().getUsername()).append("\n")
                .append("Gear: ").append(this.getGear().getName()).append("\n")
                .append("Date Created: ").append(this.getDateCreated().toString()).append("\n")
                .toString();
    }
}
