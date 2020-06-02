package vlad.duncea.transport.gui;

import vlad.duncea.transport.model.Car;
import vlad.duncea.transport.repository.CarDBRepository;
import vlad.duncea.transport.repository.CarRepositoryInterface;

import javax.swing.*;
import java.sql.SQLException;

public class CarRemoveFrame  extends JFrame{

    private CarRepositoryInterface repository = new CarDBRepository();

    public CarRemoveFrame(JFrame f) throws SQLException {
        super("Remove car");

        JLabel regNrLabel = new JLabel("Reg Nr: " );
        JTextField regNr = new JTextField();
        regNrLabel.setBounds(20, 50, 200, 30);
        regNr.setBounds(100, 50, 200, 30);
        add(regNr);
        add(regNrLabel);

        JButton button = new JButton("Remove Car");
        button.setBounds(100, 150, 150, 30);
        button.addActionListener(event -> remove(regNr));
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

    public void remove(JTextField regNr)   {
        String n1 = regNr.getText();
        try {
            this.repository.removeCar(n1);
            JOptionPane.showMessageDialog(this, "Car was successfully removed!");
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "An error occurred, please try later!");
        }
    }
}
