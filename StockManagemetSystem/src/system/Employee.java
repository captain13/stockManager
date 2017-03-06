/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package system;

import java.sql.*;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author Andrew
 */
public class Employee extends javax.swing.JFrame {

    private static boolean IS_RUNNING = false;
    
     @Override
    public void dispose() {
        Employee.IS_RUNNING = false;
        super.dispose();
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonAdd = new javax.swing.JButton();
        buttonDelete = new javax.swing.JButton();
        buttonPassword = new javax.swing.JButton();
        buttonClose = new javax.swing.JButton();
        buttonLogin = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setAlwaysOnTop(true);
        setUndecorated(true);
        setResizable(false);

        buttonAdd.setText("Add Employee");
        buttonAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonAddActionPerformed(evt);
            }
        });

        buttonDelete.setText("Delete Employee");
        buttonDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonDeleteActionPerformed(evt);
            }
        });

        buttonPassword.setText("Change Employee Password");
        buttonPassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonPasswordActionPerformed(evt);
            }
        });

        buttonClose.setText("Close");
        buttonClose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonCloseActionPerformed(evt);
            }
        });

        buttonLogin.setText("Currently Logged in");
        buttonLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonLoginActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(buttonAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(buttonDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(buttonPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(buttonClose, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(buttonLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(468, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(buttonAdd)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(buttonDelete)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(buttonPassword)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(buttonLogin)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 124, Short.MAX_VALUE)
                .addComponent(buttonClose)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void buttonAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAddActionPerformed
        AddEmployee s = new AddEmployee();
        s.setVisible(true);
        Keyboard k = new Keyboard();
        k.setLocation(350, 530);
        k.setVisible(true);
    }//GEN-LAST:event_buttonAddActionPerformed

    private void buttonDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonDeleteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_buttonDeleteActionPerformed

    private void buttonPasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonPasswordActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_buttonPasswordActionPerformed

    private void buttonCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonCloseActionPerformed
       this.dispose();
    }//GEN-LAST:event_buttonCloseActionPerformed

    private void buttonLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonLoginActionPerformed

    }//GEN-LAST:event_buttonLoginActionPerformed

    /**
     * @param args the command line arguments
     */
    
    public void tester(){
        System.out.println("test");
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Employee().setVisible(true);
            }
        });
        
        try {
            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
            Connection conn = DriverManager.getConnection("jdbc;odbc;DBSauce");
            Statement st = conn.createStatement();
            ResultSet rest = st.executeQuery("SELECT fName FROM Employees");
            while(rest.next()){
                int ID = Integer.parseInt(rest.getString(1));
                String fName =  rest.getString(2);
                System.out.println(fName);
                //jTable1.addColumn(null);
            }
        } catch(ClassNotFoundException | SQLException c) {
            c.printStackTrace();
        }
    }
    public Employee()
    {
         if (IS_RUNNING) {
            throw new RuntimeException();
        } else {
            IS_RUNNING = true;
        }
        initComponents();
        super.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        
        //headers for the table
        String[] columns = {"Column 1","Column 2","C3"};
        //actual data for the table in a 2d array
        Object[][] data = {
            {"Kathy", "Smith", "Snowboarding"},
            {"John", "Doe", "Rowing"}
        };
        //create table model with data
        DefaultTableModel model = new DefaultTableModel(data, columns) {

            @Override
            public boolean isCellEditable(int row, int column)
            {
                return false;
            }

            //@Override
            /*public Class<?> getColumnClass(int columnIndex)
            {
                //return columnClass[columnIndex];
            }*/
        };
        JTable table = new JTable(model);
        
        //add the table to the frame
        this.add(new JScrollPane(table));
        
        this.setTitle("Table Example");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);        
        this.pack();
        this.setVisible(true);
    }

    public static void main(String args[]) {
    
        /*Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Employee.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Employee.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Employee.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Employee.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

         
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Employee().setVisible(true);
            }
        });
        
        try {
            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
            Connection conn = DriverManager.getConnection("jdbc;odbc;DBSauce");
            Statement st = conn.createStatement();
            ResultSet rest = st.executeQuery("SELECT fName FROM Employees");
            while(rest.next()){
                int ID = Integer.parseInt(rest.getString(1));
                String fName =  rest.getString(2);
                System.out.println(fName);
                //jTable1.addColumn(null);
            }
        } catch(ClassNotFoundException | SQLException c) {
            c.printStackTrace();
        }
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonAdd;
    private javax.swing.JButton buttonClose;
    private javax.swing.JButton buttonDelete;
    private javax.swing.JButton buttonLogin;
    private javax.swing.JButton buttonPassword;
    // End of variables declaration//GEN-END:variables
}
