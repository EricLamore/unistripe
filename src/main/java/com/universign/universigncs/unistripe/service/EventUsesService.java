package com.universign.universigncs.unistripe.service;

import com.universign.universigncs.unistripe.domain.EventUses;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link EventUses}.
 */
public interface EventUsesService {

    /**
     * Save a eventUses.
     *
     * @param eventUses the entity to save.
     * @return the persisted entity.
     */
    EventUses save(EventUses eventUses);

    /**
     * Get all the eventUses.
     *
     * @return the list of entities.
     */
    List<EventUses> findAll();


    /**
     * Get the "id" eventUses.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<EventUses> findOne(Long id);

    /**
     * Delete the "id" eventUses.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
