package pl.jreclaw.onlinelibrary.visitor;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.jreclaw.onlinelibrary.factory.FactoryProvider;
import pl.jreclaw.onlinelibrary.model.Author;
import pl.jreclaw.onlinelibrary.model.publication.Article;
import pl.jreclaw.onlinelibrary.model.publication.Book;
import pl.jreclaw.onlinelibrary.model.publication.Poem;
import pl.jreclaw.onlinelibrary.model.publication.Publication;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class VisitorTest {

    @Mock
    Author author;
    Publication book;
    Publication poem;
    Publication article;

    @BeforeEach
    public void setUp() {
        doNothing().when(author).listPublication(any(Publication.class));
        book = FactoryProvider.getBookFactory().getPublication(author, "test", "test", "test");
        poem = FactoryProvider.getPoemFactory().getPublication(author, "test2", "test2", "test2");
        article = FactoryProvider.getArticleFactory().getPublication(author, "test3", "test3", "test3");
    }

    @Test
    public void visitBook() {
        // arrange
        PublicationVisitor publicationVisitor = new PublicationVisitorImpl();

        // act
        String result = book.accept(publicationVisitor);

        // assert
        assertThat(result)
                .contains("\nNumber of pages: " + 1);
    }

    @Test
    public void visitArticle() {
        // arrange
        PublicationVisitor publicationVisitor = new PublicationVisitorImpl();

        // act
        String result = article.accept(publicationVisitor);

        // assert
        assertThat(result)
                .contains("\nMinutes to read: " + 1);
    }

    @Test
    public void visitPoem() {
        // arrange
        PublicationVisitor publicationVisitor = new PublicationVisitorImpl();

        // act
        String result = poem.accept(publicationVisitor);

        // assert
        assertThat(result)
                .contains("\nNumber of lines: " + 1);
    }

}
