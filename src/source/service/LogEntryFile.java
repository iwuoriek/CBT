/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package source.service;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import source.model.Administrator;
import source.model.Student;

/**
 *
 * @author KELECHI
 */
public class LogEntryFile {

    private Student student;
    private Administrator admin;
    private final String root = System.getProperty("user.home");

    public LogEntryFile(Administrator admin) {
        this.admin = admin;
        Path filePath = Paths.get(root+"\\Documents\\NIIT\\Log\\Admin_Log.txt");
        createLogFile(filePath);
    }

    public LogEntryFile(Student student) {
        this.student = student;
        Path filePath = Paths.get(root+"\\Documents\\NIIT\\Log\\Student_Log.txt");
        createLogFile(filePath);
    }

    private void createLogFile(Path filePath) {
        try {
            Path path = Paths.get(root + "\\Documents\\NIIT\\Log");
            if (!Files.exists(path)) {
                Files.createDirectories(path);
            }
            
            if(!Files.exists(filePath)){
                Files.createFile(filePath);
            }
        } catch (IOException e) {
            System.err.println(e);
        }
    }

    public void logAdmin() {
        try (FileWriter f = new FileWriter(root+"\\Documents\\NIIT\\Log\\Admin_Log.txt", true);
                BufferedWriter bw = new BufferedWriter(f)) {
            bw.write(admin.getName().toUpperCase() + " with id: " + admin.getId() + ", logged in on: " + new java.util.Date().toString());
            bw.newLine();
            bw.newLine();
        } catch (IOException e) {
            System.err.println(e);
        }
    }

    public void logStudent() {
        try (FileWriter f = new FileWriter(root+"\\Documents\\NIIT\\Log\\Student_Log.txt", true);
                BufferedWriter bw = new BufferedWriter(f)) {
            bw.write(student.getFirstname().toUpperCase() + " " + student.getSurname().toUpperCase()
                    + " with id: " + student.getID() + ", took the test on: " + new java.util.Date().toString());
            bw.newLine();
            bw.newLine();
        } catch (IOException e) {
            System.err.println(e);
        }
    }
}
