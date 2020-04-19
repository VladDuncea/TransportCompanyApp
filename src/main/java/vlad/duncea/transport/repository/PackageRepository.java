package vlad.duncea.transport.repository;

import vlad.duncea.transport.model.Client;
import vlad.duncea.transport.model.Package;

import java.util.ArrayList;

public class PackageRepository
{
    private int lastId;
    private ArrayList<Package> packages;

    public PackageRepository()
    {
        packages = new ArrayList<>();
        lastId = 0;
    }

    public int getLastId() {
        return lastId;
    }

    public void addPackage(Package p)
    {
        packages.add(p);
        lastId++;
    }

    public void removePackage(Package p)
    {
        packages.remove(p);
    }

    public ArrayList<Package> getPackagesByClient(int clientId)
    {
        ArrayList<Package> res = new ArrayList<>();
        for(Package p : packages)
        {
            if(p.getClientId() == clientId)
                res.add(p);
        }

        return res;
    }

    public ArrayList<Package> getPackages() {
        return packages;
    }
}
