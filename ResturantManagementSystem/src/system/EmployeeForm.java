package system;

import java.awt.Color;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Andrew
 */
public class EmployeeForm extends javax.swing.JFrame {

    dbManager newManager = new dbManager();
    internalClock clock = new internalClock();
    double Wage;
    double rate;

    public EmployeeForm(Color color) {
        initComponents();
        populateEmployeeTable();
        setButtonColor(color);
    }

    public final void populateEmployeeTable() {
        String columnNames[] = {"ID", "First Name", "Last Name", "Number", "Hours Worked"};
        DefaultTableModel tableModel = new DefaultTableModel(newManager.getEmployeeData(), columnNames);
        tableEmp.setModel(tableModel);
    }

    public int getID() {
        int id = (int) tableEmp.getValueAt(tableEmp.getSelectedRow(), 0);
        return id;
    }

    public String getUsername() {
        String username = tableEmp.getValueAt(tableEmp.getSelectedRow(), 1).toString();
        return username;
    }

    public void changePassword() {
        String regexStr = "^[a-zA-Z0-9]{8,}";
        if (tableEmp.getSelectionModel().isSelectionEmpty() == false) {
            String password = JOptionPane.showInputDialog("Please Enter your New Password");
            if (JOptionPane.NO_OPTION != -1) {
            if (password != null && password.matches(regexStr)) {
                String passwordConfirm = JOptionPane.showInputDialog("Please Confirm Password");
                if (password.equals(passwordConfirm)) {
                    newManager.updateEmployee(getUsername(), password);
                    JOptionPane.showMessageDialog(null, "Password's Changed");
                } else {
                    JOptionPane.showMessageDialog(null, "Password's do not Match");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Please enter a valid Password");
            }
            } else {
                System.out.println("click cancel");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Please select an Employee");
        }
    }

    public Double calculateWage() {
        Wage = 0.0;
        String confirmUsername = getUsername();
        int ID = getID();

        String salarySt[] = newManager.getHoursWorked(confirmUsername).split("hrs");

        String hours = salarySt[0];
        String minutes = salarySt[1];

        Double HoursWorked = Double.parseDouble(hours);
        Double minutesWorked = Double.parseDouble(minutes);
        String option = JOptionPane.showInputDialog("Please enter the Rate");
        if (option != null) {
            rate = Double.parseDouble(option);
            Wage = HoursWorked * rate + (rate / 60) * minutesWorked;
            JOptionPane.showMessageDialog(this, "Employee payout " + String.format("R%.2f", Wage));
            newManager.insertWage(Wage, ID);
        }

        return Wage;
    }

    public final void setButtonColor(Color color) {
        buttonAdd.setBackground(color);
        buttonDelete.setBackground(color);
        buttonPassword.setBackground(color);
        toggleLogin.setBackground(color);
        buttonWage.setBackground(color);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        buttonAdd = new javax.swing.JButton();
        buttonClose = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableEmp = new javax.swing.JTable();
        buttonPassword = new javax.swing.JButton();
        buttonDelete = new javax.swing.JButton();
        toggleLogin = new javax.swing.JToggleButton();
        buttonWage = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        buttonAdd.setBackground(new java.awt.Color(0, 138, 231));
        buttonAdd.setForeground(new java.awt.Color(255, 255, 255));
        buttonAdd.setText("Add Employee");
        buttonAdd.setContentAreaFilled(false);
        buttonAdd.setOpaque(true);
        buttonAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonAddActionPerformed(evt);
            }
        });

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

        tableEmp.setModel(new javax.swing.table.DefaultTableModel(
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
        tableEmp.setGridColor(new java.awt.Color(204, 204, 204));
        jScrollPane1.setViewportView(tableEmp);

        buttonPassword.setBackground(new java.awt.Color(0, 138, 231));
        buttonPassword.setForeground(new java.awt.Color(255, 255, 255));
        buttonPassword.setText("Change Employee Password");
        buttonPassword.setContentAreaFilled(false);
        buttonPassword.setOpaque(true);
        buttonPassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonPasswordActionPerformed(evt);
            }
        });

        buttonDelete.setBackground(new java.awt.Color(0, 138, 231));
        buttonDelete.setForeground(new java.awt.Color(255, 255, 255));
        buttonDelete.setText("Delete Employee");
        buttonDelete.setContentAreaFilled(false);
        buttonDelete.setOpaque(true);
        buttonDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonDeleteActionPerformed(evt);
            }
        });

        toggleLogin.setBackground(new java.awt.Color(0, 138, 231));
        toggleLogin.setForeground(new java.awt.Color(255, 255, 255));
        toggleLogin.setText("Currently Logged in");
        toggleLogin.setContentAreaFilled(false);
        toggleLogin.setOpaque(true);
        toggleLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                toggleLoginActionPerformed(evt);
            }
        });

        buttonWage.setBackground(new java.awt.Color(0, 138, 231));
        buttonWage.setForeground(new java.awt.Color(255, 255, 255));
        buttonWage.setText("Calculate Wage");
        buttonWage.setContentAreaFilled(false);
        buttonWage.setOpaque(true);
        buttonWage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonWageActionPerformed(evt);
            }
        });

        jLabel1.setText("Employees");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(toggleLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(buttonWage, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(230, 230, 230)
                                .addComponent(buttonClose, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(buttonAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(buttonDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(buttonPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 452, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(jSeparator2))))
                .addContainerGap())
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
                        .addComponent(toggleLogin)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonWage)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonAdd)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonDelete)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonPassword))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 288, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(7, 7, 7)
                .addComponent(buttonClose)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 7, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void buttonAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAddActionPerformed
        AddEmployee s = new AddEmployee(this);
        s.setVisible(true);
    }//GEN-LAST:event_buttonAddActionPerformed

    private void buttonDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonDeleteActionPerformed
        if (tableEmp.getSelectionModel().isSelectionEmpty() == false) {
            int confirm = JOptionPane.showConfirmDialog(null, "Are you sure?", null, JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                newManager.removeEmployee(getID());
                populateEmployeeTable();
            }
        } else {
            JOptionPane.showMessageDialog(null, "Please select an employee");
        }
    }//GEN-LAST:event_buttonDeleteActionPerformed

    private void buttonPasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonPasswordActionPerformed
        changePassword();
    }//GEN-LAST:event_buttonPasswordActionPerformed

    private void buttonCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonCloseActionPerformed
        this.dispose();
    }//GEN-LAST:event_buttonCloseActionPerformed

    private void toggleLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_toggleLoginActionPerformed
        if (toggleLogin.isSelected()) {
            newManager.showActiveEmp();
            toggleLogin.setText("View employee hours");
        } else {
            populateEmployeeTable();
            toggleLogin.setText("Currently Logged in");
        }
    }//GEN-LAST:event_toggleLoginActionPerformed

    private void buttonWageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonWageActionPerformed
        if (tableEmp.getSelectionModel().isSelectionEmpty() == false) {
            calculateWage();
            newManager.insertExpenses("Wage", Wage, clock.getCurrentDate());
        } else {
            JOptionPane.showMessageDialog(null, "Please select an employee");
        }
    }//GEN-LAST:event_buttonWageActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonAdd;
    private javax.swing.JButton buttonClose;
    private javax.swing.JButton buttonDelete;
    private javax.swing.JButton buttonPassword;
    private javax.swing.JButton buttonWage;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    public static javax.swing.JTable tableEmp;
    private javax.swing.JToggleButton toggleLogin;
    // End of variables declaration//GEN-END:variables
}
