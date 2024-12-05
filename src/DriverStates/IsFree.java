package DriverStates;

import exceptions.StateException;
import models.Driver;

public class IsFree implements DriverState {
    @Override
    public void startDriving(Driver driver) {
        driver.setState(new OnTruck());
    }

    @Override
    public void stopDriving(Driver driver) throws StateException {
        throw new StateException("Driver is not on a truck");
    }
}
