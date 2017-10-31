package system;

import java.awt.Color;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Andrew
 */
public class Reports extends javax.swing.JFrame {

    dbManager newManager = new dbManager();
    Object[][] row;

    public Reports(Color color) {
        initComponents();
        populateOrderTable("ALL");
        setButtonColor(color);
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

    public final void populateExpensesTable() {
        String columnNames[] = {"ID", "Type", "Date", "Total Expense"};
        DefaultTableModel tableModel = new DefaultTableModel(newManager.getExpensesData(), columnNames);
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
            row[i][1] = newManager.getRecipeData()[i][6];
        }
        DefaultTableModel tableModel = new DefaultTableModel(row, columnNames);
        tableSales.setModel(tableModel);
    }

    public final void setButtonColor(Color color) {
        buttonSales.setBackground(color);
        buttonSalesDaily.setBackground(color);
        buttonSalesMonthly.setBackground(color);
        buttonExpense.setBackground(color);
        buttonEmployeePerform.setBackground(color);
        buttonStock.setBackground(color);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        buttonClose = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableSales = new javax.swing.JTable();
        buttonSales = new javax.swing.JButton();
        buttonEmployeePerform = new javax.swing.JButton();
        buttonSalesMonthly = new javax.swing.JButton();
        buttonSalesDaily = new javax.swing.JButton();
        buttonStock = new javax.swing.JButton();
        buttonExpense = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel1 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

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
        tableSales.setShowVerticalLines(false);
        jScrollPane1.setViewportView(tableSales);

        buttonSales.setBackground(new java.awt.Color(0, 138, 231));
        buttonSales.setForeground(new java.awt.Color(255, 255, 255));
        buttonSales.setText("Sales History");
        buttonSales.setContentAreaFilled(false);
        buttonSales.setOpaque(true);
        buttonSales.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonSalesActionPerformed(evt);
            }
        });

        buttonEmployeePerform.setBackground(new java.awt.Color(0, 138, 231));
        buttonEmployeePerform.setForeground(new java.awt.Color(255, 255, 255));
        buttonEmployeePerform.setText("Employee Sales");
        buttonEmployeePerform.setContentAreaFilled(false);
        buttonEmployeePerform.setOpaque(true);
        buttonEmployeePerform.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonEmployeePerformActionPerformed(evt);
            }
        });

        buttonSalesMonthly.setBackground(new java.awt.Color(0, 138, 231));
        buttonSalesMonthly.setForeground(new java.awt.Color(255, 255, 255));
        buttonSalesMonthly.setText("Monthly Sales");
        buttonSalesMonthly.setContentAreaFilled(false);
        buttonSalesMonthly.setOpaque(true);
        buttonSalesMonthly.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonSalesMonthlyActionPerformed(evt);
            }
        });

        buttonSalesDaily.setBackground(new java.awt.Color(0, 138, 231));
        buttonSalesDaily.setForeground(new java.awt.Color(255, 255, 255));
        buttonSalesDaily.setText("Daily Sales");
        buttonSalesDaily.setContentAreaFilled(false);
        buttonSalesDaily.setOpaque(true);
        buttonSalesDaily.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonSalesDailyActionPerformed(evt);
            }
        });

        buttonStock.setBackground(new java.awt.Color(0, 138, 231));
        buttonStock.setForeground(new java.awt.Color(255, 255, 255));
        buttonStock.setText("Stock Control");
        buttonStock.setContentAreaFilled(false);
        buttonStock.setOpaque(true);
        buttonStock.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonStockActionPerformed(evt);
            }
        });

        buttonExpense.setBackground(new java.awt.Color(0, 138, 231));
        buttonExpense.setForeground(new java.awt.Color(255, 255, 255));
        buttonExpense.setText("Expenses");
        buttonExpense.setContentAreaFilled(false);
        buttonExpense.setOpaque(true);
        buttonExpense.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonExpenseActionPerformed(evt);
            }
        });

        jLabel1.setText("Reports");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jSeparator2, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(buttonSales, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(buttonEmployeePerform, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(buttonSalesMonthly, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(buttonSalesDaily, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(buttonStock, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(buttonExpense, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1)))
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(273, 273, 273)
                .addComponent(buttonClose, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(270, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(7, 7, 7)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(buttonSales)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonSalesDaily)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonSalesMonthly)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonExpense)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonEmployeePerform)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonStock))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 360, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(buttonClose)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void buttonCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonCloseActionPerformed
        this.dispose();
    }//GEN-LAST:event_buttonCloseActionPerformed

    private void buttonSalesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonSalesActionPerformed
        populateOrderTable("ALL");
    }//GEN-LAST:event_buttonSalesActionPerformed

    private void buttonEmployeePerformActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonEmployeePerformActionPerformed
        populateEmployeeSalesTable();
    }//GEN-LAST:event_buttonEmployeePerformActionPerformed

    private void buttonSalesMonthlyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonSalesMonthlyActionPerformed
        populateOrderTable("MONTH");
    }//GEN-LAST:event_buttonSalesMonthlyActionPerformed

    private void buttonSalesDailyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonSalesDailyActionPerformed
        populateOrderTable("DAY");
    }//GEN-LAST:event_buttonSalesDailyActionPerformed

    private void buttonStockActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonStockActionPerformed
        populateStockCount();
    }//GEN-LAST:event_buttonStockActionPerformed

    private void buttonExpenseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonExpenseActionPerformed
        populateExpensesTable();
    }//GEN-LAST:event_buttonExpenseActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonClose;
    private javax.swing.JButton buttonEmployeePerform;
    private javax.swing.JButton buttonExpense;
    private javax.swing.JButton buttonSales;
    private javax.swing.JButton buttonSalesDaily;
    private javax.swing.JButton buttonSalesMonthly;
    private javax.swing.JButton buttonStock;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTable tableSales;
    // End of variables declaration//GEN-END:variables
}
