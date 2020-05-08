package presentation.waiter;

import business.utils.Restaurant;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WaiterGraphicalUserInterface {
    private JPanel waiterPanel;
    private JButton addNewOrderButton;
    private JButton viewAllOrdersButton;
    private JButton generateBillButton;

    Restaurant restaurant;

    public WaiterGraphicalUserInterface(Restaurant restaurant) {
        this.restaurant = restaurant;
        addNewOrderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame adminFrame = new JFrame("Administrator GUI");
                adminFrame.setContentPane(new AddOrderGUI(restaurant).getAddOrderPanel());
                adminFrame.setDefaultCloseOperation((JFrame.DISPOSE_ON_CLOSE));
                adminFrame.pack();
                adminFrame.setVisible(true);
            }
        });
        viewAllOrdersButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame adminFrame = new JFrame("Administrator GUI");
                adminFrame.setContentPane(new ViewOrdersGUI(restaurant).getOrdersPanel());
                adminFrame.setDefaultCloseOperation((JFrame.DISPOSE_ON_CLOSE));
                adminFrame.pack();
                adminFrame.setVisible(true);
            }
        });
        generateBillButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame adminFrame = new JFrame("Bill GUI");
                adminFrame.setContentPane(new BillGUI(restaurant).getBillPanel());
                adminFrame.setDefaultCloseOperation((JFrame.DISPOSE_ON_CLOSE));
                adminFrame.pack();
                adminFrame.setVisible(true);
            }
        });
    }

    public JPanel getWaiterPanel(){
        return waiterPanel;
    }
}
