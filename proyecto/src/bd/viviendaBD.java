/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bd;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import uml.vivienda;

/**
 *
 * @author jon
 */
public class viviendaBD extends genericoBD{
    
    private static Statement sentencia;
    private static PreparedStatement sentenciaCon;
    private static String plantilla;
    private static ResultSet resultado;
    
    
    
    public static void AñadirVivienda(vivienda v){
        
        try{
            genericoBD.setCon();
            
            String sql ="{call paquete.insert_vivienda(?,?,?,?)}";
            CallableStatement cs = genericoBD.getCon().prepareCall(sql);
               
            // Cargamos los parametros de entrada IN
            cs.setString(1,v.getNumero());
            cs.setString(2,v.getLetra());
            cs.setString(3,v.getPiso());
            cs.setString(4,v.getMano());
            
            // Ejecutamos
            cs.execute();
            System.out.println("Procedimiento insert ejecutado");
           
            
            genericoBD.desconectar();
        } 
        catch(Exception e){
               System.out.println("Error en la insert de la vivienda");
                }
            
            
            
            
            
        }
        
    }
    
