package vlad.duncea.transport.repository;

import vlad.duncea.transport.model.Car;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CarRepositoryInterface {

    void addCar(Car c) throws SQLException;

    Boolean removeCar(String regNr) throws SQLException;

    ArrayList<Car> getCars() throws SQLException;
}
