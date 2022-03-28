package pl.jreclaw.onlinelibrary.model.moderation;

public class ScreeningModeration {

    private static ScreeningModeration instance;

    private ScreeningModeration() {

    }

    public static ScreeningModeration getInstance() {
        if (instance == null) {
            synchronized (ScreeningModeration.class) {
                if (instance == null) {
                    instance = new ScreeningModeration();
                }
            }
        }
        return instance;
    }

    public boolean hasContentProfanities(String content) {
        String lcContent = content.toLowerCase();
        return lcContent.contains("shit") || lcContent.contains("fuck") || lcContent.contains("bitch");
    }
}
