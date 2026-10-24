package vehicle.registration;

import vehicle.registration.service.ApplicationService;
import vehicle.registration.service.RegistrationService;
import vehicle.registration.service.impl.RegistrationServiceImpl;
import vehicle.registration.service.impl.VehicleApplicationServiceImpl;

public class Main {
    public static void main(String[] args) {
        RegistrationService registrationService = new RegistrationServiceImpl();
        ApplicationService applicationService =
                new VehicleApplicationServiceImpl(registrationService);
        applicationService.start();
    }
}
