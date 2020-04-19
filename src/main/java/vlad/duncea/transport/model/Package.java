package vlad.duncea.transport.model;

public class Package
{
    private int packageID;
    private float volume, weight;
    private String from,to;
    private int clientId;

    public Package(int packageID, float volume, float weight, String from, String to, int clientId) {
        this.packageID = packageID;
        this.volume = volume;
        this.weight = weight;
        this.from = from;
        this.to = to;
        this.clientId = clientId;
    }

    public int getPackageID() {
        return packageID;
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

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    @Override
    public String toString() {
        return  "Package" +
                " volume: " + volume +
                " weight:" + weight +
                " from:" + from +
                " to:" + to +
                " clientID:" + clientId;
    }
}
