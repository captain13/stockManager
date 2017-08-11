/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package system;

import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author GGPQ9ZJ42
 */
public final class NewOrder extends javax.swing.JFrame implements ActionListener {

    String recipeName;
    String recipeIndex;
    String recipeImage;
    ArrayList<JButton> buttonArray = new ArrayList();
    Thread time;
    JButton button;
    String waiter;
    String columnNames[] = {"Item", "Qty", "Price"};
    DefaultTableModel model = new DefaultTableModel(columnNames, 0);
    boolean isRunning = false;
    Object[][] recipeInfo;

    public NewOrder() {
        initComponents();
        getScreenResolution();
        this.setLocation(0, 0);
        tblItems.setModel(model);
        tblItems.getColumnModel().getColumn(0).setPreferredWidth(90);
        tblItems.getColumnModel().getColumn(1).setPreferredWidth(25);
        tblItems.getColumnModel().getColumn(2).setPreferredWidth(45);
        internalClock();
        // dbManager.getRecipe();
        menuLayout();
        startTime();
        //  userManager.createUserLog();
//        customerNo.setText(JOptionPane.showInputDialog(null, "Enter number of customers"));
    }

    public void menuLayout() {

        int n = dbManager.getRecipesCount();
        recipeInfo = new Object[n][4];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < 4; j++) {
                recipeInfo[i][j] = dbManager.getRecipe()[i][j];
            }
        }

//        for (int i = 0; i < n; i++) {
//            System.out.println(recipeInfo[i][0]);
//            System.out.println(recipeInfo[i][1]);
//            System.out.println(recipeInfo[i][2]);
//            System.out.println(recipeInfo[i][3]);
//        }
        GridLayout menuLayout = new GridLayout(0, 8);
        jPanel6.setLayout(menuLayout);
        int emptySpace = 48 - n;
        for (int i = 0; i < n; i++) {
            ImageIcon icon = (new ImageIcon(recipeInfo[i][3].toString()));
            button = new JButton(recipeInfo[i][1].toString(), icon);
            button.setIcon(new ImageIcon(recipeInfo[i][3].toString()));
            button.setVerticalTextPosition(SwingConstants.BOTTOM);
            button.setHorizontalTextPosition(SwingConstants.CENTER);
            button.setMargin(new Insets(0, 0, 0, 0));
            button.setContentAreaFilled(false);
            button.setFocusPainted(false);
            button.setBorder(new EmptyBorder(0, 0, 0, 0));
            button.addActionListener(this);
            jPanel6.add(button);
        }
        for (int i = 1; i < emptySpace; i++) {
            JButton emptyButton = new JButton();
            jPanel6.add(emptyButton);
            emptyButton.setVisible(false);
        }
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        String buttonId = ae.getActionCommand();
        for (int i = 0; i < recipeInfo.length; i++) {
            if (buttonId == recipeInfo[i][1].toString()) {
                model.addRow(new Object[]{recipeInfo[i][1], "1", recipeInfo[i][2]});
            }

        }
        totalAmount();
    }

    public void getScreenResolution() {
        Toolkit tk = Toolkit.getDefaultToolkit();
        int xSize = ((int) tk.getScreenSize().getWidth());
        int ySize = ((int) tk.getScreenSize().getHeight());
        this.setSize(xSize, ySize);
    }

    public static void setUsername() {
        nameTF.setText(UserForm.getUsername());
    }

    public void internalClock() {
        time = new Thread(() -> {
            try {
                while (isRunning == true) {
                    lblClock1.setText(new SimpleDateFormat("hh:mm:ss").format(new Date()));
                }
            } catch (Exception e) {
            }
        });
    }

    public static String getTotal() {
        return textfieldTotal.getText().replaceAll("R", "");
    }

    public void startTime() {
        isRunning = true;
        time.start();
    }

    public void interrupTime() {
        time.interrupt();
        isRunning = false;
    }

    public void totalAmount() {
        double total = 0;
        for (int i = 0; i < tblItems.getRowCount(); i++) {
            double amount = Double.parseDouble(String.valueOf(tblItems.getModel().getValueAt(i, 2)));
            total = total + amount;
        }
        textfieldTotal.setText(String.format("R%.2f", total));
    }

    public void voidItem() {
        int confirm = JOptionPane.showConfirmDialog(null, "Are you sure?", null, JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            try {
                model.removeRow(tblItems.getSelectedRow());
                totalAmount();
            } catch (ArrayIndexOutOfBoundsException exp) {
            }
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblTitle = new javax.swing.JLabel();
        buttonEdit = new javax.swing.JButton();
        buttonClose = new javax.swing.JButton();
        textfieldTotal = new javax.swing.JFormattedTextField();
        buttonPay = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblItems = new javax.swing.JTable();
        jToggleButton8 = new javax.swing.JToggleButton();
        lblWaiter = new javax.swing.JLabel();
        lblNum = new javax.swing.JLabel();
        lblClock1 = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel6 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jButton10 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jButton4 = new javax.swing.JButton();
        jButton14 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        buttonOverride = new javax.swing.JButton();
        nameTF = new javax.swing.JLabel();
        customerNo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setSize(new java.awt.Dimension(1366, 768));

        lblTitle.setText("Order");

        buttonEdit.setText("Edit Order");
        buttonEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonEditActionPerformed(evt);
            }
        });

        buttonClose.setText("Close");
        buttonClose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonCloseActionPerformed(evt);
            }
        });

        textfieldTotal.setCaretColor(new java.awt.Color(255, 255, 255));
        textfieldTotal.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        textfieldTotal.setFocusable(false);
        textfieldTotal.setFont(new java.awt.Font("DialogInput", 0, 24)); // NOI18N

        buttonPay.setText("Pay");
        buttonPay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonPayActionPerformed(evt);
            }
        });

        tblItems.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Item", "Qty", "Price"
            }
        ));
        tblItems.setGridColor(new java.awt.Color(255, 255, 255));
        jScrollPane2.setViewportView(tblItems);

        jToggleButton8.setText("Print Order");
        jToggleButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton8ActionPerformed(evt);
            }
        });

        lblWaiter.setText("Waiter:");

        lblNum.setText("No. of Customers:");

        lblClock1.setText("Clock");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 666, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 440, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Main Meals", jPanel6);

        jButton10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/_f_sandwhich.jpg"))); // NOI18N
        jButton10.setBorder(null);

        jButton9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/_f_salad.jpg"))); // NOI18N
        jButton9.setBorder(null);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jButton10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton9)
                .addGap(0, 494, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton9)
                    .addComponent(jButton10))
                .addGap(0, 357, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Light Meals", jPanel1);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 666, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 440, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Desserts", jPanel2);

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/_d_cokeLogo.png"))); // NOI18N
        jButton4.setBorder(null);
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/_d_cokeLightLogo.png"))); // NOI18N
        jButton14.setBorder(null);

        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/_d_fantaLogo.png"))); // NOI18N
        jButton5.setBorder(null);

        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/_d_spriteLogo.png"))); // NOI18N
        jButton6.setBorder(null);

        jButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/_d_liptonLogo.png"))); // NOI18N
        jButton7.setBorder(null);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jButton4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton14)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton7)
                .addGap(0, 223, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton7, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton4, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton5, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton6, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton14, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(0, 357, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Drinks", jPanel3);

        jTabbedPane2.setBackground(new java.awt.Color(204, 204, 204));
        jTabbedPane1.addTab("Extra", jTabbedPane2);

        buttonOverride.setText("Pay Override");
        buttonOverride.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonOverrideActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jToggleButton8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(buttonEdit, javax.swing.GroupLayout.DEFAULT_SIZE, 166, Short.MAX_VALUE)
                            .addComponent(buttonClose, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(buttonPay, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 166, Short.MAX_VALUE)
                            .addComponent(textfieldTotal, javax.swing.GroupLayout.DEFAULT_SIZE, 166, Short.MAX_VALUE)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(buttonOverride, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblTitle)
                                    .addComponent(lblClock1)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(lblWaiter)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(nameTF, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(lblNum)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(customerNo, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addGap(12, 12, 12)
                        .addComponent(jTabbedPane1)
                        .addGap(22, 22, 22)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblTitle)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(textfieldTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lblWaiter, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(nameTF, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(customerNo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblNum, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblClock1)
                        .addGap(47, 47, 47)
                        .addComponent(buttonPay)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonOverride)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonEdit)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonClose)
                        .addGap(27, 27, 27))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jTabbedPane1)
                        .addGap(6, 6, 6)))
                .addComponent(jToggleButton8)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jToggleButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton8ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jToggleButton8ActionPerformed

    private void buttonCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonCloseActionPerformed
        interrupTime();
        super.setVisible(false);
    }//GEN-LAST:event_buttonCloseActionPerformed

    private void buttonPayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonPayActionPerformed
        Checkout s = new Checkout();
        s.setVisible(true);
    }//GEN-LAST:event_buttonPayActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed

    }//GEN-LAST:event_jButton4ActionPerformed

    private void buttonOverrideActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonOverrideActionPerformed
        interrupTime();
    }//GEN-LAST:event_buttonOverrideActionPerformed

    private void buttonEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonEditActionPerformed
        voidItem();
    }//GEN-LAST:event_buttonEditActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonClose;
    private javax.swing.JButton buttonEdit;
    private javax.swing.JButton buttonOverride;
    private javax.swing.JButton buttonPay;
    private javax.swing.JLabel customerNo;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JToggleButton jToggleButton8;
    private javax.swing.JLabel lblClock1;
    private javax.swing.JLabel lblNum;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JLabel lblWaiter;
    private static javax.swing.JLabel nameTF;
    public static javax.swing.JTable tblItems;
    public static javax.swing.JFormattedTextField textfieldTotal;
    // End of variables declaration//GEN-END:variables
}
