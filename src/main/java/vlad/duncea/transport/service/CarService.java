package vlad.duncea.transport.service;

import vlad.duncea.transport.model.Car;
import vlad.duncea.transport.repository.CarRepository;

import java.util.Scanner;

public class CarService
{
    private CarRepository carRepository;

    public CarService()
    {
        carRepository = new CarRepository();
    }

    public Car addCar(Scanner s)
    {
        System.out.println("Enter car reg number: ");
        String regnr = s.next();

        System.out.println("Enter car volume: ");
        float volume = s.nextFloat();

        //TODO: add route here
        //System.out.println("Enter car route: ");

        Car c = new Car(regnr,volume, null);
        carRepository.addCar(c);

        return c;
    }

    public void removeCar(Scanner s)
    {
        System.out.println("Enter registratio nr:");
        if(carRepository.removeCar(s.next()))
            System.out.println("Car removed successfully!");
        else
            System.out.println("Car has drivers assigned to it, first remove those drivers!");
    }

    public Car getCarByReg(String regNr)
    {
        for(Car c : carRepository.getCars()) {
            if(c.getRegistration_nr().equals(regNr))
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
        return res.toString();
    }
}
