package system;

import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Andrew
 */
public class Reports extends javax.swing.JFrame {

    dbManager newManager = new dbManager();
    Object[][] row;

    public Reports() {
        initComponents();
        populateOrderTable("ALL");
    }

    public final void populateOrderTable(String action) {
        String columnNames[] = {"ID", "Sales Date", "Total Sale"};
        DefaultTableModel tableModel = new DefaultTableModel(newManager.getSalesData(action), columnNames);
        tableSales.setModel(tableModel);
//        tblInventory.getColumnModel().getColumn(0).setPreferredWidth(100);
//        tblInventory.getColumnModel().getColumn(1).setPreferredWidth(300);
//        tblInventory.getColumnModel().getColumn(2).setPreferredWidth(100);
//        tblInventory.getColumnModel().getColumn(3).setPreferredWidth(100);
//        tblInventory.getColumnModel().getColumn(4).setPreferredWidth(100);
    }
    
      public final void populateEmployeeSalesTable() {
        String columnNames[] = {"Sale ID", "User", "Employee ID"};
        DefaultTableModel tableModel = new DefaultTableModel(newManager.getEmployeeSales(), columnNames);
        tableSales.setModel(tableModel);
//        tblInventory.getColumnModel().getColumn(0).setPreferredWidth(100);
//        tblInventory.getColumnModel().getColumn(1).setPreferredWidth(300);
//        tblInventory.getColumnModel().getColumn(2).setPreferredWidth(100);
//        tblInventory.getColumnModel().getColumn(3).setPreferredWidth(100);
//        tblInventory.getColumnModel().getColumn(4).setPreferredWidth(100);
    }

    public int getID() {
        int id = (int) tableSales.getValueAt(tableSales.getSelectedRow(), 0);
        return id;
    }

    public void populateStockCount() {
        String columnNames[] = {"Item", "Ordered Count"};

        int n = newManager.getRecipeData().length;
        row = new Object[n][2];
        int index = 0;
        for (int i = 0; i < n; i++) {
            row[i][0] = newManager.getRecipeData()[i][1];
            row[i][1] = newManager.getRecipeData()[i][5];
        }
        DefaultTableModel tableModel = new DefaultTableModel(row, columnNames);
        tableSales.setModel(tableModel);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        buttonClose = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableSales = new javax.swing.JTable();
        buttonClose1 = new javax.swing.JButton();
        buttonClose2 = new javax.swing.JButton();
        buttonClose3 = new javax.swing.JButton();
        buttonClose4 = new javax.swing.JButton();
        buttonClose5 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        buttonClose.setText("Close");
        buttonClose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonCloseActionPerformed(evt);
            }
        });

        tableSales.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tableSales.setGridColor(new java.awt.Color(204, 204, 204));
        jScrollPane1.setViewportView(tableSales);

        buttonClose1.setText("Sales History");
        buttonClose1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonClose1ActionPerformed(evt);
            }
        });

        buttonClose2.setText("Employee Preformance");
        buttonClose2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonClose2ActionPerformed(evt);
            }
        });

        buttonClose3.setText("Monthly Sales");
        buttonClose3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonClose3ActionPerformed(evt);
            }
        });

        buttonClose4.setText("Daily Sales");
        buttonClose4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonClose4ActionPerformed(evt);
            }
        });

        buttonClose5.setText("Stock Control");
        buttonClose5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonClose5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(buttonClose1, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonClose2, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonClose3, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonClose4, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonClose5, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(341, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(buttonClose, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 325, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(buttonClose1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(buttonClose4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(buttonClose3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(buttonClose2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(buttonClose5)
                .addContainerGap(142, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 264, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGap(241, 241, 241)
                            .addComponent(buttonClose)))
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void buttonCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonCloseActionPerformed
        this.dispose();
    }//GEN-LAST:event_buttonCloseActionPerformed

    private void buttonClose1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonClose1ActionPerformed
        populateOrderTable("ALL");
    }//GEN-LAST:event_buttonClose1ActionPerformed

    private void buttonClose2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonClose2ActionPerformed
        populateEmployeeSalesTable();
    }//GEN-LAST:event_buttonClose2ActionPerformed

    private void buttonClose3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonClose3ActionPerformed
        populateOrderTable("MONTH");
    }//GEN-LAST:event_buttonClose3ActionPerformed

    private void buttonClose4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonClose4ActionPerformed
        populateOrderTable("DAY");
    }//GEN-LAST:event_buttonClose4ActionPerformed

    private void buttonClose5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonClose5ActionPerformed
        populateStockCount();
    }//GEN-LAST:event_buttonClose5ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonClose;
    private javax.swing.JButton buttonClose1;
    private javax.swing.JButton buttonClose2;
    private javax.swing.JButton buttonClose3;
    private javax.swing.JButton buttonClose4;
    private javax.swing.JButton buttonClose5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tableSales;
    // End of variables declaration//GEN-END:variables
}
