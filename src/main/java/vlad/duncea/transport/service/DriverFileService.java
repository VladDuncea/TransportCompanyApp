package vlad.duncea.transport.service;

import vlad.duncea.transport.model.Driver;
import vlad.duncea.transport.model.FileService;
import vlad.duncea.transport.repository.DriverRepository;

import java.io.*;

public class DriverFileService implements FileService
{
    static DriverFileService driverFileService;

    private DriverRepository driverRepository;

    private DriverFileService(DriverRepository dr) {
        driverRepository = dr;
    }

    public void loadData()
    {
        try{
            File f = new File("driver_data.csv");

            //Check if file is new
            if(!f.exists())
                return;

            BufferedReader input = new BufferedReader(new FileReader(f));

            String line;
            while((line = input.readLine()) != null)
            {
                String[] values = line.split(",");
                Driver d = new Driver(Integer.parseInt(values[0]),values[1],values[2],values[3],
                        (values[4].equals("NULL") ? null:values[4]),Float.parseFloat(values[5]));
                driverRepository.addDriver(d);
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
            File f = new File("driver_data.csv");

            BufferedWriter output = new BufferedWriter(new FileWriter(f));

            for(Driver d : driverRepository.getDrivers())
            {
                output.write(Integer.toString(d.getId()));
                output.write(",");
                output.write(d.getFirstName());
                output.write(",");
                output.write(d.getLastName());
                output.write(",");
                output.write(d.getPhoneNumber());
                output.write(",");
                output.write((d.getCarRegNr() == null ? "NULL":d.getCarRegNr()));
                output.write(",");
                output.write(Float.toString(d.getSalary()));
                output.newLine();
                output.flush();
            }

        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public static DriverFileService getFileService(DriverRepository driverRepository)
    {
        if(driverFileService == null)
        {
            driverFileService = new DriverFileService(driverRepository);
        }

        return driverFileService;
    }
}
