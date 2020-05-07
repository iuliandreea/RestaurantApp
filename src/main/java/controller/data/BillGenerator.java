package controller.data;


import business.model.MenuItem;
import business.model.Order;
import business.utils.Restaurant;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class BillGenerator {

    Restaurant restaurant;

    public BillGenerator(Restaurant restaurant){
        this.restaurant = restaurant;
    }

    public void generateBill(Order order, List<MenuItem> orderList) {

        try {
            FileWriter out = new FileWriter("BillForOrder" + String.valueOf(order.getOrderId()) + ".txt");

            out.append("Bill for Order " + String.valueOf(order.getOrderId()) + '\n');

            for(MenuItem mi : orderList){
                out.append(mi.getName() + " " + mi.computePrice() + '\n');
            }
            out.append("Total: " + restaurant.computePrice(order));

            out.flush();
            out.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
