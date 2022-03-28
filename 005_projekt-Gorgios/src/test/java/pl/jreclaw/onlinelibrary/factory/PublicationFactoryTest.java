package pl.jreclaw.onlinelibrary.factory;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.jreclaw.onlinelibrary.model.Author;
import pl.jreclaw.onlinelibrary.model.Category;
import pl.jreclaw.onlinelibrary.model.publication.Article;
import pl.jreclaw.onlinelibrary.model.publication.Book;
import pl.jreclaw.onlinelibrary.model.publication.Poem;
import pl.jreclaw.onlinelibrary.model.publication.Publication;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;

@ExtendWith(MockitoExtension.class)
public class PublicationFactoryTest {

    private static final String TITLE = "test_title";
    private static final String CONTENT = "test_content";
    private static final String CATEGORY = "test_category";

    @Mock
    Author author;

    @BeforeEach
    public void setUp() {
        doNothing().when(author).listPublication(any(Publication.class));
    }

    @Test
    public void testBookFactory() {
        // arrange
        PublicationFactory factory = FactoryProvider.getBookFactory();

        // act
        Publication publication = factory.getPublication(author, TITLE, CONTENT, CATEGORY);

        // assert
        assertThat(publication)
                .isNotNull()
                .isInstanceOf(Book.class)
                .matches(pub -> TITLE.equals(pub.getTitle())
                        && CONTENT.equals(pub.getContent())
                        && Category.of(CATEGORY).equals(pub.getCategory()));
    }

    @Test
    public void testArticleFactory() {
        // arrange
        PublicationFactory factory = FactoryProvider.getArticleFactory();

        // act
        Publication publication = factory.getPublication(author, TITLE, CONTENT, CATEGORY);

        // assert
        assertThat(publication)
                .isNotNull()
                .isInstanceOf(Article.class)
                .matches(pub -> TITLE.equals(pub.getTitle())
                        && CONTENT.equals(pub.getContent())
                        && Category.of(CATEGORY).equals(pub.getCategory()));
    }

    @Test
    public void testPoemFactory() {
        // arrange
        PublicationFactory factory = FactoryProvider.getPoemFactory();

        // act
        Publication publication = factory.getPublication(author, TITLE, CONTENT, CATEGORY);

        // assert
        assertThat(publication)
                .isNotNull()
                .isInstanceOf(Poem.class)
                .matches(pub -> TITLE.equals(pub.getTitle())
                        && CONTENT.equals(pub.getContent())
                        && Category.of(CATEGORY).equals(pub.getCategory()));
    }
}
