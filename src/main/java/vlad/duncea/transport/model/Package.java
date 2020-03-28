package vlad.duncea.transport.model;

public class Package
{
    private float volume, weight;
    private City from,to;
    private Client client;

    public Package(float volume, float weight, City from, City to, Client client) {
        this.volume = volume;
        this.weight = weight;
        this.from = from;
        this.to = to;
        this.client = client;
    }

    public float getVolume() {
        return volume;
    }

    public void setVolume(float volume) {
        this.volume = volume;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
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

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
}
