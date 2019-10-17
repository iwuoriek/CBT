/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package source.model;

/**
 *
 * @author KELECHI
 */
public class Question {

    String question, a, b, c, d, answer;
    int number;

    public Question(int number) {
        this.number = number;
    }

    public Question(int number, String answer) {
        this.number = number;
        this.answer = answer;
    }

    public Question(int number, Question question) {
        this.number = number;
        this.question = question.getQuestion();
        this.a = question.getA();
        this.b = question.getB();
        this.c = question.getC();
        this.d = question.getD();
        this.answer = question.getAnswer();
    }

    public Question(String question, String a, String b, String c, String d, String answer) {
        this.question = question;
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
        this.answer = answer;
    }

    public Question(int number, String question, String a, String b, String c, String d, String answer) {
        this.number = number;
        this.question = question;
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
        this.answer = answer;
    }

    public int getNumber() {
        return number;
    }

    public String getQuestion() {
        return question;
    }

    public String getA() {
        return a;
    }

    public String getB() {
        return b;
    }

    public String getC() {
        return c;
    }

    public String getD() {
        return d;
    }

    public String getAnswer() {
        return answer;
    }

    @Override
    public String toString() {
        return Integer.toString(number) + ". " + question;
    }
}
