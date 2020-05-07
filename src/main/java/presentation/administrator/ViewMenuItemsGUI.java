package presentation.administrator;

import business.model.BaseProduct;
import business.model.CompositeProduct;
import business.model.MenuItem;
import business.utils.Restaurant;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class ViewMenuItemsGUI {
    private JPanel menuItemsPanel;
    private JTable menuItemsTable;

    Restaurant res;

    public ViewMenuItemsGUI(){}

    public ViewMenuItemsGUI(Restaurant restaurant ){

        menuItemsPanel = new JPanel(new GridBagLayout());
        this.res = restaurant;

        String[][] data = {};
        String[] column = {"name", "price", "details"};
        DefaultTableModel tableModel = new DefaultTableModel(data, column);
        menuItemsTable = new JTable(tableModel);

        for(MenuItem mi : restaurant.getMenuItemList()){
            String[] row = new String[3];
            row[0] = mi.getName();
            row[1] = String.valueOf(mi.computePrice());
            if(mi instanceof BaseProduct){
                row[2] = "base product";
            }
            if(mi instanceof CompositeProduct){
                row[2] = "";
                for(MenuItem component : ((CompositeProduct) mi).getComponents()){
                    row[2] += component.getName() + " ";
                }
            }
            tableModel.addRow(row);
        }

        menuItemsPanel.add(new JScrollPane(menuItemsTable));
    }

    public JPanel getMenuItemsPanel(){
        return menuItemsPanel;
    }
}
