package pl.jreclaw.onlinelibrary.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
public class Category {
    String categoryName;

    private Category(String categoryName) {
        this.categoryName = categoryName;
    }

    public static Category of(String name) {
        return new Category(name.toLowerCase());
    }

    @Override
    public String toString() {
        return categoryName;
    }
}
