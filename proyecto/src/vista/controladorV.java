/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import controlador.Main;

/**
 *
 * @author Ruben
 */

public class controladorV {
    
   private static principal vp;
   private static acceso va;
   private static inscripcion vi; 
   private static usuario vu;
   private static administrador vad;
   private static proteccionDatos vpd;
   
   
   public static void abrirPrincipal(){
   vp = new principal();    
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
       vp = new principal();
       vp.setVisible(true);
   }
   public static void usuarioPrincipal(){
       vu.dispose();
       vp = new principal();
       vp.setVisible(true);
   }
   public static void accesoUsuario(){
       va.dispose();
       vu = new usuario();
       vu.setVisible(true);
   }
   public static void accesoAdmin(){
       va.dispose();
       vad = new administrador();
       vad.setVisible(true);       
   }
   public static void adminPrincipal(){
       vad.dispose();
       vp = new principal();
       vp.setVisible(true);
   }
   public static boolean usuarioExiste(){
   return Main.usuarioExiste();
   }
   public static void inscripcionProtecionDatos(){
       vi.setVisible(false);
       vpd = new proteccionDatos();
       vpd.setVisible(true);
   }
   public static void ProtecionDatosinscripcion(){
       vpd.dispose();
       vi.setVisible(true);
   }
}
