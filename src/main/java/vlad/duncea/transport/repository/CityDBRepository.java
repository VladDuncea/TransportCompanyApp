package vlad.duncea.transport.repository;

import vlad.duncea.transport.model.Car;
import vlad.duncea.transport.model.City;

import java.net.ConnectException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CityDBRepository implements CityRepositoryInterface {

    private Connection connection;

    public CityDBRepository(Connection connection)
    {
        this.connection = connection;
    }

    @Override
    public void addCity(City c) throws SQLException {
       String sqlAdd = "INSERT INTO cities VALUES (?, ?, ?)";

        PreparedStatement statement =  connection.prepareStatement(sqlAdd);
        statement.setString(1, c.getName());
        statement.setFloat(2, c.getLongitude());
        statement.setFloat(3, c.getLatitude());

        statement.executeUpdate();
    }

    @Override
    public void removeCity(City c) throws SQLException {
        removeCity(c.getName());
    }

    @Override
    public void removeCity(String name) throws SQLException {
        String sqlDelete = "DELETE FROM cities WHERE name = ?";
        PreparedStatement statement =  connection.prepareStatement(sqlDelete);
        statement.setString(1, name);
        statement.executeUpdate();
    }

    @Override
    public ArrayList<City> getCities() throws SQLException {
        ArrayList<City> cities = new ArrayList<>();

        String sqlSelect = "SELECT * FROM cities";
        PreparedStatement statement =  connection.prepareStatement(sqlSelect);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next())    {
            City c = new City(resultSet.getString(1), resultSet.getFloat(2), resultSet.getFloat(3));
            cities.add(c);
        }
        return cities;
    }
}
