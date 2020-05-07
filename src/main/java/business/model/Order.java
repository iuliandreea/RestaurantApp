package business.model;

import java.util.Date;
import java.util.Objects;

public class Order {

    private int orderId;
    private Date date;
    private int table;

    public Order(){}

    public Order(int orderId, Date date, int table){
        this.orderId = orderId;
        this.date = date;
        this.table = table;
    }

    public void setOrderId(int orderId){
        this.orderId = orderId;
    }

    public void setDate(Date date){
        this.date = date;
    }

    public void setTable(int table){
        this.table = table;
    }

    public int getOrderId(){
        return orderId;
    }

    public Date getDate(){
        return date;
    }

    public int getTable(){
        return table;
    }

    @Override
    public int hashCode(){
        return Objects.hash(orderId, date, table);
    }

    @Override
    public boolean equals(Object obj){
       return Objects.equals(this, (Order)obj);
    }
}
