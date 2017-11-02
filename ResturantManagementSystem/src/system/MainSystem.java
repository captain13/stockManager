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
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.filechooser.FileFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author GGPQ9ZJ42
 */
public class MainSystem extends javax.swing.JFrame implements ActionListener {

    BookingForm booking;
    CalendarForm calendarForm;
    Specials specials;
    userManager user = new userManager();
    settingsManager settings = new settingsManager();
    dbManager system = new dbManager();
    logSystem logs = new logSystem();
    printHandler prints = new printHandler();
    networkHandler network = new networkHandler();
    receiptHandler receipt = new receiptHandler();
    internalClock clock = new internalClock();
    HashMap<String, NewOrder> tables = new HashMap<>();
    Color color = new Color(0, 138, 231);
    boolean enableKeypad = false;
    int n;
    JButton button;
    boolean editable = false;
    String imagePath = null;

    public MainSystem() {
        initComponents();
        settings.xmlValidition();
        system.dbValidation(getUsernameDB(), getPasswordDB());
        logs.logValidation();
        clock.internalClock(lblClock, lblDate);
        network.recieveData(jTable1);
        getSetting();
        startup();
        system.scheduleBackup();
    }

    public final void startup() {
        try {
            bookingAlert();
            stockAlert();
            populateInvnetoryTable();
            populateRecipeTable();
            populateRecipeListTable();
            populateSupplierTable();
            populateOrderTable();
        } catch (Exception exp) {
            JOptionPane.showMessageDialog(null, "No Connection to the database");
        }
    }

    public final void populateInvnetoryTable() {
        String columnNamesInventory[] = {"Inventory ID", "Item Name", "Category", "Quantity(g)", "Item Threshold", "Item Limit", "Item Cost"};
        DefaultTableModel tableModel = new DefaultTableModel(system.getInventoryData(), columnNamesInventory) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return editable;
            }
        };
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
        DefaultTableModel tableModel = new DefaultTableModel(system.getRecipeData(), columnNamesRecipe) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return editable;
            }
        };
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
        DefaultTableModel tableModel = new DefaultTableModel(system.getRecipeListData(), columnNamesRecipeList) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return editable;
            }
        };
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
        DefaultTableModel tableModel = new DefaultTableModel(system.getSuppleirData(), columnNamesSupplier) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return editable;
            }
        };
        tblSupplier.setModel(tableModel);
        searchSTable();
//        tblInventory.getColumnModel().getColumn(0).setPreferredWidth(100);
//        tblInventory.getColumnModel().getColumn(1).setPreferredWidth(300);
//        tblInventory.getColumnModel().getColumn(2).setPreferredWidth(100);
//        tblInventory.getColumnModel().getColumn(3).setPreferredWidth(100);
//        tblInventory.getColumnModel().getColumn(4).setPreferredWidth(100);
    }

    public final void populateOrderTable() {
        String columnNames[] = {"ID", "Item", "Supplier Name", "Date Ordered", "ETA", "Quantity(g)", "Status", "Estimate Cost"};
        DefaultTableModel tableModel = new DefaultTableModel(system.getOrderData(), columnNames);
        tblOrderHistory.setModel(tableModel);
//        tblInventory.getColumnModel().getColumn(0).setPreferredWidth(100);
//        tblInventory.getColumnModel().getColumn(1).setPreferredWidth(300);
//        tblInventory.getColumnModel().getColumn(2).setPreferredWidth(100);
//        tblInventory.getColumnModel().getColumn(3).setPreferredWidth(100);
//        tblInventory.getColumnModel().getColumn(4).setPreferredWidth(100);
    }

    public final void tableLayout(Color color) {
        pnlLayout.removeAll();
        GridLayout tableLayout = new GridLayout(0, 5);
        pnlLayout.setLayout(tableLayout);
        tableLayout.setHgap(2);
        tableLayout.setVgap(2);
        int emptySpace = 20 - n;
        for (int i = 1; i <= n; i++) {
            button = new JButton("Table " + i);
            button.setBackground(color);
            button.setForeground(new Color(255, 255, 255));
            button.setContentAreaFilled(false);
            button.setOpaque(true);
            button.addActionListener(this);
            pnlLayout.add(button);
        }

        for (int i = 1; i < emptySpace; i++) {
            JButton emptyButton = new JButton();
            pnlLayout.add(emptyButton);
            emptyButton.setVisible(false);
        }
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        String buttonId = ae.getActionCommand();
        if (tables.get(buttonId) == null) {
            String waiter = user.createUserLog();
            if (waiter != null) {
                String customer = JOptionPane.showInputDialog(null, "Enter Number of Customers");

                if (!"".equals(waiter) && (!"".equals(customer))
                        && customer != null) {
                    tables.put(buttonId, new NewOrder(waiter, customer, buttonId, tables, color));
                    tables.get(buttonId).setVisible(true);
                } else {
                }
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
        if ("Edit".equals(buttonEdit.getText())) {
            buttonEdit.setText("Save");
            editable = true;
        } else if ("Save".equals(buttonEdit.getText())) {
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
                    system.updateInventory(ID, item, qty, itemT, itemL);
                }
            }
            buttonEdit.setText("Edit");
            editable = false;
        }
    }

    public void updateRecipe() {
        if ("Edit".equals(buttonRecipeEdit.getText())) {
            buttonRecipeEdit.setText("Save");
            editable = true;
        } else if ("Save".equals(buttonRecipeEdit.getText())) {
            //stops editting of getTableOrder cell to allow button event
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
                    Object price = tableRecipe.getModel().getValueAt(r, 3);
                    Object vat = tableRecipe.getModel().getValueAt(r, 4);
                    Object type = tableRecipe.getModel().getValueAt(r, 2);
                    //Pass getTableOrder contents to database update code
                    system.updateRecipe(ID, name, price, vat, type);
                }
            }
            buttonRecipeEdit.setText("Edit");
            editable = false;
        }
    }

    public void updateRecipeList() {
        if ("Edit".equals(buttonRecipeListEdit.getText())) {
            buttonRecipeListEdit.setText("Save");
            editable = true;
        } else if ("Save".equals(buttonRecipeListEdit.getText())) {
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
            buttonRecipeListEdit.setText("Edit");
            editable = false;
        }
    }

    public void updateSupplier() {
        if ("Edit".equals(buttonSupplierEdit.getText())) {
            buttonSupplierEdit.setText("Save");
            editable = true;
        } else if ("Save".equals(buttonSupplierEdit.getText())) {

            //stops editting of getTableOrder cell to allow button event
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
                    //Pass getTableOrder contents to database update code
                    system.updateSupplier(ID, name, email, num, address);
                }
            }
            buttonSupplierEdit.setText("Edit");
            editable = false;
        }
    }

    public void updateShrinkage() {
        String qty = JOptionPane.showInputDialog(null, "Enter the Shrinkage for the Select Item");
        String item = tblInventory.getValueAt(tblInventory.getSelectedRow(), 1).toString();
        system.updateInventoryQty(item, qty, "-");
        populateInvnetoryTable();
    }

    public void updateStockOrder() {
        int confirm = JOptionPane.showConfirmDialog(null, "Are you sure?", null, JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            String orderID = tblOrderHistory.getValueAt(tblOrderHistory.getSelectedRow(), 0).toString();
            String item = tblOrderHistory.getValueAt(tblOrderHistory.getSelectedRow(), 1).toString();
            String qty = tblOrderHistory.getValueAt(tblOrderHistory.getSelectedRow(), 5).toString();
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
        try {
            setUsernameDB();
            setPasswordDB();
            setTableCount();
            setResolution();
            setEmail();
            setEmailPassword();
            setThemeColor();
            setImage();
        } catch (Exception exc) {
            System.out.println(exc);
        }
    }

    public void setImage() {
        ImageIcon icon = new ImageIcon(settings.getLogo());
        lblImage.setIcon(icon);
    }

    public String getImage() {
        return imagePath;
    }

    public void changeLogo() {
        JFileChooser fileChooser;
        if (imagePath != null && !imagePath.equals("")) {
            fileChooser = new JFileChooser(imagePath);
        } else {
            fileChooser = new JFileChooser();
        }

        FileFilter imageFilter = new FileFilter() {
            @Override
            public String getDescription() {
                return "image file (*.PNG)";
            }

            @Override
            public boolean accept(File file) {
                if (file.isDirectory()) {
                    return true;
                } else {
                    return file.getName().toLowerCase().endsWith(".png");
                }
            }
        };

        fileChooser.setFileFilter(imageFilter);
        fileChooser.setDialogTitle("Open Image File");
        fileChooser.setAcceptAllFileFilterUsed(false);

        int userChoice = fileChooser.showOpenDialog(this);
        if (userChoice == JFileChooser.APPROVE_OPTION) {
            imagePath = fileChooser.getSelectedFile().getAbsolutePath();
            //  imagePath = fileChooser.getSelectedFile().getParent();
        }
        ImageIcon icon = new ImageIcon(imagePath);
        lblImage.setIcon(icon);
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

    public void setThemeColor() {
        comboBoxTableColor.setSelectedItem(settings.getThemeColor());
    }

    public final void setUsernameDB() {
        textUsername.setText(settings.getDatabaseUsername());
    }

    public final void setPasswordDB() {
        textPassword.setText(settings.getDatabePassword());
    }

    public final void setSelectedTable() {
        n = Integer.parseInt(getSelectedTable());
        tableLayout(color);
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
        this.pack();
    }

    public String getSelectedResolution() {
        return comboBoxSceen.getSelectedItem().toString();
    }

    public String getSelectedTable() {
        return comboBoxTableCount.getSelectedItem().toString();
    }

    public String getSelectedColor() {
        return comboBoxTableColor.getSelectedItem().toString();
    }

    public String getEmail() {
        return currentEmail.getText();
    }

    public String getEmailPassword() {
        return jPasswordField1.getText().trim();
    }

    private String getUsernameDB() {
        return textUsername.getText().trim();
    }

    private String getPasswordDB() {
        return textPassword.getText();
    }

    public final void bookingAlert() {
        if (system.getReservationData().length != 0) {
            for (int i = 0; i < system.getReservationData().length; i++) {
                if (system.getReservationData()[i][2].toString().equals(clock.getCurrentDate())) {
                    buttonBookings.setText("Bookings [" + (i + 1) + "]");
                }
            }
        } else {
            buttonBookings.setText("Bookings");
        }
    }

    public final void stockAlert() {
        for (int i = 0; i < system.checkStockLevel().length; i++) {
            String columnNamesInventory[] = {"Inventory ID", "Item Name", "Quantity(g)"};
            DefaultTableModel tableModel = new DefaultTableModel(system.checkStockLevel(), columnNamesInventory);
            tblOrderHistory.setModel(tableModel);
            buttonAlert.setText("Alert [" + (i + 1) + "]");
        }
    }

    public void buttonColor(Color color) {

        buttonBookings.setBackground(color);
        buttonSpecials.setBackground(color);
        buttonPromotions.setBackground(color);
        buttonEvents.setBackground(color);
        buttonTakeAway.setBackground(color);
        buttonLayout.setBackground(color);
        buttonReprint.setBackground(color);
        buttonReprint2.setBackground(color);
        buttonReprint3.setBackground(color);

        buttonEdit1.setBackground(color);
        buttonAdd.setBackground(color);
        buttonDelete.setBackground(color);
        buttonEdit.setBackground(color);

        buttonRecipeAdd.setBackground(color);
        buttonRecipeDelete.setBackground(color);
        buttonRecipeEdit.setBackground(color);

        buttonRecipeDelete2.setBackground(color);
        buttonRecipeListEdit.setBackground(color);

        buttonRecipeAdd1.setBackground(color);
        buttonRecipeDelete1.setBackground(color);
        buttonSupplierEdit.setBackground(color);

        buttonOrderHistory.setBackground(color);
        buttonMakeOrder.setBackground(color);
        buttonAlert.setBackground(color);
        buttonEmail.setBackground(color);
        buttonReports.setBackground(color);
        buttonEmployee.setBackground(color);
        buttonLogs.setBackground(color);
        buttonManageSpecials.setBackground(color);

        changLogoButton.setBackground(color);
        jButton4.setBackground(color);
        jButton7.setBackground(color);
        jButton9.setBackground(color);
        jButton8.setBackground(color);
        jButton10.setBackground(color);
        buttonChangeUsername.setBackground(color);
        buttonChangePassword.setBackground(color);
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
        buttonRecipeListEdit = new javax.swing.JButton();
        jScrollPane6 = new javax.swing.JScrollPane();
        tableRecipeList = new javax.swing.JTable();
        buttonRecipeDelete3 = new javax.swing.JButton();
        pnlSupplier = new javax.swing.JPanel();
        buttonRecipeAdd1 = new javax.swing.JButton();
        buttonRecipeDelete1 = new javax.swing.JButton();
        buttonSupplierEdit = new javax.swing.JButton();
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
        buttonEmail = new javax.swing.JButton();
        buttonMakeOrder = new javax.swing.JButton();
        buttonReports = new javax.swing.JButton();
        buttonEmployee = new javax.swing.JButton();
        buttonManageSpecials = new javax.swing.JButton();
        buttonLogs = new javax.swing.JButton();
        buttonLogs1 = new javax.swing.JButton();
        buttonAlert = new javax.swing.JToggleButton();
        lblTable = new javax.swing.JLabel();
        Settings = new javax.swing.JPanel();
        lblSettings = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jCheckBox1 = new javax.swing.JCheckBox();
        lblLogo1 = new javax.swing.JLabel();
        lblLogo = new javax.swing.JLabel();
        lblScreen = new javax.swing.JLabel();
        comboBoxSceen = new javax.swing.JComboBox<>();
        comboBoxTableCount = new javax.swing.JComboBox<>();
        lblSettings1 = new javax.swing.JLabel();
        lblLogo2 = new javax.swing.JLabel();
        comboBoxTableColor = new javax.swing.JComboBox<>();
        changLogoButton = new javax.swing.JButton();
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
        jPanel5 = new javax.swing.JPanel();
        currentEmail3 = new javax.swing.JLabel();
        currentEmail4 = new javax.swing.JLabel();
        textUsername = new javax.swing.JLabel();
        textPassword = new javax.swing.JPasswordField();
        buttonChangePassword = new javax.swing.JButton();
        buttonChangeUsername = new javax.swing.JButton();
        lblSettings4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(75, 75, 75));
        setLocation(new java.awt.Point(0, 0));
        setUndecorated(true);

        TabbedPanel.setBackground(new java.awt.Color(53, 53, 53));
        TabbedPanel.setForeground(new java.awt.Color(255, 255, 255));
        TabbedPanel.setTabPlacement(javax.swing.JTabbedPane.LEFT);
        TabbedPanel.setOpaque(true);
        TabbedPanel.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                TabbedPanelStateChanged(evt);
            }
        });

        Dashboard.setBackground(new java.awt.Color(255, 255, 255));

        lblImage.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblImage.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Logo_White.png"))); // NOI18N
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 382, Short.MAX_VALUE)
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

        jTable1.setFont(new java.awt.Font("Tahoma", 0, 8)); // NOI18N
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
                .addGap(0, 125, Short.MAX_VALUE))
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
        buttonLayout.setText("Open Drawer");
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
                .addContainerGap(219, Short.MAX_VALUE))
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
        buttonAdd.setToolTipText("Insert into Database");
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
        buttonPrint.setToolTipText("Prints the table to a pdf document");
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
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 492, Short.MAX_VALUE)
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
            .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 492, Short.MAX_VALUE)
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

        buttonRecipeListEdit.setBackground(new java.awt.Color(0, 138, 231));
        buttonRecipeListEdit.setForeground(new java.awt.Color(255, 255, 255));
        buttonRecipeListEdit.setText("Edit");
        buttonRecipeListEdit.setContentAreaFilled(false);
        buttonRecipeListEdit.setOpaque(true);
        buttonRecipeListEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonRecipeListEditActionPerformed(evt);
            }
        });

        tableRecipeList.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

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
                    .addComponent(buttonRecipeListEdit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(buttonRecipeDelete2, javax.swing.GroupLayout.DEFAULT_SIZE, 79, Short.MAX_VALUE)
                    .addComponent(buttonRecipeDelete3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 715, Short.MAX_VALUE))
        );
        pnlRecipeListLayout.setVerticalGroup(
            pnlRecipeListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 492, Short.MAX_VALUE)
            .addGroup(pnlRecipeListLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(buttonRecipeDelete2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(buttonRecipeListEdit)
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

        buttonSupplierEdit.setBackground(new java.awt.Color(0, 138, 231));
        buttonSupplierEdit.setForeground(new java.awt.Color(255, 255, 255));
        buttonSupplierEdit.setText("Edit");
        buttonSupplierEdit.setContentAreaFilled(false);
        buttonSupplierEdit.setOpaque(true);
        buttonSupplierEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonSupplierEditActionPerformed(evt);
            }
        });

        tblSupplier.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

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
                    .addComponent(buttonSupplierEdit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
                .addComponent(buttonSupplierEdit)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(buttonRecipeEdit4)
                .addContainerGap())
            .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 492, Short.MAX_VALUE)
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

        Management.setBackground(new java.awt.Color(255, 255, 255));

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
        buttonReports.setText("View Reports");
        buttonReports.setContentAreaFilled(false);
        buttonReports.setOpaque(true);
        buttonReports.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonReportsActionPerformed(evt);
            }
        });

        buttonEmployee.setBackground(new java.awt.Color(0, 138, 231));
        buttonEmployee.setForeground(new java.awt.Color(255, 255, 255));
        buttonEmployee.setText("Manage Employees");
        buttonEmployee.setContentAreaFilled(false);
        buttonEmployee.setOpaque(true);
        buttonEmployee.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonEmployeeActionPerformed(evt);
            }
        });

        buttonManageSpecials.setBackground(new java.awt.Color(0, 138, 231));
        buttonManageSpecials.setForeground(new java.awt.Color(255, 255, 255));
        buttonManageSpecials.setText("Manage Specials");
        buttonManageSpecials.setToolTipText("Used to add, delete or edit specials");
        buttonManageSpecials.setContentAreaFilled(false);
        buttonManageSpecials.setOpaque(true);
        buttonManageSpecials.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonManageSpecialsActionPerformed(evt);
            }
        });

        buttonLogs.setBackground(new java.awt.Color(0, 138, 231));
        buttonLogs.setForeground(new java.awt.Color(255, 255, 255));
        buttonLogs.setText("View Logs");
        buttonLogs.setContentAreaFilled(false);
        buttonLogs.setOpaque(true);
        buttonLogs.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonLogsActionPerformed(evt);
            }
        });

        buttonLogs1.setBackground(new java.awt.Color(75, 75, 75));
        buttonLogs1.setForeground(new java.awt.Color(255, 255, 255));
        buttonLogs1.setText("End of Day Sales");
        buttonLogs1.setContentAreaFilled(false);
        buttonLogs1.setOpaque(true);
        buttonLogs1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonLogs1ActionPerformed(evt);
            }
        });

        buttonAlert.setBackground(new java.awt.Color(0, 138, 231));
        buttonAlert.setForeground(new java.awt.Color(255, 255, 255));
        buttonAlert.setText("Alert");
        buttonAlert.setContentAreaFilled(false);
        buttonAlert.setOpaque(true);
        buttonAlert.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                buttonAlertItemStateChanged(evt);
            }
        });
        buttonAlert.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonAlertActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(buttonAlert, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(buttonLogs, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(buttonEmployee, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(buttonManageSpecials, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(buttonReports, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(buttonLogs1, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(buttonOrderHistory, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(buttonMakeOrder, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(buttonEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(70, 70, 70)
                .addComponent(buttonAlert)
                .addGap(32, 32, 32)
                .addComponent(buttonEmployee)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(buttonManageSpecials)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(buttonReports)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(buttonLogs)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 284, Short.MAX_VALUE)
                .addComponent(buttonLogs1)
                .addContainerGap())
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(buttonOrderHistory)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(buttonMakeOrder)
                    .addGap(35, 35, 35)
                    .addComponent(buttonEmail)
                    .addContainerGap(426, Short.MAX_VALUE)))
        );

        lblTable.setText("Order History");

        javax.swing.GroupLayout ManagementLayout = new javax.swing.GroupLayout(Management);
        Management.setLayout(ManagementLayout);
        ManagementLayout.setHorizontalGroup(
            ManagementLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ManagementLayout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(ManagementLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(ManagementLayout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 624, Short.MAX_VALUE)
                        .addGap(12, 12, 12))
                    .addGroup(ManagementLayout.createSequentialGroup()
                        .addComponent(lblTable)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        ManagementLayout.setVerticalGroup(
            ManagementLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(ManagementLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTable)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 436, Short.MAX_VALUE)
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

        lblLogo.setText("Logo");

        lblScreen.setText("Resolution");

        comboBoxSceen.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Windowed Screen", "Fullscreen" }));
        comboBoxSceen.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                comboBoxSceenItemStateChanged(evt);
            }
        });

        comboBoxTableCount.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20" }));
        comboBoxTableCount.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                comboBoxTableCountItemStateChanged(evt);
            }
        });

        lblSettings1.setText("General Settings");

        lblLogo2.setText("Theme Colour");

        comboBoxTableColor.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Black", "Blue", "Green", "Red", "Orange" }));
        comboBoxTableColor.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                comboBoxTableColorItemStateChanged(evt);
            }
        });

        changLogoButton.setBackground(new java.awt.Color(0, 138, 231));
        changLogoButton.setForeground(new java.awt.Color(255, 255, 255));
        changLogoButton.setText("Change Logo");
        changLogoButton.setContentAreaFilled(false);
        changLogoButton.setOpaque(true);
        changLogoButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                changLogoButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblSettings1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                        .addGap(13, 13, 13)
                        .addComponent(jCheckBox1))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addGap(13, 13, 13)
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(lblLogo1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(lblLogo2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                    .addContainerGap()
                                    .addComponent(lblScreen)
                                    .addGap(41, 41, 41)))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(lblLogo)
                                .addGap(49, 49, 49)))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(changLogoButton, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(comboBoxTableColor, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(comboBoxTableCount, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(comboBoxSceen, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(0, 59, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblSettings1)
                .addGap(33, 33, 33)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblLogo)
                    .addComponent(changLogoButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(comboBoxSceen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblScreen))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(comboBoxTableCount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblLogo1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(comboBoxTableColor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblLogo2))
                .addGap(12, 12, 12)
                .addComponent(jCheckBox1)
                .addContainerGap(43, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        currentEmail2.setText("Email Password");

        currentEmail1.setText("Email Address");

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
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(currentEmail1, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(currentEmail2, javax.swing.GroupLayout.DEFAULT_SIZE, 86, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jPasswordField1, javax.swing.GroupLayout.DEFAULT_SIZE, 121, Short.MAX_VALUE)
                            .addComponent(currentEmail, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                .addContainerGap(80, Short.MAX_VALUE))
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

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        currentEmail3.setText("Password");

        currentEmail4.setText("Username");

        textUsername.setText("root");

        textPassword.setText("root");
        textPassword.setBorder(null);
        textPassword.setEnabled(false);
        textPassword.setOpaque(false);

        buttonChangePassword.setBackground(new java.awt.Color(0, 138, 231));
        buttonChangePassword.setForeground(new java.awt.Color(255, 255, 255));
        buttonChangePassword.setText("Change Password");
        buttonChangePassword.setContentAreaFilled(false);
        buttonChangePassword.setOpaque(true);
        buttonChangePassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonChangePasswordActionPerformed(evt);
            }
        });

        buttonChangeUsername.setBackground(new java.awt.Color(0, 138, 231));
        buttonChangeUsername.setForeground(new java.awt.Color(255, 255, 255));
        buttonChangeUsername.setText("Change Username ");
        buttonChangeUsername.setContentAreaFilled(false);
        buttonChangeUsername.setOpaque(true);
        buttonChangeUsername.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonChangeUsernameActionPerformed(evt);
            }
        });

        lblSettings4.setText("Database Setup");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(lblSettings4)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(currentEmail4, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(currentEmail3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(textPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(28, 28, 28))
                            .addComponent(textUsername, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(buttonChangeUsername, javax.swing.GroupLayout.DEFAULT_SIZE, 145, Short.MAX_VALUE)
                            .addComponent(buttonChangePassword, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(13, 13, 13))))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(lblSettings4)
                .addGap(51, 51, 51)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonChangeUsername)
                    .addComponent(textUsername)
                    .addComponent(currentEmail4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonChangePassword)
                    .addComponent(currentEmail3)
                    .addComponent(textPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout SettingsLayout = new javax.swing.GroupLayout(Settings);
        Settings.setLayout(SettingsLayout);
        SettingsLayout.setHorizontalGroup(
            SettingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(SettingsLayout.createSequentialGroup()
                .addGroup(SettingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(SettingsLayout.createSequentialGroup()
                        .addGroup(SettingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(SettingsLayout.createSequentialGroup()
                                .addGap(25, 25, 25)
                                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(SettingsLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(SettingsLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(SettingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblSettings)
                            .addGroup(SettingsLayout.createSequentialGroup()
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(105, Short.MAX_VALUE))
        );
        SettingsLayout.setVerticalGroup(
            SettingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(SettingsLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(lblSettings)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(SettingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(SettingsLayout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(SettingsLayout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
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
        EmployeeForm emp = new EmployeeForm(color);
        emp.setVisible(true);
    }//GEN-LAST:event_buttonEmployeeActionPerformed

    private void buttonPromotionsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonPromotionsActionPerformed
        try {

            calendarForm = new CalendarForm(color);
            booking.setVisible(false);
            specials.setVisible(false);
        } catch (RuntimeException ignore) {
        }
    }//GEN-LAST:event_buttonPromotionsActionPerformed

    private void buttonSpecialsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonSpecialsActionPerformed
        try {
            specials = new Specials();
            specials.setVisible(true);
            calendarForm.setVisible(false);
            booking.setVisible(false);
        } catch (RuntimeException ignore) {
        }
    }//GEN-LAST:event_buttonSpecialsActionPerformed

    private void buttonBookingsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonBookingsActionPerformed
        try {
            booking = new BookingForm(this, keypadCheck());
            booking.setVisible(true);
            specials.setVisible(false);
            calendarForm.setVisible(false);
        } catch (RuntimeException ignore) {
        }

    }//GEN-LAST:event_buttonBookingsActionPerformed

    private void buttonCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonCloseActionPerformed
        user.logoutAll();
        System.exit(0);
    }//GEN-LAST:event_buttonCloseActionPerformed

    private void buttonTakeAwayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonTakeAwayActionPerformed
        if (tables.get("Take Away") == null) {
            tables.put("Take Away", new NewOrder("Take Away", "1", "Take Away", tables, color));
        } else {
            tables.get("Take Away").setVisible(true);
        }
    }//GEN-LAST:event_buttonTakeAwayActionPerformed

    private void buttonLayoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonLayoutActionPerformed
        JOptionPane.showMessageDialog(null, "Draw Opened");
    }//GEN-LAST:event_buttonLayoutActionPerformed

    private void buttonReprintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonReprintActionPerformed
        ReprintForm reprint = new ReprintForm();
        reprint.setVisible(true);
    }//GEN-LAST:event_buttonReprintActionPerformed

    private void comboBoxSceenItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_comboBoxSceenItemStateChanged
        if (evt.getStateChange() == ItemEvent.SELECTED) {
            changeResolution();
        }
    }//GEN-LAST:event_comboBoxSceenItemStateChanged

    private void buttonRecipeAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonRecipeAddActionPerformed
        if (user.createAdminLogin() == true) {
            AddDatabase database = new AddDatabase(this, keypadCheck());
            database.setVisible(true);
            AddDatabase.getTabbedPanel().setSelectedIndex(1);
        }
    }//GEN-LAST:event_buttonRecipeAddActionPerformed

    private void buttonEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonEditActionPerformed
        updateInventory();
        populateInvnetoryTable();
    }//GEN-LAST:event_buttonEditActionPerformed

    private void buttonAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAddActionPerformed
        if (user.createAdminLogin() == true) {
            AddDatabase database = new AddDatabase(this, keypadCheck());
            database.setVisible(true);
        }
    }//GEN-LAST:event_buttonAddActionPerformed

    private void buttonRecipeAdd1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonRecipeAdd1ActionPerformed
        if (user.createAdminLogin() == true) {
            AddDatabase database = new AddDatabase(this, keypadCheck());
            database.setVisible(true);
            AddDatabase.getTabbedPanel().setSelectedIndex(2);
        }
    }//GEN-LAST:event_buttonRecipeAdd1ActionPerformed

    private void buttonDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonDeleteActionPerformed
        if (tblInventory.getSelectedRow() == (-1)) {
            JOptionPane.showMessageDialog(null, "No Item Selected");
        } else {
            if (user.createAdminLogin() == true) {
                int confirm = JOptionPane.showConfirmDialog(null, "Are you sure?", null, JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    system.removeInventory(getID());
                    populateInvnetoryTable();
                }
            }
        }
    }//GEN-LAST:event_buttonDeleteActionPerformed

    private void buttonRecipeDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonRecipeDeleteActionPerformed
        if (tableRecipe.getSelectedRow() == (-1)) {
            JOptionPane.showMessageDialog(null, "No Item Selected");
        } else {
            if (user.createAdminLogin() == true) {
                int confirm = JOptionPane.showConfirmDialog(null, "Are you sure?", null, JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    system.removeRecipe(getIDrecipe());
                    populateRecipeTable();
                }
            }
        }
    }//GEN-LAST:event_buttonRecipeDeleteActionPerformed

    private void buttonRecipeDelete1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonRecipeDelete1ActionPerformed
        if (tblSupplier.getSelectedRow() == (-1)) {
            JOptionPane.showMessageDialog(null, "No Item Selected");
        } else {
            if (user.createAdminLogin() == true) {
                int confirm = JOptionPane.showConfirmDialog(null, "Are you sure?", null, JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    system.removeSupplier(getIDsupplier());
                    populateSupplierTable();
                }
            }
        }
    }//GEN-LAST:event_buttonRecipeDelete1ActionPerformed

    private void buttonLogOutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonLogOutActionPerformed
        user.createLogout();
    }//GEN-LAST:event_buttonLogOutActionPerformed

    private void buttonLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonLoginActionPerformed
        user.createLogin();
    }//GEN-LAST:event_buttonLoginActionPerformed

    private void buttonMakeOrderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonMakeOrderActionPerformed
        OrderForm newOrder = new OrderForm(this, keypadCheck());
        newOrder.setVisible(true);
    }//GEN-LAST:event_buttonMakeOrderActionPerformed

    private void buttonEmailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonEmailActionPerformed
        EmailForm newEmail = new EmailForm(color, keypadCheck());
        newEmail.setVisible(true);
    }//GEN-LAST:event_buttonEmailActionPerformed

    private void buttonReportsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonReportsActionPerformed
        Reports newReport = new Reports(color);
        newReport.setVisible(true);
    }//GEN-LAST:event_buttonReportsActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        settings.updateSettings(getImage(), getSelectedResolution(), getSelectedTable(), getEmail(), getEmailPassword(),
                getSelectedColor(), getUsernameDB(), getPasswordDB());
        JOptionPane.showMessageDialog(null, "Settings Saved");
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        prints.printUserManual();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        prints.printUserManual();
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
        bookingAlert();
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        String email = JOptionPane.showInputDialog(null, "Enter new Email Address");
        if ("".equals(email) || email == null) {
        } else {
            currentEmail.setText(email);
        }
    }//GEN-LAST:event_jButton8ActionPerformed

    private void buttonManageSpecialsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonManageSpecialsActionPerformed
        SpecialsForm newForm = new SpecialsForm(color);
        newForm.setVisible(true);
    }//GEN-LAST:event_buttonManageSpecialsActionPerformed

    private void buttonLogsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonLogsActionPerformed
        LogForm newForm = new LogForm();
        newForm.setVisible(true);
    }//GEN-LAST:event_buttonLogsActionPerformed

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
        if (tableRecipeList.getSelectedRow() == (-1)) {
            JOptionPane.showMessageDialog(null, "No Item Selected");
        } else {
            if (user.createAdminLogin() == true) {
                int confirm = JOptionPane.showConfirmDialog(null, "Are you sure?", null, JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    system.removeRecipeList(getIDrecipeListRecipe(), getIDrecipeListInventory());
                }
            }
        }
    }//GEN-LAST:event_buttonRecipeDelete2ActionPerformed

    private void buttonRecipeEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonRecipeEditActionPerformed
//        if (user.createAdminLogin() == true) {
        updateRecipe();
        populateRecipeTable();
//        }
    }//GEN-LAST:event_buttonRecipeEditActionPerformed

    private void buttonSupplierEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonSupplierEditActionPerformed
        updateSupplier();
        populateSupplierTable();
    }//GEN-LAST:event_buttonSupplierEditActionPerformed

    private void buttonEventsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonEventsActionPerformed
        RemindersForm newForm = new RemindersForm(keypadCheck());
        newForm.setVisible(true);
    }//GEN-LAST:event_buttonEventsActionPerformed

    private void buttonReprint2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonReprint2ActionPerformed
        network.getIP();
    }//GEN-LAST:event_buttonReprint2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        network.recieveData(jTable1);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void buttonRecipeEdit3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonRecipeEdit3ActionPerformed
        prints.printRecipe();
    }//GEN-LAST:event_buttonRecipeEdit3ActionPerformed

    private void buttonRecipeDelete3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonRecipeDelete3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_buttonRecipeDelete3ActionPerformed

    private void buttonRecipeEdit4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonRecipeEdit4ActionPerformed
        prints.printSupplier();
    }//GEN-LAST:event_buttonRecipeEdit4ActionPerformed

    private void buttonRecipeListEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonRecipeListEditActionPerformed
        updateRecipeList();
    }//GEN-LAST:event_buttonRecipeListEditActionPerformed

    private void buttonEdit1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonEdit1ActionPerformed
        if (tblInventory.getSelectedRow() == (-1)) {
            JOptionPane.showMessageDialog(null, "No Item Selected");
        } else {
            updateShrinkage();
            populateInvnetoryTable();
            system.checkStockLevel();
        }
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
        prints.printInventory();
    }//GEN-LAST:event_buttonPrintActionPerformed

    private void comboBoxTableColorItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_comboBoxTableColorItemStateChanged
        switch (comboBoxTableColor.getSelectedItem().toString()) {
            case ("Black"):
                color = new Color(72, 72, 72);
                break;
            case ("Blue"):
                color = new Color(0, 138, 231);
                break;
            case ("Red"):
                color = new Color(255, 46, 43);
                break;
            case ("Green"):
                color = new Color(51, 190, 52);
                break;
            case ("Orange"):
                color = new Color(232, 87, 17);
                break;
        }
        buttonColor(color);
        tableLayout(color);
    }//GEN-LAST:event_comboBoxTableColorItemStateChanged

    private void buttonLogs1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonLogs1ActionPerformed
        DayEndSalesForm newForm = new DayEndSalesForm();
        newForm.setVisible(true);
    }//GEN-LAST:event_buttonLogs1ActionPerformed

    private void TabbedPanelStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_TabbedPanelStateChanged
        if (TabbedPanel.getSelectedIndex() == 2) {
            populateInvnetoryTable();
        }

        if (TabbedPanel.getSelectedIndex() == 3) {
            if (system.getAdminCount() == true) {
                if (user.createAdminLogin() == true) {
                } else {
                    TabbedPanel.setSelectedIndex(0);
                }
            }
        }

        if (TabbedPanel.getSelectedIndex() == 4) {
            if (system.getAdminCount() == true) {
                if (user.createAdminLogin() == true) {
                } else {
                    TabbedPanel.setSelectedIndex(0);
                }
            }
        }
    }//GEN-LAST:event_TabbedPanelStateChanged

    private void buttonAlertItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_buttonAlertItemStateChanged
        if (evt.getStateChange() == ItemEvent.SELECTED) {
            stockAlert();
            lblTable.setText("Stock Alerts");
        } else {
            populateOrderTable();
            lblTable.setText("Order History");
        }
    }//GEN-LAST:event_buttonAlertItemStateChanged

    private void buttonAlertActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAlertActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_buttonAlertActionPerformed

    private void buttonChangePasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonChangePasswordActionPerformed
        String Password = JOptionPane.showInputDialog(null, "Enter new Email Address");
        if (Password == null) {
        } else {
            textPassword.setText(Password);
        }
    }//GEN-LAST:event_buttonChangePasswordActionPerformed

    private void buttonChangeUsernameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonChangeUsernameActionPerformed
        String username = JOptionPane.showInputDialog(null, "Enter new Email Address");
        if ("".equals(username) || username == null) {
        } else {
            textUsername.setText(username);
        }
    }//GEN-LAST:event_buttonChangeUsernameActionPerformed

    private void changLogoButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_changLogoButtonActionPerformed
        changeLogo();
    }//GEN-LAST:event_changLogoButtonActionPerformed

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
        try {
            java.awt.EventQueue.invokeLater(() -> {
                new MainSystem().setVisible(true);
            });
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Dashboard;
    private javax.swing.JPanel Inventory;
    private javax.swing.JPanel Management;
    private javax.swing.JPanel Orders;
    private javax.swing.JPanel Settings;
    private javax.swing.JTabbedPane TabbedPanel;
    private javax.swing.JButton buttonAdd;
    private javax.swing.JToggleButton buttonAlert;
    private javax.swing.JButton buttonBookings;
    private javax.swing.JButton buttonChangePassword;
    private javax.swing.JButton buttonChangeUsername;
    private javax.swing.JButton buttonClose;
    private javax.swing.JButton buttonDelete;
    private javax.swing.JButton buttonEdit;
    private javax.swing.JButton buttonEdit1;
    private javax.swing.JButton buttonEmail;
    private javax.swing.JButton buttonEmployee;
    private javax.swing.JButton buttonEvents;
    private javax.swing.JButton buttonLayout;
    private javax.swing.JButton buttonLogOut;
    private javax.swing.JButton buttonLogin;
    private javax.swing.JButton buttonLogs;
    private javax.swing.JButton buttonLogs1;
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
    private javax.swing.JButton buttonRecipeEdit3;
    private javax.swing.JButton buttonRecipeEdit4;
    private javax.swing.JButton buttonRecipeListEdit;
    private javax.swing.JButton buttonReports;
    private javax.swing.JButton buttonReprint;
    private javax.swing.JButton buttonReprint2;
    private javax.swing.JButton buttonReprint3;
    private javax.swing.JButton buttonSpecials;
    private javax.swing.JButton buttonSupplierEdit;
    private javax.swing.JButton buttonTakeAway;
    private javax.swing.JButton changLogoButton;
    private javax.swing.JComboBox<String> comboBoxSceen;
    private javax.swing.JComboBox<String> comboBoxTableColor;
    private javax.swing.JComboBox<String> comboBoxTableCount;
    private javax.swing.JLabel currentEmail;
    private javax.swing.JLabel currentEmail1;
    private javax.swing.JLabel currentEmail2;
    private javax.swing.JLabel currentEmail3;
    private javax.swing.JLabel currentEmail4;
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
    private javax.swing.JPanel jPanel5;
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
    private javax.swing.JLabel lblLogo2;
    private javax.swing.JLabel lblScreen;
    private javax.swing.JLabel lblSearch;
    private javax.swing.JLabel lblSettings;
    private javax.swing.JLabel lblSettings1;
    private javax.swing.JLabel lblSettings2;
    private javax.swing.JLabel lblSettings3;
    private javax.swing.JLabel lblSettings4;
    private javax.swing.JLabel lblTable;
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
    private javax.swing.JPasswordField textPassword;
    private javax.swing.JLabel textUsername;
    private static javax.swing.JTextField textboxSearch;
    // End of variables declaration//GEN-END:variables
}
