package com.universign.universigncs.unistripe.web.rest;

import com.universign.universigncs.unistripe.domain.CustomerOrganizationLink;
import com.universign.universigncs.unistripe.service.CustomerOrganizationLinkService;
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
 * REST controller for managing {@link com.universign.universigncs.unistripe.domain.CustomerOrganizationLink}.
 */
@RestController
@RequestMapping("/api")
public class CustomerOrganizationLinkResource {

    private final Logger log = LoggerFactory.getLogger(CustomerOrganizationLinkResource.class);

    private static final String ENTITY_NAME = "customerOrganizationLink";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CustomerOrganizationLinkService customerOrganizationLinkService;

    public CustomerOrganizationLinkResource(CustomerOrganizationLinkService customerOrganizationLinkService) {
        this.customerOrganizationLinkService = customerOrganizationLinkService;
    }

    /**
     * {@code POST  /customer-organization-links} : Create a new customerOrganizationLink.
     *
     * @param customerOrganizationLink the customerOrganizationLink to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new customerOrganizationLink, or with status {@code 400 (Bad Request)} if the customerOrganizationLink has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/customer-organization-links")
    public ResponseEntity<CustomerOrganizationLink> createCustomerOrganizationLink(@RequestBody CustomerOrganizationLink customerOrganizationLink) throws URISyntaxException {
        log.debug("REST request to save CustomerOrganizationLink : {}", customerOrganizationLink);
        if (customerOrganizationLink.getId() != null) {
            throw new BadRequestAlertException("A new customerOrganizationLink cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CustomerOrganizationLink result = customerOrganizationLinkService.save(customerOrganizationLink);
        return ResponseEntity.created(new URI("/api/customer-organization-links/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /customer-organization-links} : Updates an existing customerOrganizationLink.
     *
     * @param customerOrganizationLink the customerOrganizationLink to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated customerOrganizationLink,
     * or with status {@code 400 (Bad Request)} if the customerOrganizationLink is not valid,
     * or with status {@code 500 (Internal Server Error)} if the customerOrganizationLink couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/customer-organization-links")
    public ResponseEntity<CustomerOrganizationLink> updateCustomerOrganizationLink(@RequestBody CustomerOrganizationLink customerOrganizationLink) throws URISyntaxException {
        log.debug("REST request to update CustomerOrganizationLink : {}", customerOrganizationLink);
        if (customerOrganizationLink.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        CustomerOrganizationLink result = customerOrganizationLinkService.save(customerOrganizationLink);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, customerOrganizationLink.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /customer-organization-links} : get all the customerOrganizationLinks.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of customerOrganizationLinks in body.
     */
    @GetMapping("/customer-organization-links")
    public List<CustomerOrganizationLink> getAllCustomerOrganizationLinks() {
        log.debug("REST request to get all CustomerOrganizationLinks");
        return customerOrganizationLinkService.findAll();
    }

    /**
     * {@code GET  /customer-organization-links/:id} : get the "id" customerOrganizationLink.
     *
     * @param id the id of the customerOrganizationLink to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the customerOrganizationLink, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/customer-organization-links/{id}")
    public ResponseEntity<CustomerOrganizationLink> getCustomerOrganizationLink(@PathVariable Long id) {
        log.debug("REST request to get CustomerOrganizationLink : {}", id);
        Optional<CustomerOrganizationLink> customerOrganizationLink = customerOrganizationLinkService.findOne(id);
        return ResponseUtil.wrapOrNotFound(customerOrganizationLink);
    }

    /**
     * {@code DELETE  /customer-organization-links/:id} : delete the "id" customerOrganizationLink.
     *
     * @param id the id of the customerOrganizationLink to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/customer-organization-links/{id}")
    public ResponseEntity<Void> deleteCustomerOrganizationLink(@PathVariable Long id) {
        log.debug("REST request to delete CustomerOrganizationLink : {}", id);
        customerOrganizationLinkService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
