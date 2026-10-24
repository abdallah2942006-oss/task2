# Vehicle Registration System

A Java console application organized as a Maven project, following the package structure shown in the project screenshot.

## Project structure

```
VehicleRegistrationSystem/
├── .gitignore
├── pom.xml
└── src/
    └── main/
        └── java/
            └── vehicle/
                └── registration/
                    ├── Main.java
                    ├── exception/
                    │   ├── DuplicatePlateException.java
                    │   ├── InvalidInputException.java
                    │   └── VehicleNotFoundException.java
                    ├── model/
                    │   ├── Car.java
                    │   ├── Motorcycle.java
                    │   ├── Truck.java
                    │   └── Vehicle.java
                    ├── service/
                    │   ├── ApplicationService.java
                    │   ├── RegistrationService.java
                    │   └── impl/
                    │       ├── RegistrationServiceImpl.java
                    │       └── VehicleApplicationServiceImpl.java
                    └── util/
                        └── InputValidator.java
```

## Features

1. Register a car, motorcycle, or truck
2. View all vehicles
3. Search by plate number
4. Search by owner name
5. Update vehicle details
6. Delete a vehicle
7. Change registration status
8. Count registered vehicles
9. Filter vehicles by type
10. Exit

## Java concepts

- OOP, encapsulation, inheritance, and polymorphism
- Interfaces and service-layer separation
- Collections and streams
- Custom exceptions
- Input validation and exception handling

## Run in IntelliJ IDEA

1. Open the `VehicleRegistrationSystem` folder as a project.
2. Allow IntelliJ to load the Maven project.
3. Open `src/main/java/vehicle/registration/Main.java`.
4. Run the `main` method.

Vehicle data is kept in memory and is cleared when the application exits.
