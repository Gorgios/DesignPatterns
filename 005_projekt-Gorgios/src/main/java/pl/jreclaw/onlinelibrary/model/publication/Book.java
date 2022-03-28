package pl.jreclaw.onlinelibrary.model.publication;

import lombok.AllArgsConstructor;
import lombok.Getter;
import pl.jreclaw.onlinelibrary.visitor.PublicationVisitor;

@AllArgsConstructor
@Getter
public class Book extends Publication {
    private final int pages;

    public String accept(PublicationVisitor visitor) {
        return visitor.visitBook(this);
    }
}
