package presentation.administrator;

import business.utils.Restaurant;
import controller.guicontrollers.AdminController;

import javax.swing.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class DeleteItemGUI {
    private JPanel deleteItemPanel;
    private JComboBox deleteComboBox;
    private JButton deleteButton;

    private String itemName;
    private Restaurant res;
    private AdminController controller;

    public DeleteItemGUI(){}

    public DeleteItemGUI(Restaurant restaurant) {

        this.res = restaurant;
        controller = new AdminController(this, res);

        if(!res.getMenuItemList().isEmpty()){
            for(String mi : res.getMenuItemListNames()) {
                deleteComboBox.addItem(mi);
            }
            itemName = deleteComboBox.getItemAt(0).toString();
        }

        deleteButton.addActionListener(controller);

        deleteComboBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                itemName = deleteComboBox.getSelectedItem().toString();
            }
        });
    }

    public JPanel getDeleteItemPanel(){
        return deleteItemPanel;
    }
    public JButton getDeleteButton(){
        return deleteButton;
    }
    public String getItemName(){
        return itemName;
    }
}
