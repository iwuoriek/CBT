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
public class Administrator {
    private String userId, name, password;
    
    public Administrator(String userId, String password){
        this.userId = userId;
        this.password = password;
    }
    
    public Administrator(String userId, String name, String password){
        this.userId = userId;
        this.name = name;
        this.password = password;
    }
    
    public String getId(){
        return this.userId;
    }
    
    public String getName(){
        return this.name;
    }
    
    public String getPassword(){
        return this.password;
    }
}
