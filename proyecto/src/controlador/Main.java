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

   private static int NSolicitud;
    
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
        
        //Variables donde guardo los id para poder realizar los diferentes Insert (Son las claves primarias).
        int idVivienda;
        int idCalle;
        int CodMenor;
        
        
        for(x = 0;x < sol.getlInsc().size();x++){
            
            //Añadimos el tutor
            tutorBD.AñadirTutor(sol.getlInsc().get(x).getTutor());
            //Añadimos la vivienda. Y nos quedamos su ID
            idVivienda = viviendaBD.AñadirVivienda(sol.getlInsc().get(x).getMenor().getDrc().getVivienda());
            //Añadimos la direccion. Y nos quedamos con el ID de la calle.
            idCalle = direccionBD.AñadirDireccion(sol.getlInsc().get(x).getMenor().getDrc(),idVivienda);
            //Añadimos al menor
            CodMenor = menorBD.AñadirMenor(sol.getlInsc().get(x).getMenor(),idVivienda,idCalle);
            if(x==0){
            //Añadimos la solicitud.
            NSolicitud = solicitudBD.AñadirSolicitud();
            }
            //Si no es una nueva solicitud me quedo con el id hasta terminar.
            
            //Añadimos la inscripción a la base de datos.
            inscripcionBD.AñadirInscripcion(sol.getlInsc().get(x),CodMenor,NSolicitud);
            
            
            
        }
    }
    public static int BuscarCalle(calle c){
        
        return CalleBD.QueryByNombre(c);
    }
    public static int BuscarCentro(centro c){
        
        return centroBD.QueryByNombre(c);
    }
    
    public static void realizarSorteo() throws Exception{
        SorteoBD.RealizarSorteo();

    }
    public static  void lladadaBaja() throws Exception{
     short numS = Short.parseShort(JOptionPane.showInputDialog("Teclee el nSorteo que desea elinimar:"));
    // JPA.SolicitudJpaController.destroy(numS);
    
    } 
    
}
    
