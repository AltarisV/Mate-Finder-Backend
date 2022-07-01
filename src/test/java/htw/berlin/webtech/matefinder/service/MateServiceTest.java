package htw.berlin.webtech.matefinder.service;

import htw.berlin.webtech.matefinder.persistence.MateRepo;
import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class MateServiceTest implements WithAssertions {

    @Mock
    private MateRepo repo;

    @InjectMocks
    private MateService underTest;

    Long givenId = 111L;

    @Test
    @DisplayName("should return true if deleted successfully")
    void deleteTest() {

        // given
        doReturn(true).when(repo).existsById(givenId);

        // when
        boolean result = underTest.deleteById(givenId);

        // then
        verify(repo).deleteById(givenId);
        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("false if deletion-marked object does not exist")
    void noExistDeleteTest() {
        // given
        doReturn(false).when(repo).existsById(givenId);

        // when
        boolean result = underTest.deleteById(givenId);

        // then
        verifyNoMoreInteractions(repo);
        assertThat(result).isFalse();
    }
}
