/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package source.view;

import source.view.UpdateStudent;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Image;
import javax.swing.JTextField;
import javax.swing.JFrame;
import source.model.Administrator;
import source.service.ImageFile;
import source.service.LogEntryFile;

/**
 *
 * @author KELECHI
 */
public class AdminPage extends JFrame implements java.awt.event.ActionListener, Runnable {

    private JPanel optionScreen, titleScreen, eventScreen;
    private JLabel user;
    private JTextField userId;
    private JButton regStudent, viewStudent, updateStudent, delStudent, setQ, changeQ, delQ, viewQ, logout;
    private final String name;

    public AdminPage(Administrator admin) {
        this.name = admin.getName();
        new LogEntryFile(admin).logAdmin();
        start();
    }

    private void start() {
        Thread thread = new Thread(this, "AdminThread");
        thread.start();
    }

    @Override
    public void run() {
        initializeComponents(name);
    }

    @Override
    public void actionPerformed(java.awt.event.ActionEvent event) {
        CardLayout cl = (CardLayout) eventScreen.getLayout();
        if (event.getSource() == regStudent) {
            cl.show(eventScreen, "register");
        } else if (event.getSource() == viewStudent) {
            cl.show(eventScreen, "view");
        } else if (event.getSource() == updateStudent) {
            cl.show(eventScreen, "update");
        } else if (event.getSource() == delStudent) {
            new Delete().deleteStudent();
        } else if (event.getSource() == setQ) {
            cl.show(eventScreen, "setQ");
        } else if (event.getSource() == viewQ) {
            cl.show(eventScreen, "viewQ");
        } else if (event.getSource() == changeQ) {
            cl.show(eventScreen, "changeQ");
        } else if (event.getSource() == delQ) {
            new Delete().deleteQuestion();
        } else if (event.getSource() == logout) {
            LoginPage lp = new LoginPage();
            dispose();
        }
    }

    private void initializeComponents(String name) {
        userId = new JTextField();
        userId.setFont(new Font("Courier New", Font.PLAIN, 17));
        user = new JLabel("Current Administrator: " + name);
        user.setFont(new Font("Courier New", Font.BOLD, 20));

        regStudent = new JButton("Register Student");
        viewStudent = new JButton("View Student");
        updateStudent = new JButton("Update Student");
        delStudent = new JButton("Delete Students");
        setQ = new JButton("Set Question");
        viewQ = new JButton("View Questionnaire");
        changeQ = new JButton("Change Questions");
        delQ = new JButton("Delete Questions");
        logout = new JButton("Logout");

        titleScreen = new JPanel(new java.awt.FlowLayout(FlowLayout.CENTER));
        titleScreen.add(user);

        optionScreen = new JPanel(new java.awt.GridLayout(10, 1, 3, 5));

        JButton[] button = {regStudent, viewStudent, updateStudent, delStudent, setQ, viewQ, changeQ, delQ, logout};
        for (JButton button1 : button) {
            button1.setFont(new Font("Courier New", Font.BOLD, 17));
            button1.setOpaque(true);
            button1.setBackground(new Color(91, 155, 213));
            button1.setForeground(Color.DARK_GRAY);
            button1.setBorderPainted(false);
            button1.addActionListener(this);
            optionScreen.add(button1);
        }
        eventScreen = new JPanel(new CardLayout(30, 60));
        eventScreen.setBackground(Color.WHITE);
        eventScreen.add(new RegisterStudent(), "register");
        eventScreen.add(new ViewStudent(), "view");
        eventScreen.add(new UpdateStudent(), "update");
        eventScreen.add(new SetQuestion(), "setQ");
        eventScreen.add(new ViewQuestions(), "viewQ");
        eventScreen.add(new ChangeQuestion(), "changeQ");

        setTitle("ADMIN PAGE");
        setLayout(new java.awt.BorderLayout());
        add(titleScreen, BorderLayout.NORTH);
        add(optionScreen, BorderLayout.WEST);
        add(eventScreen, BorderLayout.CENTER);

        Image image = new ImageFile().getImage("Title.png");
        setIconImage(image);
        setSize(1000, 700);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
