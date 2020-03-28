package vlad.duncea.transport.service;

import vlad.duncea.transport.model.Client;
import vlad.duncea.transport.repository.ClientRepository;

public class ClientService
{
    private ClientRepository clientRepository;

    public ClientService()
    {
        clientRepository = new ClientRepository();
    }

    public void addClient(Client c)
    {
        clientRepository.addClient(c);
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
