package vlad.duncea.transport.repository;

import vlad.duncea.transport.model.City;
import vlad.duncea.transport.model.Driver;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DriverDBRepository implements DriverRepositoryInterface {

    Connection connection;

    public DriverDBRepository(Connection connection)
    {
        this.connection = connection;
    }

    @Override
    public void addDriver(Driver d) throws SQLException {
        String sqlAdd = "INSERT INTO drivers VALUES (NULL, ?, ?, ?, ?, ?)";

        PreparedStatement statement =  connection.prepareStatement(sqlAdd);
        statement.setString(1, d.getFirstName());
        statement.setString(2, d.getLastName());
        statement.setString(3, d.getPhoneNumber());
        statement.setString(4, d.getCarRegNr());
        statement.setFloat(5, d.getSalary());

        statement.executeUpdate();
    }

    @Override
    public void removeDriver(Driver d) throws SQLException {
        removeDriverById(d.getId());
    }

    @Override
    public void removeDriverById(int id) throws SQLException {
        String sqlDelete = "DELETE FROM drivers WHERE id = ?";
        PreparedStatement statement =  connection.prepareStatement(sqlDelete);
        statement.setInt(1, id);
        statement.executeUpdate();
    }

    @Override
    public Driver getDriverById(int id) throws SQLException {
        String sql = "SELECT * FROM drivers WHERE id = ?";
        PreparedStatement statement =  connection.prepareStatement(sql);
        statement.setInt(1, id);
        statement.executeUpdate();
        ResultSet resultSet = statement.executeQuery();
        if(resultSet.next())
        {
            return new Driver(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3),
                    resultSet.getString(4), resultSet.getString(5), resultSet.getFloat(6));
        }
        return null;
    }

    @Override
    public boolean giveCarToDriver(int driverId, String regNr) throws SQLException {
        String sql = "UPDATE drivers SET carRegNr = ? WHERE id = ?";
        PreparedStatement statement =  connection.prepareStatement(sql);
        statement.setString(1, regNr);
        statement.setInt(2, driverId);
        statement.executeUpdate();
        return true;
    }

    @Override
    public ArrayList<Driver> getDrivers() throws SQLException {
        ArrayList<Driver> drivers = new ArrayList<>();

        String sqlSelect = "SELECT * FROM drivers";
        PreparedStatement statement =  connection.prepareStatement(sqlSelect);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next())    {
            Driver d = new Driver(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3),
                        resultSet.getString(4), resultSet.getString(5), resultSet.getFloat(6));
            drivers.add(d);
        }
        return drivers;
    }
}
