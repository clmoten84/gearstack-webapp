package io.gearstack.models;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity(name = "OneLiner")
@Table(name = "one_liner")
public class OneLiner implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    @Column(table = "one_liner", name = "id", updatable = false, nullable = false, unique = true)
    private int id;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(table = "one_liner", name = "submitted_by", referencedColumnName = "username",
                nullable = false)
    private User submittedBy;

    @Column(table = "one_liner", name = "liner_value", nullable = false, length = 100)
    private String linerValue;

    @Column(table = "one_liner", name = "upvote_score", nullable = false)
    private int upvoteScore;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(table = "one_liner", name = "gear_id", nullable = false, updatable = false)
    private Gear gear;

    @Column(table = "one_liner", name = "date-created", nullable = false)
    private Timestamp dateCreated;

    @Column(table = "one_liner", name = "last_modified", nullable = false)
    private Timestamp lastModified;

    protected OneLiner() { }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getSubmittedBy() {
        return submittedBy;
    }

    public void setSubmittedBy(User submittedBy) {
        this.submittedBy = submittedBy;
    }

    public String getLinerValue() {
        return linerValue;
    }

    public void setLinerValue(String linerValue) {
        this.linerValue = linerValue;
    }

    public int getUpvoteScore() {
        return upvoteScore;
    }

    public void setUpvoteScore(int upvoteScore) {
        this.upvoteScore = upvoteScore;
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

    public Timestamp getLastModified() {
        return lastModified;
    }

    public void setLastModified(Timestamp lastModified) {
        this.lastModified = lastModified;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OneLiner oneLiner = (OneLiner) o;

        if (getId() != oneLiner.getId()) return false;
        if (getSubmittedBy().equals(oneLiner.getSubmittedBy())) return false;
        if (getUpvoteScore() != oneLiner.getUpvoteScore()) return false;
        if (getGear().equals(oneLiner.getGear())) return false;
        if (!getLinerValue().equals(oneLiner.getLinerValue())) return false;
        if (!getDateCreated().equals(oneLiner.getDateCreated())) return false;
        return getLastModified().equals(oneLiner.getLastModified());
    }

    @Override
    public int hashCode() {
        int result = getId();
        result = 31 * result + getSubmittedBy().hashCode();
        result = 31 * result + getLinerValue().hashCode();
        result = 31 * result + getUpvoteScore();
        result = 31 * result + getGear().hashCode();
        result = 31 * result + getDateCreated().hashCode();
        result = 31 * result + getLastModified().hashCode();
        return result;
    }

    @Override
    public String toString() {
        return null;
    }
}
