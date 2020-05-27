package vlad.duncea.transport.service;

import vlad.duncea.transport.main.Main;
import vlad.duncea.transport.model.Car;
import vlad.duncea.transport.model.Client;
import vlad.duncea.transport.model.Driver;
import vlad.duncea.transport.repository.ClientRepository;
import vlad.duncea.transport.repository.DriverDBRepository;
import vlad.duncea.transport.repository.DriverRepository;
import vlad.duncea.transport.repository.DriverRepositoryInterface;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class DriverService
{
    private DriverRepositoryInterface driverRepository;
    private AuditService auditService;

    public DriverService()
    {
        this(null);
    }

    public DriverService(Connection connection)
    {
        if(connection!=null)
        {
            driverRepository = new DriverDBRepository(connection);
        }
        else
        {
            driverRepository = new DriverRepository();
        }
        auditService = AuditService.getAuditService();
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
        if(!regNr.equals("None"))
        {
            Car c = Main.carService.getCarByReg(regNr);
            if(c == null)
            {
                System.out.println("There is no car with that reg nr!");
                return null;
            }
        }
        else
            regNr = null;

        System.out.println("Enter salary: ");
        float salary = s.nextFloat();

        Driver d = new Driver(-1, firstName, lastName, phoneNr, regNr, salary);
        try {
            driverRepository.addDriver(d);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        auditService.logData("DriverService_addDriver");
        return d;
    }

    public void removeDriver(Scanner s)
    {
        System.out.println("Enter driver ID: ");
        try {
            driverRepository.removeDriverById(s.nextInt());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        auditService.logData("DriverService_removeDriver");
    }

    public Driver getDriverById(int id)
    {
        try {
            for(Driver d : driverRepository.getDrivers())
                if(d.getId() == id)
                {
                    return d;
                }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        auditService.logData("DriverService_getDriverById");
        return null;
    }

    public ArrayList<Driver> getDriversByCar(Car c)
    {
        ArrayList<Driver> response = new ArrayList<>();
        try {
            for(Driver d : driverRepository.getDrivers())
                if(d.getCarRegNr()!=null && d.getCarRegNr().equals(c.getRegistrationNr()))
                {
                    response.add(d);
                }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        auditService.logData("DriverService_getDriversByCar");
        return response;
    }

    public void giveCarToDriver(Scanner s)
    {
        System.out.println("Enter Driver id: ");
        int driverId = s.nextInt();

        System.out.println("Enter Car reg nr: ");
        String regNr = s.next();

        try {
            if(driverRepository.giveCarToDriver(driverId,regNr))
                System.out.println("Update performed!");
            else
                System.out.println("Driver/Car doesn't exist! No changes made.");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        auditService.logData("DriverService_giveCarToDriver");
    }

    public String allDrivers()
    {
        auditService.logData("DriverService_allDrivers");

        StringBuilder res = new StringBuilder();
        try {
            for(Driver d : driverRepository.getDrivers())
            {
                res.append(d.toString()).append("\n");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return res.toString();
    }

    public ArrayList<Driver> getDrivers()
    {
        auditService.logData("DriverService_getDrivers");
        try {
            return driverRepository.getDrivers();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public DriverRepositoryInterface getDriverRepository() {
        return driverRepository;
    }
}
