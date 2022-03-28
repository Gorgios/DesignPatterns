package pl.jreclaw.factories.unit;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import pl.jreclaw.factories.factory.register.ReflectionVehicleFactory;
import pl.jreclaw.factories.model.vehicle.Bus;
import pl.jreclaw.factories.model.vehicle.Vehicle;

public class ReflectionTests {

    @Test
    void getNotExistingVehicle(){
        assertThrows(IllegalStateException.class,
                () -> ReflectionVehicleFactory.getVehicle("NOT_EXISTING"));
    }

    @Test
    void addNewClassToFactory(){
        assertThrows(IllegalStateException.class,
                () -> ReflectionVehicleFactory.getVehicle("bus"));

        ReflectionVehicleFactory.registerType("bus", Bus.class);

        Vehicle bus = ReflectionVehicleFactory.getVehicle("bus");
        assertInstanceOf(Bus.class,bus);
        assertEquals("Bus", bus.getVehicleName());
    }

    @Test
    void getVehicles(){
        Vehicle quad = ReflectionVehicleFactory.getVehicle("quad");
        Vehicle tank = ReflectionVehicleFactory.getVehicle("tank");
        Vehicle car = ReflectionVehicleFactory.getVehicle("car");
        Vehicle motorbike = ReflectionVehicleFactory.getVehicle("motorbike");
        assertEquals("Quad",quad.getVehicleName());
        assertEquals("Tank",tank.getVehicleName());
        assertEquals("Car",car.getVehicleName());
        assertEquals("Motorbike",motorbike.getVehicleName());
    }

}
