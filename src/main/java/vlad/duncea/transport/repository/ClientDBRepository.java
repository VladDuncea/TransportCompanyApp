package vlad.duncea.transport.repository;

import vlad.duncea.transport.model.City;
import vlad.duncea.transport.model.Client;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ClientDBRepository implements ClientRepositoryInterface {

    Connection connection;

    public ClientDBRepository(Connection connection)
    {
        this.connection = connection;
    }

    @Override
    public void addClient(Client c) throws SQLException {
        String sqlAdd = "INSERT INTO clients VALUES (NULL,?, ?, ?)";

        PreparedStatement statement =  connection.prepareStatement(sqlAdd);
        statement.setString(1, c.getFirstName());
        statement.setString(2, c.getLastName());
        statement.setString(3, c.getPhoneNumber());

        statement.executeUpdate();
    }

    @Override
    public void removeClient(Client c) throws SQLException {
        removeClient(c.getId());
    }

    @Override
    public void removeClient(int id) throws SQLException {
        String sqlDelete = "DELETE FROM clients WHERE id = ?";
        PreparedStatement statement =  connection.prepareStatement(sqlDelete);
        statement.setInt(1, id);
        statement.executeUpdate();
    }

    @Override
    public Client getClientById(int id) throws SQLException {
        String sqlSelect = "SELECT * FROM clients WHERE id = ?";
        PreparedStatement statement = connection.prepareStatement(sqlSelect);
        ResultSet result = statement.executeQuery();
        if (result.next()) {
            return  new Client(result.getInt(1), result.getString(2), result.getString(3),result.getString(4));
        }
        else
        {
            return null;
        }
    }

    @Override
    public ArrayList<Client> getClients() throws SQLException {
        ArrayList<Client> clients = new ArrayList<>();

        String sqlSelect = "SELECT * FROM clients";
        PreparedStatement statement =  connection.prepareStatement(sqlSelect);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next())    {
            Client c = new Client(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3),resultSet.getString(4));
            clients.add(c);
        }
        return clients;
    }
}
