package com.universign.universigncs.unistripe.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * This class reprensents a link between a billing\nproduct rate plan and a stripe price\n@author Eric Lamore
 */
@ApiModel(description = "This class reprensents a link between a billing\nproduct rate plan and a stripe price\n@author Eric Lamore")
@Entity
@Table(name = "product_rate_plan_price_link")
public class ProductRatePlanPriceLink implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * the stripe price identifier
     */
    @ApiModelProperty(value = "the stripe price identifier")
    @Column(name = "stripe_id")
    private String stripeId;

    /**
     * the stripe price nick name
     */
    @ApiModelProperty(value = "the stripe price nick name")
    @Column(name = "stripe_nick_name")
    private String stripeNickName;

    /**
     * the billing product identifier
     */
    @ApiModelProperty(value = "the billing product identifier")
    @Column(name = "product_id")
    private String productId;

    /**
     * the billing product name
     */
    @ApiModelProperty(value = "the billing product name")
    @Column(name = "product_name")
    private String productName;

    /**
     * the billing product rate plan identifier
     */
    @ApiModelProperty(value = "the billing product rate plan identifier")
    @Column(name = "product_rate_plan_id")
    private String productRatePlanId;

    /**
     * the billing product rate plan name
     */
    @ApiModelProperty(value = "the billing product rate plan name")
    @Column(name = "product_rate_plan_name")
    private String productRatePlanName;

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

    public ProductRatePlanPriceLink stripeId(String stripeId) {
        this.stripeId = stripeId;
        return this;
    }

    public void setStripeId(String stripeId) {
        this.stripeId = stripeId;
    }

    public String getStripeNickName() {
        return stripeNickName;
    }

    public ProductRatePlanPriceLink stripeNickName(String stripeNickName) {
        this.stripeNickName = stripeNickName;
        return this;
    }

    public void setStripeNickName(String stripeNickName) {
        this.stripeNickName = stripeNickName;
    }

    public String getProductId() {
        return productId;
    }

    public ProductRatePlanPriceLink productId(String productId) {
        this.productId = productId;
        return this;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public ProductRatePlanPriceLink productName(String productName) {
        this.productName = productName;
        return this;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductRatePlanId() {
        return productRatePlanId;
    }

    public ProductRatePlanPriceLink productRatePlanId(String productRatePlanId) {
        this.productRatePlanId = productRatePlanId;
        return this;
    }

    public void setProductRatePlanId(String productRatePlanId) {
        this.productRatePlanId = productRatePlanId;
    }

    public String getProductRatePlanName() {
        return productRatePlanName;
    }

    public ProductRatePlanPriceLink productRatePlanName(String productRatePlanName) {
        this.productRatePlanName = productRatePlanName;
        return this;
    }

    public void setProductRatePlanName(String productRatePlanName) {
        this.productRatePlanName = productRatePlanName;
    }

    public LocalDate getMigrateAt() {
        return migrateAt;
    }

    public ProductRatePlanPriceLink migrateAt(LocalDate migrateAt) {
        this.migrateAt = migrateAt;
        return this;
    }

    public void setMigrateAt(LocalDate migrateAt) {
        this.migrateAt = migrateAt;
    }

    public LocalDate getUpdatedAt() {
        return updatedAt;
    }

    public ProductRatePlanPriceLink updatedAt(LocalDate updatedAt) {
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
        if (!(o instanceof ProductRatePlanPriceLink)) {
            return false;
        }
        return id != null && id.equals(((ProductRatePlanPriceLink) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ProductRatePlanPriceLink{" +
            "id=" + getId() +
            ", stripeId='" + getStripeId() + "'" +
            ", stripeNickName='" + getStripeNickName() + "'" +
            ", productId='" + getProductId() + "'" +
            ", productName='" + getProductName() + "'" +
            ", productRatePlanId='" + getProductRatePlanId() + "'" +
            ", productRatePlanName='" + getProductRatePlanName() + "'" +
            ", migrateAt='" + getMigrateAt() + "'" +
            ", updatedAt='" + getUpdatedAt() + "'" +
            "}";
    }
}
