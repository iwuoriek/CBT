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
import java.awt.GridLayout;
import java.awt.Image;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.ButtonGroup;
import javax.swing.JRadioButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import javax.swing.JComboBox;

/**
 *
 * @author KELECHI
 */
public class TestPage extends JFrame implements ActionListener, Runnable {

    private final Student student;
    private JPanel timePane, questionPane, buttonPane;
    private JLabel question, timer, answeredQ;
    private JRadioButton a, b, c, d;
    private ButtonGroup bg;
    private JButton finishButton;
    private JComboBox selectQuestionNumber;
    private HashMap<Integer, Question> questions;
    private HashMap<Integer, Integer> questionNumbers;
    private HashMap<Integer, String> answers;
    private final Set numbers = new HashSet();
    private String optionAnswer = "";
    private int actualNumber = 0, qflag = 0, aflag = 0;

    public TestPage(Student student) {
        this.student = student;
        new LogEntryFile(student).logStudent();
        start();
    }
    
    private void start(){
        Thread thread = new Thread(this, "TestThread");
        thread.start();
    }
    
    @Override
    public void run() {
            initializeComponents();
            timer();     
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        if (event.getSource() == finishButton) {
            sendScore();
            dispose();
        } else if (event.getSource() == selectQuestionNumber) {
            int selectedNumber = (Integer) selectQuestionNumber.getSelectedItem();
            int actual_Number = questionNumbers.get(selectedNumber);
            actualNumber = actual_Number;
            selectQuestion(actual_Number);
        } else if (event.getSource() == a) {
            optionAnswer = a.getText();
            aflag = 1;
        } else if (event.getSource() == b) {
            optionAnswer = b.getText();
            aflag = 1;
        } else if (event.getSource() == c) {
            optionAnswer = c.getText();
            aflag = 1;
        } else if (event.getSource() == d) {
            optionAnswer = d.getText();
            aflag = 1;
        }
        optionSelected();
    }

    private void selectQuestion(int questionNumber) {
        Question q = questions.get(questionNumber);

        question.setText(q.getQuestion());
        a.setText(q.getA());
        b.setText(q.getB());
        c.setText(q.getC());
        d.setText(q.getD());
        bg.clearSelection();
        qflag = 1;
    }

    private void optionSelected() {
        if (aflag == 1 && qflag == 1) {
            answers.put(actualNumber, optionAnswer);
            numbers.add(selectQuestionNumber.getSelectedItem());
            StringBuilder answeredQuestions = new StringBuilder();
            answeredQuestions.append(numbers);
            answeredQ.setText(answeredQuestions.toString());
            qflag = 0;
            aflag = 0;
        } else if (aflag == 1 && qflag == 0) {
            answers.replace(actualNumber, optionAnswer);
            aflag = 0;
        }
    }
    
    private void sendScore(){
        float score = calculateScore();
        new DatabaseOperations().updateStudentScore(student.getID(), score);
        ScorePage sp = new ScorePage(score);
    }

    private float calculateScore() {
        int score = 0, total = questions.size();
        Object[] answeredNumbers = answers.keySet().toArray();
        for (Object answeredNumber : answeredNumbers) {
            int key = (Integer) answeredNumber;
            Question q = questions.get(key);
            if (answers.get(key).equals(q.getAnswer())) {
                score += 1;
            }
        }
        float iScore = Float.parseFloat(score+".0");
        float iTotal = Float.parseFloat(total+".0");
        float finalScore = (iScore/iTotal)*100;
        BigDecimal bd = new BigDecimal(Float.toString(finalScore));
        bd = bd.setScale(2, BigDecimal.ROUND_HALF_UP);
        return bd.floatValue();
    }
    
    private void timer(){
        for(int i=2; i>= 0; i--){
            if(i < 2){
                timer.setForeground(Color.RED);
            }
            for(int j = 60; j > 0; j--){
                timer.setText(i+":"+j);
                try{
                    Thread.sleep(1000);
                }catch(InterruptedException e){
                    System.err.println(e);
                }
            }
        }
        sendScore();
        dispose();
    }
    
    private void initializeComponents() {
        timer = new JLabel();
        timer.setFont(new Font("Courier New", Font.BOLD, 40));
        timePane = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 20));
        timePane.add(timer);

        questionPane = new JPanel();
        questionPane.setLayout(new GridLayout(4, 1));
        questionPane.setBackground(Color.WHITE);
        questionComponents(questionPane);

        finishButton = new JButton("Finish");
        finishButton.setFont(new Font("Courier New", Font.BOLD, 20));
        finishButton.setBorderPainted(false);
        finishButton.setOpaque(true);
        finishButton.setBackground(new Color(91,155,213));
        finishButton.setForeground(Color.DARK_GRAY);
        finishButton.addActionListener(this);
        buttonPane = new JPanel(new FlowLayout(FlowLayout.TRAILING, 20, 20));
        buttonPane.add(finishButton);
        
        setLayout(new BorderLayout());
        add(questionPane, BorderLayout.CENTER);
        add(buttonPane, BorderLayout.SOUTH);
        add(timePane, BorderLayout.NORTH);
        
        Image image = new ImageFile().getImage("Title.png");
        this.setIconImage(image);
        setVisible(true);
        setSize(900, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void questionComponents(JPanel questionPane) {
        questions = new DatabaseOperations().getAllQuestions();
        RandomizeQuestions rq = new RandomizeQuestions();
        rq.orderQuestionNumbers(questions.size());
        questionNumbers = rq.getQuestionNumbers();
        answers = new HashMap();

        answeredQ = new JLabel("Answered numbers are listed here.");
        answeredQ.setFont(new Font("Courier New", Font.BOLD, 25));
        answeredQ.setHorizontalAlignment(JLabel.CENTER);
        Integer[] numberList = new Integer[questions.size()];
        for (int i = 0; i < questions.size(); i++) {
            numberList[i] = i + 1;
        }
        selectQuestionNumber = new JComboBox(numberList);
        selectQuestionNumber.setFont(new Font("Courier New", Font.BOLD, 20));
        selectQuestionNumber.addActionListener(this);
        JPanel comboPanel = new JPanel();
        comboPanel.setBackground(Color.WHITE);
        comboPanel.add(selectQuestionNumber);

        question = new JLabel();
        question.setFont(new Font("Courier New", Font.BOLD, 20));
        question.setHorizontalAlignment(JLabel.CENTER);
        a = new JRadioButton();
        a.setFont(new Font("Courier New", Font.BOLD, 20));
        a.setBackground(Color.WHITE);
        a.addActionListener(this);
        b = new JRadioButton();
        b.setFont(new Font("Courier New", Font.BOLD, 20));
        b.setBackground(Color.WHITE);
        b.addActionListener(this);
        c = new JRadioButton();
        c.setFont(new Font("Courier New", Font.BOLD, 20));
        c.setBackground(Color.WHITE);
        c.addActionListener(this);
        d = new JRadioButton();
        d.setFont(new Font("Courier New", Font.BOLD, 20));
        d.setBackground(Color.WHITE);
        d.addActionListener(this);
        bg = new ButtonGroup();
        bg.add(a);
        bg.add(b);
        bg.add(c);
        bg.add(d);
        JPanel options = new JPanel();
        options.setBackground(Color.WHITE);
        options.add(a);
        options.add(b);
        options.add(c);
        options.add(d);

        questionPane.add(answeredQ);
        questionPane.add(comboPanel);
        questionPane.add(question);
        questionPane.add(options);
    }
}
