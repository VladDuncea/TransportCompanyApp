package vlad.duncea.transport.main;

import com.sun.source.tree.WhileLoopTree;
import vlad.duncea.transport.model.Car;
import vlad.duncea.transport.model.City;
import vlad.duncea.transport.model.Driver;
import vlad.duncea.transport.service.CarService;
import vlad.duncea.transport.service.CityService;
import vlad.duncea.transport.service.ClientService;
import vlad.duncea.transport.service.DriverService;

import java.util.ArrayList;
import java.util.Scanner;

public class Main
{
    public static CarService carService;
    public static ClientService clientService;
    public static CityService cityService;
    public static DriverService driverService;

    public static void menuLoop()
    {
        Scanner scanner = new Scanner(System.in);

        //0 - main menu
        int menuLocation = 0;
        int input;
        while(true)
        {
            //Main menu
            if(menuLocation == 0)
            {
                System.out.println("--Main Menu--");
                System.out.println("Options:");
                System.out.println("1-Cars");
                System.out.println("2-Cities");
                System.out.println("3-Clients");
                System.out.println("4-Drivers");
                System.out.println("0-Exit");

                //Read input
                menuLocation = scanner.nextInt();

                if(menuLocation == 9)
                {
                    return;
                }
            }
            else if(menuLocation == 1)      //Cars
            {
                System.out.println("--Cars Menu--");
                System.out.println("1-Add Car");
                System.out.println("2-Remove Car");
                System.out.println("5-See all cars");
                System.out.println("0-Back");

                input = scanner.nextInt();

                if(input == 1)
                {
                    carService.addCar(scanner);
                }
                else if(input == 2)
                {
                    carService.removeCar(scanner);
                }
                else if (input == 5)
                {
                    System.out.println(carService.allCars());
                }
                else if(input == 9)
                    menuLocation = 0;
            }
            else if(menuLocation == 2)      //Clients
            {
                System.out.println("--City Menu--");
                System.out.println("1-Add City");
                System.out.println("2-Remove City");
                System.out.println("5-See all cities");
                System.out.println("0-Back");

                input = scanner.nextInt();

                if(input == 1)
                {
                    cityService.addCity(scanner);
                }
                else if(input == 2)
                {
                    cityService.removeCity(scanner);
                }
                else if (input == 5)
                {
                    System.out.println(cityService.allCities());
                }
                else if(input == 9)
                    menuLocation = 0;
            }
            else if(menuLocation == 3)      //Clients
            {
                System.out.println("--Clients Menu--");
                System.out.println("1-Add Client");
                System.out.println("2-Remove Client");
                System.out.println("5-See all clients");
                System.out.println("0-Back");

                input = scanner.nextInt();

                if(input == 1)
                {
                    clientService.addClient(scanner);
                }
                else if(input == 2)
                {
                    clientService.removeClient(scanner);
                }
                else if (input == 5)
                {
                    System.out.println(clientService.allClients());
                }
                else if(input == 0)
                    menuLocation = 0;
            }
            else if(menuLocation == 4)      //Drivers
            {
                System.out.println("--Drivers Menu--");
                System.out.println("1-Add Driver");
                System.out.println("2-Remove Driver");
                System.out.println("3-Give car to driver");
                System.out.println("4-Remove car from driver");
                System.out.println("5-See all drivers");
                System.out.println("6-See drivers by car");
                System.out.println("0-Back");

                input = scanner.nextInt();

                if(input == 1)
                {
                    driverService.addDriver(scanner);
                }
                else if(input == 2)
                {
                    driverService.removeDriver(scanner);
                }
                else if(input == 3)
                {
                    driverService.giveCarToDriver(scanner);
                }
                else if(input == 4)
                {
                    System.out.println("Enter driver id: ");
                    Driver d = driverService.getDriverById(scanner.nextInt());

                    if(d!=null)
                        d.setCar(null);
                }
                else if (input == 5)
                {
                    System.out.println(driverService.allDrivers());
                }
                else if (input == 6)
                {
                    System.out.println("Enter car reg nr: ");
                    Car c = carService.getCarByReg(scanner.next());
                    ArrayList<Driver> drivers = driverService.getDriversByCar(c);
                    if(drivers.isEmpty())
                        System.out.println("No drivers for that car!");
                    else
                    {
                        for (Driver d : drivers)
                            System.out.println(d);
                    }
                }
                else if(input == 0)
                    menuLocation = 0;
            }
        }
    }

    public static void main(String[] args)
    {
        carService = new CarService();
        clientService = new ClientService();
        cityService = new CityService();
        driverService = new DriverService();
        menuLoop();
    }

}
