package TruckStates;

import exceptions.DriverException;
import exceptions.StateException;
import models.Driver;
import models.Truck;

public interface State {
    void startDriving(Truck truck) throws StateException, DriverException;
    void startRepair(Truck truck) throws StateException;
    void changeDriver(Truck truck, Driver driver) throws StateException, DriverException;
}