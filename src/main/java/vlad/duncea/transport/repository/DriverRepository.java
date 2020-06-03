package vlad.duncea.transport.repository;

import vlad.duncea.transport.main.Main;
import vlad.duncea.transport.model.Car;
import vlad.duncea.transport.model.Driver;

import java.sql.SQLException;
import java.util.ArrayList;


public class DriverRepository implements DriverRepositoryInterface
{
    private int lastId;
    private ArrayList<Driver> drivers;

    public DriverRepository()
    {
        drivers = new ArrayList<>();
        lastId =0;
    }

    public DriverRepository(ArrayList<Driver> drivers) {
        this.drivers = drivers;
    }

    public void addDriver(Driver d)
    {
        drivers.add(d);
        lastId +=1;
    }

    public int getLastId()
    {
        return lastId;
    }

    public void removeDriver(Driver d)
    {
        drivers.remove(d);
    }
    public void removeDriverById(int id)
    {
        for(Driver d : drivers)
            if(d.getId() == id)
            {
                drivers.remove(d);
                break;
            }
    }

    @Override
    public Driver getDriverById(int id) {
        for(Driver d : drivers)
            if(d.getId() == id)
            {
                return d;
            }
        return null;
    }

    @Override
    public boolean giveCarToDriver(int driverId, String regNr) {
        Driver d = this.getDriverById(driverId);
        Car c = Main.carService.getCarByReg(regNr);
        if(d != null&& c != null)
        {
            d.setCarRegNr(c.getRegistrationNr());
            return true;
        }
        return false;
    }

    public ArrayList<Driver> getDrivers() {
        return drivers;
    }

    @Override
    public ArrayList<Driver> getDriversByCar(String regNr) {
        ArrayList<Driver> response = new ArrayList<>();
        for(Driver d : drivers)
            if(d.getCarRegNr()!=null && d.getCarRegNr().equals(regNr))
            {
                response.add(d);
            }

        return response;
    }
}
