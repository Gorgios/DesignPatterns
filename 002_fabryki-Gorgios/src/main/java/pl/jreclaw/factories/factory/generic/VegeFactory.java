package pl.jreclaw.factories.factory.generic;

import pl.jreclaw.factories.model.vegetables.Carrot;
import pl.jreclaw.factories.model.vegetables.Pumpkin;
import pl.jreclaw.factories.model.vegetables.Tomato;
import pl.jreclaw.factories.model.vegetables.Vegetable;

public class VegeFactory extends GenericFactory<Vegetable> {

    private static VegeFactory instance;

    private VegeFactory() {
    }

    ;

    public static VegeFactory getInstance() {
        if (instance == null) {
            synchronized (VegeFactory.class) {
                if (instance == null) {
                    instance = new VegeFactory();
                }
            }
        }
        return instance;
    }

    @Override
    public Vegetable getItem(String name) {
        switch (name.toLowerCase()) {
            case "carrot":
                return new Carrot();
            case "pumpkin":
                return new Pumpkin();
            case "tomato":
                return new Tomato();
            default:
                throw new IllegalStateException("Vegetable with name " + name + " not found!");
        }
    }
}
