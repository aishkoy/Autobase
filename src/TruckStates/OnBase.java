package TruckStates;

import DriverStates.IsFree;
import DriverStates.OnTruck;
import exceptions.DriverException;
import models.Driver;
import models.Truck;


public class OnBase implements State {

    @Override
    public void startDriving(Truck truck) throws DriverException {
        if(truck.getDriverObj() != null){
            truck.setStateObj(new OnRoad());
            truck.setState("route");
        } else{
            throw new DriverException("Truck has not a driver. Please, choose a driver for this truck!!\n");
        }
    }

    @Override
    public void startRepair(Truck truck) throws DriverException {
        if(truck.getDriverObj() != null){
            truck.setStateObj(new OnRepair());
            truck.setState("repair");
        } else{
            throw new DriverException("Truck has not a driver. Please, choose a driver for this truck!!\n");
        }
    }

    @Override
    public void changeDriver(Truck truck, Driver driver) throws DriverException {
        if(driver == null){
            throw new DriverException("There are not free drivers!\n");
        }

        if (truck.getDriverObj() != null) {
            Driver currentDriver = truck.getDriverObj();
            currentDriver.setState(new IsFree());
            currentDriver.setTruck(null);
        }

        driver.setState(new OnTruck());
        driver.setTruck(truck);
        truck.setDriver(driver);
        System.out.println("Now driver " + driver.getName() + " is on the truck " + truck.getName() +"\n");

    }
}
