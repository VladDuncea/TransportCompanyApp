package vlad.duncea.transport.model;

public class Driver extends Person
{
    private String carRegNr;
    private float salary;

    public Driver(int id, String firstName, String lastName, String phoneNumber, String carRegNr, float salary) {
        super(id, firstName, lastName, phoneNumber);
        this.carRegNr = carRegNr;
        this.salary = salary;
    }

    public String getCarRegNr() {
        return carRegNr;
    }

    public void setCarRegNr(String carRegNr) {
        this.carRegNr = carRegNr;
    }

    public float getSalary() {
        return salary;
    }

    public void setSalary(float salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Driver " + super.toString() +
                " car: " + (carRegNr !=null ? carRegNr : "No car assigned") +
                " salary: " + salary;
    }
}
