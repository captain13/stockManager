/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package system;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author GGPQ9ZJ42
 */
public final class NewOrder extends javax.swing.JFrame implements ActionListener {

    int emptySpaceTab1 = 43;
    int emptySpaceTab2 = 43;
    int emptySpaceTab3 = 43;
    int emptySpaceTab4 = 43;
    int emptySpaceTab5 = 43;
    int emptySpaceTab6 = 43;
    int index1 = 0;
    int index2 = 0;
    int index3 = 0;
    int index4 = 0;
    int index5 = 0;
    int index6 = 0;
    Thread time;
    JButton button;
    String columnNames[] = {"Item", "Qty", "Price", "Check"};
    DefaultTableModel model = new DefaultTableModel(columnNames, 0);
    boolean isRunning = false;
    Object[][] recipeInfo;
    dbManager newManager = new dbManager();
    networkHandler network = new networkHandler();
    String key;
    HashMap tables;
    int n = 0;

    public NewOrder(String waiterID, String customerNum, String key, HashMap tables, Color color) {
        initComponents();
        getScreenResolution();
        this.setLocation(0, 0);
        this.key = key;
        this.tables = tables;
        setButtonColor(color);
        setTableLayout();
        internalClock();
        nameTF.setText(waiterID);
        customerNo.setText(customerNum);
        menuLayout();
        startTime();
    }

    public void menuLayout() {
        int n = newManager.getRecipeData().length;
        recipeInfo = new Object[n][6];
        int index = 0;
        for (int i = 0; i < n; i++) {
            System.arraycopy(newManager.getRecipeData()[i], 0, recipeInfo[i], 0, 6);
        }

        for (int i = 0; i < n; i++) {
            if (recipeInfo[i][2].equals("Main Meal")) {
                ImageIcon icon = (new ImageIcon(recipeInfo[i][5].toString()));
                button = new JButton(recipeInfo[i][1].toString(), icon);
                button.setVerticalTextPosition(SwingConstants.BOTTOM);
                button.setHorizontalTextPosition(SwingConstants.CENTER);
                button.setPreferredSize(new Dimension(50, 50));
                button.setMargin(new Insets(0, 0, 0, 0));
                button.setContentAreaFilled(false);
                button.setFocusPainted(false);
                button.addActionListener(this);
                mainMealTab.add(button);
                ++index1;
            }

            emptySpaceTab1 = emptySpaceTab1 - index1;
            index1 = 0;

            if (recipeInfo[i][2].equals("Light Meal")) {
                ImageIcon icon = (new ImageIcon(recipeInfo[i][5].toString()));
                button = new JButton(recipeInfo[i][1].toString(), icon);
                button.setVerticalTextPosition(SwingConstants.BOTTOM);
                button.setHorizontalTextPosition(SwingConstants.CENTER);
                button.setMargin(new Insets(0, 0, 0, 0));
                button.setContentAreaFilled(false);
                button.setFocusPainted(false);
                button.setBorder(new EmptyBorder(0, 0, 0, 0));
                button.addActionListener(this);
                lightMealTab.add(button);
                index2++;
            }
            emptySpaceTab2 = emptySpaceTab2 - index2;
            index2 = 0;

            if (recipeInfo[i][2].equals("Dessert")) {
                ImageIcon icon = (new ImageIcon(recipeInfo[i][5].toString()));
                button = new JButton(recipeInfo[i][1].toString(), icon);
                button.setVerticalTextPosition(SwingConstants.BOTTOM);
                button.setHorizontalTextPosition(SwingConstants.CENTER);
                button.setMargin(new Insets(0, 0, 0, 0));
                button.setContentAreaFilled(false);
                button.setFocusPainted(false);
                button.setBorder(new EmptyBorder(0, 0, 0, 0));
                button.addActionListener(this);
                dessertTab.add(button);
                index3++;
            }
            emptySpaceTab3 = emptySpaceTab3 - index3;
            index3 = 0;

            if (recipeInfo[i][2].equals("Drinks")) {
                ImageIcon icon = (new ImageIcon(recipeInfo[i][5].toString()));
                button = new JButton(recipeInfo[i][1].toString(), icon);
                button.setVerticalTextPosition(SwingConstants.BOTTOM);
                button.setHorizontalTextPosition(SwingConstants.CENTER);
                button.setMargin(new Insets(0, 0, 0, 0));
                button.setContentAreaFilled(false);
                button.setFocusPainted(false);
                button.setBorder(new EmptyBorder(0, 0, 0, 0));
                button.addActionListener(this);
                drinksTab.add(button);
                index4++;
            }
            emptySpaceTab4 = emptySpaceTab4 - index4;
            index4 = 0;

            if (recipeInfo[i][2].equals("Extra")) {
                ImageIcon icon = (new ImageIcon(recipeInfo[i][5].toString()));
                button = new JButton(recipeInfo[i][1].toString(), icon);
                button.setVerticalTextPosition(SwingConstants.BOTTOM);
                button.setHorizontalTextPosition(SwingConstants.CENTER);
                button.setMargin(new Insets(0, 0, 0, 0));
                button.setContentAreaFilled(false);
                button.setFocusPainted(false);
                button.setBorder(new EmptyBorder(0, 0, 0, 0));
                button.addActionListener(this);
                extraTab.add(button);
                index5++;
            }
            emptySpaceTab5 = emptySpaceTab5 - index5;
            index5 = 0;
        }

        for (int i = 1; i < emptySpaceTab1; i++) {
            JButton emptyButton = new JButton();
            mainMealTab.add(emptyButton);
            emptyButton.setVisible(false);
        }
        for (int i = 1; i < emptySpaceTab2; i++) {
            JButton emptyButton = new JButton();
            lightMealTab.add(emptyButton);
            emptyButton.setVisible(false);
        }
        for (int i = 1; i < emptySpaceTab3; i++) {
            JButton emptyButton = new JButton();
            dessertTab.add(emptyButton);
            emptyButton.setVisible(false);
        }

        for (int i = 1; i < emptySpaceTab4; i++) {
            JButton emptyButton = new JButton();
            drinksTab.add(emptyButton);
            emptyButton.setVisible(false);
        }

        for (int i = 1; i < emptySpaceTab5; i++) {
            JButton emptyButton = new JButton();
            extraTab.add(emptyButton);
            emptyButton.setVisible(false);
        }

    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        String buttonId = ae.getActionCommand();
        int recipeID = newManager.getRecipeID(buttonId);
        for (Object[] recipeInfo1 : recipeInfo) {
            if (buttonId.equals(recipeInfo1[1].toString())) {
                boolean check = false;
                if (!newManager.getRecipeData()[recipeID - 1][7].equals(0)&&newManager.getSpecialData()[(newManager.getSpecialsID(buttonId)-1)][4].equals(true)) {
                    model.addRow(new Object[]{recipeInfo1[1], "1", newManager.getSpecialsPrice(buttonId), check});
                } else {
                    model.addRow(new Object[]{recipeInfo1[1], "1", recipeInfo1[3], check});
                }
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

    public void internalClock() {
        time = new Thread(() -> {
            try {
                while (isRunning == true) {
                    lblClock1.setText(new SimpleDateFormat("HH:mm:ss").format(new Date()));
                }
            } catch (Exception e) {
            }
        });
    }

    public Double getTotal() {
        return Double.parseDouble(textfieldTotal.getText().replaceAll("R", ""));
    }

    public static String getWaiter() {
        return nameTF.getText();
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
        if (customerNo.getText() != null) {
            if (Integer.parseInt(customerNo.getText()) >= 10) {
                total = total * 1.1;
            }
        }
        textfieldTotal.setText(String.format("R%.2f", total));
    }

    public void voidItem() {
        int confirm = JOptionPane.showConfirmDialog(null, "Are you sure?", null, JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            try {
                if (tblItems.getSelectedRow() == -1) {
                    JOptionPane.showMessageDialog(null, "No Item Selected");
                } else {
                    model.removeRow(tblItems.getSelectedRow());
                    totalAmount();
                }
            } catch (ArrayIndexOutOfBoundsException exp) {
            }
        }
    }

    public void updateOrdercount() {
        int n = tblItems.getRowCount();
        for (int i = 0; i < n; i++) {
            System.out.println("iterate " + i);
            System.out.println("Count " + n);
            String order = tblItems.getValueAt(i, 0).toString();
            System.out.println(order);
            newManager.updateOrderCount(order);
        }
    }

    public void setTableLayout() {
        tblItems.setModel(model);
        tblItems.getColumnModel().getColumn(0).setPreferredWidth(90);
        tblItems.getColumnModel().getColumn(1).setPreferredWidth(25);
        tblItems.getColumnModel().getColumn(2).setPreferredWidth(45);
        tblItems.getColumnModel().getColumn(3).setPreferredWidth(25);
    }

    public void setButtonColor(Color color) {
        buttonPay.setBackground(color);
        buttonOverride.setBackground(color);
        buttonEdit1.setBackground(color);
        buttonEdit.setBackground(color);
        buttonClose.setBackground(color);
    }

    public void addEdit() {
        Object value = JOptionPane.showInputDialog(null, "Enter message");
        if (!value.equals("")) {
            int row = tblItems.getSelectedRow();
            model.addRow(new Object[]{"- " + value, "", "0"});
        } else {
            JOptionPane.showConfirmDialog(rootPane, value);
        }
    }

    public void getTableOrder() {
        Object order[][] = new Object[tblItems.getRowCount()][3];
        for (int i = 0; i < tblItems.getRowCount(); i++) {
            if (tblItems.getValueAt(i, 3).equals(false)) {
                order[i][0] = key;
                order[i][1] = tblItems.getValueAt(i, 0);
                order[i][2] = tblItems.getValueAt(i, 1);
                n = ++n;
            }
            tblItems.setValueAt(true, i, 3);
        }
        network.sendData(order);
    }

    public void closeTable() {
        tables.remove(key);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel4 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        lblTitle = new javax.swing.JLabel();
        textfieldTotal = new javax.swing.JFormattedTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblItems = new JTable() {
            @Override
            public Class getColumnClass(int column) {
                switch (column) {
                    case 0:
                    return String.class;
                    case 1:
                    return String.class;
                    case 2:
                    return String.class;
                    case 3:
                    return Boolean.class;
                    default:
                    return String.class;
                }
            }
        };
        lblWaiter = new javax.swing.JLabel();
        lblNum = new javax.swing.JLabel();
        nameTF = new javax.swing.JLabel();
        customerNo = new javax.swing.JLabel();
        lblClock1 = new javax.swing.JLabel();
        buttonPay = new javax.swing.JButton();
        buttonOverride = new javax.swing.JButton();
        buttonEdit1 = new javax.swing.JButton();
        buttonEdit = new javax.swing.JButton();
        buttonClose = new javax.swing.JButton();
        menuPane = new javax.swing.JTabbedPane();
        mainMealTab = new javax.swing.JPanel();
        lightMealTab = new javax.swing.JPanel();
        dessertTab = new javax.swing.JPanel();
        drinksTab = new javax.swing.JPanel();
        extraTab = new javax.swing.JPanel();
        jToggleButton8 = new javax.swing.JToggleButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setSize(new java.awt.Dimension(700, 768));

        jPanel3.setBackground(new java.awt.Color(53, 53, 53));

        lblTitle.setForeground(new java.awt.Color(255, 255, 255));
        lblTitle.setText("Order");

        textfieldTotal.setCaretColor(new java.awt.Color(255, 255, 255));
        textfieldTotal.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        textfieldTotal.setFocusable(false);
        textfieldTotal.setFont(new java.awt.Font("DialogInput", 0, 24)); // NOI18N

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

        lblWaiter.setForeground(new java.awt.Color(255, 255, 255));
        lblWaiter.setText("Waiter:");

        lblNum.setForeground(new java.awt.Color(255, 255, 255));
        lblNum.setText("No. of Customers:");

        nameTF.setForeground(new java.awt.Color(255, 255, 255));

        customerNo.setForeground(new java.awt.Color(255, 255, 255));

        lblClock1.setForeground(new java.awt.Color(255, 255, 255));
        lblClock1.setText("Clock");

        buttonPay.setBackground(new java.awt.Color(0, 138, 231));
        buttonPay.setForeground(new java.awt.Color(255, 255, 255));
        buttonPay.setText("Pay");
        buttonPay.setToolTipText("Test");
        buttonPay.setContentAreaFilled(false);
        buttonPay.setOpaque(true);
        buttonPay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonPayActionPerformed(evt);
            }
        });

        buttonOverride.setBackground(new java.awt.Color(0, 138, 231));
        buttonOverride.setForeground(new java.awt.Color(255, 255, 255));
        buttonOverride.setText("Send Order");
        buttonOverride.setContentAreaFilled(false);
        buttonOverride.setOpaque(true);
        buttonOverride.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonOverrideActionPerformed(evt);
            }
        });

        buttonEdit1.setBackground(new java.awt.Color(0, 138, 231));
        buttonEdit1.setForeground(new java.awt.Color(255, 255, 255));
        buttonEdit1.setText("Order Instructions");
        buttonEdit1.setContentAreaFilled(false);
        buttonEdit1.setOpaque(true);
        buttonEdit1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonEdit1ActionPerformed(evt);
            }
        });

        buttonEdit.setBackground(new java.awt.Color(0, 138, 231));
        buttonEdit.setForeground(new java.awt.Color(255, 255, 255));
        buttonEdit.setText("Remove Order");
        buttonEdit.setContentAreaFilled(false);
        buttonEdit.setOpaque(true);
        buttonEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonEditActionPerformed(evt);
            }
        });

        buttonClose.setBackground(new java.awt.Color(0, 138, 231));
        buttonClose.setForeground(new java.awt.Color(255, 255, 255));
        buttonClose.setText("Return");
        buttonClose.setToolTipText("");
        buttonClose.setContentAreaFilled(false);
        buttonClose.setOpaque(true);
        buttonClose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonCloseActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(buttonEdit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(buttonClose, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(buttonOverride, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(buttonEdit1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(2, 2, 2))
                    .addComponent(buttonPay, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblClock1)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(lblWaiter)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(nameTF, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(lblNum)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(customerNo, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(lblTitle))
                        .addGap(0, 112, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel3Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(textfieldTotal, javax.swing.GroupLayout.DEFAULT_SIZE, 240, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTitle)
                .addGap(74, 74, 74)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblWaiter, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(nameTF, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(customerNo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblNum))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblClock1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(buttonPay)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(buttonOverride)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(buttonEdit1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(buttonEdit)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(buttonClose)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel3Layout.createSequentialGroup()
                    .addGap(31, 31, 31)
                    .addComponent(textfieldTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(471, Short.MAX_VALUE)))
        );

        mainMealTab.setBackground(new java.awt.Color(255, 255, 255));
        mainMealTab.setLayout(new java.awt.GridLayout(0, 7));
        menuPane.addTab("Main Meals", mainMealTab);

        lightMealTab.setBackground(new java.awt.Color(255, 255, 255));
        lightMealTab.setLayout(new java.awt.GridLayout(0, 7));
        menuPane.addTab("Light Meals", lightMealTab);

        dessertTab.setBackground(new java.awt.Color(255, 255, 255));
        dessertTab.setLayout(new java.awt.GridLayout(0, 7));
        menuPane.addTab("Desserts", dessertTab);

        drinksTab.setBackground(new java.awt.Color(255, 255, 255));
        drinksTab.setLayout(new java.awt.GridLayout(0, 7));
        menuPane.addTab("Drinks", drinksTab);

        extraTab.setBackground(new java.awt.Color(255, 255, 255));
        extraTab.setLayout(new java.awt.GridLayout(0, 7));
        menuPane.addTab("Extras", extraTab);

        jToggleButton8.setBackground(new java.awt.Color(75, 75, 75));
        jToggleButton8.setForeground(new java.awt.Color(255, 255, 255));
        jToggleButton8.setText("Close Table");
        jToggleButton8.setContentAreaFilled(false);
        jToggleButton8.setOpaque(true);
        jToggleButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton8ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jToggleButton8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(menuPane))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(menuPane)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jToggleButton8)
                .addContainerGap())
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void buttonCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonCloseActionPerformed
        interrupTime();
        super.setVisible(false);
    }//GEN-LAST:event_buttonCloseActionPerformed

    private void buttonPayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonPayActionPerformed
        if ("".equals(textfieldTotal.getText())) {
            JOptionPane.showMessageDialog(null, "Nothing in Till");
        } else {
            Checkout checkout = new Checkout(getWaiter(), getTotal());
            checkout.setVisible(true);
            newManager.recipeStockUpdate(tblItems, key);
            updateOrdercount();
        }
    }//GEN-LAST:event_buttonPayActionPerformed

    private void buttonOverrideActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonOverrideActionPerformed
        getTableOrder();
    }//GEN-LAST:event_buttonOverrideActionPerformed

    private void buttonEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonEditActionPerformed
        voidItem();
    }//GEN-LAST:event_buttonEditActionPerformed

    private void buttonEdit1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonEdit1ActionPerformed
        addEdit();
    }//GEN-LAST:event_buttonEdit1ActionPerformed

    private void jToggleButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton8ActionPerformed
        closeTable();
        this.dispose();
    }//GEN-LAST:event_jToggleButton8ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonClose;
    private javax.swing.JButton buttonEdit;
    private javax.swing.JButton buttonEdit1;
    private javax.swing.JButton buttonOverride;
    private javax.swing.JButton buttonPay;
    private javax.swing.JLabel customerNo;
    private javax.swing.JPanel dessertTab;
    private javax.swing.JPanel drinksTab;
    private javax.swing.JPanel extraTab;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JToggleButton jToggleButton8;
    private javax.swing.JLabel lblClock1;
    private javax.swing.JLabel lblNum;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JLabel lblWaiter;
    private javax.swing.JPanel lightMealTab;
    private javax.swing.JPanel mainMealTab;
    private javax.swing.JTabbedPane menuPane;
    private static javax.swing.JLabel nameTF;
    public static javax.swing.JTable tblItems;
    public static javax.swing.JFormattedTextField textfieldTotal;
    // End of variables declaration//GEN-END:variables
}
