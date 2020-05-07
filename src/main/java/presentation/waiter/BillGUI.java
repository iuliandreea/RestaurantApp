package presentation.waiter;

import business.model.Order;
import business.utils.Restaurant;
import controller.guicontrollers.WaiterController;

import javax.swing.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class BillGUI {
    private JPanel billPanel;
    private JComboBox orderComboBox;
    private JTextField tableText;
    private JTextField dateText;
    private JButton generateBillButton;

    private WaiterController controller;
    private Restaurant res;
    private int orderId;


    public BillGUI(){}

    public BillGUI(Restaurant restaurant) {

        this.res = restaurant;
        controller = new WaiterController(this, res);

        if(!res.getOrderList().keySet().isEmpty()){
            for(Order mi : res.getOrderList().keySet()) {
                orderComboBox.addItem(mi.getOrderId());
            }
            orderId = Integer.parseInt(orderComboBox.getItemAt(0).toString());
        }

        generateBillButton.addActionListener(controller);

      /*  generateBillButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                for(Order o : res.orderInfo){
                    if(orderId == o.getOrderId()){
                        List<MenuItem> orderItems = res.orderList.get(o);
                        bi.generateBill(o, orderItems);
                    }
                }
            }
        });*/
        orderComboBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                orderId = Integer.parseInt(orderComboBox.getSelectedItem().toString());
            }
        });
    }

    public JPanel getBillPanel(){
        return billPanel;
    }
    public JButton getGenerateBillButton(){
        return generateBillButton;
    }
    public void setTableText(String message){
        tableText.setText(message);
    }
    public void setDateText(String message){
        dateText.setText(message);
    }
    public int getOrderId(){
        return orderId;
    }
}
