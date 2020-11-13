package com.universign.universigncs.unistripe.web.rest;

import com.universign.universigncs.unistripe.UnistripeApp;
import com.universign.universigncs.unistripe.domain.CustomerOrganizationLink;
import com.universign.universigncs.unistripe.repository.CustomerOrganizationLinkRepository;
import com.universign.universigncs.unistripe.service.CustomerOrganizationLinkService;

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
 * Integration tests for the {@link CustomerOrganizationLinkResource} REST controller.
 */
@SpringBootTest(classes = UnistripeApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class CustomerOrganizationLinkResourceIT {

    private static final String DEFAULT_ORGANISATION_ID = "AAAAAAAAAA";
    private static final String UPDATED_ORGANISATION_ID = "BBBBBBBBBB";

    private static final String DEFAULT_ORGANISATION_NAME = "AAAAAAAAAA";
    private static final String UPDATED_ORGANISATION_NAME = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_ORGANISATION_REGISTER_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_ORGANISATION_REGISTER_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_CUSTOMER_ID = "AAAAAAAAAA";
    private static final String UPDATED_CUSTOMER_ID = "BBBBBBBBBB";

    private static final String DEFAULT_CUSTOMER_NAME = "AAAAAAAAAA";
    private static final String UPDATED_CUSTOMER_NAME = "BBBBBBBBBB";

    @Autowired
    private CustomerOrganizationLinkRepository customerOrganizationLinkRepository;

    @Autowired
    private CustomerOrganizationLinkService customerOrganizationLinkService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restCustomerOrganizationLinkMockMvc;

    private CustomerOrganizationLink customerOrganizationLink;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CustomerOrganizationLink createEntity(EntityManager em) {
        CustomerOrganizationLink customerOrganizationLink = new CustomerOrganizationLink()
            .organisationId(DEFAULT_ORGANISATION_ID)
            .organisationName(DEFAULT_ORGANISATION_NAME)
            .organisationRegisterDate(DEFAULT_ORGANISATION_REGISTER_DATE)
            .customerId(DEFAULT_CUSTOMER_ID)
            .customerName(DEFAULT_CUSTOMER_NAME);
        return customerOrganizationLink;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CustomerOrganizationLink createUpdatedEntity(EntityManager em) {
        CustomerOrganizationLink customerOrganizationLink = new CustomerOrganizationLink()
            .organisationId(UPDATED_ORGANISATION_ID)
            .organisationName(UPDATED_ORGANISATION_NAME)
            .organisationRegisterDate(UPDATED_ORGANISATION_REGISTER_DATE)
            .customerId(UPDATED_CUSTOMER_ID)
            .customerName(UPDATED_CUSTOMER_NAME);
        return customerOrganizationLink;
    }

    @BeforeEach
    public void initTest() {
        customerOrganizationLink = createEntity(em);
    }

    @Test
    @Transactional
    public void createCustomerOrganizationLink() throws Exception {
        int databaseSizeBeforeCreate = customerOrganizationLinkRepository.findAll().size();
        // Create the CustomerOrganizationLink
        restCustomerOrganizationLinkMockMvc.perform(post("/api/customer-organization-links")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(customerOrganizationLink)))
            .andExpect(status().isCreated());

        // Validate the CustomerOrganizationLink in the database
        List<CustomerOrganizationLink> customerOrganizationLinkList = customerOrganizationLinkRepository.findAll();
        assertThat(customerOrganizationLinkList).hasSize(databaseSizeBeforeCreate + 1);
        CustomerOrganizationLink testCustomerOrganizationLink = customerOrganizationLinkList.get(customerOrganizationLinkList.size() - 1);
        assertThat(testCustomerOrganizationLink.getOrganisationId()).isEqualTo(DEFAULT_ORGANISATION_ID);
        assertThat(testCustomerOrganizationLink.getOrganisationName()).isEqualTo(DEFAULT_ORGANISATION_NAME);
        assertThat(testCustomerOrganizationLink.getOrganisationRegisterDate()).isEqualTo(DEFAULT_ORGANISATION_REGISTER_DATE);
        assertThat(testCustomerOrganizationLink.getCustomerId()).isEqualTo(DEFAULT_CUSTOMER_ID);
        assertThat(testCustomerOrganizationLink.getCustomerName()).isEqualTo(DEFAULT_CUSTOMER_NAME);
    }

    @Test
    @Transactional
    public void createCustomerOrganizationLinkWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = customerOrganizationLinkRepository.findAll().size();

        // Create the CustomerOrganizationLink with an existing ID
        customerOrganizationLink.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCustomerOrganizationLinkMockMvc.perform(post("/api/customer-organization-links")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(customerOrganizationLink)))
            .andExpect(status().isBadRequest());

        // Validate the CustomerOrganizationLink in the database
        List<CustomerOrganizationLink> customerOrganizationLinkList = customerOrganizationLinkRepository.findAll();
        assertThat(customerOrganizationLinkList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllCustomerOrganizationLinks() throws Exception {
        // Initialize the database
        customerOrganizationLinkRepository.saveAndFlush(customerOrganizationLink);

        // Get all the customerOrganizationLinkList
        restCustomerOrganizationLinkMockMvc.perform(get("/api/customer-organization-links?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(customerOrganizationLink.getId().intValue())))
            .andExpect(jsonPath("$.[*].organisationId").value(hasItem(DEFAULT_ORGANISATION_ID)))
            .andExpect(jsonPath("$.[*].organisationName").value(hasItem(DEFAULT_ORGANISATION_NAME)))
            .andExpect(jsonPath("$.[*].organisationRegisterDate").value(hasItem(DEFAULT_ORGANISATION_REGISTER_DATE.toString())))
            .andExpect(jsonPath("$.[*].customerId").value(hasItem(DEFAULT_CUSTOMER_ID)))
            .andExpect(jsonPath("$.[*].customerName").value(hasItem(DEFAULT_CUSTOMER_NAME)));
    }
    
    @Test
    @Transactional
    public void getCustomerOrganizationLink() throws Exception {
        // Initialize the database
        customerOrganizationLinkRepository.saveAndFlush(customerOrganizationLink);

        // Get the customerOrganizationLink
        restCustomerOrganizationLinkMockMvc.perform(get("/api/customer-organization-links/{id}", customerOrganizationLink.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(customerOrganizationLink.getId().intValue()))
            .andExpect(jsonPath("$.organisationId").value(DEFAULT_ORGANISATION_ID))
            .andExpect(jsonPath("$.organisationName").value(DEFAULT_ORGANISATION_NAME))
            .andExpect(jsonPath("$.organisationRegisterDate").value(DEFAULT_ORGANISATION_REGISTER_DATE.toString()))
            .andExpect(jsonPath("$.customerId").value(DEFAULT_CUSTOMER_ID))
            .andExpect(jsonPath("$.customerName").value(DEFAULT_CUSTOMER_NAME));
    }
    @Test
    @Transactional
    public void getNonExistingCustomerOrganizationLink() throws Exception {
        // Get the customerOrganizationLink
        restCustomerOrganizationLinkMockMvc.perform(get("/api/customer-organization-links/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCustomerOrganizationLink() throws Exception {
        // Initialize the database
        customerOrganizationLinkService.save(customerOrganizationLink);

        int databaseSizeBeforeUpdate = customerOrganizationLinkRepository.findAll().size();

        // Update the customerOrganizationLink
        CustomerOrganizationLink updatedCustomerOrganizationLink = customerOrganizationLinkRepository.findById(customerOrganizationLink.getId()).get();
        // Disconnect from session so that the updates on updatedCustomerOrganizationLink are not directly saved in db
        em.detach(updatedCustomerOrganizationLink);
        updatedCustomerOrganizationLink
            .organisationId(UPDATED_ORGANISATION_ID)
            .organisationName(UPDATED_ORGANISATION_NAME)
            .organisationRegisterDate(UPDATED_ORGANISATION_REGISTER_DATE)
            .customerId(UPDATED_CUSTOMER_ID)
            .customerName(UPDATED_CUSTOMER_NAME);

        restCustomerOrganizationLinkMockMvc.perform(put("/api/customer-organization-links")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedCustomerOrganizationLink)))
            .andExpect(status().isOk());

        // Validate the CustomerOrganizationLink in the database
        List<CustomerOrganizationLink> customerOrganizationLinkList = customerOrganizationLinkRepository.findAll();
        assertThat(customerOrganizationLinkList).hasSize(databaseSizeBeforeUpdate);
        CustomerOrganizationLink testCustomerOrganizationLink = customerOrganizationLinkList.get(customerOrganizationLinkList.size() - 1);
        assertThat(testCustomerOrganizationLink.getOrganisationId()).isEqualTo(UPDATED_ORGANISATION_ID);
        assertThat(testCustomerOrganizationLink.getOrganisationName()).isEqualTo(UPDATED_ORGANISATION_NAME);
        assertThat(testCustomerOrganizationLink.getOrganisationRegisterDate()).isEqualTo(UPDATED_ORGANISATION_REGISTER_DATE);
        assertThat(testCustomerOrganizationLink.getCustomerId()).isEqualTo(UPDATED_CUSTOMER_ID);
        assertThat(testCustomerOrganizationLink.getCustomerName()).isEqualTo(UPDATED_CUSTOMER_NAME);
    }

    @Test
    @Transactional
    public void updateNonExistingCustomerOrganizationLink() throws Exception {
        int databaseSizeBeforeUpdate = customerOrganizationLinkRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCustomerOrganizationLinkMockMvc.perform(put("/api/customer-organization-links")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(customerOrganizationLink)))
            .andExpect(status().isBadRequest());

        // Validate the CustomerOrganizationLink in the database
        List<CustomerOrganizationLink> customerOrganizationLinkList = customerOrganizationLinkRepository.findAll();
        assertThat(customerOrganizationLinkList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteCustomerOrganizationLink() throws Exception {
        // Initialize the database
        customerOrganizationLinkService.save(customerOrganizationLink);

        int databaseSizeBeforeDelete = customerOrganizationLinkRepository.findAll().size();

        // Delete the customerOrganizationLink
        restCustomerOrganizationLinkMockMvc.perform(delete("/api/customer-organization-links/{id}", customerOrganizationLink.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<CustomerOrganizationLink> customerOrganizationLinkList = customerOrganizationLinkRepository.findAll();
        assertThat(customerOrganizationLinkList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
