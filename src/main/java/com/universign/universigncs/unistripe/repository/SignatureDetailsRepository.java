package com.universign.universigncs.unistripe.repository;

import com.universign.universigncs.unistripe.domain.SignatureDetails;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the SignatureDetails entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SignatureDetailsRepository extends JpaRepository<SignatureDetails, Long> {
}
