package tn.mdweb.aminoacidopathies.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import tn.mdweb.aminoacidopathies.web.rest.TestUtil;

class PathologieTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Pathologie.class);
        Pathologie pathologie1 = new Pathologie();
        pathologie1.setId(1L);
        Pathologie pathologie2 = new Pathologie();
        pathologie2.setId(pathologie1.getId());
        assertThat(pathologie1).isEqualTo(pathologie2);
        pathologie2.setId(2L);
        assertThat(pathologie1).isNotEqualTo(pathologie2);
        pathologie1.setId(null);
        assertThat(pathologie1).isNotEqualTo(pathologie2);
    }
}
