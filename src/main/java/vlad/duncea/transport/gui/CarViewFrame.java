package vlad.duncea.transport.gui;

import vlad.duncea.transport.model.Car;
import vlad.duncea.transport.repository.CarDBRepository;
import vlad.duncea.transport.repository.CarRepositoryInterface;
import vlad.duncea.transport.repository.DbConnectionUtil;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class CarViewFrame extends JFrame{

    private CarRepositoryInterface repository = new CarDBRepository();

    public CarViewFrame(JFrame f) throws SQLException {
        super("Vizualizare masina");

        final ArrayList<Car>  cars = repository.getCars();

        TableModel dataModel = new AbstractTableModel() {
            public int getColumnCount() { return 2; }
            public int getRowCount() { return cars.size();}
            public Object getValueAt(int row, int col)
            {
                if(col ==0)
                {
                    return cars.get(row).getRegistrationNr();
                }
                return cars.get(row).getVolume();
            }
        };


        JTable tabel = new JTable(dataModel);
        tabel.setFillsViewportHeight(true);

        tabel.getColumnModel().getColumn( 0 ).setHeaderValue( "RegNr" );
        tabel.getColumnModel().getColumn( 1 ).setHeaderValue( "Volume" );

        JScrollPane scrollPane = new JScrollPane(tabel);
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
