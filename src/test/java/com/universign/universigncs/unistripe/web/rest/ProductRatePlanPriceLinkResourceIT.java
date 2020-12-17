package com.universign.universigncs.unistripe.web.rest;

import com.universign.universigncs.unistripe.UnistripeApp;
import com.universign.universigncs.unistripe.domain.ProductRatePlanPriceLink;
import com.universign.universigncs.unistripe.repository.ProductRatePlanPriceLinkRepository;
import com.universign.universigncs.unistripe.service.ProductRatePlanPriceLinkService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link ProductRatePlanPriceLinkResource} REST controller.
 */
@SpringBootTest(classes = UnistripeApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class ProductRatePlanPriceLinkResourceIT {

    private static final String DEFAULT_STRIPE_ID = "AAAAAAAAAA";
    private static final String UPDATED_STRIPE_ID = "BBBBBBBBBB";

    private static final String DEFAULT_STRIPE_NICK_NAME = "AAAAAAAAAA";
    private static final String UPDATED_STRIPE_NICK_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_PRODUCT_RATE_PLAN_ID = "AAAAAAAAAA";
    private static final String UPDATED_PRODUCT_RATE_PLAN_ID = "BBBBBBBBBB";

    private static final String DEFAULT_PRODUCT_RATE_PLAN_NAME = "AAAAAAAAAA";
    private static final String UPDATED_PRODUCT_RATE_PLAN_NAME = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_MIGRATE_AT = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_MIGRATE_AT = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_UPDATED_AT = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_UPDATED_AT = LocalDate.now(ZoneId.systemDefault());

    @Autowired
    private ProductRatePlanPriceLinkRepository productRatePlanPriceLinkRepository;

    @Autowired
    private ProductRatePlanPriceLinkService productRatePlanPriceLinkService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restProductRatePlanPriceLinkMockMvc;

    private ProductRatePlanPriceLink productRatePlanPriceLink;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ProductRatePlanPriceLink createEntity(EntityManager em) {
        ProductRatePlanPriceLink productRatePlanPriceLink = new ProductRatePlanPriceLink()
            .stripeId(DEFAULT_STRIPE_ID)
            .stripeNickName(DEFAULT_STRIPE_NICK_NAME)
            .productRatePlanId(DEFAULT_PRODUCT_RATE_PLAN_ID)
            .productRatePlanName(DEFAULT_PRODUCT_RATE_PLAN_NAME)
            .migrateAt(DEFAULT_MIGRATE_AT)
            .updatedAt(DEFAULT_UPDATED_AT);
        return productRatePlanPriceLink;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ProductRatePlanPriceLink createUpdatedEntity(EntityManager em) {
        ProductRatePlanPriceLink productRatePlanPriceLink = new ProductRatePlanPriceLink()
            .stripeId(UPDATED_STRIPE_ID)
            .stripeNickName(UPDATED_STRIPE_NICK_NAME)
            .productRatePlanId(UPDATED_PRODUCT_RATE_PLAN_ID)
            .productRatePlanName(UPDATED_PRODUCT_RATE_PLAN_NAME)
            .migrateAt(UPDATED_MIGRATE_AT)
            .updatedAt(UPDATED_UPDATED_AT);
        return productRatePlanPriceLink;
    }

    @BeforeEach
    public void initTest() {
        productRatePlanPriceLink = createEntity(em);
    }

    @Test
    @Transactional
    public void createProductRatePlanPriceLink() throws Exception {
        int databaseSizeBeforeCreate = productRatePlanPriceLinkRepository.findAll().size();
        // Create the ProductRatePlanPriceLink
        restProductRatePlanPriceLinkMockMvc.perform(post("/api/product-rate-plan-price-links")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(productRatePlanPriceLink)))
            .andExpect(status().isCreated());

        // Validate the ProductRatePlanPriceLink in the database
        List<ProductRatePlanPriceLink> productRatePlanPriceLinkList = productRatePlanPriceLinkRepository.findAll();
        assertThat(productRatePlanPriceLinkList).hasSize(databaseSizeBeforeCreate + 1);
        ProductRatePlanPriceLink testProductRatePlanPriceLink = productRatePlanPriceLinkList.get(productRatePlanPriceLinkList.size() - 1);
        assertThat(testProductRatePlanPriceLink.getStripeId()).isEqualTo(DEFAULT_STRIPE_ID);
        assertThat(testProductRatePlanPriceLink.getStripeNickName()).isEqualTo(DEFAULT_STRIPE_NICK_NAME);
        assertThat(testProductRatePlanPriceLink.getProductRatePlanId()).isEqualTo(DEFAULT_PRODUCT_RATE_PLAN_ID);
        assertThat(testProductRatePlanPriceLink.getProductRatePlanName()).isEqualTo(DEFAULT_PRODUCT_RATE_PLAN_NAME);
        assertThat(testProductRatePlanPriceLink.getMigrateAt()).isEqualTo(DEFAULT_MIGRATE_AT);
        assertThat(testProductRatePlanPriceLink.getUpdatedAt()).isEqualTo(DEFAULT_UPDATED_AT);
    }

    @Test
    @Transactional
    public void createProductRatePlanPriceLinkWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = productRatePlanPriceLinkRepository.findAll().size();

        // Create the ProductRatePlanPriceLink with an existing ID
        productRatePlanPriceLink.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restProductRatePlanPriceLinkMockMvc.perform(post("/api/product-rate-plan-price-links")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(productRatePlanPriceLink)))
            .andExpect(status().isBadRequest());

        // Validate the ProductRatePlanPriceLink in the database
        List<ProductRatePlanPriceLink> productRatePlanPriceLinkList = productRatePlanPriceLinkRepository.findAll();
        assertThat(productRatePlanPriceLinkList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllProductRatePlanPriceLinks() throws Exception {
        // Initialize the database
        productRatePlanPriceLinkRepository.saveAndFlush(productRatePlanPriceLink);

        // Get all the productRatePlanPriceLinkList
        restProductRatePlanPriceLinkMockMvc.perform(get("/api/product-rate-plan-price-links?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(productRatePlanPriceLink.getId().intValue())))
            .andExpect(jsonPath("$.[*].stripeId").value(hasItem(DEFAULT_STRIPE_ID)))
            .andExpect(jsonPath("$.[*].stripeNickName").value(hasItem(DEFAULT_STRIPE_NICK_NAME)))
            .andExpect(jsonPath("$.[*].productRatePlanId").value(hasItem(DEFAULT_PRODUCT_RATE_PLAN_ID)))
            .andExpect(jsonPath("$.[*].productRatePlanName").value(hasItem(DEFAULT_PRODUCT_RATE_PLAN_NAME)))
            .andExpect(jsonPath("$.[*].migrateAt").value(hasItem(DEFAULT_MIGRATE_AT.toString())))
            .andExpect(jsonPath("$.[*].updatedAt").value(hasItem(DEFAULT_UPDATED_AT.toString())));
    }
    
    @Test
    @Transactional
    public void getProductRatePlanPriceLink() throws Exception {
        // Initialize the database
        productRatePlanPriceLinkRepository.saveAndFlush(productRatePlanPriceLink);

        // Get the productRatePlanPriceLink
        restProductRatePlanPriceLinkMockMvc.perform(get("/api/product-rate-plan-price-links/{id}", productRatePlanPriceLink.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(productRatePlanPriceLink.getId().intValue()))
            .andExpect(jsonPath("$.stripeId").value(DEFAULT_STRIPE_ID))
            .andExpect(jsonPath("$.stripeNickName").value(DEFAULT_STRIPE_NICK_NAME))
            .andExpect(jsonPath("$.productRatePlanId").value(DEFAULT_PRODUCT_RATE_PLAN_ID))
            .andExpect(jsonPath("$.productRatePlanName").value(DEFAULT_PRODUCT_RATE_PLAN_NAME))
            .andExpect(jsonPath("$.migrateAt").value(DEFAULT_MIGRATE_AT.toString()))
            .andExpect(jsonPath("$.updatedAt").value(DEFAULT_UPDATED_AT.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingProductRatePlanPriceLink() throws Exception {
        // Get the productRatePlanPriceLink
        restProductRatePlanPriceLinkMockMvc.perform(get("/api/product-rate-plan-price-links/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateProductRatePlanPriceLink() throws Exception {
        // Initialize the database
        productRatePlanPriceLinkService.save(productRatePlanPriceLink);

        int databaseSizeBeforeUpdate = productRatePlanPriceLinkRepository.findAll().size();

        // Update the productRatePlanPriceLink
        ProductRatePlanPriceLink updatedProductRatePlanPriceLink = productRatePlanPriceLinkRepository.findById(productRatePlanPriceLink.getId()).get();
        // Disconnect from session so that the updates on updatedProductRatePlanPriceLink are not directly saved in db
        em.detach(updatedProductRatePlanPriceLink);
        updatedProductRatePlanPriceLink
            .stripeId(UPDATED_STRIPE_ID)
            .stripeNickName(UPDATED_STRIPE_NICK_NAME)
            .productRatePlanId(UPDATED_PRODUCT_RATE_PLAN_ID)
            .productRatePlanName(UPDATED_PRODUCT_RATE_PLAN_NAME)
            .migrateAt(UPDATED_MIGRATE_AT)
            .updatedAt(UPDATED_UPDATED_AT);

        restProductRatePlanPriceLinkMockMvc.perform(put("/api/product-rate-plan-price-links")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedProductRatePlanPriceLink)))
            .andExpect(status().isOk());

        // Validate the ProductRatePlanPriceLink in the database
        List<ProductRatePlanPriceLink> productRatePlanPriceLinkList = productRatePlanPriceLinkRepository.findAll();
        assertThat(productRatePlanPriceLinkList).hasSize(databaseSizeBeforeUpdate);
        ProductRatePlanPriceLink testProductRatePlanPriceLink = productRatePlanPriceLinkList.get(productRatePlanPriceLinkList.size() - 1);
        assertThat(testProductRatePlanPriceLink.getStripeId()).isEqualTo(UPDATED_STRIPE_ID);
        assertThat(testProductRatePlanPriceLink.getStripeNickName()).isEqualTo(UPDATED_STRIPE_NICK_NAME);
        assertThat(testProductRatePlanPriceLink.getProductRatePlanId()).isEqualTo(UPDATED_PRODUCT_RATE_PLAN_ID);
        assertThat(testProductRatePlanPriceLink.getProductRatePlanName()).isEqualTo(UPDATED_PRODUCT_RATE_PLAN_NAME);
        assertThat(testProductRatePlanPriceLink.getMigrateAt()).isEqualTo(UPDATED_MIGRATE_AT);
        assertThat(testProductRatePlanPriceLink.getUpdatedAt()).isEqualTo(UPDATED_UPDATED_AT);
    }

    @Test
    @Transactional
    public void updateNonExistingProductRatePlanPriceLink() throws Exception {
        int databaseSizeBeforeUpdate = productRatePlanPriceLinkRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restProductRatePlanPriceLinkMockMvc.perform(put("/api/product-rate-plan-price-links")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(productRatePlanPriceLink)))
            .andExpect(status().isBadRequest());

        // Validate the ProductRatePlanPriceLink in the database
        List<ProductRatePlanPriceLink> productRatePlanPriceLinkList = productRatePlanPriceLinkRepository.findAll();
        assertThat(productRatePlanPriceLinkList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteProductRatePlanPriceLink() throws Exception {
        // Initialize the database
        productRatePlanPriceLinkService.save(productRatePlanPriceLink);

        int databaseSizeBeforeDelete = productRatePlanPriceLinkRepository.findAll().size();

        // Delete the productRatePlanPriceLink
        restProductRatePlanPriceLinkMockMvc.perform(delete("/api/product-rate-plan-price-links/{id}", productRatePlanPriceLink.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ProductRatePlanPriceLink> productRatePlanPriceLinkList = productRatePlanPriceLinkRepository.findAll();
        assertThat(productRatePlanPriceLinkList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
