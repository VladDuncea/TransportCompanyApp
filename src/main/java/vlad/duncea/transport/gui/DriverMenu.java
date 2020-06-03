package vlad.duncea.transport.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.sql.SQLException;

public class DriverMenu extends JFrame{

    public DriverMenu(JFrame mainMenu) {
        super("Driver Menu");
        setMinimumSize(new Dimension(300, 350));
        getContentPane().setBackground(Color.darkGray);
        setLayout(null);

        JLabel title = new JLabel("Driver Menu");
        title.setBounds(0,10,300,30);
        title.setHorizontalTextPosition(SwingConstants.CENTER);
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setForeground(Color.white);
        add(title);

        JButton button;
        button = new JButton("Add driver");
        button.setBounds(75, 40, 150, 30);
        button.addActionListener(event -> openAdd());
        add(button);

        button = new JButton("View drivers");
        button.setBounds(75, 80, 150, 30);
        button.addActionListener(event -> openView());
        add(button);

        button = new JButton("Remove driver");
        button.setBounds(75, 120, 150, 30);
        button.addActionListener(event -> openRemove());
        add(button);

        button = new JButton("Give car to driver");
        button.setBounds(75, 160, 150, 30);
        button.addActionListener(event -> openUpdate());
        add(button);

        button = new JButton("Back");
        button.setBounds(100, 250, 100, 30);
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
            new DriverAddFrame(this);
            setEnabled(false);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void openView()
    {
        try {
            new DriverViewFrame(this);
            setEnabled(false);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void openRemove()
    {
        try {
            new DriverRemoveFrame(this);
            setEnabled(false);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void openUpdate()
    {
        try {
            new DriverSetCarFrame(this);
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
