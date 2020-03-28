package vlad.duncea.transport.main;

import com.sun.source.tree.WhileLoopTree;
import vlad.duncea.transport.service.CarService;
import vlad.duncea.transport.service.ClientService;

import java.util.Scanner;

public class Main
{
    static CarService carService;
    static ClientService clientService;


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
                System.out.println("--Main Menu--1");
                System.out.println("Options:");
                System.out.println("1-Cars");
                System.out.println("2-Clients");
                System.out.println("9-Exit");

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
                System.out.println("5-See all cars");
                System.out.println("9-Back");

                input = scanner.nextInt();

                if(input == 1)
                {
                    carService.addCar(scanner);
                }
                else if (input == 5)
                {
                    System.out.println(carService.allCars());
                }
                else if(input == 9)
                    menuLocation = 0;
            }


        }
    }

    public static void main(String[] args)
    {
        carService = new CarService();
        clientService = new ClientService();
        menuLoop();
    }

}
