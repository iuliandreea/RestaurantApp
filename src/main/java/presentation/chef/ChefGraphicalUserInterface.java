package presentation.chef;

import business.utils.Restaurant;

import javax.swing.*;

public class ChefGraphicalUserInterface extends JFrame implements Observer {

    Restaurant res;
    private JPanel chefPanel;
    private JLabel notifLabel;

    public ChefGraphicalUserInterface(Restaurant restaurant){

        this.res = restaurant;
        restaurant.registerObserver(this);

    }

    @Override
    public void update(String message) {
        notifLabel.setText(message);
    }

    public JPanel getChefPanel(){
        return chefPanel;
    }
}
