/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * 
 * @author Ruben
 * @author Mikel
 * @author Jon
 * @version Beta 1.0
 * @since Early May
 * 
 */
public class genericoBD {
    
    private static String nombreBaseDatos ="proyecto";
    
    private static String usuario="SCOTT";
    private static String password="polo";
    
    private static Connection con;
    
    
    public static Connection getCon(){
        return con;
    }
    public static void setCon() {
        try{
            
       
            
        //Polo                             //driver@machineName:port:SID ,  userid,  password
        DriverManager.registerDriver (new oracle.jdbc.driver.OracleDriver());
        con = DriverManager.getConnection("jdbc:oracle:thin:@192.168.56.2:1521:polo",usuario,password);
                                              
         //Jon 
        //DriverManager.registerDriver (new oracle.jdbc.driver.OracleDriver());        
        //con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","jon","Jm12345");
        
        //Mikel
        //DriverManager.registerDriver (new oracle.jdbc.driver.OracleDriver());
        //con = DriverManager.getConnection("jdbc:oracle:thin:@172.16.153.133:1521:Mikel",usuario,"0907");


         if (con!=null){
            System.out.println("Conección a base de datos OK");
         }
     
      }catch(Exception e){
         System.out.println(e);
      }
    }
    
    public static void desconectar(){
       try{
         con.close();
      }
      catch(SQLException e){
         System.out.println(e);
      }catch(Exception e){
         System.out.println(e);
      }
        
    }
    
}
