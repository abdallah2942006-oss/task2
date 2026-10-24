package vehicle.registration.model;

public abstract class Vehicle {
    private final String plateNumber;
    private String ownerName;
    private String model;
    private int year;
    private String registrationStatus;

    protected Vehicle(String plateNumber, String ownerName, String model, int year) {
        this.plateNumber = plateNumber;
        this.ownerName = ownerName;
        this.model = model;
        this.year = year;
        this.registrationStatus = "Pending";
    }

    public String getPlateNumber() { return plateNumber; }
    public String getOwnerName() { return ownerName; }
    public String getModel() { return model; }
    public int getYear() { return year; }
    public String getRegistrationStatus() { return registrationStatus; }

    public void setOwnerName(String ownerName) { this.ownerName = ownerName; }
    public void setModel(String model) { this.model = model; }
    public void setYear(int year) { this.year = year; }
    public void setRegistrationStatus(String registrationStatus) {
        this.registrationStatus = registrationStatus;
    }

    public abstract String getType();

    @Override
    public String toString() {
        return "Type: " + getType()
                + "\nPlate Number: " + plateNumber
                + "\nOwner: " + ownerName
                + "\nModel: " + model
                + "\nYear: " + year
                + "\nRegistration Status: " + registrationStatus;
    }
}
