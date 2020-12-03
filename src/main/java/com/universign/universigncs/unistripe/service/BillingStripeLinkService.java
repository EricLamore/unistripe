package com.universign.universigncs.unistripe.service;

import com.universign.universigncs.unistripe.domain.BillingStripeLink;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link BillingStripeLink}.
 */
public interface BillingStripeLinkService {

    /**
     * Save a billingStripeLink.
     *
     * @param billingStripeLink the entity to save.
     * @return the persisted entity.
     */
    BillingStripeLink save(BillingStripeLink billingStripeLink);

    /**
     * Get all the billingStripeLinks.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<BillingStripeLink> findAll(Pageable pageable);


    /**
     * Get the "id" billingStripeLink.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<BillingStripeLink> findOne(Long id);

    /**
     * Delete the "id" billingStripeLink.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
