package vlad.duncea.transport.model;

public class Driver extends Person
{
    private Car car;
    private float salary;

    public Driver(String firstName, String lastName, String phoneNumber, Car car, float salary) {
        super(firstName, lastName, phoneNumber);
        this.car = car;
        this.salary = salary;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public float getSalary() {
        return salary;
    }

    public void setSalary(float salary) {
        this.salary = salary;
    }
}
