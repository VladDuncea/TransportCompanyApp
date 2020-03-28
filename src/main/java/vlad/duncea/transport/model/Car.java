package vlad.duncea.transport.model;

public class Car
{
    private String registration_nr;
    private float volume;

    public Car(String registration_nr, float volume) {
        this.registration_nr = registration_nr;
        this.volume = volume;
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
}
