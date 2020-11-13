package com.universign.universigncs.unistripe.web.rest;

import com.universign.universigncs.unistripe.UnistripeApp;
import com.universign.universigncs.unistripe.domain.EventUses;
import com.universign.universigncs.unistripe.repository.EventUsesRepository;
import com.universign.universigncs.unistripe.service.EventUsesService;

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

import com.universign.universigncs.unistripe.domain.enumeration.StatusType;
/**
 * Integration tests for the {@link EventUsesResource} REST controller.
 */
@SpringBootTest(classes = UnistripeApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class EventUsesResourceIT {

    private static final String DEFAULT_CUSOTMER_ID = "AAAAAAAAAA";
    private static final String UPDATED_CUSOTMER_ID = "BBBBBBBBBB";

    private static final String DEFAULT_CUSTOMER_NAME = "AAAAAAAAAA";
    private static final String UPDATED_CUSTOMER_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_SUBSCRIPTION_ID = "AAAAAAAAAA";
    private static final String UPDATED_SUBSCRIPTION_ID = "BBBBBBBBBB";

    private static final String DEFAULT_SUBSCRIPTION_NAME = "AAAAAAAAAA";
    private static final String UPDATED_SUBSCRIPTION_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_PRODUCT_ID = "AAAAAAAAAA";
    private static final String UPDATED_PRODUCT_ID = "BBBBBBBBBB";

    private static final String DEFAULT_PRODUCT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_PRODUCT_NAME = "BBBBBBBBBB";

    private static final Integer DEFAULT_MONTH = 1;
    private static final Integer UPDATED_MONTH = 2;

    private static final Integer DEFAULT_YEAR = 1;
    private static final Integer UPDATED_YEAR = 2;

    private static final StatusType DEFAULT_STATUS = StatusType.NONE;
    private static final StatusType UPDATED_STATUS = StatusType.ON_GOING;

    private static final String DEFAULT_ERROR = "AAAAAAAAAA";
    private static final String UPDATED_ERROR = "BBBBBBBBBB";

    private static final Long DEFAULT_TOTAL = 1L;
    private static final Long UPDATED_TOTAL = 2L;

    @Autowired
    private EventUsesRepository eventUsesRepository;

    @Autowired
    private EventUsesService eventUsesService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restEventUsesMockMvc;

    private EventUses eventUses;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static EventUses createEntity(EntityManager em) {
        EventUses eventUses = new EventUses()
            .cusotmerId(DEFAULT_CUSOTMER_ID)
            .customerName(DEFAULT_CUSTOMER_NAME)
            .subscriptionId(DEFAULT_SUBSCRIPTION_ID)
            .subscriptionName(DEFAULT_SUBSCRIPTION_NAME)
            .productId(DEFAULT_PRODUCT_ID)
            .productName(DEFAULT_PRODUCT_NAME)
            .month(DEFAULT_MONTH)
            .year(DEFAULT_YEAR)
            .status(DEFAULT_STATUS)
            .error(DEFAULT_ERROR)
            .total(DEFAULT_TOTAL);
        return eventUses;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static EventUses createUpdatedEntity(EntityManager em) {
        EventUses eventUses = new EventUses()
            .cusotmerId(UPDATED_CUSOTMER_ID)
            .customerName(UPDATED_CUSTOMER_NAME)
            .subscriptionId(UPDATED_SUBSCRIPTION_ID)
            .subscriptionName(UPDATED_SUBSCRIPTION_NAME)
            .productId(UPDATED_PRODUCT_ID)
            .productName(UPDATED_PRODUCT_NAME)
            .month(UPDATED_MONTH)
            .year(UPDATED_YEAR)
            .status(UPDATED_STATUS)
            .error(UPDATED_ERROR)
            .total(UPDATED_TOTAL);
        return eventUses;
    }

    @BeforeEach
    public void initTest() {
        eventUses = createEntity(em);
    }

    @Test
    @Transactional
    public void createEventUses() throws Exception {
        int databaseSizeBeforeCreate = eventUsesRepository.findAll().size();
        // Create the EventUses
        restEventUsesMockMvc.perform(post("/api/event-uses")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(eventUses)))
            .andExpect(status().isCreated());

        // Validate the EventUses in the database
        List<EventUses> eventUsesList = eventUsesRepository.findAll();
        assertThat(eventUsesList).hasSize(databaseSizeBeforeCreate + 1);
        EventUses testEventUses = eventUsesList.get(eventUsesList.size() - 1);
        assertThat(testEventUses.getCusotmerId()).isEqualTo(DEFAULT_CUSOTMER_ID);
        assertThat(testEventUses.getCustomerName()).isEqualTo(DEFAULT_CUSTOMER_NAME);
        assertThat(testEventUses.getSubscriptionId()).isEqualTo(DEFAULT_SUBSCRIPTION_ID);
        assertThat(testEventUses.getSubscriptionName()).isEqualTo(DEFAULT_SUBSCRIPTION_NAME);
        assertThat(testEventUses.getProductId()).isEqualTo(DEFAULT_PRODUCT_ID);
        assertThat(testEventUses.getProductName()).isEqualTo(DEFAULT_PRODUCT_NAME);
        assertThat(testEventUses.getMonth()).isEqualTo(DEFAULT_MONTH);
        assertThat(testEventUses.getYear()).isEqualTo(DEFAULT_YEAR);
        assertThat(testEventUses.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testEventUses.getError()).isEqualTo(DEFAULT_ERROR);
        assertThat(testEventUses.getTotal()).isEqualTo(DEFAULT_TOTAL);
    }

    @Test
    @Transactional
    public void createEventUsesWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = eventUsesRepository.findAll().size();

        // Create the EventUses with an existing ID
        eventUses.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restEventUsesMockMvc.perform(post("/api/event-uses")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(eventUses)))
            .andExpect(status().isBadRequest());

        // Validate the EventUses in the database
        List<EventUses> eventUsesList = eventUsesRepository.findAll();
        assertThat(eventUsesList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllEventUses() throws Exception {
        // Initialize the database
        eventUsesRepository.saveAndFlush(eventUses);

        // Get all the eventUsesList
        restEventUsesMockMvc.perform(get("/api/event-uses?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(eventUses.getId().intValue())))
            .andExpect(jsonPath("$.[*].cusotmerId").value(hasItem(DEFAULT_CUSOTMER_ID)))
            .andExpect(jsonPath("$.[*].customerName").value(hasItem(DEFAULT_CUSTOMER_NAME)))
            .andExpect(jsonPath("$.[*].subscriptionId").value(hasItem(DEFAULT_SUBSCRIPTION_ID)))
            .andExpect(jsonPath("$.[*].subscriptionName").value(hasItem(DEFAULT_SUBSCRIPTION_NAME)))
            .andExpect(jsonPath("$.[*].productId").value(hasItem(DEFAULT_PRODUCT_ID)))
            .andExpect(jsonPath("$.[*].productName").value(hasItem(DEFAULT_PRODUCT_NAME)))
            .andExpect(jsonPath("$.[*].month").value(hasItem(DEFAULT_MONTH)))
            .andExpect(jsonPath("$.[*].year").value(hasItem(DEFAULT_YEAR)))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.toString())))
            .andExpect(jsonPath("$.[*].error").value(hasItem(DEFAULT_ERROR)))
            .andExpect(jsonPath("$.[*].total").value(hasItem(DEFAULT_TOTAL.intValue())));
    }
    
    @Test
    @Transactional
    public void getEventUses() throws Exception {
        // Initialize the database
        eventUsesRepository.saveAndFlush(eventUses);

        // Get the eventUses
        restEventUsesMockMvc.perform(get("/api/event-uses/{id}", eventUses.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(eventUses.getId().intValue()))
            .andExpect(jsonPath("$.cusotmerId").value(DEFAULT_CUSOTMER_ID))
            .andExpect(jsonPath("$.customerName").value(DEFAULT_CUSTOMER_NAME))
            .andExpect(jsonPath("$.subscriptionId").value(DEFAULT_SUBSCRIPTION_ID))
            .andExpect(jsonPath("$.subscriptionName").value(DEFAULT_SUBSCRIPTION_NAME))
            .andExpect(jsonPath("$.productId").value(DEFAULT_PRODUCT_ID))
            .andExpect(jsonPath("$.productName").value(DEFAULT_PRODUCT_NAME))
            .andExpect(jsonPath("$.month").value(DEFAULT_MONTH))
            .andExpect(jsonPath("$.year").value(DEFAULT_YEAR))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS.toString()))
            .andExpect(jsonPath("$.error").value(DEFAULT_ERROR))
            .andExpect(jsonPath("$.total").value(DEFAULT_TOTAL.intValue()));
    }
    @Test
    @Transactional
    public void getNonExistingEventUses() throws Exception {
        // Get the eventUses
        restEventUsesMockMvc.perform(get("/api/event-uses/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateEventUses() throws Exception {
        // Initialize the database
        eventUsesService.save(eventUses);

        int databaseSizeBeforeUpdate = eventUsesRepository.findAll().size();

        // Update the eventUses
        EventUses updatedEventUses = eventUsesRepository.findById(eventUses.getId()).get();
        // Disconnect from session so that the updates on updatedEventUses are not directly saved in db
        em.detach(updatedEventUses);
        updatedEventUses
            .cusotmerId(UPDATED_CUSOTMER_ID)
            .customerName(UPDATED_CUSTOMER_NAME)
            .subscriptionId(UPDATED_SUBSCRIPTION_ID)
            .subscriptionName(UPDATED_SUBSCRIPTION_NAME)
            .productId(UPDATED_PRODUCT_ID)
            .productName(UPDATED_PRODUCT_NAME)
            .month(UPDATED_MONTH)
            .year(UPDATED_YEAR)
            .status(UPDATED_STATUS)
            .error(UPDATED_ERROR)
            .total(UPDATED_TOTAL);

        restEventUsesMockMvc.perform(put("/api/event-uses")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedEventUses)))
            .andExpect(status().isOk());

        // Validate the EventUses in the database
        List<EventUses> eventUsesList = eventUsesRepository.findAll();
        assertThat(eventUsesList).hasSize(databaseSizeBeforeUpdate);
        EventUses testEventUses = eventUsesList.get(eventUsesList.size() - 1);
        assertThat(testEventUses.getCusotmerId()).isEqualTo(UPDATED_CUSOTMER_ID);
        assertThat(testEventUses.getCustomerName()).isEqualTo(UPDATED_CUSTOMER_NAME);
        assertThat(testEventUses.getSubscriptionId()).isEqualTo(UPDATED_SUBSCRIPTION_ID);
        assertThat(testEventUses.getSubscriptionName()).isEqualTo(UPDATED_SUBSCRIPTION_NAME);
        assertThat(testEventUses.getProductId()).isEqualTo(UPDATED_PRODUCT_ID);
        assertThat(testEventUses.getProductName()).isEqualTo(UPDATED_PRODUCT_NAME);
        assertThat(testEventUses.getMonth()).isEqualTo(UPDATED_MONTH);
        assertThat(testEventUses.getYear()).isEqualTo(UPDATED_YEAR);
        assertThat(testEventUses.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testEventUses.getError()).isEqualTo(UPDATED_ERROR);
        assertThat(testEventUses.getTotal()).isEqualTo(UPDATED_TOTAL);
    }

    @Test
    @Transactional
    public void updateNonExistingEventUses() throws Exception {
        int databaseSizeBeforeUpdate = eventUsesRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restEventUsesMockMvc.perform(put("/api/event-uses")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(eventUses)))
            .andExpect(status().isBadRequest());

        // Validate the EventUses in the database
        List<EventUses> eventUsesList = eventUsesRepository.findAll();
        assertThat(eventUsesList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteEventUses() throws Exception {
        // Initialize the database
        eventUsesService.save(eventUses);

        int databaseSizeBeforeDelete = eventUsesRepository.findAll().size();

        // Delete the eventUses
        restEventUsesMockMvc.perform(delete("/api/event-uses/{id}", eventUses.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<EventUses> eventUsesList = eventUsesRepository.findAll();
        assertThat(eventUsesList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
