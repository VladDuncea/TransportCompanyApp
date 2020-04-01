package vlad.duncea.transport.service;

import vlad.duncea.transport.model.Transport;
import vlad.duncea.transport.repository.TransportRepository;

import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class TransportService
{
    private TransportRepository transportRepository;

    public TransportService()
    {
        transportRepository = new TransportRepository();
    }

    public void removeTransportByID(int id)
    {
        transportRepository.removeTransportByID(id);
    }

    public ArrayList<Transport> getTransportsByDate(Date d)
    {
        return transportRepository.getTransportsByDate(d);
    }

    public void addTransport()
    {
        transportRepository.addTransport();
        System.out.println("New empty transport added!");
    }

    public Transport getTransportById(int id)
    {
        return transportRepository.getTransportById(id);
    }

    public String allTransports()
    {
        StringBuilder sb = new StringBuilder();

        for(Transport t : transportRepository.getTransports())
            sb.append(t).append("\n");
        return sb.toString();
    }
}
