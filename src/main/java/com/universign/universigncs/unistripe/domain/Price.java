package com.universign.universigncs.unistripe.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A Price.
 */
@Entity
@Table(name = "price")
public class Price implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * the Price identifier
     */
    @ApiModelProperty(value = "the Price identifier")
    @Column(name = "stripe_id")
    private String stripeId;

    /**
     * the Price nickname
     */
    @ApiModelProperty(value = "the Price nickname")
    @Column(name = "nickname")
    private String nickname;

    /**
     * the Price product identifier
     */
    @ApiModelProperty(value = "the Price product identifier")
    @Column(name = "product_id")
    private String productId;

    @OneToMany(mappedBy = "organization")
    private Set<Organization> organizations = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties(value = "prices", allowSetters = true)
    private StripeSubscription price;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStripeId() {
        return stripeId;
    }

    public Price stripeId(String stripeId) {
        this.stripeId = stripeId;
        return this;
    }

    public void setStripeId(String stripeId) {
        this.stripeId = stripeId;
    }

    public String getNickname() {
        return nickname;
    }

    public Price nickname(String nickname) {
        this.nickname = nickname;
        return this;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getProductId() {
        return productId;
    }

    public Price productId(String productId) {
        this.productId = productId;
        return this;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public Set<Organization> getOrganizations() {
        return organizations;
    }

    public Price organizations(Set<Organization> organizations) {
        this.organizations = organizations;
        return this;
    }

    public Price addOrganization(Organization organization) {
        this.organizations.add(organization);
        organization.setOrganization(this);
        return this;
    }

    public Price removeOrganization(Organization organization) {
        this.organizations.remove(organization);
        organization.setOrganization(null);
        return this;
    }

    public void setOrganizations(Set<Organization> organizations) {
        this.organizations = organizations;
    }

    public StripeSubscription getPrice() {
        return price;
    }

    public Price price(StripeSubscription stripeSubscription) {
        this.price = stripeSubscription;
        return this;
    }

    public void setPrice(StripeSubscription stripeSubscription) {
        this.price = stripeSubscription;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Price)) {
            return false;
        }
        return id != null && id.equals(((Price) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Price{" +
            "id=" + getId() +
            ", stripeId='" + getStripeId() + "'" +
            ", nickname='" + getNickname() + "'" +
            ", productId='" + getProductId() + "'" +
            "}";
    }
}
