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
   private static acceso va;
   private static inscripcion vi; 
   private static usuario vu;
   
   
   public static void abrirPrincipal(){
       
   vp.setVisible(true);
   
   }
   public static void principalAcceso(){
   vp.dispose();
   va = new acceso();
   va.setVisible(true);
   
   }
   public static void accesoInscripcion(){
       va.dispose();
       vi = new inscripcion();
       vi.setVisible(true);
   }
   public static void inscripcionPrincipal(){
       vi.dispose();
       vp.setVisible(true);
   }
   public static void usuarioPrincipal(){
       vu.dispose();
       vp.setVisible(true);
   }
   public static void accesoUsuario(){
       va.dispose();
       vu = new usuario();
       vu.setVisible(true);
   }
}
