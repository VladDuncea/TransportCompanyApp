package vlad.duncea.transport.gui;



import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.sql.SQLException;

public class CarMenu extends JFrame {

    public CarMenu(JFrame mainMenu) {
        super("Car Menu");
        setMinimumSize(new Dimension(300, 300));
        getContentPane().setBackground(Color.darkGray);
        setLayout(null);

        JLabel title = new JLabel("City Menu");
        title.setBounds(0,10,300,30);
        title.setHorizontalTextPosition(SwingConstants.CENTER);
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setForeground(Color.white);
        add(title);

        JButton button;
        button = new JButton("Add car");
        button.setBounds(100, 60, 100, 30);
        button.addActionListener(event -> openAddCar());
        add(button);

        button = new JButton("View cars");
        button.setBounds(100, 100, 100, 30);
        button.addActionListener(event -> openViewCars());
        add(button);

        button = new JButton("Back");
        button.setBounds(100, 200, 100, 30);
        button.addActionListener(event -> goBack());
        add(button);

        this.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                mainMenu.setVisible(true);
            }
        });
        setResizable(false);
        setLocationRelativeTo(mainMenu);
        setVisible(true);
        pack();
    }

    public void openAddCar()
    {
        try {
            new CarAddFrame(this);
            setEnabled(false);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void openViewCars()
    {
        try {
            new CarViewFrame(this);
            setEnabled(false);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void goBack()
    {
        dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
    }
}
