/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package system;

/**
 *
 * @author GGPQ9ZJ42
 */
public class AddDatabase extends javax.swing.JFrame {

    /**
     * Creates new form AddDatabase
     */
    public AddDatabase() {
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

        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList();
        lblTitle = new javax.swing.JLabel();
        lblID = new javax.swing.JLabel();
        lblItem = new javax.swing.JLabel();
        lblQty = new javax.swing.JLabel();
        textfieldID = new javax.swing.JTextField();
        textfieldQty = new javax.swing.JTextField();
        textfieldItem = new javax.swing.JTextField();
        lblDistInfo = new javax.swing.JLabel();
        lblDisName = new javax.swing.JLabel();
        textfieldDisName = new javax.swing.JTextField();
        lblDisID = new javax.swing.JLabel();
        lblDisContact = new javax.swing.JLabel();
        textfieldDisID = new javax.swing.JTextField();
        textfieldDisContact = new javax.swing.JTextField();
        lblCostPerItem = new javax.swing.JLabel();
        textfieldCost = new javax.swing.JTextField();
        buttonAccpet = new javax.swing.JButton();

        jList1.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(jList1);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setAlwaysOnTop(true);
        setUndecorated(true);

        lblTitle.setText("Additional Item");

        lblID.setText("ID no.");

        lblItem.setText("Item");

        lblQty.setText("Quanity");

        textfieldID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textfieldIDActionPerformed(evt);
            }
        });

        textfieldQty.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textfieldQtyActionPerformed(evt);
            }
        });

        textfieldItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textfieldItemActionPerformed(evt);
            }
        });

        lblDistInfo.setText("Distrubutor Informantion");

        lblDisName.setText("Distrubutor Name");

        textfieldDisName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textfieldDisNameActionPerformed(evt);
            }
        });

        lblDisID.setText("Distubution ID no.");

        lblDisContact.setText("Distrubutor Contact no. ");

        textfieldDisID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textfieldDisIDActionPerformed(evt);
            }
        });

        textfieldDisContact.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textfieldDisContactActionPerformed(evt);
            }
        });

        lblCostPerItem.setText("Cost per Item");

        textfieldCost.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textfieldCostActionPerformed(evt);
            }
        });

        buttonAccpet.setText("Accept");
        buttonAccpet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonAccpetActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(lblDisContact)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(textfieldDisContact, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(lblDisName)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(textfieldDisName, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGap(63, 63, 63)
                                .addComponent(lblDistInfo)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(lblDisID)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(textfieldDisID, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblTitle)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 243, Short.MAX_VALUE)
                                .addComponent(buttonAccpet))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblItem)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(lblCostPerItem)
                                        .addGap(18, 18, 18)
                                        .addComponent(textfieldCost, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(lblQty)
                                            .addComponent(lblID))
                                        .addGap(46, 46, 46)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(textfieldID, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(textfieldItem, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(textfieldQty, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTitle)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(buttonAccpet)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblID)
                    .addComponent(textfieldID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblItem)
                    .addComponent(textfieldItem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblQty)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblCostPerItem)
                            .addComponent(textfieldCost, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 43, Short.MAX_VALUE)
                        .addComponent(lblDistInfo)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(textfieldDisID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblDisID))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblDisName)
                            .addComponent(textfieldDisName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblDisContact)
                            .addComponent(textfieldDisContact, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(textfieldQty, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void textfieldIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textfieldIDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textfieldIDActionPerformed

    private void textfieldQtyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textfieldQtyActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textfieldQtyActionPerformed

    private void textfieldItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textfieldItemActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textfieldItemActionPerformed

    private void textfieldDisNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textfieldDisNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textfieldDisNameActionPerformed

    private void textfieldDisIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textfieldDisIDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textfieldDisIDActionPerformed

    private void textfieldDisContactActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textfieldDisContactActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textfieldDisContactActionPerformed

    private void textfieldCostActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textfieldCostActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textfieldCostActionPerformed

    private void buttonAccpetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAccpetActionPerformed
        super.dispose(); 
    }//GEN-LAST:event_buttonAccpetActionPerformed

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
            java.util.logging.Logger.getLogger(AddDatabase.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AddDatabase.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AddDatabase.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AddDatabase.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AddDatabase().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonAccpet;
    private javax.swing.JList jList1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblCostPerItem;
    private javax.swing.JLabel lblDisContact;
    private javax.swing.JLabel lblDisID;
    private javax.swing.JLabel lblDisName;
    private javax.swing.JLabel lblDistInfo;
    private javax.swing.JLabel lblID;
    private javax.swing.JLabel lblItem;
    private javax.swing.JLabel lblQty;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JTextField textfieldCost;
    private javax.swing.JTextField textfieldDisContact;
    private javax.swing.JTextField textfieldDisID;
    private javax.swing.JTextField textfieldDisName;
    private javax.swing.JTextField textfieldID;
    private javax.swing.JTextField textfieldItem;
    private javax.swing.JTextField textfieldQty;
    // End of variables declaration//GEN-END:variables
}