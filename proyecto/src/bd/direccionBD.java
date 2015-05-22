/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bd;

import controlador.Main;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import uml.direccion;

/**
 *
 * @author jon
 */
public class direccionBD extends genericoBD{
    
    private static Statement sentencia;
    private static PreparedStatement sentenciaCon;
    private static String plantilla;
    private static ResultSet resultado;
    
    
    
public static int AñadirDireccion(direccion drc,int idVivienda){
    
 try{
    genericoBD.setCon();
    
    plantilla = "INSERT INTO direccion(idvivienda,idcalle,cp) VALUES(?,?,?)";
    
    sentenciaCon = genericoBD.getCon().prepareStatement(plantilla);
    
    int idCalle = Main.BuscarCalle(drc.getCalle());
    sentenciaCon.setInt(1, idVivienda);
    sentenciaCon.setInt(2, idCalle);
    sentenciaCon.setString(3,drc.getCp());
    
    sentenciaCon.executeUpdate();
    
    System.out.println("Procedimiento insert de Direccion realizada");
    
    return idCalle;
    
     
 }
 catch (SQLException e){
     System.out.println("Problemas con la insert de direccion");
     return 0;
 }
 catch (Exception e){
     System.out.println("Problemas en direccion" + e.getMessage());
     return 0;
 }
    
    
    
}    
    
}
