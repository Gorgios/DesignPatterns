package pl.jreclaw.onlinelibrary.facade;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.runners.MockitoJUnitRunner;
import pl.jreclaw.onlinelibrary.model.Author;
import pl.jreclaw.onlinelibrary.model.Library;
import pl.jreclaw.onlinelibrary.model.publication.Book;
import pl.jreclaw.onlinelibrary.model.publication.Publication;

import java.lang.reflect.Field;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class FacadeTest {

    @Mock
    Publication publication;

    @BeforeEach
    public void resetSingleton() throws SecurityException, NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
        Field instance = Library.class.getDeclaredField("instance");
        instance.setAccessible(true);
        instance.set(null, null);
    }

    @Test
    public void reportElementWithProfanity() {
        // arrange
        Library library = Library.getInstance();
        ReportPublicationFacade facade = new ReportPublicationFacade(library);
        library.addPublication(publication);
        when(publication.getContent()).thenReturn("fuck");

        // act
        boolean result = facade.reportPublication(publication);

        // assert
        assertThat(result).isTrue();
        assertThat(library.getPublications())
                .isEmpty();

    }

    @Test
    public void reportElementWithBadIdeology() {
        // arrange
        Library library = Library.getInstance();
        ReportPublicationFacade facade = new ReportPublicationFacade(library);
        library.addPublication(publication);
        when(publication.getContent()).thenReturn("badideology");

        // act
        boolean result = facade.reportPublication(publication);

        // assert
        assertThat(result).isTrue();
        assertThat(library.getPublications())
                .isEmpty();

    }

    @Test
    public void reportElementButUnsuccessful() {
        // arrange
        Library library = Library.getInstance();
        ReportPublicationFacade facade = new ReportPublicationFacade(library);
        library.addPublication(publication);
        when(publication.getContent()).thenReturn("test");

        // act
        boolean result = facade.reportPublication(publication);

        // assert
        assertThat(result).isFalse();
        assertThat(library.getPublications())
                .hasSize(1)
                .contains(publication);

     }

}
