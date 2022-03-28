package pl.jreclaw.onlinelibrary.model;

import lombok.Data;
import pl.jreclaw.onlinelibrary.model.publication.Publication;
import pl.jreclaw.onlinelibrary.observer.AuthorObserver;
import pl.jreclaw.onlinelibrary.observer.PublicationObserver;

import java.util.ArrayList;
import java.util.List;

@Data
public class Client implements AuthorObserver, PublicationObserver {
    String username;
    List<Publication> publicationsToRead;
    List<Publication> favourites;

    public Client(String username) {
        this.username = username;
        this.publicationsToRead = new ArrayList<>();
        this.favourites = new ArrayList<>();
    }

    @Override
    public void update(Publication publication) {
        addPublicationTorRead(publication);
    }

    public void addPublicationTorRead(Publication publication) {
        publicationsToRead.add(publication);
        publication.addObserver(this);
    }

    public void removePublicationFromToRead(Publication publication) {
        publicationsToRead.remove(publication);
        publication.removeObserver(this);
    }
    public void followAuthor(Author author) {
        author.addObserver(this);
    }

    public void unfollowAuthor(Author author) {
        author.removeObserver(this);
    }

    public void addPublicationToFavourites(Publication publication) {
        favourites.add(publication);
        publication.addObserver(this);
    }

    public void removePublicationFromFavourites(Publication publication) {
        favourites.remove(publication);
        publication.removeObserver(this);
    }

    @Override
    public void deletePublication(Publication publication) {
        favourites.remove(publication);
        publicationsToRead.remove(publication);
    }
}
