package com.universign.universigncs.unistripe.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.universign.universigncs.unistripe.web.rest.TestUtil;

public class EventUsesTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(EventUses.class);
        EventUses eventUses1 = new EventUses();
        eventUses1.setId(1L);
        EventUses eventUses2 = new EventUses();
        eventUses2.setId(eventUses1.getId());
        assertThat(eventUses1).isEqualTo(eventUses2);
        eventUses2.setId(2L);
        assertThat(eventUses1).isNotEqualTo(eventUses2);
        eventUses1.setId(null);
        assertThat(eventUses1).isNotEqualTo(eventUses2);
    }
}
