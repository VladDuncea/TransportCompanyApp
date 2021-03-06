package vlad.duncea.transport.service;

import vlad.duncea.transport.model.Transport;
import vlad.duncea.transport.repository.TransportRepository;

import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class TransportService
{
    private TransportRepository transportRepository;
    private AuditService auditService;

    public TransportService()
    {
        transportRepository = new TransportRepository();
        auditService = AuditService.getAuditService();
    }

    public void removeTransportByID(int id)
    {
        transportRepository.removeTransportByID(id);
        auditService.logData("TransportService_removeTransportById");
    }

    public ArrayList<Transport> getTransportsByDate(Date d)
    {
        auditService.logData("TransportService_getTransportByDate");

        return transportRepository.getTransportsByDate(d);
    }

    public void addTransport()
    {
        transportRepository.addTransport();
        System.out.println("New empty transport added!");
        auditService.logData("TransportService_addTransport");
    }

    public TransportRepository getTransportRepository() {
        return transportRepository;
    }

    public Transport getTransportById(int id)
    {
        auditService.logData("TransportService_getTransportById");
        return transportRepository.getTransportById(id);
    }

    public String allTransports()
    {
        auditService.logData("TransportService_allTransports");

        StringBuilder sb = new StringBuilder();

        for(Transport t : transportRepository.getTransports())
            sb.append(t).append("\n");
        return sb.toString();
    }
}
