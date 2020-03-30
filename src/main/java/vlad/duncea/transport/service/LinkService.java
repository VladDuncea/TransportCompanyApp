package vlad.duncea.transport.service;

import vlad.duncea.transport.main.Main;
import vlad.duncea.transport.model.*;
import vlad.duncea.transport.repository.LinkRepository;

import java.util.Scanner;

public class LinkService
{
    LinkRepository linkRepository;

    public LinkService()
    {
        linkRepository = new LinkRepository();
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

        Link l = new Link(linkRepository.getLastId(), c1, c2, length, duration);
        linkRepository.addLink(l);

        return l;
    }

    public void removeLinkById(int id)
    {
        linkRepository.removeLinkById(id);
    }

    public Link getShortestLink(City city1,City city2,boolean byTime)
    {
        return linkRepository.getShortestLink(city1,city2,byTime);
    }

    public String allLinks()
    {
        StringBuilder res = new StringBuilder();
        for(Link l : linkRepository.getLinks())
        {
            res.append(l.toString()).append("\n");
        }
        return res.toString();
    }
}
