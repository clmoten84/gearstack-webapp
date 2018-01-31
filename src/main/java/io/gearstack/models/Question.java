package io.gearstack.models;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Question
 *
 * Abstract super class modeling parent type Question. Can be
 * extended to add additional question types.
 */
@Entity(name = "Question")
@Table(name = "question")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Question implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, nullable = false, unique = true)
    private int id;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "submitted_by", nullable = false, referencedColumnName = "username")
    private User submittedBy;

    @Column(name = "date_created", nullable = false)
    private Timestamp dateCreated;

    @Column(name = "last_modified", nullable = false)
    private Timestamp lastModified;

    @Column(name = "question_title", nullable = false, length = 500)
    private String questionTitle;

    @Column(name = "question", nullable = false)
    private String question;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "accepted_answer", referencedColumnName = "id")
    private Answer acceptedAnswer;

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

    public String getQuestionTitle() {
        return questionTitle;
    }

    public void setQuestionTitle(String questionTitle) {
        this.questionTitle = questionTitle;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public Answer getAcceptedAnswer() {
        return acceptedAnswer;
    }

    public void setAcceptedAnswer(Answer answer) {
        this.acceptedAnswer = answer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Question question1 = (Question) o;

        if (getId() != question1.getId()) return false;
        if (!getSubmittedBy().equals(question1.getSubmittedBy())) return false;
        if (!getQuestionTitle().equals(question1.getQuestionTitle())) return false;
        return getQuestion().equals(question1.getQuestion());
    }

    @Override
    public int hashCode() {
        int result = getId();
        result = 31 * result + getSubmittedBy().hashCode();
        result = 31 * result + getQuestionTitle().hashCode();
        result = 31 * result + getQuestion().hashCode();
        return result;
    }

    @Override
    public String toString() {
        return new StringBuilder()
                .append("Id: ").append(this.getId()).append("\n")
                .append("Submitted by: ").append(this.getSubmittedBy().getUsername()).append("\n")
                .append("Date created: ").append(this.getDateCreated().toString()).append("\n")
                .append("Title: ").append(this.getQuestionTitle()).append("\n")
                .append("Question: ").append(this.getQuestion()).append("\n")
                .append("Accepted Answer: ").append(this.getAcceptedAnswer() != null ?
                    this.getAcceptedAnswer().getId() : null)
                .toString();
    }
}
