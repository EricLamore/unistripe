package com.universign.universigncs.unistripe.service.impl;

import com.universign.universigncs.unistripe.service.CustomerOrganizationLinkService;
import com.universign.universigncs.unistripe.domain.CustomerOrganizationLink;
import com.universign.universigncs.unistripe.repository.CustomerOrganizationLinkRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing {@link CustomerOrganizationLink}.
 */
@Service
@Transactional
public class CustomerOrganizationLinkServiceImpl implements CustomerOrganizationLinkService {

    private final Logger log = LoggerFactory.getLogger(CustomerOrganizationLinkServiceImpl.class);

    private final CustomerOrganizationLinkRepository customerOrganizationLinkRepository;

    public CustomerOrganizationLinkServiceImpl(CustomerOrganizationLinkRepository customerOrganizationLinkRepository) {
        this.customerOrganizationLinkRepository = customerOrganizationLinkRepository;
    }

    @Override
    public CustomerOrganizationLink save(CustomerOrganizationLink customerOrganizationLink) {
        log.debug("Request to save CustomerOrganizationLink : {}", customerOrganizationLink);
        return customerOrganizationLinkRepository.save(customerOrganizationLink);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CustomerOrganizationLink> findAll() {
        log.debug("Request to get all CustomerOrganizationLinks");
        return customerOrganizationLinkRepository.findAll();
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<CustomerOrganizationLink> findOne(Long id) {
        log.debug("Request to get CustomerOrganizationLink : {}", id);
        return customerOrganizationLinkRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete CustomerOrganizationLink : {}", id);
        customerOrganizationLinkRepository.deleteById(id);
    }
}
