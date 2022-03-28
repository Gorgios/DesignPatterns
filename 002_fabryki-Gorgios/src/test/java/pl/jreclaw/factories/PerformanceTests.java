package pl.jreclaw.factories;

import org.openjdk.jmh.annotations.*;
import pl.jreclaw.factories.factory.abstractt.AbstractPlantFactory;
import pl.jreclaw.factories.factory.abstractt.SummerPlantFactory;
import pl.jreclaw.factories.factory.abstractt.WinterPlantFactory;
import pl.jreclaw.factories.factory.generic.FactoryProvider;
import pl.jreclaw.factories.factory.generic.FruitFactory;
import pl.jreclaw.factories.factory.method.HotDrinkFactory;
import pl.jreclaw.factories.factory.register.ReflectionVehicleFactory;
import pl.jreclaw.factories.factory.register.SupplierVehicleFactory;
import pl.jreclaw.factories.factory.simple.SimpleFruitFactory;

@State(Scope.Benchmark)
public class PerformanceTests {

    @Param({"100000000"})
    int iterations;

    @Benchmark
    @BenchmarkMode(Mode.SingleShotTime)
    public void SimpleFactoryTests() {
        SimpleFruitFactory factory = SimpleFruitFactory.getInstance();
        for (int i = 0; i < iterations; i++){
            factory.getFruit("apple");
            factory.getFruit("orange");
            factory.getFruit("banana");
        }
    }

    @Benchmark
    @BenchmarkMode(Mode.SingleShotTime)
    public void MethodFactoryTests() {
        HotDrinkFactory factory = HotDrinkFactory.getInstance();
        for (int i = 0; i < iterations; i++){
            factory.getDrink("coffee");
            factory.getDrink("tea");
            factory.getDrink("chocolate");
        }
    }
    @Benchmark
    @BenchmarkMode(Mode.SingleShotTime)
    public void GenericFactoryTests() {
        FruitFactory factory = (FruitFactory) FactoryProvider.getFactory("fruits");
        for (int i = 0; i < iterations; i++){
            factory.getItem("apple");
            factory.getItem("orange");
            factory.getItem("banana");
        }
    }

    @Benchmark
    @BenchmarkMode(Mode.SingleShotTime)
    public void AbstractFactoryTests() {
        WinterPlantFactory winter = WinterPlantFactory.getInstance();
        SummerPlantFactory summer = SummerPlantFactory.getInstance();
        for (int i = 0; i < iterations; i++){
            summer.getFruit();
            summer.getVegetable();
            winter.getFruit();
        }
    }

    @Benchmark
    @BenchmarkMode(Mode.SingleShotTime)
    public void ReflectionFactoryTests() {
        for (int i = 0; i < iterations; i++){
            ReflectionVehicleFactory.getVehicle("car");
            ReflectionVehicleFactory.getVehicle("motorbike");
            ReflectionVehicleFactory.getVehicle("tank");
        }
    }

    @Benchmark
    @BenchmarkMode(Mode.SingleShotTime)
    public void SupplierFactoryTests(){
        for (int i = 0; i < iterations; i++){
            SupplierVehicleFactory.getVehicle("car");
            SupplierVehicleFactory.getVehicle("motorbike");
            SupplierVehicleFactory.getVehicle("tank");
        }
    }
}
