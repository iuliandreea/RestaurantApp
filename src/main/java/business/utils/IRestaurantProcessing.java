package business.utils;

import business.model.MenuItem;
import business.model.Order;

import java.util.List;

public interface IRestaurantProcessing {

    public void createMenuItem(MenuItem menuItem);
    public void deleteMenuItem(MenuItem menuItem);
    public void updateMenuItem(MenuItem oldItem, MenuItem newItem);

    public void createOrder(Order order, List<MenuItem> menuItemList);
    public float computePrice(Order order);
    public void generateBill(Order order, List<MenuItem> menuItemList);
}
