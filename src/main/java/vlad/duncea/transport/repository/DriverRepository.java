package vlad.duncea.transport.repository;

import vlad.duncea.transport.model.Driver;

import java.util.ArrayList;

public class DriverRepository
{
    ArrayList<Driver> drivers;

    public DriverRepository()
    {
        drivers = new ArrayList<>();
    }

    public DriverRepository(ArrayList<Driver> drivers) {
        this.drivers = drivers;
    }

    public void addDriver(Driver d)
    {
        drivers.add(d);
    }

    public void removeDriver(Driver d)
    {
        drivers.remove(d);
    }

    public ArrayList<Driver> getDrivers() {
        return drivers;
    }
}
