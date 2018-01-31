package io.gearstack.models;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Answer
 *
 * Models an answer to a question
 */
@Entity(name = "Answer")
@Table(name = "answer")
public class Answer implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(table = "answer", name = "id", nullable = false, updatable = false,
            unique = true)
    private int id;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(table = "answer", name = "submitted_by", referencedColumnName = "username",
                nullable = false)
    private User submittedBy;

    @Column(table = "answer", name = "date_created", nullable = false)
    private Timestamp dateCreated;

    @Column(table = "answer", name = "last_modified", nullable = false)
    private Timestamp lastModified;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(table = "answer", name = "question_id", referencedColumnName = "id",
                nullable = false, updatable = false)
    private Question question;

    @Column(table = "answer", name = "answer", nullable = false)
    private String answer;

    @Column(table = "answer", name = "upvote_score", nullable = false)
    private int upvoteScore;

    protected Answer() { }

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

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
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

        Answer answer = (Answer) o;

        if (getId() != answer.getId()) return false;
        if (!getSubmittedBy().equals(answer.getSubmittedBy())) return false;
        if (!getQuestion().equals(answer.getQuestion())) return false;
        return getAnswer().equals(answer.getAnswer());
    }

    @Override
    public int hashCode() {
        int result = getId();
        result = 31 * result + getSubmittedBy().hashCode();
        result = 31 * result + getQuestion().hashCode();
        result = 31 * result + getAnswer().hashCode();
        return result;
    }

    @Override
    public String toString() {
        return new StringBuilder("Answer:\n")
                .append("Id: ").append(this.getId()).append("\n")
                .append("Submitted by: ").append(this.getSubmittedBy().getUsername()).append("\n")
                .append("Date created: ").append(this.getDateCreated().toString()).append("\n")
                .append("Last modified: ").append(this.getLastModified().toString()).append("\n")
                .append("Question id: ").append(this.getQuestion().getId()).append("\n")
                .append("Answer: ").append(this.getAnswer()).append("\n")
                .append("Upvote score: ").append(this.getUpvoteScore())
                .toString();
    }
}
