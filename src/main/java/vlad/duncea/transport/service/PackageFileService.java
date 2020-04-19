package vlad.duncea.transport.service;

import vlad.duncea.transport.model.FileService;
import vlad.duncea.transport.model.Package;
import vlad.duncea.transport.repository.PackageRepository;

import java.io.*;

public class PackageFileService implements FileService
{
    static PackageFileService packageFileService;

    private PackageRepository packageRepository;

    private PackageFileService(PackageRepository pr) {
        packageRepository = pr;
    }

    public void loadData()
    {
        try{
            File f = new File("package_data.csv");

            //Check if file is new
            if(!f.exists())
                return;

            BufferedReader input = new BufferedReader(new FileReader(f));

            String line;
            while((line = input.readLine()) != null)
            {
                String[] values = line.split(",");
                Package p = new Package(Integer.parseInt(values[0]),Float.parseFloat(values[1]),
                        Float.parseFloat(values[2]),values[3],values[4],Integer.parseInt(values[5]));
                packageRepository.addPackage(p);
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
            File f = new File("package_data.csv");

            BufferedWriter output = new BufferedWriter(new FileWriter(f));

            for(Package p : packageRepository.getPackages())
            {
                output.write(Integer.toString(p.getPackageID()));
                output.write(",");
                output.write(Float.toString(p.getVolume()));
                output.write(",");
                output.write(Float.toString(p.getWeight()));
                output.write(",");
                output.write(p.getFrom());
                output.write(",");
                output.write(p.getTo());
                output.write(",");
                output.write(Integer.toString(p.getClientId()));
                output.newLine();
                output.flush();
            }

        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public static PackageFileService getFileService(PackageRepository packageRepository)
    {
        if(packageFileService == null)
        {
            packageFileService = new PackageFileService(packageRepository);
        }

        return packageFileService;
    }
}
