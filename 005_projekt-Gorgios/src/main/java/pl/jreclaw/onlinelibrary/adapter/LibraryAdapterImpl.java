package pl.jreclaw.onlinelibrary.adapter;

import pl.jreclaw.onlinelibrary.model.Author;
import pl.jreclaw.onlinelibrary.model.Category;
import pl.jreclaw.onlinelibrary.model.Library;
import pl.jreclaw.onlinelibrary.model.publication.Article;
import pl.jreclaw.onlinelibrary.model.publication.Book;
import pl.jreclaw.onlinelibrary.model.publication.Poem;
import pl.jreclaw.onlinelibrary.model.publication.Publication;
import pl.jreclaw.onlinelibrary.visitor.PublicationVisitor;
import pl.jreclaw.onlinelibrary.visitor.PublicationVisitorImpl;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public class LibraryAdapterImpl implements LibraryAdapter {

    Library library = Library.getInstance();

    @Override
    public String showAllPublications() {
        return converSetOfPublicationToString(library.getPublications());
    }

    @Override
    public String showAllBooks() {
        Set<Publication> books = library.getPublications().stream().
                filter(publication -> publication.getClass().equals(Book.class)).collect(Collectors.toSet());
        return converSetOfPublicationToString(books);
    }

    @Override
    public String showAllPoems() {
        Set<Publication> poem = library.getPublications().stream().
                filter(publication -> publication.getClass().equals(Poem.class)).collect(Collectors.toSet());
        return converSetOfPublicationToString(poem);
    }

    @Override
    public String showAllArticles() {
        Set<Publication> articles = library.getPublications().stream().
                filter(publication -> publication.getClass().equals(Article.class)).collect(Collectors.toSet());
        return converSetOfPublicationToString(articles);
    }

    @Override
    public String showPublicationsByAuthor(String name, String surname) {
        Set<Publication> publications = library.getPublications().stream().
                filter(publication -> name.equals(publication.getAuthor().getName())
                        && surname.equals(publication.getAuthor().getSurname())).collect(Collectors.toSet());
        return converSetOfPublicationToString(publications);
    }

    @Override
    public String showPublicationsByAuthor(Author author) {
        Set<Publication> publications = library.getPublications().stream().
                filter(publication -> author.equals(publication.getAuthor())).collect(Collectors.toSet());
        return converSetOfPublicationToString(publications);    }

    @Override
    public String showPublicationsByTitle(String title) {
        Set<Publication> publications = library.getPublications().stream().
                filter(publication -> title.equals(publication.getTitle())).collect(Collectors.toSet());
        return converSetOfPublicationToString(publications);
    }

    @Override
    public String showPublicationsByTitleContains(String contains) {
        Set<Publication> publications = library.getPublications().stream().
                filter(publication -> publication.getTitle().contains(contains)).collect(Collectors.toSet());
        return converSetOfPublicationToString(publications);
    }

    @Override
    public String showPublicationsByTags(String... tags) {
        Set<Publication> publications = library.getPublications().stream().
                filter(publication -> publication.getTags().containsAll(Arrays.asList(tags))).collect(Collectors.toSet());
        return converSetOfPublicationToString(publications);
    }

    @Override
    public String showPublicationsByCategory(String category) {
        Set<Publication> publications = library.getPublications().stream().
                filter(publication -> publication.getCategory().equals(Category.of(category))).collect(Collectors.toSet());
        return converSetOfPublicationToString(publications);
    }


    private String converSetOfPublicationToString(Set<Publication> publicationSet) {
        StringBuilder sb = new StringBuilder();
        int i = 1;
        for (Publication publication : publicationSet) {
            sb.append(String.format("%d.%s", i, publication)).append("\n");
            i++;
        }
        return sb.toString();
    }

    @Override
    public String showDetailedPublications() {
        StringBuilder sb = new StringBuilder();
        PublicationVisitor publicationVisitor = new PublicationVisitorImpl();
        library.getPublications().forEach(publication -> sb.append(publication.accept(publicationVisitor)).append("\n"));
        return sb.toString();
    }

    @Override
    public boolean reportPublication(Publication publication) {
        return library.getReportPublicationFacade().reportPublication(publication);
    }
}
