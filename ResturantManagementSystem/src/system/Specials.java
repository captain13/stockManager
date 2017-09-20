/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package system;

import java.awt.GridLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;

/**
 *
 * @author Andrew
 */
public class Specials extends javax.swing.JFrame {

    dbManager newManager = new dbManager();
    Object specialsItem[];
    JButton button;

    public Specials() {
        initComponents();
        setLayout();
    }

    public final void setLayout() {
        int j = 0;
        GridLayout grid = new GridLayout(0, 2);
        pnlItems.setLayout(grid);

        for (int i = 0; i < newManager.getSpecialData().length; i++) {
            if (newManager.getSpecialData()[i][4].equals(true)) {
                j++;
            }
        }
        specialsItem = new Object[j];

        for (int i = 0; i < newManager.getSpecialData().length; i++) {
            if (newManager.getSpecialData()[i][4].equals(true)) {
                specialsItem[i] = newManager.getSpecialData()[i][5];
            }
        }

        int emptySpaceTab = 6 - specialsItem.length;
        for (int i = 1; i <= specialsItem.length; i++) {
            ImageIcon icon = new ImageIcon(specialsItem[i - 1].toString());
            button = new JButton(icon);
            button.setContentAreaFilled(false);
            button.setFocusPainted(false);
            pnlItems.add(button);
        }

        for (int i = 0; i < emptySpaceTab; i++) {
            JButton emptyButton = new JButton();
            pnlItems.add(emptyButton);
            emptyButton.setVisible(false);
        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlSpecial = new javax.swing.JPanel();
        buttonClose = new javax.swing.JButton();
        pnlItems = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        setUndecorated(true);
        setResizable(false);
        addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                formFocusLost(evt);
            }
        });
        addWindowStateListener(new java.awt.event.WindowStateListener() {
            public void windowStateChanged(java.awt.event.WindowEvent evt) {
                formWindowStateChanged(evt);
            }
        });

        pnlSpecial.setBackground(new java.awt.Color(255, 255, 255));
        pnlSpecial.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        pnlSpecial.setPreferredSize(new java.awt.Dimension(235, 370));

        buttonClose.setBackground(new java.awt.Color(75, 75, 75));
        buttonClose.setForeground(new java.awt.Color(255, 255, 255));
        buttonClose.setText("Close");
        buttonClose.setContentAreaFilled(false);
        buttonClose.setOpaque(true);
        buttonClose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonCloseActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlItemsLayout = new javax.swing.GroupLayout(pnlItems);
        pnlItems.setLayout(pnlItemsLayout);
        pnlItemsLayout.setHorizontalGroup(
            pnlItemsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 231, Short.MAX_VALUE)
        );
        pnlItemsLayout.setVerticalGroup(
            pnlItemsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 285, Short.MAX_VALUE)
        );

        jLabel1.setText("Special");

        javax.swing.GroupLayout pnlSpecialLayout = new javax.swing.GroupLayout(pnlSpecial);
        pnlSpecial.setLayout(pnlSpecialLayout);
        pnlSpecialLayout.setHorizontalGroup(
            pnlSpecialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlSpecialLayout.createSequentialGroup()
                .addGroup(pnlSpecialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlSpecialLayout.createSequentialGroup()
                        .addGap(87, 87, 87)
                        .addComponent(buttonClose))
                    .addGroup(pnlSpecialLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(pnlItems, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        pnlSpecialLayout.setVerticalGroup(
            pnlSpecialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlSpecialLayout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlItems, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(22, 22, 22)
                .addComponent(buttonClose)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlSpecial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlSpecial, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void buttonCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonCloseActionPerformed
        dispose();
    }//GEN-LAST:event_buttonCloseActionPerformed

    private void formFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_formFocusLost
         this.dispose();
    }//GEN-LAST:event_formFocusLost

    private void formWindowStateChanged(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowStateChanged
        this.dispose();
    }//GEN-LAST:event_formWindowStateChanged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonClose;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel pnlItems;
    private javax.swing.JPanel pnlSpecial;
    // End of variables declaration//GEN-END:variables
}
