package pl.jreclaw.factories.factory.method;

import pl.jreclaw.factories.model.drink.Drink;

public abstract class DrinkFactory{
    public abstract Drink getDrink(String name);
}