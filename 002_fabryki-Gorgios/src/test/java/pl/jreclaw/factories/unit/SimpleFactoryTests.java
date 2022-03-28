package pl.jreclaw.factories.unit;

import org.junit.jupiter.api.Test;
import pl.jreclaw.factories.factory.simple.SimpleFruitFactory;
import pl.jreclaw.factories.model.fruit.Fruit;
import static org.junit.jupiter.api.Assertions.*;

public class SimpleFactoryTests {

    @Test
    void createFruits(){
        SimpleFruitFactory factory = SimpleFruitFactory.getInstance();
        Fruit orange = factory.getFruit("orange");
        Fruit apple = factory.getFruit("apple");
        Fruit pear = factory.getFruit("pear");
        Fruit banana = factory.getFruit("banana");

        assertEquals("Orange",orange.getFruitName());
        assertEquals("Apple",apple.getFruitName());
        assertEquals("Pear",pear.getFruitName());
        assertEquals("Banana",banana.getFruitName());
    }

    @Test
    void createNotExistingFruit(){
        SimpleFruitFactory factory = SimpleFruitFactory.getInstance();
        assertThrows(IllegalStateException.class, () -> factory.getFruit("Tomato"));
    }
}
