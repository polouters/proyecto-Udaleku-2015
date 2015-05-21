/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;
import uml.*;

/**
 *
 * @author jon
 */
public class usuario extends javax.swing.JFrame {

    /**
     * Creates new form usuario
     */public usuario(){
      initComponents();
      setLocationRelativeTo(null);
     }
    public usuario(ArrayList<solicitud> s1,String psc) {
        initComponents();
        setLocationRelativeTo(null);
        int pos =0;
        //Selecvionar al buscado
        for(int z=0;z<s1.size();z++){
            for(int z2=0;z2<s1.get(z).getlInsc().size();z2++){
               Date fechaNac = s1.get(z).getlInsc().get(z2).getMenor().getFechaNac();
               SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
               String fechaNacS = sdf.format(fechaNac);
               if(psc.compareTo(fechaNacS)==0){pos = z2;}
            }
        }
        //Si solo es una consulta
        if(s1.size()==1){
            //Participante
            String participante = s1.get(0).getlInsc().get(pos).getMenor().getNombre()
                    +" "+s1.get(0).getlInsc().get(pos).getMenor().getApe1()
                    +" "+s1.get(0).getlInsc().get(pos).getMenor().getApe2();
            tParticipante.setText(participante);
            //Fecha de nacimiento
            dNacimiento.setDate(s1.get(0).getlInsc().get(pos).getMenor().getFechaNac());
            //nSolicitud
            tNumero.setText(Integer.toString(s1.get(0).getnSolicitud()));
            //Situacion
            jSituacion.setText(s1.get(0).getSituacion());
            //Numero sorteo
            int orden = s1.get(0).getOrden();
            if(orden !=0){ 
                jOrden.setText(Integer.toString(orden));}
           //Fecha cita
            dCita.setDate(s1.get(0).getFecha());
            //Hora de cita
            tHora.setText(s1.get(0).getHora());
            //Otros participantes
            for(int x=0;x<s1.get(0).getlInsc().size();x++){
                switch (x){
                    case 0: if(x!=pos){
                                participante = s1.get(0).getlInsc().get(x).getMenor().getNombre()
                                        +" "+s1.get(0).getlInsc().get(x).getMenor().getApe1()
                                        +" "+s1.get(0).getlInsc().get(x).getMenor().getApe2();
                                jParticipante2.setText(participante);}
                    break;
                    case 1: if(x!=pos){
                                if(jParticipante2.getText().isEmpty()){
                                     participante = s1.get(0).getlInsc().get(x).getMenor().getNombre()
                                        +" "+s1.get(0).getlInsc().get(x).getMenor().getApe1()
                                        +" "+s1.get(0).getlInsc().get(x).getMenor().getApe2();
                                    jParticipante2.setText(participante);
                                }else{
                                    participante = s1.get(0).getlInsc().get(x).getMenor().getNombre()
                                        +" "+s1.get(0).getlInsc().get(x).getMenor().getApe1()
                                        +" "+s1.get(0).getlInsc().get(x).getMenor().getApe2();
                                    jParticipante3.setText(participante);
                                 }
                            }
                    break;
                    case 2: if(x!=pos){
                                participante = s1.get(0).getlInsc().get(x).getMenor().getNombre()
                                    +" "+s1.get(0).getlInsc().get(x).getMenor().getApe1()
                                    +" "+s1.get(0).getlInsc().get(x).getMenor().getApe2();
                                jParticipante3.setText(participante);
                            }
        //Mas de dos una solicitud
        }}}else{
            //Preguntar cual mostrar
            String texto = "Tenemos varias opciones de solicitudes: \n";
            for(int x= 0;x<s1.size();x++){
                texto = texto + (x+1) + " - "+ s1.get(x).getlInsc().get(0).getMenor().getNombre()+"\n";
            }
            int posAsk = (Integer.parseInt(JOptionPane.showInputDialog(null,texto )))-1;
            //Participante
            String participante = s1.get(posAsk).getlInsc().get(pos).getMenor().getNombre()
                +" "+s1.get(posAsk).getlInsc().get(pos).getMenor().getApe1()
                +" "+s1.get(posAsk).getlInsc().get(pos).getMenor().getApe2();
            tParticipante.setText(participante);
            //fecha nacimiento
            dNacimiento.setDate(s1.get(posAsk).getlInsc().get(pos).getMenor().getFechaNac());
            //nSolicitud
            tNumero.setText(Integer.toString(s1.get(posAsk).getnSolicitud()));
            //Situacion
            jSituacion.setText(s1.get(posAsk).getSituacion());
            int orden = s1.get(posAsk).getOrden();
            //nOrden
            if(orden !=0){ 
                jOrden.setText(Integer.toString(orden));}
            //fecha cita
            dCita.setDate(s1.get(posAsk).getFecha());
            //hora cita
            tHora.setText(s1.get(posAsk).getHora());
            //otros participantes
            for(int x=0;x<s1.get(posAsk).getlInsc().size();x++){
                switch (x){
                    case 0: if(x!=pos){
                                participante = s1.get(posAsk).getlInsc().get(x).getMenor().getNombre()
                                        +" "+s1.get(posAsk).getlInsc().get(x).getMenor().getApe1()
                                        +" "+s1.get(posAsk).getlInsc().get(x).getMenor().getApe2();
                                jParticipante2.setText(participante);}
                    break;
                    case 1: if(x!=pos){
                                if(jParticipante2.getText().isEmpty()){
                                     participante = s1.get(posAsk).getlInsc().get(x).getMenor().getNombre()
                                        +" "+s1.get(posAsk).getlInsc().get(x).getMenor().getApe1()
                                        +" "+s1.get(posAsk).getlInsc().get(x).getMenor().getApe2();
                                    jParticipante2.setText(participante);
                                }else{
                                    participante = s1.get(posAsk).getlInsc().get(x).getMenor().getNombre()
                                        +" "+s1.get(posAsk).getlInsc().get(x).getMenor().getApe1()
                                        +" "+s1.get(posAsk).getlInsc().get(x).getMenor().getApe2();
                                    jParticipante3.setText(participante);
                                 }
                            }
                    break;
                    case 2: if(x!=pos){
                                participante = s1.get(posAsk).getlInsc().get(x).getMenor().getNombre()
                                    +" "+s1.get(posAsk).getlInsc().get(x).getMenor().getApe1()
                                    +" "+s1.get(posAsk).getlInsc().get(x).getMenor().getApe2();
                                jParticipante3.setText(participante);
                            }
        }}}
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        pUdalekuak = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        tParticipante = new javax.swing.JTextField();
        tNumero = new javax.swing.JTextField();
        jSituacion = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        dNacimiento = new com.toedter.calendar.JDateChooser();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jParticipante2 = new javax.swing.JTextField();
        jParticipante3 = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jOrden = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        tHora = new javax.swing.JTextField();
        dCita = new com.toedter.calendar.JDateChooser();
        volver = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setText("Detalle situación solicitud");

        pUdalekuak.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Udalekuak", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 18), new java.awt.Color(255, 0, 51))); // NOI18N

        jLabel6.setText("Situación Inscripción");

        jLabel3.setText("Participante");

        jLabel4.setText("Número  inscripción");

        tParticipante.setEditable(false);

        tNumero.setEditable(false);

        jSituacion.setEditable(false);

        jLabel5.setText("Fecha de nacimiento");

        dNacimiento.setDateFormatString("dd/MM/yyyy");
        dNacimiento.setEnabled(false);
        dNacimiento.setIcon(null);

        javax.swing.GroupLayout pUdalekuakLayout = new javax.swing.GroupLayout(pUdalekuak);
        pUdalekuak.setLayout(pUdalekuakLayout);
        pUdalekuakLayout.setHorizontalGroup(
            pUdalekuakLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pUdalekuakLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pUdalekuakLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(jLabel3)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pUdalekuakLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pUdalekuakLayout.createSequentialGroup()
                        .addComponent(tNumero, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(dNacimiento, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pUdalekuakLayout.createSequentialGroup()
                        .addGroup(pUdalekuakLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tParticipante, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jSituacion, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        pUdalekuakLayout.setVerticalGroup(
            pUdalekuakLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pUdalekuakLayout.createSequentialGroup()
                .addGroup(pUdalekuakLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(pUdalekuakLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(tParticipante, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(dNacimiento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pUdalekuakLayout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(jLabel3)
                        .addGap(18, 18, 18)
                        .addGroup(pUdalekuakLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(tNumero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))))
                .addGap(18, 18, 18)
                .addGroup(pUdalekuakLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jSituacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Participantes en la misma inscripción", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 18), new java.awt.Color(255, 0, 51))); // NOI18N

        jLabel2.setBackground(new java.awt.Color(255, 0, 0));
        jLabel2.setForeground(new java.awt.Color(0, 51, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("PARTICIPANTE");

        jParticipante2.setEditable(false);

        jParticipante3.setEditable(false);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                        .addGap(42, 42, 42)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jParticipante2, javax.swing.GroupLayout.PREFERRED_SIZE, 471, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 461, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jParticipante3, javax.swing.GroupLayout.PREFERRED_SIZE, 471, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(jParticipante2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jParticipante3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Sorteo", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 18), new java.awt.Color(255, 0, 51))); // NOI18N

        jLabel7.setText("Número orden sorteo");

        jOrden.setEditable(false);

        jLabel8.setText("Fecha de la cita");

        jLabel9.setText("Hora de la cita");

        tHora.setEditable(false);

        dCita.setDateFormatString("dd/MM/yyyy");
        dCita.setEnabled(false);
        dCita.setIcon(null);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addGap(18, 18, 18)
                        .addComponent(jOrden, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(dCita, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(tHora, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jOrden, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel9)
                        .addComponent(tHora, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(jLabel8))
                    .addComponent(dCita, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(25, Short.MAX_VALUE))
        );

        volver.setText("Volver");
        volver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                volverActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(pUdalekuak, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(153, 153, 153))
                            .addComponent(volver))))
                .addGap(52, 52, 52))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pUdalekuak, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(volver)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void volverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_volverActionPerformed
        // TODO add your handling code here:
        controladorV.usuarioPrincipal();
        
    }//GEN-LAST:event_volverActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(usuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(usuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(usuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(usuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new usuario().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.toedter.calendar.JDateChooser dCita;
    private com.toedter.calendar.JDateChooser dNacimiento;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JTextField jOrden;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JTextField jParticipante2;
    private javax.swing.JTextField jParticipante3;
    private javax.swing.JTextField jSituacion;
    private javax.swing.JPanel pUdalekuak;
    private javax.swing.JTextField tHora;
    private javax.swing.JTextField tNumero;
    private javax.swing.JTextField tParticipante;
    private javax.swing.JButton volver;
    // End of variables declaration//GEN-END:variables
}
