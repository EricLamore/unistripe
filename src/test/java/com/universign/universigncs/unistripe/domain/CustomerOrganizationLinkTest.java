package com.universign.universigncs.unistripe.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.universign.universigncs.unistripe.web.rest.TestUtil;

public class CustomerOrganizationLinkTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(CustomerOrganizationLink.class);
        CustomerOrganizationLink customerOrganizationLink1 = new CustomerOrganizationLink();
        customerOrganizationLink1.setId(1L);
        CustomerOrganizationLink customerOrganizationLink2 = new CustomerOrganizationLink();
        customerOrganizationLink2.setId(customerOrganizationLink1.getId());
        assertThat(customerOrganizationLink1).isEqualTo(customerOrganizationLink2);
        customerOrganizationLink2.setId(2L);
        assertThat(customerOrganizationLink1).isNotEqualTo(customerOrganizationLink2);
        customerOrganizationLink1.setId(null);
        assertThat(customerOrganizationLink1).isNotEqualTo(customerOrganizationLink2);
    }
}
