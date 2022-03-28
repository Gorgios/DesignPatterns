package pl.jreclaw.factories.factory.abstractt;

import pl.jreclaw.factories.model.fruit.Apple;
import pl.jreclaw.factories.model.fruit.Fruit;
import pl.jreclaw.factories.model.fruit.Pear;
import pl.jreclaw.factories.model.vegetables.Carrot;
import pl.jreclaw.factories.model.vegetables.Tomato;
import pl.jreclaw.factories.model.vegetables.Vegetable;

import java.time.LocalDateTime;
import java.util.Random;

public class SummerPlantFactory extends AbstractPlantFactory {

    private static SummerPlantFactory instance;

    private SummerPlantFactory(){}

    public static SummerPlantFactory getInstance() {
        if (instance == null){
            synchronized (SummerPlantFactory.class){
                if (instance == null){
                    instance = new SummerPlantFactory();
                }
            }
        }
        return instance;
    }
    @Override
    public Vegetable getVegetable() {
        if (LocalDateTime.now().getMonthValue() < 6)
            return new Tomato();
        return new Carrot();
    }

    @Override
    public Fruit getFruit() {
        if (new Random().nextInt(2) == 0)
            return new Apple();
        return new Pear();
    }
}