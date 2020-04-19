package vlad.duncea.transport.model;

import java.util.ArrayList;
import java.util.List;

public class Route
{
    private ArrayList<Integer> links;
    private String from,to;

    public Route(ArrayList<Integer> links, String from, String to) {
        this.links = links;
        this.from = from;
        this.to = to;
    }

    public ArrayList<Integer> getLinks() {
        return links;
    }

    public void setLinks(ArrayList<Integer> links) {
        this.links = links;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("LinkId's: ");
        for(int l :links)
            sb.append(l).append("-");
        return "Route " +
                "links:" + sb.toString() +
                " from: " + from +
                " to: " + to;
    }
}
