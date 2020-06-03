package vlad.duncea.transport.gui;

import vlad.duncea.transport.repository.CityDBRepository;
import vlad.duncea.transport.repository.CityRepositoryInterface;
import vlad.duncea.transport.repository.DriverDBRepository;
import vlad.duncea.transport.repository.DriverRepositoryInterface;

import javax.swing.*;
import java.sql.SQLException;

public class DriverRemoveFrame extends JFrame {

    private DriverRepositoryInterface repository = new DriverDBRepository();

    public DriverRemoveFrame(JFrame f) throws SQLException {
        super("Remove driver");

        JLabel idLabel = new JLabel("Driver ID: " );
        JTextField id = new JTextField();
        idLabel.setBounds(20, 50, 200, 30);
        id.setBounds(100, 50, 200, 30);
        add(id);
        add(idLabel);

        JButton button = new JButton("Remove Driver");
        button.setBounds(100, 150, 150, 30);
        button.addActionListener(event -> remove(id));
        add(button);

        setSize(350, 300);
        this.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                f.setEnabled(true);
            }
        });
        setLocationRelativeTo(f);
        setResizable(false);
        setLayout(null);
        setVisible(true);
    }

    public void remove(JTextField id)   {
        int n1 = Integer.parseInt(id.getText());
        try {
            this.repository.removeDriverById(n1);
            JOptionPane.showMessageDialog(this, "Driver was successfully removed!");
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "An error occurred, please try later!");
        }
    }
}
