package pl.jreclaw.onlinelibrary.observer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.jreclaw.onlinelibrary.model.Author;
import pl.jreclaw.onlinelibrary.model.Client;
import pl.jreclaw.onlinelibrary.model.publication.Publication;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class ObserverTest {

    @Spy
    Publication publication;

    Author author;
    Client client;

    @BeforeEach
    public void setUp() {
        author = new Author("TEST", "TEST");
        client = new Client("TEST");
    }

    @Test
    public void addPublicationToPublicationsToReadWhenFollowedAuthorAddNewPublication(){
        // arrange
        client.followAuthor(author);

        // act
        author.listPublication(publication);

        // assert
        assertThat(client.getPublicationsToRead())
                .isNotNull()
                .hasSize(1)
                .contains(publication);
    }

    @Test
    public void deletePublicationFromFavouritesWhenIsBanned(){
        // arrange
        client.addPublicationToFavourites(publication);

        // act
        publication.deletePublication();

        // assert
        assertThat(client.getFavourites())
                .isEmpty();
    }

    @Test
    public void deletePublicationFromPublicationsToReadWhenIsBanned(){
        // arrange
        client.addPublicationTorRead(publication);

        // act
        publication.deletePublication();

        // assert
        assertThat(client.getPublicationsToRead())
                .isEmpty();
    }

}
