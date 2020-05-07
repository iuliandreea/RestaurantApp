package business.validator;

import business.model.BaseProduct;
import business.model.CompositeProduct;
import business.model.MenuItem;

import javax.swing.*;

public class MenuItemValidator {

    public boolean validateName(String name){
        if(name == null || !name.matches("([a-zA-Z]\\s?)+")){
            JOptionPane.showMessageDialog(null, "Please enter a valid name");
            return false;
        }
        return true;
    }

    public boolean validatePrice(String price){
        if(price == null || !price.matches("[0-9]+(\\.[0-9]+)?")){
            JOptionPane.showMessageDialog(null, "Please enter a valid price");
            return false;
        }
        return true;
    }

    public boolean validateCompositeProduct(MenuItem menuItem){
        if(menuItem instanceof CompositeProduct){
            return true;
        }
        JOptionPane.showMessageDialog(null, "Chosen Menu Item not a Composite Product");
        return false;
    }

    public boolean validateBaseProduct(MenuItem menuItem){
        if(menuItem instanceof BaseProduct){
            return true;
        }
        JOptionPane.showMessageDialog(null, "Chosen Menu Item not a Base Product");
        return false;
    }
}
