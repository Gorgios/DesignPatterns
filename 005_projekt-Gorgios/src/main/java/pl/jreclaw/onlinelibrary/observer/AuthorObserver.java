package pl.jreclaw.onlinelibrary.observer;

import pl.jreclaw.onlinelibrary.model.publication.Publication;

public interface AuthorObserver {
    void update(Publication publication);
}
