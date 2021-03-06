package vlad.duncea.transport.service;

import vlad.duncea.transport.main.Main;
import vlad.duncea.transport.model.*;
import vlad.duncea.transport.repository.LinkRepository;

import java.util.Scanner;

public class LinkService
{
    LinkRepository linkRepository;
    AuditService auditService;

    public LinkService()
    {
        linkRepository = new LinkRepository();
        auditService = AuditService.getAuditService();
    }

    public Link addLink(Scanner s)
    {
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

        System.out.println("Enter link length: ");
        float length = s.nextFloat();

        System.out.println("Enter link duration: ");
        float duration = s.nextFloat();

        Link l = new Link(linkRepository.getLastId(), c1.getName(), c2.getName(), length, duration);
        linkRepository.addLink(l);

        auditService.logData("LinkService_addLink");
        return l;
    }

    public LinkRepository getLinkRepository() {
        return linkRepository;
    }

    public void removeLinkById(int id)
    {
        linkRepository.removeLinkById(id);
        auditService.logData("LinkService_removeLinkById");
    }

    public Link getShortestLink(String  c1Name,String c2Name,boolean byTime)
    {
        auditService.logData("LinkService_getShortestLink");
        return linkRepository.getShortestLink(c1Name,c2Name,byTime);
    }

    public String allLinks()
    {
        auditService.logData("LinkService_allLinks");

        StringBuilder res = new StringBuilder();
        for(Link l : linkRepository.getLinks())
        {
            res.append(l.toString()).append("\n");
        }
        return res.toString();
    }
}
