package com.universign.universigncs.unistripe.web.rest;

import com.universign.universigncs.unistripe.UnistripeApp;
import com.universign.universigncs.unistripe.domain.BillingCustomer;
import com.universign.universigncs.unistripe.repository.BillingCustomerRepository;

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
 * Integration tests for the {@link BillingCustomerResource} REST controller.
 */
@SpringBootTest(classes = UnistripeApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class BillingCustomerResourceIT {

    private static final String DEFAULT_TAX_NO = "AAAAAAAAAA";
    private static final String UPDATED_TAX_NO = "BBBBBBBBBB";

    private static final String DEFAULT_THIRD_PARTY_ACCOUNTING_CODE = "AAAAAAAAAA";
    private static final String UPDATED_THIRD_PARTY_ACCOUNTING_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_SIRET = "AAAAAAAAAA";
    private static final String UPDATED_SIRET = "BBBBBBBBBB";

    private static final String DEFAULT_OWNER_ID = "AAAAAAAAAA";
    private static final String UPDATED_OWNER_ID = "BBBBBBBBBB";

    private static final Boolean DEFAULT_IS_PARTICULAR = false;
    private static final Boolean UPDATED_IS_PARTICULAR = true;

    private static final Boolean DEFAULT_PARTNER = false;
    private static final Boolean UPDATED_PARTNER = true;

    private static final String DEFAULT_PARTNER_ID = "AAAAAAAAAA";
    private static final String UPDATED_PARTNER_ID = "BBBBBBBBBB";

    private static final String DEFAULT_CUSTOMER_ID = "AAAAAAAAAA";
    private static final String UPDATED_CUSTOMER_ID = "BBBBBBBBBB";

    private static final String DEFAULT_CUSTOMER_NAME = "AAAAAAAAAA";
    private static final String UPDATED_CUSTOMER_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_STRIPE_ID = "AAAAAAAAAA";
    private static final String UPDATED_STRIPE_ID = "BBBBBBBBBB";

    private static final String DEFAULT_STRIPE_EMAIL = "AAAAAAAAAA";
    private static final String UPDATED_STRIPE_EMAIL = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_MIGRATE_AT = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_MIGRATE_AT = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_UPDATED_AT = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_UPDATED_AT = LocalDate.now(ZoneId.systemDefault());

    @Autowired
    private BillingCustomerRepository billingCustomerRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restBillingCustomerMockMvc;

    private BillingCustomer billingCustomer;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static BillingCustomer createEntity(EntityManager em) {
        BillingCustomer billingCustomer = new BillingCustomer()
            .taxNo(DEFAULT_TAX_NO)
            .thirdPartyAccountingCode(DEFAULT_THIRD_PARTY_ACCOUNTING_CODE)
            .siret(DEFAULT_SIRET)
            .ownerId(DEFAULT_OWNER_ID)
            .isParticular(DEFAULT_IS_PARTICULAR)
            .partner(DEFAULT_PARTNER)
            .partnerId(DEFAULT_PARTNER_ID)
            .customerId(DEFAULT_CUSTOMER_ID)
            .customerName(DEFAULT_CUSTOMER_NAME)
            .stripeId(DEFAULT_STRIPE_ID)
            .stripeEmail(DEFAULT_STRIPE_EMAIL)
            .migrateAt(DEFAULT_MIGRATE_AT)
            .updatedAt(DEFAULT_UPDATED_AT);
        return billingCustomer;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static BillingCustomer createUpdatedEntity(EntityManager em) {
        BillingCustomer billingCustomer = new BillingCustomer()
            .taxNo(UPDATED_TAX_NO)
            .thirdPartyAccountingCode(UPDATED_THIRD_PARTY_ACCOUNTING_CODE)
            .siret(UPDATED_SIRET)
            .ownerId(UPDATED_OWNER_ID)
            .isParticular(UPDATED_IS_PARTICULAR)
            .partner(UPDATED_PARTNER)
            .partnerId(UPDATED_PARTNER_ID)
            .customerId(UPDATED_CUSTOMER_ID)
            .customerName(UPDATED_CUSTOMER_NAME)
            .stripeId(UPDATED_STRIPE_ID)
            .stripeEmail(UPDATED_STRIPE_EMAIL)
            .migrateAt(UPDATED_MIGRATE_AT)
            .updatedAt(UPDATED_UPDATED_AT);
        return billingCustomer;
    }

    @BeforeEach
    public void initTest() {
        billingCustomer = createEntity(em);
    }

    @Test
    @Transactional
    public void createBillingCustomer() throws Exception {
        int databaseSizeBeforeCreate = billingCustomerRepository.findAll().size();
        // Create the BillingCustomer
        restBillingCustomerMockMvc.perform(post("/api/billing-customers")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(billingCustomer)))
            .andExpect(status().isCreated());

        // Validate the BillingCustomer in the database
        List<BillingCustomer> billingCustomerList = billingCustomerRepository.findAll();
        assertThat(billingCustomerList).hasSize(databaseSizeBeforeCreate + 1);
        BillingCustomer testBillingCustomer = billingCustomerList.get(billingCustomerList.size() - 1);
        assertThat(testBillingCustomer.getTaxNo()).isEqualTo(DEFAULT_TAX_NO);
        assertThat(testBillingCustomer.getThirdPartyAccountingCode()).isEqualTo(DEFAULT_THIRD_PARTY_ACCOUNTING_CODE);
        assertThat(testBillingCustomer.getSiret()).isEqualTo(DEFAULT_SIRET);
        assertThat(testBillingCustomer.getOwnerId()).isEqualTo(DEFAULT_OWNER_ID);
        assertThat(testBillingCustomer.isIsParticular()).isEqualTo(DEFAULT_IS_PARTICULAR);
        assertThat(testBillingCustomer.isPartner()).isEqualTo(DEFAULT_PARTNER);
        assertThat(testBillingCustomer.getPartnerId()).isEqualTo(DEFAULT_PARTNER_ID);
        assertThat(testBillingCustomer.getCustomerId()).isEqualTo(DEFAULT_CUSTOMER_ID);
        assertThat(testBillingCustomer.getCustomerName()).isEqualTo(DEFAULT_CUSTOMER_NAME);
        assertThat(testBillingCustomer.getStripeId()).isEqualTo(DEFAULT_STRIPE_ID);
        assertThat(testBillingCustomer.getStripeEmail()).isEqualTo(DEFAULT_STRIPE_EMAIL);
        assertThat(testBillingCustomer.getMigrateAt()).isEqualTo(DEFAULT_MIGRATE_AT);
        assertThat(testBillingCustomer.getUpdatedAt()).isEqualTo(DEFAULT_UPDATED_AT);
    }

    @Test
    @Transactional
    public void createBillingCustomerWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = billingCustomerRepository.findAll().size();

        // Create the BillingCustomer with an existing ID
        billingCustomer.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restBillingCustomerMockMvc.perform(post("/api/billing-customers")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(billingCustomer)))
            .andExpect(status().isBadRequest());

        // Validate the BillingCustomer in the database
        List<BillingCustomer> billingCustomerList = billingCustomerRepository.findAll();
        assertThat(billingCustomerList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllBillingCustomers() throws Exception {
        // Initialize the database
        billingCustomerRepository.saveAndFlush(billingCustomer);

        // Get all the billingCustomerList
        restBillingCustomerMockMvc.perform(get("/api/billing-customers?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(billingCustomer.getId().intValue())))
            .andExpect(jsonPath("$.[*].taxNo").value(hasItem(DEFAULT_TAX_NO)))
            .andExpect(jsonPath("$.[*].thirdPartyAccountingCode").value(hasItem(DEFAULT_THIRD_PARTY_ACCOUNTING_CODE)))
            .andExpect(jsonPath("$.[*].siret").value(hasItem(DEFAULT_SIRET)))
            .andExpect(jsonPath("$.[*].ownerId").value(hasItem(DEFAULT_OWNER_ID)))
            .andExpect(jsonPath("$.[*].isParticular").value(hasItem(DEFAULT_IS_PARTICULAR.booleanValue())))
            .andExpect(jsonPath("$.[*].partner").value(hasItem(DEFAULT_PARTNER.booleanValue())))
            .andExpect(jsonPath("$.[*].partnerId").value(hasItem(DEFAULT_PARTNER_ID)))
            .andExpect(jsonPath("$.[*].customerId").value(hasItem(DEFAULT_CUSTOMER_ID)))
            .andExpect(jsonPath("$.[*].customerName").value(hasItem(DEFAULT_CUSTOMER_NAME)))
            .andExpect(jsonPath("$.[*].stripeId").value(hasItem(DEFAULT_STRIPE_ID)))
            .andExpect(jsonPath("$.[*].stripeEmail").value(hasItem(DEFAULT_STRIPE_EMAIL)))
            .andExpect(jsonPath("$.[*].migrateAt").value(hasItem(DEFAULT_MIGRATE_AT.toString())))
            .andExpect(jsonPath("$.[*].updatedAt").value(hasItem(DEFAULT_UPDATED_AT.toString())));
    }
    
    @Test
    @Transactional
    public void getBillingCustomer() throws Exception {
        // Initialize the database
        billingCustomerRepository.saveAndFlush(billingCustomer);

        // Get the billingCustomer
        restBillingCustomerMockMvc.perform(get("/api/billing-customers/{id}", billingCustomer.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(billingCustomer.getId().intValue()))
            .andExpect(jsonPath("$.taxNo").value(DEFAULT_TAX_NO))
            .andExpect(jsonPath("$.thirdPartyAccountingCode").value(DEFAULT_THIRD_PARTY_ACCOUNTING_CODE))
            .andExpect(jsonPath("$.siret").value(DEFAULT_SIRET))
            .andExpect(jsonPath("$.ownerId").value(DEFAULT_OWNER_ID))
            .andExpect(jsonPath("$.isParticular").value(DEFAULT_IS_PARTICULAR.booleanValue()))
            .andExpect(jsonPath("$.partner").value(DEFAULT_PARTNER.booleanValue()))
            .andExpect(jsonPath("$.partnerId").value(DEFAULT_PARTNER_ID))
            .andExpect(jsonPath("$.customerId").value(DEFAULT_CUSTOMER_ID))
            .andExpect(jsonPath("$.customerName").value(DEFAULT_CUSTOMER_NAME))
            .andExpect(jsonPath("$.stripeId").value(DEFAULT_STRIPE_ID))
            .andExpect(jsonPath("$.stripeEmail").value(DEFAULT_STRIPE_EMAIL))
            .andExpect(jsonPath("$.migrateAt").value(DEFAULT_MIGRATE_AT.toString()))
            .andExpect(jsonPath("$.updatedAt").value(DEFAULT_UPDATED_AT.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingBillingCustomer() throws Exception {
        // Get the billingCustomer
        restBillingCustomerMockMvc.perform(get("/api/billing-customers/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateBillingCustomer() throws Exception {
        // Initialize the database
        billingCustomerRepository.saveAndFlush(billingCustomer);

        int databaseSizeBeforeUpdate = billingCustomerRepository.findAll().size();

        // Update the billingCustomer
        BillingCustomer updatedBillingCustomer = billingCustomerRepository.findById(billingCustomer.getId()).get();
        // Disconnect from session so that the updates on updatedBillingCustomer are not directly saved in db
        em.detach(updatedBillingCustomer);
        updatedBillingCustomer
            .taxNo(UPDATED_TAX_NO)
            .thirdPartyAccountingCode(UPDATED_THIRD_PARTY_ACCOUNTING_CODE)
            .siret(UPDATED_SIRET)
            .ownerId(UPDATED_OWNER_ID)
            .isParticular(UPDATED_IS_PARTICULAR)
            .partner(UPDATED_PARTNER)
            .partnerId(UPDATED_PARTNER_ID)
            .customerId(UPDATED_CUSTOMER_ID)
            .customerName(UPDATED_CUSTOMER_NAME)
            .stripeId(UPDATED_STRIPE_ID)
            .stripeEmail(UPDATED_STRIPE_EMAIL)
            .migrateAt(UPDATED_MIGRATE_AT)
            .updatedAt(UPDATED_UPDATED_AT);

        restBillingCustomerMockMvc.perform(put("/api/billing-customers")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedBillingCustomer)))
            .andExpect(status().isOk());

        // Validate the BillingCustomer in the database
        List<BillingCustomer> billingCustomerList = billingCustomerRepository.findAll();
        assertThat(billingCustomerList).hasSize(databaseSizeBeforeUpdate);
        BillingCustomer testBillingCustomer = billingCustomerList.get(billingCustomerList.size() - 1);
        assertThat(testBillingCustomer.getTaxNo()).isEqualTo(UPDATED_TAX_NO);
        assertThat(testBillingCustomer.getThirdPartyAccountingCode()).isEqualTo(UPDATED_THIRD_PARTY_ACCOUNTING_CODE);
        assertThat(testBillingCustomer.getSiret()).isEqualTo(UPDATED_SIRET);
        assertThat(testBillingCustomer.getOwnerId()).isEqualTo(UPDATED_OWNER_ID);
        assertThat(testBillingCustomer.isIsParticular()).isEqualTo(UPDATED_IS_PARTICULAR);
        assertThat(testBillingCustomer.isPartner()).isEqualTo(UPDATED_PARTNER);
        assertThat(testBillingCustomer.getPartnerId()).isEqualTo(UPDATED_PARTNER_ID);
        assertThat(testBillingCustomer.getCustomerId()).isEqualTo(UPDATED_CUSTOMER_ID);
        assertThat(testBillingCustomer.getCustomerName()).isEqualTo(UPDATED_CUSTOMER_NAME);
        assertThat(testBillingCustomer.getStripeId()).isEqualTo(UPDATED_STRIPE_ID);
        assertThat(testBillingCustomer.getStripeEmail()).isEqualTo(UPDATED_STRIPE_EMAIL);
        assertThat(testBillingCustomer.getMigrateAt()).isEqualTo(UPDATED_MIGRATE_AT);
        assertThat(testBillingCustomer.getUpdatedAt()).isEqualTo(UPDATED_UPDATED_AT);
    }

    @Test
    @Transactional
    public void updateNonExistingBillingCustomer() throws Exception {
        int databaseSizeBeforeUpdate = billingCustomerRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restBillingCustomerMockMvc.perform(put("/api/billing-customers")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(billingCustomer)))
            .andExpect(status().isBadRequest());

        // Validate the BillingCustomer in the database
        List<BillingCustomer> billingCustomerList = billingCustomerRepository.findAll();
        assertThat(billingCustomerList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteBillingCustomer() throws Exception {
        // Initialize the database
        billingCustomerRepository.saveAndFlush(billingCustomer);

        int databaseSizeBeforeDelete = billingCustomerRepository.findAll().size();

        // Delete the billingCustomer
        restBillingCustomerMockMvc.perform(delete("/api/billing-customers/{id}", billingCustomer.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<BillingCustomer> billingCustomerList = billingCustomerRepository.findAll();
        assertThat(billingCustomerList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
