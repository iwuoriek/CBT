/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sourcepackage;

import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.border.BevelBorder;

/**
 *
 * @author KELECHI
 */
public class ViewStudent extends JPanel implements ListSelectionListener, ActionListener {

    private JPanel listScreen, selectionScreen;
    private JButton refresh;
    private JLabel username, name, gender, dateOB, nationality, password, passport, score;
    private JList list;
    private DefaultListModel<Student> listModel;
    private ImageIcon icon;

    public ViewStudent() {
        screenComponents();
    }
    
    @Override
    public void actionPerformed(java.awt.event.ActionEvent event){
        printList();
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        int index = list.getSelectedIndex();
        if (index >= 0) {
            String fileName = listModel.getElementAt(index).getPassportUrl();
            icon = new ImageFile().getPassport(fileName);
            passport.setIcon(icon);
            username.setText("Username: "+listModel.getElementAt(index).getID());
            String sname = listModel.getElementAt(index).getFirstname() + " " + listModel.getElementAt(index).getSurname();
            name.setText("Name: " + sname);
            gender.setText("Gender: " + listModel.getElementAt(index).getGender());
            dateOB.setText("Date of birth: " + listModel.getElementAt(index).getDatefBirth());
            nationality.setText("Nationality: " + listModel.getElementAt(index).getNationality());
            password.setText("Password: " + listModel.getElementAt(index).getPassword());
            score.setText("Score: "+listModel.get(index).getScore()+"%");
            
        }
    }
    
    public void printList() {
        try {
            listModel = new DatabaseOperations().getAllStudents();
            list.setModel(listModel);
        } catch (ClassNotFoundException | SQLException e) {
        }
    }
    private void screenComponents() {
        list = new JList();
        list.setFont(new Font("Courier New", Font.BOLD, 17));
        list.addListSelectionListener(this);
        
        refresh = new JButton("Refresh List");
        refresh.setFont(new Font("Courier New", Font.BOLD, 20));
        refresh.setBorderPainted(false);
        refresh.setOpaque(true);
        refresh.setBackground(new Color(91,155,213));
        refresh.addActionListener(this);
        
        passport = new JLabel();
        username = new JLabel("");
        username.setFont(new Font("Courier New", Font.BOLD, 20));
        name = new JLabel("");
        name.setFont(new Font("Courier New", Font.BOLD, 20));
        gender = new JLabel("");
        gender.setFont(new Font("Courier New", Font.BOLD, 20));
        dateOB = new JLabel("");
        dateOB.setFont(new Font("Courier New", Font.BOLD, 20));
        nationality = new JLabel("");
        nationality.setFont(new Font("Courier New", Font.BOLD, 20));
        password = new JLabel("");
        password.setFont(new Font("Courier New", Font.BOLD, 20));
        score = new JLabel("");
        score.setFont(new Font("Courier New", Font.BOLD, 20));
        
        listScreen = new JPanel();
        listScreen.setBackground(Color.WHITE);
        listScreen.add(list);
        
        selectionScreen = new JPanel();
        selectionScreen.setBackground(Color.WHITE);
        selectionScreen.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED, Color.GRAY, Color.DARK_GRAY));
        selectionScreen.setLayout(new BoxLayout(selectionScreen, BoxLayout.PAGE_AXIS));
        selectionScreen.add(passport);
        selectionScreen.add(name);
        selectionScreen.add(username);
        selectionScreen.add(gender);
        selectionScreen.add(dateOB);
        selectionScreen.add(nationality);
        selectionScreen.add(password);
        selectionScreen.add(score);
        
        JScrollPane pane = new JScrollPane(listScreen);
        pane.setPreferredSize(new Dimension(150, 450));
        pane.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED, Color.GRAY, Color.DARK_GRAY));
        setBackground(Color.WHITE);
        setLayout(new BorderLayout());
        add(selectionScreen, BorderLayout.CENTER);
        add(pane, BorderLayout.EAST);
        add(refresh, BorderLayout.SOUTH);
        setSize(600, 500);
    }    
}
