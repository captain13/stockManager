package system;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Chad
 */
public class ReprintForm extends javax.swing.JFrame {
    String url = "jdbc:mysql://localhost:3306/resturantdb";
    String username = "root";
    String password = "root";
    String driver = "com.mysql.jdbc.Driver";
    dbManager system = new dbManager();
    
    public ReprintForm() {
        initComponents();
        fillCombo();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton3 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextAreaRef = new javax.swing.JTextArea();
        jComboBoxDt = new javax.swing.JComboBox<>();
        jButton2 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jComboBoxWid = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jComboBoxT = new javax.swing.JComboBox<>();

        jButton3.setText("jButton3");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setAlwaysOnTop(true);
        setResizable(false);

        jPanel1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jPanel1.setEnabled(false);

        jButton1.setText("Print");

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

        jButton2.setText("Close");

        jLabel1.setText("Date and Time");

        jComboBoxWid.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select waiter" }));

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
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton2)
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
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                    .addComponent(jButton2)
                    .addComponent(jButton1))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jComboBoxDtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxDtActionPerformed
        if (jComboBoxDt.getSelectedItem() != null && jComboBoxT.getSelectedItem() != null ) {
            jTextAreaRef.setText("");
            displayReceipt();
        } else {
            jTextAreaRef.setText("");
        }
    }//GEN-LAST:event_jComboBoxDtActionPerformed

    private void jComboBoxTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxTActionPerformed
        if (jComboBoxDt.getSelectedItem() != null && jComboBoxT.getSelectedItem() != null ) {
            jTextAreaRef.setText("");
            displayReceipt();
        } else {
            jTextAreaRef.setText("");
        }
    }//GEN-LAST:event_jComboBoxTActionPerformed

    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ReprintForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ReprintForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ReprintForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ReprintForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ReprintForm().setVisible(true);
            }
        });
    }
    
    String head;
    String body;
    String tail;
    double total = 0;
    StringBuilder items = new StringBuilder();
    internalClock clock = new internalClock();
    int n = 45;
    String stars = new String(new char[n]).replace("\0", "*");
    String spaces = new String(new char[n]).replace("\0", " "); 
    
    public void header() {
        String dt = jComboBoxDt.getSelectedItem().toString();
        String t = jComboBoxT.getSelectedItem().toString();
        head =  stars + "\n"
                + "COMPANY NAME " + " " + "\n"
                + dt + " "
                + t + "\n"
                + stars + "\n";
                jTextAreaRef.append(head);
    }

    public void body() {
        String dt = jComboBoxDt.getSelectedItem().toString();
        String t = jComboBoxT.getSelectedItem().toString();
        body = spaces + "\n"
                + "Items                          Qty   Price\n"            
                + itemsList(system.getReprintReceipt(dt,t)) +"\n"
                + "Total " + total + "\n" 
                + "Amount........................\n";
                jTextAreaRef.append(body);
    }

    public void tail() {
        tail =  spaces + "\n"
                + stars + "\n"
                + "THANK YOU\n"
                + "PLEASE COME AGAIN\n"
                + spaces + "\n"
                + "WAITER\n"
                //+ NewOrder.getWaiter() + "\n"
                + stars + "\n";
                jTextAreaRef.append(tail);
    }
    
    public StringBuilder itemsList(Object[][] list) {      
        items.setLength(0);
        for (int i = 0; i < list.length; i++) {
            for (int n = 0; n < list[i].length; n++) {
                switch (n) {
                    case 0:
                        {
                            int len = list[i][n].toString().length();
                            int spc = 30 - len;
                            items.append(list[i][n]).append(printS(spc));
                            System.out.println(list[i][n]);
                            break;
                        }
                    case 1:
                        {
                            int len = list[i][n].toString().length();
                            int spc = 5 - len;
                            items.append(list[i][n]).append(printS(spc));
                            break;
                        }
                    case 2:
                        {
                            int len = list[i][n].toString().length();
                            int spc = 5 - len;
                            items.append(list[i][n]).append(printS(spc));
                            total = total + Integer.parseInt(list[i][n].toString());
                            break;
                        }
                    default:
                        break;
                }
            }
            items.append("\n");
        }
        return items;
    }
    
    private String printS(int n){
        String spc = new String(new char[n]).replace("\0", " ");
        return spc;
    }
    
    public void display() {
        header();
        body();
        tail();
    }
    
    public final void fillCombo() {
        try {
            Connection conn = DriverManager.getConnection(url, username, password);
            Statement s = conn.createStatement();
            String query = "SELECT * FROM receipt, employee";
            ResultSet rs = s.executeQuery(query);
            
            while(rs.next()) {
                String dt = rs.getString("date");
                String t = rs.getString("time");
                String wid = rs.getString("employeeID");
                jComboBoxDt.addItem(dt);
                jComboBoxWid.addItem(wid);
                jComboBoxT.addItem(t);
            }
            
        } catch (SQLException e) {
            
        }
    }
    
    public void displayReceipt() {
        String dt = jComboBoxDt.getSelectedItem().toString();
        String t = jComboBoxT.getSelectedItem().toString();
        display();
    }
//    public void reprintReceipt(){
//        
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
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
