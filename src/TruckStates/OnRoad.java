package TruckStates;

import exceptions.StateException;
import models.Driver;
import models.Truck;

public class OnRoad implements State {
    @Override
    public void startDriving(Truck truck) throws StateException {
        throw new StateException("Truck is already on the road\n");
    }

    @Override
    public void startRepair(Truck truck) {
        truck.setStateObj(new OnRepair());
        truck.setState("repair");
    }

    @Override
    public void changeDriver(Truck truck, Driver driver) throws StateException {
        throw new StateException("Cannot change driver while truck is on the road\n");
    }
}
