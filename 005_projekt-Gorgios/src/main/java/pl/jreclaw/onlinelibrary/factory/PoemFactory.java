package pl.jreclaw.onlinelibrary.factory;

import pl.jreclaw.onlinelibrary.helpers.TagsChooser;
import pl.jreclaw.onlinelibrary.model.Author;
import pl.jreclaw.onlinelibrary.model.Category;
import pl.jreclaw.onlinelibrary.model.publication.Book;
import pl.jreclaw.onlinelibrary.model.publication.Poem;
import pl.jreclaw.onlinelibrary.model.publication.Publication;

import java.time.LocalDateTime;

public class PoemFactory extends PublicationFactory{
    private static PoemFactory instance;

    private PoemFactory() {
    }

    public static PoemFactory getInstance() {
        if (instance == null) {
            synchronized (BookFactory.class) {
                if (instance == null) {
                    instance = new PoemFactory();
                }
            }
        }
        return instance;
    }

    @Override
    public Publication getPublication(Author author, String title, String content, String category) {
        Publication publication = new Poem(getPages(content));
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
        return content.split("\n").length;
    }
}
