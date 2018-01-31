package io.gearstack.models;

import javax.persistence.*;
import java.io.Serializable;

/**
 * ReviewComment
 *
 * Extends parent comment object by adding reference to Review instance
 */
@Entity(name = "ReviewComment")
@Table(name = "review_comment")
public class ReviewComment extends Comment implements Serializable {

    private static final long serialVersionUID = 1L;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(table = "review_comment", name = "review_id",  referencedColumnName = "id",
                nullable = false, updatable = false)
    private Review review;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(table = "review_comment", name = "reply_comment_id", referencedColumnName = "id")
    private ReviewComment replyComment;

    protected ReviewComment() { }

    public Review getReview() {
        return review;
    }

    public void setReview(Review review) {
        this.review = review;
    }

    public ReviewComment getReplyComment() { return replyComment; }

    public void setReplyComment(ReviewComment replyComment) { this.replyComment = replyComment; }

    @Override
    public boolean equals(Object o) {
        if (o != null && o instanceof ReviewComment) {
            ReviewComment compComment = (ReviewComment) o;

            if (!super.equals(compComment)) return false;
            if (!this.getReview().equals(compComment.getReview())) return false;
            return this.getReplyComment() != null ? this.getReplyComment()
                    .equals(compComment.getReplyComment()) : compComment.getReplyComment() == null;
        }
        return false;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + getReview().hashCode();
        result = getReplyComment() != null ? 31 * result + getReplyComment().hashCode() :
                    result;
        return result;
    }

    @Override
    public String toString() {
        return new StringBuilder("Review Comment:\n")
                .append(super.toString()).append("\n")
                .append("Review: ").append(this.getReview().getId()).append("\n")
                .append("Parent comment: ").append(this.getReplyComment().getId())
                .toString();
    }
}
