package vlad.duncea.transport.model;

import java.util.List;

public class Route
{
    private List<Link> links;
    private City from,to;

    public Route(List<Link> links, City from, City to) {
        this.links = links;
        this.from = from;
        this.to = to;
    }

    public List<Link> getLinks() {
        return links;
    }

    public void setLinks(List<Link> links) {
        this.links = links;
    }

    public City getFrom() {
        return from;
    }

    public void setFrom(City from) {
        this.from = from;
    }

    public City getTo() {
        return to;
    }

    public void setTo(City to) {
        this.to = to;
    }
}
