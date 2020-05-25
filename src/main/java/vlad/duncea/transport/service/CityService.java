package vlad.duncea.transport.service;

import vlad.duncea.transport.model.City;
import vlad.duncea.transport.model.Client;
import vlad.duncea.transport.repository.CityDBRepository;
import vlad.duncea.transport.repository.CityRepository;
import vlad.duncea.transport.repository.CityRepositoryInterface;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

import static vlad.duncea.transport.service.AuditService.auditService;

public class CityService
{
    CityRepositoryInterface cityRepository;
    AuditService auditService;

    public CityService() {
        this(null);
    }
    public CityService(Connection connection)
    {
        if(connection!= null)
        {
            cityRepository = new CityDBRepository(connection);
        }
        else
        {
            cityRepository = new CityRepository();
        }

        auditService = AuditService.getAuditService();
    }

    public City addCity(Scanner s)
    {
        System.out.println("Enter city name: ");
        String name = s.next();

        //add city only if the name is unique
        if(getCityByName(name)!=null)
            return null;

        System.out.println("Enter city longitude: ");
        float longitude = s.nextFloat();

        System.out.println("Enter city lattitude: ");
        float lattitude = s.nextFloat();

        City c = new City(name,longitude,lattitude);
        try {
            cityRepository.addCity(c);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        auditService.logData("CityService_addCity");
        return c;
    }

    public CityRepositoryInterface getCityRepository() {
        return cityRepository;
    }

    public void removeCity(Scanner s)
    {
        System.out.println("Enter city name: ");
        try {
            cityRepository.removeCity(s.next());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        auditService.logData("CityService_removeCity");


    }

    public City getCityByName(String name)
    {
        auditService.logData("CityService_getCityByName");

        try {
            for(City c : cityRepository.getCities())
            {
                if(c.getName().equals(name))
                    return c;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String allCities()
    {
        StringBuilder res = new StringBuilder();
        try {
            for(City c : cityRepository.getCities())
            {
                res.append(c.toString()).append("\n");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        auditService.logData("CityService_allCities");
        return res.toString();
    }
}
