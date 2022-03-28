package pl.jreclaw.onlinelibrary.model;

import lombok.Data;
import pl.jreclaw.onlinelibrary.facade.ReportPublicationFacade;
import pl.jreclaw.onlinelibrary.model.publication.Publication;

import java.util.HashSet;
import java.util.Set;

@Data
public class Library {

    private static Library instance;
    private final ReportPublicationFacade reportPublicationFacade;
    private final Set<Publication> publications;

    private Library() {
        publications = new HashSet<>();
        reportPublicationFacade = new ReportPublicationFacade(this);
    }

    public static Library getInstance() {
        if (instance == null) {
            synchronized (Library.class) {
                if (instance == null) {
                    instance = new Library();
                }
            }
        }
        return instance;
    }

    public void addPublication(Publication publication) { publications.add(publication);}

}
