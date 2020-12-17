package com.universign.universigncs.unistripe.web.rest;

import com.universign.universigncs.unistripe.domain.BillingStripeLink;
import com.universign.universigncs.unistripe.service.BillingStripeLinkService;
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
 * REST controller for managing {@link com.universign.universigncs.unistripe.domain.BillingStripeLink}.
 */
@RestController
@RequestMapping("/api")
public class BillingStripeLinkResource {

    private final Logger log = LoggerFactory.getLogger(BillingStripeLinkResource.class);

    private static final String ENTITY_NAME = "billingStripeLink";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final BillingStripeLinkService billingStripeLinkService;

    public BillingStripeLinkResource(BillingStripeLinkService billingStripeLinkService) {
        this.billingStripeLinkService = billingStripeLinkService;
    }

    /**
     * {@code POST  /billing-stripe-links} : Create a new billingStripeLink.
     *
     * @param billingStripeLink the billingStripeLink to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new billingStripeLink, or with status {@code 400 (Bad Request)} if the billingStripeLink has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/billing-stripe-links")
    public ResponseEntity<BillingStripeLink> createBillingStripeLink(@RequestBody BillingStripeLink billingStripeLink) throws URISyntaxException {
        log.debug("REST request to save BillingStripeLink : {}", billingStripeLink);
        if (billingStripeLink.getId() != null) {
            throw new BadRequestAlertException("A new billingStripeLink cannot already have an ID", ENTITY_NAME, "idexists");
        }
        BillingStripeLink result = billingStripeLinkService.save(billingStripeLink);
        return ResponseEntity.created(new URI("/api/billing-stripe-links/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /billing-stripe-links} : Updates an existing billingStripeLink.
     *
     * @param billingStripeLink the billingStripeLink to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated billingStripeLink,
     * or with status {@code 400 (Bad Request)} if the billingStripeLink is not valid,
     * or with status {@code 500 (Internal Server Error)} if the billingStripeLink couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/billing-stripe-links")
    public ResponseEntity<BillingStripeLink> updateBillingStripeLink(@RequestBody BillingStripeLink billingStripeLink) throws URISyntaxException {
        log.debug("REST request to update BillingStripeLink : {}", billingStripeLink);
        if (billingStripeLink.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        BillingStripeLink result = billingStripeLinkService.save(billingStripeLink);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, billingStripeLink.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /billing-stripe-links} : get all the billingStripeLinks.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of billingStripeLinks in body.
     */
    @GetMapping("/billing-stripe-links")
    public ResponseEntity<List<BillingStripeLink>> getAllBillingStripeLinks(Pageable pageable) {
        log.debug("REST request to get a page of BillingStripeLinks");
        Page<BillingStripeLink> page = billingStripeLinkService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /billing-stripe-links/:id} : get the "id" billingStripeLink.
     *
     * @param id the id of the billingStripeLink to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the billingStripeLink, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/billing-stripe-links/{id}")
    public ResponseEntity<BillingStripeLink> getBillingStripeLink(@PathVariable Long id) {
        log.debug("REST request to get BillingStripeLink : {}", id);
        Optional<BillingStripeLink> billingStripeLink = billingStripeLinkService.findOne(id);
        return ResponseUtil.wrapOrNotFound(billingStripeLink);
    }

    /**
     * {@code DELETE  /billing-stripe-links/:id} : delete the "id" billingStripeLink.
     *
     * @param id the id of the billingStripeLink to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/billing-stripe-links/{id}")
    public ResponseEntity<Void> deleteBillingStripeLink(@PathVariable Long id) {
        log.debug("REST request to delete BillingStripeLink : {}", id);
        billingStripeLinkService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
