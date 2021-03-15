package com.universign.universigncs.unistripe.domain;


import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import com.universign.universigncs.unistripe.domain.enumeration.TYPE;

/**
 * A UniproxyConso.
 */
@Entity
@Table(name = "uniproxy_conso")
public class UniproxyConso implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "subscription_id")
    private String subscriptionId;

    @Column(name = "org_id")
    private String orgId;

    @Column(name = "month")
    private Integer month;

    @Column(name = "year")
    private Integer year;

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private TYPE type;

    @Column(name = "conso")
    private Integer conso;

    @OneToMany(mappedBy = "uniproxyConso")
    private Set<SignatureDetails> signatureDetails = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSubscriptionId() {
        return subscriptionId;
    }

    public UniproxyConso subscriptionId(String subscriptionId) {
        this.subscriptionId = subscriptionId;
        return this;
    }

    public void setSubscriptionId(String subscriptionId) {
        this.subscriptionId = subscriptionId;
    }

    public String getOrgId() {
        return orgId;
    }

    public UniproxyConso orgId(String orgId) {
        this.orgId = orgId;
        return this;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public Integer getMonth() {
        return month;
    }

    public UniproxyConso month(Integer month) {
        this.month = month;
        return this;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public Integer getYear() {
        return year;
    }

    public UniproxyConso year(Integer year) {
        this.year = year;
        return this;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public TYPE getType() {
        return type;
    }

    public UniproxyConso type(TYPE type) {
        this.type = type;
        return this;
    }

    public void setType(TYPE type) {
        this.type = type;
    }

    public Integer getConso() {
        return conso;
    }

    public UniproxyConso conso(Integer conso) {
        this.conso = conso;
        return this;
    }

    public void setConso(Integer conso) {
        this.conso = conso;
    }

    public Set<SignatureDetails> getSignatureDetails() {
        return signatureDetails;
    }

    public UniproxyConso signatureDetails(Set<SignatureDetails> signatureDetails) {
        this.signatureDetails = signatureDetails;
        return this;
    }

    public UniproxyConso addSignatureDetails(SignatureDetails signatureDetails) {
        this.signatureDetails.add(signatureDetails);
        signatureDetails.setUniproxyConso(this);
        return this;
    }

    public UniproxyConso removeSignatureDetails(SignatureDetails signatureDetails) {
        this.signatureDetails.remove(signatureDetails);
        signatureDetails.setUniproxyConso(null);
        return this;
    }

    public void setSignatureDetails(Set<SignatureDetails> signatureDetails) {
        this.signatureDetails = signatureDetails;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof UniproxyConso)) {
            return false;
        }
        return id != null && id.equals(((UniproxyConso) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "UniproxyConso{" +
            "id=" + getId() +
            ", subscriptionId='" + getSubscriptionId() + "'" +
            ", orgId='" + getOrgId() + "'" +
            ", month=" + getMonth() +
            ", year=" + getYear() +
            ", type='" + getType() + "'" +
            ", conso=" + getConso() +
            "}";
    }
}
