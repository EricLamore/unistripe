package com.universign.universigncs.unistripe.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * A Organization.
 */
@Entity
@Table(name = "organization")
public class Organization implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * the organisation identifier
     */
    @ApiModelProperty(value = "the organisation identifier")
    @Column(name = "organization_id")
    private String organizationId;

    /**
     * the organisation address
     */
    @ApiModelProperty(value = "the organisation address")
    @Column(name = "address")
    private String address;

    /**
     * the organisation city
     */
    @ApiModelProperty(value = "the organisation city")
    @Column(name = "city")
    private String city;

    /**
     * the organisation country
     */
    @ApiModelProperty(value = "the organisation country")
    @Column(name = "country")
    private String country;

    /**
     * the organisation name
     */
    @ApiModelProperty(value = "the organisation name")
    @Column(name = "name")
    private String name;

    /**
     * the organisation register date
     */
    @ApiModelProperty(value = "the organisation register date")
    @Column(name = "register_date")
    private LocalDate registerDate;

    /**
     * the organisation status
     */
    @ApiModelProperty(value = "the organisation status")
    @Column(name = "status")
    private Integer status;

    /**
     * the organisation zipCode
     */
    @ApiModelProperty(value = "the organisation zipCode")
    @Column(name = "zip_code")
    private String zipCode;

    /**
     * the organisation individual
     */
    @ApiModelProperty(value = "the organisation individual")
    @Column(name = "individual")
    private Boolean individual;

    @ManyToOne
    @JsonIgnoreProperties(value = "organizations", allowSetters = true)
    private Price price;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrganizationId() {
        return organizationId;
    }

    public Organization organizationId(String organizationId) {
        this.organizationId = organizationId;
        return this;
    }

    public void setOrganizationId(String organizationId) {
        this.organizationId = organizationId;
    }

    public String getAddress() {
        return address;
    }

    public Organization address(String address) {
        this.address = address;
        return this;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public Organization city(String city) {
        this.city = city;
        return this;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public Organization country(String country) {
        this.country = country;
        return this;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getName() {
        return name;
    }

    public Organization name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getRegisterDate() {
        return registerDate;
    }

    public Organization registerDate(LocalDate registerDate) {
        this.registerDate = registerDate;
        return this;
    }

    public void setRegisterDate(LocalDate registerDate) {
        this.registerDate = registerDate;
    }

    public Integer getStatus() {
        return status;
    }

    public Organization status(Integer status) {
        this.status = status;
        return this;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getZipCode() {
        return zipCode;
    }

    public Organization zipCode(String zipCode) {
        this.zipCode = zipCode;
        return this;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public Boolean isIndividual() {
        return individual;
    }

    public Organization individual(Boolean individual) {
        this.individual = individual;
        return this;
    }

    public void setIndividual(Boolean individual) {
        this.individual = individual;
    }

    public Price getPrice() {
        return price;
    }

    public Organization price(Price price) {
        this.price = price;
        return this;
    }

    public void setPrice(Price price) {
        this.price = price;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Organization)) {
            return false;
        }
        return id != null && id.equals(((Organization) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Organization{" +
            "id=" + getId() +
            ", organizationId='" + getOrganizationId() + "'" +
            ", address='" + getAddress() + "'" +
            ", city='" + getCity() + "'" +
            ", country='" + getCountry() + "'" +
            ", name='" + getName() + "'" +
            ", registerDate='" + getRegisterDate() + "'" +
            ", status=" + getStatus() +
            ", zipCode='" + getZipCode() + "'" +
            ", individual='" + isIndividual() + "'" +
            "}";
    }
}
