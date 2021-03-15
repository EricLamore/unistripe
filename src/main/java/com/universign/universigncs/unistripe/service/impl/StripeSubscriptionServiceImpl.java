package com.universign.universigncs.unistripe.service.impl;

import com.universign.universigncs.unistripe.service.StripeSubscriptionService;
import com.universign.universigncs.unistripe.domain.StripeSubscription;
import com.universign.universigncs.unistripe.repository.StripeSubscriptionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link StripeSubscription}.
 */
@Service
@Transactional
public class StripeSubscriptionServiceImpl implements StripeSubscriptionService {

    private final Logger log = LoggerFactory.getLogger(StripeSubscriptionServiceImpl.class);

    private final StripeSubscriptionRepository stripeSubscriptionRepository;

    public StripeSubscriptionServiceImpl(StripeSubscriptionRepository stripeSubscriptionRepository) {
        this.stripeSubscriptionRepository = stripeSubscriptionRepository;
    }

    @Override
    public StripeSubscription save(StripeSubscription stripeSubscription) {
        log.debug("Request to save StripeSubscription : {}", stripeSubscription);
        return stripeSubscriptionRepository.save(stripeSubscription);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<StripeSubscription> findAll(Pageable pageable) {
        log.debug("Request to get all StripeSubscriptions");
        return stripeSubscriptionRepository.findAll(pageable);
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<StripeSubscription> findOne(Long id) {
        log.debug("Request to get StripeSubscription : {}", id);
        return stripeSubscriptionRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete StripeSubscription : {}", id);
        stripeSubscriptionRepository.deleteById(id);
    }
}
