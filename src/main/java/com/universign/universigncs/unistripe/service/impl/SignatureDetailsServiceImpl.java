package com.universign.universigncs.unistripe.service.impl;

import com.universign.universigncs.unistripe.service.SignatureDetailsService;
import com.universign.universigncs.unistripe.domain.SignatureDetails;
import com.universign.universigncs.unistripe.repository.SignatureDetailsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing {@link SignatureDetails}.
 */
@Service
@Transactional
public class SignatureDetailsServiceImpl implements SignatureDetailsService {

    private final Logger log = LoggerFactory.getLogger(SignatureDetailsServiceImpl.class);

    private final SignatureDetailsRepository signatureDetailsRepository;

    public SignatureDetailsServiceImpl(SignatureDetailsRepository signatureDetailsRepository) {
        this.signatureDetailsRepository = signatureDetailsRepository;
    }

    @Override
    public SignatureDetails save(SignatureDetails signatureDetails) {
        log.debug("Request to save SignatureDetails : {}", signatureDetails);
        return signatureDetailsRepository.save(signatureDetails);
    }

    @Override
    @Transactional(readOnly = true)
    public List<SignatureDetails> findAll() {
        log.debug("Request to get all SignatureDetails");
        return signatureDetailsRepository.findAll();
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<SignatureDetails> findOne(Long id) {
        log.debug("Request to get SignatureDetails : {}", id);
        return signatureDetailsRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete SignatureDetails : {}", id);
        signatureDetailsRepository.deleteById(id);
    }
}
