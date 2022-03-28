package pl.jreclaw.factories.factory.method;

import pl.jreclaw.factories.model.drink.*;

public class ColdDrinkFactory extends DrinkFactory {
    private static ColdDrinkFactory instance;

    private ColdDrinkFactory() {
    }

    public static ColdDrinkFactory getInstance() {
        if (instance == null) {
            synchronized (ColdDrinkFactory.class) {
                if (instance == null) {
                    instance = new ColdDrinkFactory();
                }
            }
        }
        return instance;
    }

    @Override
    public ColdDrink getDrink(String name) {
        switch (name.toLowerCase()) {
            case "cola":
                return new Cola();
            case "water":
                return new Water();
            case "juice":
                return new Juice();
            default:
                throw new IllegalStateException("Cold drink with name '" + name + "' not found !");
        }
    }
}
