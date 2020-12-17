package com.universign.universigncs.unistripe.repository;

import com.universign.universigncs.unistripe.domain.ProductRatePlanPriceLink;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the ProductRatePlanPriceLink entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ProductRatePlanPriceLinkRepository extends JpaRepository<ProductRatePlanPriceLink, Long> {
}
