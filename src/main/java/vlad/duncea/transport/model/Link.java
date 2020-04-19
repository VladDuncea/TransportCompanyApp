package vlad.duncea.transport.model;

import vlad.duncea.transport.main.Main;

public class Link
{
    private int linkId;
    private String city1,city2;
    private float length, duration;

    public Link(int linkId, String city1, String city2, float length, float duration) {
        this.linkId = linkId;
        this.city1 = city1;
        this.city2 = city2;
        this.length = length;
        this.duration = duration;
    }

    public String getCity1Id() {
        return city1;
    }

    public void setCity1Id(String city1) {
        this.city1 = city1;
    }

    public String getCity2Id() {
        return city2;
    }

    public void setCity2Id(String city2) {
        this.city2 = city2;
    }

    public float getLength() {
        return length;
    }

    public void setLength(float length) {
        this.length = length;
    }

    public float getDuration() {
        return duration;
    }

    public void setDuration(float duration) {
        this.duration = duration;
    }

    public int getLinkId() {
        return linkId;
    }

    public boolean linksCities(String c1,String c2)
    {
        return (city1.equals(c1) && city2.equals(c2)) || (city1.equals(c2) && city2.equals(c1));
    }

    @Override
    public String toString() {
        return "Link " + "ID: " + linkId +
                " city1: " + city1 +
                " city2: " + city2 +
                " length: " + length +
                " duration: " + duration;
    }
}
