package com.universign.universigncs.unistripe.web.rest;

import com.universign.universigncs.unistripe.domain.ProductRatePlanPriceLink;
import com.universign.universigncs.unistripe.service.ProductRatePlanPriceLinkService;
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
 * REST controller for managing {@link com.universign.universigncs.unistripe.domain.ProductRatePlanPriceLink}.
 */
@RestController
@RequestMapping("/api")
public class ProductRatePlanPriceLinkResource {

    private final Logger log = LoggerFactory.getLogger(ProductRatePlanPriceLinkResource.class);

    private static final String ENTITY_NAME = "productRatePlanPriceLink";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ProductRatePlanPriceLinkService productRatePlanPriceLinkService;

    public ProductRatePlanPriceLinkResource(ProductRatePlanPriceLinkService productRatePlanPriceLinkService) {
        this.productRatePlanPriceLinkService = productRatePlanPriceLinkService;
    }

    /**
     * {@code POST  /product-rate-plan-price-links} : Create a new productRatePlanPriceLink.
     *
     * @param productRatePlanPriceLink the productRatePlanPriceLink to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new productRatePlanPriceLink, or with status {@code 400 (Bad Request)} if the productRatePlanPriceLink has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/product-rate-plan-price-links")
    public ResponseEntity<ProductRatePlanPriceLink> createProductRatePlanPriceLink(@RequestBody ProductRatePlanPriceLink productRatePlanPriceLink) throws URISyntaxException {
        log.debug("REST request to save ProductRatePlanPriceLink : {}", productRatePlanPriceLink);
        if (productRatePlanPriceLink.getId() != null) {
            throw new BadRequestAlertException("A new productRatePlanPriceLink cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ProductRatePlanPriceLink result = productRatePlanPriceLinkService.save(productRatePlanPriceLink);
        return ResponseEntity.created(new URI("/api/product-rate-plan-price-links/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /product-rate-plan-price-links} : Updates an existing productRatePlanPriceLink.
     *
     * @param productRatePlanPriceLink the productRatePlanPriceLink to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated productRatePlanPriceLink,
     * or with status {@code 400 (Bad Request)} if the productRatePlanPriceLink is not valid,
     * or with status {@code 500 (Internal Server Error)} if the productRatePlanPriceLink couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/product-rate-plan-price-links")
    public ResponseEntity<ProductRatePlanPriceLink> updateProductRatePlanPriceLink(@RequestBody ProductRatePlanPriceLink productRatePlanPriceLink) throws URISyntaxException {
        log.debug("REST request to update ProductRatePlanPriceLink : {}", productRatePlanPriceLink);
        if (productRatePlanPriceLink.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ProductRatePlanPriceLink result = productRatePlanPriceLinkService.save(productRatePlanPriceLink);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, productRatePlanPriceLink.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /product-rate-plan-price-links} : get all the productRatePlanPriceLinks.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of productRatePlanPriceLinks in body.
     */
    @GetMapping("/product-rate-plan-price-links")
    public List<ProductRatePlanPriceLink> getAllProductRatePlanPriceLinks() {
        log.debug("REST request to get all ProductRatePlanPriceLinks");
        return productRatePlanPriceLinkService.findAll();
    }

    /**
     * {@code GET  /product-rate-plan-price-links/:id} : get the "id" productRatePlanPriceLink.
     *
     * @param id the id of the productRatePlanPriceLink to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the productRatePlanPriceLink, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/product-rate-plan-price-links/{id}")
    public ResponseEntity<ProductRatePlanPriceLink> getProductRatePlanPriceLink(@PathVariable Long id) {
        log.debug("REST request to get ProductRatePlanPriceLink : {}", id);
        Optional<ProductRatePlanPriceLink> productRatePlanPriceLink = productRatePlanPriceLinkService.findOne(id);
        return ResponseUtil.wrapOrNotFound(productRatePlanPriceLink);
    }

    /**
     * {@code DELETE  /product-rate-plan-price-links/:id} : delete the "id" productRatePlanPriceLink.
     *
     * @param id the id of the productRatePlanPriceLink to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/product-rate-plan-price-links/{id}")
    public ResponseEntity<Void> deleteProductRatePlanPriceLink(@PathVariable Long id) {
        log.debug("REST request to delete ProductRatePlanPriceLink : {}", id);
        productRatePlanPriceLinkService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
