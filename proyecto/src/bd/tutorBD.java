/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bd;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import uml.tutor;

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
public class tutorBD extends genericoBD{
    
    
    private static PreparedStatement sentenciaCon;
    private static String plantilla;
    private static Statement sentencia;
    private static ResultSet resultado;
    
    
    
public static void AñadirTutor(tutor t){
   
    try{
        genericoBD.setCon();
        
            plantilla = "INSERT INTO tutor (DNI,nombre,ape1,ape2,tlf1,tlf2,tlf3,tlf4) VALUES (?,?,?,?,?,?,?,?)";
            sentenciaCon = genericoBD.getCon().prepareStatement(plantilla);
            sentenciaCon.setString(1,t.getDni());
            sentenciaCon.setString(2,t.getNombre());
            sentenciaCon.setString(3,t.getApe1());
            sentenciaCon.setString(4,t.getApe2());
            sentenciaCon.setString(5,t.getTlf1());
            sentenciaCon.setString(6,t.getTlf2());
            sentenciaCon.setString(7,t.getTlf3());
            sentenciaCon.setString(8,t.getTlf4());
            sentenciaCon.executeUpdate();
            System.out.println("SE HA AÑADIDO EL TUTOR");
            
            genericoBD.desconectar();
        }
    catch(SQLException e){
        System.out.println("EL TUTOR YA EXISTE");
    }
    catch(Exception e){
        System.out.println("Problemas Tutor");
    }
    
    
}  
    
    
}
