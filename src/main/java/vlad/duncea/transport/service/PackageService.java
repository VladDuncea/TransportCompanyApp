package vlad.duncea.transport.service;

import vlad.duncea.transport.main.Main;
import vlad.duncea.transport.model.City;
import vlad.duncea.transport.model.Client;
import vlad.duncea.transport.model.Link;
import vlad.duncea.transport.model.Package;
import vlad.duncea.transport.repository.PackageRepository;

import java.util.ArrayList;
import java.util.Scanner;

public class PackageService
{
    private PackageRepository packageRepository;
    private AuditService auditService;

    public PackageService()
    {
        packageRepository = new PackageRepository();
        auditService = AuditService.getAuditService();
    }

    public PackageRepository getPackageRepository() {
        return packageRepository;
    }

    public Package addPackage(Scanner s)
    {
        System.out.println("Enter package volume: ");
        float volume = s.nextFloat();

        System.out.println("Enter package weight: ");
        float weight = s.nextFloat();

        System.out.println("Enter first city name: ");
        City c1 = Main.cityService.getCityByName(s.next());
        if(c1==null)
        {
            System.out.println("City doesn't exist!");
            return null;
        }

        System.out.println("Enter second city name: ");
        City c2 = Main.cityService.getCityByName(s.next());
        if(c2==null)
        {
            System.out.println("City doesn't exist!");
            return null;
        }

        System.out.println("Enter client id: ");
        Client client = Main.clientService.getClientById(s.nextInt());
        if(client == null)
        {
            System.out.println("Client doesn't exist!");
            return null;
        }

        Package p = new Package(packageRepository.getLastId(), volume, weight, c1.getName(), c2.getName(), client.getId());
        packageRepository.addPackage(p);

        auditService.logData("PackageService_addPackages");
        return p;
    }

    public ArrayList<Package> getPackagesForClient(int clientId)
    {
        auditService.logData("PackageService_getPackagesForClient");
        return packageRepository.getPackagesByClient(clientId);
    }
}
