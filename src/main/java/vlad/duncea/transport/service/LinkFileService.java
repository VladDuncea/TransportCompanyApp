package vlad.duncea.transport.service;

import vlad.duncea.transport.model.FileService;
import vlad.duncea.transport.model.Link;
import vlad.duncea.transport.repository.LinkRepository;

import java.io.*;

public class LinkFileService implements FileService
{
    static LinkFileService linkFileService;

    private LinkRepository linkRepository;

    private LinkFileService(LinkRepository lr) {
        linkRepository = lr;
    }

    public void loadData()
    {
        try{
            File f = new File("link_data.csv");

            //Check if file is new
            if(!f.exists())
                return;

            BufferedReader input = new BufferedReader(new FileReader(f));

            String line;
            while((line = input.readLine()) != null)
            {
                String[] values = line.split(",");
                Link l = new Link(Integer.parseInt(values[0]),values[1],values[2],
                        Float.parseFloat(values[3]),Float.parseFloat(values[4]));
                linkRepository.addLink(l);
            }

        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void saveData()
    {
        try{
            File f = new File("link_data.csv");

            BufferedWriter output = new BufferedWriter(new FileWriter(f));

            for(Link l : linkRepository.getLinks())
            {
                output.write(Integer.toString(l.getLinkId()));
                output.write(",");
                output.write(l.getCity1Id());
                output.write(",");
                output.write(l.getCity2Id());
                output.write(",");
                output.write(Float.toString(l.getLength()));
                output.write(",");
                output.write(Float.toString(l.getDuration()));
                output.newLine();
                output.flush();
            }

        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public static LinkFileService getFileService(LinkRepository linkRepository)
    {
        if(linkFileService == null)
        {
            linkFileService = new LinkFileService(linkRepository);
        }

        return linkFileService;
    }
}
