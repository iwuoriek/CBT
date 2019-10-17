/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package source.model;

/**
 *
 * @author KELECHI
 */
public class Student {
    private String id, firstname, surname, dateOfBirth, gender, nationality, password, passportUrl;
    private double testScore;
    public Student(String studentID){
        id = studentID;
    }
    public Student(String studentID, String password){
        this.id = studentID;
        this.password = password;
    }
    public Student(String id, Student student){
        this.id = id;
        this.firstname = student.getFirstname();
        this.surname = student.getSurname();
        this.dateOfBirth = student.getDatefBirth();
        this.gender = student.getGender();
        this.nationality = student.getNationality();
        this.password = student.getPassword();
        this.passportUrl = student.getPassportUrl();
    }
    public Student(String fName, String sName, String dob, String gender, String nationality, String password, String url){
        firstname = fName;
        surname = sName;
        dateOfBirth = dob;
        this.gender = gender;
        this.nationality = nationality;
        this.password = password;
        passportUrl = url;
    }
    public Student(String sID, String fName, String sName, String dob, String gender, String nationality, String password, String url){
        id = sID;
        firstname = fName;
        surname = sName;
        dateOfBirth = dob;
        this.gender = gender;
        this.nationality = nationality;
        this.password = password;
        passportUrl = url;
    }
    public Student(String sID, String fName, String sName, String dob, String gender, String nationality, String password, String url, double score){
        id = sID;
        firstname = fName;
        surname = sName;
        dateOfBirth = dob;
        this.gender = gender;
        this.nationality = nationality;
        this.password = password;
        passportUrl = url;
        testScore = score;
    }
    public void setTestScore(double testScore){
        this.testScore = testScore;
    }
    public String getID(){
        return id;   
    }
    public String getFirstname(){
        return firstname;   
    }
    public String getSurname(){
        return surname;   
    }
    public String getDatefBirth(){
        return dateOfBirth;   
    }
    public String getGender(){
        return gender;   
    }
    public String getNationality(){
        return nationality;   
    }
    public String getPassword(){
        return password;   
    }
    public String getPassportUrl(){
        return passportUrl;
    }
    public double getScore(){
        return testScore;   
    }
    
    @Override
    public String toString(){
        return getFirstname()+" "+getSurname();
    }
}
