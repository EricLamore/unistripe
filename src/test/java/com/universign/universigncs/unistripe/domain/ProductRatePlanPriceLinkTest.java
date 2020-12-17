package com.universign.universigncs.unistripe.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.universign.universigncs.unistripe.web.rest.TestUtil;

public class ProductRatePlanPriceLinkTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ProductRatePlanPriceLink.class);
        ProductRatePlanPriceLink productRatePlanPriceLink1 = new ProductRatePlanPriceLink();
        productRatePlanPriceLink1.setId(1L);
        ProductRatePlanPriceLink productRatePlanPriceLink2 = new ProductRatePlanPriceLink();
        productRatePlanPriceLink2.setId(productRatePlanPriceLink1.getId());
        assertThat(productRatePlanPriceLink1).isEqualTo(productRatePlanPriceLink2);
        productRatePlanPriceLink2.setId(2L);
        assertThat(productRatePlanPriceLink1).isNotEqualTo(productRatePlanPriceLink2);
        productRatePlanPriceLink1.setId(null);
        assertThat(productRatePlanPriceLink1).isNotEqualTo(productRatePlanPriceLink2);
    }
}
