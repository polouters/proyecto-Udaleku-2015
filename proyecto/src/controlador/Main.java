/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

<<<<<<< HEAD
import java.text.SimpleDateFormat;
=======
import bd.*;
import java.util.ArrayList;
import uml.*;
>>>>>>> 1cfde457e9805a75a42df78627bef8ae6b59b805
import vista.*;

/**
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
    public static boolean usuarioExiste(String jDni, String fNacimiento){
      ArrayList<solicitud> s1 =  solicitudBD.consultaAcceso(jDni,fNacimiento);
    if(  s1.get(0).getSituacion().toString().isEmpty())
        return false;
    else{
        controladorV.infoSolcitud(s1);
        return true;
    }
<<<<<<< HEAD
    public static void crearObjetos(){
        //SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        //sdf.
=======
>>>>>>> 1cfde457e9805a75a42df78627bef8ae6b59b805
    }
}
    
