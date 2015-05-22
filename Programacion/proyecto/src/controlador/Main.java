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
 * @author Mikel
 * @author Jon
 * @version Beta 1.0
 * @since Early May
 * 
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
         /**
          * envia datos a solcicitud BD para acceder a la ventana usurio
          */
      ArrayList<solicitud> s1 =  solicitudBD.consultaAcceso(jDni,fNacimiento);
        String psc = fNacimiento;
      controladorV.infoSolcitud(s1,psc);
   
     }catch(NullPointerException Ex){JOptionPane.showMessageDialog(null,"Error en el acceso");
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
    
