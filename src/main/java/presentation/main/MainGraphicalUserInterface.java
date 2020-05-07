package presentation.main;

import business.utils.Restaurant;
import controller.data.RestaurantSerializator;
import presentation.administrator.AdministratorGraphicalUserInterface;
import presentation.chef.ChefGraphicalUserInterface;
import presentation.waiter.WaiterGraphicalUserInterface;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class MainGraphicalUserInterface {
    private JPanel mainPanel;
    private JButton administratorButton;
    private JButton waiterButton;
    private JButton chefButton;
    private JButton saveMenuDataButton;

    Restaurant res;

    public MainGraphicalUserInterface(Restaurant restaurant, File file) {

        this.res = restaurant;

        administratorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame adminFrame = new JFrame("Administrator GUI");
                adminFrame.setContentPane(new AdministratorGraphicalUserInterface(res).getAdminPanel());
                adminFrame.setDefaultCloseOperation((JFrame.DISPOSE_ON_CLOSE));
                adminFrame.pack();
                adminFrame.setVisible(true);
            }
        });
        waiterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame waiterFrame = new JFrame("Waiter GUI");
                waiterFrame.setContentPane(new WaiterGraphicalUserInterface(res).getWaiterPanel());
                waiterFrame.setDefaultCloseOperation((JFrame.DISPOSE_ON_CLOSE));
                waiterFrame.pack();
                waiterFrame.setVisible(true);
            }
        });
        chefButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame chefFrame = new JFrame("Chef GUI");
                chefFrame.setContentPane(new ChefGraphicalUserInterface(res).getChefPanel());
                chefFrame.setDefaultCloseOperation((JFrame.DISPOSE_ON_CLOSE));
                chefFrame.pack();
                chefFrame.setVisible(true);
            }
        });
        saveMenuDataButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RestaurantSerializator rs = new RestaurantSerializator();
                rs.serializeMenu(file, res.getMenuItemList());
            }
        });
    }

    public JPanel getMainPanel(){
        return mainPanel;
    }
}
