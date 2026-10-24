package vehicle.registration.service.impl;

import vehicle.registration.exception.DuplicatePlateException;
import vehicle.registration.exception.VehicleNotFoundException;
import vehicle.registration.model.Vehicle;
import vehicle.registration.service.RegistrationService;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public class RegistrationServiceImpl implements RegistrationService {
    private final List<Vehicle> vehicles = new ArrayList<>();

    @Override
    public void registerVehicle(Vehicle vehicle) throws DuplicatePlateException {
        boolean exists = vehicles.stream().anyMatch(existing ->
                existing.getPlateNumber().equalsIgnoreCase(vehicle.getPlateNumber()));
        if (exists) {
            throw new DuplicatePlateException(
                    "A vehicle with plate " + vehicle.getPlateNumber() + " already exists.");
        }
        vehicles.add(vehicle);
    }

    @Override
    public List<Vehicle> getAllVehicles() {
        return new ArrayList<>(vehicles);
    }

    @Override
    public Vehicle findByPlate(String plateNumber) throws VehicleNotFoundException {
        return vehicles.stream()
                .filter(vehicle -> vehicle.getPlateNumber().equalsIgnoreCase(plateNumber))
                .findFirst()
                .orElseThrow(() -> new VehicleNotFoundException(
                        "No vehicle found with plate " + plateNumber + "."));
    }

    @Override
    public List<Vehicle> findByOwner(String ownerName) {
        String query = ownerName.toLowerCase(Locale.ROOT);
        return vehicles.stream()
                .filter(vehicle -> vehicle.getOwnerName().toLowerCase(Locale.ROOT).contains(query))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteVehicle(String plateNumber) throws VehicleNotFoundException {
        Vehicle vehicle = findByPlate(plateNumber);
        vehicles.remove(vehicle);
    }

    @Override
    public int getVehicleCount() {
        return vehicles.size();
    }

    @Override
    public List<Vehicle> findByType(String type) {
        return vehicles.stream()
                .filter(vehicle -> vehicle.getType().equalsIgnoreCase(type))
                .collect(Collectors.toList());
    }

    @Override
    public void updateStatus(String plateNumber, String status)
            throws VehicleNotFoundException {
        Vehicle vehicle = findByPlate(plateNumber);
        vehicle.setRegistrationStatus(status);
    }
}
