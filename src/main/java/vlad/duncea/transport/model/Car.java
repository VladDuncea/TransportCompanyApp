package vlad.duncea.transport.model;

public class Car implements Comparable
{
    private String registrationNr;
    private float volume;
    private int routeId;

    public Car(String registration_nr, float volume, int routeId) {
        if(registration_nr == null)
            throw new NullPointerException();
        this.registrationNr = registration_nr;
        this.volume = volume;
        this.routeId = routeId;
    }

    public String getRegistrationNr() {
        return registrationNr;
    }

    public void setRegistrationNr(String registrationNr) {
        this.registrationNr = registrationNr;
    }

    public float getVolume() {
        return volume;
    }

    public void setVolume(float volume) {
        this.volume = volume;
    }

    public int getRouteId() {
        return routeId;
    }

    public void setRouteId(int routeId) {
        this.routeId = routeId;
    }

    @Override
    public String toString() {
        return "Car: "+ registrationNr +
                " Volume: " + volume +
                " RouteId: " + (routeId == -1 ? "No route" : routeId);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Car car = (Car) o;

        return registrationNr.equals(car.registrationNr);
    }

    @Override
    public int compareTo(Object o) {
        Car c = (Car) o;
        return registrationNr.compareTo(c.registrationNr);
    }
}
