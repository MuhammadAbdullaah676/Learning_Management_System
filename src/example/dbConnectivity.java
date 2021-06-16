package example;
import java.util.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
/**
 *
 * @author Farwa
 */
public class dbConnectivity 
{
    public static dbConnectivity singleobj=null;
    Connection con;
    Statement stmt;
    public static dbConnectivity getInstance()
    {
        if(singleobj==null)
            singleobj=new dbConnectivity();
        return singleobj;
    }
    private dbConnectivity() //cons
    {
        
       
        try
        {
            
             String s = "jdbc:sqlserver://DESKTOP-72UHPAD:1433;databaseName=LMS";
             con=DriverManager.getConnection(s,"M.Abdullah","164156");


             stmt = con.createStatement(); 
        }
        catch(Exception e)
        {
            System.out.println("---------------------=="+e);
        }
    }
   
    void insertStudent(String name , String roll , String add , String con , String pass)
    {
        try
        {
            stmt.executeQuery("insert into student(rollNo,Name, address, contact ,password) values('"+roll+"','"+name+"','"+add+"','"+con+"','"+pass+"')");
          
            
        }
        catch(Exception e)
        {
            System.out.println("66666Insert SSSSSSSSSS"+e);
        }
    }
    void insertTeacher(String name , String roll , String add , String con , String pass)
    {
        try
        {
            stmt.executeQuery("insert into teacher(teachId,name, address, contact ,password) values('"+roll+"','"+name+"','"+add+"','"+con+"','"+pass+"')");
          
            
        }
        catch(Exception e)
        {
            System.out.println("66666 TTTTTTTTTTTTTTT"+e);
        }
    }
    void insertAdmin(String name , String roll , String add , String con , String pass)
    {
        try
        {
            stmt.executeQuery("insert into Admin(adminId,name, address, contact ,password) values('"+roll+"','"+name+"','"+add+"','"+con+"','"+pass+"')");
          
            
        }
        catch(Exception e)
        {
            System.out.println("66666 AAAAAAAAAAAAAAAAAA"+e);
        }
    }
    boolean checker(String roll , String pass)
      {
         
           int yeah =0;
        
        try {
            String query ="SELECT * FROM student ";
             
              ResultSet rs=stmt.executeQuery(query);
              while(rs.next())
              {
                  System.out.println(roll);
                  String val1 = rs.getString(1).replaceAll("\\s+", "");
                  String val2 = rs.getString(5).replaceAll("\\s+", "");
                if (val1.equals(roll) && val2.equals(pass))
                {       

                        
                        yeah =1;
                        return true;


                }
        }
        } catch (Exception e) {
             System.out.println("+++++++++++++++++++++++++++"+e);
        }
               if(yeah ==1) return true;
               else return false;
      }
    boolean checker1(String roll , String pass)
      {
         
           int yeah =0;
        
        try {
            String query ="SELECT * FROM admin ";
             
              ResultSet rs=stmt.executeQuery(query);
              while(rs.next())
              {
                  System.out.println(roll);
                  String val1 = rs.getString(1).replaceAll("\\s+", "");
                  String val2 = rs.getString(5).replaceAll("\\s+", "");
                if (val1.equals(roll) && val2.equals(pass))
                {       
                        yeah =1;
                        return true;


                }
        }
        } catch (Exception e) {
             System.out.println("+++++++++++++++++++++++++++"+e);
        }
               if(yeah ==1) return true;
               else return false;
      }
    boolean checker2(String roll , String pass)
      {
         
           int yeah =0;
        
        try {
            String query ="SELECT * FROM teacher ";
             
              ResultSet rs=stmt.executeQuery(query);
              while(rs.next())
              {
                  System.out.println(roll);
                  String val1 = rs.getString(1).replaceAll("\\s+", "");
                  String val2 = rs.getString(5).replaceAll("\\s+", "");
                if (val1.equals(roll) && val2.equals(pass))
                {       

                        
                        yeah =1;
                        return true;


                }
        }
        } catch (Exception e) {
             System.out.println("+++++++++++++++++++++++++++"+e);
        }
               if(yeah ==1) return true;
               else return false;
      }
    boolean registerCourse(String Cid , String Croll,String sec)
    {
        int yes = 0;  
       
         try
        {
           ResultSet rs = stmt.executeQuery("select * from Courses ");
          // ResultSet rss = stmt.executeQuery("select * from courseSection");
           while(rs.next())
           {
               if(Cid.equals(rs.getString("Cid")))
               {
                   yes = 1; 
               }
           }
            if(yes == 1)
            {
                stmt.executeUpdate("insert into registeredCourses(Cid,Sid,sec) values('"+Cid+"','"+Croll+"','"+sec+"')");
                reduceseats(Cid,sec);
                
                
                
                return true;
            }
            else
            {
               JOptionPane.showMessageDialog(null, "Your Desired Course is Not offered", "Course Not offered", 2);
               return false;
            }
    }
        catch(Exception e)
        {
            System.out.println("66660000006kjkgfvbhnjk"+e);
            JOptionPane.showMessageDialog(null, "this course is already Registered ", "COurse Registration Staus", 2);
                
                      return false;
        }
       
       
    }
     boolean registerTeacherCourse(String Cid , String tid,String sec)
    {
        int yes = 0;  
       
         try
        {
           ResultSet rs = stmt.executeQuery("select * from Courses ");
          // ResultSet rss = stmt.executeQuery("select * from courseSection");
           while(rs.next())
           {
               if(Cid.equals(rs.getString("Cid")))
               {
                   yes = 1; 
               }
           }
            if(yes == 1)
            {
                stmt.executeUpdate("insert into teacherRegisteredCourse(cid,tid,sec) values('"+Cid+"','"+tid+"','"+sec+"')");
                //reduceseats(Cid,sec);
                
                     return true;
            }
            else
            {
               JOptionPane.showMessageDialog(null, "Your Desired Course is Not offered", "Course Not offered", 2);
               return false;
            }
    }
        catch(Exception e)
        {
            System.out.println("66660000006kjkgfvbhnjk"+e);
            JOptionPane.showMessageDialog(null, "this course is already Registered ", "COurse Registration Staus", 2);
                
                      return false;
        }
       
       
    }

    public boolean reduceseats(String Cid,String sec) throws SQLException
    {

         ResultSet rss = stmt.executeQuery("select * from courseSection");
                          
        try {
           

             int seats =0 ;
           while(rss.next()){
                if(Cid.equals(rss.getString("Cid"))&&sec.equals(rss.getString("secname")))
                {
                    seats=rss.getInt("seats");

                }
                
           }
           seats=seats-1;
           stmt.executeUpdate("update courseSection set seats='"+seats+"' where Cid='"+Cid+"' and secname='"+sec+"'" );
                          System.out.println("66660000006pppppppppppppp2222222222kjkgfvbhnjk");   
           return true;
        }
           catch (SQLException ex) {
            System.out.println("66660000006ppppppppppppppkjkgfvbhnjk"+ex);   
            return false;
        }
        
    }
    public boolean increaseseats(String Cid,String sec) throws SQLException
    {

         ResultSet rss = stmt.executeQuery("select * from courseSection");
                          
        try {
           

             int seats =0 ;
           while(rss.next()){
                if(Cid.equals(rss.getString("Cid"))&&sec.equals(rss.getString("secname")))
                {
                    seats=rss.getInt("seats");

                }
                
           }
           seats=seats+1;
           stmt.executeUpdate("update courseSection set seats='"+seats+"' where Cid='"+Cid+"' and secname='"+sec+"'" );
                          System.out.println("66660000006pppppppppppppp2222222222kjkgfvbhnjk");   
           return true;
        }
           catch (SQLException ex) {
            System.out.println("66660000006ppppppppppppppkjkgfvbhnjk"+ex);   
            return false;
        }
        
    }
    boolean dropCourse(String Cid , String Croll,String sec)
   {
       int yes = 0;  
         try
        {
           ResultSet rs = stmt.executeQuery("select * from registeredCourses ");
           
           while(rs.next())
           {
               if(Cid.equals(rs.getString(1)) && Croll.equals(rs.getString(2)))
               {
                   yes = 1; 
               }
           }
            if(yes == 1)
            {
                stmt.executeUpdate("Delete from registeredCourses where Cid ='"+Cid+"'and Sid ='"+Croll+"'");
                increaseseats(Cid,sec);
                return true;
            }
            else
            {
               JOptionPane.showMessageDialog(null, "Your Desired Course is Not offered", "Course Not offered", 2);
               return false;
            }
        }
        catch(Exception e)
        {
            System.out.println("66666kjkgfvbhnjk"+e);
            return false;
        }
         
   }
     void openRegistrartion()
    {
        try
        {
            stmt.executeUpdate("Update Status SET registActive = 1 where registActive =0");
          
        }
        catch(Exception e)
        {
            System.out.println("66666Insert SSSSSSSSSS"+e);
        }
    }
     void closeRegistrartion()
    {
        try
        {
            stmt.executeUpdate("Update Status SET registActive = 0 where registActive =1");
          
        }
        catch(Exception e)
        {
            System.out.println("66666close SSSSSSSSSS"+e);
        }
    }
     int getRegist()
     {
          try
        {
            int i=0;
           ResultSet rs =stmt.executeQuery("Select * from Status");
            while(rs.next())
                i= rs.getInt(1);
           return i;
        }
        catch(Exception e)
        {
            System.out.println("66666get SSSSSSSSSS"+e);
            return -1;
        }
     }
     void setWithDraw(String  deadline)
     {
         try
        {
            stmt.executeUpdate("Update Status SET withdraw ='"+ deadline+"'where yeah =5");
          
        }
        catch(Exception e)
        {
            System.out.println("66666Insert SSSSSSSSSS"+e);
        }
     }
     String getWithdraw()
     {
            try
        {
            String i = null;
           ResultSet rs =stmt.executeQuery("Select * from Status where yeah ='5'");
            while(rs.next())
            {
                i = rs.getString(2);
            }
            System.out.println(rs.getString(2));
           return i;
        }
        catch(Exception e)
        {
            System.out.println("66666get SSSSSSSSSS"+e);
            return "not returned";
        }
     }
    public ResultSet getCourses()
    {
         try
        {
           ResultSet rs = stmt.executeQuery("select * from Courses");
           return rs;
        }
        catch(Exception e)
        {
            System.out.println("66666Insert SSSSSSSSSS"+e);
            return null;
            
        }
    }
    public ArrayList<String> getsecname(String Cid)
    {
         try
        {
           String SectionQuery="select secname from courseSection where Cid='"+Cid+"' ";
           ResultSet rs = stmt.executeQuery(SectionQuery);
           ArrayList <String> seclist=new ArrayList();
           while(rs.next())
           {
               seclist.add(rs.getString("secname"));
           }
           return seclist;
        }
        catch(Exception e)
        {
            System.out.println("66666Insert SSSSSSSSSS"+e);
            return null;
            
        }
       
    }
     public int getSeats(String Cid,String sec)
     {
         try{
              String SectionQuery="select seats from courseSection where Cid='"+Cid+"'and secname='"+sec+"'";
              ResultSet rs = stmt.executeQuery(SectionQuery);
              int seats=0;
               while(rs.next())
               {
                  seats=rs.getInt("seats");
               }
               return seats;
         }
         catch(Exception e)
         {
              System.out.println("Section invalid or invalid input"+e);
              return -1;
         }
       
     }

    ResultSet getSecAndSeats(String courseid) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}