package vlad.duncea.transport.repository;

import vlad.duncea.transport.main.Main;
import vlad.duncea.transport.model.Car;
import vlad.duncea.transport.model.Driver;

import java.util.ArrayList;

public class CarRepository
{
    private ArrayList<Car> cars;

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
    public Boolean removeCar(String re)
    {
        Car toDel = null;
        for(Car c : cars)
            if (re.equals(c.getRegistration_nr()))
            {
                toDel = c;
                break;
            }

        if(toDel == null)
            return true;
        if(Main.driverService.getDriversByCar(toDel).isEmpty())
        {
            cars.remove(toDel);
            return true;
        }

        return false;
    }

    public ArrayList<Car> getCars() {
        return cars;
    }
}
