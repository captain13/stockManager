package system;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author GGPQ9ZJ42
 */
public class MainSystem extends javax.swing.JFrame implements ActionListener {

    BookingForm booking = new BookingForm();
    Calendar calendar = new Calendar();
    Specials specials;
    userManager user = new userManager();
    xmlManager settings = new xmlManager();
    dbManager system = new dbManager();
    logSystem logs = new logSystem();
    Prints prints = new Prints();
    networkHandler network = new networkHandler();
    receiptHandler receipt = new receiptHandler();
    internalClock clock = new internalClock();
    HashMap<String, NewOrder> tables = new HashMap<>();
    boolean enableKeypad = false;
    int n;
    JButton button;
    Border blackline = BorderFactory.createLineBorder(Color.WHITE);

    public MainSystem() {
        initComponents();
        system.dbValidation();
        settings.xmlValidition();
        logs.logValidation();
        clock.internalClock(lblClock, lblDate);
        network.recieveData(jTable1);
        populateInvnetoryTable();
        populateRecipeTable();
        populateRecipeListTable();
        populateSupplierTable();
        populateOrderTable();
        getSetting();
    }

    public final void populateInvnetoryTable() {
        String columnNamesInventory[] = {"Inventory ID", "Item Name", "Category", "Quantity(g)", "Item Threshold", "Item Limit"};
        DefaultTableModel tableModel = new DefaultTableModel(system.getInventoryData(), columnNamesInventory);
        tblInventory.setModel(tableModel);
        searchITable();
        tblInventory.getColumnModel().getColumn(0).setPreferredWidth(100);
        tblInventory.getColumnModel().getColumn(1).setPreferredWidth(300);
        tblInventory.getColumnModel().getColumn(2).setPreferredWidth(100);
        tblInventory.getColumnModel().getColumn(3).setPreferredWidth(100);
        tblInventory.getColumnModel().getColumn(4).setPreferredWidth(100);
    }

    public final void populateRecipeTable() {
        String columnNamesRecipe[] = {"Recipe ID", "Description", "Type", "Price", "VAT"};
        DefaultTableModel tableModel = new DefaultTableModel(system.getRecipeData(), columnNamesRecipe);
        tableRecipe.setModel(tableModel);
        searchRTable();
//        tblInventory.getColumnModel().getColumn(0).setPreferredWidth(100);
//        tblInventory.getColumnModel().getColumn(1).setPreferredWidth(300);
//        tblInventory.getColumnModel().getColumn(2).setPreferredWidth(100);
//        tblInventory.getColumnModel().getColumn(3).setPreferredWidth(100);
//        tblInventory.getColumnModel().getColumn(4).setPreferredWidth(100);
    }

    public final void populateRecipeListTable() {
        String columnNamesRecipeList[] = {"Recipe ID", "Recipe Name", "Inventory ID", "Item Name", "Quantity"};
        DefaultTableModel tableModel = new DefaultTableModel(system.getRecipeListData(), columnNamesRecipeList);
        tableRecipeList.setModel(tableModel);
//         searchTable();
//        tblInventory.getColumnModel().getColumn(0).setPreferredWidth(100);
//        tblInventory.getColumnModel().getColumn(1).setPreferredWidth(300);
//        tblInventory.getColumnModel().getColumn(2).setPreferredWidth(100);
//        tblInventory.getColumnModel().getColumn(3).setPreferredWidth(100);
//        tblInventory.getColumnModel().getColumn(4).setPreferredWidth(100);
    }
//

    public final void populateSupplierTable() {
        String columnNamesSupplier[] = {"Supplier ID", "Name", "Email", "Contact Number", "Address"};
        DefaultTableModel tableModel = new DefaultTableModel(system.getSuppleirData(), columnNamesSupplier);
        tblSupplier.setModel(tableModel);
        searchSTable();
//        tblInventory.getColumnModel().getColumn(0).setPreferredWidth(100);
//        tblInventory.getColumnModel().getColumn(1).setPreferredWidth(300);
//        tblInventory.getColumnModel().getColumn(2).setPreferredWidth(100);
//        tblInventory.getColumnModel().getColumn(3).setPreferredWidth(100);
//        tblInventory.getColumnModel().getColumn(4).setPreferredWidth(100);
    }

    public final void populateOrderTable() {
        String columnNames[] = {"ID", "Item", "Supplier Name", "Date Ordered", "ETA", "Quantity(g)", "Status"};
        DefaultTableModel tableModel = new DefaultTableModel(system.getOrderData(), columnNames);
        tblOrderHistory.setModel(tableModel);
//        tblInventory.getColumnModel().getColumn(0).setPreferredWidth(100);
//        tblInventory.getColumnModel().getColumn(1).setPreferredWidth(300);
//        tblInventory.getColumnModel().getColumn(2).setPreferredWidth(100);
//        tblInventory.getColumnModel().getColumn(3).setPreferredWidth(100);
//        tblInventory.getColumnModel().getColumn(4).setPreferredWidth(100);
    }

    public final void tableLayout() {
        pnlLayout.removeAll();
        GridLayout tableLayout = new GridLayout(0, 4);
        pnlLayout.setLayout(tableLayout);
        int emptySpace = 20 - n;
        for (int i = 1; i <= n; i++) {
            button = new JButton("Table " + i);
            button.setBackground(new Color(0, 138, 231));
            button.setForeground(new Color(255, 255, 255));
            button.setBorder(blackline);
            button.setContentAreaFilled(false);
            button.setOpaque(true);
            button.addActionListener(this);
            pnlLayout.add(button);
        }
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        String buttonId = ae.getActionCommand();
        if (tables.get(buttonId) == null) {
            String waiter=user.createUserLog();
            String customer = JOptionPane.showInputDialog(null, "Enter Number of Customers");
            if (!"".equals(waiter) && (!"".equals(customer))
                    && customer != null && waiter != null) {
                tables.put(buttonId, new NewOrder(waiter, customer, buttonId, tables));
                tables.get(buttonId).setVisible(true);
            } else {
            }
        } else {
            tables.get(buttonId).setVisible(true);
        }
    }

    public void searchITable() {
        try {
            TableRowSorter<TableModel> rowSorter = new TableRowSorter<>(tblInventory.getModel());
            tblInventory.setRowSorter(rowSorter);

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
                }
            });
        } catch (Exception ex) {
        }
    }

    public void searchRTable() {
        try {
            TableRowSorter<TableModel> rowSorterRecipe = new TableRowSorter<>(tableRecipe.getModel());
            tableRecipe.setRowSorter(rowSorterRecipe);

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

    public void searchSTable() {
        try {
            TableRowSorter<TableModel> rowSorterSup = new TableRowSorter<>(tblSupplier.getModel());
            tblSupplier.setRowSorter(rowSorterSup);
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

    public boolean keypadCheck() {
        if (jCheckBox1.isSelected()) {
            return enableKeypad = true;
        } else {
            return enableKeypad = false;
        }
    }

    public void updateInventory() {
        //stops editting of table cell to allow button event
        tableRecipe.putClientProperty("terminateEditOnFocusLost", Boolean.TRUE);
        if (tableRecipe.isEditing()) {
            tableRecipe.getCellEditor().cancelCellEditing();
        }

        int confirm = JOptionPane.showConfirmDialog(null, "Are you sure?", null, JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            int rowCount = tableRecipe.getModel().getRowCount();
            for (int r = 0; r < rowCount; r++) {
                Object ID = tableRecipe.getModel().getValueAt(r, 0);
                Object name = tableRecipe.getModel().getValueAt(r, 1);
                Object price = tableRecipe.getModel().getValueAt(r, 2);
                Object vat = tableRecipe.getModel().getValueAt(r, 3);
                Object type = tableRecipe.getModel().getValueAt(r, 4);
                //Pass table contents to database update code
                system.updateRecipe(ID, name, price, vat, type);
            }
        }
    }

    public void updateShrinkage() {
        String qty = JOptionPane.showInputDialog(null, "Enter the Shrinkage for the Select Item");
        String item = tblInventory.getValueAt(tblInventory.getSelectedRow(), 1).toString();
        system.updateInventoryQty(item, qty, "-");
    }

    public void updateRecipe() {
        //stops editting of table cell to allow button event
        if (tblSupplier.isEditing()) {
            tblSupplier.getCellEditor().cancelCellEditing();
        }
        tblSupplier.putClientProperty("terminateEditOnFocusLost", Boolean.TRUE);

        int confirm = JOptionPane.showConfirmDialog(null, "Are you sure?", null, JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            int rowCount = tblSupplier.getModel().getRowCount();
            for (int r = 0; r < rowCount; r++) {
                Object ID = tblSupplier.getModel().getValueAt(r, 0);
                Object name = tblSupplier.getModel().getValueAt(r, 1);
                Object email = tblSupplier.getModel().getValueAt(r, 2);
                Object num = tblSupplier.getModel().getValueAt(r, 3);
                Object address = tblSupplier.getModel().getValueAt(r, 4);
                //Pass table contents to database update code
                system.updateSupplier(ID, name, email, num, address);
            }
        }
    }

    public void updateRecipeList() {
        tblInventory.putClientProperty("terminateEditOnFocusLost", Boolean.TRUE);
        if (tblInventory.isEditing()) {
            tblInventory.getCellEditor().cancelCellEditing();
        }

        int confirm = JOptionPane.showConfirmDialog(null, "Are you sure?", null, JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            int rowCount = tableRecipeList.getModel().getRowCount();
            for (int i = 0; i < rowCount; i++) {
                String recipeID = tableRecipeList.getModel().getValueAt(i, 0).toString();
                String inventoryID = tableRecipeList.getModel().getValueAt(i, 2).toString();
                String qty = tableRecipeList.getModel().getValueAt(i, 4).toString();
                system.updateRecipeList(inventoryID, recipeID, qty);
            }
        }
    }

    public void updateSupplier() {
        //stops editting of table cell to allow button event
        tblInventory.putClientProperty("terminateEditOnFocusLost", Boolean.TRUE);
        if (tblInventory.isEditing()) {
            tblInventory.getCellEditor().cancelCellEditing();
        }
        //Creates database manager object 

        int confirm = JOptionPane.showConfirmDialog(null, "Are you sure?", null, JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            int rowCount = tblInventory.getModel().getRowCount();
            for (int r = 0; r < rowCount; r++) {
                Object ID = tblInventory.getModel().getValueAt(r, 0);
                Object item = tblInventory.getModel().getValueAt(r, 1);
                Object qty = tblInventory.getModel().getValueAt(r, 3);
                Object itemT = tblInventory.getModel().getValueAt(r, 4);
                Object itemL = tblInventory.getModel().getValueAt(r, 5);
                //Pass table contents to database update code
                system.updateInventory(ID, item, qty, itemT, itemL);
            }
        }
    }

    public void updateStockOrder() {
        int confirm = JOptionPane.showConfirmDialog(null, "Are you sure?", null, JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            String orderID = tblOrderHistory.getValueAt(tblOrderHistory.getSelectedRow(), 0).toString();
            String item = tblOrderHistory.getValueAt(tblOrderHistory.getSelectedRow(), 1).toString();
            String qty = tblOrderHistory.getValueAt(tblOrderHistory.getSelectedRow(), 5).toString();
            System.out.println(qty);
            system.updateConfirmOrder(orderID);
            system.updateInventoryQty(item, qty, "+");
        }
    }

    public int getID() {
        int id = (int) tblInventory.getValueAt(tblInventory.getSelectedRow(), 0);
        return id;
    }

    public int getIDrecipe() {
        int id = (int) tableRecipe.getValueAt(tableRecipe.getSelectedRow(), 0);
        return id;
    }

    public int getIDrecipeListRecipe() {
        int id = (int) tableRecipeList.getValueAt(tableRecipeList.getSelectedRow(), 0);
        return id;
    }

    public int getIDrecipeListInventory() {
        int id = (int) tableRecipeList.getValueAt(tableRecipeList.getSelectedRow(), 2);
        return id;
    }

    public int getIDsupplier() {
        int id = (int) tblSupplier.getValueAt(tblSupplier.getSelectedRow(), 0);
        return id;
    }

    public JTable getOrderTable() {
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
        currentEmail.setText(settings.getEmail());
    }

    public void setEmailPassword() {
        jPasswordField1.setText(settings.getEmailPassword());
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
        return currentEmail.getText();
    }

    public String getEmailPassword() {
        return jPasswordField1.getText();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        TabbedPanel = new JTabbedPane(
            JTabbedPane.TOP, JTabbedPane.SCROLL_TAB_LAYOUT);
        TabbedPanel.setUI(new javax.swing.plaf.basic.BasicTabbedPaneUI() {
            @Override
            protected int calculateTabHeight(
                int tabPlacement, int tabIndex, int fontHeight) {
                return 50;
            }
            @Override
            protected int calculateTabWidth(
                int tabPlacement, int tabIndex, FontMetrics metrics) {
                return 100;
            }

            @Override
            protected void paintTab(
                Graphics g, int tabPlacement, Rectangle[] rects, int tabIndex,
                Rectangle iconRect, Rectangle textRect) {
                rects[tabIndex].height=50;
                rects[tabIndex].width=100;
                super.paintTab(g, tabPlacement, rects, tabIndex, iconRect, textRect);
            }
        });
        ;
        Dashboard = new javax.swing.JPanel();
        lblImage = new javax.swing.JLabel();
        buttonClose = new javax.swing.JButton();
        pnlLeft = new javax.swing.JPanel();
        buttonBookings = new javax.swing.JButton();
        buttonEvents = new javax.swing.JButton();
        buttonSpecials = new javax.swing.JButton();
        buttonPromotions = new javax.swing.JButton();
        lblVersion = new javax.swing.JLabel();
        pnlPanel = new javax.swing.JPanel();
        lblDate = new javax.swing.JLabel();
        buttonLogin = new javax.swing.JButton();
        lblClock = new javax.swing.JLabel();
        buttonLogOut = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        Orders = new javax.swing.JPanel();
        pnlLayout = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        pnlViewOrder = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        pnlManageOrder = new javax.swing.JPanel();
        buttonReprint = new javax.swing.JButton();
        buttonLayout = new javax.swing.JButton();
        buttonTakeAway = new javax.swing.JButton();
        buttonReprint2 = new javax.swing.JButton();
        buttonReprint3 = new javax.swing.JButton();
        Inventory = new javax.swing.JPanel();
        textboxSearch = new javax.swing.JTextField();
        lblSearch = new javax.swing.JLabel();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        pnlInventory = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblInventory = new javax.swing.JTable();
        buttonAdd = new javax.swing.JButton();
        buttonEdit = new javax.swing.JButton();
        buttonDelete = new javax.swing.JButton();
        buttonPrint = new javax.swing.JButton();
        buttonEdit1 = new javax.swing.JButton();
        pnlRecipe = new javax.swing.JPanel();
        buttonRecipeAdd = new javax.swing.JButton();
        buttonRecipeDelete = new javax.swing.JButton();
        buttonRecipeEdit = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        tableRecipe = new javax.swing.JTable();
        buttonRecipeEdit3 = new javax.swing.JButton();
        pnlRecipeList = new javax.swing.JPanel();
        buttonRecipeDelete2 = new javax.swing.JButton();
        buttonRecipeEdit2 = new javax.swing.JButton();
        jScrollPane6 = new javax.swing.JScrollPane();
        tableRecipeList = new javax.swing.JTable();
        buttonRecipeDelete3 = new javax.swing.JButton();
        pnlSupplier = new javax.swing.JPanel();
        buttonRecipeAdd1 = new javax.swing.JButton();
        buttonRecipeDelete1 = new javax.swing.JButton();
        buttonRecipeEdit1 = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        tblSupplier = new javax.swing.JTable();
        buttonRecipeEdit4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        Management = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblOrderHistory = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        buttonOrderHistory = new javax.swing.JButton();
        buttonAlert = new javax.swing.JButton();
        buttonEmail = new javax.swing.JButton();
        buttonMakeOrder = new javax.swing.JButton();
        buttonReports = new javax.swing.JButton();
        buttonEmployee = new javax.swing.JButton();
        buttonManageSpecials = new javax.swing.JButton();
        buttonEmployee1 = new javax.swing.JButton();
        Settings = new javax.swing.JPanel();
        lblSettings = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jCheckBox1 = new javax.swing.JCheckBox();
        lblLogo1 = new javax.swing.JLabel();
        lblLogo = new javax.swing.JLabel();
        lblScreen = new javax.swing.JLabel();
        comboBoxSceen = new javax.swing.JComboBox<String>();
        comboBoxLogo = new javax.swing.JComboBox<String>();
        comboBoxTableCount = new javax.swing.JComboBox<String>();
        lblSettings1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        currentEmail2 = new javax.swing.JLabel();
        currentEmail1 = new javax.swing.JLabel();
        currentEmail = new javax.swing.JLabel();
        jPasswordField1 = new javax.swing.JPasswordField();
        jButton10 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        lblSettings3 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jButton9 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        lblSettings2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(75, 75, 75));
        setLocation(new java.awt.Point(0, 0));
        setUndecorated(true);

        TabbedPanel.setBackground(new java.awt.Color(75, 75, 75));
        TabbedPanel.setForeground(new java.awt.Color(255, 255, 255));
        TabbedPanel.setTabPlacement(javax.swing.JTabbedPane.LEFT);
        TabbedPanel.setOpaque(true);
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

        Dashboard.setBackground(new java.awt.Color(255, 255, 255));

        lblImage.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblImage.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Logo_White.png"))); // NOI18N
        lblImage.setToolTipText("");

        buttonClose.setBackground(new java.awt.Color(75, 75, 75));
        buttonClose.setForeground(new java.awt.Color(255, 255, 255));
        buttonClose.setText("Close");
        buttonClose.setAlignmentY(0.0F);
        buttonClose.setContentAreaFilled(false);
        buttonClose.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        buttonClose.setOpaque(true);
        buttonClose.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        buttonClose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonCloseActionPerformed(evt);
            }
        });

        pnlLeft.setBackground(new java.awt.Color(75, 75, 75));

        buttonBookings.setBackground(new java.awt.Color(0, 138, 231));
        buttonBookings.setForeground(new java.awt.Color(255, 255, 255));
        buttonBookings.setText("Bookings");
        buttonBookings.setContentAreaFilled(false);
        buttonBookings.setOpaque(true);
        buttonBookings.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonBookingsActionPerformed(evt);
            }
        });

        buttonEvents.setBackground(new java.awt.Color(0, 138, 231));
        buttonEvents.setForeground(new java.awt.Color(255, 255, 255));
        buttonEvents.setText("Reminders");
        buttonEvents.setContentAreaFilled(false);
        buttonEvents.setOpaque(true);
        buttonEvents.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonEventsActionPerformed(evt);
            }
        });

        buttonSpecials.setBackground(new java.awt.Color(0, 138, 231));
        buttonSpecials.setForeground(new java.awt.Color(255, 255, 255));
        buttonSpecials.setText("Specials");
        buttonSpecials.setContentAreaFilled(false);
        buttonSpecials.setOpaque(true);
        buttonSpecials.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonSpecialsActionPerformed(evt);
            }
        });

        buttonPromotions.setBackground(new java.awt.Color(0, 138, 231));
        buttonPromotions.setForeground(new java.awt.Color(255, 255, 255));
        buttonPromotions.setText("Calendar");
        buttonPromotions.setContentAreaFilled(false);
        buttonPromotions.setOpaque(true);
        buttonPromotions.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonPromotionsActionPerformed(evt);
            }
        });

        lblVersion.setForeground(new java.awt.Color(255, 255, 255));
        lblVersion.setText("Version 1.0.0");

        javax.swing.GroupLayout pnlLeftLayout = new javax.swing.GroupLayout(pnlLeft);
        pnlLeft.setLayout(pnlLeftLayout);
        pnlLeftLayout.setHorizontalGroup(
            pnlLeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlLeftLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlLeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(buttonEvents, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(buttonPromotions, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(buttonSpecials, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(buttonBookings, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(pnlLeftLayout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addComponent(lblVersion)
                .addContainerGap(84, Short.MAX_VALUE))
        );
        pnlLeftLayout.setVerticalGroup(
            pnlLeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlLeftLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(buttonBookings, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(buttonSpecials, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(buttonPromotions, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(buttonEvents, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(209, 209, 209)
                .addComponent(lblVersion)
                .addGap(20, 20, 20))
        );

        pnlPanel.setBackground(new java.awt.Color(255, 255, 255));

        lblDate.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lblDate.setText("Date");

        buttonLogin.setBackground(new java.awt.Color(75, 75, 75));
        buttonLogin.setForeground(new java.awt.Color(255, 255, 255));
        buttonLogin.setText("Login");
        buttonLogin.setContentAreaFilled(false);
        buttonLogin.setOpaque(true);
        buttonLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonLoginActionPerformed(evt);
            }
        });

        lblClock.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        lblClock.setText("Clock");

        buttonLogOut.setBackground(new java.awt.Color(75, 75, 75));
        buttonLogOut.setForeground(new java.awt.Color(255, 255, 255));
        buttonLogOut.setText("Log out");
        buttonLogOut.setContentAreaFilled(false);
        buttonLogOut.setOpaque(true);
        buttonLogOut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonLogOutActionPerformed(evt);
            }
        });

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Logo_help.png"))); // NOI18N
        jButton3.setBorderPainted(false);
        jButton3.setContentAreaFilled(false);
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlPanelLayout = new javax.swing.GroupLayout(pnlPanel);
        pnlPanel.setLayout(pnlPanelLayout);
        pnlPanelLayout.setHorizontalGroup(
            pnlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlPanelLayout.createSequentialGroup()
                        .addGap(0, 13, Short.MAX_VALUE)
                        .addComponent(jButton3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblDate)
                            .addComponent(lblClock))
                        .addGap(25, 25, 25))
                    .addComponent(buttonLogin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(buttonLogOut, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        pnlPanelLayout.setVerticalGroup(
            pnlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlPanelLayout.createSequentialGroup()
                .addGroup(pnlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lblClock, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlPanelLayout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(buttonLogin)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(buttonLogOut)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 376, Short.MAX_VALUE)
                .addComponent(lblDate, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19))
        );

        javax.swing.GroupLayout DashboardLayout = new javax.swing.GroupLayout(Dashboard);
        Dashboard.setLayout(DashboardLayout);
        DashboardLayout.setHorizontalGroup(
            DashboardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(DashboardLayout.createSequentialGroup()
                .addComponent(pnlLeft, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(DashboardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(DashboardLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblImage, javax.swing.GroupLayout.DEFAULT_SIZE, 353, Short.MAX_VALUE))
                    .addGroup(DashboardLayout.createSequentialGroup()
                        .addGap(101, 101, 101)
                        .addComponent(buttonClose, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(81, 81, 81)))
                .addGap(112, 112, 112)
                .addComponent(pnlPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        DashboardLayout.setVerticalGroup(
            DashboardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(DashboardLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(pnlLeft, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(DashboardLayout.createSequentialGroup()
                .addComponent(lblImage, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(30, 30, 30)
                .addComponent(buttonClose)
                .addGap(25, 25, 25))
        );

        TabbedPanel.addTab("Dashboard", Dashboard);

        pnlLayout.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        pnlLayout.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        javax.swing.GroupLayout pnlLayoutLayout = new javax.swing.GroupLayout(pnlLayout);
        pnlLayout.setLayout(pnlLayoutLayout);
        pnlLayoutLayout.setHorizontalGroup(
            pnlLayoutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 567, Short.MAX_VALUE)
        );
        pnlLayoutLayout.setVerticalGroup(
            pnlLayoutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        pnlViewOrder.setBackground(new java.awt.Color(255, 255, 255));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
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
        jTable1.setGridColor(new java.awt.Color(204, 204, 204));
        jScrollPane2.setViewportView(jTable1);

        jButton1.setBackground(new java.awt.Color(75, 75, 75));
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Refresh");
        jButton1.setContentAreaFilled(false);
        jButton1.setOpaque(true);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlViewOrderLayout = new javax.swing.GroupLayout(pnlViewOrder);
        pnlViewOrder.setLayout(pnlViewOrderLayout);
        pnlViewOrderLayout.setHorizontalGroup(
            pnlViewOrderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlViewOrderLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(pnlViewOrderLayout.createSequentialGroup()
                .addGap(88, 88, 88)
                .addComponent(jButton1)
                .addContainerGap(92, Short.MAX_VALUE))
        );
        pnlViewOrderLayout.setVerticalGroup(
            pnlViewOrderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlViewOrderLayout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 370, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton1)
                .addGap(0, 119, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("View Orders", pnlViewOrder);

        pnlManageOrder.setBackground(new java.awt.Color(255, 255, 255));

        buttonReprint.setBackground(new java.awt.Color(0, 138, 231));
        buttonReprint.setForeground(new java.awt.Color(255, 255, 255));
        buttonReprint.setText("Reprint");
        buttonReprint.setContentAreaFilled(false);
        buttonReprint.setOpaque(true);
        buttonReprint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonReprintActionPerformed(evt);
            }
        });

        buttonLayout.setBackground(new java.awt.Color(0, 138, 231));
        buttonLayout.setForeground(new java.awt.Color(255, 255, 255));
        buttonLayout.setText("Open Draw");
        buttonLayout.setContentAreaFilled(false);
        buttonLayout.setOpaque(true);
        buttonLayout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonLayoutActionPerformed(evt);
            }
        });

        buttonTakeAway.setBackground(new java.awt.Color(0, 138, 231));
        buttonTakeAway.setForeground(new java.awt.Color(255, 255, 255));
        buttonTakeAway.setText("Take Away");
        buttonTakeAway.setContentAreaFilled(false);
        buttonTakeAway.setOpaque(true);
        buttonTakeAway.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonTakeAwayActionPerformed(evt);
            }
        });

        buttonReprint2.setBackground(new java.awt.Color(0, 138, 231));
        buttonReprint2.setForeground(new java.awt.Color(255, 255, 255));
        buttonReprint2.setText("Setup TCP");
        buttonReprint2.setContentAreaFilled(false);
        buttonReprint2.setOpaque(true);
        buttonReprint2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonReprint2ActionPerformed(evt);
            }
        });

        buttonReprint3.setBackground(new java.awt.Color(0, 138, 231));
        buttonReprint3.setForeground(new java.awt.Color(255, 255, 255));
        buttonReprint3.setText("Test TCP");
        buttonReprint3.setContentAreaFilled(false);
        buttonReprint3.setOpaque(true);
        buttonReprint3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonReprint3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlManageOrderLayout = new javax.swing.GroupLayout(pnlManageOrder);
        pnlManageOrder.setLayout(pnlManageOrderLayout);
        pnlManageOrderLayout.setHorizontalGroup(
            pnlManageOrderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlManageOrderLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlManageOrderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(buttonReprint, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(buttonTakeAway, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(buttonReprint2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(buttonReprint3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(buttonLayout, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 231, Short.MAX_VALUE))
                .addContainerGap())
        );
        pnlManageOrderLayout.setVerticalGroup(
            pnlManageOrderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlManageOrderLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(buttonTakeAway, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(buttonLayout, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(buttonReprint, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(buttonReprint2, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(buttonReprint3, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(213, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Manage", pnlManageOrder);

        javax.swing.GroupLayout OrdersLayout = new javax.swing.GroupLayout(Orders);
        Orders.setLayout(OrdersLayout);
        OrdersLayout.setHorizontalGroup(
            OrdersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, OrdersLayout.createSequentialGroup()
                .addComponent(pnlLayout, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        OrdersLayout.setVerticalGroup(
            OrdersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
            .addComponent(pnlLayout, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        TabbedPanel.addTab("Orders", Orders);

        Inventory.setBackground(new java.awt.Color(255, 255, 255));

        textboxSearch.setBackground(new java.awt.Color(240, 240, 240));

        lblSearch.setText("Search ");

        pnlInventory.setBackground(new java.awt.Color(255, 255, 255));

        tblInventory.setModel(new javax.swing.table.DefaultTableModel(
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
        tblInventory.setGridColor(new java.awt.Color(204, 204, 204));
        jScrollPane3.setViewportView(tblInventory);
        if (tblInventory.getColumnModel().getColumnCount() > 0) {
            tblInventory.getColumnModel().getColumn(2).setResizable(false);
            tblInventory.getColumnModel().getColumn(3).setHeaderValue("Cost per Unit");
            tblInventory.getColumnModel().getColumn(4).setHeaderValue("Distrubutor ID ");
            tblInventory.getColumnModel().getColumn(5).setHeaderValue("Distrubutor Name");
        }

        buttonAdd.setBackground(new java.awt.Color(0, 138, 231));
        buttonAdd.setForeground(new java.awt.Color(255, 255, 255));
        buttonAdd.setText("Add");
        buttonAdd.setToolTipText("insert into db");
        buttonAdd.setContentAreaFilled(false);
        buttonAdd.setOpaque(true);
        buttonAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonAddActionPerformed(evt);
            }
        });

        buttonEdit.setBackground(new java.awt.Color(0, 138, 231));
        buttonEdit.setForeground(new java.awt.Color(255, 255, 255));
        buttonEdit.setText("Edit");
        buttonEdit.setContentAreaFilled(false);
        buttonEdit.setOpaque(true);
        buttonEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonEditActionPerformed(evt);
            }
        });

        buttonDelete.setBackground(new java.awt.Color(0, 138, 231));
        buttonDelete.setForeground(new java.awt.Color(255, 255, 255));
        buttonDelete.setText("Delete");
        buttonDelete.setContentAreaFilled(false);
        buttonDelete.setOpaque(true);
        buttonDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonDeleteActionPerformed(evt);
            }
        });

        buttonPrint.setBackground(new java.awt.Color(75, 75, 75));
        buttonPrint.setForeground(new java.awt.Color(255, 255, 255));
        buttonPrint.setText("Print");
        buttonPrint.setContentAreaFilled(false);
        buttonPrint.setOpaque(true);
        buttonPrint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonPrintActionPerformed(evt);
            }
        });

        buttonEdit1.setBackground(new java.awt.Color(0, 138, 231));
        buttonEdit1.setForeground(java.awt.Color.white);
        buttonEdit1.setText("Shrinkage");
        buttonEdit1.setContentAreaFilled(false);
        buttonEdit1.setOpaque(true);
        buttonEdit1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonEdit1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlInventoryLayout = new javax.swing.GroupLayout(pnlInventory);
        pnlInventory.setLayout(pnlInventoryLayout);
        pnlInventoryLayout.setHorizontalGroup(
            pnlInventoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlInventoryLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlInventoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(buttonAdd, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(buttonDelete, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(buttonEdit, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(buttonPrint, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(buttonEdit1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 715, Short.MAX_VALUE))
        );
        pnlInventoryLayout.setVerticalGroup(
            pnlInventoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlInventoryLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(buttonEdit1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(buttonAdd)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(buttonDelete)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(buttonEdit)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(buttonPrint)
                .addContainerGap())
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 486, Short.MAX_VALUE)
        );

        jTabbedPane2.addTab("Inventory", pnlInventory);

        pnlRecipe.setBackground(new java.awt.Color(255, 255, 255));

        buttonRecipeAdd.setBackground(new java.awt.Color(0, 138, 231));
        buttonRecipeAdd.setForeground(new java.awt.Color(255, 255, 255));
        buttonRecipeAdd.setText("Add");
        buttonRecipeAdd.setContentAreaFilled(false);
        buttonRecipeAdd.setOpaque(true);
        buttonRecipeAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonRecipeAddActionPerformed(evt);
            }
        });

        buttonRecipeDelete.setBackground(new java.awt.Color(0, 138, 231));
        buttonRecipeDelete.setForeground(new java.awt.Color(255, 255, 255));
        buttonRecipeDelete.setText("Delete");
        buttonRecipeDelete.setContentAreaFilled(false);
        buttonRecipeDelete.setOpaque(true);
        buttonRecipeDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonRecipeDeleteActionPerformed(evt);
            }
        });

        buttonRecipeEdit.setBackground(new java.awt.Color(0, 138, 231));
        buttonRecipeEdit.setForeground(new java.awt.Color(255, 255, 255));
        buttonRecipeEdit.setText("Edit");
        buttonRecipeEdit.setContentAreaFilled(false);
        buttonRecipeEdit.setOpaque(true);
        buttonRecipeEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonRecipeEditActionPerformed(evt);
            }
        });

        tableRecipe.setModel(new javax.swing.table.DefaultTableModel(
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
        tableRecipe.setGridColor(new java.awt.Color(204, 204, 204));
        jScrollPane4.setViewportView(tableRecipe);
        if (tableRecipe.getColumnModel().getColumnCount() > 0) {
            tableRecipe.getColumnModel().getColumn(2).setResizable(false);
        }

        buttonRecipeEdit3.setBackground(new java.awt.Color(75, 75, 75));
        buttonRecipeEdit3.setForeground(new java.awt.Color(255, 255, 255));
        buttonRecipeEdit3.setText("Print");
        buttonRecipeEdit3.setContentAreaFilled(false);
        buttonRecipeEdit3.setOpaque(true);
        buttonRecipeEdit3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonRecipeEdit3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlRecipeLayout = new javax.swing.GroupLayout(pnlRecipe);
        pnlRecipe.setLayout(pnlRecipeLayout);
        pnlRecipeLayout.setHorizontalGroup(
            pnlRecipeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlRecipeLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlRecipeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(buttonRecipeAdd, javax.swing.GroupLayout.DEFAULT_SIZE, 79, Short.MAX_VALUE)
                    .addComponent(buttonRecipeDelete, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(buttonRecipeEdit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(buttonRecipeEdit3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 715, Short.MAX_VALUE))
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(buttonRecipeEdit3)
                .addContainerGap())
            .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 486, Short.MAX_VALUE)
        );

        jTabbedPane2.addTab("Recipe", pnlRecipe);

        pnlRecipeList.setBackground(new java.awt.Color(255, 255, 255));

        buttonRecipeDelete2.setBackground(new java.awt.Color(0, 138, 231));
        buttonRecipeDelete2.setForeground(new java.awt.Color(255, 255, 255));
        buttonRecipeDelete2.setText("Delete");
        buttonRecipeDelete2.setContentAreaFilled(false);
        buttonRecipeDelete2.setOpaque(true);
        buttonRecipeDelete2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonRecipeDelete2ActionPerformed(evt);
            }
        });

        buttonRecipeEdit2.setBackground(new java.awt.Color(0, 138, 231));
        buttonRecipeEdit2.setForeground(new java.awt.Color(255, 255, 255));
        buttonRecipeEdit2.setText("Edit");
        buttonRecipeEdit2.setContentAreaFilled(false);
        buttonRecipeEdit2.setOpaque(true);
        buttonRecipeEdit2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonRecipeEdit2ActionPerformed(evt);
            }
        });

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

        buttonRecipeDelete3.setBackground(new java.awt.Color(75, 75, 75));
        buttonRecipeDelete3.setForeground(new java.awt.Color(255, 255, 255));
        buttonRecipeDelete3.setText("Print");
        buttonRecipeDelete3.setContentAreaFilled(false);
        buttonRecipeDelete3.setOpaque(true);
        buttonRecipeDelete3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonRecipeDelete3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlRecipeListLayout = new javax.swing.GroupLayout(pnlRecipeList);
        pnlRecipeList.setLayout(pnlRecipeListLayout);
        pnlRecipeListLayout.setHorizontalGroup(
            pnlRecipeListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlRecipeListLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlRecipeListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(buttonRecipeEdit2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(buttonRecipeDelete2, javax.swing.GroupLayout.DEFAULT_SIZE, 79, Short.MAX_VALUE)
                    .addComponent(buttonRecipeDelete3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 715, Short.MAX_VALUE))
        );
        pnlRecipeListLayout.setVerticalGroup(
            pnlRecipeListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 486, Short.MAX_VALUE)
            .addGroup(pnlRecipeListLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(buttonRecipeDelete2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(buttonRecipeEdit2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(buttonRecipeDelete3)
                .addContainerGap())
        );

        jTabbedPane2.addTab("Recipe List", pnlRecipeList);

        pnlSupplier.setBackground(new java.awt.Color(255, 255, 255));

        buttonRecipeAdd1.setBackground(new java.awt.Color(0, 138, 231));
        buttonRecipeAdd1.setForeground(new java.awt.Color(255, 255, 255));
        buttonRecipeAdd1.setText("Add");
        buttonRecipeAdd1.setContentAreaFilled(false);
        buttonRecipeAdd1.setOpaque(true);
        buttonRecipeAdd1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonRecipeAdd1ActionPerformed(evt);
            }
        });

        buttonRecipeDelete1.setBackground(new java.awt.Color(0, 138, 231));
        buttonRecipeDelete1.setForeground(new java.awt.Color(255, 255, 255));
        buttonRecipeDelete1.setText("Delete");
        buttonRecipeDelete1.setContentAreaFilled(false);
        buttonRecipeDelete1.setOpaque(true);
        buttonRecipeDelete1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonRecipeDelete1ActionPerformed(evt);
            }
        });

        buttonRecipeEdit1.setBackground(new java.awt.Color(0, 138, 231));
        buttonRecipeEdit1.setForeground(new java.awt.Color(255, 255, 255));
        buttonRecipeEdit1.setText("Edit");
        buttonRecipeEdit1.setContentAreaFilled(false);
        buttonRecipeEdit1.setOpaque(true);
        buttonRecipeEdit1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonRecipeEdit1ActionPerformed(evt);
            }
        });

        tblSupplier.setModel(new javax.swing.table.DefaultTableModel(
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
        tblSupplier.setGridColor(new java.awt.Color(204, 204, 204));
        jScrollPane5.setViewportView(tblSupplier);
        if (tblSupplier.getColumnModel().getColumnCount() > 0) {
            tblSupplier.getColumnModel().getColumn(2).setResizable(false);
        }

        buttonRecipeEdit4.setBackground(new java.awt.Color(75, 75, 75));
        buttonRecipeEdit4.setForeground(new java.awt.Color(255, 255, 255));
        buttonRecipeEdit4.setText("Print");
        buttonRecipeEdit4.setContentAreaFilled(false);
        buttonRecipeEdit4.setOpaque(true);
        buttonRecipeEdit4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonRecipeEdit4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlSupplierLayout = new javax.swing.GroupLayout(pnlSupplier);
        pnlSupplier.setLayout(pnlSupplierLayout);
        pnlSupplierLayout.setHorizontalGroup(
            pnlSupplierLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlSupplierLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlSupplierLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(buttonRecipeAdd1, javax.swing.GroupLayout.DEFAULT_SIZE, 79, Short.MAX_VALUE)
                    .addComponent(buttonRecipeDelete1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(buttonRecipeEdit1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(buttonRecipeEdit4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 715, Short.MAX_VALUE))
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(buttonRecipeEdit4)
                .addContainerGap())
            .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 486, Short.MAX_VALUE)
        );

        jTabbedPane2.addTab("Supplier", pnlSupplier);

        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Logo_help.png"))); // NOI18N
        jButton5.setBorder(null);
        jButton5.setBorderPainted(false);
        jButton5.setContentAreaFilled(false);
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton6.setBackground(new java.awt.Color(0, 138, 241));
        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Logo_search.png"))); // NOI18N
        jButton6.setBorder(null);
        jButton6.setBorderPainted(false);
        jButton6.setContentAreaFilled(false);
        jButton6.setMargin(new java.awt.Insets(10, 14, 10, 14));
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
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
                        .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblSearch)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(1, 1, 1)
                        .addComponent(textboxSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(8, 8, 8)))
                .addContainerGap())
        );
        InventoryLayout.setVerticalGroup(
            InventoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(InventoryLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(InventoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(InventoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(textboxSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblSearch))
                    .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane2))
        );

        TabbedPanel.addTab("Database", Inventory);

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

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        buttonOrderHistory.setBackground(new java.awt.Color(0, 138, 231));
        buttonOrderHistory.setForeground(new java.awt.Color(255, 255, 255));
        buttonOrderHistory.setText("Confirm Order");
        buttonOrderHistory.setContentAreaFilled(false);
        buttonOrderHistory.setOpaque(true);
        buttonOrderHistory.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonOrderHistoryActionPerformed(evt);
            }
        });

        buttonAlert.setBackground(new java.awt.Color(0, 138, 231));
        buttonAlert.setForeground(new java.awt.Color(255, 255, 255));
        buttonAlert.setText("Alerts");
        buttonAlert.setContentAreaFilled(false);
        buttonAlert.setOpaque(true);

        buttonEmail.setBackground(new java.awt.Color(0, 138, 231));
        buttonEmail.setForeground(new java.awt.Color(255, 255, 255));
        buttonEmail.setText("Email");
        buttonEmail.setContentAreaFilled(false);
        buttonEmail.setOpaque(true);
        buttonEmail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonEmailActionPerformed(evt);
            }
        });

        buttonMakeOrder.setBackground(new java.awt.Color(0, 138, 231));
        buttonMakeOrder.setForeground(new java.awt.Color(255, 255, 255));
        buttonMakeOrder.setText("Make Order");
        buttonMakeOrder.setContentAreaFilled(false);
        buttonMakeOrder.setOpaque(true);
        buttonMakeOrder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonMakeOrderActionPerformed(evt);
            }
        });

        buttonReports.setBackground(new java.awt.Color(0, 138, 231));
        buttonReports.setForeground(new java.awt.Color(255, 255, 255));
        buttonReports.setText("Report");
        buttonReports.setContentAreaFilled(false);
        buttonReports.setOpaque(true);
        buttonReports.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonReportsActionPerformed(evt);
            }
        });

        buttonEmployee.setBackground(new java.awt.Color(0, 138, 231));
        buttonEmployee.setForeground(new java.awt.Color(255, 255, 255));
        buttonEmployee.setText("Employees");
        buttonEmployee.setContentAreaFilled(false);
        buttonEmployee.setOpaque(true);
        buttonEmployee.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonEmployeeActionPerformed(evt);
            }
        });

        buttonManageSpecials.setBackground(new java.awt.Color(75, 75, 75));
        buttonManageSpecials.setForeground(new java.awt.Color(255, 255, 255));
        buttonManageSpecials.setText("Manage Specials");
        buttonManageSpecials.setContentAreaFilled(false);
        buttonManageSpecials.setOpaque(true);
        buttonManageSpecials.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonManageSpecialsActionPerformed(evt);
            }
        });

        buttonEmployee1.setBackground(new java.awt.Color(0, 138, 231));
        buttonEmployee1.setForeground(new java.awt.Color(255, 255, 255));
        buttonEmployee1.setText("View Logs");
        buttonEmployee1.setContentAreaFilled(false);
        buttonEmployee1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonEmployee1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 187, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(buttonAlert, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(buttonOrderHistory, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(buttonMakeOrder, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(buttonEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(buttonReports, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(buttonEmployee, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(buttonManageSpecials, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(buttonEmployee1, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 547, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                    .addContainerGap()
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
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 266, Short.MAX_VALUE)
                    .addComponent(buttonManageSpecials)
                    .addContainerGap()))
        );

        javax.swing.GroupLayout ManagementLayout = new javax.swing.GroupLayout(Management);
        Management.setLayout(ManagementLayout);
        ManagementLayout.setHorizontalGroup(
            ManagementLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ManagementLayout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 628, Short.MAX_VALUE)
                .addGap(12, 12, 12))
        );
        ManagementLayout.setVerticalGroup(
            ManagementLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(ManagementLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 450, Short.MAX_VALUE)
                .addGap(90, 90, 90))
        );

        TabbedPanel.addTab("Management", Management);

        lblSettings.setText("Settings");

        jButton2.setBackground(new java.awt.Color(75, 75, 75));
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("Save");
        jButton2.setContentAreaFilled(false);
        jButton2.setOpaque(true);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jCheckBox1.setSelected(true);
        jCheckBox1.setText("Enable KeyPad");
        jCheckBox1.setContentAreaFilled(false);

        lblLogo1.setText("Number of Tabels");

        lblLogo.setText("Set Logo");

        lblScreen.setText("Resolution");

        comboBoxSceen.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Windowed Screen", "Fullscreen" }));
        comboBoxSceen.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                comboBoxSceenItemStateChanged(evt);
            }
        });

        comboBoxLogo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Default", "Demo 1", "Demo 2" }));
        comboBoxLogo.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                comboBoxLogoItemStateChanged(evt);
            }
        });

        comboBoxTableCount.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20" }));
        comboBoxTableCount.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                comboBoxTableCountItemStateChanged(evt);
            }
        });

        lblSettings1.setText("General Settings");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblSettings1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jCheckBox1)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblScreen)
                            .addComponent(lblLogo)
                            .addComponent(lblLogo1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(comboBoxTableCount, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(comboBoxLogo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(comboBoxSceen, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(0, 59, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblSettings1)
                .addGap(27, 27, 27)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(comboBoxSceen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblScreen))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(comboBoxLogo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblLogo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(comboBoxTableCount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblLogo1))
                .addGap(18, 18, 18)
                .addComponent(jCheckBox1)
                .addContainerGap(73, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        currentEmail2.setText("Email Password");

        currentEmail1.setText("Email Adress");

        currentEmail.setText("Email");

        jPasswordField1.setText("jPasswordField1");
        jPasswordField1.setBorder(null);
        jPasswordField1.setEnabled(false);
        jPasswordField1.setOpaque(false);

        jButton10.setBackground(new java.awt.Color(0, 138, 231));
        jButton10.setForeground(new java.awt.Color(255, 255, 255));
        jButton10.setText("Change Email Password");
        jButton10.setContentAreaFilled(false);
        jButton10.setOpaque(true);
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        jButton8.setBackground(new java.awt.Color(0, 138, 231));
        jButton8.setForeground(new java.awt.Color(255, 255, 255));
        jButton8.setText("Change Email ");
        jButton8.setContentAreaFilled(false);
        jButton8.setOpaque(true);
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        lblSettings3.setText("Email Settings");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(lblSettings3)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(currentEmail1, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(currentEmail, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                                .addComponent(currentEmail2, javax.swing.GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPasswordField1)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton10))
                        .addGap(13, 13, 13))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(lblSettings3)
                .addGap(51, 51, 51)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton8)
                    .addComponent(currentEmail)
                    .addComponent(currentEmail1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton10)
                    .addComponent(currentEmail2)
                    .addComponent(jPasswordField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(84, Short.MAX_VALUE))
        );

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jButton9.setBackground(new java.awt.Color(0, 138, 231));
        jButton9.setForeground(new java.awt.Color(255, 255, 255));
        jButton9.setText("Reset Logs");
        jButton9.setContentAreaFilled(false);
        jButton9.setOpaque(true);
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        jButton7.setBackground(new java.awt.Color(0, 138, 231));
        jButton7.setForeground(new java.awt.Color(255, 255, 255));
        jButton7.setText("Restore Database");
        jButton7.setContentAreaFilled(false);
        jButton7.setOpaque(true);
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jButton4.setBackground(new java.awt.Color(0, 138, 231));
        jButton4.setForeground(new java.awt.Color(255, 255, 255));
        jButton4.setText("Backup Database");
        jButton4.setContentAreaFilled(false);
        jButton4.setOpaque(true);
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        lblSettings2.setText("Database Settings");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(72, 72, 72)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lblSettings2)))
                .addContainerGap(87, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addComponent(lblSettings2, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton9)
                .addContainerGap(50, Short.MAX_VALUE))
        );

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
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(SettingsLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(SettingsLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(129, Short.MAX_VALUE))
        );
        SettingsLayout.setVerticalGroup(
            SettingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(SettingsLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(lblSettings)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(SettingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 24, Short.MAX_VALUE)
                .addComponent(jButton2)
                .addContainerGap())
        );

        TabbedPanel.addTab("Settings", Settings);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(TabbedPanel)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(TabbedPanel, javax.swing.GroupLayout.Alignment.TRAILING)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents
    private void buttonEmployeeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonEmployeeActionPerformed
        EmployeeForm emp = new EmployeeForm();
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
            specials = new Specials();
            specials.setVisible(true);
        } catch (RuntimeException ignore) {
        }
    }//GEN-LAST:event_buttonSpecialsActionPerformed

    private void buttonBookingsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonBookingsActionPerformed
        if (keypadCheck() == true) {
            Keyboard key = new Keyboard();
            key.setLocation(600, 650);
            key.setVisible(true);}
        try {
            booking.setVisible(true);
            specials.setVisible(false);
            calendar.setVisible(false);
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

    private void TabbedPanelStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_TabbedPanelStateChanged
        booking.setVisible(false);
    }//GEN-LAST:event_TabbedPanelStateChanged

    private void TabbedPanelFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_TabbedPanelFocusGained
        booking.setVisible(false);
    }//GEN-LAST:event_TabbedPanelFocusGained

    private void buttonRecipeAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonRecipeAddActionPerformed
        AddDatabase database = new AddDatabase(this);
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
        populateInvnetoryTable();
    }//GEN-LAST:event_buttonEditActionPerformed

    private void buttonAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAddActionPerformed
        AddDatabase database = new AddDatabase(this);
        database.setVisible(true);
        if (keypadCheck() == true) {
            Keyboard k = new Keyboard();
            k.setLocation(350, 530);
            k.setVisible(true);
        }
    }//GEN-LAST:event_buttonAddActionPerformed

    private void buttonRecipeAdd1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonRecipeAdd1ActionPerformed
        AddDatabase database = new AddDatabase(this);
        database.setVisible(true);
        AddDatabase.getTabbedPanel().setSelectedIndex(2);
        if (keypadCheck() == true) {
            Keyboard k = new Keyboard();
            k.setLocation(350, 530);
            k.setVisible(true);
        }
    }//GEN-LAST:event_buttonRecipeAdd1ActionPerformed

    private void buttonDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonDeleteActionPerformed
        int confirm = JOptionPane.showConfirmDialog(null, "Are you sure?", null, JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            system.removeInventory(getID());
            populateInvnetoryTable();
        }
    }//GEN-LAST:event_buttonDeleteActionPerformed

    private void buttonRecipeDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonRecipeDeleteActionPerformed
        int confirm = JOptionPane.showConfirmDialog(null, "Are you sure?", null, JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            system.removeRecipe(getIDrecipe());
            populateRecipeTable();
        }
    }//GEN-LAST:event_buttonRecipeDeleteActionPerformed

    private void buttonRecipeDelete1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonRecipeDelete1ActionPerformed
        int confirm = JOptionPane.showConfirmDialog(null, "Are you sure?", null, JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            system.removeSupplier(getIDsupplier());
            populateSupplierTable();
        }
    }//GEN-LAST:event_buttonRecipeDelete1ActionPerformed

    private void buttonLogOutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonLogOutActionPerformed
        user.createLogout();
    }//GEN-LAST:event_buttonLogOutActionPerformed

    private void buttonLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonLoginActionPerformed
        user.createLogin();
    }//GEN-LAST:event_buttonLoginActionPerformed

    private void buttonMakeOrderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonMakeOrderActionPerformed
        OrderForm newOrder = new OrderForm(this);
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
        settings.updateSettings(getSelectedResolution(), getSelectedTable(), getEmail(), getEmailPassword());
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
            populateInvnetoryTable();
            populateRecipeTable();
            populateRecipeListTable();
            populateSupplierTable();
        }
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        String email = JOptionPane.showInputDialog(null, "Enter new Email Address");
        if ("".equals(email) || email == null) {
        } else {
            currentEmail.setText(email);
        }
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
        int confirm = JOptionPane.showConfirmDialog(null, "Are you sure you want to Delete Logs?", null, JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            logs.refreshLogs();
        }
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        String password = JOptionPane.showInputDialog(null, "Enter new Email password");
        if ("".equals(password) || password == null) {
        } else {
            jPasswordField1.setText(password);
        }
    }//GEN-LAST:event_jButton10ActionPerformed

    private void buttonRecipeDelete2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonRecipeDelete2ActionPerformed
        int confirm = JOptionPane.showConfirmDialog(null, "Are you sure?", null, JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            system.removeRecipeList(getIDrecipeListRecipe(), getIDrecipeListInventory());
        }
    }//GEN-LAST:event_buttonRecipeDelete2ActionPerformed

    private void buttonRecipeEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonRecipeEditActionPerformed
        //Admin login window object created
        //AdminLogin s = new AdminLogin();
        //Admin login object set to visible
        //s.setVisible(true);
        updateInventory();
        populateRecipeTable();
    }//GEN-LAST:event_buttonRecipeEditActionPerformed

    private void buttonRecipeEdit1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonRecipeEdit1ActionPerformed
        //Admin login window object created
        //AdminLogin s = new AdminLogin();
        //Admin login object set to visible
        //s.setVisible(true);
        updateRecipe();
        populateSupplierTable();
    }//GEN-LAST:event_buttonRecipeEdit1ActionPerformed

    private void buttonEventsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonEventsActionPerformed

    }//GEN-LAST:event_buttonEventsActionPerformed

    private void buttonReprint2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonReprint2ActionPerformed
        network.getIP();
    }//GEN-LAST:event_buttonReprint2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        network.recieveData(jTable1);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void buttonRecipeEdit3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonRecipeEdit3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_buttonRecipeEdit3ActionPerformed

    private void buttonRecipeDelete3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonRecipeDelete3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_buttonRecipeDelete3ActionPerformed

    private void buttonRecipeEdit4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonRecipeEdit4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_buttonRecipeEdit4ActionPerformed

    private void buttonRecipeEdit2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonRecipeEdit2ActionPerformed
        updateRecipeList();
    }//GEN-LAST:event_buttonRecipeEdit2ActionPerformed

    private void buttonEdit1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonEdit1ActionPerformed
        updateShrinkage();
        populateInvnetoryTable();
    }//GEN-LAST:event_buttonEdit1ActionPerformed

    private void buttonOrderHistoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonOrderHistoryActionPerformed
        if (tblOrderHistory.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(null, "No Item Selected");
        } else {
            updateStockOrder();
            populateInvnetoryTable();
            populateOrderTable();
        }
    }//GEN-LAST:event_buttonOrderHistoryActionPerformed

    private void buttonReprint3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonReprint3ActionPerformed
        try {
            network.sendData();
        } catch (IOException ex) {
        }
    }//GEN-LAST:event_buttonReprint3ActionPerformed

    private void buttonPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonPrintActionPerformed
            prints.printTextToPDF();
    }//GEN-LAST:event_buttonPrintActionPerformed

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
                if ("Macintosh".equals(info.getName())) {
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
    private javax.swing.JButton buttonEdit1;
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
    private javax.swing.JButton buttonRecipeDelete;
    private javax.swing.JButton buttonRecipeDelete1;
    private javax.swing.JButton buttonRecipeDelete2;
    private javax.swing.JButton buttonRecipeDelete3;
    private javax.swing.JButton buttonRecipeEdit;
    private javax.swing.JButton buttonRecipeEdit1;
    private javax.swing.JButton buttonRecipeEdit2;
    private javax.swing.JButton buttonRecipeEdit3;
    private javax.swing.JButton buttonRecipeEdit4;
    private javax.swing.JButton buttonReports;
    private javax.swing.JButton buttonReprint;
    private javax.swing.JButton buttonReprint2;
    private javax.swing.JButton buttonReprint3;
    private javax.swing.JButton buttonSpecials;
    private javax.swing.JButton buttonTakeAway;
    private javax.swing.JComboBox<String> comboBoxLogo;
    private javax.swing.JComboBox<String> comboBoxSceen;
    private javax.swing.JComboBox<String> comboBoxTableCount;
    private javax.swing.JLabel currentEmail;
    private javax.swing.JLabel currentEmail1;
    private javax.swing.JLabel currentEmail2;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPasswordField jPasswordField1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel lblClock;
    private javax.swing.JLabel lblDate;
    private javax.swing.JLabel lblImage;
    private javax.swing.JLabel lblLogo;
    private javax.swing.JLabel lblLogo1;
    private javax.swing.JLabel lblScreen;
    private javax.swing.JLabel lblSearch;
    private javax.swing.JLabel lblSettings;
    private javax.swing.JLabel lblSettings1;
    private javax.swing.JLabel lblSettings2;
    private javax.swing.JLabel lblSettings3;
    private javax.swing.JLabel lblVersion;
    private javax.swing.JPanel pnlInventory;
    private javax.swing.JPanel pnlLayout;
    private javax.swing.JPanel pnlLeft;
    private javax.swing.JPanel pnlManageOrder;
    private javax.swing.JPanel pnlPanel;
    private javax.swing.JPanel pnlRecipe;
    private javax.swing.JPanel pnlRecipeList;
    private javax.swing.JPanel pnlSupplier;
    private javax.swing.JPanel pnlViewOrder;
    private javax.swing.JTable tableRecipe;
    private javax.swing.JTable tableRecipeList;
    private javax.swing.JTable tblInventory;
    private javax.swing.JTable tblOrderHistory;
    private javax.swing.JTable tblSupplier;
    private static javax.swing.JTextField textboxSearch;
    // End of variables declaration//GEN-END:variables
}
