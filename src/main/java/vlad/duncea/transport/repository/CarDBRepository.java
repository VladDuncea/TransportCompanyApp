package vlad.duncea.transport.repository;

import vlad.duncea.transport.main.Main;
import vlad.duncea.transport.model.Car;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.TreeSet;

public class CarDBRepository implements CarRepositoryInterface
{

    public CarDBRepository() {

    }

    public void addCar(Car c) throws SQLException {
        Connection connection = DbConnectionUtil.getDBConnection();
        String sqlAdd;

        //Tratam posibilitatea ca ruta sa fie inexistenta
        if (c.getRouteId() != -1) {
            sqlAdd = "INSERT INTO cars VALUES (?, ?, ?)";
        }
        else
        {
            sqlAdd = "INSERT INTO cars VALUES (?, ?, NULL)";
        }

        PreparedStatement statement =  connection.prepareStatement(sqlAdd);
        statement.setString(1, c.getRegistrationNr());
        statement.setFloat(2, c.getVolume());

        if (c.getRouteId() != -1) {
            statement.setInt(3, c.getRouteId());
        }

        statement.executeUpdate();
        DbConnectionUtil.closeDBConnection(connection);
    }

    public Boolean removeCar(String regNr) throws SQLException {
        Connection connection = DbConnectionUtil.getDBConnection();
        //Verify if car is attached to drivers
        String sqlGetDriversByCar = "SELECT COUNT(*) FROM drivers WHERE carRegNr = ?";
        PreparedStatement statement =  connection.prepareStatement(sqlGetDriversByCar);
        statement.setString(1, regNr);
        ResultSet resultSet = statement.executeQuery();
        if(resultSet.next() && resultSet.getInt(1) !=0)
        {
            DbConnectionUtil.closeDBConnection(connection);
            return false;
        }

        String sqlDelete = "DELETE FROM cars WHERE regNr = ?";
        statement =  connection.prepareStatement(sqlDelete);
        statement.setString(1, regNr);
        statement.executeUpdate();
        DbConnectionUtil.closeDBConnection(connection);
        return true;
    }

    @Override
    public Car getCarByRegNr(String regNr) throws SQLException {
        Connection connection = DbConnectionUtil.getDBConnection();
        //Verify if car is attached to drivers
        String sql = "SELECT * FROM cars WHERE regNr = ?";
        PreparedStatement statement =  connection.prepareStatement(sql);
        statement.setString(1, regNr);
        ResultSet resultSet = statement.executeQuery();

        if(resultSet.next())
        {
            int routeId = resultSet.getInt(3);
            if (resultSet.wasNull())
                routeId = -1;
            DbConnectionUtil.closeDBConnection(connection);
            return new Car(resultSet.getString(1), resultSet.getFloat(2), routeId);
        }
        DbConnectionUtil.closeDBConnection(connection);
        return null;
    }


    public ArrayList<Car> getCars() throws SQLException {
        Connection connection = DbConnectionUtil.getDBConnection();
        ArrayList<Car> cars = new ArrayList<>();

        String sqlSelect = "SELECT * FROM cars";
        PreparedStatement statement =  connection.prepareStatement(sqlSelect);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next())    {
            //Vedem daca ruta e NULL, daca e null o setam la -1
            int routeId = resultSet.getInt(3);
            if (resultSet.wasNull())
                routeId = -1;
            Car c = new Car(resultSet.getString(1), resultSet.getFloat(2), routeId);
            cars.add(c);
        }
        DbConnectionUtil.closeDBConnection(connection);
        return cars;
    }
}
