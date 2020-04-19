package vlad.duncea.transport.model;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;

public class Transport implements Comparable
{
    private int transportID;
    private ArrayList<Integer> packages;
    private Date transportDate;
    private int driverId;
    private String carRegNr;

    public Transport(int transportID, ArrayList<Integer> packages,Date date,int driverId,String carRegNr )
    {
        this.transportID = transportID;
        this.packages = packages;
        this.transportDate = date;
        this.driverId = driverId;
        this.carRegNr = carRegNr;
    }

    public Transport(int transportID) {
        this.transportID = transportID;
        packages = new ArrayList<>();
        transportDate = null;
        driverId = -1;
        carRegNr = null;
    }

    public int getTransportID() {
        return transportID;
    }

    public ArrayList<Integer> getPackages() {
        return packages;
    }

    public Date getTransportDate() {
        return transportDate;
    }

    public int getDriverId() {
        return driverId;
    }

    public String getCarRegNr() {
        return carRegNr;
    }

    //function takes driver and sets also the car to car of driver
    public void setDriverAndCar(Driver d)
    {
        if(alreadySent())
        {
            System.out.println("Transport already sent, can't edit!");
            return;
        }

        if(d.getCarRegNr() == null)
        {
            System.out.println("Cant put driver with no car to transport!");
            return;
        }
        driverId = d.getId();
        carRegNr = d.getCarRegNr();
    }

    public void addPackage(Package p)
    {
        if(!alreadySent())
            packages.add(p.getPackageID());
    }

    //send transport if not already sent
    public void sendTransport()
    {
        if(!alreadySent())
            transportDate = new Date();
    }

    public boolean alreadySent()
    {
        return transportDate == null;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if(packages.isEmpty())
        {
            sb.append("No packages");
        }
        else
        {
            sb.append("PackagesId:");
            for(int p : packages)
                sb.append(p).append(",");
        }


        return "Transport ID: " + transportID +
                " packages: " + sb.toString() +
                " transportDate: " + (transportDate==null ? "Not sent" : transportDate) +
                " driver ID: " + (driverId == -1 ? "No driver" : driverId)+
                " car: " + (carRegNr == null ? "No car" : carRegNr);// +
                //" route: " + (car == null ? "No route" : car.getRoute());
    }

    @Override
    public int compareTo(Object o) {
        Transport t = (Transport) o;
        return transportDate.compareTo(t.transportDate);
    }
}
