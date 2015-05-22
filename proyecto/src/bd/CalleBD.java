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
import uml.calle;
import uml.municipio;

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
public class CalleBD extends genericoBD{
    
    
    private static Statement sentencia;
    private static PreparedStatement sentenciaCon;
    private static String plantilla;
    private static ResultSet resultado;

    public static ArrayList<calle> ListaCalle;
    public static calle c;
    
    public static ArrayList<calle> ListCalle (municipio muni){
          try{
              
       ListaCalle = new ArrayList();
       genericoBD.setCon();
        
       plantilla = "SELECT nombre FROM Calle WHERE idMunicipio = (SELECT idMunicipio FROM Municipio WHERE nombre = ? )";
       
       sentenciaCon = genericoBD.getCon().prepareStatement(plantilla);
       sentenciaCon.setString(1, muni.getNombre());
       resultado = sentenciaCon.executeQuery();
       
       while(resultado.next()){
           c = new calle();
           c.setNombre(resultado.getString(1));
           ListaCalle.add(c);
           
       }
       genericoBD.desconectar();
       return ListaCalle;
       
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
    public static int QueryByNombre(calle c){
      try{
          genericoBD.setCon();
        
        plantilla = "SELECT idcalle FROM calle WHERE nombre = ?";
        sentenciaCon = genericoBD.getCon().prepareStatement(plantilla);
        sentenciaCon.setString(1,c.getNombre());
        
        resultado = sentenciaCon.executeQuery();
        
        if(resultado.next()){
            int idcalle = resultado.getInt(1);
            genericoBD.desconectar();
            return idcalle;
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