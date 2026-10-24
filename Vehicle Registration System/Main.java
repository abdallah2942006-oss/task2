import java.util.ArrayList;
import java.util.Scanner;

abstract class Vehicle {
    private String plateNumber;
    private String ownerName;
    private String model;
    private int year;
    private String status;

    public Vehicle(String plateNumber, String ownerName, String model, int year) {
        this.plateNumber = plateNumber;
        this.ownerName = ownerName;
        this.model = model;
        this.year = year;
        this.status = "Pending";
    }

    public String getPlateNumber() { return plateNumber; }
    public String getOwnerName() { return ownerName; }
    public String getModel() { return model; }
    public int getYear() { return year; }
    public String getStatus() { return status; }

    public void setOwnerName(String ownerName) { this.ownerName = ownerName; }
    public void setModel(String model) { this.model = model; }
    public void setYear(int year) { this.year = year; }
    public void setStatus(String status) { this.status = status; }

    public abstract String getType();

    public void displayInfo() {
        System.out.println("------------------------------");
        System.out.println("Vehicle Type : " + getType());
        System.out.println("Plate Number : " + plateNumber);
        System.out.println("Owner Name   : " + ownerName);
        System.out.println("Model        : " + model);
        System.out.println("Year         : " + year);
        System.out.println("Status       : " + status);
        System.out.println("------------------------------");
    }
}

class Car extends Vehicle {
    public Car(String plate, String owner, String model, int year) {
        super(plate, owner, model, year);
    }
    @Override public String getType() { return "Car"; }
}

class Motorcycle extends Vehicle {
    public Motorcycle(String plate, String owner, String model, int year) {
        super(plate, owner, model, year);
    }
    @Override public String getType() { return "Motorcycle"; }
}

class Truck extends Vehicle {
    public Truck(String plate, String owner, String model, int year) {
        super(plate, owner, model, year);
    }
    @Override public String getType() { return "Truck"; }
}

public class Main {
    private static final Scanner input = new Scanner(System.in);
    private static final ArrayList<Vehicle> vehicles = new ArrayList<>();

    public static void main(String[] args) {
        int choice;
        do {
            showMenu();
            choice = readInt("Enter your choice: ");
            switch (choice) {
                case 1: registerVehicle(); break;
                case 2: viewAllVehicles(); break;
                case 3: searchByPlate(); break;
                case 4: searchByOwner(); break;
                case 5: updateVehicle(); break;
                case 6: deleteVehicle(); break;
                case 7: changeStatus(); break;
                case 8: countVehicles(); break;
                case 9: filterByType(); break;
                case 10: System.out.println("Thank you for using the system!"); break;
                default: System.out.println("Invalid choice. Try again.");
            }
        } while (choice != 10);
        input.close();
    }

    private static void showMenu() {
        System.out.println("\n=================================");
        System.out.println("   VEHICLE REGISTRATION SYSTEM");
        System.out.println("=================================");
        System.out.println("1. Register Vehicle");
        System.out.println("2. View All Vehicles");
        System.out.println("3. Search by Plate Number");
        System.out.println("4. Search by Owner Name");
        System.out.println("5. Update Vehicle");
        System.out.println("6. Delete Vehicle");
        System.out.println("7. Change Registration Status");
        System.out.println("8. Count Vehicles");
        System.out.println("9. Filter Vehicles by Type");
        System.out.println("10. Exit");
        System.out.println("=================================");
    }

    private static void registerVehicle() {
        System.out.println("\n--- Register Vehicle ---");
        String plate = readText("Enter plate number: ").toUpperCase();
        if (findVehicle(plate) != null) {
            System.out.println("Error: Plate number already exists.");
            return;
        }

        String owner = readText("Enter owner name: ");
        String model = readText("Enter vehicle model: ");
        int year = readInt("Enter manufacturing year: ");
        if (year < 1886 || year > java.time.Year.now().getValue()) {
            System.out.println("Invalid manufacturing year.");
            return;
        }

        System.out.println("Select vehicle type:");
        System.out.println("1. Car");
        System.out.println("2. Motorcycle");
        System.out.println("3. Truck");
        int type = readInt("Enter type: ");
        Vehicle vehicle;
        switch (type) {
            case 1: vehicle = new Car(plate, owner, model, year); break;
            case 2: vehicle = new Motorcycle(plate, owner, model, year); break;
            case 3: vehicle = new Truck(plate, owner, model, year); break;
            default:
                System.out.println("Invalid vehicle type.");
                return;
        }
        vehicles.add(vehicle);
        System.out.println("Vehicle registered successfully!");
    }

    private static void viewAllVehicles() {
        System.out.println("\n--- All Registered Vehicles ---");
        if (vehicles.isEmpty()) {
            System.out.println("No vehicles registered yet.");
            return;
        }
        for (Vehicle vehicle : vehicles) vehicle.displayInfo();
    }

    private static void searchByPlate() {
        String plate = readText("Enter plate number: ").toUpperCase();
        Vehicle vehicle = findVehicle(plate);
        if (vehicle != null) vehicle.displayInfo();
        else System.out.println("Vehicle not found.");
    }

    private static void searchByOwner() {
        String owner = readText("Enter owner name: ");
        boolean found = false;
        for (Vehicle vehicle : vehicles) {
            if (vehicle.getOwnerName().equalsIgnoreCase(owner)) {
                vehicle.displayInfo();
                found = true;
            }
        }
        if (!found) System.out.println("No vehicles found for this owner.");
    }

    private static void updateVehicle() {
        String plate = readText("Enter plate number to update: ").toUpperCase();
        Vehicle vehicle = findVehicle(plate);
        if (vehicle == null) {
            System.out.println("Vehicle not found.");
            return;
        }

        System.out.println("1. Update owner name");
        System.out.println("2. Update model");
        System.out.println("3. Update manufacturing year");
        int choice = readInt("Choose field to update: ");
        switch (choice) {
            case 1:
                vehicle.setOwnerName(readText("Enter new owner name: "));
                break;
            case 2:
                vehicle.setModel(readText("Enter new model: "));
                break;
            case 3:
                int year = readInt("Enter new manufacturing year: ");
                if (year < 1886 || year > java.time.Year.now().getValue()) {
                    System.out.println("Invalid manufacturing year.");
                    return;
                }
                vehicle.setYear(year);
                break;
            default:
                System.out.println("Invalid choice.");
                return;
        }
        System.out.println("Vehicle updated successfully!");
    }

    private static void deleteVehicle() {
        String plate = readText("Enter plate number to delete: ").toUpperCase();
        Vehicle vehicle = findVehicle(plate);
        if (vehicle != null) {
            vehicles.remove(vehicle);
            System.out.println("Vehicle deleted successfully!");
        } else {
            System.out.println("Vehicle not found.");
        }
    }

    private static void changeStatus() {
        String plate = readText("Enter plate number: ").toUpperCase();
        Vehicle vehicle = findVehicle(plate);
        if (vehicle == null) {
            System.out.println("Vehicle not found.");
            return;
        }

        System.out.println("1. Pending");
        System.out.println("2. Approved");
        System.out.println("3. Rejected");
        int choice = readInt("Choose new status: ");
        switch (choice) {
            case 1: vehicle.setStatus("Pending"); break;
            case 2: vehicle.setStatus("Approved"); break;
            case 3: vehicle.setStatus("Rejected"); break;
            default:
                System.out.println("Invalid choice.");
                return;
        }
        System.out.println("Registration status updated successfully!");
    }

    private static void countVehicles() {
        System.out.println("Total registered vehicles: " + vehicles.size());
    }

    private static void filterByType() {
        System.out.println("1. Car");
        System.out.println("2. Motorcycle");
        System.out.println("3. Truck");
        int choice = readInt("Select vehicle type: ");
        String type;
        switch (choice) {
            case 1: type = "Car"; break;
            case 2: type = "Motorcycle"; break;
            case 3: type = "Truck"; break;
            default:
                System.out.println("Invalid choice.");
                return;
        }

        boolean found = false;
        for (Vehicle vehicle : vehicles) {
            if (vehicle.getType().equals(type)) {
                vehicle.displayInfo();
                found = true;
            }
        }
        if (!found) System.out.println("No vehicles found for this type.");
    }

    private static Vehicle findVehicle(String plate) {
        for (Vehicle vehicle : vehicles) {
            if (vehicle.getPlateNumber().equalsIgnoreCase(plate)) return vehicle;
        }
        return null;
    }

    private static String readText(String message) {
        while (true) {
            System.out.print(message);
            String value = input.nextLine().trim();
            if (!value.isEmpty()) return value;
            System.out.println("Input cannot be empty.");
        }
    }

    private static int readInt(String message) {
        while (true) {
            System.out.print(message);
            String value = input.nextLine().trim();
            try {
                return Integer.parseInt(value);
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid integer.");
            }
        }
    }
}
