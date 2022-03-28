package pl.jreclaw.factories.unit;

import org.junit.jupiter.api.Test;
import pl.jreclaw.factories.factory.register.SupplierVehicleFactory;
import pl.jreclaw.factories.model.vehicle.Truck;
import pl.jreclaw.factories.model.vehicle.Vehicle;

import static org.junit.jupiter.api.Assertions.*;

public class SupplierTests {

    @Test
    void getNotExistingVehicle(){
        assertThrows(IllegalStateException.class,
                () -> SupplierVehicleFactory.getVehicle("NOT_EXISTING"));
    }

    @Test
    void addNewVehicleToFactory(){
        assertThrows(IllegalStateException.class,
                () -> SupplierVehicleFactory.getVehicle("truck"));

        SupplierVehicleFactory.registerSupplier("truck", Truck::new);

        Vehicle truck = SupplierVehicleFactory.getVehicle("truck");
        assertInstanceOf(Truck.class,truck);
        assertEquals("Truck",truck.getVehicleName());
    }

    @Test
    void getVehicles(){
        Vehicle quad = SupplierVehicleFactory.getVehicle("quad");
        Vehicle tank = SupplierVehicleFactory.getVehicle("tank");
        Vehicle car = SupplierVehicleFactory.getVehicle("car");
        Vehicle motorbike = SupplierVehicleFactory.getVehicle("motorbike");
        assertEquals("Quad",quad.getVehicleName());
        assertEquals("Tank",tank.getVehicleName());
        assertEquals("Car",car.getVehicleName());
        assertEquals("Motorbike",motorbike.getVehicleName());
    }
}
