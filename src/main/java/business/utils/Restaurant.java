package business.utils;

import business.model.MenuItem;
import business.model.Order;
import controller.data.BillGenerator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


/**
 * @inv orderList != null &amp;&amp; menuItemList != null
 */
public class Restaurant extends Observable implements IRestaurantProcessing {

    /**
     *
     * field of type HashMap containing the list of orders and their respective MenuItems
     */
    private HashMap<Order, List<MenuItem>> orderList = new HashMap<>();

    /**
     *
     * field of type List containing all the MenuItems available in the menu
     */
    private List<MenuItem> menuItemList;

    /**
     *
     * basic constructor
     * @pre true
     * @post ordreList != null &amp;&amp; menuItemList != null
     */
    public Restaurant(){
        super();
        orderList = new HashMap<>();
        menuItemList = new ArrayList<>();
        assert (orderList != null && menuItemList != null);
    }

    /**
     *
     * constructor with both fields
     * @pre orderList != null &amp;&amp; menuItemList != null
     * @post this.orderList != null &amp;&amp; this.menuItemList != null
     * @param orderList HashMap of orders and their respective MenuItems
     * @param menuItemList list of MenuItems
     */
    public Restaurant(HashMap<Order, List<MenuItem>> orderList, List<MenuItem> menuItemList){
        super();
        assert(orderList != null && menuItemList != null);
        this.orderList = orderList;
        this.menuItemList = menuItemList;
        assert(this.orderList != null && this.menuItemList != null);
    }

    /**
     *
     * constructor with only the MenuItem list field
     * @pre menuItemList != null
     * @post this.menuItemList != null &amp;&amp; this.orderList != null
     * @param menuItemList list of MenuItems
     */
    public Restaurant(List<MenuItem> menuItemList){
        super();
        assert menuItemList != null;
        this.menuItemList = menuItemList;
        orderList = new HashMap<>();
        assert(this.menuItemList != null && this.orderList != null);
    }

    /**
     *
     * getter method for the orderList HashMap
     * @pre true
     * @post no change
     * @return the orderList HashMap
     */
    public HashMap<Order, List<MenuItem>> getOrderList() {
        return orderList;
    }

    /**
     *
     * setter method for menuItemList
     * @pre given menuItemList != null
     * @post this.menuItemList != null
     * @param menuItemList the new menuItemList
     */
    public void setMenuItemList(List<MenuItem> menuItemList){
        assert menuItemList != null;
        this.menuItemList = menuItemList;
        assert(this.menuItemList != null);
    }

    /**
     *
     * method to find an order from the HashMap based on its id
     * @pre id &gt; 0 &amp;&amp; orderList != null
     * @post no change
     * @param id int representing the id of the order
     * @return the Order object with the given id
     */
    public Order findOrderById(int id){
        assert (id > 0) && (orderList != null);
        List<Order> orders = new ArrayList<>();
        orders.addAll(orderList.keySet());
        for(Order o : orders){
            if(id == o.getOrderId()){
                return o;
            }
        }
        return null;
    }

    /**
     *
     * method to find a MenuItem from the list based on its name
     * @pre name != null &amp;&amp; menuItemList != null
     * @post no change
     * @param name the name of the MenuItem to be searched and found
     * @return the MenuItem with the given name
     */
    public MenuItem findItemByName(String name){
        assert (name != null && menuItemList != null);
        for(MenuItem mi : menuItemList){
            if(name.compareToIgnoreCase(mi.getName()) == 0){
                return mi;
            }
        }
        return null;
    }

    /**
     *
     * getter method which returns a list containing the names of all MenuItems
     * @pre menuItemList != null
     * @post no change
     * @return a list of Strings representing the names
     */
    public List<String> getMenuItemListNames(){
        assert menuItemList != null;
        List<String> list = new ArrayList<>();
        for(MenuItem mi : menuItemList){
            list.add(mi.getName());
        }
        return list;
    }

    /**
     *
     * getter method which returns the list of MenuItems
     * @pre true
     * @post no change
     * @return a list of MenuItems
     */
    public List<MenuItem> getMenuItemList(){
        return menuItemList;
    }

    /**
     *
     * method to add a new MenuObject to the list
     * @pre menuItemList != null &amp;&amp; menuItem != null
     * @post menuItemList.size() = menuItemList.size() + 1 &amp;&amp; menuItemList.contains(menuItem)
     * @param menuItem the MenuItem object to be added to the list
     */
    @Override
    public void createMenuItem(MenuItem menuItem) {
        assert (menuItemList != null && menuItem != null);
        int size = menuItemList.size();
        menuItemList.add(menuItem);
        assert((menuItemList.size() == size + 1) && menuItemList.contains(menuItem));
    }

    /**
     *
     * method to remove a MenuItem from the list
     * @pre menuItemList.contains(menuItem) &amp;&amp; !menuItemList.isEmpty()
     * @post menuItemList.size() = menuItemList.size() - 1
     * @param menuItem MenuItem object to be removed from the list
     */
    @Override
    public void deleteMenuItem(MenuItem menuItem) {
        assert (menuItemList.contains(menuItem) && !menuItemList.isEmpty());
        int size = menuItemList.size();
        menuItemList.remove(menuItem);
        assert menuItemList.size() == size - 1;
    }

    /**
     *
     * method to update an existing MenuItem from the list of MenuItems
     * @pre menuItemList.contains(oldItem) &amp;&amp; newItem != null
     * @post menuItemList.get(oldItem.index) == newItem
     * @param oldItem the MenuItem object to be updated
     * @param newItem the MenuItem object which contains the modified information from oldItem
     */
    @Override
    public void updateMenuItem(MenuItem oldItem, MenuItem newItem) {
        assert(menuItemList.contains(oldItem) && newItem != null);
        int index = menuItemList.indexOf(oldItem);
        menuItemList.remove(oldItem);
        menuItemList.add(index, newItem);
        assert menuItemList.get(index).getName().equalsIgnoreCase(newItem.getName());
    }

    /**
     *
     * method to add a new order to the HashMap
     * @pre (order != null) &amp;&amp; (orderItems != null) &amp;&amp; !orderItems.isEmpty()
     * @post orderList.size() != 0
     * @param order Order object containing information about the table and the date
     * @param orderItems list of MenuItem objects containing all the items in the order
     */
    @Override
    public void createOrder(Order order, List<MenuItem> orderItems){
        assert (order != null && orderItems != null && !orderItems.isEmpty());
        orderList.put(order, orderItems);
        String message;
        message = "Chef will soon prepare order " + order.getOrderId() + " containing:  ";
        for(MenuItem mi : orderItems){
            message += mi.getName() + " ";
        }
        notifyObserver(message);
        assert orderList.size() != 0;
    }

    /**
     *
     * method to compute the price of an order
     * @pre (order != null) &amp;&amp; (orderList.containsKey(order))
     * @post price &gt;= 0
     * @param order Order object whose price will be computed
     * @return the price of the Order given as parameter
     */
    @Override
    public float computePrice(Order order) {
        assert(order != null && orderList.containsKey(order));
        float price = 0;
        List<MenuItem> orderItems = orderList.get(order);
        for(MenuItem mi : orderItems){
            price += mi.computePrice();
        }
        assert price >= 0;
        return price;
    }

    /**
     *
     * method to generate the bill for an order
     * @pre (order != null) &amp;&amp; (itemList != null) &amp;&amp; !itemList.isEmpty(). order and itemList separated from HashMap before calling
     * @post no change
     * @param order order whose bill will be generated
     * @param itemList list of items corresponding to the order
     */
    @Override
    public void generateBill(Order order, List<MenuItem> itemList) {
        assert(order != null && itemList != null && !itemList.isEmpty() && orderList.containsKey(order) && orderList.containsValue(itemList));
        BillGenerator bg = new BillGenerator(this);
        bg.generateBill(order, itemList);
    }
}
