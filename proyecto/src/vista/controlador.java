/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

/**
 *
 * @author Ruben
 */

public class controlador {
    
   private static principal vp = new principal();
   private static acceso va = new acceso();
   private static inscripcion vi = new inscripcion(); 
   
   public static void abrirPrincipal(){
       
   vp.setVisible(true);
   
   }
   public static void principalAcceso(){
   vp.dispose();
   va.setVisible(true);
   
   }
   public static void accesoInscripcion(){
       va.dispose();
       vi.setVisible(true);
   }
   public static void InscripcionPrincipal(){
       vi.dispose();
       vp.setVisible(true);
   }
   
}
