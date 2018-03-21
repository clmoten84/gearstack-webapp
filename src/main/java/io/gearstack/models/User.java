package io.gearstack.models;

import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

/**
 * User
 *
 * Models a user in the application. This entity is cached in
 * Hibernate second level cache by username natural id.
 */
@Entity(name = "User")
@Table(name = "user")
@Cacheable @org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE,
            region = "gearstack.user.entities")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(table = "user", name = "id", updatable = false, nullable = false, unique = true)
    private int id;

    @Column(table = "user", name = "username", unique = true, nullable = false, length = 50)
    @NaturalId(mutable = true)
    private String username;

    @Column(table = "user", name = "password", nullable = false)
    private String password;

    @Column(table = "user", name = "email", nullable = false, length = 50)
    @NaturalId(mutable = true)
    private String email;

    @Column(table = "user", name = "date_created", nullable = false)
    private Timestamp dateCreated;

    @Column(table = "user", name = "last_modified", nullable = false)
    private Timestamp lastModified;

    @Column(table = "user", name = "gear_iq_score", nullable = false)
    private int iq_score;

    @Column(table = "user", name = "bio")
    private String bio;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL,
              fetch = FetchType.LAZY, orphanRemoval = true)
    @JoinColumn(table = "user", name = "current_stack", referencedColumnName = "id", unique = true)
    private GearStack currentStack;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL,
              fetch = FetchType.LAZY, orphanRemoval = true)
    @JoinColumn(table = "user", name = "old_stack", referencedColumnName = "id", unique = true)
    private GearStack oldStack;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL,
              fetch = FetchType.LAZY, orphanRemoval = true)
    @JoinColumn(table = "user", name = "dream_stack", referencedColumnName = "id", unique = true)
    private GearStack dreamStack;

    @Column(table = "user", name = "avatar_pic")
    private String avatarPic;

    @Column(table = "user", name = "is_soundclound_auth", nullable = false)
    private boolean isSoundCloudAuthenticated;

    protected User() {
    }

    protected User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.dateCreated = new Timestamp(new Date().getTime());
        this.lastModified = new Timestamp(new Date().getTime());
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public int getIq_score() {
        return iq_score;
    }

    public void setIq_score(int iq_score) {
        this.iq_score = iq_score;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public GearStack getCurrentStack() {
        return currentStack;
    }

    public void setCurrentStack(GearStack currentStack) {
        this.currentStack = currentStack;
    }

    public GearStack getOldStack() {
        return oldStack;
    }

    public void setOldStack(GearStack oldStack) {
        this.oldStack = oldStack;
    }

    public GearStack getDreamStack() {
        return dreamStack;
    }

    public void setDreamStack(GearStack dreamStack) {
        this.dreamStack = dreamStack;
    }

    public String getAvatarPic() {
        return avatarPic;
    }

    public void setAvatarPic(String avatarPic) {
        this.avatarPic = avatarPic;
    }

    public boolean isSoundCloudAuthenticated() {
        return isSoundCloudAuthenticated;
    }

    public void setSoundCloudAuthenticated(boolean soundCloudAuthenticated) {
        isSoundCloudAuthenticated = soundCloudAuthenticated;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (!getUsername().equals(user.getUsername())) return false;
        return getEmail().equals(user.getEmail());
    }

    @Override
    public int hashCode() {
        int result = getUsername().hashCode();
        result = 31 * result + getEmail().hashCode();
        return result;
    }

    @Override
    public String toString() {
        return new StringBuilder("User:\n")
                .append("Id: ").append(this.getId()).append("\n")
                .append("Username: ").append(this.getUsername()).append("\n")
                .append("Email: ").append(this.getEmail()).append("\n")
                .append("Date Created: ").append(this.getDateCreated().toString()).append("\n")
                .append("IQ Score: ").append(this.getIq_score())
                .append("Current GearStack id: ").append(this.getCurrentStack().getId()).append("\n")
                .append("Old GearStack id: ").append(this.getOldStack().getId()).append("\n")
                .append("Dream GearStack id: ").append(this.getDreamStack().getId())
                .toString();
    }
}
