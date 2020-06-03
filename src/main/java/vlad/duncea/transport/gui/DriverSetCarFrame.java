package vlad.duncea.transport.gui;

import vlad.duncea.transport.repository.DriverDBRepository;
import vlad.duncea.transport.repository.DriverRepositoryInterface;

import javax.swing.*;
import java.sql.SQLException;

public class DriverSetCarFrame extends JFrame {
    private DriverRepositoryInterface repository = new DriverDBRepository();

    public DriverSetCarFrame(JFrame f) throws SQLException {
        super("Remove driver");

        JLabel idLabel = new JLabel("Driver ID:" );
        JTextField id = new JTextField();
        idLabel.setBounds(20, 50, 200, 30);
        id.setBounds(100, 50, 200, 30);
        add(id);
        add(idLabel);

        JLabel regNrLabel = new JLabel("Car RegNr:" );
        JTextField regNr = new JTextField();
        regNrLabel.setBounds(20, 100, 200, 30);
        regNr.setBounds(100, 100, 200, 30);
        add(regNr);
        add(regNrLabel);

        JButton button = new JButton("Update Driver");
        button.setBounds(100, 200, 150, 30);
        button.addActionListener(event -> update(id,regNr));
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

    public void update(JTextField id,JTextField regNr)   {
        int n1 = Integer.parseInt(id.getText());
        try {
            this.repository.giveCarToDriver(n1,regNr.getText());
            JOptionPane.showMessageDialog(this, "Driver was successfully updated!");
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "An error occurred, please try later!");
        }
    }
}
