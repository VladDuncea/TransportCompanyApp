package vlad.duncea.transport.repository;

import vlad.duncea.transport.model.Client;

import java.util.ArrayList;

public class ClientRepository implements ClientRepositoryInterface
{
    private int last_id;
    private ArrayList<Client> clients;

    public ClientRepository()
    {
        clients = new ArrayList<>();
        last_id = 0;
    }

    public ClientRepository(ArrayList<Client> clients) {
        this.clients = clients;
    }

    public void addClient(Client c)
    {
        if(c.getId() == -1)
            c.setId(last_id);
        clients.add(c);
        last_id += 1;
    }

    public void removeClient(Client c)
    {
        clients.remove(c);
    }
    public void removeClient(int id)
    {
        for (Client c : clients)
            if(c.getId() == id) {
                clients.remove(c);
                break;
            }
    }

    public Client getClientById(int id)
    {
        for(Client c: clients)
            if(c.getId() == id)
            {
                return c;
            }
        return null;
    }

    public ArrayList<Client> getClients() {
        return clients;
    }

    public int getLast_id() {
        return last_id;
    }
}
