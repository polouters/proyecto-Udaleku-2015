/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;



import bd.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import uml.*;
import vista.*;

/**
 *
 * 
 * @author Ruben
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    
    public static void main(String[] args) {
        // TODO code application logic here
        
        //Apertura de ventana
        controladorV.abrirPrincipal();
    }
    public static void usuarioExiste(String jDni, String fNacimiento){
     try{
      ArrayList<solicitud> s1 =  solicitudBD.consultaAcceso(jDni,fNacimiento);
        String psc = fNacimiento;
      controladorV.infoSolcitud(s1,psc);
     }catch(NullPointerException Ex){JOptionPane.showMessageDialog(null,"Error en el acceso.");
     }catch(Exception Ex){JOptionPane.showMessageDialog(null,"Error: " +Ex.getMessage());}
    }   
    
    
    
    public static ArrayList<municipio> CBMunicipio(provincia p){
        
        ArrayList<municipio> ListaMunicipio = municipioBD.ListMunc(p);
    
        return ListaMunicipio;
    }
    
    public static ArrayList<calle> CBMCalle(municipio muni){
        
        ArrayList<calle> ListaCalle = CalleBD.ListCalle(muni);
    
        return ListaCalle;
    }
    public static ArrayList<centro> CBMCentros(String centro){
        
        
        return centroBD.ListaCentro(centro);
    }
    public static void AñadirSolicitud (solicitud sol){
        
        int x;
        
        for(x = 0;x < sol.getlInsc().size();x++){
            
            //Añadimos el tutor
            tutorBD.AñadirTutor(sol.getlInsc().get(x).getTutor());
            //Añadimos la vivienda
            viviendaBD.AñadirVivienda(sol.getlInsc().get(x).getMenor().getDrc().getVivienda());
            
            
            
            
        }
    }
    
    
}
    
