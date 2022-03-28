package pl.jreclaw.onlinelibrary.factory;

public class FactoryProvider {
    public static PublicationFactory getArticleFactory() {
        return ArticleFactory.getInstance();
    }

    public static PublicationFactory getBookFactory() {
        return BookFactory.getInstance();
    }

    public static PoemFactory getPoemFactory() {
        return PoemFactory.getInstance();
    }
}
