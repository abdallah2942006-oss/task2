package vehicle.registration.model;

public class Truck extends Vehicle {
    public Truck(String plateNumber, String ownerName, String model, int year) {
        super(plateNumber, ownerName, model, year);
    }

    @Override
    public String getType() { return "Truck"; }
}
