package com.universign.universigncs.unistripe.service.impl;

import com.universign.universigncs.unistripe.service.BillingStripeLinkService;
import com.universign.universigncs.unistripe.domain.BillingStripeLink;
import com.universign.universigncs.unistripe.repository.BillingStripeLinkRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link BillingStripeLink}.
 */
@Service
@Transactional
public class BillingStripeLinkServiceImpl implements BillingStripeLinkService {

    private final Logger log = LoggerFactory.getLogger(BillingStripeLinkServiceImpl.class);

    private final BillingStripeLinkRepository billingStripeLinkRepository;

    public BillingStripeLinkServiceImpl(BillingStripeLinkRepository billingStripeLinkRepository) {
        this.billingStripeLinkRepository = billingStripeLinkRepository;
    }

    @Override
    public BillingStripeLink save(BillingStripeLink billingStripeLink) {
        log.debug("Request to save BillingStripeLink : {}", billingStripeLink);
        return billingStripeLinkRepository.save(billingStripeLink);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<BillingStripeLink> findAll(Pageable pageable) {
        log.debug("Request to get all BillingStripeLinks");
        return billingStripeLinkRepository.findAll(pageable);
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<BillingStripeLink> findOne(Long id) {
        log.debug("Request to get BillingStripeLink : {}", id);
        return billingStripeLinkRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete BillingStripeLink : {}", id);
        billingStripeLinkRepository.deleteById(id);
    }
}
