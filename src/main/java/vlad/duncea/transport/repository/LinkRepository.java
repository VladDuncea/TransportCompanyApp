package vlad.duncea.transport.repository;

import vlad.duncea.transport.model.City;
import vlad.duncea.transport.model.Link;

import java.util.ArrayList;

public class LinkRepository
{
    private int lastId;
    private ArrayList<Link> links;

    public LinkRepository()
    {
        links = new ArrayList<>();
        lastId = 0;
    }

    public void addLink(Link l)
    {
        links.add(l);
        lastId++;
    }

    public void removeLink(Link l)
    {
        links.remove(l);
    }
    public void removeLinkById(int id)
    {
        for (Link l : links)
            if(l.getLinkId() == id)
            {
                links.remove(l);
                break;
            }
    }

    public int getLastId() {
        return lastId;
    }

    public Link getShortestLink(String c1Name, String c2Name,boolean byTime)
    {
        Link response = null;
        float minVal = Float.POSITIVE_INFINITY;
        for(Link l :links)
        {
            float val;
            if(byTime)
                val = l.getDuration();
            else
                val = l.getLength();

            if (l.linksCities(c1Name, c2Name) && val < minVal) {
                response = l;
                minVal = val;
            }
        }
        return response;
    }

    public ArrayList<Link> getLinks() {
        return links;
    }
}
