package vlad.duncea.transport.service;

import vlad.duncea.transport.model.Car;
import vlad.duncea.transport.model.Route;
import vlad.duncea.transport.repository.CarRepository;

import java.util.Scanner;

public class CarService
{
    private CarRepository carRepository;
    private AuditService auditService;

    public CarService()
    {
        carRepository = new CarRepository();
        auditService = AuditService.getAuditService();
    }

    public Car addCar(Scanner s)
    {
        System.out.println("Enter car reg number: ");
        String regnr = s.next();

        System.out.println("Enter car volume: ");
        float volume = s.nextFloat();

        //TODO: add route when implemented
        //System.out.println("Enter car routeId(-1 for none): ");
        //Route r = Main.ser;

        Car c = new Car(regnr,volume, -1);
        carRepository.addCar(c);

        auditService.logData("CarService_addCar");
        return c;
    }

    public void removeCar(Scanner s)
    {
        System.out.println("Enter registratio nr:");
        if(carRepository.removeCar(s.next()))
            System.out.println("Car removed successfully!");
        else
            System.out.println("Car has drivers assigned to it, first remove those drivers!");

        auditService.logData("CarService_removeCar");
    }

    public Car getCarByReg(String regNr)
    {
        auditService.logData("CarService_getCarByReg");
        for(Car c : carRepository.getCars()) {
            if(c.getRegistrationNr().equals(regNr))
                return c;
        }
        return null;
    }

    public String allCars()
    {
        StringBuilder res = new StringBuilder();
        for(Car c : carRepository.getCars())
        {
            res.append(c.toString()).append("\n");
        }

        auditService.logData("CarService_allCars");
        return res.toString();
    }

    public CarRepository getCarRepository() {
        return carRepository;
    }
}
