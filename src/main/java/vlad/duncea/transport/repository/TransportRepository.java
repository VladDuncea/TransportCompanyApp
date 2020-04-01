package vlad.duncea.transport.repository;

import vlad.duncea.transport.model.Transport;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.TreeMap;

public class TransportRepository
{
    private int lastId;
    private ArrayList<Transport> transports;

    public TransportRepository()
    {
        transports = new ArrayList<>();
        lastId = 0;
    }

    public void addTransport()
    {
        transports.add(new Transport(lastId++));
    }

    public void removeTransportByID(int id)
    {
        for(Transport t : transports)
            if(t.getTransportID() == id) {
                transports.remove(t);
                break;
            }
    }

    public ArrayList<Transport> getTransportsByDate(Date d)
    {
        ArrayList<Transport> res = new ArrayList<>();
        for(Transport t : transports)
            if(t.getTransportDate().equals(d)) {
                res.add(t);
                break;
            }
        return res;
    }

    public ArrayList<Transport> getTransports() {
        return transports;
    }
}
