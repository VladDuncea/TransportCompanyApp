package vlad.duncea.transport.model;

public class Car
{
    private String registration_nr;
    private float volume;
    private Route route;

    public Car(String registration_nr, float volume, Route route) {
        if(registration_nr == null)
            throw new NullPointerException();
        this.registration_nr = registration_nr;
        this.volume = volume;
        this.route = route;
    }

    public String getRegistration_nr() {
        return registration_nr;
    }

    public void setRegistration_nr(String registration_nr) {
        this.registration_nr = registration_nr;
    }

    public float getVolume() {
        return volume;
    }

    public void setVolume(float volume) {
        this.volume = volume;
    }

    public Route getRoute() {
        return route;
    }

    public void setRoute(Route route) {
        this.route = route;
    }

    @Override
    public String toString() {
        return "Car: "+ registration_nr +
                " Volume: " + volume +
                " Route: " + (route == null ? "No route" : route.toString());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Car car = (Car) o;

        return registration_nr.equals(car.registration_nr);
    }
}
