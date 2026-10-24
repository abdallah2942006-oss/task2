package vehicle.registration.service;

import vehicle.registration.exception.DuplicatePlateException;
import vehicle.registration.exception.VehicleNotFoundException;
import vehicle.registration.model.Vehicle;

import java.util.List;

public interface RegistrationService {
    void registerVehicle(Vehicle vehicle) throws DuplicatePlateException;
    List<Vehicle> getAllVehicles();
    Vehicle findByPlate(String plateNumber) throws VehicleNotFoundException;
    List<Vehicle> findByOwner(String ownerName);
    void deleteVehicle(String plateNumber) throws VehicleNotFoundException;
    int getVehicleCount();
    List<Vehicle> findByType(String type);
    void updateStatus(String plateNumber, String status) throws VehicleNotFoundException;
}
