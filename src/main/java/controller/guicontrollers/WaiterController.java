package controller.guicontrollers;

import business.model.MenuItem;
import business.model.Order;
import business.utils.Restaurant;
import presentation.waiter.AddOrderGUI;
import presentation.waiter.BillGUI;
import business.validator.OrderValidator;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class WaiterController implements ActionListener {

    private AddOrderGUI addGUI = new AddOrderGUI();
    private BillGUI billGUI = new BillGUI();

    private OrderValidator orderValidator = new OrderValidator();
    private Restaurant restaurant;

    private static Order currentOrder;
    private static List<MenuItem> currentItems;
    private static int orderId = 0;

    public WaiterController(AddOrderGUI addGUI, Restaurant restaurant){
        this.addGUI = addGUI;
        this.restaurant = restaurant;
    }

    public WaiterController(BillGUI billGUI, Restaurant restaurant){
        this.billGUI = billGUI;
        this.restaurant = restaurant;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if(source == addGUI.getCreateOrderButton()){
            createOrder(addGUI.getTableText().getText(), addGUI.getDateText().getText());
        }
        if(source == addGUI.getAddToOrderButton()){
            addToOrder(addGUI.getItemName());
        }
        if(source == addGUI.getConfirmButton()){
            confirm();
        }
        if(source == billGUI.getGenerateBillButton()){
            generateBill(billGUI.getOrderId());
        }
    }

    public void createOrder(String table, String date){
        if(orderValidator.validateTable(table) && orderValidator.validateDate(date)){
            DateFormat df = new SimpleDateFormat("dd/MM/yy");
            df.setLenient(false);
            try{
                Date dateAux =  df.parse(date);
                currentItems = new ArrayList<>();
                currentOrder = new Order(++orderId, dateAux, Integer.parseInt(table));
                JOptionPane.showMessageDialog(null, "Order info added successfully. Please add items to the order");
            } catch (ParseException ex) {
                ex.printStackTrace();
            }
        }
    }

    public void addToOrder(String name){
        MenuItem item = restaurant.findItemByName(name);
        currentItems.add(item);
        JOptionPane.showMessageDialog(null, "Item has been added successfully. Please press confirm to register the order");
    }

    public void confirm(){
        restaurant.createOrder(currentOrder, currentItems);
        JOptionPane.showMessageDialog(null, "Order has been registered successfully");
    }

    public void generateBill(int orderId){
        Order order = restaurant.findOrderById(orderId);
        List<MenuItem> menuItems = restaurant.getOrderList().get(order);
        restaurant.generateBill(order, menuItems);
        JOptionPane.showMessageDialog(null, "Bill has been generated successfully");
    }
}
