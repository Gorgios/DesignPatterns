package pl.jreclaw.factories.factory.abstractt;

import pl.jreclaw.factories.model.fruit.Banana;
import pl.jreclaw.factories.model.fruit.Fruit;
import pl.jreclaw.factories.model.fruit.Orange;
import pl.jreclaw.factories.model.vegetables.Pumpkin;
import pl.jreclaw.factories.model.vegetables.Tomato;
import pl.jreclaw.factories.model.vegetables.Vegetable;

import java.time.LocalDateTime;

public class WinterPlantFactory extends AbstractPlantFactory{

    private static WinterPlantFactory instance;

    private WinterPlantFactory(){}

    public static WinterPlantFactory getInstance() {
        if (instance == null){
            synchronized (WinterPlantFactory.class){
                if (instance == null){
                    instance = new WinterPlantFactory();
                }
            }
        }
        return instance;
    }

    @Override
    public Vegetable getVegetable() {
        return new Pumpkin();
    }

    @Override
    public Fruit getFruit() {
        if (LocalDateTime.now().getHour() < 20) {
            return new Banana();
        }
        return new Orange();
    }
}
