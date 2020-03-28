package vlad.duncea.transport.repository;

import vlad.duncea.transport.model.Car;

import java.util.ArrayList;

public class CarRepository
{
    ArrayList<Car> cars;

    public CarRepository() {
        cars = new ArrayList<>();
    }

    public CarRepository(ArrayList<Car> cars) {
        this.cars = new ArrayList<>(cars);
    }

    public void addCar(Car c)
    {
        cars.add(c);
    }

    public void removeCar(Car c)
    {
        cars.remove(c);
    }

    public ArrayList<Car> getCars() {
        return cars;
    }
}
