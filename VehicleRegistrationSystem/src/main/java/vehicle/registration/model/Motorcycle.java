package vehicle.registration.model;

public class Motorcycle extends Vehicle {
    public Motorcycle(String plateNumber, String ownerName, String model, int year) {
        super(plateNumber, ownerName, model, year);
    }

    @Override
    public String getType() { return "Motorcycle"; }
}
