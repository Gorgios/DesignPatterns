package pl.jreclaw.onlinelibrary.adapter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import pl.jreclaw.onlinelibrary.factory.FactoryProvider;
import pl.jreclaw.onlinelibrary.model.Author;
import pl.jreclaw.onlinelibrary.model.Library;
import pl.jreclaw.onlinelibrary.model.publication.Publication;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class AdapterTest {
    private static final String BOOK = "BOOK";
    private static final String POEM = "POEM";
    private static final String ARTICLE = "ARTICLE";
    private static final String TEST_NAME = "TEST";
    LibraryAdapter libraryAdapter;

    Publication book;
    Publication poem;
    Publication article;
    Author author;

    @BeforeEach
    public void setUp() throws SecurityException, NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
        Field instance = Library.class.getDeclaredField("instance");
        instance.setAccessible(true);
        instance.set(null, null);

        libraryAdapter = new LibraryAdapterImpl();
        author = new Author(TEST_NAME, TEST_NAME);
        book = FactoryProvider.getBookFactory().getPublication(author, BOOK, BOOK, BOOK);
        poem = FactoryProvider.getPoemFactory().getPublication(author, POEM, POEM, POEM);
        article = FactoryProvider.getArticleFactory().getPublication(author, ARTICLE, ARTICLE, ARTICLE);
    }

    @Test
    public void getAllPoems() {
        // act
        String result = libraryAdapter.showAllPoems();

        // assert
        assertThat(result)
                .contains(POEM)
                .doesNotContain(BOOK)
                .doesNotContain(ARTICLE);
    }

    @Test
    public void getAllBooks() {
        // act
        String result = libraryAdapter.showAllBooks();

        // assert
        assertThat(result)
                .contains(BOOK)
                .doesNotContain(POEM)
                .doesNotContain(ARTICLE);
    }

    @Test
    public void getAllArticles() {
        // act
        String result = libraryAdapter.showAllArticles();

        // assert
        assertThat(result)
                .contains(ARTICLE)
                .doesNotContain(BOOK)
                .doesNotContain(POEM);
    }

    @Test
    public void getAllPublications() {
        // act
        String result = libraryAdapter.showAllPublications();

        // assert
        assertThat(result)
                .contains(ARTICLE)
                .contains(POEM)
                .contains(BOOK);
    }

    @Test
    public void showPublicationsByExistingAuthor() {
        // act
        String result = libraryAdapter.showPublicationsByAuthor(author);

        // assert
        assertThat(result)
                .contains(ARTICLE)
                .contains(POEM)
                .contains(BOOK);
    }

    @Test
    public void showPublicationsByExistingAuthorNameAndSurname() {
        // act
        String result = libraryAdapter.showPublicationsByAuthor(TEST_NAME, TEST_NAME);

        // assert
        assertThat(result)
                .contains(ARTICLE)
                .contains(POEM)
                .contains(BOOK);
    }

    @Test
    public void showPublicationsByNOTExistingAuthorNameAndSurname() {
        // act
        String result = libraryAdapter.showPublicationsByAuthor("NOT_EXIST", TEST_NAME);

        // assert
        assertThat(result)
                .doesNotContain(ARTICLE)
                .doesNotContain(BOOK)
                .doesNotContain(POEM);
    }

    @Test
    public void showPublicationsByTags() {
        // arrange
        article.setTags(Set.of("TAG1","TAG2","TAG3"));
        poem.setTags(Set.of("TAG1"));

        // act
        String result = libraryAdapter.showPublicationsByTags("TAG1");

        // assert
        assertThat(result)
                .contains(ARTICLE)
                .doesNotContain(BOOK)
                .contains(POEM);
    }

    @Test
    public void showPublicationsByTile() {
        // act
        String result = libraryAdapter.showPublicationsByTitle(ARTICLE);

        // assert
        assertThat(result)
                .contains(ARTICLE)
                .doesNotContain(BOOK)
                .doesNotContain(POEM);
    }

    @Test
    public void showPublicationsByTileContain() {
        // act
        String result = libraryAdapter.showPublicationsByTitleContains("ART");

        // assert
        assertThat(result)
                .contains(ARTICLE)
                .doesNotContain(BOOK)
                .doesNotContain(POEM);
    }

    @Test
    public void showPublicationsByCategory() {
        // act
        String result = libraryAdapter.showPublicationsByCategory(ARTICLE);

        // assert
        assertThat(result)
                .contains(ARTICLE)
                .doesNotContain(BOOK)
                .doesNotContain(POEM);
    }

    @Test
    public void showDetailedPublications() {
        // act
        String result = libraryAdapter.showDetailedPublications();

        // assert
        assertThat(result)
                .contains(ARTICLE)
                .contains(POEM)
                .contains(BOOK)
                .contains("\nNumber of pages:")
                .contains("\nNumber of lines:")
                .contains("\nMinutes to read:");
    }

}
