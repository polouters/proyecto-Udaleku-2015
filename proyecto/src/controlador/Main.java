/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;



import JPA.SolicitudJpaController;
import bd.*;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.JOptionPane;
import lib.pdf;
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
    
    public static void realizarSorteo() throws Exception{
        SorteoBD.RealizarSorteo();

    }
    public static  void lladadaBaja() throws Exception{
     short numS = Short.parseShort(JOptionPane.showInputDialog("Teclee el nSorteo que desea elinimar:"));
     EntityManagerFactory emf = Persistence.createEntityManagerFactory("proyectoPU");
     SolicitudJpaController sjc = new SolicitudJpaController(emf);
     sjc.destroy(numS);
     System.out.println("Eliniado correctamente");
    
    } 
    
    public static  void busquedaPDF() throws Exception{
     
     EntityManagerFactory emf = Persistence.createEntityManagerFactory("proyectoPU");
     SolicitudJpaController sjc = new SolicitudJpaController(emf);
     solicitud[] array = sjc.findSolicitudEntities().toArray(new solicitud[]{});
    String texto = "Listado de Solicitudes:\n NumeroSolcitud/dniTutor/nombres menores/Orden sorteo";
     for(int x=0;x<array.length;x++){
         int y=1;
       texto = texto + array[x].getnSolicitud() + " / "+ array[x].getlInsc().get(y).getTutor().getDni();
        
    }
     System.out.println("Busqueda realizada");
    
    } 
    
    public static void pdfC(String texto){
    pdf p = new pdf();
    p.crearPDF(texto);
    System.out.println("pdf generado");
    }
    
}
    
