package com.universign.universigncs.unistripe.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.universign.universigncs.unistripe.web.rest.TestUtil;

public class StripeSubscriptionTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(StripeSubscription.class);
        StripeSubscription stripeSubscription1 = new StripeSubscription();
        stripeSubscription1.setId(1L);
        StripeSubscription stripeSubscription2 = new StripeSubscription();
        stripeSubscription2.setId(stripeSubscription1.getId());
        assertThat(stripeSubscription1).isEqualTo(stripeSubscription2);
        stripeSubscription2.setId(2L);
        assertThat(stripeSubscription1).isNotEqualTo(stripeSubscription2);
        stripeSubscription1.setId(null);
        assertThat(stripeSubscription1).isNotEqualTo(stripeSubscription2);
    }
}
