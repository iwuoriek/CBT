/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package source.view;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import source.model.Student;
import source.service.FieldValidation;
import source.service.GenerateIdAndRegister;
import source.service.ImageFile;
import source.service.VerifyDateFormat;

/**
 *
 * @author KELECHI
 */
public class RegisterStudent extends JPanel implements java.awt.event.ActionListener {

    private JTextField fName, sName, pword, dateOB;
    private JLabel firstName, surname, password, date, nation, image;
    private JRadioButton male, female;
    private JComboBox nationality;
    private JButton save, cancel, uploadImage;
    private JPanel panel1, panel2, panel3, panel4, panel5, panel6, panel7, panel8, dummy;
    private ButtonGroup bgroup;
    private JFileChooser passportFile;
    private final ImageFile passport = new ImageFile();

    public RegisterStudent() {
        addComponents();
    }

    @Override
    public void actionPerformed(java.awt.event.ActionEvent event) {
        if (event.getSource() == save) {
            registerStudent();
        } else if (event.getSource() == uploadImage) {
            int option = passportFile.showOpenDialog(this);
            if (option == JFileChooser.APPROVE_OPTION) {
                passport.uploadPassport(fName.getText()+sName.getText(), passportFile.getSelectedFile());
                image.setText(passport.getFileName());
            }
        } else if (event.getSource() == cancel){
            fName.setText("");
            sName.setText("");
            dateOB.setText("");
            pword.setText("");
            nationality.setSelectedItem("Select");
            bgroup.clearSelection();
        }
    }

    public void registerStudent() {
        String first_name = fName.getText();
        String sur_name = sName.getText();
        String gender = "";
        if (male.isSelected()) {
            gender = "Male";
        }
        if (female.isSelected()) {
            gender = "Female";
        }
        String nation = (String) nationality.getSelectedItem();
        String doB = new VerifyDateFormat().getDateFormat(dateOB.getText());
        String pwd = pword.getText();
        String passportUrl = passport.getFileName();
        validateFields(first_name, sur_name, doB, gender, nation, pwd, passportUrl);
    }
    
    public void validateFields(String... fields) {
        boolean fieldIsEmpty = new FieldValidation().isEmptyField(fields);
        if (fieldIsEmpty) {
            JOptionPane.showMessageDialog(this, "Input fields cannot be empty", "Exception", JOptionPane.WARNING_MESSAGE);
        } else {
            Student student = new Student(fields[0], fields[1], fields[2], fields[3], fields[4], fields[5], fields[6]);
            new GenerateIdAndRegister().createUsername(student);
            fName.setText("");
            sName.setText("");
            dateOB.setText("");
            pword.setText("");
        }
    }

    private void addComponents() {
        firstName = new JLabel("First Name");
        surname = new JLabel("Surname");
        password = new JLabel("Password");
        date = new JLabel("Date of Birth (dd-mm-yyyy)");
        nation = new JLabel("Nationlity");
        image = new JLabel();
        JLabel[] labels = {firstName, surname, password, date, nation, image};
        for (JLabel label : labels) {
            label.setFont(new Font("Courier New", Font.BOLD, 17));
        }

        fName = new JTextField(20);
        sName = new JTextField(20);
        pword = new JTextField(20);
        dateOB = new JTextField(20);
        JTextField[] fields = {fName, sName, pword, dateOB};
        for (JTextField field : fields) {
            field.setFont(new Font("Courier New", Font.PLAIN, 17));
        }

        male = new JRadioButton("Male");
        male.setFont(new Font("Courier New", Font.BOLD, 17));
        female = new JRadioButton("Female");
        female.setFont(new Font("Courier New", Font.BOLD, 17));
        bgroup = new ButtonGroup();
        bgroup.add(male);
        bgroup.add(female);
        String[] nations = {"Select", "United States", "United Kingdom", "Nigeria", "Ghana", "Canada", "Other"};
        nationality = new JComboBox(nations);
        nationality.setFont(new Font("Courier New", Font.PLAIN, 17));

        passportFile = new JFileChooser();

        uploadImage = new JButton("Upload Passport");
        uploadImage.setBorderPainted(false);
        uploadImage.setFont(new Font("Courier New", Font.BOLD, 17));
        uploadImage.setOpaque(true);
        uploadImage.setBackground(new Color(91,155,213));
        uploadImage.setForeground(Color.DARK_GRAY);
        uploadImage.addActionListener(this);
        
        save = new JButton("Save Entry");
        save.setBorderPainted(false);
        save.setFont(new Font("Courier New", Font.BOLD, 17));
        save.setOpaque(true);
        save.setBackground(new Color(91,155,213));
        save.setForeground(Color.DARK_GRAY);
        save.addActionListener(this);
        
        cancel = new JButton("Cancel Entry");
        cancel.setBorderPainted(false);
        cancel.setFont(new Font("Courier New", Font.BOLD, 17));
        cancel.setOpaque(true);
        cancel.setBackground(new Color(91,155,213));
        cancel.setForeground(Color.DARK_GRAY);
        cancel.addActionListener(this);

        panel1 = new JPanel();
        panel2 = new JPanel();
        panel3 = new JPanel();
        panel4 = new JPanel();
        panel5 = new JPanel();
        panel6 = new JPanel();
        panel7 = new JPanel();
        panel8 = new JPanel();
        dummy = new JPanel();
        dummy.setSize(new java.awt.Dimension(100, 50));
        JPanel[] panels = {dummy, panel1, panel2, panel3, panel4, panel5, panel6, panel7, panel8};
        for (JPanel panel : panels) {
            panel.setLayout(new java.awt.FlowLayout(FlowLayout.LEADING));
            panel.setBackground(Color.WHITE);
            add(panel);
        }
        panel1.add(firstName);
        panel1.add(fName);
        panel2.add(surname);
        panel2.add(sName);
        panel3.add(male);
        panel3.add(female);
        panel4.add(nation);
        panel4.add(nationality);
        panel5.add(date);
        panel5.add(dateOB);
        panel6.add(password);
        panel6.add(pword);
        panel7.add(uploadImage);
        panel7.add(image);
        panel8.add(save);
        panel8.add(cancel);
        Border border = BorderFactory.createBevelBorder(BevelBorder.RAISED, Color.GRAY, Color.DARK_GRAY);
        Font font = new Font("Courier New", Font.BOLD, 25);
        setBorder(BorderFactory.createTitledBorder(border, "Register Student", TitledBorder.CENTER, TitledBorder.TOP, font, Color.DARK_GRAY));
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        setBackground(Color.WHITE);
        setSize(500, 400);
    }
}
