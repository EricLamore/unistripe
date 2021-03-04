package com.universign.universigncs.unistripe.service;

import com.universign.universigncs.unistripe.domain.SignatureDetails;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link SignatureDetails}.
 */
public interface SignatureDetailsService {

    /**
     * Save a signatureDetails.
     *
     * @param signatureDetails the entity to save.
     * @return the persisted entity.
     */
    SignatureDetails save(SignatureDetails signatureDetails);

    /**
     * Get all the signatureDetails.
     *
     * @return the list of entities.
     */
    List<SignatureDetails> findAll();


    /**
     * Get the "id" signatureDetails.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<SignatureDetails> findOne(Long id);

    /**
     * Delete the "id" signatureDetails.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
