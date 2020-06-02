package vlad.duncea.transport.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.sql.SQLException;

public class CityMenu extends JFrame {


    public CityMenu(JFrame mainMenu) {
        super("City Menu");
        setMinimumSize(new Dimension(300, 300));
        getContentPane().setBackground(Color.darkGray);
        setLayout(null);

        JButton button;
        button = new JButton("Add city");
        button.setBounds(100, 60, 100, 30);
        button.addActionListener(event -> openAdd());
        add(button);

        button = new JButton("View cities");
        button.setBounds(100, 100, 100, 30);
        button.addActionListener(event -> openView());
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
        setLocationRelativeTo(mainMenu);
        setResizable(false);
        setVisible(true);
        pack();
    }

    public void openAdd()
    {
        try {
            new CityAddFrame(this);
            setEnabled(false);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void openView()
    {
        try {
            new CityViewFrame(this);
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
