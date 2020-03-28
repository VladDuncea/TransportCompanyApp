package vlad.duncea.transport.model;

public class Link
{
    private City city1,city2;
    private float length, duration;

    public Link(City city1, City city2, float length, float duration) {
        this.city1 = city1;
        this.city2 = city2;
        this.length = length;
        this.duration = duration;
    }

    public City getCity1() {
        return city1;
    }

    public void setCity1(City city1) {
        this.city1 = city1;
    }

    public City getCity2() {
        return city2;
    }

    public void setCity2(City city2) {
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
}
