package pl.jreclaw.onlinelibrary.observer;

import pl.jreclaw.onlinelibrary.model.publication.Publication;

public interface PublicationObserver {
    void deletePublication(Publication publication);
}
