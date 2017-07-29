package system;

import javax.swing.JOptionPane;

/**
 *
 * @author Andrew
 */
public class AddEmployee extends javax.swing.JFrame {

    /**
     * Creates new form AddEmployee
     */
    public AddEmployee() {
        initComponents();
    }

    public static String getEmpFirstName() {
        return textfieildFname.getText();
    }

    public static String getEmpLastName() {
        return textfieildLname.getText();
    }

    public static String getEmpPassword() {
        if (textfieildPassowrd.getText().equals(textfieildPassowrdC.getText())) {
            return textfieildPassowrd.getText();
        } else {
            JOptionPane.showMessageDialog(null, "Password does not Match");
            return null;
        }
    }

    public static String getEmpContact() {
        return textfieildContact.getText();
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

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        buttonAccept.setText("Cancel");
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

        buttonAccept1.setText("Accept");
        buttonAccept1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonAccept1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(lblPasswordC)
                        .addGap(18, 18, 18)
                        .addComponent(textfieildPassowrdC, javax.swing.GroupLayout.DEFAULT_SIZE, 121, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(lblTitle)
                        .addGap(18, 18, 18))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblFname)
                            .addComponent(lblSname)
                            .addComponent(lblContact)
                            .addComponent(lblPassword))
                        .addGap(47, 47, 47)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(textfieildFname)
                            .addComponent(textfieildLname)
                            .addComponent(textfieildContact)
                            .addComponent(textfieildPassowrd))))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(buttonAccept1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(buttonAccept)
                .addGap(60, 60, 60))
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
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonAccept1)
                    .addComponent(buttonAccept))
                .addContainerGap(22, Short.MAX_VALUE))
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
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void buttonAcceptActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAcceptActionPerformed
        super.dispose();
    }//GEN-LAST:event_buttonAcceptActionPerformed

    private void buttonAccept1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAccept1ActionPerformed
        dbManager.insertEmployee();
    }//GEN-LAST:event_buttonAccept1ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonAccept;
    private javax.swing.JButton buttonAccept1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblContact;
    private javax.swing.JLabel lblFname;
    private javax.swing.JLabel lblPassword;
    private javax.swing.JLabel lblPasswordC;
    private javax.swing.JLabel lblSname;
    private javax.swing.JLabel lblTitle;
    public static javax.swing.JTextField textfieildContact;
    public static javax.swing.JTextField textfieildFname;
    public static javax.swing.JTextField textfieildLname;
    public static javax.swing.JTextField textfieildPassowrd;
    private static javax.swing.JTextField textfieildPassowrdC;
    // End of variables declaration//GEN-END:variables
}
