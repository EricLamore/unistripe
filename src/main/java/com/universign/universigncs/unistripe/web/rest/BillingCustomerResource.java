package com.universign.universigncs.unistripe.web.rest;

import com.universign.universigncs.unistripe.domain.BillingCustomer;
import com.universign.universigncs.unistripe.repository.BillingCustomerRepository;
import com.universign.universigncs.unistripe.web.rest.errors.BadRequestAlertException;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.universign.universigncs.unistripe.domain.BillingCustomer}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class BillingCustomerResource {

    private final Logger log = LoggerFactory.getLogger(BillingCustomerResource.class);

    private static final String ENTITY_NAME = "billingCustomer";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final BillingCustomerRepository billingCustomerRepository;

    public BillingCustomerResource(BillingCustomerRepository billingCustomerRepository) {
        this.billingCustomerRepository = billingCustomerRepository;
    }

    /**
     * {@code POST  /billing-customers} : Create a new billingCustomer.
     *
     * @param billingCustomer the billingCustomer to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new billingCustomer, or with status {@code 400 (Bad Request)} if the billingCustomer has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/billing-customers")
    public ResponseEntity<BillingCustomer> createBillingCustomer(@RequestBody BillingCustomer billingCustomer) throws URISyntaxException {
        log.debug("REST request to save BillingCustomer : {}", billingCustomer);
        if (billingCustomer.getId() != null) {
            throw new BadRequestAlertException("A new billingCustomer cannot already have an ID", ENTITY_NAME, "idexists");
        }
        BillingCustomer result = billingCustomerRepository.save(billingCustomer);
        return ResponseEntity.created(new URI("/api/billing-customers/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /billing-customers} : Updates an existing billingCustomer.
     *
     * @param billingCustomer the billingCustomer to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated billingCustomer,
     * or with status {@code 400 (Bad Request)} if the billingCustomer is not valid,
     * or with status {@code 500 (Internal Server Error)} if the billingCustomer couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/billing-customers")
    public ResponseEntity<BillingCustomer> updateBillingCustomer(@RequestBody BillingCustomer billingCustomer) throws URISyntaxException {
        log.debug("REST request to update BillingCustomer : {}", billingCustomer);
        if (billingCustomer.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        BillingCustomer result = billingCustomerRepository.save(billingCustomer);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, billingCustomer.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /billing-customers} : get all the billingCustomers.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of billingCustomers in body.
     */
    @GetMapping("/billing-customers")
    public List<BillingCustomer> getAllBillingCustomers() {
        log.debug("REST request to get all BillingCustomers");
        return billingCustomerRepository.findAll();
    }

    /**
     * {@code GET  /billing-customers/:id} : get the "id" billingCustomer.
     *
     * @param id the id of the billingCustomer to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the billingCustomer, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/billing-customers/{id}")
    public ResponseEntity<BillingCustomer> getBillingCustomer(@PathVariable Long id) {
        log.debug("REST request to get BillingCustomer : {}", id);
        Optional<BillingCustomer> billingCustomer = billingCustomerRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(billingCustomer);
    }

    /**
     * {@code DELETE  /billing-customers/:id} : delete the "id" billingCustomer.
     *
     * @param id the id of the billingCustomer to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/billing-customers/{id}")
    public ResponseEntity<Void> deleteBillingCustomer(@PathVariable Long id) {
        log.debug("REST request to delete BillingCustomer : {}", id);
        billingCustomerRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
