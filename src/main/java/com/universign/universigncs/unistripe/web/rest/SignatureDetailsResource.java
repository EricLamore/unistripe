package com.universign.universigncs.unistripe.web.rest;

import com.universign.universigncs.unistripe.domain.SignatureDetails;
import com.universign.universigncs.unistripe.service.SignatureDetailsService;
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
 * REST controller for managing {@link com.universign.universigncs.unistripe.domain.SignatureDetails}.
 */
@RestController
@RequestMapping("/api")
public class SignatureDetailsResource {

    private final Logger log = LoggerFactory.getLogger(SignatureDetailsResource.class);

    private static final String ENTITY_NAME = "signatureDetails";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final SignatureDetailsService signatureDetailsService;

    public SignatureDetailsResource(SignatureDetailsService signatureDetailsService) {
        this.signatureDetailsService = signatureDetailsService;
    }

    /**
     * {@code POST  /signature-details} : Create a new signatureDetails.
     *
     * @param signatureDetails the signatureDetails to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new signatureDetails, or with status {@code 400 (Bad Request)} if the signatureDetails has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/signature-details")
    public ResponseEntity<SignatureDetails> createSignatureDetails(@RequestBody SignatureDetails signatureDetails) throws URISyntaxException {
        log.debug("REST request to save SignatureDetails : {}", signatureDetails);
        if (signatureDetails.getId() != null) {
            throw new BadRequestAlertException("A new signatureDetails cannot already have an ID", ENTITY_NAME, "idexists");
        }
        SignatureDetails result = signatureDetailsService.save(signatureDetails);
        return ResponseEntity.created(new URI("/api/signature-details/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /signature-details} : Updates an existing signatureDetails.
     *
     * @param signatureDetails the signatureDetails to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated signatureDetails,
     * or with status {@code 400 (Bad Request)} if the signatureDetails is not valid,
     * or with status {@code 500 (Internal Server Error)} if the signatureDetails couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/signature-details")
    public ResponseEntity<SignatureDetails> updateSignatureDetails(@RequestBody SignatureDetails signatureDetails) throws URISyntaxException {
        log.debug("REST request to update SignatureDetails : {}", signatureDetails);
        if (signatureDetails.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        SignatureDetails result = signatureDetailsService.save(signatureDetails);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, signatureDetails.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /signature-details} : get all the signatureDetails.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of signatureDetails in body.
     */
    @GetMapping("/signature-details")
    public List<SignatureDetails> getAllSignatureDetails() {
        log.debug("REST request to get all SignatureDetails");
        return signatureDetailsService.findAll();
    }

    /**
     * {@code GET  /signature-details/:id} : get the "id" signatureDetails.
     *
     * @param id the id of the signatureDetails to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the signatureDetails, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/signature-details/{id}")
    public ResponseEntity<SignatureDetails> getSignatureDetails(@PathVariable Long id) {
        log.debug("REST request to get SignatureDetails : {}", id);
        Optional<SignatureDetails> signatureDetails = signatureDetailsService.findOne(id);
        return ResponseUtil.wrapOrNotFound(signatureDetails);
    }

    /**
     * {@code DELETE  /signature-details/:id} : delete the "id" signatureDetails.
     *
     * @param id the id of the signatureDetails to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/signature-details/{id}")
    public ResponseEntity<Void> deleteSignatureDetails(@PathVariable Long id) {
        log.debug("REST request to delete SignatureDetails : {}", id);
        signatureDetailsService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
