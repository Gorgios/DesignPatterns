package pl.jreclaw.onlinelibrary.visitor;

import pl.jreclaw.onlinelibrary.model.publication.Article;
import pl.jreclaw.onlinelibrary.model.publication.Book;
import pl.jreclaw.onlinelibrary.model.publication.Poem;

public interface PublicationVisitor {
    String visitPoem(Poem poem);
    String visitBook(Book book);
    String visitArticle(Article article);
}
