package models;

import DriverStates.IsFree;
import DriverStates.DriverState;

public class Driver {
    private final String id;
    private final String name;
    private transient Truck truck;
    private transient DriverState state;

    public Driver(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public void initializeState() {
        this.state = new IsFree();
    }

    public Driver(Driver driver) {
        this.id = driver.id;
        this.name = driver.name;
    }

    public void setTruck(Truck truck) {
        this.truck = truck;
    }

    public void getInfoMessage(){
        System.out.println("  #  | Driver       | Bus            \n" +
                           "-----|--------------|---------------");
    }

    @Override
    public String toString() {
        String truckName = truck == null ? "" : truck.getName();
        return String.format("%3s  | %-12s | %-15s | %-10s", turnIntoInt(id), name, truckName, state);
    }

    private int turnIntoInt(String id){
        return Integer.parseInt(id.replace("drv-", ""));
    }

    public String getName() {
        return name;
    }

    public void setState(DriverState state) {
        this.state = state;
    }

    public DriverState getState() {
        return state;
    }
}
