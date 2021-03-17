package com.universign.universigncs.unistripe.service.impl;

import com.universign.universigncs.unistripe.service.PriceService;
import com.universign.universigncs.unistripe.domain.Price;
import com.universign.universigncs.unistripe.repository.PriceRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing {@link Price}.
 */
@Service
@Transactional
public class PriceServiceImpl implements PriceService {

    private final Logger log = LoggerFactory.getLogger(PriceServiceImpl.class);

    private final PriceRepository priceRepository;

    public PriceServiceImpl(PriceRepository priceRepository) {
        this.priceRepository = priceRepository;
    }

    @Override
    public Price save(Price price) {
        log.debug("Request to save Price : {}", price);
        return priceRepository.save(price);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Price> findAll() {
        log.debug("Request to get all Prices");
        return priceRepository.findAll();
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<Price> findOne(Long id) {
        log.debug("Request to get Price : {}", id);
        return priceRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Price : {}", id);
        priceRepository.deleteById(id);
    }
}
