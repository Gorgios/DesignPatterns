package pl.jreclaw.factories.unit;

import org.junit.jupiter.api.Test;
import pl.jreclaw.factories.factory.abstractt.AbstractPlantFactory;
import pl.jreclaw.factories.factory.abstractt.SummerPlantFactory;
import pl.jreclaw.factories.factory.abstractt.WinterPlantFactory;
import pl.jreclaw.factories.model.fruit.Fruit;
import pl.jreclaw.factories.model.vegetables.Tomato;
import pl.jreclaw.factories.model.vegetables.Vegetable;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class AbstractFactoryTests {
    @Test
    void getPlants(){
        WinterPlantFactory winter = WinterPlantFactory.getInstance();
        SummerPlantFactory summer = SummerPlantFactory.getInstance();
        Vegetable winterVege = winter.getVegetable();
        Vegetable summerVege = summer.getVegetable();
        Fruit winterFruit = winter.getFruit();
        Fruit summerFruit = summer.getFruit();

        String expectedWinterFruit = LocalDateTime.now().getHour() < 20 ? "Banana" : "Orange";
        String expectedSummerVege = LocalDateTime.now().getMonthValue() < 6 ? "Tomato" : "Orange";
        assertEquals("Pumpkin",winterVege.getVegetableName());
        assertEquals(expectedSummerVege, summerVege.getVegetableName());
        assertTrue("Apple".equals(summerFruit.getFruitName()) || "Pear".equals(summerFruit.getFruitName()));
        assertEquals(expectedWinterFruit, winterFruit.getFruitName());
    }
}
