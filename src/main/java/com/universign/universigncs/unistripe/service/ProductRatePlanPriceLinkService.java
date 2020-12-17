package com.universign.universigncs.unistripe.service;

import com.universign.universigncs.unistripe.domain.ProductRatePlanPriceLink;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link ProductRatePlanPriceLink}.
 */
public interface ProductRatePlanPriceLinkService {

    /**
     * Save a productRatePlanPriceLink.
     *
     * @param productRatePlanPriceLink the entity to save.
     * @return the persisted entity.
     */
    ProductRatePlanPriceLink save(ProductRatePlanPriceLink productRatePlanPriceLink);

    /**
     * Get all the productRatePlanPriceLinks.
     *
     * @return the list of entities.
     */
    List<ProductRatePlanPriceLink> findAll();


    /**
     * Get the "id" productRatePlanPriceLink.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ProductRatePlanPriceLink> findOne(Long id);

    /**
     * Delete the "id" productRatePlanPriceLink.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
