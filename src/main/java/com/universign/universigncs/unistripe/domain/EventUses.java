package com.universign.universigncs.unistripe.domain;

import io.swagger.annotations.ApiModel;

import javax.persistence.*;

import java.io.Serializable;

import com.universign.universigncs.unistripe.domain.enumeration.StatusType;

/**
 * This class an event uses\n@author Eric Lamore
 */
@ApiModel(description = "This class an event uses\n@author Eric Lamore")
@Entity
@Table(name = "event_uses")
public class EventUses implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "cusotmer_id")
    private String cusotmerId;

    @Column(name = "customer_name")
    private String customerName;

    @Column(name = "subscription_id")
    private String subscriptionId;

    @Column(name = "subscription_name")
    private String subscriptionName;

    @Column(name = "product_id")
    private String productId;

    @Column(name = "product_name")
    private String productName;

    @Column(name = "month")
    private Integer month;

    @Column(name = "year")
    private Integer year;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private StatusType status;

    @Column(name = "error")
    private String error;

    @Column(name = "total")
    private Long total;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCusotmerId() {
        return cusotmerId;
    }

    public EventUses cusotmerId(String cusotmerId) {
        this.cusotmerId = cusotmerId;
        return this;
    }

    public void setCusotmerId(String cusotmerId) {
        this.cusotmerId = cusotmerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public EventUses customerName(String customerName) {
        this.customerName = customerName;
        return this;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getSubscriptionId() {
        return subscriptionId;
    }

    public EventUses subscriptionId(String subscriptionId) {
        this.subscriptionId = subscriptionId;
        return this;
    }

    public void setSubscriptionId(String subscriptionId) {
        this.subscriptionId = subscriptionId;
    }

    public String getSubscriptionName() {
        return subscriptionName;
    }

    public EventUses subscriptionName(String subscriptionName) {
        this.subscriptionName = subscriptionName;
        return this;
    }

    public void setSubscriptionName(String subscriptionName) {
        this.subscriptionName = subscriptionName;
    }

    public String getProductId() {
        return productId;
    }

    public EventUses productId(String productId) {
        this.productId = productId;
        return this;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public EventUses productName(String productName) {
        this.productName = productName;
        return this;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Integer getMonth() {
        return month;
    }

    public EventUses month(Integer month) {
        this.month = month;
        return this;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public Integer getYear() {
        return year;
    }

    public EventUses year(Integer year) {
        this.year = year;
        return this;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public StatusType getStatus() {
        return status;
    }

    public EventUses status(StatusType status) {
        this.status = status;
        return this;
    }

    public void setStatus(StatusType status) {
        this.status = status;
    }

    public String getError() {
        return error;
    }

    public EventUses error(String error) {
        this.error = error;
        return this;
    }

    public void setError(String error) {
        this.error = error;
    }

    public Long getTotal() {
        return total;
    }

    public EventUses total(Long total) {
        this.total = total;
        return this;
    }

    public void setTotal(Long total) {
        this.total = total;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof EventUses)) {
            return false;
        }
        return id != null && id.equals(((EventUses) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "EventUses{" +
            "id=" + getId() +
            ", cusotmerId='" + getCusotmerId() + "'" +
            ", customerName='" + getCustomerName() + "'" +
            ", subscriptionId='" + getSubscriptionId() + "'" +
            ", subscriptionName='" + getSubscriptionName() + "'" +
            ", productId='" + getProductId() + "'" +
            ", productName='" + getProductName() + "'" +
            ", month=" + getMonth() +
            ", year=" + getYear() +
            ", status='" + getStatus() + "'" +
            ", error='" + getError() + "'" +
            ", total=" + getTotal() +
            "}";
    }
}
