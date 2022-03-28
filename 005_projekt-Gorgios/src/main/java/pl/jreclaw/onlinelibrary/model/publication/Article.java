package pl.jreclaw.onlinelibrary.model.publication;

import lombok.AllArgsConstructor;
import lombok.Getter;
import pl.jreclaw.onlinelibrary.visitor.PublicationVisitor;

@AllArgsConstructor
@Getter
public class Article extends Publication {
    private final int minutesReadingTime;

    public String accept(PublicationVisitor visitor) {
       return visitor.visitArticle(this);
    }
}
