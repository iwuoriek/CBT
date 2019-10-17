/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package source.service;

import java.util.HashMap;
import java.util.Random ;

/**
 *
 * @author KELECHI
 */
public class RandomizeQuestions {
    private final HashMap<Integer, Integer> questionNumbers = new HashMap();
    
    public void orderQuestionNumbers(int numberOfQuestions) {
        
        int[] numberArray = new int[numberOfQuestions];
        int key = 1;
        while (numberArray[numberOfQuestions-1] == 0) {
            int value = new Random().nextInt(numberOfQuestions+1);
            boolean numberExists = checkNumberExists(numberArray, value);
            if (!numberExists && value > 0) {
                questionNumbers.put(key, value);
                numberArray[key-1] = value;
                key++;
            }
        }
    }

    public boolean checkNumberExists(int[] numbers, int value) {
        boolean numberExists = false;
        for (int i = 0; i < numbers.length; i++) {
            if (value == numbers[i]) {
                numberExists = true;
                break;
            }
        }
        return numberExists;
    }
    
    public HashMap<Integer, Integer> getQuestionNumbers(){
        return questionNumbers;
    }
}
