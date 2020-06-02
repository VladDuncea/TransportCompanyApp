package vlad.duncea.transport.gui;

import vlad.duncea.transport.model.Car;
import vlad.duncea.transport.model.City;
import vlad.duncea.transport.repository.CityDBRepository;
import vlad.duncea.transport.repository.CityRepositoryInterface;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;
import java.sql.SQLException;
import java.util.ArrayList;

public class CityViewFrame extends JFrame {

    private CityRepositoryInterface repository = new CityDBRepository();

    public CityViewFrame(JFrame f) throws SQLException {
        super("Vizualizare masina");

        final ArrayList<City> cities = repository.getCities();

        TableModel dataModel = new AbstractTableModel() {
            public int getColumnCount() { return 3; }
            public int getRowCount() { return cities.size();}
            public Object getValueAt(int row, int col)
            {
                if(col ==0)
                {
                    return cities.get(row).getName();
                }
                else if(col == 1)
                {
                    return cities.get(row).getLatitude();
                }
                return cities.get(row).getLongitude();
            }
        };

        JTable table = new JTable(dataModel);
        table.setFillsViewportHeight(true);

        table.getColumnModel().getColumn( 0 ).setHeaderValue( "Name" );
        table.getColumnModel().getColumn( 1 ).setHeaderValue( "Latitude" );
        table.getColumnModel().getColumn( 2 ).setHeaderValue( "Longitude" );

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        JPanel panel = new JPanel();
        panel.add(scrollPane);
        add(panel);

        this.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                f.setEnabled(true);
            }
        });
        setSize(500, 500);
        setLocationRelativeTo(f);
        setResizable(false);
        setVisible(true);
    }
}
