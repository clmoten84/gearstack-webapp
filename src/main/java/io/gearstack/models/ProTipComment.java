package io.gearstack.models;

import javax.persistence.*;
import java.io.Serializable;

/**
 * ProTipComment
 *
 * Extends parent comment object by adding reference to ProTip instance
 */
@Entity(name = "ProTipComment")
@Table(name = "pro_tip_comment")
public class ProTipComment extends Comment implements Serializable {

    private static final long serialVersionUID = 1L;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(table = "pro_tip_comment", name = "pro_tip_id", referencedColumnName = "id",
                nullable = false, updatable = false)
    private ProTip proTip;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(table = "pro_tip_comment", name = "reply_comment_id", referencedColumnName = "id")
    private ProTipComment replyComment;

    protected ProTipComment() { }

    public ProTip getProTip() {
        return proTip;
    }

    public void setProTip(ProTip proTip) {
        this.proTip = proTip;
    }

    public ProTipComment getReplyComment() { return replyComment; }

    public void setReplyComment(ProTipComment replyComment) { this.replyComment = replyComment; }

    @Override
    public boolean equals(Object o) {
        if (o != null && o instanceof ProTipComment) {
            ProTipComment compComment = (ProTipComment) o;

            if (!super.equals(compComment)) return false;
            if (!this.getProTip().equals(compComment.getProTip())) return false;
            return this.getProTip() != null ? this.getProTip()
                    .equals(compComment.getProTip()) : compComment.getReplyComment() == null;
        }
        return false;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + getProTip().hashCode();
        result = getReplyComment() != null ? 31 * result + getReplyComment().hashCode() :
                    result;
        return result;
    }

    @Override
    public String toString() {
        return new StringBuilder("Pro Tip Comment:\n")
                .append(super.toString()).append("\n")
                .append("Pro Tip: ").append(this.getProTip().getId()).append("\n")
                .append("Parent comment: ").append(this.getReplyComment().getId())
                .toString();
    }
}
