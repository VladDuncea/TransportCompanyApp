package vlad.duncea.transport.service;

import vlad.duncea.transport.main.Main;
import vlad.duncea.transport.model.Car;
import vlad.duncea.transport.model.Client;
import vlad.duncea.transport.model.Driver;
import vlad.duncea.transport.repository.ClientRepository;
import vlad.duncea.transport.repository.DriverRepository;

import java.util.ArrayList;
import java.util.Scanner;

public class DriverService
{
    private DriverRepository driverRepository;

    public DriverService()
    {
        driverRepository = new DriverRepository();
    }

    public Driver addDriver(Scanner s)
    {
        System.out.println("Enter first name: ");
        String firstName = s.next();

        System.out.println("Enter last name: ");
        String lastName = s.next();

        System.out.println("Enter phone number: ");
        String phoneNr = s.next();

        System.out.println("Enter car registration number('None' for none): ");
        String regNr = s.next();
        Car c = Main.carService.getCarByReg(regNr);

        System.out.println("Enter salary: ");
        float salary = s.nextFloat();

        Driver d = new Driver(driverRepository.getLastId(), firstName, lastName, phoneNr, c, salary);
        driverRepository.addDriver(d);

        return d;
    }

    public void removeDriver(Scanner s)
    {
        System.out.println("Enter driver ID: ");
        driverRepository.removeDriverById(s.nextInt());
    }

    public Driver getDriverById(int id)
    {
        for(Driver d : driverRepository.getDrivers())
            if(d.getId() == id)
            {
                return d;
            }
        return null;
    }

    public ArrayList<Driver> getDriversByCar(Car c)
    {
        ArrayList<Driver> response = new ArrayList<>();
        for(Driver d : driverRepository.getDrivers())
            if(d.getCar().equals(c))
            {
                response.add(d);
            }
        return response;
    }

    public void giveCarToDriver(Scanner s)
    {
        System.out.println("Enter Driver id: ");
        int driverId = s.nextInt();

        System.out.println("Enter Car reg nr: ");
        Car c = Main.carService.getCarByReg(s.next());

        Driver d = getDriverById(driverId);

        if(d != null&& c != null)
            d.setCar(c);
        else
            System.out.println("Driver/Car doesn't exist! No changes made");
    }

    public String allDrivers()
    {
        StringBuilder res = new StringBuilder();
        for(Driver d : driverRepository.getDrivers())
        {
            res.append(d.toString()).append("\n");
        }
        return res.toString();
    }

    public ArrayList<Driver> getDrivers()
    {
        return driverRepository.getDrivers();
    }
}
