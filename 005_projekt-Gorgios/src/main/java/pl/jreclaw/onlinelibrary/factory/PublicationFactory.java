package pl.jreclaw.onlinelibrary.factory;

import pl.jreclaw.onlinelibrary.model.Author;
import pl.jreclaw.onlinelibrary.model.publication.Publication;

public abstract class PublicationFactory {
    public abstract Publication getPublication(Author author, String title, String content, String category);

}
