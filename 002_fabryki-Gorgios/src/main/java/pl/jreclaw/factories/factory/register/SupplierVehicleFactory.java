package pl.jreclaw.factories.factory.register;

import pl.jreclaw.factories.model.vehicle.*;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class SupplierVehicleFactory {
    private static final Map<String, Supplier<? extends Vehicle>> types = new HashMap<>();
    static {
        types.put("car", Car::new);
        types.put("quad", Quad::new);
        types.put("tank", Tank::new);
        types.put("motorbike", Motorbike::new);
    }
    private SupplierVehicleFactory(){};

    public static void registerSupplier(String name, Supplier<? extends Vehicle> supplier){
        types.put(name.toLowerCase(), supplier);
    }

    public static Vehicle getVehicle(String name){
        if (!types.containsKey(name.toLowerCase())){
            throw new IllegalStateException("Vehicle with provided name: " + name + " not found!");
        }
        return types.get(name).get();
    }
}
