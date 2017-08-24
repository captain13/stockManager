package system;

import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author GGPQ9ZJ42
 */
public class MainSystem extends javax.swing.JFrame implements ActionListener {

    Booking booking = new Booking();
    Calendar calendar = new Calendar();
    Specials specials = new Specials();
    userManager user = new userManager();
    xmlManager settings = new xmlManager();
    dbManager system = new dbManager();
    logSystem logs = new logSystem();
    internalClock clock = new internalClock();
    HashMap<String, NewOrder> tables = new HashMap<>();
    boolean enableKeypad = false;
    int n;

    public MainSystem() {
        initComponents();
        system.dbValidation();
        settings.xmlValidition();
        logs.logValidation();
        clock.internalClock();
        system.populateTables();
        system.populateOrder();
        getSetting();
//        setTableDesign();
    }

    public final void setTableDesign() {
        tblSuplier.getColumnModel().getColumn(0).setPreferredWidth(100);
        tblSuplier.getColumnModel().getColumn(1).setPreferredWidth(300);
        tblSuplier.getColumnModel().getColumn(2).setPreferredWidth(100);
        tblSuplier.getColumnModel().getColumn(3).setPreferredWidth(100);
        tblSuplier.getColumnModel().getColumn(4).setPreferredWidth(100);

        tableInventory.getColumnModel().getColumn(0).setPreferredWidth(50);
        tableInventory.getColumnModel().getColumn(1).setPreferredWidth(300);
        tableInventory.getColumnModel().getColumn(2).setPreferredWidth(150);
        tableInventory.getColumnModel().getColumn(3).setPreferredWidth(50);
        tableInventory.getColumnModel().getColumn(3).setPreferredWidth(50);

        tableRecipeList.getColumnModel().getColumn(0).setPreferredWidth(25);
        tableRecipeList.getColumnModel().getColumn(1).setPreferredWidth(200);
        tableRecipeList.getColumnModel().getColumn(2).setPreferredWidth(25);
        tableRecipeList.getColumnModel().getColumn(3).setPreferredWidth(200);
        tableRecipeList.getColumnModel().getColumn(4).setPreferredWidth(25);

        tblRecipe.getColumnModel().getColumn(0).setPreferredWidth(100);
        tblRecipe.getColumnModel().getColumn(1).setPreferredWidth(300);
        tblRecipe.getColumnModel().getColumn(2).setPreferredWidth(300);
        tblRecipe.getColumnModel().getColumn(3).setPreferredWidth(200);
        tblRecipe.getColumnModel().getColumn(4).setPreferredWidth(250);
    }

    public final void tableLayout() {
        pnlLayout.removeAll();
        JButton button;
        GridLayout tableLayout = new GridLayout(0, 4);
        pnlLayout.setLayout(tableLayout);
        int emptySpace = 20 - n;
        for (int i = 1; i <= n; i++) {
            button = new JButton("Table " + i);
            button.setMargin(new Insets(0, 0, 0, 0));
            button.setBorder(new EmptyBorder(0, 0, 0, 0));
            button.addActionListener(this);
            pnlLayout.add(button);
        }
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        String buttonId = ae.getActionCommand();
        if (tables.get(buttonId) == null) {
            // String waiter =user.createUserLog();
            String waiter = JOptionPane.showInputDialog(null, "Enter Waiter ID");
            String customer = JOptionPane.showInputDialog(null, "Enter Number of Customers");
            if (!"".equals(waiter) && !"".equals(customer)) {
                tables.put(buttonId, new NewOrder(waiter, customer, buttonId, tables));
                tables.get(buttonId).setVisible(true);
            }
        } else {
            tables.get(buttonId).setVisible(true);
        }
    }

    public static void searchITable() {
        if (tblSuplier.getRowCount() != 1) {
            tblSuplier.setRowSorter(null);

        } else {
            try {
                TableRowSorter<TableModel> rowSorter = new TableRowSorter<>(tblSuplier.getModel());
                tblSuplier.setRowSorter(rowSorter);
                textboxSearch.getDocument().addDocumentListener(new DocumentListener() {
                    @Override
                    public void insertUpdate(DocumentEvent e) {
                        String text = textboxSearch.getText();
                        if (text.trim().length() == 0) {
                            rowSorter.setRowFilter(null);
                        } else {
                            rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
                        }
                    }

                    @Override
                    public void removeUpdate(DocumentEvent e) {
                        String text = textboxSearch.getText();
                        if (text.trim().length() == 0) {
                            rowSorter.setRowFilter(null);
                        } else {
                            rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
                        }
                    }

                    @Override
                    public void changedUpdate(DocumentEvent e) {
                        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                    }
                });
            } catch (Exception ex) {
            }
        }
    }

    public static void searchRTable() {
        if (tableInventory.getRowCount() != 1) {
            tableInventory.setRowSorter(null);

        } else {
            try {
                TableRowSorter<TableModel> rowSorterRecipe = new TableRowSorter<>(tableInventory.getModel());
                tableInventory.setRowSorter(rowSorterRecipe);

                ArrayList<RowSorter.SortKey> sortKeys = new ArrayList<>(25);
                sortKeys.add(new RowSorter.SortKey(2, SortOrder.DESCENDING));
                sortKeys.add(new RowSorter.SortKey(1, SortOrder.ASCENDING));
                rowSorterRecipe.setSortKeys(sortKeys);

                textboxSearch.getDocument().addDocumentListener(new DocumentListener() {
                    @Override
                    public void insertUpdate(DocumentEvent e) {
                        String text = textboxSearch.getText();
                        if (text.trim().length() == 0) {
                            rowSorterRecipe.setRowFilter(null);
                        } else {
                            rowSorterRecipe.setRowFilter(RowFilter.regexFilter("(?i)" + text));
                        }
                    }

                    @Override
                    public void removeUpdate(DocumentEvent e) {
                        String text = textboxSearch.getText();
                        if (text.trim().length() == 0) {
                            rowSorterRecipe.setRowFilter(null);
                        } else {
                            rowSorterRecipe.setRowFilter(RowFilter.regexFilter("(?i)" + text));
                        }
                    }

                    @Override
                    public void changedUpdate(DocumentEvent e) {
                        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                    }
                });
            } catch (Exception ex) {
            }
        }
    }

    public static void searchSTable() {
        if (tblRecipe.getRowCount() != 1) {
            tblRecipe.setRowSorter(null);

        } else {
            try {
                TableRowSorter<TableModel> rowSorterSup = new TableRowSorter<>(tblRecipe.getModel());
                tblRecipe.setRowSorter(rowSorterSup);
                textboxSearch.getDocument().addDocumentListener(new DocumentListener() {
                    @Override
                    public void insertUpdate(DocumentEvent e) {
                        String text = textboxSearch.getText();
                        if (text.trim().length() == 0) {
                            rowSorterSup.setRowFilter(null);
                        } else {
                            rowSorterSup.setRowFilter(RowFilter.regexFilter("(?i)" + text));
                        }
                    }

                    @Override
                    public void removeUpdate(DocumentEvent e) {
                        String text = textboxSearch.getText();

                        if (text.trim().length() == 0) {
                            rowSorterSup.setRowFilter(null);
                        } else {
                            rowSorterSup.setRowFilter(RowFilter.regexFilter("(?i)" + text));
                        }
                    }

                    @Override
                    public void changedUpdate(DocumentEvent e) {
                        throw new UnsupportedOperationException("Not supported yet.");
                    }
                });
            } catch (Exception ex) {
            }
        }
    }

    public boolean keypadCheck() {
        if (jCheckBox1.isSelected()) {
            return enableKeypad = true;
        } else {
            return enableKeypad = false;
        }
    }

    public void updateInventory() {
        //stops editting of table cell to allow button event

        tableInventory.putClientProperty("terminateEditOnFocusLost", Boolean.TRUE);
        if (tableInventory.isEditing()) {
            tableInventory.getCellEditor().cancelCellEditing();
        }

        int confirm = JOptionPane.showConfirmDialog(null, "Are you sure?", null, JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            int rowCount = tableInventory.getModel().getRowCount();
            for (int r = 0; r < rowCount; r++) {
                Object ID = tableInventory.getModel().getValueAt(r, 0);
                Object name = tableInventory.getModel().getValueAt(r, 1);
                Object price = tableInventory.getModel().getValueAt(r, 2);
                Object vat = tableInventory.getModel().getValueAt(r, 3);
                Object type = tableInventory.getModel().getValueAt(r, 4);
                //Pass table contents to database update code
                system.updateRecipe(ID, name, price, vat, type);
            }
        }
    }

    public void updateRecipe() {
        //stops editting of table cell to allow button event
        if (tblRecipe.isEditing()) {
            tblRecipe.getCellEditor().cancelCellEditing();
        }
        tblRecipe.putClientProperty("terminateEditOnFocusLost", Boolean.TRUE);

        int confirm = JOptionPane.showConfirmDialog(null, "Are you sure?", null, JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            int rowCount = tblRecipe.getModel().getRowCount();
            for (int r = 0; r < rowCount; r++) {
                Object ID = tblRecipe.getModel().getValueAt(r, 0);
                Object name = tblRecipe.getModel().getValueAt(r, 1);
                Object email = tblRecipe.getModel().getValueAt(r, 2);
                Object num = tblRecipe.getModel().getValueAt(r, 3);
                Object address = tblRecipe.getModel().getValueAt(r, 4);
                //Pass table contents to database update code
                system.updateSupplier(ID, name, email, num, address);
            }
        }
    }

    public void updateSupplier() {
        //stops editting of table cell to allow button event
        tblSuplier.putClientProperty("terminateEditOnFocusLost", Boolean.TRUE);
        if (tblSuplier.isEditing()) {
            tblSuplier.getCellEditor().cancelCellEditing();
        }
        //Creates database manager object 

        int confirm = JOptionPane.showConfirmDialog(null, "Are you sure?", null, JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            int rowCount = tblSuplier.getModel().getRowCount();
            for (int r = 0; r < rowCount; r++) {
                Object ID = tblSuplier.getModel().getValueAt(r, 0);
                Object item = tblSuplier.getModel().getValueAt(r, 1);
                Object qty = tblSuplier.getModel().getValueAt(r, 3);
                Object itemT = tblSuplier.getModel().getValueAt(r, 4);
                Object itemL = tblSuplier.getModel().getValueAt(r, 5);
                //Pass table contents to database update code
                system.updateInventory(ID, item, qty, itemT, itemL);
            }
        }
    }

    public int getID() {
        int id = (int) tblSuplier.getValueAt(tblSuplier.getSelectedRow(), 0);
        return id;
    }

    public int getIDrecipe() {
        int id = (int) tableInventory.getValueAt(tableInventory.getSelectedRow(), 0);
        return id;
    }

    public int getIDsupplier() {
        int id = (int) tblRecipe.getValueAt(tblRecipe.getSelectedRow(), 0);
        return id;
    }

    public static JLabel getDate() {
        return lblDate;
    }

    public static JLabel getTime() {
        return lblClock;
    }

    public static JTable getOrderTable() {
        return tblOrderHistory;
    }

    public final void getSetting() {
        setTableCount();
        setResolution();
        setEmail();
    }

    public final void setResolution() {
        String resolution = settings.getResolution();
        comboBoxSceen.setSelectedItem(resolution);
    }

    public final void setTableCount() {
        String table = settings.getTableCount();
        comboBoxTableCount.setSelectedItem(table);
    }

    public void setEmail() {
        jLabel1.setText(settings.getEmail());
    }

    public final void setSelectedTable() {
        n = Integer.parseInt(getSelectedTable());
        tableLayout();
    }

    public void changeResolution() {
        switch (getSelectedResolution()) {
            case "Windowed Screen":
                windows();
                break;

            case "Fullscreen":
                fullscreen();
                break;
        }
    }

    public void fullscreen() {
        Toolkit tk = Toolkit.getDefaultToolkit();
        int xSize = ((int) tk.getScreenSize().getWidth());
        int ySize = ((int) tk.getScreenSize().getHeight());
        this.setSize(xSize, ySize);
        this.setLocation(0, 0);
    }

    public void windows() {
        this.setSize(800, 600);
        this.setLocationRelativeTo(null);
    }

    public String getSelectedResolution() {
        return comboBoxSceen.getSelectedItem().toString();
    }

    public String getSelectedTable() {
        return comboBoxTableCount.getSelectedItem().toString();
    }

    public String getEmail() {
        return jLabel1.getText();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        TabbedPanel = new javax.swing.JTabbedPane();
        Dashboard = new javax.swing.JPanel();
        lblImage = new javax.swing.JLabel();
        lblVersion = new javax.swing.JLabel();
        buttonClose = new javax.swing.JButton();
        pnlLeft = new javax.swing.JPanel();
        buttonBookings = new javax.swing.JButton();
        buttonEvents = new javax.swing.JButton();
        buttonSpecials = new javax.swing.JButton();
        buttonPromotions = new javax.swing.JButton();
        pnlPanel = new javax.swing.JPanel();
        lblDate = new javax.swing.JLabel();
        buttonLogin = new javax.swing.JButton();
        lblClock = new javax.swing.JLabel();
        buttonLogOut = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        Orders = new javax.swing.JPanel();
        pnlOrderLeft = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable4 = new javax.swing.JTable();
        buttonReprint = new javax.swing.JButton();
        buttonLayout = new javax.swing.JButton();
        buttonTakeAway = new javax.swing.JButton();
        pnlLayout = new javax.swing.JPanel();
        buttonReprint1 = new javax.swing.JButton();
        Inventory = new javax.swing.JPanel();
        textboxSearch = new javax.swing.JTextField();
        lblSearch = new javax.swing.JLabel();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        pnlInventory = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblSuplier = new javax.swing.JTable();
        buttonAdd = new javax.swing.JButton();
        buttonEdit = new javax.swing.JButton();
        buttonDelete = new javax.swing.JButton();
        buttonPrint = new javax.swing.JButton();
        pnlRecipe = new javax.swing.JPanel();
        buttonRecipeAdd = new javax.swing.JButton();
        buttonRecipeDelete = new javax.swing.JButton();
        buttonRecipeEdit = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        tableInventory = new javax.swing.JTable();
        pnlRecipeList = new javax.swing.JPanel();
        buttonRecipeAdd2 = new javax.swing.JButton();
        buttonRecipeDelete2 = new javax.swing.JButton();
        buttonRecipeEdit2 = new javax.swing.JButton();
        jScrollPane6 = new javax.swing.JScrollPane();
        tableRecipeList = new javax.swing.JTable();
        pnlSupplier = new javax.swing.JPanel();
        buttonRecipeAdd1 = new javax.swing.JButton();
        buttonRecipeDelete1 = new javax.swing.JButton();
        buttonRecipeEdit1 = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        tblRecipe = new javax.swing.JTable();
        radioID = new javax.swing.JRadioButton();
        radioItem = new javax.swing.JRadioButton();
        jButton5 = new javax.swing.JButton();
        Management = new javax.swing.JPanel();
        buttonOrderHistory = new javax.swing.JButton();
        buttonAlert = new javax.swing.JButton();
        buttonEmail = new javax.swing.JButton();
        buttonMakeOrder = new javax.swing.JButton();
        buttonReports = new javax.swing.JButton();
        buttonEmployee = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblOrderHistory = new javax.swing.JTable();
        buttonManageSpecials = new javax.swing.JButton();
        buttonEmployee1 = new javax.swing.JButton();
        Settings = new javax.swing.JPanel();
        comboBoxSceen = new javax.swing.JComboBox<>();
        lblSettings = new javax.swing.JLabel();
        comboBoxLogo = new javax.swing.JComboBox<>();
        lblScreen = new javax.swing.JLabel();
        lblLogo = new javax.swing.JLabel();
        jCheckBox1 = new javax.swing.JCheckBox();
        jButton2 = new javax.swing.JButton();
        lblLogo1 = new javax.swing.JLabel();
        comboBoxTableCount = new javax.swing.JComboBox<>();
        jButton4 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jButton10 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setLocation(new java.awt.Point(0, 0));
        setUndecorated(true);

        TabbedPanel.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                TabbedPanelStateChanged(evt);
            }
        });
        TabbedPanel.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                TabbedPanelFocusGained(evt);
            }
        });

        Dashboard.setBackground(new java.awt.Color(242, 241, 241));

        lblImage.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblImage.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Logo_White.png"))); // NOI18N
        lblImage.setToolTipText("");

        lblVersion.setText("Version 1.0.0");

        buttonClose.setText("Close");
        buttonClose.setAlignmentY(0.0F);
        buttonClose.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        buttonClose.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        buttonClose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonCloseActionPerformed(evt);
            }
        });

        buttonBookings.setText("Bookings");
        buttonBookings.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonBookingsActionPerformed(evt);
            }
        });

        buttonEvents.setText("Events");

        buttonSpecials.setText("Specials");
        buttonSpecials.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonSpecialsActionPerformed(evt);
            }
        });

        buttonPromotions.setText("Calendar");
        buttonPromotions.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonPromotionsActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlLeftLayout = new javax.swing.GroupLayout(pnlLeft);
        pnlLeft.setLayout(pnlLeftLayout);
        pnlLeftLayout.setHorizontalGroup(
            pnlLeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlLeftLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(pnlLeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(buttonSpecials, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(buttonPromotions, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(buttonEvents, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(buttonBookings, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        pnlLeftLayout.setVerticalGroup(
            pnlLeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlLeftLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(buttonBookings, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(buttonSpecials, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(buttonPromotions, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(buttonEvents, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        lblDate.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lblDate.setText("Date");

        buttonLogin.setText("Login");
        buttonLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonLoginActionPerformed(evt);
            }
        });

        lblClock.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        lblClock.setText("Clock");

        buttonLogOut.setText("Log out");
        buttonLogOut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonLogOutActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlPanelLayout = new javax.swing.GroupLayout(pnlPanel);
        pnlPanel.setLayout(pnlPanelLayout);
        pnlPanelLayout.setHorizontalGroup(
            pnlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(buttonLogOut, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(buttonLogin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(pnlPanelLayout.createSequentialGroup()
                        .addGroup(pnlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblClock, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblDate, javax.swing.GroupLayout.Alignment.LEADING))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        pnlPanelLayout.setVerticalGroup(
            pnlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblClock, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(buttonLogin)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(buttonLogOut)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblDate, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Logo_help.png"))); // NOI18N
        jButton3.setBorder(null);
        jButton3.setBorderPainted(false);
        jButton3.setContentAreaFilled(false);
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout DashboardLayout = new javax.swing.GroupLayout(Dashboard);
        Dashboard.setLayout(DashboardLayout);
        DashboardLayout.setHorizontalGroup(
            DashboardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(DashboardLayout.createSequentialGroup()
                .addGroup(DashboardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(DashboardLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lblVersion)
                        .addGap(216, 216, 216)
                        .addComponent(buttonClose, javax.swing.GroupLayout.DEFAULT_SIZE, 291, Short.MAX_VALUE)
                        .addGap(187, 187, 187))
                    .addGroup(DashboardLayout.createSequentialGroup()
                        .addComponent(pnlLeft, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(104, 104, 104)
                        .addComponent(lblImage, javax.swing.GroupLayout.DEFAULT_SIZE, 395, Short.MAX_VALUE)
                        .addGap(117, 117, 117)
                        .addComponent(jButton3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addComponent(pnlPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        DashboardLayout.setVerticalGroup(
            DashboardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(DashboardLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(DashboardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(DashboardLayout.createSequentialGroup()
                        .addGroup(DashboardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(DashboardLayout.createSequentialGroup()
                                .addGroup(DashboardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(DashboardLayout.createSequentialGroup()
                                        .addComponent(pnlLeft, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 84, Short.MAX_VALUE))
                                    .addComponent(lblImage, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 368, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                            .addGroup(DashboardLayout.createSequentialGroup()
                                .addGap(17, 17, 17)
                                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGroup(DashboardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblVersion)
                            .addComponent(buttonClose))
                        .addContainerGap())
                    .addComponent(pnlPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        TabbedPanel.addTab("Dashboard", Dashboard);

        jTable4.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"Bill:", null},
                {"Waiter:", null},
                {"No. Customers", null}
            },
            new String [] {
                "", "Table #"
            }
        ));
        jTable4.setGridColor(new java.awt.Color(204, 204, 204));
        jScrollPane2.setViewportView(jTable4);

        buttonReprint.setText("Reprint");
        buttonReprint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonReprintActionPerformed(evt);
            }
        });

        buttonLayout.setText("Open Draw");
        buttonLayout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonLayoutActionPerformed(evt);
            }
        });

        buttonTakeAway.setText("Take Away");
        buttonTakeAway.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonTakeAwayActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlOrderLeftLayout = new javax.swing.GroupLayout(pnlOrderLeft);
        pnlOrderLeft.setLayout(pnlOrderLeftLayout);
        pnlOrderLeftLayout.setHorizontalGroup(
            pnlOrderLeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlOrderLeftLayout.createSequentialGroup()
                .addGap(57, 57, 57)
                .addGroup(pnlOrderLeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(buttonReprint, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(buttonLayout, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(buttonTakeAway, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );
        pnlOrderLeftLayout.setVerticalGroup(
            pnlOrderLeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlOrderLeftLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(buttonTakeAway, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(buttonLayout, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(buttonReprint, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(67, 67, 67)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(57, Short.MAX_VALUE))
        );

        pnlLayout.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        pnlLayout.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        javax.swing.GroupLayout pnlLayoutLayout = new javax.swing.GroupLayout(pnlLayout);
        pnlLayout.setLayout(pnlLayoutLayout);
        pnlLayoutLayout.setHorizontalGroup(
            pnlLayoutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 616, Short.MAX_VALUE)
        );
        pnlLayoutLayout.setVerticalGroup(
            pnlLayoutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 359, Short.MAX_VALUE)
        );

        buttonReprint1.setText("Edit");
        buttonReprint1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonReprint1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout OrdersLayout = new javax.swing.GroupLayout(Orders);
        Orders.setLayout(OrdersLayout);
        OrdersLayout.setHorizontalGroup(
            OrdersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, OrdersLayout.createSequentialGroup()
                .addGroup(OrdersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(pnlLayout, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(buttonReprint1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(93, 93, 93)
                .addComponent(pnlOrderLeft, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        OrdersLayout.setVerticalGroup(
            OrdersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(OrdersLayout.createSequentialGroup()
                .addComponent(pnlLayout, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(buttonReprint1, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(OrdersLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlOrderLeft, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        TabbedPanel.addTab("Orders", Orders);

        lblSearch.setText("Search ");

        tblSuplier.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "ID no.", "Item", "Quantity", "Cost per Unit", "Distrubutor ID ", "Distrubutor Name"
            }
        ));
        tblSuplier.setGridColor(new java.awt.Color(204, 204, 204));
        jScrollPane3.setViewportView(tblSuplier);
        if (tblSuplier.getColumnModel().getColumnCount() > 0) {
            tblSuplier.getColumnModel().getColumn(2).setResizable(false);
            tblSuplier.getColumnModel().getColumn(3).setHeaderValue("Cost per Unit");
            tblSuplier.getColumnModel().getColumn(4).setHeaderValue("Distrubutor ID ");
            tblSuplier.getColumnModel().getColumn(5).setHeaderValue("Distrubutor Name");
        }

        buttonAdd.setText("Add");
        buttonAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonAddActionPerformed(evt);
            }
        });

        buttonEdit.setText("Edit");
        buttonEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonEditActionPerformed(evt);
            }
        });

        buttonDelete.setText("Delete");
        buttonDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonDeleteActionPerformed(evt);
            }
        });

        buttonPrint.setText("Print");

        javax.swing.GroupLayout pnlInventoryLayout = new javax.swing.GroupLayout(pnlInventory);
        pnlInventory.setLayout(pnlInventoryLayout);
        pnlInventoryLayout.setHorizontalGroup(
            pnlInventoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlInventoryLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlInventoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(buttonDelete, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(buttonPrint, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(buttonEdit, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 69, Short.MAX_VALUE)
                    .addComponent(buttonAdd, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 761, Short.MAX_VALUE))
        );
        pnlInventoryLayout.setVerticalGroup(
            pnlInventoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlInventoryLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(buttonAdd)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(buttonDelete)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(buttonEdit)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(buttonPrint)
                .addContainerGap())
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 338, Short.MAX_VALUE)
        );

        jTabbedPane2.addTab("Inventory", pnlInventory);

        buttonRecipeAdd.setText("Add");
        buttonRecipeAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonRecipeAddActionPerformed(evt);
            }
        });

        buttonRecipeDelete.setText("Delete");
        buttonRecipeDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonRecipeDeleteActionPerformed(evt);
            }
        });

        buttonRecipeEdit.setText("Edit");
        buttonRecipeEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonRecipeEditActionPerformed(evt);
            }
        });

        tableInventory.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "ID no.", "Item", "Ingredients"
            }
        ));
        tableInventory.setGridColor(new java.awt.Color(204, 204, 204));
        jScrollPane4.setViewportView(tableInventory);
        if (tableInventory.getColumnModel().getColumnCount() > 0) {
            tableInventory.getColumnModel().getColumn(2).setResizable(false);
        }

        javax.swing.GroupLayout pnlRecipeLayout = new javax.swing.GroupLayout(pnlRecipe);
        pnlRecipe.setLayout(pnlRecipeLayout);
        pnlRecipeLayout.setHorizontalGroup(
            pnlRecipeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlRecipeLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlRecipeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(buttonRecipeEdit, javax.swing.GroupLayout.DEFAULT_SIZE, 69, Short.MAX_VALUE)
                    .addComponent(buttonRecipeAdd, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(buttonRecipeDelete, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 755, Short.MAX_VALUE))
        );
        pnlRecipeLayout.setVerticalGroup(
            pnlRecipeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlRecipeLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(buttonRecipeAdd)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(buttonRecipeDelete)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(buttonRecipeEdit)
                .addGap(0, 0, Short.MAX_VALUE))
            .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 338, Short.MAX_VALUE)
        );

        jTabbedPane2.addTab("Recipe", pnlRecipe);

        buttonRecipeAdd2.setText("Add");
        buttonRecipeAdd2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonRecipeAdd2ActionPerformed(evt);
            }
        });

        buttonRecipeDelete2.setText("Delete");
        buttonRecipeDelete2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonRecipeDelete2ActionPerformed(evt);
            }
        });

        buttonRecipeEdit2.setText("Edit");

        tableRecipeList.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "ID no.", "Item", "Ingredients"
            }
        ));
        tableRecipeList.setGridColor(new java.awt.Color(204, 204, 204));
        jScrollPane6.setViewportView(tableRecipeList);
        if (tableRecipeList.getColumnModel().getColumnCount() > 0) {
            tableRecipeList.getColumnModel().getColumn(2).setResizable(false);
        }

        javax.swing.GroupLayout pnlRecipeListLayout = new javax.swing.GroupLayout(pnlRecipeList);
        pnlRecipeList.setLayout(pnlRecipeListLayout);
        pnlRecipeListLayout.setHorizontalGroup(
            pnlRecipeListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlRecipeListLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlRecipeListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(buttonRecipeEdit2, javax.swing.GroupLayout.DEFAULT_SIZE, 69, Short.MAX_VALUE)
                    .addComponent(buttonRecipeAdd2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(buttonRecipeDelete2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 755, Short.MAX_VALUE))
        );
        pnlRecipeListLayout.setVerticalGroup(
            pnlRecipeListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlRecipeListLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(buttonRecipeAdd2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(buttonRecipeDelete2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(buttonRecipeEdit2)
                .addGap(0, 0, Short.MAX_VALUE))
            .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 338, Short.MAX_VALUE)
        );

        jTabbedPane2.addTab("Recipe List", pnlRecipeList);

        buttonRecipeAdd1.setText("Add");
        buttonRecipeAdd1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonRecipeAdd1ActionPerformed(evt);
            }
        });

        buttonRecipeDelete1.setText("Delete");
        buttonRecipeDelete1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonRecipeDelete1ActionPerformed(evt);
            }
        });

        buttonRecipeEdit1.setText("Edit");
        buttonRecipeEdit1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonRecipeEdit1ActionPerformed(evt);
            }
        });

        tblRecipe.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "ID no.", "Item", "Ingredients"
            }
        ));
        tblRecipe.setGridColor(new java.awt.Color(204, 204, 204));
        jScrollPane5.setViewportView(tblRecipe);
        if (tblRecipe.getColumnModel().getColumnCount() > 0) {
            tblRecipe.getColumnModel().getColumn(2).setResizable(false);
        }

        javax.swing.GroupLayout pnlSupplierLayout = new javax.swing.GroupLayout(pnlSupplier);
        pnlSupplier.setLayout(pnlSupplierLayout);
        pnlSupplierLayout.setHorizontalGroup(
            pnlSupplierLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlSupplierLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlSupplierLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(buttonRecipeAdd1, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonRecipeEdit1, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonRecipeDelete1, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 755, Short.MAX_VALUE))
        );
        pnlSupplierLayout.setVerticalGroup(
            pnlSupplierLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlSupplierLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(buttonRecipeAdd1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(buttonRecipeDelete1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(buttonRecipeEdit1)
                .addGap(0, 0, Short.MAX_VALUE))
            .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 338, Short.MAX_VALUE)
        );

        jTabbedPane2.addTab("Supplier", pnlSupplier);

        radioID.setText("ID no.");
        radioID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioIDActionPerformed(evt);
            }
        });

        radioItem.setText("Item");

        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Logo_help.png"))); // NOI18N
        jButton5.setBorder(null);
        jButton5.setBorderPainted(false);
        jButton5.setContentAreaFilled(false);
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout InventoryLayout = new javax.swing.GroupLayout(Inventory);
        Inventory.setLayout(InventoryLayout);
        InventoryLayout.setHorizontalGroup(
            InventoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, InventoryLayout.createSequentialGroup()
                .addGroup(InventoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jTabbedPane2)
                    .addGroup(InventoryLayout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(InventoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(InventoryLayout.createSequentialGroup()
                                .addComponent(jButton5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lblSearch)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(textboxSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(InventoryLayout.createSequentialGroup()
                                .addComponent(radioItem)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(radioID)))
                        .addGap(8, 8, 8)))
                .addContainerGap())
        );
        InventoryLayout.setVerticalGroup(
            InventoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(InventoryLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(InventoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(InventoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(textboxSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblSearch))
                    .addComponent(jButton5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(InventoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(radioID)
                    .addComponent(radioItem))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane2))
        );

        TabbedPanel.addTab("Database", Inventory);

        buttonOrderHistory.setText("Confirm Order");

        buttonAlert.setText("Alerts");

        buttonEmail.setText("Email");
        buttonEmail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonEmailActionPerformed(evt);
            }
        });

        buttonMakeOrder.setText("Make Order");
        buttonMakeOrder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonMakeOrderActionPerformed(evt);
            }
        });

        buttonReports.setText("Report");
        buttonReports.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonReportsActionPerformed(evt);
            }
        });

        buttonEmployee.setText("Employees");
        buttonEmployee.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonEmployeeActionPerformed(evt);
            }
        });

        tblOrderHistory.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Item ID", "Item Name", "Qty", "Amount Ordered"
            }
        ));
        tblOrderHistory.setGridColor(new java.awt.Color(204, 204, 204));
        tblOrderHistory.setOpaque(false);
        jScrollPane1.setViewportView(tblOrderHistory);

        buttonManageSpecials.setText("Manage Specials");
        buttonManageSpecials.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonManageSpecialsActionPerformed(evt);
            }
        });

        buttonEmployee1.setText("View Logs");
        buttonEmployee1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonEmployee1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout ManagementLayout = new javax.swing.GroupLayout(Management);
        Management.setLayout(ManagementLayout);
        ManagementLayout.setHorizontalGroup(
            ManagementLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ManagementLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(ManagementLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(ManagementLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(ManagementLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(ManagementLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(ManagementLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(buttonAlert, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(buttonOrderHistory, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(buttonMakeOrder, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(buttonEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(buttonReports, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(buttonEmployee, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(buttonManageSpecials, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(buttonEmployee1, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 674, Short.MAX_VALUE)
                .addGap(6, 6, 6))
        );
        ManagementLayout.setVerticalGroup(
            ManagementLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ManagementLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(ManagementLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 401, Short.MAX_VALUE)
                    .addGroup(ManagementLayout.createSequentialGroup()
                        .addComponent(buttonOrderHistory)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonMakeOrder)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonAlert)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonEmail)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonReports)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonEmployee)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonEmployee1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(buttonManageSpecials)))
                .addContainerGap())
        );

        TabbedPanel.addTab("Management", Management);

        comboBoxSceen.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Windowed Screen", "Fullscreen" }));
        comboBoxSceen.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                comboBoxSceenItemStateChanged(evt);
            }
        });

        lblSettings.setText("Settings");

        comboBoxLogo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Default", "Demo 1", "Demo 2" }));
        comboBoxLogo.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                comboBoxLogoItemStateChanged(evt);
            }
        });

        lblScreen.setText("Resolution");

        lblLogo.setText("Set Logo");

        jCheckBox1.setText("Enable KeyPad");

        jButton2.setText("Save");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        lblLogo1.setText("Number of Tabels");

        comboBoxTableCount.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20" }));
        comboBoxTableCount.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                comboBoxTableCountItemStateChanged(evt);
            }
        });

        jButton4.setText("Backup Database");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton7.setText("Restore Database");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jButton8.setText("Change Email ");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        jButton9.setText("Reset Logs");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        jLabel1.setText("Email");

        jButton10.setText("Change Email Password");
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout SettingsLayout = new javax.swing.GroupLayout(Settings);
        Settings.setLayout(SettingsLayout);
        SettingsLayout.setHorizontalGroup(
            SettingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(SettingsLayout.createSequentialGroup()
                .addGroup(SettingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(SettingsLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lblSettings))
                    .addGroup(SettingsLayout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(SettingsLayout.createSequentialGroup()
                        .addGroup(SettingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(SettingsLayout.createSequentialGroup()
                                .addGap(26, 26, 26)
                                .addGroup(SettingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jCheckBox1)
                                    .addGroup(SettingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(lblScreen)
                                        .addComponent(lblLogo)
                                        .addComponent(lblLogo1))))
                            .addGroup(SettingsLayout.createSequentialGroup()
                                .addGap(35, 35, 35)
                                .addComponent(jLabel1)))
                        .addGap(32, 32, 32)
                        .addGroup(SettingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(comboBoxTableCount, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(comboBoxLogo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(comboBoxSceen, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton10, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(567, Short.MAX_VALUE))
        );
        SettingsLayout.setVerticalGroup(
            SettingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(SettingsLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(lblSettings)
                .addGap(18, 18, 18)
                .addGroup(SettingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(comboBoxSceen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblScreen))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(SettingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(comboBoxLogo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblLogo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(SettingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(comboBoxTableCount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblLogo1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jCheckBox1)
                .addGap(9, 9, 9)
                .addGroup(SettingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton8)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 73, Short.MAX_VALUE)
                .addComponent(jButton2)
                .addContainerGap())
        );

        TabbedPanel.addTab("Settings", Settings);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(TabbedPanel))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(TabbedPanel, javax.swing.GroupLayout.Alignment.TRAILING)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents
    private void buttonEmployeeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonEmployeeActionPerformed
        Employee emp = new Employee();
        emp.setVisible(true);
    }//GEN-LAST:event_buttonEmployeeActionPerformed

    private void buttonPromotionsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonPromotionsActionPerformed
        try {
            calendar.setVisible(true);
            booking.setVisible(false);
            specials.setVisible(false);
        } catch (RuntimeException ignore) {
        }
    }//GEN-LAST:event_buttonPromotionsActionPerformed

    private void buttonSpecialsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonSpecialsActionPerformed
        try {
            calendar.setVisible(false);
            booking.setVisible(false);
            specials.setVisible(true);
        } catch (RuntimeException ignore) {
        }
    }//GEN-LAST:event_buttonSpecialsActionPerformed

    private void buttonBookingsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonBookingsActionPerformed
        try {
            specials.setVisible(false);
            calendar.setVisible(false);
            booking.setVisible(true);
        } catch (RuntimeException ignore) {
        }
    }//GEN-LAST:event_buttonBookingsActionPerformed

    private void buttonCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonCloseActionPerformed
        System.exit(0);
    }//GEN-LAST:event_buttonCloseActionPerformed

    private void buttonTakeAwayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonTakeAwayActionPerformed
        if (tables.get("takeAway") == null) {
            tables.put("takeAway", new NewOrder("Guest", null, "takeAway", tables));
        } else {
            tables.get("takeAway").setVisible(true);
        }
    }//GEN-LAST:event_buttonTakeAwayActionPerformed

    private void buttonLayoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonLayoutActionPerformed
        JOptionPane.showMessageDialog(null, "Draw Opened");
    }//GEN-LAST:event_buttonLayoutActionPerformed

    private void buttonReprintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonReprintActionPerformed

    }//GEN-LAST:event_buttonReprintActionPerformed

    private void comboBoxSceenItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_comboBoxSceenItemStateChanged
        if (evt.getStateChange() == ItemEvent.SELECTED) {
            changeResolution();
        }
    }//GEN-LAST:event_comboBoxSceenItemStateChanged

    private void comboBoxLogoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_comboBoxLogoItemStateChanged
        String selected_item = comboBoxLogo.getSelectedItem().toString();
        switch (selected_item) {

            case "Default":
                ImageIcon icon = new ImageIcon("./src/images/Logo_Red.png");
                lblImage.setIcon(icon);
                break;

            case "Demo 1":
                ImageIcon icon1 = new ImageIcon("./src/images/Logo_Bistro.png");
                lblImage.setIcon(icon1);
                break;

            case "Demo 2":
                ImageIcon icon2 = new ImageIcon("./src/images/Logo_Cattle_Baron.png");
                lblImage.setIcon(icon2);
                break;
        }
    }//GEN-LAST:event_comboBoxLogoItemStateChanged

    private void radioIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioIDActionPerformed

    }//GEN-LAST:event_radioIDActionPerformed

    private void TabbedPanelStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_TabbedPanelStateChanged
        booking.setVisible(false);
    }//GEN-LAST:event_TabbedPanelStateChanged

    private void TabbedPanelFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_TabbedPanelFocusGained
        booking.setVisible(false);
    }//GEN-LAST:event_TabbedPanelFocusGained

    private void buttonRecipeAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonRecipeAddActionPerformed
        AddDatabase database = new AddDatabase();
        database.setVisible(true);
        AddDatabase.getTabbedPanel().setSelectedIndex(1);
        if (keypadCheck() == true) {
            Keyboard k = new Keyboard();
            k.setLocation(350, 530);
            k.setVisible(true);
        }
    }//GEN-LAST:event_buttonRecipeAddActionPerformed

    private void buttonEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonEditActionPerformed
        //Admin login window object created
        //AdminLogin s = new AdminLogin();
        //Admin login object set to visible
        //s.setVisible(true);
        updateSupplier();
    }//GEN-LAST:event_buttonEditActionPerformed

    private void buttonAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAddActionPerformed
        AddDatabase database = new AddDatabase();
        database.setVisible(true);
        if (keypadCheck() == true) {
            Keyboard k = new Keyboard();
            k.setLocation(350, 530);
            k.setVisible(true);
        }
    }//GEN-LAST:event_buttonAddActionPerformed

    private void buttonRecipeAdd1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonRecipeAdd1ActionPerformed
        AddDatabase database = new AddDatabase();
        database.setVisible(true);
        AddDatabase.getTabbedPanel().setSelectedIndex(2);
        if (keypadCheck() == true) {
            Keyboard k = new Keyboard();
            k.setLocation(350, 530);
            k.setVisible(true);
        }
    }//GEN-LAST:event_buttonRecipeAdd1ActionPerformed

    private void buttonReprint1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonReprint1ActionPerformed

    }//GEN-LAST:event_buttonReprint1ActionPerformed

    private void buttonDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonDeleteActionPerformed
        int confirm = JOptionPane.showConfirmDialog(null, "Are you sure?", null, JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            system.removeInventory(getID());
        }
    }//GEN-LAST:event_buttonDeleteActionPerformed

    private void buttonRecipeDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonRecipeDeleteActionPerformed
        int confirm = JOptionPane.showConfirmDialog(null, "Are you sure?", null, JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            system.removeRecipe(getIDrecipe());
        }
    }//GEN-LAST:event_buttonRecipeDeleteActionPerformed

    private void buttonRecipeDelete1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonRecipeDelete1ActionPerformed
        int confirm = JOptionPane.showConfirmDialog(null, "Are you sure?", null, JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            system.removeSupplier(getIDsupplier());
        }
    }//GEN-LAST:event_buttonRecipeDelete1ActionPerformed

    private void buttonLogOutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonLogOutActionPerformed
        user.createLogout();
    }//GEN-LAST:event_buttonLogOutActionPerformed

    private void buttonLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonLoginActionPerformed
        user.createLogin();
    }//GEN-LAST:event_buttonLoginActionPerformed

    private void buttonMakeOrderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonMakeOrderActionPerformed
        OrderForm newOrder = new OrderForm();
        newOrder.setVisible(true);
    }//GEN-LAST:event_buttonMakeOrderActionPerformed

    private void buttonEmailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonEmailActionPerformed
        EmailForm newEmail = new EmailForm();
        newEmail.setVisible(true);
    }//GEN-LAST:event_buttonEmailActionPerformed

    private void buttonReportsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonReportsActionPerformed
        Reports newReport = new Reports();
        newReport.setVisible(true);
    }//GEN-LAST:event_buttonReportsActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        settings.updateSettings(getSelectedResolution(), getSelectedTable(), getEmail());
        JOptionPane.showMessageDialog(null, "Settings Saved");
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        String file = System.getProperty("user.home") + File.separator + "Documents\\NetBeansProjects\\stockManager\\ResturantManagementSystem\\src\\docs\\Help.txt";
        Runtime runtime = Runtime.getRuntime();
        try {
            Process p = runtime.exec("notepad " + file);
        } catch (IOException ex) {
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        String file = System.getProperty("user.home") + File.separator + "Documents\\NetBeansProjects\\stockManager\\ResturantManagementSystem\\src\\docs\\HelpDB.txt";
        Runtime runtime = Runtime.getRuntime();
        try {
            Process p = runtime.exec("notepad " + file);
        } catch (IOException ex) {
        }
    }//GEN-LAST:event_jButton5ActionPerformed

    private void comboBoxTableCountItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_comboBoxTableCountItemStateChanged
        setSelectedTable();
    }//GEN-LAST:event_comboBoxTableCountItemStateChanged

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        int confirm = JOptionPane.showConfirmDialog(null, "Are you sure you want to backup?", null, JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            system.backup();
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed

    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        int confirm = JOptionPane.showConfirmDialog(null, "Are you sure you want to retore?", null, JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            system.restore();
        }
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton8ActionPerformed

    private void buttonManageSpecialsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonManageSpecialsActionPerformed
        SpecialsForm newForm = new SpecialsForm();
        newForm.setVisible(true);
    }//GEN-LAST:event_buttonManageSpecialsActionPerformed

    private void buttonEmployee1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonEmployee1ActionPerformed
        String file = System.getProperty("user.home") + File.separator + "Documents\\NetBeansProjects\\stockManager\\ResturantManagementSystem\\src\\logs\\logs.txt";
        Runtime runtime = Runtime.getRuntime();
        try {
            Process p = runtime.exec("notepad " + file);
        } catch (IOException ex) {
        }
    }//GEN-LAST:event_buttonEmployee1ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        logs.refreshLogs();
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton10ActionPerformed

    private void buttonRecipeAdd2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonRecipeAdd2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_buttonRecipeAdd2ActionPerformed

    private void buttonRecipeDelete2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonRecipeDelete2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_buttonRecipeDelete2ActionPerformed

    private void buttonRecipeEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonRecipeEditActionPerformed
        //Admin login window object created
        //AdminLogin s = new AdminLogin();
        //Admin login object set to visible
        //s.setVisible(true);
        updateInventory();
    }//GEN-LAST:event_buttonRecipeEditActionPerformed

    private void buttonRecipeEdit1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonRecipeEdit1ActionPerformed
        //Admin login window object created
        //AdminLogin s = new AdminLogin();
        //Admin login object set to visible
        //s.setVisible(true);
        updateRecipe();
    }//GEN-LAST:event_buttonRecipeEdit1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;

                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainSystem.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new MainSystem().setVisible(true);
        });
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Dashboard;
    private javax.swing.JPanel Inventory;
    private javax.swing.JPanel Management;
    private javax.swing.JPanel Orders;
    private javax.swing.JPanel Settings;
    private javax.swing.JTabbedPane TabbedPanel;
    private javax.swing.JButton buttonAdd;
    private javax.swing.JButton buttonAlert;
    private javax.swing.JButton buttonBookings;
    private javax.swing.JButton buttonClose;
    private javax.swing.JButton buttonDelete;
    private javax.swing.JButton buttonEdit;
    private javax.swing.JButton buttonEmail;
    private javax.swing.JButton buttonEmployee;
    private javax.swing.JButton buttonEmployee1;
    private javax.swing.JButton buttonEvents;
    private javax.swing.JButton buttonLayout;
    private javax.swing.JButton buttonLogOut;
    private javax.swing.JButton buttonLogin;
    private javax.swing.JButton buttonMakeOrder;
    private javax.swing.JButton buttonManageSpecials;
    private javax.swing.JButton buttonOrderHistory;
    private javax.swing.JButton buttonPrint;
    private javax.swing.JButton buttonPromotions;
    private javax.swing.JButton buttonRecipeAdd;
    private javax.swing.JButton buttonRecipeAdd1;
    private javax.swing.JButton buttonRecipeAdd2;
    private javax.swing.JButton buttonRecipeDelete;
    private javax.swing.JButton buttonRecipeDelete1;
    private javax.swing.JButton buttonRecipeDelete2;
    private javax.swing.JButton buttonRecipeEdit;
    private javax.swing.JButton buttonRecipeEdit1;
    private javax.swing.JButton buttonRecipeEdit2;
    private javax.swing.JButton buttonReports;
    private javax.swing.JButton buttonReprint;
    private javax.swing.JButton buttonReprint1;
    private javax.swing.JButton buttonSpecials;
    private javax.swing.JButton buttonTakeAway;
    private javax.swing.JComboBox<String> comboBoxLogo;
    private javax.swing.JComboBox<String> comboBoxSceen;
    private javax.swing.JComboBox<String> comboBoxTableCount;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JTable jTable4;
    private static javax.swing.JLabel lblClock;
    private static javax.swing.JLabel lblDate;
    private javax.swing.JLabel lblImage;
    private javax.swing.JLabel lblLogo;
    private javax.swing.JLabel lblLogo1;
    private javax.swing.JLabel lblScreen;
    private javax.swing.JLabel lblSearch;
    private javax.swing.JLabel lblSettings;
    private javax.swing.JLabel lblVersion;
    private javax.swing.JPanel pnlInventory;
    public static javax.swing.JPanel pnlLayout;
    private javax.swing.JPanel pnlLeft;
    private javax.swing.JPanel pnlOrderLeft;
    private javax.swing.JPanel pnlPanel;
    private javax.swing.JPanel pnlRecipe;
    private javax.swing.JPanel pnlRecipeList;
    private javax.swing.JPanel pnlSupplier;
    private javax.swing.JRadioButton radioID;
    private javax.swing.JRadioButton radioItem;
    public static javax.swing.JTable tableInventory;
    public static javax.swing.JTable tableRecipeList;
    private static javax.swing.JTable tblOrderHistory;
    public static javax.swing.JTable tblRecipe;
    public static javax.swing.JTable tblSuplier;
    private static javax.swing.JTextField textboxSearch;
    // End of variables declaration//GEN-END:variables
}
