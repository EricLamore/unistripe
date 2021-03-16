package com.universign.universigncs.unistripe.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

/**
 * This class represents a customer link beetween\nbilling and stripe\n@author Eric Lamore
 */
@ApiModel(description = "This class represents a customer link beetween\nbilling and stripe\n@author Eric Lamore")
@Entity
@Table(name = "stripe_subscription")
public class StripeSubscription implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * the stripe customer identifier
     */
    @ApiModelProperty(value = "the stripe customer identifier")
    @Column(name = "stripe_id")
    private String stripeId;

    /**
     * the stripe email of the customer
     */
    @ApiModelProperty(value = "the stripe email of the customer")
    @Column(name = "stripe_email")
    private String stripeEmail;

    /**
     * the taxeRate for this customer
     */
    @ApiModelProperty(value = "the taxeRate for this customer")
    @Column(name = "taxe_rate")
    private Long taxeRate;

    /**
     * migration Date
     */
    @ApiModelProperty(value = "migration Date")
    @Column(name = "migrate_at")
    private LocalDate migrateAt;

    /**
     * migration update Date
     */
    @ApiModelProperty(value = "migration update Date")
    @Column(name = "updated_at")
    private LocalDate updatedAt;

    @OneToMany(mappedBy = "price")
    private Set<Price> prices = new HashSet<>();

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

    public StripeSubscription stripeId(String stripeId) {
        this.stripeId = stripeId;
        return this;
    }

    public void setStripeId(String stripeId) {
        this.stripeId = stripeId;
    }

    public String getStripeEmail() {
        return stripeEmail;
    }

    public StripeSubscription stripeEmail(String stripeEmail) {
        this.stripeEmail = stripeEmail;
        return this;
    }

    public void setStripeEmail(String stripeEmail) {
        this.stripeEmail = stripeEmail;
    }

    public Long getTaxeRate() {
        return taxeRate;
    }

    public StripeSubscription taxeRate(Long taxeRate) {
        this.taxeRate = taxeRate;
        return this;
    }

    public void setTaxeRate(Long taxeRate) {
        this.taxeRate = taxeRate;
    }

    public LocalDate getMigrateAt() {
        return migrateAt;
    }

    public StripeSubscription migrateAt(LocalDate migrateAt) {
        this.migrateAt = migrateAt;
        return this;
    }

    public void setMigrateAt(LocalDate migrateAt) {
        this.migrateAt = migrateAt;
    }

    public LocalDate getUpdatedAt() {
        return updatedAt;
    }

    public StripeSubscription updatedAt(LocalDate updatedAt) {
        this.updatedAt = updatedAt;
        return this;
    }

    public void setUpdatedAt(LocalDate updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Set<Price> getPrices() {
        return prices;
    }

    public StripeSubscription prices(Set<Price> prices) {
        this.prices = prices;
        return this;
    }

    public StripeSubscription addPrice(Price price) {
        this.prices.add(price);
        price.setPrice(this);
        return this;
    }

    public StripeSubscription removePrice(Price price) {
        this.prices.remove(price);
        price.setPrice(null);
        return this;
    }

    public void setPrices(Set<Price> prices) {
        this.prices = prices;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof StripeSubscription)) {
            return false;
        }
        return id != null && id.equals(((StripeSubscription) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "StripeSubscription{" +
            "id=" + getId() +
            ", stripeId='" + getStripeId() + "'" +
            ", stripeEmail='" + getStripeEmail() + "'" +
            ", taxeRate=" + getTaxeRate() +
            ", migrateAt='" + getMigrateAt() + "'" +
            ", updatedAt='" + getUpdatedAt() + "'" +
            "}";
    }
}
