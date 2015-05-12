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
                System.out.println(rset.getString(1) + rset.getString(2));
            }
            
            // procedimento con un update       
            sql ="{call gest_depart.cambiar_localidad(?,?)}";
            cs = conn.prepareCall(sql);
               
            cs.setInt(1,50);
            cs.setString(2,"Vitria");
 
            cs.execute();
            cs.close();
            conn.close();
     
            System.out.println("Procedimiento update");
    }
    catch(Exception e){
        System.out.println("Problemas");
    }
    return null;
    }
}
