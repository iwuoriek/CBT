/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sourcepackage;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

/**
 *
 * @author KELECHI
 */
public class SetQuestion extends JPanel implements java.awt.event.ActionListener{
    private JTextField question, a, b, c, d, answer;
    private JLabel qLabel, aLabel, bLabel, cLabel, dLabel, ansLabel;
    private JPanel panel1, panel2, panel3, panel4, panel5, panel6, panel7;
    private JButton save, cancel;
    
    public SetQuestion(){
        addComponents();
    }
    
    @Override
    public void actionPerformed(java.awt.event.ActionEvent event) {
        if (event.getSource() == save) {
            getQuestionFields();
        } else if (event.getSource() == cancel){
            question.setText("");
            a.setText("");
            b.setText("");
            c.setText("");
            d.setText("");
            answer.setText("");
        }
    }
    
    public void getQuestionFields() {
        boolean answerInOptions = false;
        String quest = question.getText();
        String aOpt = a.getText();
        String bOpt = b.getText();
        String cOpt = c.getText();
        String dOpt = d.getText();
        String ans = answer.getText();
        String options[] = {aOpt, bOpt, cOpt, dOpt};
        for(String option : options){
            if (option.equals(ans)){
                answerInOptions = true;
                break;
            }
        }
        if(answerInOptions){
            validateFields(quest, aOpt, bOpt, cOpt, dOpt, ans);
        } else {
            JOptionPane.showMessageDialog(this, "Options do not hold answer", "Exception", JOptionPane.WARNING_MESSAGE);
        }
    }
    
    public void validateFields(String... fields) {
        boolean fieldIsEmpty = new FieldValidation().isEmptyField(fields);
        if (fieldIsEmpty) {
            JOptionPane.showMessageDialog(null, "Input fields cannot be empty", "Exception", JOptionPane.WARNING_MESSAGE);
        } else {
            Question questions = new Question(fields[0], fields[1], fields[2], fields[3], fields[4], fields[5]);
            new GenerateIdAndRegister().setNumber(questions);
            question.setText("");
            a.setText("");
            b.setText("");
            c.setText("");
            d.setText("");
            answer.setText(""); 
        }
    }
    
    private void addComponents(){
        qLabel = new JLabel("Enter Question:");
        aLabel = new JLabel("Option A:");
        bLabel = new JLabel("Option B:");
        cLabel = new JLabel("Option C:");
        dLabel = new JLabel("Option D:");
        ansLabel = new JLabel("Answer:");
        
        JLabel[] labels = {qLabel, aLabel, bLabel, cLabel, dLabel, ansLabel};
        for(JLabel label : labels){
            label.setFont(new Font("Courier New", Font.BOLD, 17));
        }
        question = new JTextField(60);
        a = new JTextField(20);
        b = new JTextField(20);
        c = new JTextField(20);
        d = new JTextField(20);
        answer = new JTextField(20);
        JTextField[] fields = {question, a, b, c, d, answer};
        for(JTextField field : fields){
            field.setFont(new Font("Courier New", Font.PLAIN, 17));
        }
        save = new JButton("Save Entry");
        save.setBorderPainted(false);
        save.setFont(new Font("Courier New", Font.BOLD, 17));
        save.setOpaque(true);
        save.setBackground(new Color(91,155,213));
        save.setForeground(Color.DARK_GRAY);
        save.addActionListener(this);
        
        cancel = new JButton("Clear Entry");
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
        JPanel[] panels = {panel1, panel2, panel3, panel4, panel5, panel6, panel7};
        for(JPanel panel : panels){
            panel.setLayout(new java.awt.FlowLayout(FlowLayout.LEFT));
            panel.setBackground(Color.WHITE);
            add(panel);
        }
        panel1.add(qLabel);
        panel1.add(question);
        panel2.add(aLabel);
        panel2.add(a);
        panel3.add(bLabel);
        panel3.add(b);
        panel4.add(cLabel);
        panel4.add(c);
        panel5.add(dLabel);
        panel5.add(d);
        panel6.add(ansLabel);
        panel6.add(answer);
        panel7.add(save);
        panel7.add(cancel);
        Border border = BorderFactory.createBevelBorder(BevelBorder.RAISED, Color.GRAY, Color.DARK_GRAY);
        Font font = new Font("Courier New", Font.BOLD, 25);
        setBorder(BorderFactory.createTitledBorder(border, "Set Question", TitledBorder.CENTER, TitledBorder.TOP, font, Color.DARK_GRAY));
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        setBackground(Color.WHITE);
        setSize(600, 500);
    }
}
