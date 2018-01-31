package io.gearstack.models;

import javax.persistence.*;
import java.io.Serializable;

/**
 * GearStackComment
 *
 * Extends parent comment object by adding reference to a Gearstack instance
 */
@Entity(name = "GearstackComment")
@Table(name = "gear_stack_comment")
public class GearStackComment extends Comment implements Serializable {

    private static final long serialVersionUID = 1L;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(table = "gear_stack_comment", name = "gear_stack_id", referencedColumnName = "id",
                nullable = false, updatable = false)
    private GearStack gearStack;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(table = "gear_stack_comment", name = "reply_comment_id", referencedColumnName = "id")
    private GearStackComment replyComment;

    protected GearStackComment() { }

    public GearStack getGearStack() {
        return gearStack;
    }

    public void setGearStack(GearStack gearStack) {
        this.gearStack = gearStack;
    }

    public GearStackComment getReplyComment() { return replyComment; }

    public void setReplyComment(GearStackComment replyComment) {
        this.replyComment = replyComment;
    }

    @Override
    public boolean equals(Object o) {
        if (o != null && o instanceof GearStackComment) {
            GearStackComment compComment = (GearStackComment) o;

            if (!super.equals(compComment)) return false;
            if (!this.gearStack.equals(compComment.getGearStack())) return false;
            return this.getReplyComment() != null ? this.getReplyComment()
                    .equals(compComment.getReplyComment()) : compComment.getReplyComment() == null;
        }
        return false;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + getGearStack().hashCode();
        result = getReplyComment() != null ? 31 * result + getReplyComment().hashCode() :
                    result;
        return result;
    }

    @Override
    public String toString() {
        return new StringBuilder("GearStack Comment:\n")
                .append(super.toString()).append("\n")
                .append("Gear Stack: ").append(this.getGearStack().getId()).append("\n")
                .append("Parent comment: ").append(this.getReplyComment().getId())
                .toString();
    }
}
