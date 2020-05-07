package presentation.waiter;

import business.model.MenuItem;
import business.model.Order;
import business.utils.Restaurant;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.*;
import java.util.List;

public class ViewOrdersGUI {
    private JPanel ordersPanel;
    private JTable ordersTable;

    Restaurant res;

    public ViewOrdersGUI(Restaurant restaurant){

        ordersPanel = new JPanel(new GridBagLayout());
        this.res = restaurant;

        String[][] data = {};
        String[] column = {"orderId", "table", "date", "items"};
        DefaultTableModel tableModel = new DefaultTableModel(data, column);
        ordersTable = new JTable(tableModel);

        Iterator it = res.getOrderList().entrySet().iterator();
        while(it.hasNext()){
            Map.Entry pair = (Map.Entry)it.next();
            Order order = (Order)pair.getKey();
            List<MenuItem> orderItems = (List<MenuItem>)pair.getValue();

            String[] row = new String[4];
            row[0] = String.valueOf(order.getOrderId());
            row[1] = String.valueOf(order.getTable());
            row[2] = order.getDate().toString();
            row[3] = "";
            for(MenuItem mi : orderItems){
                row[3] += mi.getName() + "";
            }
            tableModel.addRow(row);
        }

        ordersPanel.add(new JScrollPane(ordersTable));

    }

    public JPanel getOrdersPanel(){
        return ordersPanel;
    }
}
