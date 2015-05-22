/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bd;

import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
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
    private static Connection conn;
    private static ResultSet rset;
    
    public static ArrayList<solicitud> consultaAcceso(String jDni, String fNacimiento) throws Exception{
        ArrayList<solicitud> solicitudes = new ArrayList();
          genericoBD.setCon();
          conn= genericoBD.getCon();
          
            System.out.println("Base de datos abierta");
        
            String sql ="{call paquete.consultaSolicitud(?,?,?)}";
         
            CallableStatement cs = conn.prepareCall(sql);
            
            cs.setString(1,jDni);
            
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            Date de = sdf.parse(fNacimiento); 
            java.sql.Date sqlDate = new java.sql.Date(de.getTime());          
            cs.setDate(2,sqlDate);
            
           
            cs.registerOutParameter(3,OracleTypes.CURSOR);
            // Ejecutamos
            cs.execute();

            
            rset = (ResultSet)cs.getObject(3);
          
            solicitud s = new solicitud();
            ArrayList<inscripcion> lInsc = new ArrayList();
            
            int y =0;
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
               //fecha
                s.setFecha(rset.getDate(8));
                //El if es para cuando el cursor devuelve mas de una solicitud
                if(y==3){
                    //Añadir las Añadir las inscripciones a la solicititud
                    s.setlInsc(lInsc);
                    //Añadir la solicittud a la arrayList de solicitudes
                    solicitudes.add(s);
                    y = 0;
                }
                y = y +1;
            }
            //Si solo devuelve una solicitud entra en el if
            if(y<=3){
                //Añadir las Añadir las inscripciones a la solicititud
                s.setlInsc(lInsc);
                //Añadir la solicittud a la arrayList de solicitudes
                solicitudes.add(s);
            }
            genericoBD.desconectar();
    return solicitudes;
    } 
    public static int AñadirSolicitud(){
      try{
          
        genericoBD.setCon();
        
        String sql = "{call paquete.InsertarSolicitud(?)}";
        CallableStatement cs = genericoBD.getCon().prepareCall(sql); 
        
        //Este procedimiento nos devuelve el id de la solicitud.
        cs.registerOutParameter(1,OracleTypes.NUMBER);
        
        cs.execute();
        
        BigDecimal bd = cs.getBigDecimal(1);
        
        int NSolicitud = bd.intValue();
        
            System.out.println("Procedimiento insert de Solicitud ejecutado");

            genericoBD.desconectar();
            
            return NSolicitud;
        
      }
      catch(SQLException e){
          
         System.out.println("Problemas en la insert de la solicitud \n " + e.getMessage());
         return 0;
      }
      catch (Exception e){
          System.out.println("Problemas en solicitud \n " + e.getMessage());
         return 0;
      }
        
        
    }
}
