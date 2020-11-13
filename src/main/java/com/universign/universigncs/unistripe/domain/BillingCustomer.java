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
@Table(name = "billing_customer")
public class BillingCustomer implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * the tax number of customer
     */
    @ApiModelProperty(value = "the tax number of customer")
    @Column(name = "tax_no")
    private String taxNo;

    /**
     * the third party accounting code of the customer
     */
    @ApiModelProperty(value = "the third party accounting code of the customer")
    @Column(name = "third_party_accounting_code")
    private String thirdPartyAccountingCode;

    /**
     * the siret of the customer
     */
    @ApiModelProperty(value = "the siret of the customer")
    @Column(name = "siret")
    private String siret;

    /**
     * the owner identifier of the customer
     */
    @ApiModelProperty(value = "the owner identifier of the customer")
    @Column(name = "owner_id")
    private String ownerId;

    /**
     * true if the customer is a particular, false otherwise
     */
    @ApiModelProperty(value = "true if the customer is a particular, false otherwise")
    @Column(name = "is_particular")
    private Boolean isParticular;

    /**
     * true if the customer is a partner, false otherwise
     */
    @ApiModelProperty(value = "true if the customer is a partner, false otherwise")
    @Column(name = "partner")
    private Boolean partner;

    /**
     * the partner identifier
     */
    @ApiModelProperty(value = "the partner identifier")
    @Column(name = "partner_id")
    private String partnerId;

    /**
     * the billing customer identifier
     */
    @ApiModelProperty(value = "the billing customer identifier")
    @Column(name = "customer_id")
    private String customerId;

    /**
     * the billing name of the customer
     */
    @ApiModelProperty(value = "the billing name of the customer")
    @Column(name = "customer_name")
    private String customerName;

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

    public String getTaxNo() {
        return taxNo;
    }

    public BillingCustomer taxNo(String taxNo) {
        this.taxNo = taxNo;
        return this;
    }

    public void setTaxNo(String taxNo) {
        this.taxNo = taxNo;
    }

    public String getThirdPartyAccountingCode() {
        return thirdPartyAccountingCode;
    }

    public BillingCustomer thirdPartyAccountingCode(String thirdPartyAccountingCode) {
        this.thirdPartyAccountingCode = thirdPartyAccountingCode;
        return this;
    }

    public void setThirdPartyAccountingCode(String thirdPartyAccountingCode) {
        this.thirdPartyAccountingCode = thirdPartyAccountingCode;
    }

    public String getSiret() {
        return siret;
    }

    public BillingCustomer siret(String siret) {
        this.siret = siret;
        return this;
    }

    public void setSiret(String siret) {
        this.siret = siret;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public BillingCustomer ownerId(String ownerId) {
        this.ownerId = ownerId;
        return this;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    public Boolean isIsParticular() {
        return isParticular;
    }

    public BillingCustomer isParticular(Boolean isParticular) {
        this.isParticular = isParticular;
        return this;
    }

    public void setIsParticular(Boolean isParticular) {
        this.isParticular = isParticular;
    }

    public Boolean isPartner() {
        return partner;
    }

    public BillingCustomer partner(Boolean partner) {
        this.partner = partner;
        return this;
    }

    public void setPartner(Boolean partner) {
        this.partner = partner;
    }

    public String getPartnerId() {
        return partnerId;
    }

    public BillingCustomer partnerId(String partnerId) {
        this.partnerId = partnerId;
        return this;
    }

    public void setPartnerId(String partnerId) {
        this.partnerId = partnerId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public BillingCustomer customerId(String customerId) {
        this.customerId = customerId;
        return this;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public BillingCustomer customerName(String customerName) {
        this.customerName = customerName;
        return this;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getStripeId() {
        return stripeId;
    }

    public BillingCustomer stripeId(String stripeId) {
        this.stripeId = stripeId;
        return this;
    }

    public void setStripeId(String stripeId) {
        this.stripeId = stripeId;
    }

    public String getStripeEmail() {
        return stripeEmail;
    }

    public BillingCustomer stripeEmail(String stripeEmail) {
        this.stripeEmail = stripeEmail;
        return this;
    }

    public void setStripeEmail(String stripeEmail) {
        this.stripeEmail = stripeEmail;
    }

    public LocalDate getMigrateAt() {
        return migrateAt;
    }

    public BillingCustomer migrateAt(LocalDate migrateAt) {
        this.migrateAt = migrateAt;
        return this;
    }

    public void setMigrateAt(LocalDate migrateAt) {
        this.migrateAt = migrateAt;
    }

    public LocalDate getUpdatedAt() {
        return updatedAt;
    }

    public BillingCustomer updatedAt(LocalDate updatedAt) {
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
        if (!(o instanceof BillingCustomer)) {
            return false;
        }
        return id != null && id.equals(((BillingCustomer) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "BillingCustomer{" +
            "id=" + getId() +
            ", taxNo='" + getTaxNo() + "'" +
            ", thirdPartyAccountingCode='" + getThirdPartyAccountingCode() + "'" +
            ", siret='" + getSiret() + "'" +
            ", ownerId='" + getOwnerId() + "'" +
            ", isParticular='" + isIsParticular() + "'" +
            ", partner='" + isPartner() + "'" +
            ", partnerId='" + getPartnerId() + "'" +
            ", customerId='" + getCustomerId() + "'" +
            ", customerName='" + getCustomerName() + "'" +
            ", stripeId='" + getStripeId() + "'" +
            ", stripeEmail='" + getStripeEmail() + "'" +
            ", migrateAt='" + getMigrateAt() + "'" +
            ", updatedAt='" + getUpdatedAt() + "'" +
            "}";
    }
}
