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
    private Connection connection;

    public CarDBRepository(Connection connection) {
        this.connection = connection;
    }

    public void addCar(Car c) throws SQLException {
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
    }

    public Boolean removeCar(String regNr) throws SQLException {
        //Verify if car is attached to drivers
        String sqlGetDriversByCar = "SELECT COUNT(*) FROM drivers WHERE carRegNr = ?";
        PreparedStatement statement =  connection.prepareStatement(sqlGetDriversByCar);
        statement.setString(1, regNr);
        ResultSet resultSet = statement.executeQuery();
        if(resultSet.next() && resultSet.getInt(1) !=0)
        {
            return false;
        }

        String sqlDelete = "DELETE FROM cars WHERE regNr = ?";
        statement =  connection.prepareStatement(sqlDelete);
        statement.setString(1, regNr);
        statement.executeUpdate();
        return true;
    }


    public ArrayList<Car> getCars() throws SQLException {
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
        return cars;
    }
}
