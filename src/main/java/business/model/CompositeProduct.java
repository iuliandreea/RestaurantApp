package business.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CompositeProduct implements MenuItem, Serializable {

    private String name;
    private List<MenuItem> components;
    private float price;

    public CompositeProduct(){
        components = new ArrayList<>();
        price = 0;
    }

    public CompositeProduct(String name){
        this.name = name;
        this.price = 0;
        this.components = new ArrayList<>();
    }

    public CompositeProduct(String name, List<MenuItem> components){
        this.name = name;
        this.components = components;
        computePrice();
    }

    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public void setPrice(float price){
        this.price = price;
    }

    public float getPrice(){
        return price;
    }

    public void addComponent(MenuItem component){
        components.add(component);
        computePrice();
    }

    public void removeComponent(MenuItem component){
        components.remove(component);
        computePrice();
    }

    public void removeAllComponents(){
        components.clear();
    }

    public void updateComponent(MenuItem oldComponent, MenuItem newComponent){
        int index = components.indexOf(oldComponent);
        components.remove(oldComponent);
        components.add(index, newComponent);
        computePrice();
    }

    public List<MenuItem> getComponents(){
        return components;
    }

    @Override
    public float computePrice() {
        float priceAux = 0;
        for(MenuItem component : components){
            priceAux += component.computePrice();
        }
        price = priceAux;
        return price;
    }
}
