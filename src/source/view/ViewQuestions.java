/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package source.view;

import javax.swing.event.ListSelectionListener;
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
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.border.BevelBorder;
import source.database.DatabaseOperations;
import source.model.Question;
/**
 *
 * @author KELECHI
 */
public class ViewQuestions extends JPanel implements ListSelectionListener, ActionListener{
    private JPanel listScreen, optionScreen;
    private JButton refresh;
    private JLabel optionA, optionB, optionC, optionD, answer;
    private JList list;
    private DefaultListModel<Question> listModel;
    
    public ViewQuestions(){
        addComponents();
    }
    
    public void printList() {
        listModel = new DefaultListModel<>();
        try {
            listModel = new DatabaseOperations().viewQuestions();
            list.setModel(listModel);
        } catch (ClassNotFoundException | SQLException e) {
        }
    }
    
    private void addComponents(){
        list = new JList();
        list.setFont(new Font("Courier New", Font.BOLD, 17));       
        list.addListSelectionListener(this);
        
        refresh = new JButton("Refresh List");
        refresh.setFont(new Font("Courier New", Font.BOLD, 20));
        refresh.setBorderPainted(false);
        refresh.setOpaque(true);
        refresh.setBackground(new Color(91,155,213));
        refresh.addActionListener(this);
        
        optionA = new JLabel("");
        optionA.setFont(new Font("Courier New", Font.BOLD, 20));
        optionB = new JLabel("");
        optionB.setFont(new Font("Courier New", Font.BOLD, 20));
        optionC = new JLabel("");
        optionC.setFont(new Font("Courier New", Font.BOLD, 20));
        optionD = new JLabel("");
        optionD.setFont(new Font("Courier New", Font.BOLD, 20));
        answer = new JLabel("");
        answer.setFont(new Font("Courier New", Font.BOLD, 20));
        
        listScreen = new JPanel();
        listScreen.setBackground(Color.WHITE);
        listScreen.add(list);
        
        optionScreen = new JPanel();
        optionScreen.setBackground(Color.WHITE);
        optionScreen.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED, Color.GRAY, Color.DARK_GRAY));
        optionScreen.setLayout(new BoxLayout(optionScreen, BoxLayout.PAGE_AXIS));
        optionScreen.add(optionA);
        optionScreen.add(optionB);
        optionScreen.add(optionC);
        optionScreen.add(optionD);
        optionScreen.add(answer);
        
        JScrollPane listPane = new JScrollPane(listScreen);
        listPane.setPreferredSize(new Dimension(500, 450));
        listPane.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED, Color.GRAY, Color.DARK_GRAY));
        
        JScrollPane optionPane = new JScrollPane(optionScreen);
        optionPane.setPreferredSize(new Dimension(220, 450));
        optionPane.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED, Color.GRAY, Color.DARK_GRAY));
        
        setBackground(Color.WHITE);
        setLayout(new BorderLayout());
        add(listPane, BorderLayout.WEST);
        add(optionPane, BorderLayout.EAST);
        add(refresh, BorderLayout.SOUTH);
        setSize(600, 500);
    }
    @Override
    public void valueChanged(javax.swing.event.ListSelectionEvent e) {
        int index = list.getSelectedIndex();
        if (index >= 0) {
            optionA.setText("A. " + listModel.getElementAt(index).getA());
            optionB.setText("B. " + listModel.getElementAt(index).getB());
            optionC.setText("C. " + listModel.getElementAt(index).getC());
            optionD.setText("D. " + listModel.getElementAt(index).getD());
            answer.setText("Answer: " + listModel.getElementAt(index).getAnswer());
        }
    }
    
    @Override
    public void actionPerformed(java.awt.event.ActionEvent event){
        printList();
    }
}
