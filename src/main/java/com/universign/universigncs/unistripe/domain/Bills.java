package com.universign.universigncs.unistripe.domain;


import javax.persistence.*;

import java.io.Serializable;
import java.time.Instant;

/**
 * A Bills.
 */
@Entity
@Table(name = "bills")
public class Bills implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "customer_id")
    private String customerId;

    @Column(name = "customer_name")
    private String customerName;

    @Column(name = "customer_email")
    private String customerEmail;

    @Column(name = "year")
    private Integer year;

    @Column(name = "month")
    private Integer month;

    @Column(name = "period_start")
    private Instant periodStart;

    @Column(name = "period_end")
    private Instant periodEnd;

    @Column(name = "created")
    private Instant created;

    @Column(name = "send")
    private Instant send;

    @Column(name = "due_date")
    private Instant dueDate;

    @Column(name = "amount_due")
    private Integer amountDue;

    @Column(name = "total")
    private Integer total;

    @Column(name = "tax")
    private Integer tax;

    @Column(name = "total_discount_amounts")
    private Integer totalDiscountAmounts;

    @Column(name = "total_tax_amounts")
    private Integer totalTaxAmounts;

    @Column(name = "url")
    private String url;

    @Column(name = "pdf_url")
    private String pdfUrl;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCustomerId() {
        return customerId;
    }

    public Bills customerId(String customerId) {
        this.customerId = customerId;
        return this;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public Bills customerName(String customerName) {
        this.customerName = customerName;
        return this;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public Bills customerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
        return this;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public Integer getYear() {
        return year;
    }

    public Bills year(Integer year) {
        this.year = year;
        return this;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getMonth() {
        return month;
    }

    public Bills month(Integer month) {
        this.month = month;
        return this;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public Instant getPeriodStart() {
        return periodStart;
    }

    public Bills periodStart(Instant periodStart) {
        this.periodStart = periodStart;
        return this;
    }

    public void setPeriodStart(Instant periodStart) {
        this.periodStart = periodStart;
    }

    public Instant getPeriodEnd() {
        return periodEnd;
    }

    public Bills periodEnd(Instant periodEnd) {
        this.periodEnd = periodEnd;
        return this;
    }

    public void setPeriodEnd(Instant periodEnd) {
        this.periodEnd = periodEnd;
    }

    public Instant getCreated() {
        return created;
    }

    public Bills created(Instant created) {
        this.created = created;
        return this;
    }

    public void setCreated(Instant created) {
        this.created = created;
    }

    public Instant getSend() {
        return send;
    }

    public Bills send(Instant send) {
        this.send = send;
        return this;
    }

    public void setSend(Instant send) {
        this.send = send;
    }

    public Instant getDueDate() {
        return dueDate;
    }

    public Bills dueDate(Instant dueDate) {
        this.dueDate = dueDate;
        return this;
    }

    public void setDueDate(Instant dueDate) {
        this.dueDate = dueDate;
    }

    public Integer getAmountDue() {
        return amountDue;
    }

    public Bills amountDue(Integer amountDue) {
        this.amountDue = amountDue;
        return this;
    }

    public void setAmountDue(Integer amountDue) {
        this.amountDue = amountDue;
    }

    public Integer getTotal() {
        return total;
    }

    public Bills total(Integer total) {
        this.total = total;
        return this;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getTax() {
        return tax;
    }

    public Bills tax(Integer tax) {
        this.tax = tax;
        return this;
    }

    public void setTax(Integer tax) {
        this.tax = tax;
    }

    public Integer getTotalDiscountAmounts() {
        return totalDiscountAmounts;
    }

    public Bills totalDiscountAmounts(Integer totalDiscountAmounts) {
        this.totalDiscountAmounts = totalDiscountAmounts;
        return this;
    }

    public void setTotalDiscountAmounts(Integer totalDiscountAmounts) {
        this.totalDiscountAmounts = totalDiscountAmounts;
    }

    public Integer getTotalTaxAmounts() {
        return totalTaxAmounts;
    }

    public Bills totalTaxAmounts(Integer totalTaxAmounts) {
        this.totalTaxAmounts = totalTaxAmounts;
        return this;
    }

    public void setTotalTaxAmounts(Integer totalTaxAmounts) {
        this.totalTaxAmounts = totalTaxAmounts;
    }

    public String getUrl() {
        return url;
    }

    public Bills url(String url) {
        this.url = url;
        return this;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPdfUrl() {
        return pdfUrl;
    }

    public Bills pdfUrl(String pdfUrl) {
        this.pdfUrl = pdfUrl;
        return this;
    }

    public void setPdfUrl(String pdfUrl) {
        this.pdfUrl = pdfUrl;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Bills)) {
            return false;
        }
        return id != null && id.equals(((Bills) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Bills{" +
            "id=" + getId() +
            ", customerId='" + getCustomerId() + "'" +
            ", customerName='" + getCustomerName() + "'" +
            ", customerEmail='" + getCustomerEmail() + "'" +
            ", year=" + getYear() +
            ", month=" + getMonth() +
            ", periodStart='" + getPeriodStart() + "'" +
            ", periodEnd='" + getPeriodEnd() + "'" +
            ", created='" + getCreated() + "'" +
            ", send='" + getSend() + "'" +
            ", dueDate='" + getDueDate() + "'" +
            ", amountDue=" + getAmountDue() +
            ", total=" + getTotal() +
            ", tax=" + getTax() +
            ", totalDiscountAmounts=" + getTotalDiscountAmounts() +
            ", totalTaxAmounts=" + getTotalTaxAmounts() +
            ", url='" + getUrl() + "'" +
            ", pdfUrl='" + getPdfUrl() + "'" +
            "}";
    }
}
