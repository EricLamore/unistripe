package com.universign.universigncs.unistripe.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * This class reprensents a link between a billing customer\nand one of the customer's organization\n@author Eric Lamore
 */
@ApiModel(description = "This class reprensents a link between a billing customer\nand one of the customer's organization\n@author Eric Lamore")
@Entity
@Table(name = "customer_organization_link")
public class CustomerOrganizationLink implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * the organisation identifier
     */
    @ApiModelProperty(value = "the organisation identifier")
    @Column(name = "organisation_id")
    private String organisationId;

    /**
     * the organisation name
     */
    @ApiModelProperty(value = "the organisation name")
    @Column(name = "organisation_name")
    private String organisationName;

    /**
     * the organisation register date
     */
    @ApiModelProperty(value = "the organisation register date")
    @Column(name = "organisation_register_date")
    private LocalDate organisationRegisterDate;

    /**
     * the customer identifier
     */
    @ApiModelProperty(value = "the customer identifier")
    @Column(name = "customer_id")
    private String customerId;

    /**
     * the customer name
     */
    @ApiModelProperty(value = "the customer name")
    @Column(name = "customer_name")
    private String customerName;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrganisationId() {
        return organisationId;
    }

    public CustomerOrganizationLink organisationId(String organisationId) {
        this.organisationId = organisationId;
        return this;
    }

    public void setOrganisationId(String organisationId) {
        this.organisationId = organisationId;
    }

    public String getOrganisationName() {
        return organisationName;
    }

    public CustomerOrganizationLink organisationName(String organisationName) {
        this.organisationName = organisationName;
        return this;
    }

    public void setOrganisationName(String organisationName) {
        this.organisationName = organisationName;
    }

    public LocalDate getOrganisationRegisterDate() {
        return organisationRegisterDate;
    }

    public CustomerOrganizationLink organisationRegisterDate(LocalDate organisationRegisterDate) {
        this.organisationRegisterDate = organisationRegisterDate;
        return this;
    }

    public void setOrganisationRegisterDate(LocalDate organisationRegisterDate) {
        this.organisationRegisterDate = organisationRegisterDate;
    }

    public String getCustomerId() {
        return customerId;
    }

    public CustomerOrganizationLink customerId(String customerId) {
        this.customerId = customerId;
        return this;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public CustomerOrganizationLink customerName(String customerName) {
        this.customerName = customerName;
        return this;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CustomerOrganizationLink)) {
            return false;
        }
        return id != null && id.equals(((CustomerOrganizationLink) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "CustomerOrganizationLink{" +
            "id=" + getId() +
            ", organisationId='" + getOrganisationId() + "'" +
            ", organisationName='" + getOrganisationName() + "'" +
            ", organisationRegisterDate='" + getOrganisationRegisterDate() + "'" +
            ", customerId='" + getCustomerId() + "'" +
            ", customerName='" + getCustomerName() + "'" +
            "}";
    }
}
