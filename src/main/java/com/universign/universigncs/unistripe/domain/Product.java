package com.universign.universigncs.unistripe.domain;

import io.swagger.annotations.ApiModel;

import javax.persistence.*;

import java.io.Serializable;

/**
 * This class a product\n@author Eric Lamore
 */
@ApiModel(description = "This class a product\n@author Eric Lamore")
@Entity
@Table(name = "product")
public class Product implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "product_name")
    private String productName;

    @Column(name = "product_id")
    private String productId;

    @Column(name = "calculator")
    private String calculator;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public Product productName(String productName) {
        this.productName = productName;
        return this;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductId() {
        return productId;
    }

    public Product productId(String productId) {
        this.productId = productId;
        return this;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getCalculator() {
        return calculator;
    }

    public Product calculator(String calculator) {
        this.calculator = calculator;
        return this;
    }

    public void setCalculator(String calculator) {
        this.calculator = calculator;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Product)) {
            return false;
        }
        return id != null && id.equals(((Product) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Product{" +
            "id=" + getId() +
            ", productName='" + getProductName() + "'" +
            ", productId='" + getProductId() + "'" +
            ", calculator='" + getCalculator() + "'" +
            "}";
    }
}
