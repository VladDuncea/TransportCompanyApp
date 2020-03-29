package vlad.duncea.transport.repository;

import vlad.duncea.transport.model.Link;

import java.util.ArrayList;

public class LinkRepository
{
    private ArrayList<Link> links;

    public LinkRepository()
    {
        links = new ArrayList<>();
    }

    public void addLink(Link l)
    {
        links.add(l);
    }

    public void removeLink(Link l)
    {
        links.remove(l);
    }

    public ArrayList<Link> getLinks() {
        return links;
    }
}
