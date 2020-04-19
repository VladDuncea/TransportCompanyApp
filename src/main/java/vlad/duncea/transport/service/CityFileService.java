package vlad.duncea.transport.service;

import vlad.duncea.transport.model.City;
import vlad.duncea.transport.model.FileService;
import vlad.duncea.transport.repository.CityRepository;

import java.io.*;

public class CityFileService implements FileService
{
    static CityFileService cityFileService;

    private CityRepository cityRepository;

    private CityFileService(CityRepository cr) {
        cityRepository = cr;
    }

    public void loadData()
    {
        try{
            File f = new File("city_data.csv");

            //Check if file is new
            if(!f.exists())
                return;

            BufferedReader input = new BufferedReader(new FileReader(f));

            String line;
            while((line = input.readLine()) != null)
            {
                String[] values = line.split(",");
                City c = new City(values[0],Float.parseFloat(values[1]),Float.parseFloat(values[2]));
                cityRepository.addCity(c);
            }

        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void saveData()
    {
        try{
            File f = new File("city_data.csv");

            BufferedWriter output = new BufferedWriter(new FileWriter(f));

            for(City c : cityRepository.getCities())
            {
                output.write(c.getName());
                output.write(",");
                output.write(Float.toString(c.getLongitude()));
                output.write(",");
                output.write(Float.toString(c.getLatitude()));
                output.newLine();
                output.flush();
            }

        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public static CityFileService getFileService(CityRepository cityRepository)
    {
        if(cityFileService == null)
        {
            cityFileService = new CityFileService(cityRepository);
        }

        return cityFileService;
    }
}
