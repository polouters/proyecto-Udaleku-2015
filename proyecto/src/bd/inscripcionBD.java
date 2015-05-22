/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bd;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import uml.inscripcion;

/**
 *
 * @author jon
 */
public class inscripcionBD extends genericoBD{
    
    
    private static Statement sentencia;
    private static PreparedStatement sentenciaCon;
    private static String plantilla;
    private static ResultSet resultado;
    
    
public static void AñadirInscripcion (inscripcion ins, int CodMenor,int NSolicitud){
    try{
       genericoBD.setCon();
     
     
     String sql = "{call paquete.InsertarInscripcion(?,?,?)";
     CallableStatement cs = genericoBD.getCon().prepareCall(sql);
     cs.setInt(1, NSolicitud);
     cs.setString(2,ins.getTutor().getDni());
     cs.setInt(3, CodMenor);
     
     
     cs.execute();
     
     System.out.println("Procedimiento Insert de Inscripcion ejecutado correctamente.");
     
     genericoBD.desconectar();
     
    }
    catch(SQLException e){
        
        System.out.println("Problemas con la insert de inscripcion \n " + e.getMessage());
    }
     
     
    
 
    
    
    
 }   


}
