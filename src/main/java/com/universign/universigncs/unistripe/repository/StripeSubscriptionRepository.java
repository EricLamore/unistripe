package com.universign.universigncs.unistripe.repository;

import com.universign.universigncs.unistripe.domain.StripeSubscription;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the StripeSubscription entity.
 */
@SuppressWarnings("unused")
@Repository
public interface StripeSubscriptionRepository extends JpaRepository<StripeSubscription, Long> {
}
