/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bd;

import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import oracle.jdbc.OracleTypes;
import uml.vivienda;

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
public class viviendaBD extends genericoBD{
    
    private static Statement sentencia;
    private static PreparedStatement sentenciaCon;
    private static String plantilla;
    private static ResultSet resultado;
    
    
    
    public static int AñadirVivienda(vivienda v){
        
        try{
            genericoBD.setCon();
            
            String sql ="{call paquete.InsertVivienda(?,?,?,?,?)}";
            CallableStatement cs = genericoBD.getCon().prepareCall(sql);
               
            // Cargamos los parametros de entrada IN
            cs.setString(1,v.getNumero());
            cs.setString(2,v.getLetra());
            cs.setString(3,v.getPiso());
            cs.setString(4,v.getMano());
            
             //El procedimiento nos devuelve un valor
            cs.registerOutParameter(5, OracleTypes.NUMBER);
            
            // Ejecutamos
            cs.execute();
            
            BigDecimal bd = cs.getBigDecimal(5);
            
            int ID = bd.intValue();
            System.out.println("Procedimiento insert de Vivienda ejecutado");

            genericoBD.desconectar();
            
            return ID;
        }
        catch(SQLException e){
            System.out.println("Error en la insert de la vivienda");
            return 0;
        }
        catch(Exception e){
               System.out.println("Problemas con la vivienda");
               return 0;
                }     
            
        }
        
    }
    
