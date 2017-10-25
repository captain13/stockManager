package system;

import javax.swing.DefaultComboBoxModel;

/**
 *
 * @author Chad
 */
public class ReprintForm extends javax.swing.JFrame {

    dbManager system = new dbManager();
    receiptHandler rHandler = new receiptHandler();

    public ReprintForm() {
        initComponents();
        this.setLocationRelativeTo(null);
        fillEmpCombo();
    }

    String head;
    String body;
    String tail;
    double total = 0;
    StringBuilder items = new StringBuilder();
    internalClock clock = new internalClock();
    String stars = new String(new char[54]).replace("\0", "*");
    String spaces = new String(new char[54]).replace("\0", " ");

    public void header(String dt, String t) {
        head = stars + "\n"
                + "COMPANY NAME " + " " + "\n"
                + dt + " "
                + t + "\n"
                + stars + "\n";
        jTextAreaRef.append(head);
    }

    public void body(String dt, String t) {
        body = spaces + "\n"
                + "Items                        Qty   Price\n"
                + itemsList(system.getReprintReceipt(dt, t)) + "\n"
                + "Total " + total + "\n"
                + "Amount........................\n";
        jTextAreaRef.append(body);
    }

    public void tail() {
        tail = spaces + "\n"
                + stars + "\n"
                + "THANK YOU\n"
                + "PLEASE COME AGAIN\n"
                //+ spaces + "\n"
                //+ "WAITER\n"
                //+ NewOrder.getWaiter() + "\n"
                + stars + "\n";
        jTextAreaRef.append(tail);
    }

    public StringBuilder itemsList(Object[][] list) {
        items.setLength(0);
        int len;
        int spc;
        for (Object[] list1 : list) {
            len = system.getRecipeName(Integer.parseInt(list1[1].toString())).length();
            spc = 30 - len;
            items.append(system.getRecipeName(Integer.parseInt(list1[1].toString()))).append(printS(spc));
             
            len = list1[2].toString().length();
            spc = 5 - len;
            items.append(list1[2]).append(printS(spc));
            
            len = list1[6].toString().length();
            spc = 5 - len;
            items.append(list1[6]).append(printS(spc));
            
            total = total + Double.parseDouble(list1[6].toString());
            items.append("\n");
        }
        return items;
    }

    private String printS(int n) {
        String spc = new String(new char[n]).replace("\0", " ");
        return spc;
    }

    public void display() {
        String dt = null;
        String t = null;
        if (jComboBoxDt.getSelectedItem() != null) {
            dt = jComboBoxDt.getSelectedItem().toString();
        }
        if (jComboBoxT.getSelectedItem() != null) {
            t = jComboBoxT.getSelectedItem().toString();
        }

        header(dt, t);
        body(dt, t);
        tail();
    }

    public String getPrintPreview() {
        return jTextAreaRef.getText();
    }

    public void fillDTCombo(String empID) {
        Object[][] list = system.getReceiptData(empID);
        for (Object[] list1 : list) {
            if (((DefaultComboBoxModel) jComboBoxDt.getModel()).getIndexOf(list1[0].toString()) == -1) {
                jComboBoxDt.addItem(list1[0].toString());
            }
            if (((DefaultComboBoxModel) jComboBoxT.getModel()).getIndexOf(list1[1].toString()) == -1) {
                jComboBoxT.addItem(list1[1].toString());
            }
        }
    }

    public void fillEmpCombo() {
        Object[][] list = system.getEmployeeData();
        for (Object[] list1 : list) {
            jComboBoxWid.addItem(list1[0].toString());
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton3 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jButtonPrint = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextAreaRef = new javax.swing.JTextArea();
        jComboBoxDt = new javax.swing.JComboBox<>();
        jButtonClose = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jComboBoxWid = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jComboBoxT = new javax.swing.JComboBox<>();

        jButton3.setText("jButton3");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setAlwaysOnTop(true);
        setUndecorated(true);
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jPanel1.setEnabled(false);

        jButtonPrint.setBackground(new java.awt.Color(53, 53, 53));
        jButtonPrint.setForeground(new java.awt.Color(255, 255, 255));
        jButtonPrint.setText("Print");
        jButtonPrint.setContentAreaFilled(false);
        jButtonPrint.setOpaque(true);
        jButtonPrint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonPrintActionPerformed(evt);
            }
        });

        jTextAreaRef.setEditable(false);
        jTextAreaRef.setColumns(20);
        jTextAreaRef.setRows(5);
        jScrollPane1.setViewportView(jTextAreaRef);

        jComboBoxDt.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { }));
        jComboBoxDt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxDtActionPerformed(evt);
            }
        });

        jButtonClose.setBackground(new java.awt.Color(53, 53, 53));
        jButtonClose.setForeground(new java.awt.Color(255, 255, 255));
        jButtonClose.setText("Close");
        jButtonClose.setContentAreaFilled(false);
        jButtonClose.setOpaque(true);
        jButtonClose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCloseActionPerformed(evt);
            }
        });

        jLabel1.setText("Date and Time");

        jComboBoxWid.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select" }));
        jComboBoxWid.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxWidActionPerformed(evt);
            }
        });

        jLabel2.setText("Waiter ID");

        jComboBoxT.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { }));
        jComboBoxT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxTActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(140, 140, 140)
                .addComponent(jButtonPrint)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButtonClose)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2))
                        .addGap(8, 8, 8)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jComboBoxWid, 0, 127, Short.MAX_VALUE)
                            .addComponent(jComboBoxDt, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addComponent(jComboBoxT, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 360, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(16, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBoxWid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBoxDt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(jComboBoxT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(7, 7, 7)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 404, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonClose)
                    .addComponent(jButtonPrint))
                .addContainerGap(18, Short.MAX_VALUE))
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
    }// </editor-fold>//GEN-END:initComponents

    private void jComboBoxTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxTActionPerformed
        if (jComboBoxDt.getSelectedItem() != null || jComboBoxT.getSelectedItem() != null) {
            jTextAreaRef.setText("");
            total = 0;
            display();
        } else {
            jTextAreaRef.setText("");
        }
    }//GEN-LAST:event_jComboBoxTActionPerformed

    private void jButtonCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCloseActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButtonCloseActionPerformed

    private void jButtonPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonPrintActionPerformed
        rHandler.writeTextToPDF(getPrintPreview());
    }//GEN-LAST:event_jButtonPrintActionPerformed

    private void jComboBoxWidActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxWidActionPerformed
        jTextAreaRef.setText("");
        jComboBoxDt.removeAllItems();
        jComboBoxT.removeAllItems();
        fillDTCombo(jComboBoxWid.getSelectedItem().toString());
    }//GEN-LAST:event_jComboBoxWidActionPerformed

    private void jComboBoxDtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxDtActionPerformed
        jTextAreaRef.setText("");
    }//GEN-LAST:event_jComboBoxDtActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButtonClose;
    private javax.swing.JButton jButtonPrint;
    private javax.swing.JComboBox<String> jComboBoxDt;
    private javax.swing.JComboBox<String> jComboBoxT;
    private javax.swing.JComboBox<String> jComboBoxWid;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    public javax.swing.JTextArea jTextAreaRef;
    // End of variables declaration//GEN-END:variables
}
