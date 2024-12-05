package DriverStates;

import exceptions.StateException;
import models.Driver;


public interface DriverState {
    void startDriving(Driver driver) throws StateException;
    void stopDriving(Driver driver) throws StateException;
}
