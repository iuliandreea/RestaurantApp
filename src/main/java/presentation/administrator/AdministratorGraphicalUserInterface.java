package presentation.administrator;

import business.utils.Restaurant;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdministratorGraphicalUserInterface {
    private JPanel adminPanel;
    private JButton addCompositeProductButton;
    private JButton editMenuItemButton;
    private JButton viewAllMenuItemsButton;
    private JButton deleteMenuItemButton;

    Restaurant restaurant;

    public AdministratorGraphicalUserInterface(){}

    public AdministratorGraphicalUserInterface(Restaurant restaurant) {

        this.restaurant = restaurant;


        editMenuItemButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame adminFrame = new JFrame("MenuItems GUI");
                adminFrame.setContentPane(new EditItemGUI(restaurant).getEditItemPanel());
                adminFrame.setDefaultCloseOperation((JFrame.DISPOSE_ON_CLOSE));
                adminFrame.pack();
                adminFrame.setVisible(true);
            }
        });
        viewAllMenuItemsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame adminFrame = new JFrame("MenuItems GUI");
                adminFrame.setContentPane(new ViewMenuItemsGUI(restaurant).getMenuItemsPanel());
                adminFrame.setDefaultCloseOperation((JFrame.DISPOSE_ON_CLOSE));
                adminFrame.pack();
                adminFrame.setVisible(true);
            }
        });
        addCompositeProductButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame adminFrame = new JFrame("MenuItems GUI");
                adminFrame.setContentPane(new AddItemGUI(restaurant).getAddItemPanel());
                adminFrame.setDefaultCloseOperation((JFrame.DISPOSE_ON_CLOSE));
                adminFrame.pack();
                adminFrame.setVisible(true);
            }
        });
        deleteMenuItemButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame adminFrame = new JFrame("MenuItems GUI");
                adminFrame.setContentPane(new DeleteItemGUI(restaurant).getDeleteItemPanel());
                adminFrame.setDefaultCloseOperation((JFrame.DISPOSE_ON_CLOSE));
                adminFrame.pack();
                adminFrame.setVisible(true);
            }
        });
    }

    public JPanel getAdminPanel(){
        return adminPanel;
    }
}
