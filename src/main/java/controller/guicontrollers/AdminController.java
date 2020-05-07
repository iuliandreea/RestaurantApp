package controller.guicontrollers;

import business.model.BaseProduct;
import business.model.CompositeProduct;
import business.model.MenuItem;
import business.utils.Restaurant;
import presentation.administrator.*;
import business.validator.MenuItemValidator;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class AdminController implements ActionListener {

    private AddItemGUI addGUI = new AddItemGUI();
    private DeleteItemGUI deleteGUI = new DeleteItemGUI();
    private EditItemGUI editGUI = new EditItemGUI();

    private MenuItemValidator menuItemValidator = new MenuItemValidator();
    private Restaurant restaurant;
    private static CompositeProduct currentProduct;

    public AdminController(AddItemGUI addGUI, Restaurant restaurant){
        this.addGUI = addGUI;
        this.restaurant = restaurant;
    }
    public AdminController(DeleteItemGUI deleteGUI, Restaurant restaurant){
        this.deleteGUI = deleteGUI;
        this.restaurant = restaurant;
    }
    public AdminController(EditItemGUI editGUI, Restaurant restaurant){
        this.editGUI = editGUI;
        this.restaurant = restaurant;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if(source == addGUI.getCreateBPButton()){
            createBP(addGUI.getNameBP().getText(), addGUI.getPriceBP().getText());
        }
        if(source == addGUI.getCreateCPButton()){
            createCP(addGUI.getNameCP().getText());
        }
        if(source == addGUI.getAddToCPButton()){
            addNewToCP(addGUI.getItemName());
        }
        if(source == addGUI.getConfirmButton()){
            confirm();
        }
        if(source == deleteGUI.getDeleteButton()){
            delete(deleteGUI.getItemName());
        }
        if(source == editGUI.getUpdateBPButton()){
            updateBP(editGUI.getNameAll(), editGUI.getPriceBP().getText());
        }
        if(source == editGUI.getDeleteFromCPButton()){
            deleteFromCP(editGUI.getNameAll(), editGUI.getNameCP());
        }
        if(source == editGUI.getAddToCPButton()){
            addToCP(editGUI.getNameAll(), editGUI.getNameAllCP());
        }
    }

    private void createBP(String name, String price){
        if(menuItemValidator.validateName(name) && menuItemValidator.validatePrice(price)){
            MenuItem bp = new BaseProduct(name, Float.parseFloat(price));
            restaurant.createMenuItem(bp);
            JOptionPane.showMessageDialog(null, "Base Product created successfully");
        }
    }

    private void createCP(String name){
        if(menuItemValidator.validateName(name)){
            currentProduct = new CompositeProduct();
            currentProduct.setName(name);
            JOptionPane.showMessageDialog(null, "Composite Product created successfully. Please add items to it");
        }
    }

    private void addNewToCP(String add){
        MenuItem item = restaurant.findItemByName(add);
        currentProduct.addComponent(item);
        JOptionPane.showMessageDialog(null, "Menu Item added to Composite Product successfully. Press confirm if you wish to save the CP to the menu");
    }

    private void confirm(){
        if(currentProduct.getComponents().isEmpty()){
            JOptionPane.showMessageDialog(null, "Please add a MenuItem to the CP before registering it");
        }
        else{
            restaurant.createMenuItem(currentProduct);
            JOptionPane.showMessageDialog(null, "Composite Product has been added successfully");
        }
    }

    private void delete(String name){
        MenuItem item = restaurant.findItemByName(name);
        if(item != null){
            restaurant.deleteMenuItem(item);
        }
        List<MenuItem> toRemove = new ArrayList<>();
        for(MenuItem mi : restaurant.getMenuItemList()){
            if(mi instanceof CompositeProduct){
                if(((CompositeProduct) mi).getComponents().contains(item)){
                    toRemove.add(mi);
                }
            }
        }
        for(MenuItem remove : toRemove){
            restaurant.deleteMenuItem(remove);
        }
        toRemove.isEmpty();
        JOptionPane.showMessageDialog(null, "Menu Item deleted successfully");
    }

    private void updateBP(String name, String price){
        if(menuItemValidator.validatePrice(price)) {
            MenuItem oldItem = restaurant.findItemByName(name);
            if(menuItemValidator.validateBaseProduct(oldItem)){
                MenuItem newItem = new BaseProduct(name, Float.parseFloat(price));
                restaurant.updateMenuItem(oldItem, newItem);
                for(MenuItem mi : restaurant.getMenuItemList()){
                    if((mi instanceof CompositeProduct) && ((CompositeProduct) mi).getComponents().contains(oldItem)){
                        ((CompositeProduct) mi).updateComponent(oldItem, newItem);
                    }
                }
                JOptionPane.showMessageDialog(null, "Base Product updated successfully");
            }
        }
    }

    private void deleteFromCP(String name, String delete){
        MenuItem oldItem = restaurant.findItemByName(name);
        MenuItem toDel = restaurant.findItemByName(delete);
        if (menuItemValidator.validateCompositeProduct(oldItem)) {
            CompositeProduct newItem = (CompositeProduct)oldItem;
            newItem.removeComponent(toDel);
            if(newItem.getComponents().isEmpty()){
                restaurant.deleteMenuItem(oldItem);
            }
            else{
                restaurant.updateMenuItem(oldItem, newItem);
            }
            JOptionPane.showMessageDialog(null, "Menu Item deleted from Composite Product Successfully");
        }
    }

    public void addToCP(String name, String add){
        MenuItem oldItem = restaurant.findItemByName(name);
        MenuItem toAdd = restaurant.findItemByName(add);
        if(menuItemValidator.validateCompositeProduct(oldItem)){
            CompositeProduct newItem = (CompositeProduct)oldItem;
            newItem.addComponent(toAdd);
            restaurant.updateMenuItem(oldItem, newItem);
            JOptionPane.showMessageDialog(null, "Menu Item added to the Composite Product successfully");
        }
    }
}
