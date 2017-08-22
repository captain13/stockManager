/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package system;

/**
 *
 * @author Andrew
 */
public class Specials extends javax.swing.JFrame {
    private static boolean IS_RUNNING = false;

    public Specials() {
        if (IS_RUNNING) {
            throw new RuntimeException();
        } else {
            IS_RUNNING = true;
        }
        initComponents();
        super.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }
    
      @Override
    public void dispose() {
        Specials.IS_RUNNING = false;
        super.dispose();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlSpecial = new javax.swing.JPanel();
        lblSpecials1 = new javax.swing.JLabel();
        lblSpecials2 = new javax.swing.JLabel();
        lblSpecials3 = new javax.swing.JLabel();
        lblSpecials4 = new javax.swing.JLabel();
        lblSpecials5 = new javax.swing.JLabel();
        lblSpecials6 = new javax.swing.JLabel();
        buttonClose = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setAlwaysOnTop(true);
        setBackground(new java.awt.Color(255, 255, 255));
        setUndecorated(true);
        setResizable(false);

        pnlSpecial.setBackground(new java.awt.Color(255, 255, 255));
        pnlSpecial.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lblSpecials1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/_f_steak.jpg"))); // NOI18N
        lblSpecials1.setText("l");

        lblSpecials2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/_f_chickenBurger.jpg"))); // NOI18N

        lblSpecials3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/_f_beefBurger.jpg"))); // NOI18N

        lblSpecials4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/_f_chickenBurger.jpg"))); // NOI18N

        lblSpecials5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/_f_pizza.jpg"))); // NOI18N

        lblSpecials6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/_f_beefBurger.jpg"))); // NOI18N

        buttonClose.setText("Close");
        buttonClose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonCloseActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlSpecialLayout = new javax.swing.GroupLayout(pnlSpecial);
        pnlSpecial.setLayout(pnlSpecialLayout);
        pnlSpecialLayout.setHorizontalGroup(
            pnlSpecialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlSpecialLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlSpecialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlSpecialLayout.createSequentialGroup()
                        .addGap(0, 10, Short.MAX_VALUE)
                        .addComponent(lblSpecials6)
                        .addGap(18, 18, 18)
                        .addComponent(lblSpecials2))
                    .addGroup(pnlSpecialLayout.createSequentialGroup()
                        .addComponent(lblSpecials5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblSpecials1))
                    .addGroup(pnlSpecialLayout.createSequentialGroup()
                        .addComponent(lblSpecials3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblSpecials4)))
                .addContainerGap())
            .addGroup(pnlSpecialLayout.createSequentialGroup()
                .addGap(76, 76, 76)
                .addComponent(buttonClose)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlSpecialLayout.setVerticalGroup(
            pnlSpecialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlSpecialLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(pnlSpecialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblSpecials6)
                    .addComponent(lblSpecials2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlSpecialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblSpecials5)
                    .addComponent(lblSpecials1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlSpecialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblSpecials3)
                    .addComponent(lblSpecials4))
                .addGap(18, 18, 18)
                .addComponent(buttonClose)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlSpecial, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlSpecial, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void buttonCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonCloseActionPerformed
        dispose();
    }//GEN-LAST:event_buttonCloseActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonClose;
    private javax.swing.JLabel lblSpecials1;
    private javax.swing.JLabel lblSpecials2;
    private javax.swing.JLabel lblSpecials3;
    private javax.swing.JLabel lblSpecials4;
    private javax.swing.JLabel lblSpecials5;
    private javax.swing.JLabel lblSpecials6;
    private javax.swing.JPanel pnlSpecial;
    // End of variables declaration//GEN-END:variables
}
