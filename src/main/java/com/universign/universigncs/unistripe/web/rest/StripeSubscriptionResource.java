package com.universign.universigncs.unistripe.web.rest;

import com.universign.universigncs.unistripe.domain.StripeSubscription;
import com.universign.universigncs.unistripe.service.StripeSubscriptionService;
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
 * REST controller for managing {@link com.universign.universigncs.unistripe.domain.StripeSubscription}.
 */
@RestController
@RequestMapping("/api")
public class StripeSubscriptionResource {

    private final Logger log = LoggerFactory.getLogger(StripeSubscriptionResource.class);

    private static final String ENTITY_NAME = "stripeSubscription";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final StripeSubscriptionService stripeSubscriptionService;

    public StripeSubscriptionResource(StripeSubscriptionService stripeSubscriptionService) {
        this.stripeSubscriptionService = stripeSubscriptionService;
    }

    /**
     * {@code POST  /stripe-subscriptions} : Create a new stripeSubscription.
     *
     * @param stripeSubscription the stripeSubscription to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new stripeSubscription, or with status {@code 400 (Bad Request)} if the stripeSubscription has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/stripe-subscriptions")
    public ResponseEntity<StripeSubscription> createStripeSubscription(@RequestBody StripeSubscription stripeSubscription) throws URISyntaxException {
        log.debug("REST request to save StripeSubscription : {}", stripeSubscription);
        if (stripeSubscription.getId() != null) {
            throw new BadRequestAlertException("A new stripeSubscription cannot already have an ID", ENTITY_NAME, "idexists");
        }
        StripeSubscription result = stripeSubscriptionService.save(stripeSubscription);
        return ResponseEntity.created(new URI("/api/stripe-subscriptions/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /stripe-subscriptions} : Updates an existing stripeSubscription.
     *
     * @param stripeSubscription the stripeSubscription to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated stripeSubscription,
     * or with status {@code 400 (Bad Request)} if the stripeSubscription is not valid,
     * or with status {@code 500 (Internal Server Error)} if the stripeSubscription couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/stripe-subscriptions")
    public ResponseEntity<StripeSubscription> updateStripeSubscription(@RequestBody StripeSubscription stripeSubscription) throws URISyntaxException {
        log.debug("REST request to update StripeSubscription : {}", stripeSubscription);
        if (stripeSubscription.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        StripeSubscription result = stripeSubscriptionService.save(stripeSubscription);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, stripeSubscription.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /stripe-subscriptions} : get all the stripeSubscriptions.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of stripeSubscriptions in body.
     */
    @GetMapping("/stripe-subscriptions")
    public ResponseEntity<List<StripeSubscription>> getAllStripeSubscriptions(Pageable pageable) {
        log.debug("REST request to get a page of StripeSubscriptions");
        Page<StripeSubscription> page = stripeSubscriptionService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /stripe-subscriptions/:id} : get the "id" stripeSubscription.
     *
     * @param id the id of the stripeSubscription to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the stripeSubscription, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/stripe-subscriptions/{id}")
    public ResponseEntity<StripeSubscription> getStripeSubscription(@PathVariable Long id) {
        log.debug("REST request to get StripeSubscription : {}", id);
        Optional<StripeSubscription> stripeSubscription = stripeSubscriptionService.findOne(id);
        return ResponseUtil.wrapOrNotFound(stripeSubscription);
    }

    /**
     * {@code DELETE  /stripe-subscriptions/:id} : delete the "id" stripeSubscription.
     *
     * @param id the id of the stripeSubscription to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/stripe-subscriptions/{id}")
    public ResponseEntity<Void> deleteStripeSubscription(@PathVariable Long id) {
        log.debug("REST request to delete StripeSubscription : {}", id);
        stripeSubscriptionService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
