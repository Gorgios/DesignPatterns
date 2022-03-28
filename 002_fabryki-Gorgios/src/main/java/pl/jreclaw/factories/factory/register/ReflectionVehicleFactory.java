package pl.jreclaw.factories.factory.register;

import pl.jreclaw.factories.model.vehicle.*;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

public class ReflectionVehicleFactory {
    private static final Map<String, Class<? extends Vehicle>> types = new HashMap<>();
    static {
        types.put("car", Car.class);
        types.put("quad", Quad.class);
        types.put("tank", Tank.class);
        types.put("motorbike", Motorbike.class);
    }
    private ReflectionVehicleFactory(){}

    public static void registerType(String name, Class<? extends Vehicle> _class){
        types.put(name.toLowerCase(),_class);
    }
    public static Vehicle getVehicle(String name) {
        if (!types.containsKey(name.toLowerCase())){
            throw new IllegalStateException("Vehicle with provided name: " + name + " not found!");
        }
        Class<? extends Vehicle> _class = types.get(name);
        try {
            Constructor constructor = _class.getDeclaredConstructor();
            return (Vehicle) constructor.newInstance();
        }
        catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException | InstantiationException ex){
            throw new RuntimeException(ex.getMessage());
        }
    }
}
