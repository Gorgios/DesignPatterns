package pl.jreclaw.onlinelibrary.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import pl.jreclaw.onlinelibrary.model.publication.Publication;
import pl.jreclaw.onlinelibrary.observer.AuthorObserver;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Author {
    @EqualsAndHashCode.Include
    String name;
    @EqualsAndHashCode.Include
    String surname;
    Set<Publication> publications;
    Set<AuthorObserver> observers;

    public Author(String name, String surname) {
        this.name = name;
        this.surname = surname;
        this.publications = new HashSet<>();
        this.observers = new HashSet<>();
    }

    public void addObserver(AuthorObserver authorObserver) {
        observers.add(authorObserver);
    }

    public void removeObserver(AuthorObserver authorObserver) {
        observers.remove(authorObserver);
    }

    public void listPublication(Publication publication) {
        publications.add(publication);
        Library.getInstance().addPublication(publication);
        for (AuthorObserver authorObserver : observers) {
            authorObserver.update(publication);
        }
    }

    @Override
    public String toString() {
        return name + " " + surname;
    }
}
