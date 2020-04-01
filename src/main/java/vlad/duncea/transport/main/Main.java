package vlad.duncea.transport.main;

import com.sun.source.tree.WhileLoopTree;
import vlad.duncea.transport.model.*;
import vlad.duncea.transport.model.Package;
import vlad.duncea.transport.service.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Main
{
    public static CarService carService;
    public static ClientService clientService;
    public static CityService cityService;
    public static DriverService driverService;
    public static LinkService linkService;
    public static PackageService packageService;
    public static TransportService transportService;

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
                System.out.println("5-Links");
                System.out.println("6-Packages");
                System.out.println("8-Transports");
                System.out.println("0-Exit");

                //Read input
                menuLocation = scanner.nextInt();

                if(menuLocation == 0)
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
                else if(input == 0)
                    menuLocation = 0;
            }
            else if(menuLocation == 2)      //City
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
                else if(input == 0)
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
            else if(menuLocation == 5)      //Links
            {
                System.out.println("--Links Menu--");
                System.out.println("1-Add Link");
                System.out.println("2-Remove Link");
                System.out.println("3-Shortest link between two cities");
                System.out.println("5-See all links");
                System.out.println("0-Back");

                input = scanner.nextInt();

                if(input == 1)
                {
                    linkService.addLink(scanner);
                }
                else if(input == 2)
                {
                    System.out.println("Enter link id: ");
                    linkService.removeLinkById(scanner.nextInt());
                }
                else if(input == 3)
                {
                    System.out.println("Enter first city name: ");
                    City c1 = cityService.getCityByName(scanner.next());

                    System.out.println("Enter first city name: ");
                    City c2 = cityService.getCityByName(scanner.next());

                    System.out.println("1-By distance\n2-By time");
                    int n = scanner.nextInt();

                    System.out.println(linkService.getShortestLink(c1,c2,n==2));

                }
                else if (input == 5)
                {
                    System.out.println(linkService.allLinks());
                }
                else if(input == 0)
                    menuLocation = 0;
            }
            else if(menuLocation == 6)      //Packages
            {
                System.out.println("--Packages Menu--");
                System.out.println("1-Add Package");
                //System.out.println("2-Remove Package");
                System.out.println("3-See packages for client");
                //System.out.println("5-See all Packages");
                System.out.println("0-Back");

                input = scanner.nextInt();

                if(input == 1)
                {
                    packageService.addPackage(scanner);
                }
                else if(input == 2)
                {
                    //packageService.removePackageById(scanner);
                }
                else if(input == 3)
                {
                    System.out.println("Enter client id: ");
                    Client c = clientService.getClientById(scanner.nextInt());

                    ArrayList<vlad.duncea.transport.model.Package> packages = packageService.getPackagesForClient(c);
                    if(packages.isEmpty())
                        System.out.println("No packages");
                    else
                    {
                        for(Package p : packages)
                            System.out.println(p);
                    }
                }
                else if (input == 5)
                {
                    //System.out.println(packageService.allPackages());
                }
                else if(input == 0)
                    menuLocation = 0;
            }
            else if(menuLocation == 6)      //Transports
            {
                System.out.println("--Transports Menu--");
                System.out.println("1-Add Transport");
                System.out.println("2-Remove Transport");
                System.out.println("3-See transports for date");
                System.out.println("5-See all Transports");
                System.out.println("0-Back");

                input = scanner.nextInt();

                if(input == 1)
                {
                    transportService.addTransport();
                }
                else if(input == 2)
                {
                    System.out.println("Enter transport ID: ");
                    transportService.removeTransportByID(scanner.nextInt());
                }
                else if(input == 3)
                {
                    System.out.println("Enter date: ");
                    try{
                        Date d = new SimpleDateFormat("dd/MM/yyyy").parse(scanner.next());


                        ArrayList<Transport> transports = transportService.getTransportsByDate(d);
                        if(transports.isEmpty())
                            System.out.println("No transports");
                        else
                        {
                            for(Transport t : transports)
                                System.out.println(t);
                        }
                    }
                    catch (Exception e) {
                        System.out.println("Wrong date input");
                    }
                }
                else if (input == 5)
                {
                    System.out.println(transportService.allTransports());
                }
                else if(input == 0)
                    menuLocation = 0;
            }
        }

        //TODO: save data on clean exit
    }

    public static void main(String[] args)
    {
        carService = new CarService();
        clientService = new ClientService();
        cityService = new CityService();
        driverService = new DriverService();
        linkService = new LinkService();
        packageService = new PackageService();
        transportService = new TransportService();
        menuLoop();
    }

}
