package com.universign.universigncs.unistripe.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.universign.universigncs.unistripe.web.rest.TestUtil;

public class BillingStripeLinkTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(BillingStripeLink.class);
        BillingStripeLink billingStripeLink1 = new BillingStripeLink();
        billingStripeLink1.setId(1L);
        BillingStripeLink billingStripeLink2 = new BillingStripeLink();
        billingStripeLink2.setId(billingStripeLink1.getId());
        assertThat(billingStripeLink1).isEqualTo(billingStripeLink2);
        billingStripeLink2.setId(2L);
        assertThat(billingStripeLink1).isNotEqualTo(billingStripeLink2);
        billingStripeLink1.setId(null);
        assertThat(billingStripeLink1).isNotEqualTo(billingStripeLink2);
    }
}
