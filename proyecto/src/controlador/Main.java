/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
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
    public static boolean usuarioExiste(){
    
        return true;
    }
    public static void AbrirAchivoHTML(String Archivo){
          try {

            File objetofile = new File (Archivo);
            Desktop.getDesktop().open(objetofile);

     }catch (IOException ex) {

            System.out.println(ex);
     }
          
    }       
}
    
