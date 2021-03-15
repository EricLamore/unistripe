package com.universign.universigncs.unistripe.service.impl;

import com.universign.universigncs.unistripe.service.BillsService;
import com.universign.universigncs.unistripe.domain.Bills;
import com.universign.universigncs.unistripe.repository.BillsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing {@link Bills}.
 */
@Service
@Transactional
public class BillsServiceImpl implements BillsService {

    private final Logger log = LoggerFactory.getLogger(BillsServiceImpl.class);

    private final BillsRepository billsRepository;

    public BillsServiceImpl(BillsRepository billsRepository) {
        this.billsRepository = billsRepository;
    }

    @Override
    public Bills save(Bills bills) {
        log.debug("Request to save Bills : {}", bills);
        return billsRepository.save(bills);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Bills> findAll() {
        log.debug("Request to get all Bills");
        return billsRepository.findAll();
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<Bills> findOne(Long id) {
        log.debug("Request to get Bills : {}", id);
        return billsRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Bills : {}", id);
        billsRepository.deleteById(id);
    }
}
