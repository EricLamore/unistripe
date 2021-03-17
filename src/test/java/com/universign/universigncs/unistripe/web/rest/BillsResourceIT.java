package com.universign.universigncs.unistripe.web.rest;

import com.universign.universigncs.unistripe.UnistripeApp;
import com.universign.universigncs.unistripe.domain.Bills;
import com.universign.universigncs.unistripe.repository.BillsRepository;
import com.universign.universigncs.unistripe.service.BillsService;

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
 * Integration tests for the {@link BillsResource} REST controller.
 */
@SpringBootTest(classes = UnistripeApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class BillsResourceIT {

    private static final String DEFAULT_CUSTOMER_ID = "AAAAAAAAAA";
    private static final String UPDATED_CUSTOMER_ID = "BBBBBBBBBB";

    private static final String DEFAULT_CUSTOMER_NAME = "AAAAAAAAAA";
    private static final String UPDATED_CUSTOMER_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_CUSTOMER_EMAIL = "AAAAAAAAAA";
    private static final String UPDATED_CUSTOMER_EMAIL = "BBBBBBBBBB";

    private static final Integer DEFAULT_YEAR = 1;
    private static final Integer UPDATED_YEAR = 2;

    private static final Integer DEFAULT_MONTH = 1;
    private static final Integer UPDATED_MONTH = 2;

    private static final Instant DEFAULT_PERIOD_START = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_PERIOD_START = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_PERIOD_END = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_PERIOD_END = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_CREATED = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_CREATED = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_SEND = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_SEND = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_DUE_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_DUE_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Integer DEFAULT_AMOUNT_DUE = 1;
    private static final Integer UPDATED_AMOUNT_DUE = 2;

    private static final Integer DEFAULT_TOTAL = 1;
    private static final Integer UPDATED_TOTAL = 2;

    private static final Integer DEFAULT_TAX = 1;
    private static final Integer UPDATED_TAX = 2;

    private static final Integer DEFAULT_TOTAL_DISCOUNT_AMOUNTS = 1;
    private static final Integer UPDATED_TOTAL_DISCOUNT_AMOUNTS = 2;

    private static final Integer DEFAULT_TOTAL_TAX_AMOUNTS = 1;
    private static final Integer UPDATED_TOTAL_TAX_AMOUNTS = 2;

    private static final String DEFAULT_URL = "AAAAAAAAAA";
    private static final String UPDATED_URL = "BBBBBBBBBB";

    private static final String DEFAULT_PDF_URL = "AAAAAAAAAA";
    private static final String UPDATED_PDF_URL = "BBBBBBBBBB";

    @Autowired
    private BillsRepository billsRepository;

    @Autowired
    private BillsService billsService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restBillsMockMvc;

    private Bills bills;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Bills createEntity(EntityManager em) {
        Bills bills = new Bills()
            .customerId(DEFAULT_CUSTOMER_ID)
            .customerName(DEFAULT_CUSTOMER_NAME)
            .customerEmail(DEFAULT_CUSTOMER_EMAIL)
            .year(DEFAULT_YEAR)
            .month(DEFAULT_MONTH)
            .periodStart(DEFAULT_PERIOD_START)
            .periodEnd(DEFAULT_PERIOD_END)
            .created(DEFAULT_CREATED)
            .send(DEFAULT_SEND)
            .dueDate(DEFAULT_DUE_DATE)
            .amountDue(DEFAULT_AMOUNT_DUE)
            .total(DEFAULT_TOTAL)
            .tax(DEFAULT_TAX)
            .totalDiscountAmounts(DEFAULT_TOTAL_DISCOUNT_AMOUNTS)
            .totalTaxAmounts(DEFAULT_TOTAL_TAX_AMOUNTS)
            .url(DEFAULT_URL)
            .pdfUrl(DEFAULT_PDF_URL);
        return bills;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Bills createUpdatedEntity(EntityManager em) {
        Bills bills = new Bills()
            .customerId(UPDATED_CUSTOMER_ID)
            .customerName(UPDATED_CUSTOMER_NAME)
            .customerEmail(UPDATED_CUSTOMER_EMAIL)
            .year(UPDATED_YEAR)
            .month(UPDATED_MONTH)
            .periodStart(UPDATED_PERIOD_START)
            .periodEnd(UPDATED_PERIOD_END)
            .created(UPDATED_CREATED)
            .send(UPDATED_SEND)
            .dueDate(UPDATED_DUE_DATE)
            .amountDue(UPDATED_AMOUNT_DUE)
            .total(UPDATED_TOTAL)
            .tax(UPDATED_TAX)
            .totalDiscountAmounts(UPDATED_TOTAL_DISCOUNT_AMOUNTS)
            .totalTaxAmounts(UPDATED_TOTAL_TAX_AMOUNTS)
            .url(UPDATED_URL)
            .pdfUrl(UPDATED_PDF_URL);
        return bills;
    }

    @BeforeEach
    public void initTest() {
        bills = createEntity(em);
    }

    @Test
    @Transactional
    public void createBills() throws Exception {
        int databaseSizeBeforeCreate = billsRepository.findAll().size();
        // Create the Bills
        restBillsMockMvc.perform(post("/api/bills")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(bills)))
            .andExpect(status().isCreated());

        // Validate the Bills in the database
        List<Bills> billsList = billsRepository.findAll();
        assertThat(billsList).hasSize(databaseSizeBeforeCreate + 1);
        Bills testBills = billsList.get(billsList.size() - 1);
        assertThat(testBills.getCustomerId()).isEqualTo(DEFAULT_CUSTOMER_ID);
        assertThat(testBills.getCustomerName()).isEqualTo(DEFAULT_CUSTOMER_NAME);
        assertThat(testBills.getCustomerEmail()).isEqualTo(DEFAULT_CUSTOMER_EMAIL);
        assertThat(testBills.getYear()).isEqualTo(DEFAULT_YEAR);
        assertThat(testBills.getMonth()).isEqualTo(DEFAULT_MONTH);
        assertThat(testBills.getPeriodStart()).isEqualTo(DEFAULT_PERIOD_START);
        assertThat(testBills.getPeriodEnd()).isEqualTo(DEFAULT_PERIOD_END);
        assertThat(testBills.getCreated()).isEqualTo(DEFAULT_CREATED);
        assertThat(testBills.getSend()).isEqualTo(DEFAULT_SEND);
        assertThat(testBills.getDueDate()).isEqualTo(DEFAULT_DUE_DATE);
        assertThat(testBills.getAmountDue()).isEqualTo(DEFAULT_AMOUNT_DUE);
        assertThat(testBills.getTotal()).isEqualTo(DEFAULT_TOTAL);
        assertThat(testBills.getTax()).isEqualTo(DEFAULT_TAX);
        assertThat(testBills.getTotalDiscountAmounts()).isEqualTo(DEFAULT_TOTAL_DISCOUNT_AMOUNTS);
        assertThat(testBills.getTotalTaxAmounts()).isEqualTo(DEFAULT_TOTAL_TAX_AMOUNTS);
        assertThat(testBills.getUrl()).isEqualTo(DEFAULT_URL);
        assertThat(testBills.getPdfUrl()).isEqualTo(DEFAULT_PDF_URL);
    }

    @Test
    @Transactional
    public void createBillsWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = billsRepository.findAll().size();

        // Create the Bills with an existing ID
        bills.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restBillsMockMvc.perform(post("/api/bills")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(bills)))
            .andExpect(status().isBadRequest());

        // Validate the Bills in the database
        List<Bills> billsList = billsRepository.findAll();
        assertThat(billsList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllBills() throws Exception {
        // Initialize the database
        billsRepository.saveAndFlush(bills);

        // Get all the billsList
        restBillsMockMvc.perform(get("/api/bills?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(bills.getId().intValue())))
            .andExpect(jsonPath("$.[*].customerId").value(hasItem(DEFAULT_CUSTOMER_ID)))
            .andExpect(jsonPath("$.[*].customerName").value(hasItem(DEFAULT_CUSTOMER_NAME)))
            .andExpect(jsonPath("$.[*].customerEmail").value(hasItem(DEFAULT_CUSTOMER_EMAIL)))
            .andExpect(jsonPath("$.[*].year").value(hasItem(DEFAULT_YEAR)))
            .andExpect(jsonPath("$.[*].month").value(hasItem(DEFAULT_MONTH)))
            .andExpect(jsonPath("$.[*].periodStart").value(hasItem(DEFAULT_PERIOD_START.toString())))
            .andExpect(jsonPath("$.[*].periodEnd").value(hasItem(DEFAULT_PERIOD_END.toString())))
            .andExpect(jsonPath("$.[*].created").value(hasItem(DEFAULT_CREATED.toString())))
            .andExpect(jsonPath("$.[*].send").value(hasItem(DEFAULT_SEND.toString())))
            .andExpect(jsonPath("$.[*].dueDate").value(hasItem(DEFAULT_DUE_DATE.toString())))
            .andExpect(jsonPath("$.[*].amountDue").value(hasItem(DEFAULT_AMOUNT_DUE)))
            .andExpect(jsonPath("$.[*].total").value(hasItem(DEFAULT_TOTAL)))
            .andExpect(jsonPath("$.[*].tax").value(hasItem(DEFAULT_TAX)))
            .andExpect(jsonPath("$.[*].totalDiscountAmounts").value(hasItem(DEFAULT_TOTAL_DISCOUNT_AMOUNTS)))
            .andExpect(jsonPath("$.[*].totalTaxAmounts").value(hasItem(DEFAULT_TOTAL_TAX_AMOUNTS)))
            .andExpect(jsonPath("$.[*].url").value(hasItem(DEFAULT_URL)))
            .andExpect(jsonPath("$.[*].pdfUrl").value(hasItem(DEFAULT_PDF_URL)));
    }
    
    @Test
    @Transactional
    public void getBills() throws Exception {
        // Initialize the database
        billsRepository.saveAndFlush(bills);

        // Get the bills
        restBillsMockMvc.perform(get("/api/bills/{id}", bills.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(bills.getId().intValue()))
            .andExpect(jsonPath("$.customerId").value(DEFAULT_CUSTOMER_ID))
            .andExpect(jsonPath("$.customerName").value(DEFAULT_CUSTOMER_NAME))
            .andExpect(jsonPath("$.customerEmail").value(DEFAULT_CUSTOMER_EMAIL))
            .andExpect(jsonPath("$.year").value(DEFAULT_YEAR))
            .andExpect(jsonPath("$.month").value(DEFAULT_MONTH))
            .andExpect(jsonPath("$.periodStart").value(DEFAULT_PERIOD_START.toString()))
            .andExpect(jsonPath("$.periodEnd").value(DEFAULT_PERIOD_END.toString()))
            .andExpect(jsonPath("$.created").value(DEFAULT_CREATED.toString()))
            .andExpect(jsonPath("$.send").value(DEFAULT_SEND.toString()))
            .andExpect(jsonPath("$.dueDate").value(DEFAULT_DUE_DATE.toString()))
            .andExpect(jsonPath("$.amountDue").value(DEFAULT_AMOUNT_DUE))
            .andExpect(jsonPath("$.total").value(DEFAULT_TOTAL))
            .andExpect(jsonPath("$.tax").value(DEFAULT_TAX))
            .andExpect(jsonPath("$.totalDiscountAmounts").value(DEFAULT_TOTAL_DISCOUNT_AMOUNTS))
            .andExpect(jsonPath("$.totalTaxAmounts").value(DEFAULT_TOTAL_TAX_AMOUNTS))
            .andExpect(jsonPath("$.url").value(DEFAULT_URL))
            .andExpect(jsonPath("$.pdfUrl").value(DEFAULT_PDF_URL));
    }
    @Test
    @Transactional
    public void getNonExistingBills() throws Exception {
        // Get the bills
        restBillsMockMvc.perform(get("/api/bills/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateBills() throws Exception {
        // Initialize the database
        billsService.save(bills);

        int databaseSizeBeforeUpdate = billsRepository.findAll().size();

        // Update the bills
        Bills updatedBills = billsRepository.findById(bills.getId()).get();
        // Disconnect from session so that the updates on updatedBills are not directly saved in db
        em.detach(updatedBills);
        updatedBills
            .customerId(UPDATED_CUSTOMER_ID)
            .customerName(UPDATED_CUSTOMER_NAME)
            .customerEmail(UPDATED_CUSTOMER_EMAIL)
            .year(UPDATED_YEAR)
            .month(UPDATED_MONTH)
            .periodStart(UPDATED_PERIOD_START)
            .periodEnd(UPDATED_PERIOD_END)
            .created(UPDATED_CREATED)
            .send(UPDATED_SEND)
            .dueDate(UPDATED_DUE_DATE)
            .amountDue(UPDATED_AMOUNT_DUE)
            .total(UPDATED_TOTAL)
            .tax(UPDATED_TAX)
            .totalDiscountAmounts(UPDATED_TOTAL_DISCOUNT_AMOUNTS)
            .totalTaxAmounts(UPDATED_TOTAL_TAX_AMOUNTS)
            .url(UPDATED_URL)
            .pdfUrl(UPDATED_PDF_URL);

        restBillsMockMvc.perform(put("/api/bills")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedBills)))
            .andExpect(status().isOk());

        // Validate the Bills in the database
        List<Bills> billsList = billsRepository.findAll();
        assertThat(billsList).hasSize(databaseSizeBeforeUpdate);
        Bills testBills = billsList.get(billsList.size() - 1);
        assertThat(testBills.getCustomerId()).isEqualTo(UPDATED_CUSTOMER_ID);
        assertThat(testBills.getCustomerName()).isEqualTo(UPDATED_CUSTOMER_NAME);
        assertThat(testBills.getCustomerEmail()).isEqualTo(UPDATED_CUSTOMER_EMAIL);
        assertThat(testBills.getYear()).isEqualTo(UPDATED_YEAR);
        assertThat(testBills.getMonth()).isEqualTo(UPDATED_MONTH);
        assertThat(testBills.getPeriodStart()).isEqualTo(UPDATED_PERIOD_START);
        assertThat(testBills.getPeriodEnd()).isEqualTo(UPDATED_PERIOD_END);
        assertThat(testBills.getCreated()).isEqualTo(UPDATED_CREATED);
        assertThat(testBills.getSend()).isEqualTo(UPDATED_SEND);
        assertThat(testBills.getDueDate()).isEqualTo(UPDATED_DUE_DATE);
        assertThat(testBills.getAmountDue()).isEqualTo(UPDATED_AMOUNT_DUE);
        assertThat(testBills.getTotal()).isEqualTo(UPDATED_TOTAL);
        assertThat(testBills.getTax()).isEqualTo(UPDATED_TAX);
        assertThat(testBills.getTotalDiscountAmounts()).isEqualTo(UPDATED_TOTAL_DISCOUNT_AMOUNTS);
        assertThat(testBills.getTotalTaxAmounts()).isEqualTo(UPDATED_TOTAL_TAX_AMOUNTS);
        assertThat(testBills.getUrl()).isEqualTo(UPDATED_URL);
        assertThat(testBills.getPdfUrl()).isEqualTo(UPDATED_PDF_URL);
    }

    @Test
    @Transactional
    public void updateNonExistingBills() throws Exception {
        int databaseSizeBeforeUpdate = billsRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restBillsMockMvc.perform(put("/api/bills")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(bills)))
            .andExpect(status().isBadRequest());

        // Validate the Bills in the database
        List<Bills> billsList = billsRepository.findAll();
        assertThat(billsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteBills() throws Exception {
        // Initialize the database
        billsService.save(bills);

        int databaseSizeBeforeDelete = billsRepository.findAll().size();

        // Delete the bills
        restBillsMockMvc.perform(delete("/api/bills/{id}", bills.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Bills> billsList = billsRepository.findAll();
        assertThat(billsList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
