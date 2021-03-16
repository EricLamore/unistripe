package com.universign.universigncs.unistripe.service.impl;

import com.universign.universigncs.unistripe.service.UniproxyConsoService;
import com.universign.universigncs.unistripe.domain.UniproxyConso;
import com.universign.universigncs.unistripe.repository.UniproxyConsoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link UniproxyConso}.
 */
@Service
@Transactional
public class UniproxyConsoServiceImpl implements UniproxyConsoService {

    private final Logger log = LoggerFactory.getLogger(UniproxyConsoServiceImpl.class);

    private final UniproxyConsoRepository uniproxyConsoRepository;

    public UniproxyConsoServiceImpl(UniproxyConsoRepository uniproxyConsoRepository) {
        this.uniproxyConsoRepository = uniproxyConsoRepository;
    }

    @Override
    public UniproxyConso save(UniproxyConso uniproxyConso) {
        log.debug("Request to save UniproxyConso : {}", uniproxyConso);
        return uniproxyConsoRepository.save(uniproxyConso);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<UniproxyConso> findAll(Pageable pageable) {
        log.debug("Request to get all UniproxyConsos");
        return uniproxyConsoRepository.findAll(pageable);
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<UniproxyConso> findOne(Long id) {
        log.debug("Request to get UniproxyConso : {}", id);
        return uniproxyConsoRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete UniproxyConso : {}", id);
        uniproxyConsoRepository.deleteById(id);
    }
}
