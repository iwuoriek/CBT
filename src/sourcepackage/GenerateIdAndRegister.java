/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sourcepackage;

import java.util.Vector;
import javax.swing.JOptionPane;

/**
 *
 * @author KELECHI
 */
public class GenerateIdAndRegister {

    public void createUsername(Student student) {
        char initial = student.getFirstname().charAt(0);
        String surname = student.getSurname();
        StringBuilder user = new StringBuilder(surname + initial);
        String username = new String(user).toLowerCase();
        validateUsername(username, student, 1);
    }

    public void validateUsername(String username, Student student, int counter) {

        Boolean usernameExists = false;
            Vector<String> usernames = new DatabaseOperations().getAllUserNames();
            for (String id : usernames) {
                String userId = id;
                if (userId.equals(username)) {
                    usernameExists = true;
                    break;
                }
            }
        if (!usernameExists) {
            Student studentObj = new Student(username, student);
            new DatabaseOperations().registerStudent(studentObj);
            Object object[] = {"Username: "+username, "Password: "+student.getPassword()};
            JOptionPane.showMessageDialog(null, object, "Registeration Successful", JOptionPane.INFORMATION_MESSAGE);
        } else if (counter == 1) {
            StringBuffer user = new StringBuffer(username + counter);
            String newUsername = new String(user);
            counter++;
            new GenerateIdAndRegister().validateUsername(newUsername, student, counter);
        } else if (counter > 1) {
            StringBuffer user = new StringBuffer(username);
            user.deleteCharAt(user.length() - 1);
            user.append(counter);
            String newUsername = new String(user);
            counter++;
            new GenerateIdAndRegister().validateUsername(newUsername, student, counter);
        }
    }

    public void setNumber(Question question) {
        int number = 0;
        Vector<Integer> numbers = new DatabaseOperations().getAllNumbers();
        if (!numbers.isEmpty()) {
            int lastNumber = numbers.lastElement();
            number = lastNumber + 1;
        } else {
            number = 1;
        }
        if (number > 0) {
            Question questionObj = new Question(number, question);
            new DatabaseOperations().setQuestions(questionObj);
        }
    }
}
