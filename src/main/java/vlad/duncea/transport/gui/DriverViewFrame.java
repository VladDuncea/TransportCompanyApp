package vlad.duncea.transport.gui;

import vlad.duncea.transport.model.City;
import vlad.duncea.transport.model.Driver;
import vlad.duncea.transport.repository.CityDBRepository;
import vlad.duncea.transport.repository.CityRepositoryInterface;
import vlad.duncea.transport.repository.DriverDBRepository;
import vlad.duncea.transport.repository.DriverRepositoryInterface;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;
import java.sql.SQLException;
import java.util.ArrayList;

public class DriverViewFrame extends JFrame {

    private DriverRepositoryInterface repository = new DriverDBRepository();

    JTable table;
    ArrayList<Driver> drivers;

    public DriverViewFrame(JFrame f) throws SQLException {
        super("View drivers");
        setSize(500, 600);

        JLabel regNrLabel = new JLabel("CarRegNr:" );
        JTextField regNr = new JTextField();
        regNrLabel.setBounds(10, 500, 200, 30);
        regNr.setBounds(90, 500, 150, 30);
        add(regNrLabel);
        add(regNr);

        JButton button = new JButton("Search");
        button.setBounds(250, 500, 100, 30);
        button.addActionListener(event -> findByRegNr(regNr));
        add(button);

        button = new JButton("Reset");
        button.setBounds(350, 500, 100, 30);
        button.addActionListener(event -> reset());
        add(button);


         drivers = repository.getDrivers();

        TableModel dataModel = new AbstractTableModel() {
            public int getColumnCount() { return 6; }
            public int getRowCount() { return drivers.size();}
            public Object getValueAt(int row, int col)
            {
                if(col ==0) {
                    return drivers.get(row).getId();
                }
                else if(col == 1) {
                    return drivers.get(row).getFirstName();
                }
                else if(col == 2) {
                    return drivers.get(row).getLastName();
                }
                else if(col == 3) {
                    return drivers.get(row).getPhoneNumber();
                }
                else if(col == 4) {
                    return drivers.get(row).getCarRegNr();

                }
                return drivers.get(row).getSalary();
            }
        };

        table = new JTable(dataModel);
        table.setFillsViewportHeight(true);

        table.getColumnModel().getColumn( 0 ).setHeaderValue( "ID" );
        table.getColumnModel().getColumn( 1 ).setHeaderValue( "First Name" );
        table.getColumnModel().getColumn( 2 ).setHeaderValue( "Last Name" );
        table.getColumnModel().getColumn( 3 ).setHeaderValue( "Phone Number" );
        table.getColumnModel().getColumn( 4 ).setHeaderValue( "Car RegNr" );
        table.getColumnModel().getColumn( 5 ).setHeaderValue( "Salary" );

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

        setLocationRelativeTo(f);
        setResizable(false);
        setVisible(true);
    }

    private void findByRegNr(JTextField regNr)
    {
        try {
            drivers = repository.getDriversByCar(regNr.getText());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ((AbstractTableModel)table.getModel()).fireTableDataChanged();
    }

    private void reset()
    {
        try {
            drivers = repository.getDrivers();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ((AbstractTableModel)table.getModel()).fireTableDataChanged();
    }

}
