package pl.jreclaw.factories.unit;

import org.junit.jupiter.api.Test;
import pl.jreclaw.factories.factory.method.ColdDrinkFactory;
import pl.jreclaw.factories.factory.method.DrinkFactory;
import pl.jreclaw.factories.factory.method.HotDrinkFactory;
import pl.jreclaw.factories.model.drink.*;
import static org.junit.jupiter.api.Assertions.*;
public class MethodFactoryTests {

    @Test
    void createColdDrinks(){
        DrinkFactory factory = ColdDrinkFactory.getInstance();
        Drink cola = factory.getDrink("cola");
        Drink water = factory.getDrink("water");
        assertInstanceOf(ColdDrink.class,cola);
        assertInstanceOf(ColdDrink.class,water);
        assertEquals("Cola",cola.getDrinkName());
        assertEquals("Water",water.getDrinkName());
    }
    @Test
    void createHotDrinks(){
        DrinkFactory factory = HotDrinkFactory.getInstance();
        Drink coffee = factory.getDrink("coffee");
        Drink tea = factory.getDrink("tea");
        assertInstanceOf(HotDrink.class,coffee);
        assertInstanceOf(HotDrink.class,tea);
        assertEquals("Coffee",coffee.getDrinkName());
        assertEquals("Tea",tea.getDrinkName());
    }
    @Test
    void createHotDrinkInsteadOfCold(){
        ColdDrinkFactory factory = ColdDrinkFactory.getInstance();
        assertThrows(IllegalStateException.class,() -> factory.getDrink("coffee"));
    }
}
