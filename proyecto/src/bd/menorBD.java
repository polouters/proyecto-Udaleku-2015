/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bd;

import controlador.Main;
import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import oracle.jdbc.OracleTypes;
import uml.menor;

/**
 *
 * @author jon
 */
public class menorBD {
    
    private static Statement sentencia;
    private static PreparedStatement sentenciaCon;
    private static String plantilla;
    private static ResultSet resultado;
    
    
 public static int AñadirMenor (menor m,int idVivienda,int idCalle){
    
     try{
     
        
        
        int idCentro = Main.BuscarCentro(m.getCntr());
        genericoBD.setCon();
            
        String sql ="{call paquete.InsertarMenor(?,?,?,?,?,?,?,?,?,?,?)}";
        CallableStatement cs = genericoBD.getCon().prepareCall(sql);
        
        //Cargamos los parametros de entrada
        
        cs.setString(1,m.getNombre());
        cs.setString(2,m.getApe1());
        cs.setString(3,m.getApe2());
        cs.setString(4,m.getSexo());
        cs.setString(5,m.getDni());
        
        // Conversion java.util.Date en java.sql.Date
        java.sql.Date fecha =  new java.sql.Date(m.getFechaNac().getTime());
        cs.setDate(6,fecha);
        
        cs.setString(7,m.getDiscapacidad());
        cs.setInt(8,idCalle);
        cs.setInt(9,idVivienda);
        cs.setInt(10,idCentro);

        //Recogemos el valor que nos devuelve el procedimiento.
        cs.registerOutParameter(11, OracleTypes.NUMBER);
        
        //Lo guardamos en una variable
        
        cs.execute();
        
         BigDecimal bd = cs.getBigDecimal(11);
         
          int CodMenor = bd.intValue();
            System.out.println("Procedimiento insert ejecutado");

            genericoBD.desconectar();
            
            return CodMenor;
            
            
        
     }
     catch(SQLException e){
         System.out.println("Problemas en la insert del Menor \n" + e.getMessage());
         return 0;
     }
     catch(Exception e){
         System.out.println("Problemas en Menor" + e.getMessage());
         return 0;
     }
     
     
 }   
    
}
