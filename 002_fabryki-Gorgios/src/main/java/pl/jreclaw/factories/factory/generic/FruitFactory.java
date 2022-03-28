package pl.jreclaw.factories.factory.generic;

import pl.jreclaw.factories.model.fruit.*;

public class FruitFactory extends GenericFactory<Fruit> {
    private static FruitFactory instance;

    private FruitFactory(){}

    public static FruitFactory getInstance() {
        if (instance == null){
            synchronized (FruitFactory.class){
                if (instance == null){
                    instance = new FruitFactory();
                }
            }
        }
        return instance;
    }

    @Override
    public Fruit getItem(String name) {
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
