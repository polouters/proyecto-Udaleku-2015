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
import uml.centro;
import uml.provincia;


public class centroBD extends genericoBD{
    
    private static Statement sentencia;
    private static PreparedStatement sentenciaCon;
    private static String plantilla;
    private static ResultSet resultado;
    //Centro
    
    private static ArrayList<centro> ListaCentros;
    
    
 public static ArrayList<centro> ListaCentro(String centro){
     
    
 try{
       ListaCentros = new ArrayList();
       genericoBD.setCon();
        
       if(centro.equalsIgnoreCase("Alava/Araba") == true){
            plantilla = "SELECT nombre,modelo FROM centro WHERE idProv = (SELECT idProv FROM Provincia WHERE nombre = ? )";
            sentenciaCon = genericoBD.getCon().prepareStatement(plantilla);
            sentenciaCon.setString(1,centro);
       }
       else{
           plantilla = "SELECT nombre,modelo FROM centro WHERE idProv IN (SELECT idProv FROM Provincia WHERE nombre != ? )";
           sentenciaCon = genericoBD.getCon().prepareStatement(plantilla);
           sentenciaCon.setString(1,"Alava/Araba");
       }
       
       resultado = sentenciaCon.executeQuery();
       
       while(resultado.next()){
           centro c = new centro();
           c.setNombre(resultado.getString(1));
           c.setModelo(resultado.getString(2));
           ListaCentros.add(c);
           
       }
       
       return ListaCentros;
       
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
 public static int QueryByNombre(centro c){
     
     try{
          genericoBD.setCon();
        
        plantilla = "SELECT idcentro FROM centro WHERE nombre = ?";
        sentenciaCon = genericoBD.getCon().prepareStatement(plantilla);
        sentenciaCon.setString(1,c.getNombre());
        
        resultado = sentenciaCon.executeQuery();
        
        if(resultado.next()){
            int idCentro = resultado.getInt(1);
            genericoBD.desconectar();
            return idCentro;
        }
        else{
            genericoBD.desconectar();
            return 0;
      
        }
      
      }
      catch(SQLException e){
          JOptionPane.showMessageDialog(null,"Problemas con busqueda de la calle");
          return 0;
      }
      catch (Exception e){
          JOptionPane.showMessageDialog(null,"Problemas con calle" + e.getMessage());
          return 0;
      }  
     
     
     
     
 }
}
