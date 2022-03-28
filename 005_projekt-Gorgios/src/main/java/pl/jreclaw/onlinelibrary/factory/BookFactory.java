package pl.jreclaw.onlinelibrary.factory;

import pl.jreclaw.onlinelibrary.helpers.TagsChooser;
import pl.jreclaw.onlinelibrary.model.Author;
import pl.jreclaw.onlinelibrary.model.Category;
import pl.jreclaw.onlinelibrary.model.publication.Book;
import pl.jreclaw.onlinelibrary.model.publication.Publication;

import java.time.LocalDateTime;

public class BookFactory extends PublicationFactory{

    private static BookFactory instance;

    private BookFactory() {
    }

    public static BookFactory getInstance() {
        if (instance == null) {
            synchronized (BookFactory.class) {
                if (instance == null) {
                    instance = new BookFactory();
                }
            }
        }
        return instance;
    }

    @Override
    public Publication getPublication(Author author, String title, String content, String category) {
        Publication publication = new Book(getPages(content));
        publication.setAuthor(author);
        publication.setTitle(title);
        publication.setCategory(Category.of(category));
        publication.setContent(content);
        publication.setPublicationTime(LocalDateTime.now());
        publication.setTags(TagsChooser.getTags(content));
        author.listPublication(publication);
        return publication;
    }

    private int getPages(String content) {
        return content.split(" ").length/150 + 1;
    }
}
