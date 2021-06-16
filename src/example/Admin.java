/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package example;

/**
 *
 * @author hp
 */
public class Admin extends Person {
    public int openRegistration;
    public String withdrawDeadline;
    
    public String adminId;
    public Admin(){
        openRegistration =0;
        
    }
    public void openRegistration(int i){
        dbConnectivity db = dbConnectivity.getInstance();
       db.openRegistrartion();
        
    }
     public void closeRegistration(int i){
       dbConnectivity db = dbConnectivity.getInstance();
       db.closeRegistrartion();
    }
    public String setDeadline(String date){
        withdrawDeadline = date;
        return withdrawDeadline;
    }
}
