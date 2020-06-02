package vlad.duncea.transport.main;

import vlad.duncea.transport.gui.CarMenu;
import vlad.duncea.transport.gui.CarViewFrame;
import vlad.duncea.transport.gui.CityMenu;

import javax.swing.*;
import java.awt.*;
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

        button = new JButton("Car Menu");
        button.setBounds(100, 60, 100, 30);
        button.addActionListener(event -> openCarMenu());
        f.add(button);

        button = new JButton("City Menu");
        button.setBounds(100, 100, 100, 30);
        button.addActionListener(event -> openCityMenu());
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
}
