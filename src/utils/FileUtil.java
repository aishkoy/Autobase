package utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import models.Driver;
import models.Truck;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;


public class FileUtil {
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    private FileUtil() {};

    public static Truck[] readFile(){
        Truck[] trucks = new Truck[3];
        try{
            String str = Files.readString(Path.of("data/trucks.json"));
            trucks = GSON.fromJson(str, Truck[].class);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return trucks;
    }

    public static Driver[] readFileDrivers(){
        Driver[] drivers = new Driver[3];
        try{
            String str = Files.readString(Path.of("data/drivers.json"));
            drivers = GSON.fromJson(str, Driver[].class);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return drivers;
    }

    public static void writeFile(Truck[] trucks){
        String newJson = GSON.toJson(trucks);
        byte[] bytes = newJson.getBytes();
        try{
            Files.write(Path.of("data/trucks.json"), bytes);
        } catch (IOException e) {
            System.out.println("Something went wrong");
        }
    }

    public static void writeFileDrivers(Driver[] drivers){
        String newJson = GSON.toJson(drivers);
        byte[] bytes = newJson.getBytes();
        try{
            Files.write(Path.of("data/drivers.json"), bytes);
        } catch (IOException e) {
            System.out.println("Something went wrong");
        }
    }
}
