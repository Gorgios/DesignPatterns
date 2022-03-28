package pl.jreclaw.onlinelibrary.facade;

import pl.jreclaw.onlinelibrary.model.Library;
import pl.jreclaw.onlinelibrary.model.moderation.IdeologyModeration;
import pl.jreclaw.onlinelibrary.model.moderation.ScreeningModeration;
import pl.jreclaw.onlinelibrary.model.publication.Publication;

public class ReportPublicationFacade {
    private final IdeologyModeration ideologyModeration;
    private final ScreeningModeration screeningModeration;
    private final Library library;

    public ReportPublicationFacade(Library library) {
        ideologyModeration = IdeologyModeration.getInstance();
        screeningModeration = ScreeningModeration.getInstance();
        this.library = library;
    }

    public boolean reportPublication(Publication publication) {
        boolean willBeDeleted = false;
        String content = publication.getContent();
        if (ideologyModeration.hasContentBadIdeologyWords(content)) {
            willBeDeleted = true;
        }
        if (screeningModeration.hasContentProfanities(content)) {
            willBeDeleted = true;
        }
        if (willBeDeleted) {
            library.getPublications().remove(publication);
            publication.deletePublication();
        }
        return willBeDeleted;
    }
}
