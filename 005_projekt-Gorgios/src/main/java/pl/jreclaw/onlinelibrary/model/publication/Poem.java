package pl.jreclaw.onlinelibrary.model.publication;

import lombok.AllArgsConstructor;
import lombok.Getter;
import pl.jreclaw.onlinelibrary.visitor.PublicationVisitor;

@Getter
@AllArgsConstructor
public class Poem extends Publication {
    private final int numberOfLines;

    public String accept(PublicationVisitor visitor) {
        return visitor.visitPoem(this);
    }
}
