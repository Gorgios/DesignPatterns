package pl.jreclaw.factories.factory.method;

import pl.jreclaw.factories.model.drink.*;

public class HotDrinkFactory extends DrinkFactory {

    private static HotDrinkFactory instance;

    private HotDrinkFactory() {
    }

    public static HotDrinkFactory getInstance() {
        if (instance == null) {
            synchronized (HotDrinkFactory.class) {
                if (instance == null) {
                    instance = new HotDrinkFactory();
                }
            }
        }
        return instance;
    }

    @Override
    public HotDrink getDrink(String name) {
        switch (name.toLowerCase()) {
            case "chocolate":
                return new Chocolate();
            case "coffee":
                return new Coffee();
            case "tea":
                return new Tea();
            default:
                throw new IllegalStateException("Hot drink with name '" + name + "' not found !");
        }
    }
}
