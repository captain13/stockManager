package system;

import javax.swing.JOptionPane;

/**
 *
 * @author Andrew
 */
public class AddEmployee extends javax.swing.JFrame {
    dbManager newManger=new dbManager();
    EmployeeForm newForm;
    /**
     * Creates new form AddEmployee
     */
    public AddEmployee(EmployeeForm newForm) {
        initComponents();
        this.newForm=newForm;
    }

    public String getEmpFirstName() {
        return textfieildFname.getText();
    }

    public String getEmpLastName() {
        return textfieildLname.getText();
    }

    public String getEmpPassword() {
        if (textfieildPassowrd.getText().equals(textfieildPassowrdC.getText())) {
            return textfieildPassowrd.getText();
        } else {
            JOptionPane.showMessageDialog(null, "Password does not Match");
            return null;
        }
    }

    public String getEmpContact() {
        return textfieildContact.getText();
    }

    public int getAdminRights() {
        if (checkAdmin.isSelected()) {
            return 1;
        } else {
            return 0;
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        buttonAccept = new javax.swing.JButton();
        textfieildPassowrdC = new javax.swing.JTextField();
        lblPasswordC = new javax.swing.JLabel();
        lblPassword = new javax.swing.JLabel();
        textfieildPassowrd = new javax.swing.JTextField();
        textfieildContact = new javax.swing.JTextField();
        lblContact = new javax.swing.JLabel();
        lblSname = new javax.swing.JLabel();
        textfieildLname = new javax.swing.JTextField();
        textfieildFname = new javax.swing.JTextField();
        lblFname = new javax.swing.JLabel();
        lblTitle = new javax.swing.JLabel();
        buttonAccept1 = new javax.swing.JButton();
        checkAdmin = new javax.swing.JCheckBox();
        lblQty3 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        buttonAccept.setBackground(new java.awt.Color(75, 75, 75));
        buttonAccept.setForeground(new java.awt.Color(255, 255, 255));
        buttonAccept.setText("Cancel");
        buttonAccept.setContentAreaFilled(false);
        buttonAccept.setOpaque(true);
        buttonAccept.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonAcceptActionPerformed(evt);
            }
        });

        lblPasswordC.setText("Confirm Password");

        lblPassword.setText("Password");

        lblContact.setText("Contact no.");

        lblSname.setText("Last Name");

        lblFname.setText("First Name");

        lblTitle.setText("Add Employee");

        buttonAccept1.setBackground(new java.awt.Color(75, 75, 75));
        buttonAccept1.setForeground(new java.awt.Color(255, 255, 255));
        buttonAccept1.setText("Accept");
        buttonAccept1.setContentAreaFilled(false);
        buttonAccept1.setOpaque(true);
        buttonAccept1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonAccept1ActionPerformed(evt);
            }
        });

        checkAdmin.setText("Admin");
        checkAdmin.setContentAreaFilled(false);

        lblQty3.setText("Postion");

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Waiter", "Cheif", "Manager", " " }));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(lblTitle)
                        .addGap(167, 167, 167))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblFname)
                                    .addComponent(lblSname)
                                    .addComponent(lblContact)
                                    .addComponent(lblPassword))
                                .addGap(47, 47, 47)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(textfieildFname, javax.swing.GroupLayout.DEFAULT_SIZE, 142, Short.MAX_VALUE)
                                    .addComponent(textfieildLname)
                                    .addComponent(textfieildContact)
                                    .addComponent(textfieildPassowrd)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(lblPasswordC)
                                .addGap(18, 18, 18)
                                .addComponent(textfieildPassowrdC)))
                        .addContainerGap())))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblQty3)
                .addGap(71, 71, 71)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(checkAdmin)
                        .addGap(0, 85, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(67, 67, 67)
                .addComponent(buttonAccept1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(buttonAccept)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTitle)
                .addGap(8, 8, 8)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(lblSname)
                        .addGap(11, 11, 11)
                        .addComponent(lblContact)
                        .addGap(32, 32, 32))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(textfieildFname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblFname))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(textfieildLname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(4, 4, 4)
                        .addComponent(textfieildContact, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(textfieildPassowrd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblPassword))))
                .addGap(3, 3, 3)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPasswordC)
                    .addComponent(textfieildPassowrdC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblQty3)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
                .addComponent(checkAdmin)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonAccept1)
                    .addComponent(buttonAccept))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void buttonAcceptActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAcceptActionPerformed
        super.dispose();
    }//GEN-LAST:event_buttonAcceptActionPerformed

    private void buttonAccept1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAccept1ActionPerformed
        newManger.insertEmployee(getEmpFirstName(), getEmpLastName(), getEmpPassword(), getEmpContact(), getAdminRights());
        newForm.populateEmployeeTable();
        this.dispose();
    }//GEN-LAST:event_buttonAccept1ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonAccept;
    private javax.swing.JButton buttonAccept1;
    private javax.swing.JCheckBox checkAdmin;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblContact;
    private javax.swing.JLabel lblFname;
    private javax.swing.JLabel lblPassword;
    private javax.swing.JLabel lblPasswordC;
    private javax.swing.JLabel lblQty3;
    private javax.swing.JLabel lblSname;
    private javax.swing.JLabel lblTitle;
    public static javax.swing.JTextField textfieildContact;
    public static javax.swing.JTextField textfieildFname;
    public static javax.swing.JTextField textfieildLname;
    public static javax.swing.JTextField textfieildPassowrd;
    private static javax.swing.JTextField textfieildPassowrdC;
    // End of variables declaration//GEN-END:variables
}
