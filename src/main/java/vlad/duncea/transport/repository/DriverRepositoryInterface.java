package vlad.duncea.transport.repository;

import vlad.duncea.transport.model.Car;
import vlad.duncea.transport.model.Driver;

import java.sql.SQLException;
import java.util.ArrayList;

public interface DriverRepositoryInterface {

    void addDriver(Driver d) throws SQLException;
    void removeDriver(Driver d) throws SQLException;
    void removeDriverById(int id) throws SQLException;
    Driver getDriverById(int id) throws SQLException;
    boolean giveCarToDriver(int driverId, String regNr) throws SQLException;
    ArrayList<Driver> getDrivers() throws SQLException;
    ArrayList<Driver> getDriversByCar(String regNr) throws SQLException;
}
