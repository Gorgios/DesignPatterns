package pl.jreclaw.onlinelibrary.model.moderation;

public class IdeologyModeration {

    private static IdeologyModeration instance;

    private IdeologyModeration() {

    }

    public static IdeologyModeration getInstance() {
        if (instance == null) {
            synchronized (IdeologyModeration.class) {
                if (instance == null) {
                    instance = new IdeologyModeration();
                }
            }
        }
        return instance;
    }

    public boolean hasContentBadIdeologyWords(String content) {
        return content.toLowerCase().contains("badideology");
    }
}
