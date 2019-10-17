/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package source.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import source.model.Student;
import source.service.ImageFile;

/**
 *
 * @author KELECHI
 */
public class StudentPage extends JFrame implements ActionListener, Runnable{
    private final Student student;
    private JPanel buttonPanel, componentPanel, instructionPanel, container;
    private JLabel titleLabel, name, testdate, dateOfBirth, gender, nationality, instruction, passport;
    private JButton start, cancel;
    private ImageIcon passportIcon;
    
    public StudentPage(Student student){
        this.student = student;
        start();
    }
    
    @Override
    public void actionPerformed(ActionEvent event) {
        if (event.getSource() == start){
            TestPage test = new TestPage(student);
        } else if (event.getSource() == cancel){
            LoginPage login =  new LoginPage();
        }
        dispose();
    }
    
    private void start(){
        Thread thread = new Thread(this, "StudentThread");
        thread.start();
    }
    
    @Override
    public void run() {
        initializeComponents();
    }
    
    private void initializeComponents(){
        String fileName = student.getPassportUrl();
        passportIcon = new ImageFile().getPassport(fileName);
        passport = new JLabel();
        passport.setIcon(passportIcon);
        
        titleLabel = new JLabel("NIIT Aptitude Test");
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        titleLabel.setFont(new Font("Times New Roman", Font.BOLD, 40));
        
        name = new JLabel("Name: "+student.getFirstname()+" "+student.getSurname());
        testdate = new JLabel("Today is "+new java.util.Date().toString());
        dateOfBirth = new JLabel("Date of birth: "+student.getDatefBirth());
        gender = new JLabel("Gender: "+student.getGender());
        nationality = new JLabel("Nationality: "+student.getNationality());
        
        instruction = new JLabel("Read the INSTRUCTIONS before you begin.");
        instruction.setFont(new Font("Courier New", Font.BOLD, 25));
        
        JLabel labels[] = {name, testdate, dateOfBirth, gender, nationality};
        for (JLabel label : labels){
            label.setFont(new Font("Courier New", Font.BOLD, 20));
        }
        componentPanel = new JPanel();
        componentPanel.setLayout(new BoxLayout(componentPanel, BoxLayout.PAGE_AXIS));
        componentPanel.setBackground(new Color(91,155,213));
        componentPanel.add(passport);
        componentPanel.add(name);
        componentPanel.add(gender);
        componentPanel.add(dateOfBirth);
        componentPanel.add(nationality);
        componentPanel.add(instruction);
        
        instructionPanel = new JPanel();
        instructionPanel.setBackground(new Color(91,155,213));
        instructionPanel.setLayout(new BorderLayout());
        instructionPanel.add(instruction, BorderLayout.NORTH);
        
        
        start = new JButton("Start Test");
        start.setBorderPainted(false);
        start.setFont(new Font("Courier New", Font.BOLD, 17));
        start.setOpaque(true);
        start.setBackground(new Color(91,155,213));
        start.setForeground(Color.DARK_GRAY);
        start.addActionListener(this);
        
        cancel = new JButton("Cancel Test");
        cancel.setBorderPainted(false);
        cancel.setFont(new Font("Courier New", Font.BOLD, 17));
        cancel.setOpaque(true);
        cancel.setBackground(new Color(91,155,213));
        cancel.setForeground(Color.DARK_GRAY);
        cancel.addActionListener(this);
        buttonPanel = new JPanel(new FlowLayout(FlowLayout.TRAILING));
        buttonPanel.setBackground(Color.WHITE);
        buttonPanel.add(start);
        buttonPanel.add(cancel);
        
        container = new JPanel();
        container.setLayout(new BorderLayout(50, 50));
        container.add(titleLabel, BorderLayout.NORTH);
        container.add(componentPanel, BorderLayout.CENTER);
        container.add(instructionPanel, BorderLayout.WEST);
        container.add(buttonPanel, BorderLayout.SOUTH);
        container.setBackground(new Color(91,155,213));
        add(container);
        
        Image image = new ImageFile().getImage("Title.png");
        this.setIconImage(image);
        setSize(1000, 700);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
