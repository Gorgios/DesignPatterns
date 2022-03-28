package pl.jreclaw.factories.factory.abstractt;

import pl.jreclaw.factories.model.fruit.Fruit;
import pl.jreclaw.factories.model.vegetables.Vegetable;

public abstract class AbstractPlantFactory {
    public abstract Vegetable getVegetable();
    public abstract Fruit getFruit();
}
