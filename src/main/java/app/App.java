package app;

import business.model.MenuItem;
import business.utils.Restaurant;
import controller.data.RestaurantSerializator;
import presentation.main.MainGraphicalUserInterface;

import javax.swing.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class App {

    public static void main(String[] args) {
        RestaurantSerializator rs = new RestaurantSerializator();
        List<MenuItem> menu = new ArrayList<>();
        File file;
        Restaurant restaurant = new Restaurant();
        if(args.length != 0){
            file = new File(args[0]);
            if(file.exists() && file.length() != 0){
                 menu = rs.deserializeMenu(args[0]);
                 restaurant.setMenuItemList(menu);
            }
        }
        else{
            file = new File("restaurant.ser");
            if(file.exists() && file.length() != 0){
                menu = rs.deserializeMenu("restaurant.ser");
                restaurant.setMenuItemList(menu);
            }
        }
        JFrame frame = new JFrame("Main GUI");
        frame.setContentPane(new MainGraphicalUserInterface(restaurant, file).getMainPanel());
        frame.setDefaultCloseOperation((JFrame.EXIT_ON_CLOSE));
        frame.pack();
        frame.setVisible(true);
    }
}
