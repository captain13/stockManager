package system;

import java.awt.HeadlessException;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Andrew
 */
public class dbManager {

    logSystem logs = new logSystem();
    internalClock clock = new internalClock();
    String url = "jdbc:mysql://localhost:3306/resturantdb";
    String username = "root";
    String password = "root";
    String driver = "com.mysql.jdbc.Driver";
    String currentUsersHomeDir = System.getProperty("user.home");
    String location = currentUsersHomeDir + File.separator + "Documents\\NetBeansProjects\\stockManager\\ResturantManagementSystem\\src\\database\\resturantDB_bk.sql";

    public void dbValidation() {
        String url = "jdbc:mysql://localhost:3306";
        try {
            Class.forName(driver).newInstance();
            Connection conn = DriverManager.getConnection(url, username, password);
            Statement s = conn.createStatement();
            s.executeUpdate("CREATE SCHEMA IF NOT EXISTS `resturantDB`");
            s.close();
            conn.close();
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {

        }
    }

    public Object[][] getInventoryData() {
        Object[][] rowData = null;
        try {
            Class.forName(driver).newInstance();
            Connection conn = DriverManager.getConnection(url, username, password);
            Statement s = conn.createStatement();
            String query = "SELECT * FROM inventory ";
            ResultSet rs = s.executeQuery(query);
            ResultSetMetaData metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();
            int rowCount = 0;
            while (rs.next()) {
                rowCount++;
            }
            rs = s.executeQuery(query);
            rowData = new Object[rowCount][columnCount];
            int i = 0;
            while (rs.next()) {
                rowData[i][0] = rs.getObject(1);
                rowData[i][1] = rs.getObject(2);
                rowData[i][2] = rs.getObject(3);
                rowData[i][3] = rs.getObject(4);
                rowData[i][4] = rs.getObject(5);
                rowData[i][5] = rs.getObject(6);
                i++;
            }

            rs.close();
            s.close();
            conn.close();
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | SQLException exc) {
        }
        return rowData;
    }

    public Object[][] getRecipeData() {
        Object[][] rowData = null;
        try {
            Class.forName(driver).newInstance();
            Connection conn = DriverManager.getConnection(url, username, password);
            Statement s = conn.createStatement();
            String query = "SELECT recipeID, recipeName,recipeType, recipePrice, recipeVAT FROM recipe";
            ResultSet rs = s.executeQuery(query);
            ResultSetMetaData metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();
            int rowCount = 0;
            while (rs.next()) {
                rowCount++;
            }
            rs = s.executeQuery(query);
            rowData = new Object[rowCount][columnCount];
            int i = 0;
            while (rs.next()) {
                rowData[i][0] = rs.getObject(1);
                rowData[i][1] = rs.getObject(2);
                rowData[i][2] = rs.getObject(3);
                rowData[i][3] = rs.getObject(4);
                rowData[i][4] = rs.getObject(5);
                i++;
            }

            rs.close();
            s.close();
            conn.close();
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | SQLException exc) {
        }
        return rowData;
    }

    public Object[][] getRecipeListData() {
        Object[][] rowData = null;
        try {
            Class.forName(driver).newInstance();
            Connection conn = DriverManager.getConnection(url, username, password);
            Statement s = conn.createStatement();
            String query = "SELECT inventory_recipe.recipeID, recipe.recipeName, inventory_recipe.inventoryID, "
                    + "inventory.item,inventory_recipe.qty "
                    + "FROM inventory_recipe, recipe,inventory "
                    + "WHERE recipe.recipeID=inventory_recipe.recipeID "
                    + "AND inventory.inventoryID=inventory_recipe.inventoryID ";
            ResultSet rs = s.executeQuery(query);
            ResultSetMetaData metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();
            int rowCount = 0;
            while (rs.next()) {
                rowCount++;
            }
            rs = s.executeQuery(query);
            rowData = new Object[rowCount][columnCount];
            int i = 0;
            while (rs.next()) {
                rowData[i][0] = rs.getObject(1);
                rowData[i][1] = rs.getObject(2);
                rowData[i][2] = rs.getObject(3);
                rowData[i][3] = rs.getObject(4);
                rowData[i][4] = rs.getObject(5);
                i++;
            }

            rs.close();
            s.close();
            conn.close();
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | SQLException exc) {
        }
        return rowData;
    }

    public Object[][] getSuppleirData() {
        Object[][] rowData = null;
        try {
            Class.forName(driver).newInstance();
            Connection conn = DriverManager.getConnection(url, username, password);
            Statement s = conn.createStatement();
            String query = "SELECT * FROM supplier";
            ResultSet rs = s.executeQuery(query);
            ResultSetMetaData metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();
            int rowCount = 0;
            while (rs.next()) {
                rowCount++;
            }
            rs = s.executeQuery(query);
            rowData = new Object[rowCount][columnCount];
            int i = 0;
            while (rs.next()) {
                rowData[i][0] = rs.getObject(1);
                rowData[i][1] = rs.getObject(2);
                rowData[i][2] = rs.getObject(3);
                rowData[i][3] = rs.getObject(4);
                rowData[i][4] = rs.getObject(5);
                i++;
            }
            rs.close();
            s.close();
            conn.close();
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | SQLException exc) {
        }
        return rowData;
    }
    
     public Object[][] getOrderData() {
        Object[][] rowData = null;
        try {
            Class.forName(driver).newInstance();
            Connection conn = DriverManager.getConnection(url, username, password);
            Statement s = conn.createStatement();
            String query = "SELECT stockOrderID, inventory.item, supplier.supplierName,dateOrdered, dateETA, quantity, status "
                    + "FROM stockOrder, inventory, supplier "
                    + "WHERE inventory.inventoryID=stockorder.inventoryID "
                    + "AND supplier.supplierID=stockorder.supplierID";
            ResultSet rs = s.executeQuery(query);
            ResultSetMetaData metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();
            int rowCount = 0;
            while (rs.next()) {
                rowCount++;
            }
            rs = s.executeQuery(query);
            rowData = new Object[rowCount][columnCount];
            int i = 0;
            while (rs.next()) {
                rowData[i][0] = rs.getObject(1);
                rowData[i][1] = rs.getObject(2);
                rowData[i][2] = rs.getObject(3);
                rowData[i][3] = rs.getObject(4);
                rowData[i][4] = rs.getObject(5);
                rowData[i][5] = rs.getObject(6);
                rowData[i][6] = rs.getObject(7);
                i++;
            }
            rs.close();
            s.close();
            conn.close();
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | SQLException exc) {
        }
        return rowData;
    }

    public void populateEmpTable() {
        String columnNamesEmp[] = {"ID", "First Name", "Last Name", "Number", "Hours Worked"};
        try {
            Class.forName(driver).newInstance();
            Connection conn = DriverManager.getConnection(url, username, password);
            Statement s = conn.createStatement();
            String query = "SELECT employeeID,employeeFName,employeeLName,employeeContactNumber,employeeHoursWorked FROM employee";
            ResultSet rs = s.executeQuery(query);
            ResultSetMetaData metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();
            DefaultTableModel tableModel3 = new DefaultTableModel();
            Employee.tableEmp.setModel(tableModel3);

            for (int i = 0; i < columnCount; i++) {
                tableModel3.addColumn(columnNamesEmp[i]);
            }
            Object[] row = new Object[columnCount];

            while (rs.next()) {
                for (int i = 0; i < columnCount; i++) {
                    row[i] = rs.getObject(i + 1);
                }
                tableModel3.addRow(row);
            }
            rs.close();
            s.close();
            conn.close();
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | SQLException exc) {
        }
    }

    public void populateReservation() {
        String columnNamesEmp[] = {"Employee", "Date", "Time", "Customer", "Table No.", "No. Customer"};
        try {
            Class.forName(driver).newInstance();
            Connection conn = DriverManager.getConnection(url, username, password);
            Statement s = conn.createStatement();
            String query = "SELECT employeeID,reservationDate,reservationTime,reservationCustomer,reservationTableNumber,reservationNumberPeople FROM reservation";
            ResultSet rs = s.executeQuery(query);
            ResultSetMetaData metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();
            DefaultTableModel tableModelRes = new DefaultTableModel();
            Booking.tableRes.setModel(tableModelRes);

            for (int i = 0; i < columnCount; i++) {
                tableModelRes.addColumn(columnNamesEmp[i]);
            }
            Object[] row = new Object[columnCount];

            while (rs.next()) {
                for (int i = 0; i < columnCount; i++) {
                    row[i] = rs.getObject(i + 1);
                }
                tableModelRes.addRow(row);
            }
            Booking.tableRes.getColumnModel().getColumn(0).setPreferredWidth(45);
            Booking.tableRes.getColumnModel().getColumn(2).setPreferredWidth(45);
            rs.close();
            s.close();
            conn.close();
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | SQLException exc) {
            System.out.println(exc);
        }
    }

    public void populateSales(JTable sales, String type) {
        String columnNames[] = {"ID", "Sales Date", "Total Sale"};
        String query = null;
        String date = clock.getCurrentDate();
        String currentDATE[] = clock.getCurrentDate().split("-");
        String month = currentDATE[1];
        try {
            Class.forName(driver).newInstance();
            Connection conn = DriverManager.getConnection(url, username, password);
            Statement s = conn.createStatement();
            if ("ALL".equals(type)) {
                query = "SELECT* FROM sales "
                        + "UNION ALL SELECT '','TOTAL', SUM(totalCost) FROM sales";
            }
            if ("DAY".equals(type)) {
                query = "SELECT* FROM sales WHERE salesDate='" + date + "' "
                        + "UNION ALL SELECT '','TOTAL', SUM(totalCost) FROM sales "
                        + "WHERE salesDate='" + date + "'";
            }
            if ("MONTH".equals(type)) {
                query = "SELECT* FROM sales WHERE MONTH(salesDate)='" + month + "' "
                        + "UNION ALL SELECT '','TOTAL', SUM(totalCost) FROM sales "
                        + "WHERE MONTH(salesDate)='" + month + "'";
            }
            ResultSet rs = s.executeQuery(query);
            ResultSetMetaData metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();
            DefaultTableModel tableModel = new DefaultTableModel();
            sales.setModel(tableModel);

            for (int i = 0; i < columnCount; i++) {
                tableModel.addColumn(columnNames[i]);
            }
            Object[] row = new Object[columnCount];

            while (rs.next()) {
                for (int i = 0; i < columnCount; i++) {
                    row[i] = rs.getObject(i + 1);
                }
                tableModel.addRow(row);
            }
            rs.close();
            s.close();
            conn.close();
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | SQLException exc) {
            System.out.println(exc);
        }
    }

    public void populateEmployeeSales(JTable sales) {
        String columnNames[] = {"Sale ID", "User", "Employee ID"};
        try {
            Class.forName(driver).newInstance();
            Connection conn = DriverManager.getConnection(url, username, password);
            Statement s = conn.createStatement();
            String query = "SELECT sales_employee.salesID, employee.employeeFName, sales_employee.employeeID FROM sales_employee,employee WHERE employee.employeeID=sales_employee.employeeID ";
            ResultSet rs = s.executeQuery(query);
            ResultSetMetaData metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();
            DefaultTableModel tableModel = new DefaultTableModel();
            sales.setModel(tableModel);

            for (int i = 0; i < columnCount; i++) {
                tableModel.addColumn(columnNames[i]);
            }
            Object[] row = new Object[columnCount];

            while (rs.next()) {
                for (int i = 0; i < columnCount; i++) {
                    row[i] = rs.getObject(i + 1);
                }
                tableModel.addRow(row);
            }
            rs.close();
            s.close();
            conn.close();
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | SQLException exc) {
            System.out.println(exc);
        }
    }

    public Object[][] getSpecialDetails() {
        Object[][] row = new Object[getRecipesCount()][6];
        try {
            Class.forName(driver).newInstance();
            Connection conn = DriverManager.getConnection(url, username, password);
            Statement s = conn.createStatement();
            String query = "SELECT recipe.specialsID, recipeName,recipeType, specialsPrice,recipeImageDirectory, status "
                    + "FROM recipe,specials "
                    + "WHERE recipe.specialsID=specials.specialsID";
            ResultSet rs = s.executeQuery(query);

            int i = 0;
            while (rs.next()) {
                row[i][0] = rs.getObject(1);
                row[i][1] = rs.getObject(2);
                row[i][2] = rs.getObject(3);
                row[i][3] = rs.getObject(4);
                row[i][4] = rs.getObject(6);
                row[i][5] = rs.getObject(5);
                i++;
            }

            rs.close();
            s.close();
            conn.close();
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | SQLException exc) {
            System.out.println(exc);
        }
        return row;
    }

    public String[][] getIngredients() {
        int count = 0;
        String ingredients[][] = null;
        try {
            Class.forName(driver).newInstance();
            Connection conn = DriverManager.getConnection(url, username, password);
            Statement s = conn.createStatement();

            String queryCount = "SELECT COUNT(inventoryID) FROM inventory ";
            ResultSet rs = s.executeQuery(queryCount);

            if (rs.next()) {
                count = rs.getInt(1);
            }
            String query = "SELECT inventoryID,item FROM inventory ";
            rs = s.executeQuery(query);
            ingredients = new String[count][2];
            int i = 0;
            while (rs.next()) {
                ingredients[i][0] = rs.getString("inventoryID");
                ingredients[i][1] = rs.getString("item");
                i++;
            }

            rs.close();
            s.close();
            conn.close();
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | SQLException exc) {
            System.out.println(exc);
        }
        return ingredients;
    }

    public Object[][] getRecipe() {
        int count = getRecipesCount();
        Object recipe[][] = new Object[count][6];
        try {
            Class.forName(driver).newInstance();
            Connection conn = DriverManager.getConnection(url, username, password);
            Statement s = conn.createStatement();
            String query = "SELECT recipeName,recipePrice,recipeImageDirectory,recipeType,recipeCount FROM recipe ";
            ResultSet rs = s.executeQuery(query);

            int i = 0;
            while (rs.next()) {
                recipe[i][0] = i;
                recipe[i][1] = rs.getString(1);
                recipe[i][2] = rs.getString(2);
                recipe[i][3] = rs.getString(3);
                recipe[i][4] = rs.getString(4);
                recipe[i][5] = rs.getString(5);
                i++;
            }

//            for (int j = 0; j < i; j++) {
//                System.out.println(recipe[j][0]);
//                System.out.println(recipe[j][1]);
//                System.out.println(recipe[j][2]);
//                System.out.println(recipe[j][3]);
//            }
//                recipeName.add(rs.getString(1));
//                recipeIndex.add(rs.getString(2));
//                recipeImage.add(rs.getString(3));
            rs.close();
            s.close();
            conn.close();
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | SQLException exc) {
            System.out.println(exc);
        }
        return recipe;
    }

    public Object[][] getSupplier() {
        int count = getSupplierCount();
        Object supplier[][] = new Object[count][5];
        try {
            Class.forName(driver).newInstance();
            Connection conn = DriverManager.getConnection(url, username, password);
            Statement s = conn.createStatement();
            String query = "SELECT * FROM supplier ";
            ResultSet rs = s.executeQuery(query);
            int i = 0;
            while (rs.next()) {
                supplier[i][0] = rs.getString(1);
                supplier[i][1] = rs.getString(2);
                supplier[i][2] = rs.getString(3);
                supplier[i][3] = rs.getString(4);
                supplier[i][4] = rs.getString(5);
                i++;
            }

            rs.close();
            s.close();
            conn.close();
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | SQLException exc) {
            System.out.println(exc);
        }
        return supplier;
    }

    public int getRecipesCount() {
        int count = 0;
        try {
            Class.forName(driver).newInstance();
            Connection conn = DriverManager.getConnection(url, username, password);
            Statement s = conn.createStatement();
            String query = "SELECT COUNT(recipeID) FROM recipe";
            ResultSet rs = s.executeQuery(query);
            if (rs.next()) {
                count = rs.getInt(1);
            }
            rs.close();
            s.close();
            conn.close();
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | SQLException exc) {
            System.out.println(exc);
        }
        return count;
    }

    public int getSupplierCount() {
        int count = 0;
        try {
            Class.forName(driver).newInstance();
            Connection conn = DriverManager.getConnection(url, username, password);
            Statement s = conn.createStatement();
            String query = "SELECT COUNT(supplierID) FROM supplier";
            ResultSet rs = s.executeQuery(query);
            if (rs.next()) {
                count = rs.getInt(1);
            }
            rs.close();
            s.close();
            conn.close();
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | SQLException exc) {
            System.out.println(exc);
        }
        return count;
    }

    public String getSupplierName(String ID) {
        String supplierName = null;
        try {
            Class.forName(driver).newInstance();
            Connection conn = DriverManager.getConnection(url, username, password);
            Statement s = conn.createStatement();
            String query = "SELECT supplierName FROM supplier WHERE supplierID='" + ID + "'";
            ResultSet rs = s.executeQuery(query);
            if (rs.next()) {
                supplierName = rs.getString(1);
            }
            rs.close();
            s.close();
            conn.close();
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | SQLException exc) {
            System.out.println(exc);
        }
        return supplierName;
    }

    public String getItemName(String ID) {
        String itemName = null;
        try {
            Class.forName(driver).newInstance();
            Connection conn = DriverManager.getConnection(url, username, password);
            Statement s = conn.createStatement();
            String query = "SELECT item FROM inventory WHERE inventoryID='" + ID + "'";
            ResultSet rs = s.executeQuery(query);
            if (rs.next()) {
                itemName = rs.getString(1);
            }
            rs.close();
            s.close();
            conn.close();
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | SQLException exc) {
            System.out.println(exc);
        }
        return itemName;
    }

    public void showActiveEmp() {
        String columnNamesEmp[] = {"First Name", "Last Name", "Active"};
        try {
            Class.forName(driver).newInstance();
            Connection conn = DriverManager.getConnection(url, username, password);
            Statement s = conn.createStatement();
            String query = "SELECT employeeFName,employeeLName,employeeStatus FROM employee WHERE employeeStatus= 'Active' ";
            ResultSet rs = s.executeQuery(query);
            ResultSetMetaData metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();
            DefaultTableModel tableModel3 = new DefaultTableModel();
            Employee.tableEmp.setModel(tableModel3);

            for (int i = 0; i < columnCount; i++) {
                tableModel3.addColumn(columnNamesEmp[i]);
            }
            Object[] row = new Object[columnCount];

            while (rs.next()) {
                for (int i = 0; i < columnCount; i++) {
                    row[i] = rs.getObject(i + 1);
                }
                tableModel3.addRow(row);
            }
            rs.close();
            s.close();
            conn.close();
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | SQLException exc) {
        }
    }

    public String getHoursWorked(String Username) {
        String time = "00:00:00";
        try {
            Connection conn = DriverManager.getConnection(url, username, password);
            Statement s = conn.createStatement();
            String querySelect = "SELECT employeeHoursWorked FROM employee WHERE employeeFName='" + Username + "'";
            ResultSet rs = s.executeQuery(querySelect);
            while (rs.next()) {
                time = rs.getString("employeeHoursWorked");
            }

            s.close();
            conn.close();
        } catch (SQLException exp) {
        }
        return time;
    }

    public void insertEmployee(String firstName, String lastName, String empPassword, String contact, int adminRights) {
        try {
            Connection conn = DriverManager.getConnection(url, username, password);
            Statement s = conn.createStatement();
            String insertQuery = "INSERT INTO employee (employeeFName, employeeLName, employeePassword,employeeContactNumber,employeeHoursWorked,admin )"
                    + "VALUES ('" + firstName + "', '"
                    + lastName + "', '"
                    + empPassword + "', '"
                    + contact + "', '"
                    + "00:00" + "','"
                    + adminRights + "')";
            s.execute(insertQuery);
            populateEmpTable();
            logs.writeLogs("ADDED");
            s.close();
            conn.close();
        } catch (SQLException exp) {
            System.out.println(exp);
        }
    }

    public void insertInventory(String item, String category, int quantity, int limit, Double threshold) {
        try {
            Connection conn = DriverManager.getConnection(url, username, password);
            Statement s = conn.createStatement();
            String insertQuery = "INSERT INTO inventory (item,category ,qty,itemThreshold,itemLimit)"
                    + "VALUES ('" + item + "',"
                    + "'" + category + "',"
                    + "'" + quantity + "',"
                    + "'" + threshold + "', '"
                    + limit + "')";
            s.execute(insertQuery);
            logs.writeLogs("ADDED");
            s.close();
            conn.close();
        } catch (SQLException exp) {
            System.out.println(exp);
        }
    }

    public void insertRecipe(String name, String price, String vat, String directory, String category) {
        String ID = "";
        try {
            Connection conn = DriverManager.getConnection(url, username, password);
            Statement s = conn.createStatement();
            String insertQuerySup = "INSERT INTO recipe(recipeName, recipePrice,recipeVAT,recipeType,recipeImageDirectory)"
                    + "VALUES ('" + name + "','"
                    + price + "', '"
                    + vat + "', '"
                    + category + "', '"
                    + directory + "')";
            s.execute(insertQuerySup);
            String selectQuery = "SELECT * FROM recipe ORDER BY recipeID DESC LIMIT 1";
            ResultSet rs = s.executeQuery(selectQuery);

            while (rs.next()) {
                ID = rs.getString("recipeID");
            }
            logs.writeLogs("ADDED");
            s.close();
            conn.close();
        } catch (SQLException exp) {
        }
    }

    public int getRecipeID() {
        int ID = 0;
        try {
            Connection conn = DriverManager.getConnection(url, username, password);
            Statement s = conn.createStatement();
            String selectQuery = "SELECT * FROM recipe ORDER BY recipeID DESC LIMIT 1";
            ResultSet rs = s.executeQuery(selectQuery);

            while (rs.next()) {
                ID = Integer.parseInt(rs.getString("recipeID"));
            }
            s.close();
            conn.close();
        } catch (SQLException exp) {
        }
        return ID;
    }

    public int getInvetoryID(String item) {
        int ID = 0;
        try {
            Connection conn = DriverManager.getConnection(url, username, password);
            Statement s = conn.createStatement();
            String selectQuery = "SELECT inventoryID FROM inventory WHERE item='" + item + "'";
            ResultSet rs = s.executeQuery(selectQuery);

            while (rs.next()) {
                ID = Integer.parseInt(rs.getString("inventoryID"));
            }
            s.close();
            conn.close();
        } catch (SQLException exp) {
        }
        return ID;
    }

    public void insertRecipeList(String item, String qty) {
        try {
            Connection conn = DriverManager.getConnection(url, username, password);
            Statement s = conn.createStatement();
            String insertQuerySup = "INSERT INTO inventory_recipe(inventoryID, recipeID,qty)"
                    + "VALUES ('" + getInvetoryID(item) + "','"
                    + getRecipeID() + "', '"
                    + qty + "')";
            s.execute(insertQuerySup);
            logs.writeLogs("ADDED");
            s.close();
            conn.close();
        } catch (SQLException exp) {
            System.out.println(exp);
        }
    }

    public void insertSupplier(String name, String email, String number, String address) {
        try {
            Connection conn = DriverManager.getConnection(url, username, password);
            Statement s = conn.createStatement();
            String insertQuerySup = "INSERT INTO supplier(supplierName, supplierEmail,supplierNumber, supplierAddress)"
                    + "VALUES ('" + name + "','"
                    + email + "', '"
                    + number + "', '"
                    + address + "')";
            s.execute(insertQuerySup);
            logs.writeLogs("ADDED");
            s.close();
            conn.close();
        } catch (SQLException exp) {
        }
    }

    public void insertReservations(String employee, String date, String time, String customerName, int tableNum, int customerNum) {
        try {
            Connection conn = DriverManager.getConnection(url, username, password);
            Statement s = conn.createStatement();
            String insertQuerySup = "INSERT INTO reservation(employeeID, reservationDate, reservationTime, reservationCustomer,reservationTableNumber,reservationNumberPeople)"
                    + "VALUES ('" + employee + "','"
                    + date + "','"
                    + time + "','"
                    + customerName + "', '"
                    + tableNum + "', '"
                    + customerNum + "')";
            s.execute(insertQuerySup);
            JOptionPane.showMessageDialog(null, "Reservation Added");
            populateReservation();
            logs.writeLogs("ADDED");
            s.close();
            conn.close();
        } catch (SQLException exp) {
            System.out.println(exp);
        }

    }

    public void insertStockOrder(String inventoryID, String supplierID, String quantity, String date) {
        try {
            Connection conn = DriverManager.getConnection(url, username, password);
            Statement s = conn.createStatement();
            String insertQuerySup = "INSERT INTO stockorder(inventoryID, supplierID,dateOrdered,dateETA,quantity,status)"
                    + "VALUES ('" + inventoryID + "','"
                    + supplierID + "', '"
                    + clock.getCurrentDate() + "', '"
                    + date + "', '"
                    + quantity + "', 'Not Delievered')";
            s.execute(insertQuerySup);
            logs.writeLogs("ADDED");
            s.close();
            conn.close();
        } catch (SQLException exp) {
            System.out.println(exp);
        }
    }

    public void insertSales(double currentTotal, String user) {
        try {
            Connection conn = DriverManager.getConnection(url, username, password);
            Statement s = conn.createStatement();
            String insertQuerySup = "INSERT INTO sales(salesDate,totalCost)"
                    + "VALUES ('" + clock.getCurrentDate() + "','"
                    + currentTotal + "')";
            s.execute(insertQuerySup);
            String selectQuery = "SELECT * FROM sales ORDER BY salesID DESC LIMIT 1";
            ResultSet rs = s.executeQuery(selectQuery);
            String ID = "";
            while (rs.next()) {
                ID = rs.getString("salesID");
            }
            insertEmployeeSales(ID, user);
            logs.writeLogs("ADDED");
            s.close();
            conn.close();
        } catch (SQLException exp) {
            System.out.println(exp);
        }
    }

    public void insertEmployeeSales(String ID, String user) {
        try {
            Connection conn = DriverManager.getConnection(url, username, password);
            Statement s = conn.createStatement();
            String selectQuery = "SELECT employeeID FROM employee WHERE employeeFName='" + user + "'";
            ResultSet rs = s.executeQuery(selectQuery);
            String employeeID = "";
            while (rs.next()) {
                employeeID = rs.getString("employeeID");
            }

            String insertQuerySup = "INSERT INTO sales_employee(employeeID,salesID)"
                    + "VALUES ('" + employeeID + "','"
                    + ID + "')";
            s.execute(insertQuerySup);
            logs.writeLogs("ADDED");
            s.close();
            conn.close();
        } catch (SQLException exp) {
            System.out.println(exp);
        }
    }

    public void insertSpecials(double specialPrice, String recipeName) {
        try {
            Connection conn = DriverManager.getConnection(url, username, password);
            Statement s = conn.createStatement();
            String insertQuerySup = "INSERT INTO specials(specialsPrice)"
                    + "VALUES ('" + specialPrice + "')";
            s.execute(insertQuerySup);
            String selectQuery = "SELECT * FROM specials ORDER BY specialsID DESC LIMIT 1";
            ResultSet rs = s.executeQuery(selectQuery);
            String ID = null;
            while (rs.next()) {
                ID = rs.getString("specialsID");
            }
            updateRecipeSpecialsID(ID, recipeName);
            s.close();
            conn.close();
        } catch (SQLException exp) {
            System.out.println(exp);
        }
    }

    public void removeInventory(int index) {
        try {
            Connection conn = DriverManager.getConnection(url, username, password);
            Statement s = conn.createStatement();
            String query = "DELETE FROM inventory WHERE inventoryID='" + index + "'";
            s.execute(query);
            logs.writeLogs("DELETED");
            s.close();
            conn.close();
        } catch (SQLException exp) {
        }
    }

    public void removeRecipe(int index) {
        try {
            Connection conn = DriverManager.getConnection(url, username, password);
            Statement s = conn.createStatement();
            String query = "DELETE FROM recipe WHERE recipeID='" + index + "'";
            String query1 = "DELETE FROM inventory_recipe WHERE recipeID='" + index + "'";
            s.execute(query1);
            s.execute(query);
            removeRecipeList(index);
            logs.writeLogs("DELETED");
            s.close();
            conn.close();
        } catch (SQLException exp) {
        }
    }

    public void removeRecipeList(int index) {
        try {
            Connection conn = DriverManager.getConnection(url, username, password);
            Statement s = conn.createStatement();
            String query = "DELETE FROM inventory_recipe WHERE recipeID='" + index + "'";
            s.execute(query);
            logs.writeLogs("DELETED");
            s.close();
            conn.close();
        } catch (SQLException exp) {
        }
    }

    public void removeRecipeList(int index, int ID) {
        try {
            Connection conn = DriverManager.getConnection(url, username, password);
            Statement s = conn.createStatement();
            String query = "DELETE FROM inventory_recipe WHERE recipeID='" + index + "'AND inventoryID='" + ID + "'";
            s.execute(query);

            logs.writeLogs("DELETED");
            s.close();
            conn.close();
        } catch (SQLException exp) {
        }
    }

    public void removeSupplier(int index) {
        try {
            Connection conn = DriverManager.getConnection(url, username, password);
            Statement s = conn.createStatement();
            String query = "DELETE FROM supplier WHERE supplierID='" + index + "'";
            s.execute(query);
            logs.writeLogs("DELETED");
            s.close();
            conn.close();
        } catch (SQLException exp) {
        }
    }

    public void removeEmployee(int index) {
        try {
            Connection conn = DriverManager.getConnection(url, username, password);
            Statement s = conn.createStatement();
            String query = "DELETE FROM employee WHERE employeeID='" + index + "'";
            s.execute(query);
            populateEmpTable();
            logs.writeLogs("DELETED");
            s.close();
            conn.close();
        } catch (SQLException exp) {
        }
    }

    public void updateSpecials(String specialsID) {
        try {
            Connection conn = DriverManager.getConnection(url, username, password);
            Statement s = conn.createStatement();
            String query = "UPDATE specials set status= '1' WHERE specialsID='" + specialsID + "'";
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.executeUpdate();
            getSpecialDetails();
            s.close();
            conn.close();
        } catch (SQLException exp) {
            System.out.println(exp);
        }
    }

    public void updateHours(String Username, int i) {
        try {
            Connection conn = DriverManager.getConnection(url, username, password);
            Statement s = conn.createStatement();
            String queryUpdate = "UPDATE employee set employeeHoursWorked='" + clock.calculateHours(i, getHoursWorked(Username))
                    + "' WHERE employeeFName='" + Username + "'";
            PreparedStatement preparedStmt = conn.prepareStatement(queryUpdate);
            preparedStmt.executeUpdate();
            logs.writeLogs("UPDATED");
            s.close();
            conn.close();
        } catch (SQLException exp) {
        }
    }

    public void updateEmployeeStatusIn(String Username) {
        try {
            Connection conn = DriverManager.getConnection(url, username, password);
            Statement s = conn.createStatement();
            String query = "UPDATE employee set employeeStatus= 'Active' WHERE employeeFName='" + Username + "'";
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.executeUpdate();
            s.close();
            conn.close();
        } catch (SQLException exp) {
        }
    }

    public void updateEmployeeStatusOut(String Username) {
        try {
            Connection conn = DriverManager.getConnection(url, username, password);
            Statement s = conn.createStatement();
            String query = "UPDATE employee set employeeStatus= 'Deactive' WHERE employeeFName='" + Username + "'";
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.executeUpdate();
            s.close();
            conn.close();
        } catch (SQLException exp) {
        }
    }

    //Updates Inventory table with "Update" button
    public void updateInventory(Object ID, Object item, Object qty, Object itemT, Object itemL) {
        try {
            //connect to db
            Connection conn = DriverManager.getConnection(url, username, password);
            Statement s = conn.createStatement();
            //update query
            String query = "UPDATE inventory set item='" + item + "' '"
                    + "' ,qty='" + qty + "' '"
                    + "' ,itemThreshold='" + itemT + "' '"
                    + "' ,itemLimit='" + itemL + "' WHERE inventoryID='" + ID + "'";
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.executeUpdate();
            s.close();
            conn.close();
        } catch (SQLException exp) {
            //prints SQL Exception
            System.out.println("Error: " + exp);
        }
    }

    //Updates recipe table with "Update" button
    public void updateRecipe(Object ID, Object name, Object price, Object vat, Object type) {
        try {
            //connect to db
            Connection conn = DriverManager.getConnection(url, username, password);
            Statement s = conn.createStatement();
            //update query
            String query = "UPDATE recipe set recipeName='" + name + "' '"
                    + "' ,recipePrice='" + price + "' '"
                    + "' ,recipeVAT='" + vat + "' '"
                    + "' ,recipeType='" + type + "' WHERE recipeID='" + ID + "'";
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.executeUpdate();
            s.close();
            conn.close();
        } catch (SQLException exp) {
            //prints SQL Exception
            System.out.println("Error: " + exp);
        }
    }

    public void updateInventoryQty(String item, String qty, String operator) {
        int ID = getInvetoryID(item);
        try {
            Connection conn = DriverManager.getConnection(url, username, password);
            Statement s = conn.createStatement();
            String query = "UPDATE inventory set qty=qty" + operator + "'" + qty + "' "
                    + "WHERE inventoryID='" + ID + "'";
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            System.out.println("ran");
            preparedStmt.executeUpdate();
            s.close();
            conn.close();
        } catch (SQLException exp) {
            System.out.println(exp);
        }
    }

    public void updateRecipeList(String iventoryID, String recipeID, String qty) {
        try {
            Connection conn = DriverManager.getConnection(url, username, password);
            Statement s = conn.createStatement();
            String query = "UPDATE inventory_recipe set qty='" + qty + "' "
                    + "WHERE  inventoryID='" + iventoryID + "'AND recipeID='" + recipeID + "'";
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.executeUpdate();
            s.close();
            conn.close();
        } catch (SQLException exp) {
            System.out.println(exp);
        }
    }
    //Updates supplier table with "Update" button

    public void updateSupplier(Object ID, Object name, Object email, Object num, Object address) {
        try {
            //connect to db
            Connection conn = DriverManager.getConnection(url, username, password);
            Statement s = conn.createStatement();
            //update query
            String query = "UPDATE supplier set supplierName='" + name + "' '"
                    + "' ,supplierEmail='" + email + "' '"
                    + "' ,supplierNumber='" + num + "' '"
                    + "' ,supplierAddress='" + address + "' WHERE supplierID='" + ID + "'";
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.executeUpdate();
            s.close();
            conn.close();
        } catch (SQLException exp) {
            //prints SQL Exception
            System.out.println("Error: " + exp);
        }
    }

    public void updateEmployee(String user, String newPassword) {
        try {
            Connection conn = DriverManager.getConnection(url, username, password);
            Statement s = conn.createStatement();
            String updateQuery = "UPDATE employee SET employeePassword='" + newPassword + "' WHERE employeeFName ='" + user + "'";
            s.execute(updateQuery);
            PreparedStatement preparedStmt = conn.prepareStatement(updateQuery);
            preparedStmt.executeUpdate();
            s.close();
            conn.close();
        } catch (SQLException exp) {
            System.out.println(exp);
        }
    }

    public void updateRecipeSpecialsID(String specialsID, String recipeName) {
        try {
            Connection conn = DriverManager.getConnection(url, username, password);
            Statement s = conn.createStatement();
            String updateQuery = "UPDATE recipe SET specialsID='" + specialsID + "' WHERE recipeName ='" + recipeName + "'";
            s.execute(updateQuery);
            PreparedStatement preparedStmt = conn.prepareStatement(updateQuery);
            preparedStmt.executeUpdate();
            s.close();
            conn.close();
        } catch (SQLException exp) {
            System.out.println(exp);
        }
    }

    public void updateOrderCount(String order) {
        System.out.println(order);
        try {
            Connection conn = DriverManager.getConnection(url, username, password);
            Statement s = conn.createStatement();
            String updateQuery = "UPDATE recipe SET recipeCount=(recipeCount+1) WHERE recipeName ='" + order + "'";
            s.execute(updateQuery);
            PreparedStatement preparedStmt = conn.prepareStatement(updateQuery);
            preparedStmt.executeUpdate();
            s.close();
            conn.close();
        } catch (SQLException exp) {
            System.out.println(exp);
        }
    }

    public void updateConfirmOrder(String orderID) {
        try {
            Connection conn = DriverManager.getConnection(url, username, password);
            Statement s = conn.createStatement();
            String updateQuery = "UPDATE stockorder SET status='Delievered' WHERE stockOrderID ='" + orderID + "'";
            s.execute(updateQuery);
            PreparedStatement preparedStmt = conn.prepareStatement(updateQuery);
            preparedStmt.executeUpdate();
            s.close();
            conn.close();
        } catch (SQLException exp) {
            System.out.println(exp);
        }
    }

    public boolean login(String userName, String passWord) {
        ArrayList userPasswordAL = new ArrayList();
        ArrayList<String> userNameAL = new ArrayList();
        boolean login = false;
        try {
            Class.forName(driver).newInstance();
            Connection conn = DriverManager.getConnection(url, username, password);
            Statement s = conn.createStatement();
            String query = "SELECT employeeFName,employeePassword FROM employee";
            ResultSet rs = s.executeQuery(query);
            while (rs.next()) {
                userNameAL.add(rs.getString("employeeFName"));
                userPasswordAL.add(rs.getString("employeePassword"));
            }
            for (int i = 0; i < userPasswordAL.size(); i++) {
                if (userName.equals(userNameAL.get(i)) && passWord.equals(userPasswordAL.get(i))) {
                    i = userNameAL.size();
                    login = true;
                } else {
                    login = false;
                }
            }

        } catch (ClassNotFoundException | SQLException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(dbManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return login;
    }

    public void backup() {
        try {
            String executeCmd = ".\\src\\database\\mysqldump.exe"
                    + " -u " + username + " -p" + password + " resturantdb  -r " + location;
            Process runtimeProcess = Runtime.getRuntime().exec(executeCmd);
            int processComplete = runtimeProcess.waitFor();
            if (processComplete == 0) {
                JOptionPane.showMessageDialog(null, "Backup Complete");
            } else {
                JOptionPane.showMessageDialog(null, "Backup Failed");
            }

        } catch (IOException | InterruptedException ex) {
            JOptionPane.showMessageDialog(null, "Error at Backuprestore" + ex.getMessage());
        }
    }

    public void restore() {
        try {
            String[] executeCmd = new String[]{".\\src\\database\\mysql.exe",
                "--user=" + username, "--password=" + password, "resturantdb",
                "-e", "source " + location};

            Process runtimeProcess = Runtime.getRuntime().exec(executeCmd);
            int processComplete = runtimeProcess.waitFor();

            if (processComplete == 0) {
                JOptionPane.showMessageDialog(null, "Database Successfully Restored");
            } else {
                JOptionPane.showMessageDialog(null, "Restore Failed");
            }

        } catch (IOException | InterruptedException | HeadlessException ex) {
            JOptionPane.showMessageDialog(null, "Error at Restoredbfromsql" + ex.getMessage());
        }
    }
}
