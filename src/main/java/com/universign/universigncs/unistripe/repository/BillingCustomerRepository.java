package com.universign.universigncs.unistripe.repository;

import com.universign.universigncs.unistripe.domain.BillingCustomer;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the BillingCustomer entity.
 */
@SuppressWarnings("unused")
@Repository
public interface BillingCustomerRepository extends JpaRepository<BillingCustomer, Long> {
}
