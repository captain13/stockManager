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
    private String time;

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

    public Object[][] getRecipeData() {
        Object[][] rowData = null;
        try {
            Class.forName(driver).newInstance();
            Connection conn = DriverManager.getConnection(url, username, password);
            Statement s = conn.createStatement();
            String query = "SELECT recipeID, recipeName,recipeType, recipePrice, recipeVAT,recipeImageDirectory, recipeCount FROM recipe";
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
            String query = "SELECT stockOrderID, inventory.item, supplier.supplierName,dateOrdered, dateETA, quantity, status, totalCost "
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
                rowData[i][7] = rs.getObject(8);
                i++;
            }
            rs.close();
            s.close();
            conn.close();
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | SQLException exc) {
        }
        return rowData;
    }

    public Object[][] getExpensesData() {
        Object[][] rowData = null;
        try {
            Class.forName(driver).newInstance();
            Connection conn = DriverManager.getConnection(url, username, password);
            Statement s = conn.createStatement();
            String query = "SELECT expensesID,expensesType,date, expensesAmount FROM expenses "
                    + "UNION ALL SELECT '','','TOTAL', SUM(expensesAmount) FROM expenses";
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
                rowData[i][3] = rs.getString(4);
                i++;
            }
            rs.close();
            s.close();
            conn.close();
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | SQLException exc) {
            System.out.println(exc);
        }
        return rowData;
    }

    public Object[][] getEmployeeData() {
        Object[][] rowData = null;
        try {
            Class.forName(driver).newInstance();
            Connection conn = DriverManager.getConnection(url, username, password);
            Statement s = conn.createStatement();
            String query = "SELECT employeeID,employeeFName,employeeLName,employeeContactNumber,employeeHoursWorked, admin FROM employee";
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

    public Object[][] getReservationData() {
        Object[][] rowData = null;
        try {
            Class.forName(driver).newInstance();
            Connection conn = DriverManager.getConnection(url, username, password);
            Statement s = conn.createStatement();
            String query = "SELECT employeeID,reservationDate,reservationTime,reservationCustomer,reservationTableNumber,reservationNumberPeople FROM reservation";
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

    public Object[][] getSalesData(String type) {
        Object[][] rowData = null;
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
                i++;
            }
            rs.close();
            s.close();
            conn.close();
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | SQLException exc) {
        }
        return rowData;
    }

    public Object[][] getEmployeeSales() {
        Object[][] rowData = null;
        try {
            Class.forName(driver).newInstance();
            Connection conn = DriverManager.getConnection(url, username, password);
            Statement s = conn.createStatement();
            String query = "SELECT sales_employee.salesID, employee.employeeFName, sales_employee.employeeID FROM sales_employee,employee WHERE employee.employeeID=sales_employee.employeeID ";
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
                i++;
            }
            rs.close();
            s.close();
            conn.close();
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | SQLException exc) {
        }
        return rowData;
    }

    public Object[][] getSpecialData() {
        Object[][] rowData = null;
        try {
            Class.forName(driver).newInstance();
            Connection conn = DriverManager.getConnection(url, username, password);
            Statement s = conn.createStatement();

            String query = "SELECT recipe.specialsID, recipeName,recipeType, specialsPrice,recipeImageDirectory, status "
                    + "FROM recipe,specials "
                    + "WHERE recipe.specialsID=specials.specialsID";
            ResultSet rs = s.executeQuery(query);
            ResultSetMetaData metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();
            int rowCount = 0;
            while (rs.next()) {
                rowCount++;
            }
            rowData = new Object[rowCount][columnCount];
            rs = s.executeQuery(query);
            int i = 0;
            while (rs.next()) {
                rowData[i][0] = rs.getObject(1);
                rowData[i][1] = rs.getObject(2);
                rowData[i][2] = rs.getObject(3);
                rowData[i][3] = rs.getObject(4);
                rowData[i][4] = rs.getObject(6);
                rowData[i][5] = rs.getObject(5);
                i++;
            }

            rs.close();
            s.close();
            conn.close();
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | SQLException exc) {
            System.out.println(exc);
        }
        return rowData;
    }

    public String[] getIngredients() {
        int count = 0;
        String ingredients[] = null;
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
            ingredients = new String[count];
            int i = 0;
            while (rs.next()) {
                ingredients[i] = rs.getString("item");
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

    public double getDayExpenses() {
        double totalExpenses = 0;
        String date = clock.getCurrentDate();
        try {
            Class.forName(driver).newInstance();
            Connection conn = DriverManager.getConnection(url, username, password);
            Statement s = conn.createStatement();
            String query = "SELECT SUM(expensesAmount) FROM expenses WHERE date='" + date + "' ";
            ResultSet rs = s.executeQuery(query);
            if (rs.next()) {
                totalExpenses = rs.getDouble(1);
            }

            rs.close();
            s.close();
            conn.close();
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | SQLException exc) {
            System.out.println(exc);
        }
        return totalExpenses;
    }
    
    public double getDaySales() {
        double totalSales = 0;
        String date = clock.getCurrentDate();
        try {
            Class.forName(driver).newInstance();
            Connection conn = DriverManager.getConnection(url, username, password);
            Statement s = conn.createStatement();
            String query = "SELECT SUM(totalCost) FROM sales WHERE salesDate='" + date + "' ";
            ResultSet rs = s.executeQuery(query);
            if (rs.next()) {
                totalSales = rs.getDouble(1);
            }

            rs.close();
            s.close();
            conn.close();
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | SQLException exc) {
            System.out.println(exc);
        }
        return totalSales;
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

    //delete later
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
            EmployeeForm.tableEmp.setModel(tableModel3);

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

    public String calcHoursWorked(String Username) {
        String time = "0";
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
            System.out.println(exp);
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
            logs.writeLogs("ADDED", "Employee");
            s.close();
            conn.close();
        } catch (SQLException exp) {
            System.out.println(exp);
        }
    }

    public void insertExpenses(String type, double amount, String date) {
        try {
            Connection conn = DriverManager.getConnection(url, username, password);
            Statement s = conn.createStatement();
            String insertQuery = "INSERT INTO expenses (expensesType, expensesAmount,date)"
                    + "VALUES ('" + type + "', '"
                    + amount + "','"
                    + date + "')";
            s.execute(insertQuery);
            logs.writeLogs("ADDED", "Expenses");
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
            logs.writeLogs("ADDED", "Inventory");
            s.close();
            conn.close();
        } catch (SQLException exp) {
            System.out.println(exp);
        }
    }

    public void insertRecipe(String recipe, double price, double vat, String imageDirectory, String category) {
        try {
            Connection conn = DriverManager.getConnection(url, username, password);
            Statement s = conn.createStatement();
            String insertQuery = "INSERT INTO recipe(recipeName, recipePrice, recipeVAT,recipeType, recipeImageDirectory, recipeCount)"
                    + "VALUES ('" + recipe + "','"
                    + price + "', '"
                    + vat + "', '"
                    + category + "', '"
                    + imageDirectory + "','0')";
            s.execute(insertQuery);
            logs.writeLogs("ADDED", "Recipe");
            s.close();
            conn.close();
        } catch (SQLException exp) {
        }
    }

    public int getRecipeID(String item) {
        int ID = 0;
        try {
            Connection conn = DriverManager.getConnection(url, username, password);
            Statement s = conn.createStatement();
            String selectQuery = "SELECT * FROM recipe WHERE recipeName='" + item + "'";
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

    public int getRecipeCost(String item) {
        int cost = 0;
        try {
            Connection conn = DriverManager.getConnection(url, username, password);
            Statement s = conn.createStatement();
            String selectQuery = "SELECT * FROM recipe WHERE recipeName='" + item + "'";
            ResultSet rs = s.executeQuery(selectQuery);

            while (rs.next()) {
                cost = Integer.parseInt(rs.getString("cost"));
            }
            s.close();
            conn.close();
        } catch (SQLException exp) {
        }
        return cost;
    }

    public int getSalesID() {
        int ID = 0;
        try {
            Connection conn = DriverManager.getConnection(url, username, password);
            Statement s = conn.createStatement();
            String selectQuery = "SELECT * FROM sales ORDER BY salesID DESC LIMIT 1";
            ResultSet rs = s.executeQuery(selectQuery);

            while (rs.next()) {
                ID = Integer.parseInt(rs.getString("salesID"));
                System.out.println(ID);
            }
            s.close();
            conn.close();
        } catch (SQLException exp) {
        }
        return ID;
    }

    public void insertReceipt(int recipeID, String cost) {
        int receiptID = 0;
        try {
            Connection conn = DriverManager.getConnection(url, username, password);
            Statement s = conn.createStatement();
//            String selectQuery = "SELECT * FROM receipt ORDER BY recipeNumber DESC LIMIT 1";
//            ResultSet rs = s.executeQuery(selectQuery);
//            while (rs.next()) {
//                receiptID = rs.getString("receiptID");
//            }
            receiptID += 1;

            String insertQuery = "INSERT INTO receipt( recipeID, orderQuantity, salesID, date, time , cost)"
                    + "VALUES ('"
                    + recipeID + "', '"
                    + "1" + "', '"
                    + getSalesID() + "', '"
                    + clock.getCurrentDate() + "', '"
                    + clock.getCurrentTimeStamp() + "', '"
                    + cost + "')";
            s.execute(insertQuery);

            logs.writeLogs("ADDED", "receipt");
            s.close();
            conn.close();
        } catch (SQLException exp) {
            System.out.println(exp);
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
            logs.writeLogs("ADDED", "Inventory_recipe");
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
            logs.writeLogs("ADDED", "Supplier");
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
            logs.writeLogs("ADDED", "Reservations");
            s.close();
            conn.close();
        } catch (SQLException exp) {
            System.out.println(exp);
        }

    }

    public void insertStockOrder(String inventoryID, String supplierID, double quantity, String date, double cost) {
        try {
            Connection conn = DriverManager.getConnection(url, username, password);
            Statement s = conn.createStatement();
            String insertQuerySup = "INSERT INTO stockorder(inventoryID, supplierID,dateOrdered,dateETA,quantity,status,totalCost)"
                    + "VALUES ('" + inventoryID + "','"
                    + supplierID + "', '"
                    + clock.getCurrentDate() + "', '"
                    + date + "', '"
                    + quantity + "', 'Not Delievered', '"
                    + cost + "')";
            s.execute(insertQuerySup);
            logs.writeLogs("ADDED", "StockOrder");
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
            logs.writeLogs("ADDED", "Sales");
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
            logs.writeLogs("ADDED", "EmployeeSales");
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
            logs.writeLogs("ADDED", "Specials");
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
            logs.writeLogs("DELETED", "Inventory");
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
            logs.writeLogs("DELETED", "Recipe");
            s.close();
            conn.close();
        } catch (SQLException exp) {
        }
    }
//test to see duplicates

    public void removeRecipeList(int index) {
        try {
            Connection conn = DriverManager.getConnection(url, username, password);
            Statement s = conn.createStatement();
            String query = "DELETE FROM inventory_recipe WHERE recipeID='" + index + "'";
            s.execute(query);
            logs.writeLogs("DELETED", "Inventory_recipe");
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
            logs.writeLogs("DELETED", "Inventory_recipe");
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
            logs.writeLogs("DELETED", "Supplier");
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
            logs.writeLogs("DELETED", "Employee");
            s.close();
            conn.close();
        } catch (SQLException exp) {
        }
    }

    public void updateSpecials(String specialsID, int status) {
        try {
            Connection conn = DriverManager.getConnection(url, username, password);
            Statement s = conn.createStatement();
            String query = "UPDATE specials set status= '" + status + "' WHERE specialsID='" + specialsID + "'";
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.executeUpdate();
            getSpecialData();
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
            logs.writeLogs("UPDATED", "Employee");
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
            logs.writeLogs("UPDATED", "Inventory");
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
            logs.writeLogs("UPDATED", "Recipe");
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
            preparedStmt.executeUpdate();
            logs.writeLogs("UPDATED", "Inventory");
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
            logs.writeLogs("UPDATED", "Inventory_recipe");
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
            logs.writeLogs("UPDATED", "Supplier");
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
            logs.writeLogs("UPDATED", "Employee");
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
        System.out.println("ran");
        try {
            Connection conn = DriverManager.getConnection(url, username, password);
            Statement s = conn.createStatement();
            String updateQuery = "UPDATE recipe SET recipeCount=recipeCount+1 WHERE recipeName ='" + order + "'";
            s.execute(updateQuery);
            PreparedStatement preparedStmt = conn.prepareStatement(updateQuery);
            preparedStmt.executeUpdate();
            logs.writeLogs("UPDATED", "recipe");
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
