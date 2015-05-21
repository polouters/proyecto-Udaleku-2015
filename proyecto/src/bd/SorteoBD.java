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
 * @author Mikel Ereño
 */
public class SorteoBD {
    
   private static Connection conn;
   
    public static void RealizarSorteo() throws Exception{
        genericoBD.setCon();
        conn = genericoBD.getCon();
        
        String plantilla = "{call paquete.realizarSorteo}";
        
        CallableStatement cs = conn.prepareCall(plantilla);
        
        cs.execute();
        
        genericoBD.desconectar();
    }
}
