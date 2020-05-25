package vlad.duncea.transport.repository;

import vlad.duncea.transport.model.City;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CityRepositoryInterface {

    void addCity(City c) throws SQLException;
    void removeCity(City c) throws SQLException;
    void removeCity(String name) throws SQLException;
    ArrayList<City> getCities() throws SQLException;
}
