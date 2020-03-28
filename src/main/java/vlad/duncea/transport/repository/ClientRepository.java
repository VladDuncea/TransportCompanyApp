package vlad.duncea.transport.repository;

import vlad.duncea.transport.model.Client;

import java.util.ArrayList;

public class ClientRepository
{
    ArrayList<Client> clients;

    public ClientRepository()
    {
        clients = new ArrayList<>();
    }

    public ClientRepository(ArrayList<Client> clients) {
        this.clients = clients;
    }

    public void addClient(Client c)
    {
        clients.add(c);
    }

    public void removeClient(Client c)
    {
        clients.remove(c);
    }

    public ArrayList<Client> getClients() {
        return clients;
    }
}
