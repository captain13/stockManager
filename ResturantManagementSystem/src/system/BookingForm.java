package system;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Andrew
 */
public class BookingForm extends javax.swing.JFrame {

    dbManager newManager = new dbManager();
    internalClock clock = new internalClock();
    settingsManager settings = new settingsManager();
    Keyboard k;
    MainSystem system;

    public BookingForm(MainSystem system, boolean enabled) {
        initComponents();
        populateReservationTable();
        setCurrentDate();
        setEmployeeID();
        setTableNum();
        super.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.system = system;
        isKeypadEnable(enabled);
    }

    public final void isKeypadEnable(boolean enabled) {
        if (enabled == true) {
            k = new Keyboard();
            k.setVisible(true);
            k.setAlwaysOnTop(rootPaneCheckingEnabled);
        }
    }

    public final void populateReservationTable() {
        String columnNames[] = {"Employee", "Table No.", "Date", "Time", "Customer", "Contact Number"};
        DefaultTableModel tableModel = new DefaultTableModel(newManager.getReservationData(), columnNames);
        tableReservation.setModel(tableModel);
        tableReservation.getColumnModel().getColumn(0).setPreferredWidth(100);
        tableReservation.getColumnModel().getColumn(1).setPreferredWidth(100);
        tableReservation.getColumnModel().getColumn(2).setPreferredWidth(100);
        tableReservation.getColumnModel().getColumn(3).setPreferredWidth(75);
        tableReservation.getColumnModel().getColumn(4).setPreferredWidth(175);
        tableReservation.getColumnModel().getColumn(5).setPreferredWidth(150);
    }

    public String getCustomerName() {
        return textCustomerName.getText();
    }

    public String getCustomerContact() {
        return textCustomerContact.getText();
    }

    public String getEmployee() {
        return comboEmployee.getSelectedItem().toString();
    }

    public String getDate() {
        String date = comboboxYear.getSelectedItem().toString() + "-"
                + comboboxMonth.getSelectedItem().toString() + "-"
                + comboboxDay.getSelectedItem().toString();
        return date;
    }

    public int getTableNum() {
        return Integer.parseInt(comboTableNum.getSelectedItem().toString());
    }

    public int getCustomerNum() {
        return Integer.parseInt(comboCustomerNum.getSelectedItem().toString());
    }

    public String getTime() {
        String time = comboboxHour.getSelectedItem().toString()
                + ":" + comboboxMinute.getSelectedItem().toString();
        return time;
    }

    public final void setCurrentDate() {
        String currentDate = clock.getCurrentDate();
        String date[] = currentDate.split("-");
        String day = date[2];
        String month = date[1];
        String year = date[0];
        comboboxDay.setSelectedItem(day);
        comboboxMonth.setSelectedItem(month);
        comboboxYear.setSelectedItem(year);
    }

    public final void setEmployeeID() {
        int n = newManager.getEmployeeData().length;
        for (int i = 0; i < n; i++) {
            comboEmployee.addItem(newManager.getEmployeeData()[i][0].toString());
        }
    }

    public final void setTableNum() {
        int n = Integer.parseInt(settings.getTableCount());
        for (int i = 0; i < n; i++) {
            comboTableNum.addItem((i + 1) + "");
        }
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

    public void checkPredateYear() {
        String date[] = clock.getCurrentDate().split("-");
        int currentYear = Integer.parseInt(date[0]);
        int currentMonth = Integer.parseInt(date[1]);
        int currentDay = Integer.parseInt(date[2]);
        int month = Integer.parseInt(comboboxMonth.getSelectedItem().toString());
        int day = Integer.parseInt(comboboxDay.getSelectedItem().toString());
        int year = Integer.parseInt(comboboxYear.getSelectedItem().toString());

        if (currentYear > year) {
            JOptionPane.showMessageDialog(null, "Booking Date Past");
            setCurrentDate();
        }
    }

    public void checkPredateMonth() {
        String date[] = clock.getCurrentDate().split("-");
        int currentYear = Integer.parseInt(date[0]);
        int currentMonth = Integer.parseInt(date[1]);
        int currentDay = Integer.parseInt(date[2]);
        int month = Integer.parseInt(comboboxMonth.getSelectedItem().toString());
        int day = Integer.parseInt(comboboxDay.getSelectedItem().toString());
        int year = Integer.parseInt(comboboxYear.getSelectedItem().toString());

        if (currentYear >= year && currentMonth > month) {
            JOptionPane.showMessageDialog(null, "Booking Date Past");
            setCurrentDate();
        }
    }

    public void checkPredateDay() {
        String date[] = clock.getCurrentDate().split("-");
        int currentYear = Integer.parseInt(date[0]);
        int currentMonth = Integer.parseInt(date[1]);
        int currentDay = Integer.parseInt(date[2]);
        int month = Integer.parseInt(comboboxMonth.getSelectedItem().toString());
        int day = Integer.parseInt(comboboxDay.getSelectedItem().toString());
        int year = Integer.parseInt(comboboxYear.getSelectedItem().toString());

        if (currentYear >= year && currentMonth >= month && currentDay > day) {
            JOptionPane.showMessageDialog(null, "Booking Date Past");
            setCurrentDate();
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
        lblNumber = new javax.swing.JLabel();
        lblTitle = new javax.swing.JLabel();
        lblNumber3 = new javax.swing.JLabel();
        buttonCancel = new javax.swing.JButton();
        lblNumber4 = new javax.swing.JLabel();
        textCustomerName = new javax.swing.JTextField();
        comboboxMonth = new javax.swing.JComboBox<>();
        comboboxDay = new javax.swing.JComboBox<>();
        comboboxYear = new javax.swing.JComboBox<>();
        comboEmployee = new javax.swing.JComboBox<>();
        comboTableNum = new javax.swing.JComboBox<>();
        comboCustomerNum = new javax.swing.JComboBox<>();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        textCustomerContact = new javax.swing.JTextField();
        lblNumber5 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableReservation = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jTabbedPane1.setBackground(new java.awt.Color(255, 255, 255));
        jTabbedPane1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jTabbedPane1.setOpaque(true);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

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

        lblTime.setText("Time ");

        comboboxHour.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20" }));

        comboboxMinute.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "00", "15", "30", "45", " " }));

        lblNumber2.setText("Date");

        lblNumber1.setText("No. of customers");

        lblNumber.setText("Employee ID");

        lblTitle.setText("Bookings");

        lblNumber3.setText("Table No.");

        buttonCancel.setBackground(new java.awt.Color(75, 75, 75));
        buttonCancel.setForeground(new java.awt.Color(255, 255, 255));
        buttonCancel.setText("Close");
        buttonCancel.setContentAreaFilled(false);
        buttonCancel.setOpaque(true);
        buttonCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonCancelActionPerformed(evt);
            }
        });

        lblNumber4.setText("Customer Name");

        textCustomerName.setBackground(new java.awt.Color(240, 240, 240));
        textCustomerName.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                textCustomerNameMouseClicked(evt);
            }
        });
        textCustomerName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textCustomerNameActionPerformed(evt);
            }
        });

        comboboxMonth.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" }));
        comboboxMonth.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboboxMonthActionPerformed(evt);
            }
        });

        comboboxDay.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" }));
        comboboxDay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboboxDayActionPerformed(evt);
            }
        });

        comboboxYear.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "2016", "2017", "2018", "2019", "2020", "2021", "2022", "2023", "2024", "2025" }));
        comboboxYear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboboxYearActionPerformed(evt);
            }
        });

        comboEmployee.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select..." }));

        comboTableNum.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select..." }));

        comboCustomerNum.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select...", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15" }));

        textCustomerContact.setBackground(new java.awt.Color(240, 240, 240));
        textCustomerContact.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                textCustomerContactMouseClicked(evt);
            }
        });

        lblNumber5.setText("Customer Contact");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jSeparator1)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(69, 69, 69)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(lblNumber1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(comboCustomerNum, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblNumber2)
                            .addComponent(lblNumber)
                            .addComponent(lblNumber3)
                            .addComponent(lblTime)
                            .addComponent(lblNumber4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(textCustomerName, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                        .addComponent(comboboxHour, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(comboboxMinute, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(comboEmployee, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(comboTableNum, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(comboboxDay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(comboboxMonth, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(comboboxYear, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(25, 25, 25))))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addComponent(lblNumber5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(textCustomerContact, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(69, 69, 69))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lblTitle))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(115, 115, 115)
                        .addComponent(buttonAccept1, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(buttonCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(115, 115, 115))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jSeparator2)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(lblTitle)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(lblNumber)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(comboTableNum, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblNumber3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(comboCustomerNum, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblNumber1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(textCustomerName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblNumber4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(textCustomerContact, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblNumber5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(comboboxMonth, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(comboboxDay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(comboboxYear, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblNumber2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(comboboxHour, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(comboboxMinute, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblTime))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 43, Short.MAX_VALUE)
                        .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 8, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(buttonAccept1)
                            .addComponent(buttonCancel))
                        .addGap(21, 21, 21))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(comboEmployee, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        jTabbedPane1.addTab("Add Revervation", jPanel1);

        jScrollPane1.setBorder(null);

        tableReservation.setAutoCreateRowSorter(true);
        tableReservation.setModel(new javax.swing.table.DefaultTableModel(
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
        tableReservation.setEnabled(false);
        tableReservation.setGridColor(new java.awt.Color(204, 204, 204));
        jScrollPane1.setViewportView(tableReservation);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 438, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 359, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("View Reservation", jPanel2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 445, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 391, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void buttonAccept1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAccept1ActionPerformed
        if (comboEmployee.getSelectedIndex() != 0 && comboTableNum.getSelectedIndex() != 0 && comboCustomerNum.getSelectedIndex() != 0) {
            if (!"".equals(textCustomerName.getText()) && !"".equals(textCustomerContact.getText())) {
                newManager.insertReservations(getEmployee(), getDate(), getTime(), getCustomerName(), getTableNum(), getCustomerNum(), getCustomerContact());
                populateReservationTable();
                system.bookingAlert();
            } else {
                JOptionPane.showMessageDialog(null, "Pleased Enter All Text Fields");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Pleased Select All Fields");
        }
    }//GEN-LAST:event_buttonAccept1ActionPerformed

    private void buttonCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonCancelActionPerformed
        if (system.keypadCheck()) {
            k.dispose();
        }
        this.dispose();
    }//GEN-LAST:event_buttonCancelActionPerformed

    private void comboboxDayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboboxDayActionPerformed
        checkPredateDay();
    }//GEN-LAST:event_comboboxDayActionPerformed

    private void comboboxMonthActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboboxMonthActionPerformed
        checkPredateMonth();
        checkMonth();
    }//GEN-LAST:event_comboboxMonthActionPerformed

    private void comboboxYearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboboxYearActionPerformed
        checkPredateYear();
    }//GEN-LAST:event_comboboxYearActionPerformed

    private void textCustomerNameMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_textCustomerNameMouseClicked
        k.setTextfield(textCustomerName);
    }//GEN-LAST:event_textCustomerNameMouseClicked

    private void textCustomerContactMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_textCustomerContactMouseClicked
        k.setTextfield(textCustomerContact);
    }//GEN-LAST:event_textCustomerContactMouseClicked

    private void textCustomerNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textCustomerNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textCustomerNameActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonAccept1;
    private javax.swing.JButton buttonCancel;
    private javax.swing.JComboBox<String> comboCustomerNum;
    private javax.swing.JComboBox<String> comboEmployee;
    private javax.swing.JComboBox<String> comboTableNum;
    public static javax.swing.JComboBox<String> comboboxDay;
    public static javax.swing.JComboBox<String> comboboxHour;
    public static javax.swing.JComboBox<String> comboboxMinute;
    public static javax.swing.JComboBox<String> comboboxMonth;
    public static javax.swing.JComboBox<String> comboboxYear;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel lblNumber;
    private javax.swing.JLabel lblNumber1;
    private javax.swing.JLabel lblNumber2;
    private javax.swing.JLabel lblNumber3;
    private javax.swing.JLabel lblNumber4;
    private javax.swing.JLabel lblNumber5;
    private javax.swing.JLabel lblTime;
    private javax.swing.JLabel lblTitle;
    public static javax.swing.JTable tableReservation;
    public static javax.swing.JTextField textCustomerContact;
    public static javax.swing.JTextField textCustomerName;
    // End of variables declaration//GEN-END:variables
}
