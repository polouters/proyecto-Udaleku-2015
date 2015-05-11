/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import bd.*;
import uml.*;
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
      solicitud s1 =  solicitudBD.consultaAcceso(jDni,fNacimiento);
      
        return true;
    }
}
    
