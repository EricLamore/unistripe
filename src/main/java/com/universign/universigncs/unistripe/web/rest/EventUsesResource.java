package com.universign.universigncs.unistripe.web.rest;

import com.universign.universigncs.unistripe.domain.EventUses;
import com.universign.universigncs.unistripe.service.EventUsesService;
import com.universign.universigncs.unistripe.web.rest.errors.BadRequestAlertException;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.universign.universigncs.unistripe.domain.EventUses}.
 */
@RestController
@RequestMapping("/api")
public class EventUsesResource {

    private final Logger log = LoggerFactory.getLogger(EventUsesResource.class);

    private static final String ENTITY_NAME = "eventUses";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final EventUsesService eventUsesService;

    public EventUsesResource(EventUsesService eventUsesService) {
        this.eventUsesService = eventUsesService;
    }

    /**
     * {@code POST  /event-uses} : Create a new eventUses.
     *
     * @param eventUses the eventUses to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new eventUses, or with status {@code 400 (Bad Request)} if the eventUses has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/event-uses")
    public ResponseEntity<EventUses> createEventUses(@RequestBody EventUses eventUses) throws URISyntaxException {
        log.debug("REST request to save EventUses : {}", eventUses);
        if (eventUses.getId() != null) {
            throw new BadRequestAlertException("A new eventUses cannot already have an ID", ENTITY_NAME, "idexists");
        }
        EventUses result = eventUsesService.save(eventUses);
        return ResponseEntity.created(new URI("/api/event-uses/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /event-uses} : Updates an existing eventUses.
     *
     * @param eventUses the eventUses to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated eventUses,
     * or with status {@code 400 (Bad Request)} if the eventUses is not valid,
     * or with status {@code 500 (Internal Server Error)} if the eventUses couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/event-uses")
    public ResponseEntity<EventUses> updateEventUses(@RequestBody EventUses eventUses) throws URISyntaxException {
        log.debug("REST request to update EventUses : {}", eventUses);
        if (eventUses.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        EventUses result = eventUsesService.save(eventUses);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, eventUses.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /event-uses} : get all the eventUses.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of eventUses in body.
     */
    @GetMapping("/event-uses")
    public List<EventUses> getAllEventUses() {
        log.debug("REST request to get all EventUses");
        return eventUsesService.findAll();
    }

    /**
     * {@code GET  /event-uses/:id} : get the "id" eventUses.
     *
     * @param id the id of the eventUses to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the eventUses, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/event-uses/{id}")
    public ResponseEntity<EventUses> getEventUses(@PathVariable Long id) {
        log.debug("REST request to get EventUses : {}", id);
        Optional<EventUses> eventUses = eventUsesService.findOne(id);
        return ResponseUtil.wrapOrNotFound(eventUses);
    }

    /**
     * {@code DELETE  /event-uses/:id} : delete the "id" eventUses.
     *
     * @param id the id of the eventUses to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/event-uses/{id}")
    public ResponseEntity<Void> deleteEventUses(@PathVariable Long id) {
        log.debug("REST request to delete EventUses : {}", id);
        eventUsesService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
