import TruckStates.OnBase;
import exceptions.DriverException;
import exceptions.StateException;
import models.Driver;
import models.Truck;
import utils.FileUtil;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    private static final Scanner sc = new Scanner(System.in);
    public static void main(String[] args) throws DriverException, StateException {
        Truck[] trucks = FileUtil.readFile();
        Driver[] drivers = FileUtil.readFileDrivers();

        printInfo(trucks);
        printInfo(drivers);

        System.out.print("Enter the number of truck: ");
        try {
            int number = sc.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Please enter a number");
        }
    }




    private static void printInfo(Object[] objects) {
        if (objects.length == 0) {
            System.out.println("No objects found");
            return;
        }

        if (objects[0] instanceof Truck truck) {
            truck.getInfoMessage();
        } else if (objects[0] instanceof Driver driver) {
            driver.getInfoMessage();
        }

        for (var object : objects) {
            if (object instanceof Truck truck) {
                if (truck.getStateObj() == null) {
                    truck.initializeState();
                }
            }


            if (object instanceof Driver driver) {
                if (driver.getState() == null) {
                    driver.initializeState();
                }
            }
            System.out.println(object);
        }

    }
}
