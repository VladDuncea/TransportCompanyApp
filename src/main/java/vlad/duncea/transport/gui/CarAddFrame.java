package vlad.duncea.transport.gui;

import vlad.duncea.transport.model.Car;
import vlad.duncea.transport.repository.CarDBRepository;
import vlad.duncea.transport.repository.CarRepository;
import vlad.duncea.transport.repository.CarRepositoryInterface;
import vlad.duncea.transport.repository.DbConnectionUtil;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.SQLException;


public class CarAddFrame extends JFrame {

    private CarRepositoryInterface repository = new CarDBRepository();

    public CarAddFrame(JFrame f) throws SQLException {
        super("Add car");

        JLabel regNrLabel = new JLabel("Registration Nr: " );
        JTextField regNr = new JTextField();
        regNrLabel.setBounds(10, 50, 200, 30);
        regNr.setBounds(100, 50, 200, 30);

        JLabel volumeLabel = new JLabel("Volume: " );
        JTextField volume = new JTextField();
        volumeLabel.setBounds(10, 100, 200, 30);
        volume.setBounds(100, 100, 200, 30);

        JButton button = new JButton("Add Car");
        button.setBounds(100, 150, 150, 30);
        button.addActionListener(event -> addCarDB(regNr, volume));

        add(regNr);
        add(regNrLabel);
        add(volume);
        add(volumeLabel);
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

    public void addCarDB(JTextField regNr, JTextField volume)   {
        String n1 = regNr.getText();
        Float n2 = Float.parseFloat(volume.getText());
        Car car = new Car(n1, n2, -1);
        try {
            this.repository.addCar(car);
            JOptionPane.showMessageDialog(this, "Car was successfully added!");
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "An error occurred, please try later!");
        }


    }

}
