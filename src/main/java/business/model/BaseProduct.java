package business.model;

import java.io.Serializable;

public class BaseProduct implements MenuItem, Serializable {

    private String name;
    private float price;

    public BaseProduct(){
    }

    public BaseProduct(String name, float price){
        this.name = name;
        this.price = price;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setPrice(float price){
        this.price = price;
    }

    public String getName(){
        return name;
    }

    public float getPrice(){
        return price;
    }

    @Override
    public float computePrice() {
        return price;
    }

}
