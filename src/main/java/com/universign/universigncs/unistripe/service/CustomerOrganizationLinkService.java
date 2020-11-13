package com.universign.universigncs.unistripe.service;

import com.universign.universigncs.unistripe.domain.CustomerOrganizationLink;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link CustomerOrganizationLink}.
 */
public interface CustomerOrganizationLinkService {

    /**
     * Save a customerOrganizationLink.
     *
     * @param customerOrganizationLink the entity to save.
     * @return the persisted entity.
     */
    CustomerOrganizationLink save(CustomerOrganizationLink customerOrganizationLink);

    /**
     * Get all the customerOrganizationLinks.
     *
     * @return the list of entities.
     */
    List<CustomerOrganizationLink> findAll();


    /**
     * Get the "id" customerOrganizationLink.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<CustomerOrganizationLink> findOne(Long id);

    /**
     * Delete the "id" customerOrganizationLink.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
