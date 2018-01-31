package io.gearstack.models;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity(name = "ProTip")
@Table(name = "pro_tip")
public class ProTip implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    @Column(table = "pro_tip", name = "id", updatable = false, nullable = false, unique = true)
    private int id;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(table = "pro_tip", name = "submitted_by", referencedColumnName = "username",
                nullable = false)
    private User submittedBy;

    @Column(table = "pro_tip", name = "tip")
    private String tip;

    @Column(table = "pro_tip", name = "date_created", nullable = false)
    private Timestamp dateCreated;

    @Column(table = "pro_tip", name = "last_modified", nullable = false)
    private Timestamp lastModified;

    @Column(table = "pro_tip", name = "upvote_score", nullable = false)
    private int upvoteScore;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(table = "pro_tip", name = "gear_id", referencedColumnName = "id",
                nullable = false, updatable = false)
    private Gear gear;

    protected ProTip() { }

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

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProTip proTip = (ProTip) o;

        if (getId() != proTip.getId()) return false;
        if (getUpvoteScore() != proTip.getUpvoteScore()) return false;
        if (!getSubmittedBy().equals(proTip.getSubmittedBy())) return false;
        if (getTip() != null ? !getTip().equals(proTip.getTip()) : proTip.getTip() != null) return false;
        if (!getDateCreated().equals(proTip.getDateCreated())) return false;
        if (!getLastModified().equals(proTip.getLastModified())) return false;
        return getGear().equals(proTip.getGear());
    }

    @Override
    public int hashCode() {
        int result = getId();
        result = 31 * result + getSubmittedBy().hashCode();
        result = 31 * result + (getTip() != null ? getTip().hashCode() : 0);
        result = 31 * result + getDateCreated().hashCode();
        result = 31 * result + getLastModified().hashCode();
        result = 31 * result + getUpvoteScore();
        result = 31 * result + getGear().hashCode();
        return result;
    }

    @Override
    public String toString() {
        return null;
    }
}
