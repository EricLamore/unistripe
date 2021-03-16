package com.universign.universigncs.unistripe.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

import java.io.Serializable;
import java.time.Instant;

/**
 * A SignatureDetails.
 */
@Entity
@Table(name = "signature_details")
public class SignatureDetails implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "siganture_date")
    private Instant sigantureDate;

    @Column(name = "siganture_count")
    private Integer sigantureCount;

    @ManyToOne
    @JsonIgnoreProperties(value = "signatureDetails", allowSetters = true)
    private UniproxyConso uniproxyConso;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public SignatureDetails firstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public SignatureDetails lastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Instant getSigantureDate() {
        return sigantureDate;
    }

    public SignatureDetails sigantureDate(Instant sigantureDate) {
        this.sigantureDate = sigantureDate;
        return this;
    }

    public void setSigantureDate(Instant sigantureDate) {
        this.sigantureDate = sigantureDate;
    }

    public Integer getSigantureCount() {
        return sigantureCount;
    }

    public SignatureDetails sigantureCount(Integer sigantureCount) {
        this.sigantureCount = sigantureCount;
        return this;
    }

    public void setSigantureCount(Integer sigantureCount) {
        this.sigantureCount = sigantureCount;
    }

    public UniproxyConso getUniproxyConso() {
        return uniproxyConso;
    }

    public SignatureDetails uniproxyConso(UniproxyConso uniproxyConso) {
        this.uniproxyConso = uniproxyConso;
        return this;
    }

    public void setUniproxyConso(UniproxyConso uniproxyConso) {
        this.uniproxyConso = uniproxyConso;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SignatureDetails)) {
            return false;
        }
        return id != null && id.equals(((SignatureDetails) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "SignatureDetails{" +
            "id=" + getId() +
            ", firstName='" + getFirstName() + "'" +
            ", lastName='" + getLastName() + "'" +
            ", sigantureDate='" + getSigantureDate() + "'" +
            ", sigantureCount=" + getSigantureCount() +
            "}";
    }
}
