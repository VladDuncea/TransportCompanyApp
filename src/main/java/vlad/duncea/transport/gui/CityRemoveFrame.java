package vlad.duncea.transport.gui;

import vlad.duncea.transport.repository.CarDBRepository;
import vlad.duncea.transport.repository.CarRepositoryInterface;
import vlad.duncea.transport.repository.CityDBRepository;
import vlad.duncea.transport.repository.CityRepositoryInterface;

import javax.swing.*;
import java.sql.SQLException;

public class CityRemoveFrame extends JFrame {
    private CityRepositoryInterface repository = new CityDBRepository();

    public CityRemoveFrame(JFrame f) throws SQLException {
        super("Remove car");

        JLabel regNrLabel = new JLabel("Name: " );
        JTextField name = new JTextField();
        regNrLabel.setBounds(20, 50, 200, 30);
        name.setBounds(100, 50, 200, 30);
        add(name);
        add(regNrLabel);

        JButton button = new JButton("Remove City");
        button.setBounds(100, 150, 150, 30);
        button.addActionListener(event -> remove(name));
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

    public void remove(JTextField name)   {
        String n1 = name.getText();
        try {
            this.repository.removeCity(n1);
            JOptionPane.showMessageDialog(this, "City was successfully removed!");
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "An error occurred, please try later!");
        }


    }
}
