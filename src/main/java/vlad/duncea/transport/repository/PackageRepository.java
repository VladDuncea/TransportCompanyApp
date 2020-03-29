package vlad.duncea.transport.repository;

import vlad.duncea.transport.model.Package;

import java.util.ArrayList;

public class PackageRepository
{
    private ArrayList<Package> packages;

    public PackageRepository()
    {
        packages = new ArrayList<>();
    }

    public void addPackage(Package p)
    {
        packages.add(p);
    }

    public void removePackage(Package p)
    {
        packages.remove(p);
    }

    public ArrayList<Package> getPackages() {
        return packages;
    }
}
