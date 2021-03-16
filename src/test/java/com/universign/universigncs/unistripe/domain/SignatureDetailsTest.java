package com.universign.universigncs.unistripe.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.universign.universigncs.unistripe.web.rest.TestUtil;

public class SignatureDetailsTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(SignatureDetails.class);
        SignatureDetails signatureDetails1 = new SignatureDetails();
        signatureDetails1.setId(1L);
        SignatureDetails signatureDetails2 = new SignatureDetails();
        signatureDetails2.setId(signatureDetails1.getId());
        assertThat(signatureDetails1).isEqualTo(signatureDetails2);
        signatureDetails2.setId(2L);
        assertThat(signatureDetails1).isNotEqualTo(signatureDetails2);
        signatureDetails1.setId(null);
        assertThat(signatureDetails1).isNotEqualTo(signatureDetails2);
    }
}
