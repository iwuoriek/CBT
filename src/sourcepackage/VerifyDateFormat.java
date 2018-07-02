/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sourcepackage;

import javax.swing.JOptionPane;

/**
 *
 * @author KELECHI
 */
public class VerifyDateFormat {

     public String getDateFormat(String date) {
        String[] splitDate = date.split("-");
        String correctDate = "";
        try {
            if (splitDate.length == 3) {
                int day = Integer.parseInt(splitDate[0]);
                int month = Integer.parseInt(splitDate[1]);
                int year = Integer.parseInt(splitDate[2]);
                if (day <= 31 && month <= 12 && Integer.toString(year).length() == 4) {
                    correctDate = date;
                } else {
                    JOptionPane.showMessageDialog(null, date + " is an incorrect date format", "Date Format Warning", JOptionPane.WARNING_MESSAGE);
                    correctDate = "";
                }
            } else {
                correctDate = "";
            }
        } catch (NumberFormatException e) {
            System.out.println(e);
            JOptionPane.showMessageDialog(null, date + " is not a valid date input", "Date Input Warning", JOptionPane.WARNING_MESSAGE);
            correctDate = "";
        }
        return correctDate;
    }
    
}
