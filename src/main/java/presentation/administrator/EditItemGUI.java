package presentation.administrator;

import business.model.CompositeProduct;
import business.model.MenuItem;
import business.utils.Restaurant;
import controller.guicontrollers.AdminController;

import javax.swing.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class EditItemGUI {
    private JPanel editItemPanel;
    private JComboBox allItemsComboBox;
    private JTextField priceBP;
    private JComboBox itemsCPComboBox;
    private JComboBox allItemsCPComboBox;
    private JButton updateBPButton;
    private JButton deleteFromCPButton;
    private JButton addToCPButton;

    private Restaurant res;
    private AdminController controller;

    private String nameAll, nameCP, nameAllCP;

    public EditItemGUI(){}

    public EditItemGUI(Restaurant restaurant) {

        this.res = restaurant;
        controller = new AdminController(this, res);

        if(!res.getMenuItemListNames().isEmpty()){
            for(String mi : res.getMenuItemListNames()) {
                allItemsComboBox.addItem(mi);
                allItemsCPComboBox.addItem(mi);
            }
            nameAll = allItemsComboBox.getItemAt(0).toString();
            nameAllCP = allItemsCPComboBox.getItemAt(0).toString();
        }

        updateBPButton.addActionListener(controller);
        deleteFromCPButton.addActionListener(controller);
        addToCPButton.addActionListener(controller);

        allItemsComboBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                itemsCPComboBox.removeAllItems();
                nameAll = allItemsComboBox.getSelectedItem().toString();
                MenuItem item = res.findItemByName(nameAll);
                if(item instanceof CompositeProduct && !((CompositeProduct) item).getComponents().isEmpty()){
                    for(MenuItem mi : ((CompositeProduct) item).getComponents()){
                        itemsCPComboBox.addItem(mi.getName());
                    }
                    nameCP = itemsCPComboBox.getItemAt(0).toString();
                }
            }
        });
        itemsCPComboBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if(itemsCPComboBox.getItemCount() > 0){
                    nameCP = itemsCPComboBox.getSelectedItem().toString();
                }
            }
        });
        allItemsCPComboBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                nameAllCP = allItemsCPComboBox.getSelectedItem().toString();
            }
        });
    }

    public JPanel getEditItemPanel(){
        return editItemPanel;
    }
    public JButton getUpdateBPButton(){
        return updateBPButton;
    }
    public JButton getDeleteFromCPButton(){
        return deleteFromCPButton;
    }
    public JButton getAddToCPButton(){
        return addToCPButton;
    }
    public JTextField getPriceBP(){
        return priceBP;
    }
    public String getNameAll(){
        return nameAll;
    }
    public String getNameCP(){
        return nameCP;
    }
    public String getNameAllCP(){
        return nameAllCP;
    }
}
