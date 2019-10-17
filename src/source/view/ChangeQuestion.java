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
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import source.database.DatabaseOperations;
import source.model.Question;
import source.service.FieldValidation;

/**
 *
 * @author KELECHI
 */
public class ChangeQuestion extends JPanel implements java.awt.event.ActionListener {

    private JTextField number, question, a, b, c, d, answer;
    private JLabel numLabel, qLabel, aLabel, bLabel, cLabel, dLabel, ansLabel;
    private JPanel panel1, panel2, panel3, panel4, panel5, panel6, panel7, panel8;
    private JButton save, cancel, getQuestion;

    public ChangeQuestion() {
        addComponents();
    }

    @Override
    public void actionPerformed(java.awt.event.ActionEvent event) {
        if (event.getSource() == save) {
            getQuestionFields();
        } else if (event.getSource() == getQuestion) {
            getQuestion();
        } else if (event.getSource() == cancel) {
            number.setText("");
            question.setText("");
            a.setText("");
            b.setText("");
            c.setText("");
            d.setText("");
            answer.setText("");
        }
    }

    public void getQuestion() {
        int num = Integer.parseInt(number.getText());
        Question questionObj = new DatabaseOperations().getQuestion(num);
        if (questionObj != null) {
            question.setText(questionObj.getQuestion());
            a.setText(questionObj.getA());
            b.setText(questionObj.getB());
            c.setText(questionObj.getC());
            d.setText(questionObj.getD());
            answer.setText(questionObj.getAnswer());
        } else {
            JOptionPane.showMessageDialog(this, "This question number does not exist!", "Invalid Question", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void getQuestionFields() {
        boolean answerInOptions = false;
        String num = number.getText();
        String quest = question.getText();
        String aOpt = a.getText();
        String bOpt = b.getText();
        String cOpt = c.getText();
        String dOpt = d.getText();
        String ans = answer.getText();
        String options[] = {aOpt, bOpt, cOpt, dOpt};
        for (String option : options) {
            System.out.println(option);
            if (option.equals(ans)) {
                answerInOptions = true;
                break;
            }
        }
        System.out.println("Answer: " + ans);
        if (answerInOptions) {
            validateFields(num, quest, aOpt, bOpt, cOpt, dOpt, ans);
        } else {
            JOptionPane.showMessageDialog(this, "Options do not hold answer", "Exception", JOptionPane.WARNING_MESSAGE);
        }
    }

    public void validateFields(String... fields) {
        boolean fieldIsEmpty = new FieldValidation().isEmptyField(fields);
        if (fieldIsEmpty) {
            JOptionPane.showMessageDialog(this, "Input fields cannot be empty", "Exception", JOptionPane.WARNING_MESSAGE);
        } else {
            int num = Integer.parseInt(fields[0]);
            Question questions = new Question(num, fields[1], fields[2], fields[3], fields[4], fields[5], fields[6]);
            new DatabaseOperations().changeQuestion(questions);
            number.setText("");
            question.setText("");
            a.setText("");
            b.setText("");
            c.setText("");
            d.setText("");
            answer.setText("");
        }
    }

    private void addComponents() {
        numLabel = new JLabel("Question Number:");
        qLabel = new JLabel("Question:");
        aLabel = new JLabel("Option A:");
        bLabel = new JLabel("Option B:");
        cLabel = new JLabel("Option C:");
        dLabel = new JLabel("Option D:");
        ansLabel = new JLabel("Answer:");

        JLabel[] labels = {numLabel, qLabel, aLabel, bLabel, cLabel, dLabel, ansLabel};
        for (JLabel label : labels) {
            label.setFont(new Font("Courier New", Font.BOLD, 17));
        }
        number = new JTextField(10);
        question = new JTextField(60);
        a = new JTextField(20);
        b = new JTextField(20);
        c = new JTextField(20);
        d = new JTextField(20);
        answer = new JTextField(20);
        JTextField[] fields = {number, question, a, b, c, d, answer};
        for (JTextField field : fields) {
            field.setFont(new Font("Courier New", Font.PLAIN, 17));
        }
        getQuestion = new JButton("Get Question");
        getQuestion.setBorderPainted(false);
        getQuestion.setFont(new Font("Courier New", Font.BOLD, 17));
        getQuestion.setOpaque(true);
        getQuestion.setBackground(new Color(91,155,213));
        getQuestion.setForeground(Color.DARK_GRAY);
        getQuestion.addActionListener(this);
        
        save = new JButton("Save Entry");
        save.setBorderPainted(false);
        save.setFont(new Font("Courier New", Font.BOLD, 17));
        save.setOpaque(true);
        save.setBackground(new Color(91,155,213));
        save.setForeground(Color.DARK_GRAY);
        save.addActionListener(this);
        
        cancel = new JButton("Clear Entry");
        cancel.setBorderPainted(false);
        cancel.setFont(new Font("Courier New", Font.BOLD, 17));cancel.setOpaque(true);
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
        JPanel[] panels = {panel1, panel2, panel3, panel4, panel5, panel6, panel7, panel8};
        for (JPanel panel : panels) {
            panel.setLayout(new java.awt.FlowLayout(FlowLayout.LEFT));
            panel.setBackground(Color.WHITE);
            add(panel);
        }
        panel1.add(numLabel);
        panel1.add(number);
        panel1.add(getQuestion);
        panel2.add(qLabel);
        panel2.add(question);
        panel3.add(aLabel);
        panel3.add(a);
        panel4.add(bLabel);
        panel4.add(b);
        panel5.add(cLabel);
        panel5.add(c);
        panel6.add(dLabel);
        panel6.add(d);
        panel7.add(ansLabel);
        panel7.add(answer);
        panel8.add(save);
        panel8.add(cancel);

        Border border = BorderFactory.createBevelBorder(BevelBorder.RAISED, Color.GRAY, Color.DARK_GRAY);
        Font font = new Font("Courier New", Font.BOLD, 25);
        setBorder(BorderFactory.createTitledBorder(border, "Change Question", TitledBorder.CENTER, TitledBorder.TOP, font, Color.DARK_GRAY));
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        setBackground(Color.WHITE);
        setSize(600, 500);
    }
}
