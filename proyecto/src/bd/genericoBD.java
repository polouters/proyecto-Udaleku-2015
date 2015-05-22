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
    
    private static String usuario="daw12";
    private static String password="daw12";
    
    private static Connection con;
    
    
    public static Connection getCon(){
        return con;
    }
    public static void setCon() {
        try{
                 
        //Conexion -- driver@machineName:port:SID ,  userid,  password
        DriverManager.registerDriver (new oracle.jdbc.driver.OracleDriver());
        con = DriverManager.getConnection("jdbc:oracle:thin:@server224:1521:orcl",usuario,password);

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
