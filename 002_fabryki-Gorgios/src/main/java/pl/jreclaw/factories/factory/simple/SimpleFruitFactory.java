package pl.jreclaw.factories.factory.simple;

import pl.jreclaw.factories.model.fruit.*;

@SuppressWarnings("DuplicatedCode")
public class SimpleFruitFactory {
    private static SimpleFruitFactory instance;

    private SimpleFruitFactory() {
    }

    ;

    public static SimpleFruitFactory getInstance() {
        if (instance == null) {
            synchronized (SimpleFruitFactory.class) {
                if (instance == null) {
                    instance = new SimpleFruitFactory();
                }
            }
        }
        return instance;
    }

    public Fruit getFruit(String name) {
        switch (name.toLowerCase()) {
            case "apple":
                return new Apple();
            case "orange":
                return new Orange();
            case "pear":
                return new Pear();
            case "banana":
                return new Banana();
            default:
                throw new IllegalStateException("Fruit with name " + name + " not found !");
        }
    }
}
