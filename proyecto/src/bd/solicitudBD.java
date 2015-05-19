/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bd;

import java.sql.CallableStatement;
import java.sql.Connection;

import java.sql.ResultSet;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import java.util.Date;
import oracle.jdbc.OracleTypes;
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
        try{
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
            
           
            cs.registerOutParameter(3,OracleTypes.CURSOR);
            // Ejecutamos
            cs.execute();

            
            rset = (ResultSet)cs.getObject(3);
          
            solicitud s = new solicitud();
            ArrayList<inscripcion> lInsc = new ArrayList();
            
            
            while(rset.next()){
             menor m = new menor();
             inscripcion Insc = new inscripcion();
              //nSolicitud
              s.setnSolicitud(rset.getInt(1));
              
              //Situacion
              s.setSituacion(rset.getString(2));
              
              //Inscripciones
                m.setNombre(rset.getString(3));
                m.setApe1(rset.getString(4));
                m.setApe2(rset.getString(5));
                m.setFechaNac(rset.getDate(6));
                
                Insc.setMenor(m);
                lInsc.add(Insc);
               //Orden
                s.setOrden(rset.getInt(7));
                s.setFecha(rset.getDate(8));
            }
                s.setlInsc(lInsc);
               
                solicitudes.add(s);
              
            
           
           
    }
    catch(Exception e){
        System.out.println("Problemas" + e);
    }
    return solicitudes;
    }
}
