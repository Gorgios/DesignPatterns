package pl.jreclaw.onlinelibrary.factory;

import pl.jreclaw.onlinelibrary.helpers.TagsChooser;
import pl.jreclaw.onlinelibrary.model.Author;
import pl.jreclaw.onlinelibrary.model.Category;
import pl.jreclaw.onlinelibrary.model.publication.Article;
import pl.jreclaw.onlinelibrary.model.publication.Publication;

import java.time.LocalDateTime;

public class ArticleFactory extends PublicationFactory {

    private static ArticleFactory instance;

    private ArticleFactory() {
    }

    public static ArticleFactory getInstance() {
        if (instance == null) {
            synchronized (ArticleFactory.class) {
                if (instance == null) {
                    instance = new ArticleFactory();
                }
            }
        }
        return instance;
    }

    @Override
    public Publication getPublication(Author author, String title, String content, String category) {
        Publication publication = new Article(getReadTime(content));
        publication.setAuthor(author);
        publication.setTitle(title);
        publication.setCategory(Category.of(category));
        publication.setContent(content);
        publication.setPublicationTime(LocalDateTime.now());
        publication.setTags(TagsChooser.getTags(content));
        author.listPublication(publication);

        return publication;
    }

    private int getReadTime(String content) {
        return (content.split(" ").length / 200) + 1;
    }


}
