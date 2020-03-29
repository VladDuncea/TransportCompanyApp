package vlad.duncea.transport.service;

import vlad.duncea.transport.model.Car;
import vlad.duncea.transport.model.Client;
import vlad.duncea.transport.repository.ClientRepository;

import java.util.Scanner;

public class ClientService
{
    private ClientRepository clientRepository;

    public ClientService()
    {
        clientRepository = new ClientRepository();
    }

    public Client addClient(Scanner s)
    {
        System.out.println("Enter first name: ");
        String first_name = s.next();

        System.out.println("Enter last name: ");
        String last_name = s.next();

        System.out.println("Enter phone number: ");
        String phone_nr = s.next();

        Client c = new Client(clientRepository.getLast_id(), first_name, last_name, phone_nr);
        clientRepository.addClient(c);

        return c;
    }

    public void removeClient(Scanner s)
    {
        System.out.println("Enter client ID: ");
        clientRepository.removeClient(s.nextInt());
    }

    public String allClients()
    {
        StringBuilder res = new StringBuilder();
        for(Client c : clientRepository.getClients())
        {
            res.append(c.toString()).append("\n");
        }
        return res.toString();
    }
}
