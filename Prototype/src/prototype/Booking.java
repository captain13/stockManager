/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prototype;

/**
 *
 * @author Andrew
 */
public class Booking extends javax.swing.JFrame {

    /**
     * Creates new form Booking
     */
    public Booking() {
        initComponents();
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
        jButton1 = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        table1 = new javax.swing.JButton();
        table3 = new javax.swing.JButton();
        table5 = new javax.swing.JButton();
        table2 = new javax.swing.JButton();
        table6 = new javax.swing.JButton();
        table4 = new javax.swing.JButton();
        table8 = new javax.swing.JButton();
        table7 = new javax.swing.JButton();
        table9 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setAlwaysOnTop(true);
        setUndecorated(true);

        jLabel1.setText("Bookings");

        jButton1.setText("Accept");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));

        table1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/_o_tableB.jpg"))); // NOI18N
        table1.setContentAreaFilled(false);
        table1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                table1ActionPerformed(evt);
            }
        });

        table3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/_o_tableBar.png"))); // NOI18N
        table3.setContentAreaFilled(false);
        table3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                table3ActionPerformed(evt);
            }
        });

        table5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/_o_tableS.jpg"))); // NOI18N
        table5.setContentAreaFilled(false);
        table5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                table5ActionPerformed(evt);
            }
        });

        table2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/_o_tableB.jpg"))); // NOI18N
        table2.setContentAreaFilled(false);
        table2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                table2ActionPerformed(evt);
            }
        });

        table6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/_o_tableS.jpg"))); // NOI18N
        table6.setContentAreaFilled(false);
        table6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                table6ActionPerformed(evt);
            }
        });

        table4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/_o_tableS.jpg"))); // NOI18N
        table4.setContentAreaFilled(false);
        table4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                table4ActionPerformed(evt);
            }
        });

        table8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/_o_tableS.jpg"))); // NOI18N
        table8.setContentAreaFilled(false);
        table8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                table8ActionPerformed(evt);
            }
        });

        table7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/_o_tableB.jpg"))); // NOI18N
        table7.setContentAreaFilled(false);
        table7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                table7ActionPerformed(evt);
            }
        });

        table9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/_o_tableB.jpg"))); // NOI18N
        table9.setContentAreaFilled(false);
        table9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                table9ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(table9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(table8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(table1, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(table7, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                                .addComponent(table4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(table2)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(table5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))))
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                        .addComponent(table6)
                        .addGap(218, 218, 218))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(table3)
                        .addGap(206, 206, 206))))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(table1)
                                .addGap(36, 36, 36)
                                .addComponent(table4))
                            .addComponent(table7)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(table2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(table5)))
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGap(74, 74, 74)
                                .addComponent(table9))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(table8))))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(table3)
                        .addGap(35, 35, 35)
                        .addComponent(table6)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(197, 197, 197)
                        .addComponent(jButton1)))
                .addContainerGap(196, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, 438, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(20, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(379, 379, 379)
                .addComponent(jButton1)
                .addContainerGap())
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(49, 49, 49)
                    .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, 348, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(50, Short.MAX_VALUE)))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        super.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void table1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_table1ActionPerformed
        BookingPrompt s = new BookingPrompt();
        s.setVisible(true);
    }//GEN-LAST:event_table1ActionPerformed

    private void table3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_table3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_table3ActionPerformed

    private void table5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_table5ActionPerformed
        BookingPrompt s = new BookingPrompt();
        s.setVisible(true);
    }//GEN-LAST:event_table5ActionPerformed

    private void table2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_table2ActionPerformed
        BookingPrompt s = new BookingPrompt();
        s.setVisible(true);
    }//GEN-LAST:event_table2ActionPerformed

    private void table6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_table6ActionPerformed
        BookingPrompt s = new BookingPrompt();
        s.setVisible(true);
    }//GEN-LAST:event_table6ActionPerformed

    private void table4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_table4ActionPerformed
        BookingPrompt s = new BookingPrompt();
        s.setVisible(true);
    }//GEN-LAST:event_table4ActionPerformed

    private void table8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_table8ActionPerformed
        BookingPrompt s = new BookingPrompt();
        s.setVisible(true);
    }//GEN-LAST:event_table8ActionPerformed

    private void table7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_table7ActionPerformed
        BookingPrompt s = new BookingPrompt();
        s.setVisible(true);
    }//GEN-LAST:event_table7ActionPerformed

    private void table9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_table9ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_table9ActionPerformed

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
            java.util.logging.Logger.getLogger(Booking.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Booking.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Booking.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Booking.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Booking().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JButton table1;
    private javax.swing.JButton table2;
    private javax.swing.JButton table3;
    private javax.swing.JButton table4;
    private javax.swing.JButton table5;
    private javax.swing.JButton table6;
    private javax.swing.JButton table7;
    private javax.swing.JButton table8;
    private javax.swing.JButton table9;
    // End of variables declaration//GEN-END:variables
}
