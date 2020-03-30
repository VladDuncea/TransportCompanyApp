package vlad.duncea.transport.service;

import vlad.duncea.transport.model.City;
import vlad.duncea.transport.model.Client;
import vlad.duncea.transport.repository.CityRepository;

import java.util.Scanner;

public class CityService
{
    CityRepository cityRepository;

    public CityService()
    {
        cityRepository = new CityRepository();
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
        cityRepository.addCity(c);

        return c;
    }

    public void removeCity(Scanner s)
    {
        System.out.println("Enter city name: ");
        cityRepository.removeCity(s.next());
    }

    public City getCityByName(String name)
    {
        for(City c : cityRepository.getCities())
        {
            if(c.getName().equals(name))
                return c;
        }
        return null;
    }

    public String allCities()
    {
        StringBuilder res = new StringBuilder();
        for(City c : cityRepository.getCities())
        {
            res.append(c.toString()).append("\n");
        }
        return res.toString();
    }
}
