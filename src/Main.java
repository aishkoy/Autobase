import exceptions.DriverException;
import exceptions.StateException;
import models.Driver;
import models.Truck;
import utils.FileUtil;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    private static final Scanner sc = new Scanner(System.in);
    private static final Truck[] trucks = FileUtil.readFile();
    private static final Driver[] drivers = FileUtil.readFileDrivers();

    public static void main(String[] args) throws DriverException, StateException {
        printInfo(trucks);
        printInfo(drivers);

        int choice;
        do {
            int num = getNum(1, 3, "\nEnter number of Truck: ");
            trucks[num - 1].printInfo();
            choice = changeTruck(num);
        } while (choice != 4);

        printInfo(trucks);
        printInfo(drivers);
    }

    private static int changeTruck(int truck) throws DriverException, StateException {
        System.out.print("""
                
                How do you want to change Truck's state?
                1. Change the driver
                2. Send to route
                3. Send on repair
                4. exit""");
        int choice = getNum(1, 4, "");

        switch (choice) {
            case 1:
                trucks[truck - 1].changeDriver(drivers);
                break;
            case 2:
                trucks[truck - 1].startDriving();
                break;
            case 3:
                trucks[truck - 1].startRepairing();
                break;
            case 4:
                System.out.println("Goodbye!");
        }
        return choice;
    }

    private static int getNum(int min, int max, String sense) {
        try {
            System.out.println(sense);
            int num = sc.nextInt();
            if (num < min || num > max) {
                throw new InputMismatchException();
            }
            return num;
        } catch (InputMismatchException e) {
            System.out.println("Incorrect input");
            sc.nextLine();
            return getNum(min, max, sense);
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
