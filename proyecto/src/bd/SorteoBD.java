/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bd;

import java.sql.CallableStatement;
import java.sql.Connection;

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
public class SorteoBD {
    
   private static Connection conn;
/**
 * 
 * @throws Exception 
 */  
    public static void RealizarSorteo() throws Exception{
       /**
        * realiza el sorteo mediante PL/SQL
        */
 
        genericoBD.setCon();
        conn = genericoBD.getCon();
        
        String plantilla = "{call paquete.realizarSorteo}";
        
        CallableStatement cs = conn.prepareCall(plantilla);
        
        cs.execute();
        
        genericoBD.desconectar();
    }
}
