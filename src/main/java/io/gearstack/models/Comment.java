package io.gearstack.models;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Comment
 *
 * Abstract super class that models parent type comment
 */
@MappedSuperclass
public abstract class Comment implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, nullable = false, unique = true)
    private int id;

    @Column(name = "date_created", nullable = false)
    private Timestamp dateCreated;

    @Column(name = "last_modified", nullable = false)
    private Timestamp lastModified;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "submitted_by", nullable = false, referencedColumnName = "username")
    private User submittedBy;

    @Column(name = "comment", nullable = false)
    private String comment;

    @Column(name = "upvote_score", nullable = false)
    private int upvoteScore;

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

    public User getSubmittedBy() {
        return submittedBy;
    }

    public void setSubmittedBy(User submittedBy) {
        this.submittedBy = submittedBy;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getUpvoteScore() {
        return upvoteScore;
    }

    public void setUpvoteScore(int upvoteScore) {
        this.upvoteScore = upvoteScore;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Comment comment1 = (Comment) o;

        if (getUpvoteScore() != comment1.getUpvoteScore()) return false;
        if (!getDateCreated().equals(comment1.getDateCreated())) return false;
        if (!getLastModified().equals(comment1.getLastModified())) return false;
        if (!getSubmittedBy().equals(comment1.getSubmittedBy())) return false;
        return getComment().equals(comment1.getComment());
    }

    @Override
    public int hashCode() {
        int result = getId();
        result = 31 * result + getDateCreated().hashCode();
        result = 31 * result + getLastModified().hashCode();
        result = 31 * result + getSubmittedBy().hashCode();
        result = 31 * result + getComment().hashCode();
        result = 31 * result + getUpvoteScore();
        return result;
    }

    @Override
    public String toString() {
        return new StringBuilder()
                .append("Id: ").append(this.getId()).append("\n")
                .append("Date Created: ").append(this.getDateCreated().toString()).append("\n")
                .append("Last Modified: ").append(this.getLastModified().toString()).append("\n")
                .append("Submitted By: ").append(this.getSubmittedBy().getUsername()).append("\n")
                .append("Comment: ").append(this.getComment()).append("\n")
                .append("Upvote Score: ").append(this.getUpvoteScore())
                .toString();
    }
}
