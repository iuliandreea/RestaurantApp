package presentation.waiter;

import business.utils.Restaurant;
import controller.guicontrollers.WaiterController;

import javax.swing.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class AddOrderGUI {
    private JPanel addOrderPanel;
    private JTextField tableText;
    private JTextField dateText;
    private JComboBox itemComboBox;
    private JButton createOrderButton;
    private JButton addToOrderButton;
    private JButton confirmButton;

    private WaiterController controller;
    private Restaurant res;
    private String name;

    public AddOrderGUI(){}

    public AddOrderGUI(Restaurant restaurant) {
        this.res = restaurant;
        controller = new WaiterController(this, res);
        if(!res.getMenuItemListNames().isEmpty()){
            for(String mi : res.getMenuItemListNames()) {
                itemComboBox.addItem(mi);
            }
            name = itemComboBox.getItemAt(0).toString();
        }
        createOrderButton.addActionListener(controller);
        addToOrderButton.addActionListener(controller);
        confirmButton.addActionListener(controller);
        itemComboBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                name = itemComboBox.getSelectedItem().toString();
            }
        });
    }

    public JPanel getAddOrderPanel(){
        return addOrderPanel;
    }
    public JTextField getTableText(){
        return tableText;
    }
    public JTextField getDateText(){
        return dateText;
    }
    public JButton getCreateOrderButton(){
        return createOrderButton;
    }
    public JButton getAddToOrderButton(){
        return addToOrderButton;
    }
    public JButton getConfirmButton(){
        return confirmButton;
    }
    public String getItemName(){
        return name;
    }
}
