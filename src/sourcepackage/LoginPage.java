/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sourcepackage;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;

/**
 *
 * @author KELECHI
 */
public class LoginPage extends JFrame implements java.awt.event.ActionListener {

    private JLabel titleLabel, idLabel, pwdLabel, wallpaper;
    private JTextField idField;
    private JPasswordField pwdField;
    private JRadioButton admin, student;
    private JButton loginButton;
    private JPanel panel;

    public LoginPage() {
        initializeComponents();
    }

    @Override
    public void actionPerformed(java.awt.event.ActionEvent event) {
        String username = idField.getText();
        String password = new String(pwdField.getPassword());
        LoginValidation validate = new LoginValidation();
        if (admin.isSelected()) {
            Administrator adminObj = new Administrator(username, password);
            boolean adminValid = validate.validateAdmin(adminObj);
            if (adminValid) {
                AdminPage adminPage = new AdminPage(validate.getAdminDetails());
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Invalid username or password!", "Invalid User", JOptionPane.ERROR_MESSAGE);
            }

        } else if (student.isSelected()) {
            Student studentObj = new Student(username, password);
            boolean studentIsValid = validate.validateStudent(studentObj);
            if (studentIsValid) {
                StudentPage stuPage = new StudentPage(validate.getStudentDetails());
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Invalid username or password!", "Invalid User", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void initializeComponents() {
        setSize(600, 500);
        wallpaper = new JLabel();
        ImageIcon icon = new ImageFile().getIcon("Background_2.png", getWidth(), getHeight());
        wallpaper.setIcon(icon);

        titleLabel = new JLabel("APTITUDE  TEST");
        titleLabel.setBounds(20, 20, 540, 50);
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        titleLabel.setFont(new Font("Times New Roman", Font.BOLD, 40));
        titleLabel.setForeground(new Color(91,155,213));

        idLabel = new JLabel("USER ID");
        idLabel.setBounds(100, 150, 80, 25);
        idLabel.setFont(new Font("Courier New", Font.BOLD, 17));

        pwdLabel = new JLabel("PASSWORD");
        pwdLabel.setBounds(100, 185, 80, 25);
        pwdLabel.setFont(new Font("Courier New", Font.BOLD, 17));

        idField = new JTextField();
        idField.setBounds(190, 150, 200, 25);
        idField.setFont(new Font("Courier New", Font.PLAIN, 17));

        pwdField = new JPasswordField();
        pwdField.setBounds(190, 185, 200, 25);
        pwdField.setFont(new Font("Courier New", Font.PLAIN, 17));

        panel = new JPanel(new FlowLayout());
        panel.setBackground(Color.WHITE);
        panel.setBounds(190, 220, 200, 25);

        admin = new JRadioButton("Admin");
        admin.setOpaque(true);
        admin.setBackground(Color.WHITE);
        admin.setFont(new Font("Courier New", Font.BOLD, 17));

        student = new JRadioButton("Student");
        student.setOpaque(true);
        student.setBackground(Color.WHITE);
        student.setFont(new Font("Courier New", Font.BOLD, 17));

        ButtonGroup bgroup = new ButtonGroup();
        bgroup.add(admin);
        bgroup.add(student);
        panel.add(admin);
        panel.add(student);

        loginButton = new JButton("Login");
        loginButton.setFont(new Font("Courier New", Font.BOLD, 17));
        loginButton.setBounds(190, 260, 150, 25);
        loginButton.setOpaque(true);
        loginButton.setBorderPainted(false);
        loginButton.setBackground(new Color(91,155,213));
        loginButton.setForeground(Color.DARK_GRAY);
        loginButton.addActionListener(this);

        setBackground(java.awt.Color.WHITE);
        setLayout(new BorderLayout());
        wallpaper.add(titleLabel);
        wallpaper.add(idLabel);
        wallpaper.add(idField);
        wallpaper.add(pwdField);
        wallpaper.add(pwdLabel);
        wallpaper.add(panel);
        wallpaper.add(loginButton);
        add(wallpaper);
        
        Image image = new ImageFile().getImage("Title.png");
        this.setIconImage(image);
        setVisible(true);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        LoginPage lp = new LoginPage();

    }
}
