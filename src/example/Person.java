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
public class Person {
    
    protected String Name , address , contact;
    
    public Person()
    {
        Name = address = contact=null;
    }
    public Person( String l , String A , String C)
    {
        Name = l;
        this.address = A;
        this.contact = C;
    }
   
}
