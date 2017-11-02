package system;

import java.io.File;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JTabbedPane;
import javax.swing.filechooser.FileFilter;

/**
 *
 * @author GGPQ9ZJ42
 */
public class AddDatabase extends javax.swing.JFrame {

    String imagePath;
    String newImagePath;
    dbManager database = new dbManager();
    MainSystem system;
    Keyboard k = new Keyboard();

    public AddDatabase(MainSystem sytem, boolean enabled) {
        initComponents();
        this.system = sytem;
        isKeypadEnable(enabled);
    }

    public final void isKeypadEnable(boolean enabled) {
        if (enabled == true) {
            k = new Keyboard();
            k.setVisible(true);
        }
    }

    public void insert() {
        if (!"".equals(textfieldItem.getText()) || !"".equals(textfieldQty.getText())) {
            database.insertInventory(getItem(), getInventoryCategory(), getQuantity(), getLimit(), getReorderLevel(), getItemCost());
            system.populateInvnetoryTable();
        } else {
        }
        if (!"".equals(textfieldDisName.getText()) || !"".equals(textfieldDisEmail.getText()) || !"".equals(textfieldDisContact.getText())) {
            database.insertSupplier(getSupName(), getSupEmail(), getSupContact(), getSupAddress());
            system.populateSupplierTable();
        } else {
        }
        if (!"".equals(textRecipe.getText()) || !"".equals(textPrice.getText())) {
            database.insertRecipe(getRecipe(), getPrice(), getVAT(), getImageDirectory(), getCategory());
            system.populateRecipeTable();
            RecipeForm newForm = new RecipeForm(getRecipe(), system);
            newForm.setVisible(true);
        } else {
        }
    }

    public void openImage() {
        JFileChooser fileChooser;
        if (imagePath != null && !imagePath.equals("")) {
            fileChooser = new JFileChooser(imagePath);
        } else {
            fileChooser = new JFileChooser();
        }

        FileFilter imageFilter = new FileFilter() {
            @Override
            public String getDescription() {
                return "image file (*.JPG)";
            }

            @Override
            public boolean accept(File file) {
                if (file.isDirectory()) {
                    return true;
                } else {
                    return file.getName().toLowerCase().endsWith(".jpg");
                }
            }
        };

        fileChooser.setFileFilter(imageFilter);
        fileChooser.setDialogTitle("Open Image File");
        fileChooser.setAcceptAllFileFilterUsed(false);

        int userChoice = fileChooser.showOpenDialog(this);
        if (userChoice == JFileChooser.APPROVE_OPTION) {
            newImagePath = fileChooser.getSelectedFile().getAbsolutePath();
            imagePath = fileChooser.getSelectedFile().getParent();
        }
        ImageIcon icon = new ImageIcon(newImagePath);
        recipeImage.setIcon(icon);
    }

    public void calculateVAT() {
        double numVAT = (Double.parseDouble(textPrice.getText())) * 0.14;
        textVAT.setText(String.format("%.2f", numVAT));
    }

    public String getItem() {
        return textfieldItem.getText();
    }

    public int getQuantity() {
        return Integer.parseInt(textfieldQty.getText());
    }

    public int getLimit() {
        return Integer.parseInt(textfieldLimit.getText());
    }

    public Double getReorderLevel() {
        return (Double.parseDouble(comboxThreshold.getSelectedItem().toString().replace("%", "")) / 100);
    }

    public String getInventoryCategory() {
        return comboxThreshold1.getSelectedItem().toString();
    }

    public String getRecipe() {
        return textRecipe.getText();
    }

    public Double getPrice() {
        return Double.parseDouble(textPrice.getText());
    }

    public Double getItemCost() {
        return Double.parseDouble(costText.getText());
    }

    public Double getVAT() {
        return Double.parseDouble(textVAT.getText());
    }

    public String getCategory() {
        return comboxCat.getSelectedItem().toString();
    }

    public String getImageDirectory() {
        String testPath = System.getProperty("user.home") + File.separator + "Documents\\NetBeansProjects\\stockManager\\ResturantManagementSystem\\src\\";
        //Developers Line
        if (newImagePath.startsWith(testPath)) {
            String fileName = newImagePath.substring(newImagePath.lastIndexOf("\\") + 1);
            newImagePath = "./src/images/" + fileName;
        }
        return newImagePath.replace("\\", "/");
    }

    public String getSupName() {
        return textfieldDisName.getText();
    }

    public String getSupEmail() {
        return textfieldDisEmail.getText();
    }

    public String getSupContact() {
        return textfieldDisContact.getText();
    }

    public String getSupAddress() {
        return textfieldDisAddress.getText();
    }

    public static JTabbedPane getTabbedPanel() {
        return tabbedPanel;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTextField1 = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        tabbedPanel = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        lblQty = new javax.swing.JLabel();
        lblItem = new javax.swing.JLabel();
        lblTitle = new javax.swing.JLabel();
        textfieldQty = new javax.swing.JTextField();
        textfieldItem = new javax.swing.JTextField();
        textfieldLimit = new javax.swing.JTextField();
        lblQty3 = new javax.swing.JLabel();
        lblQty4 = new javax.swing.JLabel();
        comboxThreshold = new javax.swing.JComboBox<>();
        lblQty5 = new javax.swing.JLabel();
        comboxThreshold1 = new javax.swing.JComboBox<>();
        costText = new javax.swing.JTextField();
        costLabel = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        lblCostPerItem1 = new javax.swing.JLabel();
        textRecipe = new javax.swing.JTextField();
        textVAT = new javax.swing.JTextField();
        textPrice = new javax.swing.JTextField();
        lblItem1 = new javax.swing.JLabel();
        lblQty1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        recipeImage = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        lblQty2 = new javax.swing.JLabel();
        comboxCat = new javax.swing.JComboBox<>();
        lblQty6 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        lblDistInfo = new javax.swing.JLabel();
        lblDisName = new javax.swing.JLabel();
        textfieldDisName = new javax.swing.JTextField();
        textfieldDisEmail = new javax.swing.JTextField();
        textfieldDisContact = new javax.swing.JTextField();
        textfieldDisAddress = new javax.swing.JTextField();
        lblDisContact2 = new javax.swing.JLabel();
        lblDisContact1 = new javax.swing.JLabel();
        lblDisContact = new javax.swing.JLabel();
        buttonAccpet = new javax.swing.JButton();
        buttonCancel = new javax.swing.JButton();

        jTextField1.setText("jTextField1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setAlwaysOnTop(true);
        setUndecorated(true);

        jPanel4.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        lblQty.setText("Quanity");

        lblItem.setText("Item");

        lblTitle.setText("Inventory Details");

        textfieldQty.setBackground(new java.awt.Color(204, 204, 204));
        textfieldQty.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                textfieldQtyMouseClicked(evt);
            }
        });
        textfieldQty.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textfieldQtyActionPerformed(evt);
            }
        });

        textfieldItem.setBackground(new java.awt.Color(204, 204, 204));
        textfieldItem.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                textfieldItemMouseClicked(evt);
            }
        });

        textfieldLimit.setBackground(new java.awt.Color(204, 204, 204));
        textfieldLimit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                textfieldLimitMouseClicked(evt);
            }
        });

        lblQty3.setText("Reorder Level");

        lblQty4.setText("Item Limit");

        comboxThreshold.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "20%", "25%", "30%", "40%", "50%", "60%", "70%", "75%", "80%" }));

        lblQty5.setText("Category");

        comboxThreshold1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Meat", "Grain", "Dairy", "Vegetable", "Soft Drinks", "Alcohol", "Sauces" }));

        costText.setBackground(new java.awt.Color(204, 204, 204));
        costText.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                costTextMouseClicked(evt);
            }
        });

        costLabel.setText("Cost per Unit");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(lblTitle)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblQty3)
                                    .addComponent(lblQty5))
                                .addGap(44, 44, 44)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(comboxThreshold, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(comboxThreshold1, 0, 162, Short.MAX_VALUE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(lblItem)
                                                .addComponent(lblQty))
                                            .addGap(69, 69, 69))
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                            .addComponent(lblQty4)
                                            .addGap(61, 61, 61)))
                                    .addComponent(costLabel))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(costText)
                                    .addComponent(textfieldQty, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(textfieldItem, javax.swing.GroupLayout.DEFAULT_SIZE, 162, Short.MAX_VALUE)
                                    .addComponent(textfieldLimit, javax.swing.GroupLayout.Alignment.LEADING))))
                        .addGap(65, 65, 65))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTitle)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblItem)
                            .addComponent(textfieldItem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblQty)
                            .addComponent(textfieldQty, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(textfieldLimit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblQty4))
                        .addGap(13, 13, 13)
                        .addComponent(costLabel))
                    .addComponent(costText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblQty3)
                    .addComponent(comboxThreshold, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblQty5)
                    .addComponent(comboxThreshold1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(50, Short.MAX_VALUE))
        );

        tabbedPanel.addTab("Inventory", jPanel1);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        lblCostPerItem1.setText("Recipe Name");

        textRecipe.setBackground(new java.awt.Color(204, 204, 204));
        textRecipe.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                textRecipeMouseClicked(evt);
            }
        });
        textRecipe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textRecipeActionPerformed(evt);
            }
        });

        textVAT.setBackground(new java.awt.Color(204, 204, 204));
        textVAT.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                textVATMouseClicked(evt);
            }
        });

        textPrice.setBackground(new java.awt.Color(204, 204, 204));
        textPrice.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                textPriceMouseClicked(evt);
            }
        });

        lblItem1.setText("Price");

        lblQty1.setText("VAT");

        jButton1.setBackground(new java.awt.Color(0, 138, 231));
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Calculate VAT");
        jButton1.setContentAreaFilled(false);
        jButton1.setOpaque(true);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        recipeImage.setBackground(new java.awt.Color(204, 204, 204));
        recipeImage.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jButton2.setBackground(new java.awt.Color(0, 138, 231));
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("Add Image");
        jButton2.setContentAreaFilled(false);
        jButton2.setOpaque(true);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        lblQty2.setText("Category");

        comboxCat.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Main Meal", "Light Meal", "Dessert", "Drinks", "Extra" }));

        lblQty6.setText("Preview");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(lblQty2)
                                .addComponent(lblQty1)
                                .addComponent(lblItem1)
                                .addComponent(lblCostPerItem1, javax.swing.GroupLayout.Alignment.TRAILING))
                            .addComponent(recipeImage, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(comboxCat, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(textRecipe)
                                .addComponent(textVAT)
                                .addComponent(textPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addComponent(lblQty6)))
                .addContainerGap(76, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textRecipe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblCostPerItem1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textPrice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblItem1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textVAT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblQty1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(comboxCat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblQty2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2))
                    .addComponent(recipeImage, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblQty6)
                .addContainerGap(23, Short.MAX_VALUE))
        );

        tabbedPanel.addTab("Recipe", jPanel2);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        lblDistInfo.setText("Distrubutor Informantion");

        lblDisName.setText("Distrubutor Name");

        textfieldDisName.setBackground(new java.awt.Color(204, 204, 204));
        textfieldDisName.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                textfieldDisNameMouseClicked(evt);
            }
        });

        textfieldDisEmail.setBackground(new java.awt.Color(204, 204, 204));
        textfieldDisEmail.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                textfieldDisEmailMouseClicked(evt);
            }
        });

        textfieldDisContact.setBackground(new java.awt.Color(204, 204, 204));
        textfieldDisContact.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                textfieldDisContactMouseClicked(evt);
            }
        });

        textfieldDisAddress.setBackground(new java.awt.Color(204, 204, 204));
        textfieldDisAddress.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                textfieldDisAddressMouseClicked(evt);
            }
        });

        lblDisContact2.setText("Distrubutor Address ");

        lblDisContact1.setText("Distrubutor Contact no. ");

        lblDisContact.setText("Distrubutor Email ");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                        .addComponent(lblDisContact1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(textfieldDisContact, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lblDistInfo, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblDisName)
                            .addComponent(lblDisContact2))
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(28, 28, 28)
                                .addComponent(textfieldDisAddress, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(textfieldDisName, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                        .addComponent(lblDisContact)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(textfieldDisEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(22, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblDistInfo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblDisName)
                    .addComponent(textfieldDisName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblDisContact)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(textfieldDisEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblDisContact1)
                            .addComponent(textfieldDisContact, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(textfieldDisAddress, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblDisContact2))))
                .addContainerGap(80, Short.MAX_VALUE))
        );

        tabbedPanel.addTab("Supplier", jPanel3);

        buttonAccpet.setBackground(new java.awt.Color(75, 75, 75));
        buttonAccpet.setForeground(new java.awt.Color(255, 255, 255));
        buttonAccpet.setText("Accept");
        buttonAccpet.setContentAreaFilled(false);
        buttonAccpet.setOpaque(true);
        buttonAccpet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonAccpetActionPerformed(evt);
            }
        });

        buttonCancel.setBackground(new java.awt.Color(75, 75, 75));
        buttonCancel.setForeground(new java.awt.Color(255, 255, 255));
        buttonCancel.setText("Canel");
        buttonCancel.setContentAreaFilled(false);
        buttonCancel.setOpaque(true);
        buttonCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonCancelActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tabbedPanel)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(104, 104, 104)
                .addComponent(buttonAccpet)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(buttonCancel)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(tabbedPanel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonCancel)
                    .addComponent(buttonAccpet))
                .addContainerGap())
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

    private void buttonAccpetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAccpetActionPerformed
        insert();
        k.dispose();
        this.dispose();
    }//GEN-LAST:event_buttonAccpetActionPerformed

    private void buttonCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonCancelActionPerformed
        k.dispose();
        this.dispose();
    }//GEN-LAST:event_buttonCancelActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        calculateVAT();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        openImage();
    }//GEN-LAST:event_jButton2ActionPerformed


    private void textfieldQtyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textfieldQtyActionPerformed

    }//GEN-LAST:event_textfieldQtyActionPerformed

    private void textfieldItemMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_textfieldItemMouseClicked
        k.setTextfield(textfieldItem);
    }//GEN-LAST:event_textfieldItemMouseClicked

    private void textfieldQtyMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_textfieldQtyMouseClicked
        k.setTextfield(textfieldQty);
    }//GEN-LAST:event_textfieldQtyMouseClicked

    private void textfieldLimitMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_textfieldLimitMouseClicked
        k.setTextfield(textfieldLimit);
    }//GEN-LAST:event_textfieldLimitMouseClicked

    private void textRecipeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_textRecipeMouseClicked
        k.setTextfield(textRecipe);
    }//GEN-LAST:event_textRecipeMouseClicked

    private void textPriceMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_textPriceMouseClicked
        k.setTextfield(textPrice);
    }//GEN-LAST:event_textPriceMouseClicked

    private void textVATMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_textVATMouseClicked
        k.setTextfield(textVAT);
    }//GEN-LAST:event_textVATMouseClicked

    private void textfieldDisNameMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_textfieldDisNameMouseClicked
        k.setTextfield(textfieldDisName);
    }//GEN-LAST:event_textfieldDisNameMouseClicked

    private void textfieldDisEmailMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_textfieldDisEmailMouseClicked
        k.setTextfield(textfieldDisEmail);
    }//GEN-LAST:event_textfieldDisEmailMouseClicked

    private void textfieldDisContactMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_textfieldDisContactMouseClicked
        k.setTextfield(textfieldDisContact);
    }//GEN-LAST:event_textfieldDisContactMouseClicked

    private void textfieldDisAddressMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_textfieldDisAddressMouseClicked
        k.setTextfield(textfieldDisAddress);
    }//GEN-LAST:event_textfieldDisAddressMouseClicked

    private void textRecipeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textRecipeActionPerformed

    }//GEN-LAST:event_textRecipeActionPerformed

    private void costTextMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_costTextMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_costTextMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonAccpet;
    private javax.swing.JButton buttonCancel;
    private javax.swing.JComboBox<String> comboxCat;
    private javax.swing.JComboBox<String> comboxThreshold;
    private javax.swing.JComboBox<String> comboxThreshold1;
    private javax.swing.JLabel costLabel;
    private static javax.swing.JTextField costText;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JLabel lblCostPerItem1;
    private javax.swing.JLabel lblDisContact;
    private javax.swing.JLabel lblDisContact1;
    private javax.swing.JLabel lblDisContact2;
    private javax.swing.JLabel lblDisName;
    private javax.swing.JLabel lblDistInfo;
    private javax.swing.JLabel lblItem;
    private javax.swing.JLabel lblItem1;
    private javax.swing.JLabel lblQty;
    private javax.swing.JLabel lblQty1;
    private javax.swing.JLabel lblQty2;
    private javax.swing.JLabel lblQty3;
    private javax.swing.JLabel lblQty4;
    private javax.swing.JLabel lblQty5;
    private javax.swing.JLabel lblQty6;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JLabel recipeImage;
    private static javax.swing.JTabbedPane tabbedPanel;
    public static javax.swing.JTextField textPrice;
    public static javax.swing.JTextField textRecipe;
    public static javax.swing.JTextField textVAT;
    public static javax.swing.JTextField textfieldDisAddress;
    public static javax.swing.JTextField textfieldDisContact;
    public static javax.swing.JTextField textfieldDisEmail;
    public static javax.swing.JTextField textfieldDisName;
    private static javax.swing.JTextField textfieldItem;
    private static javax.swing.JTextField textfieldLimit;
    private static javax.swing.JTextField textfieldQty;
    // End of variables declaration//GEN-END:variables
}
