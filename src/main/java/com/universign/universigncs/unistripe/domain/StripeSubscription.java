package com.universign.universigncs.unistripe.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;

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
     * the subscription identifier
     */
    @ApiModelProperty(value = "the subscription identifier")
    @Column(name = "subscription_id")
    private String subscriptionId;

    /**
     * migration date to stripe
     */
    @ApiModelProperty(value = "migration date to stripe")
    @Column(name = "migrate_at")
    private LocalDate migrateAt;

    /**
     * migration update Date
     */
    @ApiModelProperty(value = "migration update Date")
    @Column(name = "updated_at")
    private LocalDate updatedAt;

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

    public String getSubscriptionId() {
        return subscriptionId;
    }

    public StripeSubscription subscriptionId(String subscriptionId) {
        this.subscriptionId = subscriptionId;
        return this;
    }

    public void setSubscriptionId(String subscriptionId) {
        this.subscriptionId = subscriptionId;
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
            ", subscriptionId='" + getSubscriptionId() + "'" +
            ", migrateAt='" + getMigrateAt() + "'" +
            ", updatedAt='" + getUpdatedAt() + "'" +
            "}";
    }
}
