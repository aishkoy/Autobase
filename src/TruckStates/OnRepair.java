package TruckStates;

import exceptions.StateException;
import models.Driver;
import models.Truck;

import java.util.Random;

public class OnRepair implements State{
    private static final Random rand = new Random();

    @Override
    public void startDriving(Truck truck) {
        if(rand.nextDouble() <= 0.5){
            truck.setStateObj(new OnRoad());
            truck.setState("route");
            System.out.println("Truck is on road");
        } else {
            truck.setStateObj(new OnBase());
            truck.setState("base");
            System.out.println("Truck is on base");
        }
    }

    @Override
    public void startRepair(Truck truck) throws StateException {
        throw new StateException("Truck is already on repair");
    }

    @Override
    public void changeDriver(Truck truck, Driver driver)  throws StateException {
        throw new StateException("Cannot change driver while truck is on repair");
    }
}
