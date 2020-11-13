package com.universign.universigncs.unistripe.repository;

import com.universign.universigncs.unistripe.domain.BillingStripeLink;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the BillingStripeLink entity.
 */
@SuppressWarnings("unused")
@Repository
public interface BillingStripeLinkRepository extends JpaRepository<BillingStripeLink, Long> {
}
