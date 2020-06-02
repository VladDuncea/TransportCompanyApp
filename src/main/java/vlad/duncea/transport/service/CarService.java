package vlad.duncea.transport.service;

import vlad.duncea.transport.main.Main;
import vlad.duncea.transport.model.Car;
import vlad.duncea.transport.model.Route;
import vlad.duncea.transport.repository.CarDBRepository;
import vlad.duncea.transport.repository.CarRepository;
import vlad.duncea.transport.repository.CarRepositoryInterface;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class CarService
{
    private CarRepositoryInterface carRepository;
    private AuditService auditService;

    public CarService()
    {
        //Use correct repo
        carRepository = Main.USE_DATABASE ? new CarDBRepository(): new CarRepository();

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
        try {
            carRepository.addCar(c);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        auditService.logData("CarService_addCar");
        return c;
    }

    public void removeCar(Scanner s)
    {
        System.out.println("Enter registratio nr:");
        try {
            if(carRepository.removeCar(s.next()))
                System.out.println("Car removed successfully!");
            else
                System.out.println("Car has drivers assigned to it, first remove those drivers!");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        auditService.logData("CarService_removeCar");
    }

    public Car getCarByReg(String regNr)
    {
        auditService.logData("CarService_getCarByReg");
        try {
            return carRepository.getCarByRegNr(regNr);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String allCars()
    {
        StringBuilder res = new StringBuilder();
        try {
            for(Car c : carRepository.getCars())
            {
                res.append(c.toString()).append("\n");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        auditService.logData("CarService_allCars");
        return res.toString();
    }

    public CarRepositoryInterface getCarRepository() {
        return carRepository;
    }
}
