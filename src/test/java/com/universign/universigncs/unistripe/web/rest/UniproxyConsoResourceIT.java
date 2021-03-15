package com.universign.universigncs.unistripe.web.rest;

import com.universign.universigncs.unistripe.UnistripeApp;
import com.universign.universigncs.unistripe.domain.UniproxyConso;
import com.universign.universigncs.unistripe.repository.UniproxyConsoRepository;
import com.universign.universigncs.unistripe.service.UniproxyConsoService;

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
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.universign.universigncs.unistripe.domain.enumeration.TYPE;
/**
 * Integration tests for the {@link UniproxyConsoResource} REST controller.
 */
@SpringBootTest(classes = UnistripeApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class UniproxyConsoResourceIT {

    private static final String DEFAULT_SUBSCRIPTION_ID = "AAAAAAAAAA";
    private static final String UPDATED_SUBSCRIPTION_ID = "BBBBBBBBBB";

    private static final String DEFAULT_ORG_ID = "AAAAAAAAAA";
    private static final String UPDATED_ORG_ID = "BBBBBBBBBB";

    private static final Integer DEFAULT_MONTH = 1;
    private static final Integer UPDATED_MONTH = 2;

    private static final Integer DEFAULT_YEAR = 1;
    private static final Integer UPDATED_YEAR = 2;

    private static final TYPE DEFAULT_TYPE = TYPE.SIGNATURE;
    private static final TYPE UPDATED_TYPE = TYPE.SERVER;

    private static final Integer DEFAULT_CONSO = 1;
    private static final Integer UPDATED_CONSO = 2;

    @Autowired
    private UniproxyConsoRepository uniproxyConsoRepository;

    @Autowired
    private UniproxyConsoService uniproxyConsoService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restUniproxyConsoMockMvc;

    private UniproxyConso uniproxyConso;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static UniproxyConso createEntity(EntityManager em) {
        UniproxyConso uniproxyConso = new UniproxyConso()
            .subscriptionId(DEFAULT_SUBSCRIPTION_ID)
            .orgId(DEFAULT_ORG_ID)
            .month(DEFAULT_MONTH)
            .year(DEFAULT_YEAR)
            .type(DEFAULT_TYPE)
            .conso(DEFAULT_CONSO);
        return uniproxyConso;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static UniproxyConso createUpdatedEntity(EntityManager em) {
        UniproxyConso uniproxyConso = new UniproxyConso()
            .subscriptionId(UPDATED_SUBSCRIPTION_ID)
            .orgId(UPDATED_ORG_ID)
            .month(UPDATED_MONTH)
            .year(UPDATED_YEAR)
            .type(UPDATED_TYPE)
            .conso(UPDATED_CONSO);
        return uniproxyConso;
    }

    @BeforeEach
    public void initTest() {
        uniproxyConso = createEntity(em);
    }

    @Test
    @Transactional
    public void createUniproxyConso() throws Exception {
        int databaseSizeBeforeCreate = uniproxyConsoRepository.findAll().size();
        // Create the UniproxyConso
        restUniproxyConsoMockMvc.perform(post("/api/uniproxy-consos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(uniproxyConso)))
            .andExpect(status().isCreated());

        // Validate the UniproxyConso in the database
        List<UniproxyConso> uniproxyConsoList = uniproxyConsoRepository.findAll();
        assertThat(uniproxyConsoList).hasSize(databaseSizeBeforeCreate + 1);
        UniproxyConso testUniproxyConso = uniproxyConsoList.get(uniproxyConsoList.size() - 1);
        assertThat(testUniproxyConso.getSubscriptionId()).isEqualTo(DEFAULT_SUBSCRIPTION_ID);
        assertThat(testUniproxyConso.getOrgId()).isEqualTo(DEFAULT_ORG_ID);
        assertThat(testUniproxyConso.getMonth()).isEqualTo(DEFAULT_MONTH);
        assertThat(testUniproxyConso.getYear()).isEqualTo(DEFAULT_YEAR);
        assertThat(testUniproxyConso.getType()).isEqualTo(DEFAULT_TYPE);
        assertThat(testUniproxyConso.getConso()).isEqualTo(DEFAULT_CONSO);
    }

    @Test
    @Transactional
    public void createUniproxyConsoWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = uniproxyConsoRepository.findAll().size();

        // Create the UniproxyConso with an existing ID
        uniproxyConso.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restUniproxyConsoMockMvc.perform(post("/api/uniproxy-consos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(uniproxyConso)))
            .andExpect(status().isBadRequest());

        // Validate the UniproxyConso in the database
        List<UniproxyConso> uniproxyConsoList = uniproxyConsoRepository.findAll();
        assertThat(uniproxyConsoList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllUniproxyConsos() throws Exception {
        // Initialize the database
        uniproxyConsoRepository.saveAndFlush(uniproxyConso);

        // Get all the uniproxyConsoList
        restUniproxyConsoMockMvc.perform(get("/api/uniproxy-consos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(uniproxyConso.getId().intValue())))
            .andExpect(jsonPath("$.[*].subscriptionId").value(hasItem(DEFAULT_SUBSCRIPTION_ID)))
            .andExpect(jsonPath("$.[*].orgId").value(hasItem(DEFAULT_ORG_ID)))
            .andExpect(jsonPath("$.[*].month").value(hasItem(DEFAULT_MONTH)))
            .andExpect(jsonPath("$.[*].year").value(hasItem(DEFAULT_YEAR)))
            .andExpect(jsonPath("$.[*].type").value(hasItem(DEFAULT_TYPE.toString())))
            .andExpect(jsonPath("$.[*].conso").value(hasItem(DEFAULT_CONSO)));
    }
    
    @Test
    @Transactional
    public void getUniproxyConso() throws Exception {
        // Initialize the database
        uniproxyConsoRepository.saveAndFlush(uniproxyConso);

        // Get the uniproxyConso
        restUniproxyConsoMockMvc.perform(get("/api/uniproxy-consos/{id}", uniproxyConso.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(uniproxyConso.getId().intValue()))
            .andExpect(jsonPath("$.subscriptionId").value(DEFAULT_SUBSCRIPTION_ID))
            .andExpect(jsonPath("$.orgId").value(DEFAULT_ORG_ID))
            .andExpect(jsonPath("$.month").value(DEFAULT_MONTH))
            .andExpect(jsonPath("$.year").value(DEFAULT_YEAR))
            .andExpect(jsonPath("$.type").value(DEFAULT_TYPE.toString()))
            .andExpect(jsonPath("$.conso").value(DEFAULT_CONSO));
    }
    @Test
    @Transactional
    public void getNonExistingUniproxyConso() throws Exception {
        // Get the uniproxyConso
        restUniproxyConsoMockMvc.perform(get("/api/uniproxy-consos/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateUniproxyConso() throws Exception {
        // Initialize the database
        uniproxyConsoService.save(uniproxyConso);

        int databaseSizeBeforeUpdate = uniproxyConsoRepository.findAll().size();

        // Update the uniproxyConso
        UniproxyConso updatedUniproxyConso = uniproxyConsoRepository.findById(uniproxyConso.getId()).get();
        // Disconnect from session so that the updates on updatedUniproxyConso are not directly saved in db
        em.detach(updatedUniproxyConso);
        updatedUniproxyConso
            .subscriptionId(UPDATED_SUBSCRIPTION_ID)
            .orgId(UPDATED_ORG_ID)
            .month(UPDATED_MONTH)
            .year(UPDATED_YEAR)
            .type(UPDATED_TYPE)
            .conso(UPDATED_CONSO);

        restUniproxyConsoMockMvc.perform(put("/api/uniproxy-consos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedUniproxyConso)))
            .andExpect(status().isOk());

        // Validate the UniproxyConso in the database
        List<UniproxyConso> uniproxyConsoList = uniproxyConsoRepository.findAll();
        assertThat(uniproxyConsoList).hasSize(databaseSizeBeforeUpdate);
        UniproxyConso testUniproxyConso = uniproxyConsoList.get(uniproxyConsoList.size() - 1);
        assertThat(testUniproxyConso.getSubscriptionId()).isEqualTo(UPDATED_SUBSCRIPTION_ID);
        assertThat(testUniproxyConso.getOrgId()).isEqualTo(UPDATED_ORG_ID);
        assertThat(testUniproxyConso.getMonth()).isEqualTo(UPDATED_MONTH);
        assertThat(testUniproxyConso.getYear()).isEqualTo(UPDATED_YEAR);
        assertThat(testUniproxyConso.getType()).isEqualTo(UPDATED_TYPE);
        assertThat(testUniproxyConso.getConso()).isEqualTo(UPDATED_CONSO);
    }

    @Test
    @Transactional
    public void updateNonExistingUniproxyConso() throws Exception {
        int databaseSizeBeforeUpdate = uniproxyConsoRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restUniproxyConsoMockMvc.perform(put("/api/uniproxy-consos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(uniproxyConso)))
            .andExpect(status().isBadRequest());

        // Validate the UniproxyConso in the database
        List<UniproxyConso> uniproxyConsoList = uniproxyConsoRepository.findAll();
        assertThat(uniproxyConsoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteUniproxyConso() throws Exception {
        // Initialize the database
        uniproxyConsoService.save(uniproxyConso);

        int databaseSizeBeforeDelete = uniproxyConsoRepository.findAll().size();

        // Delete the uniproxyConso
        restUniproxyConsoMockMvc.perform(delete("/api/uniproxy-consos/{id}", uniproxyConso.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<UniproxyConso> uniproxyConsoList = uniproxyConsoRepository.findAll();
        assertThat(uniproxyConsoList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
