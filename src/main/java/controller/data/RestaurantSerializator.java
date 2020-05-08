package controller.data;

import business.model.MenuItem;

import java.io.*;
import java.util.List;

public class RestaurantSerializator {

    public void serializeMenu(File file, List<MenuItem> menuItems){
        try{
            FileOutputStream fileStream = new FileOutputStream(file, false);
            ObjectOutputStream out = new ObjectOutputStream(fileStream);
            out.writeObject(menuItems);
            out.close();
            fileStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<MenuItem> deserializeMenu(String fileName){
        List<MenuItem> menuItems = null;
        try{
            FileInputStream file = new FileInputStream(fileName);
            ObjectInputStream in = new ObjectInputStream(file);
            menuItems = (List<MenuItem>)in.readObject();
            in.close();
            file.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return menuItems;
    }
}
