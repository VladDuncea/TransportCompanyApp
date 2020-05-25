package vlad.duncea.transport.service;

import vlad.duncea.transport.model.Car;
import vlad.duncea.transport.model.Client;
import vlad.duncea.transport.repository.ClientDBRepository;
import vlad.duncea.transport.repository.ClientRepository;
import vlad.duncea.transport.repository.ClientRepositoryInterface;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class ClientService
{
    private ClientRepositoryInterface clientRepository;
    private AuditService auditService;

    public ClientService()
    {
        this(null);
    }

    public ClientService(Connection connection)
    {
        if(connection != null)
        {
            clientRepository = new ClientDBRepository(connection);
        }
        else
        {
            clientRepository = new ClientRepository();
        }

        auditService = AuditService.getAuditService();
    }

    public Client addClient(Scanner s)
    {
        System.out.println("Enter first name: ");
        String first_name = s.next();

        System.out.println("Enter last name: ");
        String last_name = s.next();

        System.out.println("Enter phone number: ");
        String phone_nr = s.next();

        Client c = new Client(-1, first_name, last_name, phone_nr);
        try {
            clientRepository.addClient(c);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        auditService.logData("ClientService_addClient");
        return c;
    }

    public void removeClient(Scanner s)
    {
        System.out.println("Enter client ID: ");
        try {
            clientRepository.removeClient(s.nextInt());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        auditService.logData("ClientService_removeClient");
    }

    public Client getClientById(int id)
    {
        auditService.logData("ClientService_getClientById");

        try {
            return clientRepository.getClientById(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }



    public String allClients()
    {
        auditService.logData("ClientService_allClients");

        StringBuilder res = new StringBuilder();
        try {
            for(Client c : clientRepository.getClients())
            {
                res.append(c.toString()).append("\n");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return res.toString();
    }

    public ClientRepositoryInterface getClientRepository() {
        return clientRepository;
    }
}
