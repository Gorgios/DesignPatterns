package pl.jreclaw.onlinelibrary.visitor;

import pl.jreclaw.onlinelibrary.model.publication.Article;
import pl.jreclaw.onlinelibrary.model.publication.Book;
import pl.jreclaw.onlinelibrary.model.publication.Poem;

public class PublicationVisitorImpl implements PublicationVisitor {

    public String visitPoem(Poem poem) {
        return poem.toString() + "\nNumber of lines: " + poem.getNumberOfLines();
    }

    public String visitBook(Book book) {
        return book.toString() + "\nNumber of pages: " + book.getPages();
    }

    public String visitArticle(Article article) {
        return article.toString() + "\nMinutes to read: " + article.getMinutesReadingTime();
    }

}
