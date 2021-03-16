package com.universign.universigncs.unistripe.service;

import com.universign.universigncs.unistripe.domain.UniproxyConso;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link UniproxyConso}.
 */
public interface UniproxyConsoService {

    /**
     * Save a uniproxyConso.
     *
     * @param uniproxyConso the entity to save.
     * @return the persisted entity.
     */
    UniproxyConso save(UniproxyConso uniproxyConso);

    /**
     * Get all the uniproxyConsos.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<UniproxyConso> findAll(Pageable pageable);


    /**
     * Get the "id" uniproxyConso.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<UniproxyConso> findOne(Long id);

    /**
     * Delete the "id" uniproxyConso.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
