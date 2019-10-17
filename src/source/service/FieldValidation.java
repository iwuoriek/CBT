/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package source.service;

/**
 *
 * @author KELECHI IHEDI
 */
public class FieldValidation {
    
    public boolean isEmptyField(String... fields){
        boolean fieldIsEmpty = false;
        for (String field : fields) {
            if (field.equals("") || field.isEmpty()) {
                fieldIsEmpty = true;
                break;
            }
        }
        return fieldIsEmpty;
    }
}
