package pl.jreclaw.onlinelibrary.adapter;

import pl.jreclaw.onlinelibrary.model.Author;
import pl.jreclaw.onlinelibrary.model.publication.Publication;

import java.util.List;

public interface LibraryAdapter {
    String showAllPublications();
    String showAllBooks();
    String showAllPoems();
    String showAllArticles();
    String showPublicationsByAuthor(String name, String surname);
    String showPublicationsByAuthor(Author author);
    String showPublicationsByTitle(String title);
    String showPublicationsByTitleContains(String contains);
    String showPublicationsByTags(String... tags);
    String showPublicationsByCategory(String category);
    String showDetailedPublications();

    boolean reportPublication(Publication publication);
}
