package vlad.duncea.transport.repository;

import vlad.duncea.transport.model.Car;
import vlad.duncea.transport.model.City;
import vlad.duncea.transport.model.Driver;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DriverDBRepository implements DriverRepositoryInterface {

    @Override
    public void addDriver(Driver d) throws SQLException {
        Connection connection = DbConnectionUtil.getDBConnection();
        String sqlAdd = "INSERT INTO drivers VALUES (NULL, ?, ?, ?, ?, ?)";

        PreparedStatement statement =  connection.prepareStatement(sqlAdd);
        statement.setString(1, d.getFirstName());
        statement.setString(2, d.getLastName());
        statement.setString(3, d.getPhoneNumber());
        statement.setString(4, d.getCarRegNr());
        statement.setFloat(5, d.getSalary());

        statement.executeUpdate();
        DbConnectionUtil.closeDBConnection(connection);
    }

    @Override
    public void removeDriver(Driver d) throws SQLException {
        removeDriverById(d.getId());
    }

    @Override
    public void removeDriverById(int id) throws SQLException {
        Connection connection = DbConnectionUtil.getDBConnection();
        String sqlDelete = "DELETE FROM drivers WHERE id = ?";
        PreparedStatement statement =  connection.prepareStatement(sqlDelete);
        statement.setInt(1, id);
        statement.executeUpdate();
        DbConnectionUtil.closeDBConnection(connection);
    }

    @Override
    public Driver getDriverById(int id) throws SQLException {
        Connection connection = DbConnectionUtil.getDBConnection();
        String sql = "SELECT * FROM drivers WHERE id = ?";
        PreparedStatement statement =  connection.prepareStatement(sql);
        statement.setInt(1, id);
        statement.executeUpdate();
        ResultSet resultSet = statement.executeQuery();
        if(resultSet.next())
        {
            DbConnectionUtil.closeDBConnection(connection);
            return new Driver(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3),
                    resultSet.getString(4), resultSet.getString(5), resultSet.getFloat(6));
        }
        DbConnectionUtil.closeDBConnection(connection);
        return null;
    }

    @Override
    public boolean giveCarToDriver(int driverId, String regNr) throws SQLException {
        Connection connection = DbConnectionUtil.getDBConnection();
        String sql = "UPDATE drivers SET carRegNr = ? WHERE id = ?";
        PreparedStatement statement =  connection.prepareStatement(sql);
        statement.setString(1, regNr);
        statement.setInt(2, driverId);
        statement.executeUpdate();
        DbConnectionUtil.closeDBConnection(connection);
        return true;
    }

    @Override
    public ArrayList<Driver> getDrivers() throws SQLException {
        ArrayList<Driver> drivers = new ArrayList<>();
        Connection connection = DbConnectionUtil.getDBConnection();

        String sqlSelect = "SELECT * FROM drivers";
        PreparedStatement statement =  connection.prepareStatement(sqlSelect);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next())    {
            Driver d = new Driver(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3),
                        resultSet.getString(4), resultSet.getString(5), resultSet.getFloat(6));
            drivers.add(d);
        }
        DbConnectionUtil.closeDBConnection(connection);
        return drivers;
    }

    @Override
    public ArrayList<Driver> getDriversByCar(String regNr) throws SQLException {
        ArrayList<Driver> drivers = new ArrayList<>();
        Connection connection = DbConnectionUtil.getDBConnection();

        String sqlSelect = "SELECT * FROM drivers WHERE carRegNr = ?";
        PreparedStatement statement =  connection.prepareStatement(sqlSelect);
        statement.setString(1, regNr);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next())
        {
            Driver d = new Driver(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3),
                    resultSet.getString(4), resultSet.getString(5), resultSet.getFloat(6));
            drivers.add(d);
        }
        DbConnectionUtil.closeDBConnection(connection);
        return drivers;
    }
}
