package com.universign.universigncs.unistripe.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * This class represents a light\nbilling customer\n@author Eric Lamore
 */
@ApiModel(description = "This class represents a light\nbilling customer\n@author Eric Lamore")
@Entity
@Table(name = "billing_stripe_link")
public class BillingStripeLink implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "stripe_id")
    private String stripeId;

    @Column(name = "stripe_email")
    private String stripeEmail;

    @Column(name = "customer_id")
    private String customerId;

    /**
     * the customer identifier
     */
    @ApiModelProperty(value = "the customer identifier")
    @Column(name = "customer_name")
    private String customerName;

    /**
     * the name of the customer
     */
    @ApiModelProperty(value = "the name of the customer")
    @Column(name = "migrate_at")
    private LocalDate migrateAt;

    /**
     * migration date to stripe
     */
    @ApiModelProperty(value = "migration date to stripe")
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

    public BillingStripeLink stripeId(String stripeId) {
        this.stripeId = stripeId;
        return this;
    }

    public void setStripeId(String stripeId) {
        this.stripeId = stripeId;
    }

    public String getStripeEmail() {
        return stripeEmail;
    }

    public BillingStripeLink stripeEmail(String stripeEmail) {
        this.stripeEmail = stripeEmail;
        return this;
    }

    public void setStripeEmail(String stripeEmail) {
        this.stripeEmail = stripeEmail;
    }

    public String getCustomerId() {
        return customerId;
    }

    public BillingStripeLink customerId(String customerId) {
        this.customerId = customerId;
        return this;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public BillingStripeLink customerName(String customerName) {
        this.customerName = customerName;
        return this;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public LocalDate getMigrateAt() {
        return migrateAt;
    }

    public BillingStripeLink migrateAt(LocalDate migrateAt) {
        this.migrateAt = migrateAt;
        return this;
    }

    public void setMigrateAt(LocalDate migrateAt) {
        this.migrateAt = migrateAt;
    }

    public LocalDate getUpdatedAt() {
        return updatedAt;
    }

    public BillingStripeLink updatedAt(LocalDate updatedAt) {
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
        if (!(o instanceof BillingStripeLink)) {
            return false;
        }
        return id != null && id.equals(((BillingStripeLink) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "BillingStripeLink{" +
            "id=" + getId() +
            ", stripeId='" + getStripeId() + "'" +
            ", stripeEmail='" + getStripeEmail() + "'" +
            ", customerId='" + getCustomerId() + "'" +
            ", customerName='" + getCustomerName() + "'" +
            ", migrateAt='" + getMigrateAt() + "'" +
            ", updatedAt='" + getUpdatedAt() + "'" +
            "}";
    }
}
