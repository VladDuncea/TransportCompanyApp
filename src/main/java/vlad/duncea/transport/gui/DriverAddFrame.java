package vlad.duncea.transport.gui;

import vlad.duncea.transport.model.City;
import vlad.duncea.transport.model.Driver;
import vlad.duncea.transport.repository.*;

import javax.swing.*;
import java.sql.SQLException;

public class DriverAddFrame extends JFrame{
    private DriverRepositoryInterface repository = new DriverDBRepository();

    public DriverAddFrame(JFrame f) throws SQLException {
        super("Add driver");

        JLabel fNameLabel = new JLabel("First Name:" );
        JTextField fName = new JTextField();
        fNameLabel.setBounds(10, 40, 200, 30);
        fName.setBounds(100, 40, 200, 30);
        add(fNameLabel);
        add(fName);

        JLabel lNameLabel = new JLabel("Last Name:" );
        JTextField lName = new JTextField();
        lNameLabel.setBounds(10, 90, 200, 30);
        lName.setBounds(100, 90, 200, 30);
        add(lNameLabel);
        add(lName);

        JLabel phoneLabel = new JLabel("Phone:" );
        JTextField phone = new JTextField();
        phoneLabel.setBounds(10, 140, 200, 30);
        phone.setBounds(100, 140, 200, 30);
        add(phoneLabel);
        add(phone);

        JLabel salaryLabel = new JLabel("Salary:" );
        JTextField salary = new JTextField();
        salaryLabel.setBounds(10, 190, 200, 30);
        salary.setBounds(100, 190, 200, 30);
        add(salaryLabel);
        add(salary);

        JButton button = new JButton("Add Driver");
        button.setBounds(100, 250, 150, 30);
        button.addActionListener(event -> addDB(fName,lName, phone, salary));
        add(button);

        setSize(350, 350);
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

    public void addDB(JTextField fName,JTextField lName,JTextField phone,JTextField salary)   {
        String n1 = fName.getText();
        String n2 = lName.getText();
        String n3 = phone.getText();
        float n4 = Float.parseFloat(salary.getText());
        Driver d = new Driver(-1,n1, n2, n3,null,n4);
        try {
            this.repository.addDriver(d);
            JOptionPane.showMessageDialog(this, "Driver was successfully added!");
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "An error occurred, please try later!");
        }


    }
}
