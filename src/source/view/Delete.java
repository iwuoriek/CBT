/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package source.view;

import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JLabel;
import source.database.DatabaseOperations;
import source.model.Student;
import source.service.ImageFile;

/**
 *
 * @author KELECHI
 */
public class Delete {
    private final JTextField idField = new JTextField();
    public void deleteStudent(){
        Object[] object = {"Enter username of student to be deleted", idField};
        int option = JOptionPane.showConfirmDialog(null, object, "Delete Student", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION){
            Student student = new DatabaseOperations().getStudentDetails(idField.getText());
            JLabel passport = new JLabel(new ImageFile().getPassport(student.getPassportUrl()));
            Object[] stu = {"Confirm Delete?", passport, student.getFirstname()+" "+student.getSurname()};
            int confirmOption = JOptionPane.showConfirmDialog(null, stu, "Confirm Option", JOptionPane.OK_CANCEL_OPTION);
            if (confirmOption == JOptionPane.OK_OPTION){
                new DatabaseOperations().deleteStudent(idField.getText());
            }
        }
    }
    public void deleteQuestion(){
        Object[] object = {"Enter question number to be delete", idField};
        int option = JOptionPane.showConfirmDialog(null, object, "Delete Question", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION){
            try{
                int number = Integer.parseInt(idField.getText());
                new DatabaseOperations().deleteQuestion(number);
            }catch(NumberFormatException e){
                JOptionPane.showMessageDialog(null, "Invalid Input type", "Delete Question", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
