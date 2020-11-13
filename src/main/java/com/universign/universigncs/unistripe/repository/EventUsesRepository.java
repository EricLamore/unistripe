package com.universign.universigncs.unistripe.repository;

import com.universign.universigncs.unistripe.domain.EventUses;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the EventUses entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EventUsesRepository extends JpaRepository<EventUses, Long> {
}
