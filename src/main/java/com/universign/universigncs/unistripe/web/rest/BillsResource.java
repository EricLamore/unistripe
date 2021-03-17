package com.universign.universigncs.unistripe.web.rest;

import com.universign.universigncs.unistripe.domain.Bills;
import com.universign.universigncs.unistripe.service.BillsService;
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
 * REST controller for managing {@link com.universign.universigncs.unistripe.domain.Bills}.
 */
@RestController
@RequestMapping("/api")
public class BillsResource {

    private final Logger log = LoggerFactory.getLogger(BillsResource.class);

    private static final String ENTITY_NAME = "bills";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final BillsService billsService;

    public BillsResource(BillsService billsService) {
        this.billsService = billsService;
    }

    /**
     * {@code POST  /bills} : Create a new bills.
     *
     * @param bills the bills to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new bills, or with status {@code 400 (Bad Request)} if the bills has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/bills")
    public ResponseEntity<Bills> createBills(@RequestBody Bills bills) throws URISyntaxException {
        log.debug("REST request to save Bills : {}", bills);
        if (bills.getId() != null) {
            throw new BadRequestAlertException("A new bills cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Bills result = billsService.save(bills);
        return ResponseEntity.created(new URI("/api/bills/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /bills} : Updates an existing bills.
     *
     * @param bills the bills to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated bills,
     * or with status {@code 400 (Bad Request)} if the bills is not valid,
     * or with status {@code 500 (Internal Server Error)} if the bills couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/bills")
    public ResponseEntity<Bills> updateBills(@RequestBody Bills bills) throws URISyntaxException {
        log.debug("REST request to update Bills : {}", bills);
        if (bills.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Bills result = billsService.save(bills);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, bills.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /bills} : get all the bills.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of bills in body.
     */
    @GetMapping("/bills")
    public ResponseEntity<List<Bills>> getAllBills(Pageable pageable) {
        log.debug("REST request to get a page of Bills");
        Page<Bills> page = billsService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /bills/:id} : get the "id" bills.
     *
     * @param id the id of the bills to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the bills, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/bills/{id}")
    public ResponseEntity<Bills> getBills(@PathVariable Long id) {
        log.debug("REST request to get Bills : {}", id);
        Optional<Bills> bills = billsService.findOne(id);
        return ResponseUtil.wrapOrNotFound(bills);
    }

    /**
     * {@code DELETE  /bills/:id} : delete the "id" bills.
     *
     * @param id the id of the bills to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/bills/{id}")
    public ResponseEntity<Void> deleteBills(@PathVariable Long id) {
        log.debug("REST request to delete Bills : {}", id);
        billsService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
