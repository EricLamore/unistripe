package com.universign.universigncs.unistripe.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.universign.universigncs.unistripe.web.rest.TestUtil;

public class UniproxyConsoTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(UniproxyConso.class);
        UniproxyConso uniproxyConso1 = new UniproxyConso();
        uniproxyConso1.setId(1L);
        UniproxyConso uniproxyConso2 = new UniproxyConso();
        uniproxyConso2.setId(uniproxyConso1.getId());
        assertThat(uniproxyConso1).isEqualTo(uniproxyConso2);
        uniproxyConso2.setId(2L);
        assertThat(uniproxyConso1).isNotEqualTo(uniproxyConso2);
        uniproxyConso1.setId(null);
        assertThat(uniproxyConso1).isNotEqualTo(uniproxyConso2);
    }
}
