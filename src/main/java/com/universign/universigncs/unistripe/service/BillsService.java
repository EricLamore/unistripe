package com.universign.universigncs.unistripe.service;

import com.universign.universigncs.unistripe.domain.Bills;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link Bills}.
 */
public interface BillsService {

    /**
     * Save a bills.
     *
     * @param bills the entity to save.
     * @return the persisted entity.
     */
    Bills save(Bills bills);

    /**
     * Get all the bills.
     *
     * @return the list of entities.
     */
    List<Bills> findAll();


    /**
     * Get the "id" bills.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Bills> findOne(Long id);

    /**
     * Delete the "id" bills.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
