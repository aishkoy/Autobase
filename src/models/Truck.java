package models;

import DriverStates.IsFree;
import TruckStates.OnBase;
import TruckStates.OnRepair;
import TruckStates.OnRoad;
import exceptions.DriverException;
import exceptions.StateException;
import TruckStates.State;

public class Truck {
    private final int id;
    private final String name;
    private String driver;
    private String state;

    private transient Driver driverObj;
    private transient State stateObj;

    public Truck(int id, String name, String driver, String state) {
        this.id = id;
        this.name = name;
        this.driver = driver;
        this.state = state;
        initializeState();
    }

    public void initializeState() {
        switch (state.toLowerCase()) {
            case "base":
                this.stateObj = new OnBase();
                break;
            case "repair":
                this.stateObj = new OnRepair();
                break;
            case "route":
                this.stateObj = new OnRoad();
                break;
        }
    }

    public String getName() {
        return name;
    }

    public void setDriver(Driver driver) {
        this.driverObj = driver;
        this.driver = driver.getName();
    }

    public Driver getDriverObj() {
        return driverObj;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setStateObj(State stateObj) {
        this.stateObj = stateObj;
    }

    public State getStateObj() {
        return this.stateObj;
    }

    @Override
    public String toString() {
        return String.format("%3s  ║ %-15s ║ %-10s ║ %-10s ", id, name, driver, state);
    }

    public void getInfoMessage() {
        System.out.println("""
                                INFO ABOUT TRUCKS
                  #  ║ Bus             ║ Driver     ║ State
                ═════╬═════════════════╬════════════╬════════════""");
    }

    public void printInfo() {
        System.out.printf("""
                ╔════════════════════════════╗
                ║ #         : %-14s ║
                ║ Bus       : %-14s ║
                ║ Driver    : %-14s ║
                ║ Bus State : On %-11s ║
                ╚════════════════════════════╝""", id, name, driver, state);
    }

    public void startDriving(){
        try {
            if (driverObj == null) {
                throw new DriverException("Driver not set. Please set driver first\n");
            }
            stateObj.startDriving(this);
            System.out.println("Truck " + this.getName() + " is on the route\n");
        } catch (StateException | DriverException e) {
            System.out.println(e.getMessage());
        }
    }

    public void startRepairing() {
        try {
            stateObj.startRepair(this);
            System.out.println("Truck " + this.getName() + " is on repair\n");
        } catch (StateException | DriverException e) {
            System.out.println(e.getMessage());
        }
    }

    public void changeDriver(Driver[] drivers) {
        try {
            Driver freeDriver = findFreeDriver(drivers);
            stateObj.changeDriver(this, freeDriver);
        } catch (StateException | DriverException e) {
            System.out.println(e.getMessage());
        }
    }

    private Driver findFreeDriver(Driver[] drivers) {
        for (Driver driver : drivers) {
            if (driver.getState() instanceof IsFree) {
                return driver;
            }
        }
        return null;
    }
}
