package vehicle.registration.model;

public class Car extends Vehicle {
    public Car(String plateNumber, String ownerName, String model, int year) {
        super(plateNumber, ownerName, model, year);
    }

    @Override
    public String getType() { return "Car"; }
}
