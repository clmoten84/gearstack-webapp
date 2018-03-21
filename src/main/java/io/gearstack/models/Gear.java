package io.gearstack.models;

import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Gear
 *
 * Models a piece of gear in the application. This entity is cached
 * in Hibernate second level cache with some pretty interesting caching
 * settings. See gearEntityCache config...
 */
@Entity(name = "Gear")
@Table(name = "gear")
@Cacheable @org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE,
            region = "gearstack.gear.entities")
public class Gear implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(table = "gear", name = "id", updatable = false, nullable = false, unique = true)
    private int id;

    @Column(table = "gear", name = "name", nullable = false, unique = true)
    @NaturalId(mutable = true)
    private String name;

    @Column(table = "gear", name = "description")
    private String description;

    @OneToOne(optional = false)
    @JoinColumn(table = "gear", name = "gear_type", referencedColumnName = "amazon_node_id",
                nullable = false, updatable = false)
    private GearType gearType;

    @Column(table = "gear", name = "thumb_url")
    private String thumbUrl;

    @Column(table = "gear", name = "amazon_link")
    private String amazonLink;

    @Column(table = "gear", name = "image_url")
    private String imageUrl;

    @org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE,
        region = "gearstack.gear.entities.stackCollection")
    @ManyToMany(mappedBy = "gear", fetch = FetchType.LAZY)
    private Set<GearStack> gearStacks = new HashSet<>();

    @Column(table = "gear", name = "manufacturer", nullable = false)
    private String manufacturer;

    @Column(table = "gear", name = "features")
    private String features;

    protected Gear() { }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public GearType getGearType() {
        return gearType;
    }

    public void setGearType(GearType gearType) {
        this.gearType = gearType;
    }

    public String getThumbUrl() {
        return thumbUrl;
    }

    public void setThumbUrl(String thumbUrl) {
        this.thumbUrl = thumbUrl;
    }

    public String getAmazonLink() {
        return amazonLink;
    }

    public void setAmazonLink(String amazonLink) {
        this.amazonLink = amazonLink;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Set<GearStack> getGearStacks() { return this.gearStacks; }

    public void setGearStacks(Set<GearStack> gearStacks) { this.gearStacks = gearStacks; }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getFeatures() { return features; }

    public void setFeatures(String features) { this.features = features; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Gear gear = (Gear) o;

        if (!getName().equals(gear.getName())) return false;
        if (!getManufacturer().equals(gear.getManufacturer())) return false;
        return getGearType().equals(gear.getGearType());
    }

    @Override
    public int hashCode() {
        int result = getName().hashCode();
        result = 31 * result + getManufacturer().hashCode();
        result = 31 * result + getGearType().hashCode();
        return result;
    }

    @Override
    public String toString() {
        return new StringBuilder("Gear Instance:\n")
                .append("Id: ").append(this.getId()).append("\n")
                .append("Name: ").append(this.getName()).append("\n")
                .append("Manufacturer: ").append(this.getManufacturer()).append("\n")
                .append("Type: ").append(this.getGearType().getName()).append("\n")
                .append("Amazon Link: ").append(this.getAmazonLink()).append("\n")
                .append("Thumb URL: ").append(this.getThumbUrl()).append("\n")
                .append("Image URL: ").append(this.getImageUrl()).append("\n")
                .toString();
    }
}
