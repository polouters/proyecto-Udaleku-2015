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
import java.util.ArrayList;
import javax.swing.JOptionPane;
import uml.provincia;



public class provinciaBD extends genericoBD{
    
    
    private static Statement sentencia;
    private static PreparedStatement sentenciaCon;
    private static String plantilla;
    private static ResultSet resultado;
    //Provincia
    public static provincia p;
    public static ArrayList<provincia> ListaProvincia;
    
    
    public static ArrayList<provincia> ListaProv(){
    
        try{
       ListaProvincia = new ArrayList();
       genericoBD.setCon();
        
       plantilla = "SELECT nombre FROM provincia";
       
       sentenciaCon = genericoBD.getCon().prepareStatement(plantilla);
       
       resultado = sentenciaCon.executeQuery();
       
       while(resultado.next()){
           p = new provincia();
           p.setNombre(resultado.getString(1));
           
           ListaProvincia.add(p);
           
       }
       
       return ListaProvincia;
       
    }
      catch(SQLException e){
          JOptionPane.showMessageDialog(null,"Problemas con la sentencia SELECT");
          genericoBD.desconectar();
          return null;
      }
      catch (Exception e){
          JOptionPane.showMessageDialog(null,"Problemas con la sentencia SELECT");
          return null;
      }
    
    
    
}
    
    
    
    
}
