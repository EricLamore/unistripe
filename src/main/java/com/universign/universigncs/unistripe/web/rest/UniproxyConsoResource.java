package com.universign.universigncs.unistripe.web.rest;

import com.universign.universigncs.unistripe.domain.UniproxyConso;
import com.universign.universigncs.unistripe.service.UniproxyConsoService;
import com.universign.universigncs.unistripe.web.rest.errors.BadRequestAlertException;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.universign.universigncs.unistripe.domain.UniproxyConso}.
 */
@RestController
@RequestMapping("/api")
public class UniproxyConsoResource {

    private final Logger log = LoggerFactory.getLogger(UniproxyConsoResource.class);

    private static final String ENTITY_NAME = "uniproxyConso";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final UniproxyConsoService uniproxyConsoService;

    public UniproxyConsoResource(UniproxyConsoService uniproxyConsoService) {
        this.uniproxyConsoService = uniproxyConsoService;
    }

    /**
     * {@code POST  /uniproxy-consos} : Create a new uniproxyConso.
     *
     * @param uniproxyConso the uniproxyConso to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new uniproxyConso, or with status {@code 400 (Bad Request)} if the uniproxyConso has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/uniproxy-consos")
    public ResponseEntity<UniproxyConso> createUniproxyConso(@RequestBody UniproxyConso uniproxyConso) throws URISyntaxException {
        log.debug("REST request to save UniproxyConso : {}", uniproxyConso);
        if (uniproxyConso.getId() != null) {
            throw new BadRequestAlertException("A new uniproxyConso cannot already have an ID", ENTITY_NAME, "idexists");
        }
        UniproxyConso result = uniproxyConsoService.save(uniproxyConso);
        return ResponseEntity.created(new URI("/api/uniproxy-consos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /uniproxy-consos} : Updates an existing uniproxyConso.
     *
     * @param uniproxyConso the uniproxyConso to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated uniproxyConso,
     * or with status {@code 400 (Bad Request)} if the uniproxyConso is not valid,
     * or with status {@code 500 (Internal Server Error)} if the uniproxyConso couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/uniproxy-consos")
    public ResponseEntity<UniproxyConso> updateUniproxyConso(@RequestBody UniproxyConso uniproxyConso) throws URISyntaxException {
        log.debug("REST request to update UniproxyConso : {}", uniproxyConso);
        if (uniproxyConso.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        UniproxyConso result = uniproxyConsoService.save(uniproxyConso);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, uniproxyConso.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /uniproxy-consos} : get all the uniproxyConsos.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of uniproxyConsos in body.
     */
    @GetMapping("/uniproxy-consos")
    public ResponseEntity<List<UniproxyConso>> getAllUniproxyConsos(Pageable pageable) {
        log.debug("REST request to get a page of UniproxyConsos");
        Page<UniproxyConso> page = uniproxyConsoService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /uniproxy-consos/:id} : get the "id" uniproxyConso.
     *
     * @param id the id of the uniproxyConso to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the uniproxyConso, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/uniproxy-consos/{id}")
    public ResponseEntity<UniproxyConso> getUniproxyConso(@PathVariable Long id) {
        log.debug("REST request to get UniproxyConso : {}", id);
        Optional<UniproxyConso> uniproxyConso = uniproxyConsoService.findOne(id);
        return ResponseUtil.wrapOrNotFound(uniproxyConso);
    }

    /**
     * {@code DELETE  /uniproxy-consos/:id} : delete the "id" uniproxyConso.
     *
     * @param id the id of the uniproxyConso to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/uniproxy-consos/{id}")
    public ResponseEntity<Void> deleteUniproxyConso(@PathVariable Long id) {
        log.debug("REST request to delete UniproxyConso : {}", id);
        uniproxyConsoService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
