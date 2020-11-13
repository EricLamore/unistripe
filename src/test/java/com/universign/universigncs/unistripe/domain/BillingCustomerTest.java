package com.universign.universigncs.unistripe.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.universign.universigncs.unistripe.web.rest.TestUtil;

public class BillingCustomerTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(BillingCustomer.class);
        BillingCustomer billingCustomer1 = new BillingCustomer();
        billingCustomer1.setId(1L);
        BillingCustomer billingCustomer2 = new BillingCustomer();
        billingCustomer2.setId(billingCustomer1.getId());
        assertThat(billingCustomer1).isEqualTo(billingCustomer2);
        billingCustomer2.setId(2L);
        assertThat(billingCustomer1).isNotEqualTo(billingCustomer2);
        billingCustomer1.setId(null);
        assertThat(billingCustomer1).isNotEqualTo(billingCustomer2);
    }
}
