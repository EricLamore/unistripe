package com.universign.universigncs.unistripe.service.impl;

import com.universign.universigncs.unistripe.service.ProductRatePlanPriceLinkService;
import com.universign.universigncs.unistripe.domain.ProductRatePlanPriceLink;
import com.universign.universigncs.unistripe.repository.ProductRatePlanPriceLinkRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing {@link ProductRatePlanPriceLink}.
 */
@Service
@Transactional
public class ProductRatePlanPriceLinkServiceImpl implements ProductRatePlanPriceLinkService {

    private final Logger log = LoggerFactory.getLogger(ProductRatePlanPriceLinkServiceImpl.class);

    private final ProductRatePlanPriceLinkRepository productRatePlanPriceLinkRepository;

    public ProductRatePlanPriceLinkServiceImpl(ProductRatePlanPriceLinkRepository productRatePlanPriceLinkRepository) {
        this.productRatePlanPriceLinkRepository = productRatePlanPriceLinkRepository;
    }

    @Override
    public ProductRatePlanPriceLink save(ProductRatePlanPriceLink productRatePlanPriceLink) {
        log.debug("Request to save ProductRatePlanPriceLink : {}", productRatePlanPriceLink);
        return productRatePlanPriceLinkRepository.save(productRatePlanPriceLink);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductRatePlanPriceLink> findAll() {
        log.debug("Request to get all ProductRatePlanPriceLinks");
        return productRatePlanPriceLinkRepository.findAll();
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<ProductRatePlanPriceLink> findOne(Long id) {
        log.debug("Request to get ProductRatePlanPriceLink : {}", id);
        return productRatePlanPriceLinkRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete ProductRatePlanPriceLink : {}", id);
        productRatePlanPriceLinkRepository.deleteById(id);
    }
}
