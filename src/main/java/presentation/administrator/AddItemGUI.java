package presentation.administrator;

import business.utils.Restaurant;
import controller.guicontrollers.AdminController;

import javax.swing.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class AddItemGUI {
    private JPanel addItemPanel;
    private JTextField nameBP;
    private JTextField priceBP;
    private JButton createBPButton;
    private JTextField nameCP;
    private JComboBox listBP;
    private JButton createCPButton;
    private JButton addToCPButton;
    private JButton confirmButton;

    private Restaurant res;
    private AdminController controller;

    private String nameCombo;

    public AddItemGUI(){}

    public AddItemGUI(Restaurant restaurant) {

        this.res = restaurant;
        controller = new AdminController(this, res);

        if(!res.getMenuItemList().isEmpty()){
            for(String mi : res.getMenuItemListNames()) {
                listBP.addItem(mi);
            }
            nameCombo = listBP.getItemAt(0).toString();
        }

        createBPButton.addActionListener(controller);
        createCPButton.addActionListener(controller);
        addToCPButton.addActionListener(controller);
        confirmButton.addActionListener(controller);

        listBP.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                nameCombo = listBP.getSelectedItem().toString();
            }
        });
    }

    public JPanel getAddItemPanel(){
        return addItemPanel;
    }
    public JButton getCreateBPButton(){
        return createBPButton;
    }
    public JButton getCreateCPButton(){
        return createCPButton;
    }
    public JButton getAddToCPButton(){
        return addToCPButton;
    }
    public JButton getConfirmButton(){
        return confirmButton;
    }
    public JTextField getNameBP(){
        return nameBP;
    }
    public JTextField getPriceBP(){
        return priceBP;
    }
    public JTextField getNameCP(){
        return nameCP;
    }
    public String getItemName(){
        return nameCombo;
    }
}
