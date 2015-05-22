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
    /**
     * envia datos a solcicitud BD para acceder a la ventana usurio
     * @param jDni
     * @param fNacimiento 
     */
    public static void usuarioExiste(String jDni, String fNacimiento){
     try{
         
      ArrayList<solicitud> s1 =  solicitudBD.consultaAcceso(jDni,fNacimiento);
        String psc = fNacimiento;
      controladorV.infoSolcitud(s1,psc);
   
     }catch(NullPointerException Ex){JOptionPane.showMessageDialog(null,"Error en el acceso");
     }catch(Exception Ex){JOptionPane.showMessageDialog(null,"Error: " +Ex.getMessage());}
    }   
    
    
    /**
     * 
     * @param p
     * @return 
     */
    public static ArrayList<municipio> CBMunicipio(provincia p){
        
        ArrayList<municipio> ListaMunicipio = municipioBD.ListMunc(p);
    
        return ListaMunicipio;
    }
    /**
     * 
     * @param muni
     * @return 
     */
    public static ArrayList<calle> CBMCalle(municipio muni){
        
        ArrayList<calle> ListaCalle = CalleBD.ListCalle(muni);
    
        return ListaCalle;
    }
    /**
     * 
     * @param centro
     * @return 
     */
    public static ArrayList<centro> CBMCentros(String centro){
        
        
        return centroBD.ListaCentro(centro);
    }/**
     * Añade una solicitud
     * @param sol 
     */
    public static void AñadirSolicitud (solicitud sol){
        /**
         * PARA EL TEMA DE LA INSCRIPCION
         */
        int x;
        
        /**
         * Variables donde guardo los id para poder realizar los diferentes Insert (Son las claves primarias).
         */
        int idVivienda;
        int idCalle;
        int CodMenor;
        
        
        for(x = 0;x < sol.getlInsc().size();x++){
            
            /**
             * Añadimos el tutor
             */ 
            tutorBD.AñadirTutor(sol.getlInsc().get(x).getTutor());
            /**
             * Añadimos la vivienda. Y nos quedamos su ID
            */ 
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
            
            /**
             * Añadimos la inscripción a la base de datos.
             */ 
            inscripcionBD.AñadirInscripcion(sol.getlInsc().get(x),CodMenor,NSolicitud);
            
            
            
        }
    }
    /**
     * para buscar calle
     * @param c
     * @return 
     */
    public static int BuscarCalle(calle c){
        
        return CalleBD.QueryByNombre(c);
    }
    /**
     * para buscar centro
     * @param c
     * @return 
     */
    public static int BuscarCentro(centro c){
        
        return centroBD.QueryByNombre(c);
    }
    
    public static void realizarSorteo() throws Exception{
        SorteoBD.RealizarSorteo();

    }
    /**
     * Da de baja una solcitud
     * @throws Exception 
     */
    public static  void lladadaBaja() throws Exception{
        /**
      * DAR DE BAJA
      */
     short numS = Short.parseShort(JOptionPane.showInputDialog("Teclee el nSorteo que desea elinimar:"));
     EntityManagerFactory emf = Persistence.createEntityManagerFactory("proyectoPU");
     SolicitudJpaController sjc = new SolicitudJpaController(emf);
     sjc.destroy(numS);
     System.out.println("Eliniado correctamente");
    
    } 
    /**
     * para generar el pdf
     * @throws Exception 
     */
    public static  void busquedaPDF() throws Exception{
     /**
      * PARA EL PDF
      */
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
    /**
     * mas tema del pdf
     * @param texto 
     */
    public static void pdfC(String texto){
        /**
      * PARA EL PDF
      */
    pdf p = new pdf();
    p.crearPDF(texto);
    System.out.println("pdf generado");
    }
    
}
    
