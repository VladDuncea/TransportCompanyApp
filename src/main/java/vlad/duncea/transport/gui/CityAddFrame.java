package vlad.duncea.transport.gui;

import vlad.duncea.transport.model.Car;
import vlad.duncea.transport.model.City;
import vlad.duncea.transport.repository.CarDBRepository;
import vlad.duncea.transport.repository.CarRepositoryInterface;
import vlad.duncea.transport.repository.CityDBRepository;
import vlad.duncea.transport.repository.CityRepositoryInterface;

import javax.swing.*;
import java.sql.SQLException;

public class CityAddFrame  extends JFrame{
    private CityRepositoryInterface repository = new CityDBRepository();

    public CityAddFrame(JFrame f) throws SQLException {
        super("Add car");

        JLabel nameLabel = new JLabel("City name:" );
        JTextField name = new JTextField();
        nameLabel.setBounds(10, 40, 200, 30);
        name.setBounds(100, 50, 200, 30);
        add(nameLabel);
        add(name);

        JLabel latitudeLabel = new JLabel("Latitude:" );
        JTextField latitude = new JTextField();
        latitudeLabel.setBounds(10, 90, 200, 30);
        latitude.setBounds(100, 100, 200, 30);
        add(latitudeLabel);
        add(latitude);

        JLabel longitudeLabel = new JLabel("Longitude:" );
        JTextField longitude = new JTextField();
        longitudeLabel.setBounds(10, 140, 200, 30);
        longitude.setBounds(100, 150, 200, 30);
        add(longitudeLabel);
        add(longitude);

        JButton button = new JButton("Add City");
        button.setBounds(100, 200, 150, 30);
        button.addActionListener(event -> addDB(name, latitude,longitude));
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

    public void addDB(JTextField name, JTextField latitude,JTextField longitude)   {
        String n1 = name.getText();
        float n2 = Float.parseFloat(latitude.getText());
        float n3 = Float.parseFloat(longitude.getText());
        City c = new City(n1, n3, n2);
        try {
            this.repository.addCity(c);
            JOptionPane.showMessageDialog(this, "City was successfully added!");
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "An error occurred, please try later!");
        }
    }
}
