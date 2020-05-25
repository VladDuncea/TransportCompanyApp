package vlad.duncea.transport.repository;

import vlad.duncea.transport.model.Client;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ClientRepositoryInterface {

    public void addClient(Client c) throws SQLException;

    public void removeClient(Client c) throws SQLException;
    public void removeClient(int id) throws SQLException;

    public Client getClientById(int id) throws SQLException;

    public ArrayList<Client> getClients() throws SQLException;
}
