package vlad.duncea.transport.main;

import vlad.duncea.transport.gui.CarMenu;
import vlad.duncea.transport.gui.CarViewFrame;
import vlad.duncea.transport.gui.CityMenu;
import vlad.duncea.transport.gui.DriverMenu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.sql.SQLException;

public class MainGui {
    private static JButton button;
    private static JFrame f;
    public static void main(String[] args) {
        f = new JFrame("Main Menu");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setMinimumSize(new Dimension(300, 300));
        f.getContentPane().setBackground(Color.darkGray);
        f.setLayout(null);

        JLabel title = new JLabel("Transport Application");
        title.setBounds(0,10,300,30);
        title.setHorizontalTextPosition(SwingConstants.CENTER);
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setForeground(Color.white);
        f.add(title);

        button = new JButton("Car Menu");
        button.setBounds(75, 60, 150, 30);
        button.addActionListener(event -> openCarMenu());
        f.add(button);

        button = new JButton("City Menu");
        button.setBounds(75, 100, 150, 30);
        button.addActionListener(event -> openCityMenu());
        f.add(button);

        button = new JButton("Driver Menu");
        button.setBounds(75, 140, 150, 30);
        button.addActionListener(event -> openDriverMenu());
        f.add(button);

        button = new JButton("Quit");
        button.setBounds(100, 200, 100, 30);
        button.addActionListener(event -> quit());
        f.add(button);

        f.setLocationRelativeTo(null);
        f.setVisible(true);
        f.pack();
    }

    public static void openCarMenu()
    {
        new CarMenu(f);
        f.setVisible(false);
    }

    public static void openCityMenu()
    {
        new CityMenu(f);
        f.setVisible(false);
    }

    public static void openDriverMenu()
    {
        new DriverMenu(f);
        f.setVisible(false);
    }

    private static void quit()
    {
        f.dispatchEvent(new WindowEvent(f, WindowEvent.WINDOW_CLOSING));
    }
}
