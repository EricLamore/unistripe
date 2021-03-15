package com.universign.universigncs.unistripe.web.rest;

import com.universign.universigncs.unistripe.UnistripeApp;
import com.universign.universigncs.unistripe.domain.StripeSubscription;
import com.universign.universigncs.unistripe.repository.StripeSubscriptionRepository;
import com.universign.universigncs.unistripe.service.StripeSubscriptionService;

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
 * Integration tests for the {@link StripeSubscriptionResource} REST controller.
 */
@SpringBootTest(classes = UnistripeApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class StripeSubscriptionResourceIT {

    private static final String DEFAULT_STRIPE_ID = "AAAAAAAAAA";
    private static final String UPDATED_STRIPE_ID = "BBBBBBBBBB";

    private static final String DEFAULT_STRIPE_EMAIL = "AAAAAAAAAA";
    private static final String UPDATED_STRIPE_EMAIL = "BBBBBBBBBB";

    private static final String DEFAULT_SUBSCRIPTION_ID = "AAAAAAAAAA";
    private static final String UPDATED_SUBSCRIPTION_ID = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_MIGRATE_AT = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_MIGRATE_AT = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_UPDATED_AT = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_UPDATED_AT = LocalDate.now(ZoneId.systemDefault());

    @Autowired
    private StripeSubscriptionRepository stripeSubscriptionRepository;

    @Autowired
    private StripeSubscriptionService stripeSubscriptionService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restStripeSubscriptionMockMvc;

    private StripeSubscription stripeSubscription;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static StripeSubscription createEntity(EntityManager em) {
        StripeSubscription stripeSubscription = new StripeSubscription()
            .stripeId(DEFAULT_STRIPE_ID)
            .stripeEmail(DEFAULT_STRIPE_EMAIL)
            .subscriptionId(DEFAULT_SUBSCRIPTION_ID)
            .migrateAt(DEFAULT_MIGRATE_AT)
            .updatedAt(DEFAULT_UPDATED_AT);
        return stripeSubscription;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static StripeSubscription createUpdatedEntity(EntityManager em) {
        StripeSubscription stripeSubscription = new StripeSubscription()
            .stripeId(UPDATED_STRIPE_ID)
            .stripeEmail(UPDATED_STRIPE_EMAIL)
            .subscriptionId(UPDATED_SUBSCRIPTION_ID)
            .migrateAt(UPDATED_MIGRATE_AT)
            .updatedAt(UPDATED_UPDATED_AT);
        return stripeSubscription;
    }

    @BeforeEach
    public void initTest() {
        stripeSubscription = createEntity(em);
    }

    @Test
    @Transactional
    public void createStripeSubscription() throws Exception {
        int databaseSizeBeforeCreate = stripeSubscriptionRepository.findAll().size();
        // Create the StripeSubscription
        restStripeSubscriptionMockMvc.perform(post("/api/stripe-subscriptions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(stripeSubscription)))
            .andExpect(status().isCreated());

        // Validate the StripeSubscription in the database
        List<StripeSubscription> stripeSubscriptionList = stripeSubscriptionRepository.findAll();
        assertThat(stripeSubscriptionList).hasSize(databaseSizeBeforeCreate + 1);
        StripeSubscription testStripeSubscription = stripeSubscriptionList.get(stripeSubscriptionList.size() - 1);
        assertThat(testStripeSubscription.getStripeId()).isEqualTo(DEFAULT_STRIPE_ID);
        assertThat(testStripeSubscription.getStripeEmail()).isEqualTo(DEFAULT_STRIPE_EMAIL);
        assertThat(testStripeSubscription.getSubscriptionId()).isEqualTo(DEFAULT_SUBSCRIPTION_ID);
        assertThat(testStripeSubscription.getMigrateAt()).isEqualTo(DEFAULT_MIGRATE_AT);
        assertThat(testStripeSubscription.getUpdatedAt()).isEqualTo(DEFAULT_UPDATED_AT);
    }

    @Test
    @Transactional
    public void createStripeSubscriptionWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = stripeSubscriptionRepository.findAll().size();

        // Create the StripeSubscription with an existing ID
        stripeSubscription.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restStripeSubscriptionMockMvc.perform(post("/api/stripe-subscriptions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(stripeSubscription)))
            .andExpect(status().isBadRequest());

        // Validate the StripeSubscription in the database
        List<StripeSubscription> stripeSubscriptionList = stripeSubscriptionRepository.findAll();
        assertThat(stripeSubscriptionList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllStripeSubscriptions() throws Exception {
        // Initialize the database
        stripeSubscriptionRepository.saveAndFlush(stripeSubscription);

        // Get all the stripeSubscriptionList
        restStripeSubscriptionMockMvc.perform(get("/api/stripe-subscriptions?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(stripeSubscription.getId().intValue())))
            .andExpect(jsonPath("$.[*].stripeId").value(hasItem(DEFAULT_STRIPE_ID)))
            .andExpect(jsonPath("$.[*].stripeEmail").value(hasItem(DEFAULT_STRIPE_EMAIL)))
            .andExpect(jsonPath("$.[*].subscriptionId").value(hasItem(DEFAULT_SUBSCRIPTION_ID)))
            .andExpect(jsonPath("$.[*].migrateAt").value(hasItem(DEFAULT_MIGRATE_AT.toString())))
            .andExpect(jsonPath("$.[*].updatedAt").value(hasItem(DEFAULT_UPDATED_AT.toString())));
    }
    
    @Test
    @Transactional
    public void getStripeSubscription() throws Exception {
        // Initialize the database
        stripeSubscriptionRepository.saveAndFlush(stripeSubscription);

        // Get the stripeSubscription
        restStripeSubscriptionMockMvc.perform(get("/api/stripe-subscriptions/{id}", stripeSubscription.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(stripeSubscription.getId().intValue()))
            .andExpect(jsonPath("$.stripeId").value(DEFAULT_STRIPE_ID))
            .andExpect(jsonPath("$.stripeEmail").value(DEFAULT_STRIPE_EMAIL))
            .andExpect(jsonPath("$.subscriptionId").value(DEFAULT_SUBSCRIPTION_ID))
            .andExpect(jsonPath("$.migrateAt").value(DEFAULT_MIGRATE_AT.toString()))
            .andExpect(jsonPath("$.updatedAt").value(DEFAULT_UPDATED_AT.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingStripeSubscription() throws Exception {
        // Get the stripeSubscription
        restStripeSubscriptionMockMvc.perform(get("/api/stripe-subscriptions/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateStripeSubscription() throws Exception {
        // Initialize the database
        stripeSubscriptionService.save(stripeSubscription);

        int databaseSizeBeforeUpdate = stripeSubscriptionRepository.findAll().size();

        // Update the stripeSubscription
        StripeSubscription updatedStripeSubscription = stripeSubscriptionRepository.findById(stripeSubscription.getId()).get();
        // Disconnect from session so that the updates on updatedStripeSubscription are not directly saved in db
        em.detach(updatedStripeSubscription);
        updatedStripeSubscription
            .stripeId(UPDATED_STRIPE_ID)
            .stripeEmail(UPDATED_STRIPE_EMAIL)
            .subscriptionId(UPDATED_SUBSCRIPTION_ID)
            .migrateAt(UPDATED_MIGRATE_AT)
            .updatedAt(UPDATED_UPDATED_AT);

        restStripeSubscriptionMockMvc.perform(put("/api/stripe-subscriptions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedStripeSubscription)))
            .andExpect(status().isOk());

        // Validate the StripeSubscription in the database
        List<StripeSubscription> stripeSubscriptionList = stripeSubscriptionRepository.findAll();
        assertThat(stripeSubscriptionList).hasSize(databaseSizeBeforeUpdate);
        StripeSubscription testStripeSubscription = stripeSubscriptionList.get(stripeSubscriptionList.size() - 1);
        assertThat(testStripeSubscription.getStripeId()).isEqualTo(UPDATED_STRIPE_ID);
        assertThat(testStripeSubscription.getStripeEmail()).isEqualTo(UPDATED_STRIPE_EMAIL);
        assertThat(testStripeSubscription.getSubscriptionId()).isEqualTo(UPDATED_SUBSCRIPTION_ID);
        assertThat(testStripeSubscription.getMigrateAt()).isEqualTo(UPDATED_MIGRATE_AT);
        assertThat(testStripeSubscription.getUpdatedAt()).isEqualTo(UPDATED_UPDATED_AT);
    }

    @Test
    @Transactional
    public void updateNonExistingStripeSubscription() throws Exception {
        int databaseSizeBeforeUpdate = stripeSubscriptionRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restStripeSubscriptionMockMvc.perform(put("/api/stripe-subscriptions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(stripeSubscription)))
            .andExpect(status().isBadRequest());

        // Validate the StripeSubscription in the database
        List<StripeSubscription> stripeSubscriptionList = stripeSubscriptionRepository.findAll();
        assertThat(stripeSubscriptionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteStripeSubscription() throws Exception {
        // Initialize the database
        stripeSubscriptionService.save(stripeSubscription);

        int databaseSizeBeforeDelete = stripeSubscriptionRepository.findAll().size();

        // Delete the stripeSubscription
        restStripeSubscriptionMockMvc.perform(delete("/api/stripe-subscriptions/{id}", stripeSubscription.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<StripeSubscription> stripeSubscriptionList = stripeSubscriptionRepository.findAll();
        assertThat(stripeSubscriptionList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
