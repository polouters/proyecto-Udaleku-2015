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
 * @author Ruben
 */
public class genericoBD {
    
    private static String nombreBaseDatos ="proyecto";
    private static String url="jdbc:mysql://localhost:3306/"+nombreBaseDatos;
    private static String usuario="SCOTT";
    private static String password="polo";
    
    private static Connection con;
    
    
    public static Connection getCon(){
        return con;
    }
    public static void setCon() {
        try{
            
        /* //obtenemos el driver de para mysql
         Class.forName("com.mysql.jdbc.Driver");
         //obtenemos la conexión
         con = DriverManager.getConnection(url,usuario,password);
         */

            
          //riverManager.registerDriver (new oracle.jdbc.driver.OracleDriver());
          //con = DriverManager.getConnection("jdbc:oracle:thin:@192.168.56.2:5560:polo",usuario,password);
         DriverManager.registerDriver (new oracle.jdbc.driver.OracleDriver());
         con = DriverManager.getConnection("jdbc:oracle:thin:@192.168.56.2:1521:polo",usuario,password);
                                               // driver@machineName:port:SID ,  userid,  password
          
         DriverManager.registerDriver (new oracle.jdbc.driver.OracleDriver());
          con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","jon","Jm12345");
        
 
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
