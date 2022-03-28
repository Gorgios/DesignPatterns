package pl.jreclaw.onlinelibrary.model.publication;

import lombok.*;
import pl.jreclaw.onlinelibrary.model.Author;
import pl.jreclaw.onlinelibrary.model.Category;
import pl.jreclaw.onlinelibrary.observer.PublicationObserver;
import pl.jreclaw.onlinelibrary.visitor.PublicationVisitor;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public abstract class Publication {
    @EqualsAndHashCode.Include
    private Author author;
    @EqualsAndHashCode.Include
    private String title;
    private String content;
    private LocalDateTime publicationTime;
    private Category category;
    private Set<String> tags;
    private Set<PublicationObserver> observers;

    public Publication() {
        observers = new HashSet<>();
    }

    public void addObserver(PublicationObserver publicationObserver) {
        observers.add(publicationObserver);
    }

    public void removeObserver(PublicationObserver publicationObserver) {
        observers.remove(publicationObserver);
    }

    public void deletePublication() {
        observers.forEach(publicationObserver -> publicationObserver.deletePublication(this));
    }

    @Override
    public String toString() {
        return String.format("Title: %s, Author: %s, Category: %s", title, author, category);
    }

    public abstract String accept(PublicationVisitor visitor);
}
