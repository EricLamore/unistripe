package com.universign.universigncs.unistripe.web.rest;

import com.universign.universigncs.unistripe.UnistripeApp;
import com.universign.universigncs.unistripe.domain.BillingStripeLink;
import com.universign.universigncs.unistripe.repository.BillingStripeLinkRepository;
import com.universign.universigncs.unistripe.service.BillingStripeLinkService;

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
 * Integration tests for the {@link BillingStripeLinkResource} REST controller.
 */
@SpringBootTest(classes = UnistripeApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class BillingStripeLinkResourceIT {

    private static final String DEFAULT_STRIPE_ID = "AAAAAAAAAA";
    private static final String UPDATED_STRIPE_ID = "BBBBBBBBBB";

    private static final String DEFAULT_STRIPE_EMAIL = "AAAAAAAAAA";
    private static final String UPDATED_STRIPE_EMAIL = "BBBBBBBBBB";

    private static final String DEFAULT_CUSTOMER_ID = "AAAAAAAAAA";
    private static final String UPDATED_CUSTOMER_ID = "BBBBBBBBBB";

    private static final String DEFAULT_CUSTOMER_NAME = "AAAAAAAAAA";
    private static final String UPDATED_CUSTOMER_NAME = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_MIGRATE_AT = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_MIGRATE_AT = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_UPDATED_AT = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_UPDATED_AT = LocalDate.now(ZoneId.systemDefault());

    @Autowired
    private BillingStripeLinkRepository billingStripeLinkRepository;

    @Autowired
    private BillingStripeLinkService billingStripeLinkService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restBillingStripeLinkMockMvc;

    private BillingStripeLink billingStripeLink;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static BillingStripeLink createEntity(EntityManager em) {
        BillingStripeLink billingStripeLink = new BillingStripeLink()
            .stripeId(DEFAULT_STRIPE_ID)
            .stripeEmail(DEFAULT_STRIPE_EMAIL)
            .customerId(DEFAULT_CUSTOMER_ID)
            .customerName(DEFAULT_CUSTOMER_NAME)
            .migrateAt(DEFAULT_MIGRATE_AT)
            .updatedAt(DEFAULT_UPDATED_AT);
        return billingStripeLink;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static BillingStripeLink createUpdatedEntity(EntityManager em) {
        BillingStripeLink billingStripeLink = new BillingStripeLink()
            .stripeId(UPDATED_STRIPE_ID)
            .stripeEmail(UPDATED_STRIPE_EMAIL)
            .customerId(UPDATED_CUSTOMER_ID)
            .customerName(UPDATED_CUSTOMER_NAME)
            .migrateAt(UPDATED_MIGRATE_AT)
            .updatedAt(UPDATED_UPDATED_AT);
        return billingStripeLink;
    }

    @BeforeEach
    public void initTest() {
        billingStripeLink = createEntity(em);
    }

    @Test
    @Transactional
    public void createBillingStripeLink() throws Exception {
        int databaseSizeBeforeCreate = billingStripeLinkRepository.findAll().size();
        // Create the BillingStripeLink
        restBillingStripeLinkMockMvc.perform(post("/api/billing-stripe-links")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(billingStripeLink)))
            .andExpect(status().isCreated());

        // Validate the BillingStripeLink in the database
        List<BillingStripeLink> billingStripeLinkList = billingStripeLinkRepository.findAll();
        assertThat(billingStripeLinkList).hasSize(databaseSizeBeforeCreate + 1);
        BillingStripeLink testBillingStripeLink = billingStripeLinkList.get(billingStripeLinkList.size() - 1);
        assertThat(testBillingStripeLink.getStripeId()).isEqualTo(DEFAULT_STRIPE_ID);
        assertThat(testBillingStripeLink.getStripeEmail()).isEqualTo(DEFAULT_STRIPE_EMAIL);
        assertThat(testBillingStripeLink.getCustomerId()).isEqualTo(DEFAULT_CUSTOMER_ID);
        assertThat(testBillingStripeLink.getCustomerName()).isEqualTo(DEFAULT_CUSTOMER_NAME);
        assertThat(testBillingStripeLink.getMigrateAt()).isEqualTo(DEFAULT_MIGRATE_AT);
        assertThat(testBillingStripeLink.getUpdatedAt()).isEqualTo(DEFAULT_UPDATED_AT);
    }

    @Test
    @Transactional
    public void createBillingStripeLinkWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = billingStripeLinkRepository.findAll().size();

        // Create the BillingStripeLink with an existing ID
        billingStripeLink.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restBillingStripeLinkMockMvc.perform(post("/api/billing-stripe-links")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(billingStripeLink)))
            .andExpect(status().isBadRequest());

        // Validate the BillingStripeLink in the database
        List<BillingStripeLink> billingStripeLinkList = billingStripeLinkRepository.findAll();
        assertThat(billingStripeLinkList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllBillingStripeLinks() throws Exception {
        // Initialize the database
        billingStripeLinkRepository.saveAndFlush(billingStripeLink);

        // Get all the billingStripeLinkList
        restBillingStripeLinkMockMvc.perform(get("/api/billing-stripe-links?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(billingStripeLink.getId().intValue())))
            .andExpect(jsonPath("$.[*].stripeId").value(hasItem(DEFAULT_STRIPE_ID)))
            .andExpect(jsonPath("$.[*].stripeEmail").value(hasItem(DEFAULT_STRIPE_EMAIL)))
            .andExpect(jsonPath("$.[*].customerId").value(hasItem(DEFAULT_CUSTOMER_ID)))
            .andExpect(jsonPath("$.[*].customerName").value(hasItem(DEFAULT_CUSTOMER_NAME)))
            .andExpect(jsonPath("$.[*].migrateAt").value(hasItem(DEFAULT_MIGRATE_AT.toString())))
            .andExpect(jsonPath("$.[*].updatedAt").value(hasItem(DEFAULT_UPDATED_AT.toString())));
    }
    
    @Test
    @Transactional
    public void getBillingStripeLink() throws Exception {
        // Initialize the database
        billingStripeLinkRepository.saveAndFlush(billingStripeLink);

        // Get the billingStripeLink
        restBillingStripeLinkMockMvc.perform(get("/api/billing-stripe-links/{id}", billingStripeLink.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(billingStripeLink.getId().intValue()))
            .andExpect(jsonPath("$.stripeId").value(DEFAULT_STRIPE_ID))
            .andExpect(jsonPath("$.stripeEmail").value(DEFAULT_STRIPE_EMAIL))
            .andExpect(jsonPath("$.customerId").value(DEFAULT_CUSTOMER_ID))
            .andExpect(jsonPath("$.customerName").value(DEFAULT_CUSTOMER_NAME))
            .andExpect(jsonPath("$.migrateAt").value(DEFAULT_MIGRATE_AT.toString()))
            .andExpect(jsonPath("$.updatedAt").value(DEFAULT_UPDATED_AT.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingBillingStripeLink() throws Exception {
        // Get the billingStripeLink
        restBillingStripeLinkMockMvc.perform(get("/api/billing-stripe-links/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateBillingStripeLink() throws Exception {
        // Initialize the database
        billingStripeLinkService.save(billingStripeLink);

        int databaseSizeBeforeUpdate = billingStripeLinkRepository.findAll().size();

        // Update the billingStripeLink
        BillingStripeLink updatedBillingStripeLink = billingStripeLinkRepository.findById(billingStripeLink.getId()).get();
        // Disconnect from session so that the updates on updatedBillingStripeLink are not directly saved in db
        em.detach(updatedBillingStripeLink);
        updatedBillingStripeLink
            .stripeId(UPDATED_STRIPE_ID)
            .stripeEmail(UPDATED_STRIPE_EMAIL)
            .customerId(UPDATED_CUSTOMER_ID)
            .customerName(UPDATED_CUSTOMER_NAME)
            .migrateAt(UPDATED_MIGRATE_AT)
            .updatedAt(UPDATED_UPDATED_AT);

        restBillingStripeLinkMockMvc.perform(put("/api/billing-stripe-links")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedBillingStripeLink)))
            .andExpect(status().isOk());

        // Validate the BillingStripeLink in the database
        List<BillingStripeLink> billingStripeLinkList = billingStripeLinkRepository.findAll();
        assertThat(billingStripeLinkList).hasSize(databaseSizeBeforeUpdate);
        BillingStripeLink testBillingStripeLink = billingStripeLinkList.get(billingStripeLinkList.size() - 1);
        assertThat(testBillingStripeLink.getStripeId()).isEqualTo(UPDATED_STRIPE_ID);
        assertThat(testBillingStripeLink.getStripeEmail()).isEqualTo(UPDATED_STRIPE_EMAIL);
        assertThat(testBillingStripeLink.getCustomerId()).isEqualTo(UPDATED_CUSTOMER_ID);
        assertThat(testBillingStripeLink.getCustomerName()).isEqualTo(UPDATED_CUSTOMER_NAME);
        assertThat(testBillingStripeLink.getMigrateAt()).isEqualTo(UPDATED_MIGRATE_AT);
        assertThat(testBillingStripeLink.getUpdatedAt()).isEqualTo(UPDATED_UPDATED_AT);
    }

    @Test
    @Transactional
    public void updateNonExistingBillingStripeLink() throws Exception {
        int databaseSizeBeforeUpdate = billingStripeLinkRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restBillingStripeLinkMockMvc.perform(put("/api/billing-stripe-links")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(billingStripeLink)))
            .andExpect(status().isBadRequest());

        // Validate the BillingStripeLink in the database
        List<BillingStripeLink> billingStripeLinkList = billingStripeLinkRepository.findAll();
        assertThat(billingStripeLinkList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteBillingStripeLink() throws Exception {
        // Initialize the database
        billingStripeLinkService.save(billingStripeLink);

        int databaseSizeBeforeDelete = billingStripeLinkRepository.findAll().size();

        // Delete the billingStripeLink
        restBillingStripeLinkMockMvc.perform(delete("/api/billing-stripe-links/{id}", billingStripeLink.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<BillingStripeLink> billingStripeLinkList = billingStripeLinkRepository.findAll();
        assertThat(billingStripeLinkList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
