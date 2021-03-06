package vlad.duncea.transport.repository;

import vlad.duncea.transport.main.Main;
import vlad.duncea.transport.model.Car;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.TreeSet;

public class CarRepository implements CarRepositoryInterface
{
    private TreeSet<Car> cars;

    public CarRepository() {
        cars = new TreeSet<>();
    }

    public CarRepository(ArrayList<Car> cars) {
        this.cars = new TreeSet<>(cars);
    }

    public void addCar(Car c)
    {
        cars.add(c);
    }

    public Boolean removeCar(String re)
    {
        Car toDel = null;
        for(Car c : cars)
            if (re.equals(c.getRegistrationNr()))
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

    @Override
    public Car getCarByRegNr(String regNr) throws SQLException {
        for(Car c : cars) {
            if(c.getRegistrationNr().equals(regNr))
                return c;
        }
        return null;
    }

    public ArrayList<Car> getCars() {
        return new ArrayList<>(cars);
    }
}
