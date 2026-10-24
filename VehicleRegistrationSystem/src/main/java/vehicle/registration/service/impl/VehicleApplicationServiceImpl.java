package vehicle.registration.service.impl;

import vehicle.registration.exception.DuplicatePlateException;
import vehicle.registration.exception.InvalidInputException;
import vehicle.registration.exception.VehicleNotFoundException;
import vehicle.registration.model.Car;
import vehicle.registration.model.Motorcycle;
import vehicle.registration.model.Truck;
import vehicle.registration.model.Vehicle;
import vehicle.registration.service.ApplicationService;
import vehicle.registration.service.RegistrationService;
import vehicle.registration.util.InputValidator;

import java.util.List;
import java.util.Scanner;

public class VehicleApplicationServiceImpl implements ApplicationService {
    private final Scanner scanner = new Scanner(System.in);
    private final RegistrationService registrationService;

    public VehicleApplicationServiceImpl(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @Override
    public void start() {
        int choice;
        do {
            showMenu();
            choice = readInt("Enter your choice: ");
            try {
                switch (choice) {
                    case 1 -> registerVehicle();
                    case 2 -> viewAllVehicles();
                    case 3 -> searchByPlate();
                    case 4 -> searchByOwner();
                    case 5 -> updateVehicle();
                    case 6 -> deleteVehicle();
                    case 7 -> changeStatus();
                    case 8 -> System.out.println("Total registered vehicles: "
                            + registrationService.getVehicleCount());
                    case 9 -> filterByType();
                    case 10 -> System.out.println("Thank you for using the system!");
                    default -> System.out.println("Invalid choice. Please select 1-10.");
                }
            } catch (InvalidInputException | DuplicatePlateException
                     | VehicleNotFoundException exception) {
                System.out.println("Error: " + exception.getMessage());
            }
        } while (choice != 10);
    }

    private void showMenu() {
        System.out.println("\n====================================");
        System.out.println("     VEHICLE REGISTRATION SYSTEM");
        System.out.println("====================================");
        System.out.println("1. Register Vehicle");
        System.out.println("2. View All Vehicles");
        System.out.println("3. Search by Plate Number");
        System.out.println("4. Search by Owner Name");
        System.out.println("5. Update Vehicle Details");
        System.out.println("6. Delete Vehicle");
        System.out.println("7. Change Registration Status");
        System.out.println("8. Count Vehicles");
        System.out.println("9. Filter Vehicles by Type");
        System.out.println("10. Exit");
        System.out.println("====================================");
    }

    private void registerVehicle() throws InvalidInputException, DuplicatePlateException {
        String plate = InputValidator.validatePlate(readText("Plate number: "));
        String owner = InputValidator.requireText(readText("Owner name: "), "Owner name");
        String model = InputValidator.requireText(readText("Vehicle model: "), "Model");
        int year = InputValidator.validateYear(readInt("Manufacturing year: "));

        System.out.println("Vehicle type: 1. Car  2. Motorcycle  3. Truck");
        int type = readInt("Choose type: ");
        Vehicle vehicle;
        switch (type) {
            case 1 -> vehicle = new Car(plate, owner, model, year);
            case 2 -> vehicle = new Motorcycle(plate, owner, model, year);
            case 3 -> vehicle = new Truck(plate, owner, model, year);
            default -> throw new InvalidInputException("Invalid vehicle type.");
        }
        registrationService.registerVehicle(vehicle);
        System.out.println("Vehicle registered successfully.");
    }

    private void viewAllVehicles() {
        List<Vehicle> vehicles = registrationService.getAllVehicles();
        if (vehicles.isEmpty()) {
            System.out.println("No vehicles registered yet.");
            return;
        }
        vehicles.forEach(vehicle -> System.out.println("\n" + vehicle + "\n"));
    }

    private void searchByPlate() throws InvalidInputException, VehicleNotFoundException {
        String plate = InputValidator.validatePlate(readText("Plate number: "));
        System.out.println(registrationService.findByPlate(plate));
    }

    private void searchByOwner() throws InvalidInputException {
        String owner = InputValidator.requireText(readText("Owner name: "), "Owner name");
        List<Vehicle> results = registrationService.findByOwner(owner);
        if (results.isEmpty()) {
            System.out.println("No vehicles found for this owner.");
            return;
        }
        results.forEach(vehicle -> System.out.println("\n" + vehicle + "\n"));
    }

    private void updateVehicle() throws InvalidInputException, VehicleNotFoundException {
        String plate = InputValidator.validatePlate(readText("Plate number: "));
        Vehicle vehicle = registrationService.findByPlate(plate);
        System.out.println("1. Owner name  2. Model  3. Manufacturing year");
        int choice = readInt("Choose field: ");
        switch (choice) {
            case 1 -> vehicle.setOwnerName(
                    InputValidator.requireText(readText("New owner name: "), "Owner name"));
            case 2 -> vehicle.setModel(
                    InputValidator.requireText(readText("New model: "), "Model"));
            case 3 -> vehicle.setYear(InputValidator.validateYear(
                    readInt("New manufacturing year: ")));
            default -> throw new InvalidInputException("Invalid update option.");
        }
        System.out.println("Vehicle updated successfully.");
    }

    private void deleteVehicle() throws InvalidInputException, VehicleNotFoundException {
        String plate = InputValidator.validatePlate(readText("Plate number to delete: "));
        registrationService.deleteVehicle(plate);
        System.out.println("Vehicle deleted successfully.");
    }

    private void changeStatus() throws InvalidInputException, VehicleNotFoundException {
        String plate = InputValidator.validatePlate(readText("Plate number: "));
        System.out.println("1. Pending  2. Approved  3. Rejected");
        int choice = readInt("Choose status: ");
        String status = switch (choice) {
            case 1 -> "Pending";
            case 2 -> "Approved";
            case 3 -> "Rejected";
            default -> throw new InvalidInputException("Invalid status option.");
        };
        registrationService.updateStatus(plate, status);
        System.out.println("Registration status updated.");
    }

    private void filterByType() throws InvalidInputException {
        System.out.println("1. Car  2. Motorcycle  3. Truck");
        int choice = readInt("Choose type: ");
        String type = switch (choice) {
            case 1 -> "Car";
            case 2 -> "Motorcycle";
            case 3 -> "Truck";
            default -> throw new InvalidInputException("Invalid vehicle type.");
        };
        List<Vehicle> results = registrationService.findByType(type);
        if (results.isEmpty()) {
            System.out.println("No vehicles found for this type.");
            return;
        }
        results.forEach(vehicle -> System.out.println("\n" + vehicle + "\n"));
    }

    private String readText(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }

    private int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException exception) {
                System.out.println("Please enter a valid whole number.");
            }
        }
    }
}
