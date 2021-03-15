package com.universign.universigncs.unistripe.repository;

import com.universign.universigncs.unistripe.domain.UniproxyConso;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the UniproxyConso entity.
 */
@SuppressWarnings("unused")
@Repository
public interface UniproxyConsoRepository extends JpaRepository<UniproxyConso, Long> {
}
