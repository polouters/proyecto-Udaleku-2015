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
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import oracle.jdbc.OracleTypes;
import oracle.sql.TIMESTAMP;
import uml.*;

/**
 *
 * @author Ruben
 */
public class solicitudBD {
    public static Connection conn;
    public static ResultSet rset;
    public static ResultSet rset2;
    public static ArrayList<solicitud> consultaAcceso(String jDni, String fNacimiento){
       ArrayList<solicitud> solicitudes = new ArrayList();
       ArrayList<inscripcion> lins = new ArrayList();
        try
       {
          genericoBD.setCon();
          conn= genericoBD.getCon();
          
          System.out.println("Base de datos abierta");
        
            String sql ="{call paquete.consultaSolicitud(?,?,?)}";
            CallableStatement cs = conn.prepareCall(sql);
            cs.setString(1,jDni);
            SimpleDateFormat sdf = new SimpleDateFormat("dd/mm/yyyy");
            Date de = sdf.parse(fNacimiento); 
            java.sql.Date sqlDate = new java.sql.Date(de.getTime());
            
            cs.setDate(2,sqlDate);
            // Ejecutamos
            cs.registerOutParameter(3,OracleTypes.CURSOR);
         
            cs.execute();

            
            rset = (ResultSet)cs.getObject(3);
          
            solicitud s= new solicitud();
            int codigo;
            inscripcion i = new inscripcion();
            menor m = new menor();
            while(rset.next()){
                //nSolicitud
                s.setnSolicitud(rset.getInt(1));
                //Situacion
                s.setSituacion(rset.getString(2));
                //Nombre    
                m.setNombre(rset.getString(3));
                //Ape1
                m.setApe1(rset.getString(4));
                //Ape2
                m.setApe2(rset.getString(5));
                //FechaNac
                java.util.Date uDate = rset.getDate(6);                
                m.setFechaNac(uDate);
                
                i.setMenor(m);
                lins.add(i);
                //Orden
                s.setOrden(rset.getInt(7));
                //Fecha
                s.setFecha(rset.getDate(8));
                //Hora
                long hora = rset.getTimestamp(9).getTime();
                Calendar c = Calendar.getInstance();
                c.setTimeInMillis(hora);
                s.setHora(c);
     
           
              
            solicitudes.add(s); 
            }
            
            
           
           
    }
    catch(Exception e){
        System.out.println("Problemas" + e);
    }
    return solicitudes;
    }
}
