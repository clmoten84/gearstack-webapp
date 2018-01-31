package io.gearstack.models;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity(name = "Review")
@Table(name = "review")
public class Review implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    @Column(table = "review", name = "id", updatable = false, nullable = false, unique = true)
    private int id;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(table = "review", name = "submitted_by", referencedColumnName = "username",
                nullable = false)
    private User submittedBy;

    @Column(table = "review", name = "date_created", nullable = false)
    private Timestamp dateCreated;

    @Column(table = "review", name = "last_modified", nullable = false)
    private Timestamp lastModified;

    @Column(table = "review", name = "review")
    private String review;

    @Column(table = "review", name = "upvote_score", nullable = false)
    private int upvoteScore;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(table = "review", name = "gear_id", referencedColumnName = "id",
                nullable = false, updatable = false)
    private Gear gear;

    protected Review() { }

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

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
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

        Review review1 = (Review) o;

        if (getId() != review1.getId()) return false;
        if (getUpvoteScore() != review1.getUpvoteScore()) return false;
        if (!getSubmittedBy().equals(review1.getSubmittedBy())) return false;
        if (!getDateCreated().equals(review1.getDateCreated())) return false;
        if (!getLastModified().equals(review1.getLastModified())) return false;
        if (getReview() != null ? !getReview().equals(review1.getReview()) : review1.getReview() != null) return false;
        return getGear().equals(review1.getGear());
    }

    @Override
    public int hashCode() {
        int result = getId();
        result = 31 * result + getSubmittedBy().hashCode();
        result = 31 * result + getDateCreated().hashCode();
        result = 31 * result + getLastModified().hashCode();
        result = 31 * result + (getReview() != null ? getReview().hashCode() : 0);
        result = 31 * result + getUpvoteScore();
        result = 31 * result + getGear().hashCode();
        return result;
    }

    @Override
    public String toString() {
        return null;
    }
}
