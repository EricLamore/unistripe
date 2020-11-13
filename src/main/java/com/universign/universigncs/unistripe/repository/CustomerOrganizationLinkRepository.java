package com.universign.universigncs.unistripe.repository;

import com.universign.universigncs.unistripe.domain.CustomerOrganizationLink;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the CustomerOrganizationLink entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CustomerOrganizationLinkRepository extends JpaRepository<CustomerOrganizationLink, Long> {
}
