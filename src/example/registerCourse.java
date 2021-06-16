/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package example;

/**
 *
 * @author abdul
 */
public class registerCourse {
 dbConnectivity db;
 registerCourse()
 {
     db=dbConnectivity.getInstance();
 }
 boolean isOpenReg()
 {
     int open=db.getRegist();
     if(open==1)
         return true;
     else
         return false;
 }
 boolean isSeat(String cid,String sec)
 {
     try
     {
         int seats=db.getSeats(cid, sec);
         if(seats>0)
             return true;
         else
             return false;
     }
     catch(Exception e){
         System.out.println("Section invalid or invalid input"+e);
         return false;
     }
 }
     public boolean AddCourse(String cid,String sid,String sec)
     {
         if(db.registerCourse(cid, sid, sec))
             return true;
         return false;
     }
      public boolean dropCourse(String cid,String sid,String sec)
     {
         if(db.dropCourse(cid, sid, sec))
             return true;
         return false;
     }
      public boolean addTeacherCourse(String cid,String tid,String sec)
     {
         if(db.registerTeacherCourse(cid, tid, sec))
             return true;
         return false;
     }
     
 
}
