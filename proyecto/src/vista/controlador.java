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
    
   public static principal vp = new principal();
   public static acceso va = new acceso();
   
   public static void abrirPrincipal(){
       
   vp.setVisible(true);
   
   }
   public static void principalAcceso(){
   vp.dispose();
   va.setVisible(true);
   
   }
   
}
