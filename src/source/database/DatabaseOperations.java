/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package source.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Vector;
import javax.swing.DefaultListModel;
import source.model.Administrator;
import source.model.Question;
import source.model.Student;

/**
 *
 * @author KELECHI
 */
public class DatabaseOperations {

    private Statement statement;
    private PreparedStatement pStatement;
    private ResultSet result;

    private Connection getConnection() throws ClassNotFoundException, SQLException {
        String url = "jdbc:mysql://localhost:3306/cbt";
        String user = "root";
        String password = "password";
        Class.forName("com.mysql.jdbc.Driver");
        Connection db = DriverManager.getConnection(url, user, password);
        return db;
    }

    public Administrator getAdminDetails(String userId) {
        Administrator adminObj = null;
        try (Connection db = getConnection()) {
            pStatement = db.prepareStatement("SELECT * FROM Administrator WHERE admin_id=?");
            pStatement.setString(1, userId);
            result = pStatement.executeQuery();
            while (result.next()) {
                String id = result.getString("admin_id");
                String name = result.getString("admin_name");
                String pwd = result.getString("admin_password");
                adminObj = new Administrator(id, name, pwd);
            }
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println("Error: " + e);
        }
        return adminObj;
    }

    public void registerStudent(Student student) {
        try {
            try (Connection db = getConnection()) {
                pStatement = db.prepareStatement("INSERT INTO Students(username,firstname,surname,date_of_birth,gender,nationality,password,passport_url) VALUES(?,?,?,?,?,?,?,?)");
                pStatement.setString(1, student.getID());
                pStatement.setString(2, student.getFirstname());
                pStatement.setString(3, student.getSurname());
                pStatement.setString(4, student.getDatefBirth());
                pStatement.setString(5, student.getGender());
                pStatement.setString(6, student.getNationality());
                pStatement.setString(7, student.getPassword());
                pStatement.setString(8, student.getPassportUrl());
                pStatement.executeUpdate();
            }
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println("Error: " + e);
        }

    }

    public void updateStudent(Student student) {
        try {
            try (Connection db = getConnection()) {
                pStatement = db.prepareStatement("UPDATE Students SET firstname=?,surname=?,date_of_birth=?,gender=?,nationality=?,password=?,passport_url=? WHERE username=?");
                pStatement.setString(1, student.getFirstname());
                pStatement.setString(2, student.getSurname());
                pStatement.setString(3, student.getDatefBirth());
                pStatement.setString(4, student.getGender());
                pStatement.setString(5, student.getNationality());
                pStatement.setString(6, student.getPassword());
                pStatement.setString(7, student.getPassportUrl());
                pStatement.setString(8, student.getID());
                pStatement.executeUpdate();
            }
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println("Error: " + e);
        }
    }

    public Vector getAllUserNames() {
        Vector<String> usernames = new Vector();
        try (Connection db = getConnection()) {
            statement = db.createStatement();
            result = statement.executeQuery("SELECT username FROM Students");
            while (result.next()) {
                String userId = result.getString("username");
                usernames.add(userId);
            }
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println("Error: " + e);
        }
        return usernames;
    }

    public Student getStudentDetails(String username) {
        Student student = null;
        try (Connection db = getConnection()) {
            pStatement = db.prepareStatement("SELECT * FROM Students WHERE username=?");
            pStatement.setString(1, username);
            result = pStatement.executeQuery();
            while (result.next()) {
                username = result.getString("username");
                String firstname = result.getString("firstname");
                String surname = result.getString("surname");
                String gender = result.getString("gender");
                String dateOfBirth = result.getString("date_of_birth");
                String nationality = result.getString("nationality");
                String password = result.getString("password");
                String url = result.getString("passport_url");
                student = new Student(username, firstname, surname, dateOfBirth, gender, nationality, password, url);
            }
        } catch (SQLException | ClassNotFoundException e) {
            System.err.println("Error: " + e);
        }
        return student;
    }

    public DefaultListModel getAllStudents() throws ClassNotFoundException, SQLException {
        DefaultListModel<Student> list;
        try (Connection db = getConnection()) {
            statement = db.createStatement();
            result = statement.executeQuery("SELECT * FROM Students");
            list = new DefaultListModel<>();
            while (result.next()) {
                String username = result.getString("username");
                String firstname = result.getString("firstname");
                String surname = result.getString("surname");
                String dateOfBirth = result.getString("date_of_birth");
                String gender = result.getString("gender");
                String nationality = result.getString("nationality");
                String pword = result.getString("password");
                String url = result.getString("passport_url");
                Double score = result.getDouble("test_score");
                Student student = new Student(username, firstname, surname, dateOfBirth, gender, nationality, pword, url, score);
                list.addElement(student);
            }
        }
        return list;
    }
    
    public void updateStudentScore(String username, float score){
        try(Connection db = getConnection()){
            pStatement = db.prepareStatement("UPDATE Students SET test_score=? WHERE username=?");
            pStatement.setFloat(1, score);
            pStatement.setString(2, username);
            pStatement.executeUpdate();
        }catch(ClassNotFoundException | SQLException e) {
            System.err.println("Error: " + e);
        }
    }
    
    public void deleteStudent(String userId) {
        try (Connection db = getConnection()) {
            pStatement = db.prepareStatement("DELETE FROM Students WHERE username=?");
            pStatement.setString(1, userId);
            pStatement.executeUpdate();
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println("Error: " + e);
        }
    }

    public void setQuestions(Question question) {
        try (Connection db = getConnection()) {
            pStatement = db.prepareStatement("INSERT INTO Questions VALUES (?,?,?,?,?,?,?)");
            pStatement.setInt(1, question.getNumber());
            pStatement.setString(2, question.getQuestion());
            pStatement.setString(3, question.getA());
            pStatement.setString(4, question.getB());
            pStatement.setString(5, question.getC());
            pStatement.setString(6, question.getD());
            pStatement.setString(7, question.getAnswer());
            pStatement.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            System.err.println("Error: " + e);
        }
    }

    public void changeQuestion(Question question) {
        try (Connection db = getConnection()) {
            pStatement = db.prepareStatement("UPDATE Questions SET question=?,option_a=?,option_b=?,option_c=?,option_d=?,answer=? WHERE number=?");
            pStatement.setString(1, question.getQuestion());
            pStatement.setString(2, question.getA());
            pStatement.setString(3, question.getB());
            pStatement.setString(4, question.getC());
            pStatement.setString(5, question.getD());
            pStatement.setString(6, question.getAnswer());
            pStatement.setInt(7, question.getNumber());
            pStatement.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            System.err.println("Error: " + e);
        }
    }

    public DefaultListModel viewQuestions() throws SQLException, ClassNotFoundException {
        DefaultListModel<Question> listModel = new DefaultListModel();
        try (Connection db = getConnection()) {
            statement = db.createStatement();
            result = statement.executeQuery("SELECT * FROM Questions");
            while (result.next()) {
                int number = result.getInt("number");
                String question = result.getString("question");
                String optionA = result.getString("option_a");
                String optionB = result.getString("option_b");
                String optionC = result.getString("option_c");
                String optionD = result.getString("option_d");
                String answer = result.getString("answer");
                Question questionObj = new Question(number, question, optionA, optionB, optionC, optionD, answer);
                listModel.addElement(questionObj);
            }
        }
        return listModel;
    }

    public Question getQuestion(int number) {
        Question questionObj = null;
        try (Connection db = getConnection()) {
            pStatement = db.prepareStatement("SELECT * FROM Questions WHERE number=?");
            pStatement.setInt(1, number);
            result = pStatement.executeQuery();
            while (result.next()) {
                String question = result.getString("question");
                String a = result.getString("option_a");
                String b = result.getString("option_b");
                String c = result.getString("option_c");
                String d = result.getString("option_d");
                String answer = result.getString("answer");
                questionObj = new Question(question, a, b, c, d, answer);
            }
        } catch (SQLException | ClassNotFoundException e) {
            System.err.println("Error: " + e);
        }
        return questionObj;
    }

    public Vector getAllNumbers() {
        Vector<Integer> numbers = new Vector();
        try (Connection db = getConnection()) {
            statement = db.createStatement();
            result = statement.executeQuery("SELECT number FROM Questions");
            while (result.next()) {
                int number = result.getInt("number");
                numbers.add(number);
            }
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println("Error: " + e);
        }
        return numbers;
    }

    public HashMap getAllQuestions() {
        HashMap<Integer, Question> questions = new HashMap();
        try (Connection db = getConnection()) {
            statement = db.createStatement();
            result = statement.executeQuery("SELECT * FROM Questions");
            while (result.next()) {
                int number = result.getInt("number");
                String question = result.getString("question");
                String a = result.getString("option_a");
                String b = result.getString("option_b");
                String c = result.getString("option_c");
                String d = result.getString("option_d");
                String answer = result.getString("answer");
                Question questionObj = new Question(question, a, b, c, d, answer);
                questions.put(number, questionObj);
            }
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println("Error: " + e);
        }
        return questions;
    }

    public void deleteQuestion(int number) {
        try (Connection db = getConnection()) {
            pStatement = db.prepareStatement("DELETE FROM Questions WHERE number=?");
            pStatement.setInt(1, number);
            pStatement.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            System.err.println("Error: " + e);
        }
    }

}
