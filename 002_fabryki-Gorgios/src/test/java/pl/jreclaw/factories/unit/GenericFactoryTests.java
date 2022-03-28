package pl.jreclaw.factories.unit;

import org.junit.jupiter.api.Test;
import pl.jreclaw.factories.factory.generic.GenericFactory;
import pl.jreclaw.factories.factory.generic.FactoryProvider;
import pl.jreclaw.factories.factory.generic.FruitFactory;
import pl.jreclaw.factories.factory.generic.VegeFactory;
import pl.jreclaw.factories.model.fruit.Fruit;
import pl.jreclaw.factories.model.vegetables.Vegetable;
import static org.junit.jupiter.api.Assertions.*;

public class GenericFactoryTests {
    @Test
    void getFactoriesFromOneProvider() {
        VegeFactory vegeFactory = (VegeFactory) FactoryProvider.getFactory("vegetables");
        FruitFactory fruitFactory = (FruitFactory) FactoryProvider.getFactory("fruits");
        assertInstanceOf(FruitFactory.class, fruitFactory);
        assertInstanceOf(VegeFactory.class, vegeFactory);
        assertInstanceOf(GenericFactory.class, fruitFactory);
        assertInstanceOf(GenericFactory.class, vegeFactory);
    }

    @Test
    void getInvalidFactory(){
        assertThrows(IllegalStateException.class,
                () -> FactoryProvider.getFactory("lubiana")
        );
    }

    @Test
    void createVegetables() {
        VegeFactory factory = (VegeFactory) FactoryProvider.getFactory("vegetables");
        Vegetable carrot = factory.getItem("carrot");
        Vegetable tomato = factory.getItem("tomato");
        Vegetable pumpkin = factory.getItem("pumpkin");
        assertEquals(carrot.getVegetableName(), "Carrot");
        assertEquals(tomato.getVegetableName(), "Tomato");
        assertEquals(pumpkin.getVegetableName(), "Pumpkin");
    }

    @Test
    void createFruits(){
        FruitFactory factory = (FruitFactory) FactoryProvider.getFactory("fruits");
        Fruit orange = factory.getItem("orange");
        Fruit apple = factory.getItem("apple");
        Fruit pear = factory.getItem("pear");
        Fruit banana = factory.getItem("banana");

        assertEquals("Orange",orange.getFruitName());
        assertEquals("Apple",apple.getFruitName());
        assertEquals("Pear",pear.getFruitName());
        assertEquals("Banana",banana.getFruitName());
    }

    @Test
    void invalidFruit(){
        FruitFactory factory = (FruitFactory) FactoryProvider.getFactory("fruits");
        assertThrows(IllegalStateException.class,
                () -> factory.getItem("carrot")
        );
    }

    @Test
    void invalidVegetable(){
        VegeFactory factory = (VegeFactory) FactoryProvider.getFactory("vegetables");
        assertThrows(IllegalStateException.class,
                () -> factory.getItem("apple")
        );
    }
}
