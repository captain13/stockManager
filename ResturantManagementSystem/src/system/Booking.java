package system;

/**
 *
 * @author Andrew
 */
public class Booking extends javax.swing.JFrame {

    private boolean IS_RUNNING = false;
    dbManager newManager = new dbManager();
    internalClock clock = new internalClock();

    public Booking() {
        if (IS_RUNNING) {
            throw new RuntimeException();
        } else {
            IS_RUNNING = true;
        }
        initComponents();
        newManager.populateReservation();
        setCurrentDate();
        super.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    @Override
    public void dispose() {
        IS_RUNNING = false;
        super.dispose();
    }

    public String getCustomerName() {
        return textCustomerName.getText();
    }

    public String getEmployee() {
        return textEmp.getText();
    }

    public String getDate() {
        String date = comboboxDay.getSelectedItem().toString() + "/"
                + comboboxMonth.getSelectedItem().toString() + "/"
                + comboboxYear.getSelectedItem().toString();
        return date;
    }

    public String getTableNum() {
        return textTable.getText();
    }

    public String getCustomerNum() {
        return textCustomerNum.getText();
    }

    public String getTime() {
        String time = comboboxHour.getSelectedItem().toString()
                + ":" + comboboxMinute.getSelectedItem().toString();
        return time;
    }

    public final void setCurrentDate() {
        String currentDate = clock.getCurrentDate();
        String date[] = currentDate.split("-");
        String day = date[0];
        String month = date[1];
        comboboxDay.setSelectedItem(day);
        comboboxMonth.setSelectedItem(month);
    }

    public void checkMonth() {
        if (comboboxMonth.getSelectedItem() == "02") {
            comboboxDay.removeItem("31");
            comboboxDay.removeItem("30");
            comboboxDay.removeItem("29");
        }
        if (comboboxMonth.getSelectedItem() == "01" || comboboxMonth.getSelectedItem() == "03"
                || comboboxMonth.getSelectedItem() == "05" || comboboxMonth.getSelectedItem() == "07"
                || comboboxMonth.getSelectedItem() == "08" || comboboxMonth.getSelectedItem() == "10"
                || comboboxMonth.getSelectedItem() == "12") {
            comboboxDay.removeItem("31");
            comboboxDay.removeItem("30");
            comboboxDay.removeItem("29");
            comboboxDay.addItem("29");
            comboboxDay.addItem("30");
            comboboxDay.addItem("31");
            if (comboboxDay.getSelectedItem() == "28" || comboboxDay.getSelectedItem() == "30") {
                comboboxDay.setSelectedItem("31");
            }
        }
        if (comboboxMonth.getSelectedItem() == "04" || comboboxMonth.getSelectedItem() == "06"
                || comboboxMonth.getSelectedItem() == "09" || comboboxMonth.getSelectedItem() == "11") {
            comboboxDay.removeItem("31");
            comboboxDay.removeItem("30");
            comboboxDay.removeItem("29");
            comboboxDay.addItem("29");
            comboboxDay.addItem("30");
            if (comboboxDay.getSelectedItem() == "28" || comboboxDay.getSelectedItem() == "31") {
                comboboxDay.setSelectedItem("30");
            }
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

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        buttonAccept1 = new javax.swing.JButton();
        lblTime = new javax.swing.JLabel();
        comboboxHour = new javax.swing.JComboBox<>();
        comboboxMinute = new javax.swing.JComboBox<>();
        lblNumber2 = new javax.swing.JLabel();
        lblNumber1 = new javax.swing.JLabel();
        textCustomerNum = new javax.swing.JTextField();
        textEmp = new javax.swing.JTextField();
        lblNumber = new javax.swing.JLabel();
        lblTitle = new javax.swing.JLabel();
        lblNumber3 = new javax.swing.JLabel();
        textTable = new javax.swing.JTextField();
        buttonCancel = new javax.swing.JButton();
        lblNumber4 = new javax.swing.JLabel();
        textCustomerName = new javax.swing.JTextField();
        comboboxMonth = new javax.swing.JComboBox<>();
        comboboxDay = new javax.swing.JComboBox<>();
        comboboxYear = new javax.swing.JComboBox<>();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableRes = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        buttonAccept1.setText("Accept");
        buttonAccept1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonAccept1ActionPerformed(evt);
            }
        });

        lblTime.setText("Time ");

        comboboxHour.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20" }));

        comboboxMinute.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "00", "15", "30", "45", " " }));

        lblNumber2.setText("Date");

        lblNumber1.setText("No. of customers");

        textCustomerNum.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textCustomerNumActionPerformed(evt);
            }
        });

        textEmp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textEmpActionPerformed(evt);
            }
        });

        lblNumber.setText("Employee ID");

        lblTitle.setText("Bookings");

        lblNumber3.setText("Table No.");

        textTable.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textTableActionPerformed(evt);
            }
        });

        buttonCancel.setText("Cancel");
        buttonCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonCancelActionPerformed(evt);
            }
        });

        lblNumber4.setText("Customer Name");

        textCustomerName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textCustomerNameActionPerformed(evt);
            }
        });

        comboboxMonth.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" }));

        comboboxDay.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" }));

        comboboxYear.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "2017", "2018", "2019", "2020", "2021", "2022", "2023", "2024", "2025" }));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(51, 51, 51)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblNumber1)
                            .addComponent(lblTime)
                            .addComponent(lblNumber2))
                        .addGap(33, 33, 33)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(comboboxHour, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(comboboxMinute, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(textCustomerNum, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(comboboxDay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(comboboxMonth, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(comboboxYear, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(lblNumber4)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(textCustomerName, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(lblNumber)
                                .addComponent(lblNumber3))
                            .addGap(55, 55, 55)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(textTable, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(textEmp, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(17, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(buttonAccept1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(buttonCancel)
                .addGap(88, 88, 88))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(21, 21, 21)
                    .addComponent(lblTitle)
                    .addContainerGap(257, Short.MAX_VALUE)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNumber)
                    .addComponent(textEmp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNumber3)
                    .addComponent(textTable, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNumber4)
                    .addComponent(textCustomerName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textCustomerNum, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblNumber1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNumber2)
                    .addComponent(comboboxMonth, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(comboboxDay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(comboboxYear, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(comboboxHour, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(comboboxMinute, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTime))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonAccept1)
                    .addComponent(buttonCancel))
                .addContainerGap(34, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(lblTitle)
                    .addContainerGap(242, Short.MAX_VALUE)))
        );

        jTabbedPane1.addTab("Add Revervation", jPanel1);

        tableRes.setAutoCreateRowSorter(true);
        tableRes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Employee ID", "Date", "Table No.", "No.of People"
            }
        ));
        tableRes.setGridColor(new java.awt.Color(204, 204, 204));
        jScrollPane1.setViewportView(tableRes);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 303, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(112, 112, 112))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 25, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("View", jPanel2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 329, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void textEmpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textEmpActionPerformed
//        Checkout s = new Checkout();
//        s.setLocation(350, 530);
//        s.setVisible(true);
    }//GEN-LAST:event_textEmpActionPerformed

    private void buttonAccept1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAccept1ActionPerformed
        newManager.insertReservations(getEmployee(), getDate(), getTime(), getCustomerName(), getTableNum(), getCustomerNum());
    }//GEN-LAST:event_buttonAccept1ActionPerformed

    private void buttonCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonCancelActionPerformed
        this.dispose();
    }//GEN-LAST:event_buttonCancelActionPerformed

    private void textCustomerNumActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textCustomerNumActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textCustomerNumActionPerformed

    private void textCustomerNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textCustomerNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textCustomerNameActionPerformed

    private void textTableActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textTableActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textTableActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonAccept1;
    private javax.swing.JButton buttonCancel;
    public static javax.swing.JComboBox<String> comboboxDay;
    public static javax.swing.JComboBox<String> comboboxHour;
    public static javax.swing.JComboBox<String> comboboxMinute;
    public static javax.swing.JComboBox<String> comboboxMonth;
    public static javax.swing.JComboBox<String> comboboxYear;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel lblNumber;
    private javax.swing.JLabel lblNumber1;
    private javax.swing.JLabel lblNumber2;
    private javax.swing.JLabel lblNumber3;
    private javax.swing.JLabel lblNumber4;
    private javax.swing.JLabel lblTime;
    private javax.swing.JLabel lblTitle;
    public static javax.swing.JTable tableRes;
    public static javax.swing.JTextField textCustomerName;
    public static javax.swing.JTextField textCustomerNum;
    public static javax.swing.JTextField textEmp;
    public static javax.swing.JTextField textTable;
    // End of variables declaration//GEN-END:variables
}
