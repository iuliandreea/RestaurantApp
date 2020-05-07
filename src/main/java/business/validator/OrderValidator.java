package business.validator;

import javax.swing.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class OrderValidator {

    public boolean validateTable(String table){
        if(table == null || !table.matches("[0-9]+")) {
            JOptionPane.showMessageDialog(null, "Please enter a valid table number");
            return false;
        }
        return true;
    }

    public boolean validateDate(String date){
        DateFormat df = new SimpleDateFormat("dd/MM/yy");
        df.setLenient(false);
        try{
            df.parse(date);
        } catch (ParseException ex) {
            JOptionPane.showMessageDialog(null, "Please enter a valid date format");
            return false;
        }
        return true;
    }
}
