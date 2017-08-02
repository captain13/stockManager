package system;

import java.awt.Toolkit;
import java.awt.event.ItemEvent;
import java.awt.event.MouseAdapter;
import java.util.HashMap;
import javax.swing.*;
import java.awt.event.MouseEvent;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author GGPQ9ZJ42
 */
public class MainSystem extends javax.swing.JFrame {

    Booking booking = new Booking();
    Calendar calendar = new Calendar();
    Specials specials = new Specials();
    userManager userman = new userManager();
    HashMap<String, NewOrder> tables = new HashMap<>();
    boolean enableKeypad = false;

    public MainSystem() {
        initComponents();
        internalClock.internalClock();
        dbManager.populateTables();
        dbManager.populateOrder();
    }

    public static void searchTable() {
        try {
            TableRowSorter<TableModel> rowSorter = new TableRowSorter<>(tblInventory.getModel());
            TableRowSorter<TableModel> rowSorterRecipe = new TableRowSorter<>(tableRecipe.getModel());
            TableRowSorter<TableModel> rowSorterSup = new TableRowSorter<>(tableSupplier.getModel());
            tblInventory.setRowSorter(rowSorter);
            tableRecipe.setRowSorter(rowSorterRecipe);
            tableSupplier.setRowSorter(rowSorterSup);
            textboxSearch.getDocument().addDocumentListener(new DocumentListener() {

                @Override
                public void insertUpdate(DocumentEvent e) {
                    String text = textboxSearch.getText();

                    if (text.trim().length() == 0) {
                        rowSorter.setRowFilter(null);
                        rowSorterRecipe.setRowFilter(null);
                        rowSorterSup.setRowFilter(null);
                    } else {
                        rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
                        rowSorterRecipe.setRowFilter(RowFilter.regexFilter("(?i)" + text));
                        rowSorterSup.setRowFilter(RowFilter.regexFilter("(?i)" + text));
                    }
                }

                @Override
                public void removeUpdate(DocumentEvent e) {
                    String text = textboxSearch.getText();

                    if (text.trim().length() == 0) {
                        rowSorter.setRowFilter(null);
                        rowSorterRecipe.setRowFilter(null);
                        rowSorterSup.setRowFilter(null);
                    } else {
                        rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
                        rowSorterRecipe.setRowFilter(RowFilter.regexFilter("(?i)" + text));
                        rowSorterSup.setRowFilter(RowFilter.regexFilter("(?i)" + text));
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

    public boolean keypadCheck() {
        if (jCheckBox1.isSelected()) {
            return enableKeypad = true;
        } else {
            return enableKeypad = false;
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

    public int getIDsupplier() {
        int id = (int) tableSupplier.getValueAt(tableSupplier.getSelectedRow(), 0);
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

    public void moveButton() {

        buttonTable1.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent evt) {
                {
                    int X = buttonTable1.getX() + evt.getX();
                    int Y = buttonTable1.getY() + evt.getY();
                    buttonTable1.setBounds(X, Y, 60, 60);
                }
            }
        });
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
        Orders = new javax.swing.JPanel();
        pnlOrderLeft = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable4 = new javax.swing.JTable();
        buttonReprint = new javax.swing.JButton();
        buttonLayout = new javax.swing.JButton();
        buttonTakeAway = new javax.swing.JButton();
        buttonReprint2 = new javax.swing.JButton();
        pnlLayout = new javax.swing.JPanel();
        buttonTable3 = new javax.swing.JButton();
        buttonTable1 = new javax.swing.JButton();
        buttonTable2 = new javax.swing.JButton();
        buttonTable4 = new javax.swing.JButton();
        buttonTable6 = new javax.swing.JButton();
        buttonTable7 = new javax.swing.JButton();
        buttonTable8 = new javax.swing.JButton();
        buttonTable5 = new javax.swing.JButton();
        buttonReprint1 = new javax.swing.JButton();
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
        pnlRecipe = new javax.swing.JPanel();
        buttonRecipeAdd = new javax.swing.JButton();
        buttonRecipeDelete = new javax.swing.JButton();
        buttonRecipeEdit = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        tableRecipe = new javax.swing.JTable();
        pnlSupplier = new javax.swing.JPanel();
        buttonRecipeAdd1 = new javax.swing.JButton();
        buttonRecipeDelete1 = new javax.swing.JButton();
        buttonRecipeEdit1 = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        tableSupplier = new javax.swing.JTable();
        radioID = new javax.swing.JRadioButton();
        radioItem = new javax.swing.JRadioButton();
        Management = new javax.swing.JPanel();
        buttonOrderHistory = new javax.swing.JButton();
        buttonAlert = new javax.swing.JButton();
        buttonEmail = new javax.swing.JButton();
        buttonMakeOrder = new javax.swing.JButton();
        buttonReports = new javax.swing.JButton();
        buttonEmployee = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblOrderHistory = new javax.swing.JTable();
        Settings = new javax.swing.JPanel();
        comboBoxSceen = new javax.swing.JComboBox<>();
        lblSettings = new javax.swing.JLabel();
        comboBoxLogo = new javax.swing.JComboBox<>();
        lblScreen = new javax.swing.JLabel();
        lblLogo = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jCheckBox1 = new javax.swing.JCheckBox();

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

        lblVersion.setText("Version 2.5.21");

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
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(buttonLogOut, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(buttonLogin, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlPanelLayout.createSequentialGroup()
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

        javax.swing.GroupLayout DashboardLayout = new javax.swing.GroupLayout(Dashboard);
        Dashboard.setLayout(DashboardLayout);
        DashboardLayout.setHorizontalGroup(
            DashboardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(DashboardLayout.createSequentialGroup()
                .addGroup(DashboardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(DashboardLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lblVersion)
                        .addGap(186, 186, 186)
                        .addComponent(buttonClose, javax.swing.GroupLayout.DEFAULT_SIZE, 218, Short.MAX_VALUE)
                        .addGap(217, 217, 217))
                    .addGroup(DashboardLayout.createSequentialGroup()
                        .addComponent(pnlLeft, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblImage, javax.swing.GroupLayout.DEFAULT_SIZE, 503, Short.MAX_VALUE)
                        .addGap(63, 63, 63)))
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
                                .addComponent(pnlLeft, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 89, Short.MAX_VALUE))
                            .addComponent(lblImage, javax.swing.GroupLayout.DEFAULT_SIZE, 373, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(DashboardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblVersion, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, DashboardLayout.createSequentialGroup()
                                .addComponent(buttonClose)
                                .addContainerGap())))
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
        jScrollPane2.setViewportView(jTable4);

        buttonReprint.setText("Reprint");
        buttonReprint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonReprintActionPerformed(evt);
            }
        });

        buttonLayout.setText("Change Layout");
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

        buttonReprint2.setText("Add Button");
        buttonReprint2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonReprint2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlOrderLeftLayout = new javax.swing.GroupLayout(pnlOrderLeft);
        pnlOrderLeft.setLayout(pnlOrderLeftLayout);
        pnlOrderLeftLayout.setHorizontalGroup(
            pnlOrderLeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlOrderLeftLayout.createSequentialGroup()
                .addGap(57, 57, 57)
                .addGroup(pnlOrderLeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(buttonReprint2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
                .addGap(1, 1, 1)
                .addComponent(buttonReprint2, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(57, Short.MAX_VALUE))
        );

        pnlLayout.setBackground(new java.awt.Color(255, 255, 255));
        pnlLayout.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        buttonTable3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/_o_tableS.jpg"))); // NOI18N
        buttonTable3.setContentAreaFilled(false);
        buttonTable3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonTable3ActionPerformed(evt);
            }
        });

        buttonTable1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/_o_tableB.jpg"))); // NOI18N
        buttonTable1.setContentAreaFilled(false);
        buttonTable1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonTable1ActionPerformed(evt);
            }
        });

        buttonTable2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/_o_tableB.jpg"))); // NOI18N
        buttonTable2.setContentAreaFilled(false);
        buttonTable2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonTable2ActionPerformed(evt);
            }
        });

        buttonTable4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/_o_tableS.jpg"))); // NOI18N
        buttonTable4.setContentAreaFilled(false);
        buttonTable4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonTable4ActionPerformed(evt);
            }
        });

        buttonTable6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/_o_tableS.jpg"))); // NOI18N
        buttonTable6.setContentAreaFilled(false);
        buttonTable6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonTable6ActionPerformed(evt);
            }
        });

        buttonTable7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/_o_tableS.jpg"))); // NOI18N
        buttonTable7.setContentAreaFilled(false);
        buttonTable7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonTable7ActionPerformed(evt);
            }
        });

        buttonTable8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/_o_tableBar.png"))); // NOI18N
        buttonTable8.setContentAreaFilled(false);
        buttonTable8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonTable8ActionPerformed(evt);
            }
        });

        buttonTable5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/_o_tableB.jpg"))); // NOI18N
        buttonTable5.setContentAreaFilled(false);
        buttonTable5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonTable5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlLayoutLayout = new javax.swing.GroupLayout(pnlLayout);
        pnlLayout.setLayout(pnlLayoutLayout);
        pnlLayoutLayout.setHorizontalGroup(
            pnlLayoutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlLayoutLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlLayoutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlLayoutLayout.createSequentialGroup()
                        .addComponent(buttonTable5)
                        .addGap(83, 83, 83)
                        .addComponent(buttonTable6)
                        .addContainerGap(196, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlLayoutLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(pnlLayoutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlLayoutLayout.createSequentialGroup()
                                .addComponent(buttonTable4)
                                .addGap(180, 180, 180))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlLayoutLayout.createSequentialGroup()
                                .addComponent(buttonTable2)
                                .addGap(200, 200, 200))))))
            .addGroup(pnlLayoutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pnlLayoutLayout.createSequentialGroup()
                    .addGap(10, 10, 10)
                    .addGroup(pnlLayoutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(buttonTable1, javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(buttonTable3, javax.swing.GroupLayout.Alignment.TRAILING))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 247, Short.MAX_VALUE)
                    .addGroup(pnlLayoutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(buttonTable8)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlLayoutLayout.createSequentialGroup()
                            .addComponent(buttonTable7)
                            .addGap(12, 12, 12)))
                    .addContainerGap()))
        );
        pnlLayoutLayout.setVerticalGroup(
            pnlLayoutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlLayoutLayout.createSequentialGroup()
                .addGroup(pnlLayoutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlLayoutLayout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(buttonTable5))
                    .addGroup(pnlLayoutLayout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(buttonTable2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 33, Short.MAX_VALUE)
                        .addComponent(buttonTable4)
                        .addGap(18, 18, 18)
                        .addComponent(buttonTable6)))
                .addGap(37, 37, 37))
            .addGroup(pnlLayoutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pnlLayoutLayout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(pnlLayoutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(pnlLayoutLayout.createSequentialGroup()
                            .addGap(107, 107, 107)
                            .addComponent(buttonTable3))
                        .addComponent(buttonTable1)
                        .addGroup(pnlLayoutLayout.createSequentialGroup()
                            .addComponent(buttonTable8)
                            .addGap(35, 35, 35)
                            .addComponent(buttonTable7)))
                    .addContainerGap(19, Short.MAX_VALUE)))
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
                .addComponent(pnlLayout, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(buttonReprint1, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(OrdersLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlOrderLeft, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        TabbedPanel.addTab("Orders", Orders);

        textboxSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textboxSearchActionPerformed(evt);
            }
        });

        lblSearch.setText("Search ");

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
                .addGap(18, 18, 18)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 688, Short.MAX_VALUE))
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
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 688, Short.MAX_VALUE))
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

        tableSupplier.setModel(new javax.swing.table.DefaultTableModel(
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
        tableSupplier.setGridColor(new java.awt.Color(204, 204, 204));
        jScrollPane5.setViewportView(tableSupplier);
        if (tableSupplier.getColumnModel().getColumnCount() > 0) {
            tableSupplier.getColumnModel().getColumn(2).setResizable(false);
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
                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 688, Short.MAX_VALUE))
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
                .addGroup(InventoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textboxSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblSearch))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(InventoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(radioID)
                    .addComponent(radioItem))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane2))
        );

        TabbedPanel.addTab("Inventory", Inventory);

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

        javax.swing.GroupLayout ManagementLayout = new javax.swing.GroupLayout(Management);
        Management.setLayout(ManagementLayout);
        ManagementLayout.setHorizontalGroup(
            ManagementLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ManagementLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(ManagementLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(ManagementLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(ManagementLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(buttonAlert, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(buttonOrderHistory, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(buttonMakeOrder, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(buttonEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(buttonReports, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(buttonEmployee, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 607, Short.MAX_VALUE)
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
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        TabbedPanel.addTab("Management", Management);

        comboBoxSceen.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Windowed Screen", "Fullscreen" }));
        comboBoxSceen.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                comboBoxSceenItemStateChanged(evt);
            }
        });
        comboBoxSceen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboBoxSceenActionPerformed(evt);
            }
        });

        lblSettings.setText("Settings");

        comboBoxLogo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Default", "Bistro", "Cattle Baron" }));
        comboBoxLogo.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                comboBoxLogoItemStateChanged(evt);
            }
        });

        lblScreen.setText("Resolution");

        lblLogo.setText("Set Logo");

        jButton1.setText("Refresh the database");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jCheckBox1.setText("Enable KeyPad");

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
                        .addGap(39, 39, 39)
                        .addGroup(SettingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(SettingsLayout.createSequentialGroup()
                                .addGroup(SettingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblScreen)
                                    .addComponent(lblLogo))
                                .addGap(32, 32, 32)
                                .addGroup(SettingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(comboBoxSceen, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(comboBoxLogo, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jButton1)
                            .addComponent(jCheckBox1))))
                .addContainerGap(568, Short.MAX_VALUE))
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
                .addGap(12, 12, 12)
                .addComponent(jCheckBox1)
                .addGap(18, 18, 18)
                .addComponent(jButton1)
                .addContainerGap(235, Short.MAX_VALUE))
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

    private void textboxSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textboxSearchActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textboxSearchActionPerformed

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
        NewOrder s = new NewOrder();
        s.setVisible(true);
    }//GEN-LAST:event_buttonTakeAwayActionPerformed

    private void buttonLayoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonLayoutActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_buttonLayoutActionPerformed

    private void buttonReprintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonReprintActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_buttonReprintActionPerformed

    private void comboBoxSceenItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_comboBoxSceenItemStateChanged
        if (evt.getStateChange() == ItemEvent.SELECTED) {
            String selected_item = comboBoxSceen.getSelectedItem().toString();
            switch (selected_item) {
                case "Windowed Screen":
                    this.setSize(800, 600);
                    this.setLocationRelativeTo(null);

                    break;

                case "Fullscreen":
                    Toolkit tk = Toolkit.getDefaultToolkit();
                    int xSize = ((int) tk.getScreenSize().getWidth());
                    int ySize = ((int) tk.getScreenSize().getHeight());
                    this.setSize(xSize, ySize);
                    this.setLocation(0, 0);
                    break;
            }
        }
    }//GEN-LAST:event_comboBoxSceenItemStateChanged

    private void comboBoxSceenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboBoxSceenActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_comboBoxSceenActionPerformed

    private void comboBoxLogoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_comboBoxLogoItemStateChanged
        if (evt.getStateChange() == ItemEvent.SELECTED) {
            String selected_item = comboBoxLogo.getSelectedItem().toString();
            switch (selected_item) {

                case "Default":
                    ImageIcon icon = new ImageIcon("./src/images/Logo_White.png");
                    lblImage.setIcon(icon);
                    break;

                case "Bistro":
                    ImageIcon icon1 = new ImageIcon("./src/images/Logo_Bistro.png");
                    lblImage.setIcon(icon1);
                    break;

                case "Cattle Baron":
                    ImageIcon icon2 = new ImageIcon("./src/images/Logo_Cattle_Baron.png");
                    lblImage.setIcon(icon2);
                    break;
            }
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
        AddDatabase s = new AddDatabase();
        s.setVisible(true);
        AddDatabase.getTabbedPanel().setSelectedIndex(1);
        if (keypadCheck() == true) {
        Keyboard k = new Keyboard();
        k.setLocation(350, 530);
        k.setVisible(true);
        }
    }//GEN-LAST:event_buttonRecipeAddActionPerformed

    private void buttonEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonEditActionPerformed
        AdminLogin s = new AdminLogin();
        s.setVisible(true);
    }//GEN-LAST:event_buttonEditActionPerformed

    private void buttonAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAddActionPerformed
        AddDatabase s = new AddDatabase();
        s.setVisible(true);
        Keyboard k = new Keyboard();
        k.setLocation(350, 530);
        k.setVisible(true);
    }//GEN-LAST:event_buttonAddActionPerformed

    private void buttonRecipeAdd1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonRecipeAdd1ActionPerformed
        AddDatabase s = new AddDatabase();
        s.setVisible(true);
        AddDatabase.getTabbedPanel().setSelectedIndex(2);
   if (keypadCheck() == true) {
        Keyboard k = new Keyboard();
        k.setLocation(350, 530);
        k.setVisible(true);
        }        
    }//GEN-LAST:event_buttonRecipeAdd1ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
//        try {
//            new DatabaseEdits.refresh();
//        } catch (SQLException ex) {
//            Logger.getLogger(MainSystem.class.getName()).log(Level.SEVERE, null, ex);
//        }

    }//GEN-LAST:event_jButton1ActionPerformed

    private void buttonReprint1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonReprint1ActionPerformed
        moveButton();
    }//GEN-LAST:event_buttonReprint1ActionPerformed

    private void buttonTable2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonTable2ActionPerformed
        if (tables.get("table2") == null) {
            tables.put("table2", new NewOrder());
            // Login user = new Login(tables.get("table2"));
            //  user.setVisible(true);
        } else {
            tables.get("table2").setVisible(true);
        }
    }//GEN-LAST:event_buttonTable2ActionPerformed

    private void buttonTable4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonTable4ActionPerformed
        if (tables.get("table4") == null) {
            tables.put("table4", new NewOrder());
            //  Login user = new Login(tables.get("table4"));
            //  user.setVisible(true);
        } else {
            tables.get("table4").setVisible(true);
        }
    }//GEN-LAST:event_buttonTable4ActionPerformed

    private void buttonTable8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonTable8ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_buttonTable8ActionPerformed

    private void buttonTable7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonTable7ActionPerformed
        if (tables.get("table7") == null) {
            tables.put("table7", new NewOrder());
            // Login user = new Login(tables.get("table7"));
            //  user.setVisible(true);
        } else {
            tables.get("table7").setVisible(true);
        }
    }//GEN-LAST:event_buttonTable7ActionPerformed

    private void buttonTable6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonTable6ActionPerformed
        if (tables.get("table6") == null) {
            tables.put("table6", new NewOrder());
            //  Login user = new Login(tables.get("table6"));
            //  user.setVisible(true);
        } else {
            tables.get("table6").setVisible(true);
        }
    }//GEN-LAST:event_buttonTable6ActionPerformed

    private void buttonTable5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonTable5ActionPerformed
        if (tables.get("table5") == null) {
            tables.put("table5", new NewOrder());
            // Login user = new Login(tables.get("table5"));
            //  user.setVisible(true);
        } else {
            tables.get("table5").setVisible(true);
        }
    }//GEN-LAST:event_buttonTable5ActionPerformed

    private void buttonTable3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonTable3ActionPerformed
        if (tables.get("table3") == null) {
            tables.put("table3", new NewOrder());
            //Login user = new Login(tables.get("table3"));
            //user.setVisible(true);
        } else {
            tables.get("table3").setVisible(true);
        }
    }//GEN-LAST:event_buttonTable3ActionPerformed

    private void buttonReprint2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonReprint2ActionPerformed
        layoutManager.newButton();
    }//GEN-LAST:event_buttonReprint2ActionPerformed

    private void buttonTable1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonTable1ActionPerformed
        if (tables.get("table1") == null) {
            tables.put("table1", new NewOrder());
            //Login user = new Login(tables.get("table3"));
            //user.setVisible(true);
        } else {
            tables.get("table1").setVisible(true);
        }
    }//GEN-LAST:event_buttonTable1ActionPerformed

    private void buttonDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonDeleteActionPerformed
        int confirm = JOptionPane.showConfirmDialog(null, "Are you sure?", null, JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            dbManager.removeInventory(getID());
        }
    }//GEN-LAST:event_buttonDeleteActionPerformed

    private void buttonRecipeDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonRecipeDeleteActionPerformed
        int confirm = JOptionPane.showConfirmDialog(null, "Are you sure?", null, JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            dbManager.removeRecipe(getIDrecipe());
        }
    }//GEN-LAST:event_buttonRecipeDeleteActionPerformed

    private void buttonRecipeDelete1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonRecipeDelete1ActionPerformed
        int confirm = JOptionPane.showConfirmDialog(null, "Are you sure?", null, JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            dbManager.removeSupplier(getIDsupplier());
        }
    }//GEN-LAST:event_buttonRecipeDelete1ActionPerformed

    private void buttonLogOutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonLogOutActionPerformed
        userManager.createLogout();
    }//GEN-LAST:event_buttonLogOutActionPerformed

    private void buttonLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonLoginActionPerformed
        userManager.createLogin();
    }//GEN-LAST:event_buttonLoginActionPerformed

    private void buttonMakeOrderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonMakeOrderActionPerformed
        OrderForm newOrder = new OrderForm();
        newOrder.setVisible(true);
    }//GEN-LAST:event_buttonMakeOrderActionPerformed

    private void buttonEmailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonEmailActionPerformed
        EmailForm newEmail = new EmailForm();
        newEmail.setVisible(true);
    }//GEN-LAST:event_buttonEmailActionPerformed

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
    private javax.swing.JButton buttonEvents;
    private javax.swing.JButton buttonLayout;
    private javax.swing.JButton buttonLogOut;
    private javax.swing.JButton buttonLogin;
    private javax.swing.JButton buttonMakeOrder;
    private javax.swing.JButton buttonOrderHistory;
    private javax.swing.JButton buttonPrint;
    private javax.swing.JButton buttonPromotions;
    private javax.swing.JButton buttonRecipeAdd;
    private javax.swing.JButton buttonRecipeAdd1;
    private javax.swing.JButton buttonRecipeDelete;
    private javax.swing.JButton buttonRecipeDelete1;
    private javax.swing.JButton buttonRecipeEdit;
    private javax.swing.JButton buttonRecipeEdit1;
    private javax.swing.JButton buttonReports;
    private javax.swing.JButton buttonReprint;
    private javax.swing.JButton buttonReprint1;
    private javax.swing.JButton buttonReprint2;
    private javax.swing.JButton buttonSpecials;
    private javax.swing.JButton buttonTable1;
    private javax.swing.JButton buttonTable2;
    private javax.swing.JButton buttonTable3;
    private javax.swing.JButton buttonTable4;
    private javax.swing.JButton buttonTable5;
    private javax.swing.JButton buttonTable6;
    private javax.swing.JButton buttonTable7;
    private javax.swing.JButton buttonTable8;
    private javax.swing.JButton buttonTakeAway;
    private javax.swing.JComboBox<String> comboBoxLogo;
    private javax.swing.JComboBox<String> comboBoxSceen;
    private javax.swing.JButton jButton1;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JTable jTable4;
    private static javax.swing.JLabel lblClock;
    private static javax.swing.JLabel lblDate;
    private javax.swing.JLabel lblImage;
    private javax.swing.JLabel lblLogo;
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
    private javax.swing.JPanel pnlSupplier;
    private javax.swing.JRadioButton radioID;
    private javax.swing.JRadioButton radioItem;
    public static javax.swing.JTable tableRecipe;
    public static javax.swing.JTable tableSupplier;
    public static javax.swing.JTable tblInventory;
    private static javax.swing.JTable tblOrderHistory;
    private static javax.swing.JTextField textboxSearch;
    // End of variables declaration//GEN-END:variables
}
