package business.utils;

import java.util.ArrayList;
import java.util.List;

import presentation.chef.Observer;

public class Observable {

    private List<Observer> observers = new ArrayList<>();

    public void registerObserver(Observer o) {
        observers.add(o);
    }

    public void unregisterObserver(Observer o ) {
        observers.remove(o);
    }

    public void notifyObserver(String message){
        for(Observer o : observers){
            o.update(message);
        }
    }

}
