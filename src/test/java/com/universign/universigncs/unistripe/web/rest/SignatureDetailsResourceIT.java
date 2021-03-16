package com.universign.universigncs.unistripe.web.rest;

import com.universign.universigncs.unistripe.UnistripeApp;
import com.universign.universigncs.unistripe.domain.SignatureDetails;
import com.universign.universigncs.unistripe.repository.SignatureDetailsRepository;
import com.universign.universigncs.unistripe.service.SignatureDetailsService;

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
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link SignatureDetailsResource} REST controller.
 */
@SpringBootTest(classes = UnistripeApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class SignatureDetailsResourceIT {

    private static final String DEFAULT_FIRST_NAME = "AAAAAAAAAA";
    private static final String UPDATED_FIRST_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_LAST_NAME = "AAAAAAAAAA";
    private static final String UPDATED_LAST_NAME = "BBBBBBBBBB";

    private static final Instant DEFAULT_SIGANTURE_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_SIGANTURE_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Integer DEFAULT_SIGANTURE_COUNT = 1;
    private static final Integer UPDATED_SIGANTURE_COUNT = 2;

    @Autowired
    private SignatureDetailsRepository signatureDetailsRepository;

    @Autowired
    private SignatureDetailsService signatureDetailsService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restSignatureDetailsMockMvc;

    private SignatureDetails signatureDetails;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static SignatureDetails createEntity(EntityManager em) {
        SignatureDetails signatureDetails = new SignatureDetails()
            .firstName(DEFAULT_FIRST_NAME)
            .lastName(DEFAULT_LAST_NAME)
            .sigantureDate(DEFAULT_SIGANTURE_DATE)
            .sigantureCount(DEFAULT_SIGANTURE_COUNT);
        return signatureDetails;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static SignatureDetails createUpdatedEntity(EntityManager em) {
        SignatureDetails signatureDetails = new SignatureDetails()
            .firstName(UPDATED_FIRST_NAME)
            .lastName(UPDATED_LAST_NAME)
            .sigantureDate(UPDATED_SIGANTURE_DATE)
            .sigantureCount(UPDATED_SIGANTURE_COUNT);
        return signatureDetails;
    }

    @BeforeEach
    public void initTest() {
        signatureDetails = createEntity(em);
    }

    @Test
    @Transactional
    public void createSignatureDetails() throws Exception {
        int databaseSizeBeforeCreate = signatureDetailsRepository.findAll().size();
        // Create the SignatureDetails
        restSignatureDetailsMockMvc.perform(post("/api/signature-details")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(signatureDetails)))
            .andExpect(status().isCreated());

        // Validate the SignatureDetails in the database
        List<SignatureDetails> signatureDetailsList = signatureDetailsRepository.findAll();
        assertThat(signatureDetailsList).hasSize(databaseSizeBeforeCreate + 1);
        SignatureDetails testSignatureDetails = signatureDetailsList.get(signatureDetailsList.size() - 1);
        assertThat(testSignatureDetails.getFirstName()).isEqualTo(DEFAULT_FIRST_NAME);
        assertThat(testSignatureDetails.getLastName()).isEqualTo(DEFAULT_LAST_NAME);
        assertThat(testSignatureDetails.getSigantureDate()).isEqualTo(DEFAULT_SIGANTURE_DATE);
        assertThat(testSignatureDetails.getSigantureCount()).isEqualTo(DEFAULT_SIGANTURE_COUNT);
    }

    @Test
    @Transactional
    public void createSignatureDetailsWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = signatureDetailsRepository.findAll().size();

        // Create the SignatureDetails with an existing ID
        signatureDetails.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restSignatureDetailsMockMvc.perform(post("/api/signature-details")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(signatureDetails)))
            .andExpect(status().isBadRequest());

        // Validate the SignatureDetails in the database
        List<SignatureDetails> signatureDetailsList = signatureDetailsRepository.findAll();
        assertThat(signatureDetailsList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllSignatureDetails() throws Exception {
        // Initialize the database
        signatureDetailsRepository.saveAndFlush(signatureDetails);

        // Get all the signatureDetailsList
        restSignatureDetailsMockMvc.perform(get("/api/signature-details?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(signatureDetails.getId().intValue())))
            .andExpect(jsonPath("$.[*].firstName").value(hasItem(DEFAULT_FIRST_NAME)))
            .andExpect(jsonPath("$.[*].lastName").value(hasItem(DEFAULT_LAST_NAME)))
            .andExpect(jsonPath("$.[*].sigantureDate").value(hasItem(DEFAULT_SIGANTURE_DATE.toString())))
            .andExpect(jsonPath("$.[*].sigantureCount").value(hasItem(DEFAULT_SIGANTURE_COUNT)));
    }
    
    @Test
    @Transactional
    public void getSignatureDetails() throws Exception {
        // Initialize the database
        signatureDetailsRepository.saveAndFlush(signatureDetails);

        // Get the signatureDetails
        restSignatureDetailsMockMvc.perform(get("/api/signature-details/{id}", signatureDetails.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(signatureDetails.getId().intValue()))
            .andExpect(jsonPath("$.firstName").value(DEFAULT_FIRST_NAME))
            .andExpect(jsonPath("$.lastName").value(DEFAULT_LAST_NAME))
            .andExpect(jsonPath("$.sigantureDate").value(DEFAULT_SIGANTURE_DATE.toString()))
            .andExpect(jsonPath("$.sigantureCount").value(DEFAULT_SIGANTURE_COUNT));
    }
    @Test
    @Transactional
    public void getNonExistingSignatureDetails() throws Exception {
        // Get the signatureDetails
        restSignatureDetailsMockMvc.perform(get("/api/signature-details/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateSignatureDetails() throws Exception {
        // Initialize the database
        signatureDetailsService.save(signatureDetails);

        int databaseSizeBeforeUpdate = signatureDetailsRepository.findAll().size();

        // Update the signatureDetails
        SignatureDetails updatedSignatureDetails = signatureDetailsRepository.findById(signatureDetails.getId()).get();
        // Disconnect from session so that the updates on updatedSignatureDetails are not directly saved in db
        em.detach(updatedSignatureDetails);
        updatedSignatureDetails
            .firstName(UPDATED_FIRST_NAME)
            .lastName(UPDATED_LAST_NAME)
            .sigantureDate(UPDATED_SIGANTURE_DATE)
            .sigantureCount(UPDATED_SIGANTURE_COUNT);

        restSignatureDetailsMockMvc.perform(put("/api/signature-details")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedSignatureDetails)))
            .andExpect(status().isOk());

        // Validate the SignatureDetails in the database
        List<SignatureDetails> signatureDetailsList = signatureDetailsRepository.findAll();
        assertThat(signatureDetailsList).hasSize(databaseSizeBeforeUpdate);
        SignatureDetails testSignatureDetails = signatureDetailsList.get(signatureDetailsList.size() - 1);
        assertThat(testSignatureDetails.getFirstName()).isEqualTo(UPDATED_FIRST_NAME);
        assertThat(testSignatureDetails.getLastName()).isEqualTo(UPDATED_LAST_NAME);
        assertThat(testSignatureDetails.getSigantureDate()).isEqualTo(UPDATED_SIGANTURE_DATE);
        assertThat(testSignatureDetails.getSigantureCount()).isEqualTo(UPDATED_SIGANTURE_COUNT);
    }

    @Test
    @Transactional
    public void updateNonExistingSignatureDetails() throws Exception {
        int databaseSizeBeforeUpdate = signatureDetailsRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSignatureDetailsMockMvc.perform(put("/api/signature-details")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(signatureDetails)))
            .andExpect(status().isBadRequest());

        // Validate the SignatureDetails in the database
        List<SignatureDetails> signatureDetailsList = signatureDetailsRepository.findAll();
        assertThat(signatureDetailsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteSignatureDetails() throws Exception {
        // Initialize the database
        signatureDetailsService.save(signatureDetails);

        int databaseSizeBeforeDelete = signatureDetailsRepository.findAll().size();

        // Delete the signatureDetails
        restSignatureDetailsMockMvc.perform(delete("/api/signature-details/{id}", signatureDetails.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<SignatureDetails> signatureDetailsList = signatureDetailsRepository.findAll();
        assertThat(signatureDetailsList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
