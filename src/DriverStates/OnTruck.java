package DriverStates;

import exceptions.StateException;
import models.Driver;

public class OnTruck implements DriverState {

    @Override
    public void startDriving(Driver driver) throws StateException {
        throw new StateException("Driver is already on the truck\n");
    }

    @Override
    public void stopDriving(Driver driver) {
        driver.setTruck(null);
        driver.setState(new IsFree());
    }
}
