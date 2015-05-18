/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bd;

/**
 *
 * @author jon
 */

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import uml.municipio;
import uml.provincia;


public class municipioBD extends genericoBD{
    
    
    private static Statement sentencia;
    private static PreparedStatement sentenciaCon;
    private static String plantilla;
    private static ResultSet resultado;
    private static municipio muni;
    

    public static ArrayList<municipio> ListaMunicipio;
    
    public static ArrayList<municipio> ListMunc (provincia p){
          try{
              
       ListaMunicipio = new ArrayList();
       genericoBD.setCon();
        
       plantilla = "SELECT nombre FROM municipio WHERE idProv = (SELECT idProv FROM Provincia WHERE nombre = ? )";
       
       sentenciaCon = genericoBD.getCon().prepareStatement(plantilla);
       sentenciaCon.setString(1, p.getNombre());
       resultado = sentenciaCon.executeQuery();
       
       while(resultado.next()){
           muni = new municipio();
           muni.setNombre(resultado.getString(1));
           ListaMunicipio.add(muni);
       }
       
       return ListaMunicipio;
       
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