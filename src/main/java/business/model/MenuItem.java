package business.model;


import java.io.Serializable;

public interface MenuItem extends Serializable {

    public float computePrice();
    public String getName();

}


