package vlad.duncea.transport.service;

import vlad.duncea.transport.model.FileService;
import vlad.duncea.transport.model.Transport;
import vlad.duncea.transport.repository.TransportRepository;

import java.text.SimpleDateFormat;

import java.io.*;
import java.util.ArrayList;

public class TransportFileService implements FileService
{
    static TransportFileService transportFileService;

    private TransportRepository transportRepository;
    private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    private TransportFileService(TransportRepository tr) {
        transportRepository = tr;
    }

    public void loadData()
    {
        try{
            File f = new File("transport_data.csv");

            //Check if file is new
            if(!f.exists())
                return;

            BufferedReader input = new BufferedReader(new FileReader(f));

            String line;
            while((line = input.readLine()) != null)
            {
                String[] values = line.split(",");

                ArrayList<Integer> packages = new ArrayList<>();
                //get package list
                int n = Integer.parseInt(values[1]);
                for(int i =0;i<n;i++)
                {
                    packages.add(Integer.parseInt(values[i+2]));
                }
                try {
                    Transport t = new Transport(Integer.parseInt(values[0]),packages,
                            (values[n + 2].equals("NULL") ? null: sdf.parse(values[n + 2])),
                            Integer.parseInt(values[n+3]),values[n+4].equals("NULL") ? null : values[n+4]);
                    transportRepository.addTransport(t);
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }

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
            File f = new File("transport_data.csv");

            BufferedWriter output = new BufferedWriter(new FileWriter(f));

            for(Transport t : transportRepository.getTransports())
            {
                output.write(Integer.toString(t.getTransportID()));
                output.write(",");
                //Write nr of packages
                output.write(Integer.toString(t.getPackages().size()));
                output.write(",");
                for(int p : t.getPackages()) {
                    output.write(Integer.toString(p));
                    output.write(",");
                }

                //Rest of data
                output.write(t.getTransportDate() == null ? "NULL" : sdf.format(t.getTransportDate()));
                output.write(",");
                output.write(Integer.toString(t.getDriverId()));
                output.write(",");
                output.write(t.getCarRegNr() == null ? "NULL" : t.getCarRegNr());
                output.newLine();
                output.flush();
            }

        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public static TransportFileService getFileService(TransportRepository transportRepository)
    {
        if(transportFileService == null)
        {
            transportFileService = new TransportFileService(transportRepository);
        }

        return transportFileService;
    }
}
