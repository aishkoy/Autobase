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
            System.out.println("Truck " + truck.getName() + " is on road\n");
        } else {
            truck.setStateObj(new OnBase());
            truck.setState("base");
            System.out.println("Truck " + truck.getName() + " is on base\n");
        }
    }

    @Override
    public void startRepair(Truck truck) throws StateException {
        throw new StateException("Truck " + truck.getName() + " is already on repair\n");
    }

    @Override
    public void changeDriver(Truck truck, Driver driver)  throws StateException {
        throw new StateException("Cannot change driver while truck is on repair\n");
    }
}
