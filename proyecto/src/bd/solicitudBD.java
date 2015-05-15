/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bd;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import oracle.jdbc.OracleTypes;
import uml.*;

/**
 *
 * @author Ruben
 */
public class solicitudBD {
    public static Connection conn;
    public static ResultSet rset;
    public static ArrayList<solicitud> consultaAcceso(String jDni, String fNacimiento){
       try
       {
          genericoBD.setCon();
          conn= genericoBD.getCon();
          
          System.out.println("Base de datos abierta");
        
            String sql ="{call paquete.consultaSolicitud(?,?)}";
            CallableStatement cs = conn.prepareCall(sql);
            cs.setString(1,jDni);
            cs.setString(2,fNacimiento);
            // Ejecutamos
            cs.execute();


            rset = (ResultSet)cs.getObject(1);
            
            while(rset.next()){
                System.out.println(rset.getInt(1) + rset.getString(2)+ rset.getInt(3)+rset.getInt(4)+ rset.getString(5)+rset.getString(6) 
                 +rset.getString(7)+rset.getDate(8).toString());
            }
            
           
            System.out.println("Procedimiento update");
    }
    catch(Exception e){
        System.out.println("Problemas");
    }
    return null;
    }
}
