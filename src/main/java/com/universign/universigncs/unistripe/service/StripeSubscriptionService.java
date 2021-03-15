package com.universign.universigncs.unistripe.service;

import com.universign.universigncs.unistripe.domain.StripeSubscription;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link StripeSubscription}.
 */
public interface StripeSubscriptionService {

    /**
     * Save a stripeSubscription.
     *
     * @param stripeSubscription the entity to save.
     * @return the persisted entity.
     */
    StripeSubscription save(StripeSubscription stripeSubscription);

    /**
     * Get all the stripeSubscriptions.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<StripeSubscription> findAll(Pageable pageable);


    /**
     * Get the "id" stripeSubscription.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<StripeSubscription> findOne(Long id);

    /**
     * Delete the "id" stripeSubscription.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
