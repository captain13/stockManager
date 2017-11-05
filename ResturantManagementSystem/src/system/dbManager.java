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
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;
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
    static String username;
    static String password;
    String driver = "com.mysql.jdbc.Driver";
    String currentUsersHomeDir = System.getProperty("user.home");
    String location = ".\\src\\database\\resturantDB_bk.sql";

    public void dbValidation(String Username, String Password) {
        connect(Username, Password);
        String urlVal = "jdbc:mysql://localhost:3306";
        try (Connection conn = DriverManager.getConnection(urlVal, username, password); Statement s = conn.createStatement()) {
            Class.forName(driver).newInstance();
            s.executeUpdate("CREATE SCHEMA IF NOT EXISTS `resturantDB`;");
            s.executeUpdate("CREATE DATABASE IF NOT EXISTS `resturantDB`;");
            s.executeUpdate("GRANT ALL PRIVILEGES ON resturantDB.* TO 'root'@'localhost';");
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
            System.out.println("db Validation method" + ex);
        }
    }

    public void connect(String Username, String Password) {
        dbManager.username = Username;
        dbManager.password = Password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public Object[][] getInventoryData() {
        Object[][] rowData = null;
        try (Connection conn = DriverManager.getConnection(url, username, password); Statement s = conn.createStatement()) {
            Class.forName(driver).newInstance();
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
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | SQLException ex) {
            System.out.println("get inventory method" + ex);
        }
        return rowData;
    }

    public String getRecipeName(int id) {
        String name = null;
        try (Connection conn = DriverManager.getConnection(url, username, password); Statement s = conn.createStatement()) {
            Class.forName(driver).newInstance();

            String query = "SELECT recipeName FROM recipe WHERE recipeID ='" + id + "' ";
            try (ResultSet rs = s.executeQuery(query)) {
                while (rs.next()) {
                    name = rs.getString(1);
                }
            }
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | SQLException exc) {
            System.out.println("recipe name method" + exc);
        }
        return name;
    }

    public Object[][] getRecipeData() {
        Object[][] rowData = null;
        try (Connection conn = DriverManager.getConnection(url, username, password); Statement s = conn.createStatement()) {
            Class.forName(driver).newInstance();
            String query = "SELECT recipeID, recipeName,recipeType, recipePrice, recipeVAT,recipeImageDirectory, recipeCount, specialsID FROM recipe";
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
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | SQLException exc) {
        }
        return rowData;
    }

    public Object[][] getRecipeListData() {
        Object[][] rowData = null;
        try (Connection conn = DriverManager.getConnection(url, username, password); Statement s = conn.createStatement()) {
            Class.forName(driver).newInstance();
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
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | SQLException exc) {
        }
        return rowData;
    }

    public Object[][] getSuppleirData() {
        Object[][] rowData = null;
        try (Connection conn = DriverManager.getConnection(url, username, password); Statement s = conn.createStatement()) {
            Class.forName(driver).newInstance();
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
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | SQLException exc) {
        }
        return rowData;
    }

    public Object[][] getOrderData() {
        Object[][] rowData = null;
        try (Connection conn = DriverManager.getConnection(url, username, password); Statement s = conn.createStatement()) {
            Class.forName(driver).newInstance();

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
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | SQLException exc) {
        }
        return rowData;
    }

    public Object[][] getExpensesData() {
        Object[][] rowData = null;
        try (Connection conn = DriverManager.getConnection(url, username, password); Statement s = conn.createStatement()) {
            Class.forName(driver).newInstance();

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
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | SQLException exc) {
            System.out.println(exc);
        }
        return rowData;
    }

    public Object[][] getEmployeeData() {
        Object[][] rowData = null;
        try (Connection conn = DriverManager.getConnection(url, username, password); Statement s = conn.createStatement()) {
            Class.forName(driver).newInstance();

            String query = "SELECT employeeID,employeeFName,employeeLName,employeeContactNumber,employeeHoursWorked, admin, employeePassword, employeeStatus "
                    + "FROM employee WHERE employeeID >1 ";
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
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | SQLException exc) {
            System.out.println(exc);
        }
        return rowData;
    }

    public Object[][] getReservationData() {
        Object[][] rowData = null;
        try (Connection conn = DriverManager.getConnection(url, username, password); Statement s = conn.createStatement()) {
            Class.forName(driver).newInstance();

            String query = "SELECT employeeID,reservationTableNumber,reservationDate,reservationTime,"
                    + "reservationCustomer, contactNumber,"
                    + "reservationNumberPeople FROM reservation";
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
        try (Connection conn = DriverManager.getConnection(url, username, password); Statement s = conn.createStatement()) {
            Class.forName(driver).newInstance();

            if ("ALL".equals(type)) {
                query = "SELECT salesID,salesDate,totalCost FROM sales "
                        + "UNION ALL SELECT '','TOTAL', SUM(totalCost) FROM sales";
            }
            if ("DAY".equals(type)) {
                query = "SELECT salesID,salesDate,totalCost FROM sales WHERE salesDate='" + date + "' "
                        + "UNION ALL SELECT '','TOTAL', SUM(totalCost) FROM sales "
                        + "WHERE salesDate='" + date + "'";
            }
            if ("MONTH".equals(type)) {
                query = "SELECT salesID,salesDate,totalCost FROM sales WHERE MONTH(salesDate)='" + month + "' "
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
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | SQLException exc) {
            System.out.println(exc);
        }
        return rowData;
    }

    public Object[][] getEmployeeSales() {
        Object[][] rowData = null;
        try (Connection conn = DriverManager.getConnection(url, username, password); Statement s = conn.createStatement()) {
            Class.forName(driver).newInstance();

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
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | SQLException exc) {
        }
        return rowData;
    }

    public Object[][] getSpecialData() {
        Object[][] rowData = null;
        try (Connection conn = DriverManager.getConnection(url, username, password); Statement s = conn.createStatement()) {
            Class.forName(driver).newInstance();

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
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | SQLException exc) {
            System.out.println(exc);
        }
        return rowData;
    }

    public String[] getIngredients() {
        String ingredients[] = null;
        try (Connection conn = DriverManager.getConnection(url, username, password); Statement s = conn.createStatement()) {
            Class.forName(driver).newInstance();

            String query = "SELECT item FROM inventory ";
            ResultSet rs = s.executeQuery(query);
            ResultSetMetaData metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();
            int rowCount = 0;
            while (rs.next()) {
                rowCount++;
            }
            rs = s.executeQuery(query);
            ingredients = new String[rowCount];
            int i = 0;
            while (rs.next()) {
                ingredients[i] = rs.getString("item");
                i++;
            }

            rs.close();
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | SQLException exc) {
            System.out.println(exc);
        }
        return ingredients;
    }

    public double getDayExpenses() {
        double totalExpenses = 0;
        String date = clock.getCurrentDate();
        try (Connection conn = DriverManager.getConnection(url, username, password); Statement s = conn.createStatement()) {
            Class.forName(driver).newInstance();

            String query = "SELECT SUM(expensesAmount) FROM expenses WHERE date='" + date + "' ";
            try (ResultSet rs = s.executeQuery(query)) {
                if (rs.next()) {
                    totalExpenses = rs.getDouble(1);
                }
            }
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | SQLException exc) {
            System.out.println(exc);
        }
        return totalExpenses;
    }

    public double getDayWageExpenses() {
        double totalExpenses = 0;
        String date = clock.getCurrentDate();
        try (Connection conn = DriverManager.getConnection(url, username, password); Statement s = conn.createStatement()) {
            Class.forName(driver).newInstance();

            String query = "SELECT SUM(expensesAmount) FROM expenses WHERE date='" + date + "' AND expensesType='Wage'";
            try (ResultSet rs = s.executeQuery(query)) {
                if (rs.next()) {
                    totalExpenses = rs.getDouble(1);
                }
            }
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | SQLException exc) {
            System.out.println(exc);
        }
        return totalExpenses;
    }

    public double getDaySaleExpenses() {
        double totalExpenses = 0;
        String date = clock.getCurrentDate();
        try (Connection conn = DriverManager.getConnection(url, username, password); Statement s = conn.createStatement()) {
            Class.forName(driver).newInstance();

            String query = "SELECT SUM(expensesAmount) FROM expenses WHERE date='" + date + "' AND expensesType='Stock Order' ";
            try (ResultSet rs = s.executeQuery(query)) {
                if (rs.next()) {
                    totalExpenses = rs.getDouble(1);
                }
            }
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | SQLException exc) {
            System.out.println(exc);
        }
        return totalExpenses;
    }

    public double getDaySales() {
        double totalSales = 0;
        String date = clock.getCurrentDate();
        try (Connection conn = DriverManager.getConnection(url, username, password); Statement s = conn.createStatement()) {
            Class.forName(driver).newInstance();

            String query = "SELECT SUM(totalCost) FROM sales WHERE salesDate='" + date + "' ";
            try (ResultSet rs = s.executeQuery(query)) {
                if (rs.next()) {
                    totalSales = rs.getDouble(1);
                }
            }
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | SQLException exc) {
            System.out.println(exc);
        }
        return totalSales;
    }

    public String getItemName(String ID) {
        String itemName = null;
        try (Connection conn = DriverManager.getConnection(url, username, password); Statement s = conn.createStatement()) {
            Class.forName(driver).newInstance();

            String query = "SELECT item FROM inventory WHERE inventoryID='" + ID + "'";
            try (ResultSet rs = s.executeQuery(query)) {
                if (rs.next()) {
                    itemName = rs.getString(1);
                }
            }
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | SQLException exc) {
            System.out.println(exc);
        }
        return itemName;
    }

    public String getSupplierName(String ID) {
        String supplierName = null;
        try (Connection conn = DriverManager.getConnection(url, username, password); Statement s = conn.createStatement()) {
            Class.forName(driver).newInstance();
            String query = "SELECT supplierName FROM supplier WHERE supplierID='" + ID + "'";
            try (ResultSet rs = s.executeQuery(query)) {
                if (rs.next()) {
                    supplierName = rs.getString(1);
                }
            }
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | SQLException exc) {
            System.out.println(exc);
        }
        return supplierName;
    }

    public Object[][] getEmpSales(int emp) {
        Object[][] sales = null;
        try (Connection conn = DriverManager.getConnection(url, username, password); Statement s = conn.createStatement()) {
            String query = "SELECT receipt.date receipt.time FROM ((receipt INNER JOIN sales_employee ON receipt.salesID = sales_employee.salesID) INNER JOIN employee ON sales_employee.employeeID = '" + emp + "')";
            ResultSet rs = s.executeQuery(query);
            ResultSetMetaData metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();
            int rowCount = 0;
            while (rs.next()) {
                rowCount++;
            }
            rs = s.executeQuery(query);
            sales = new Object[rowCount][columnCount];
            int i = 0;
            while (rs.next()) {
                sales[i][0] = rs.getString("date");
                sales[i][1] = rs.getString("time");
                i++;
            }

        } catch (SQLException e) {

        }
        return sales;
    }

    public int getEmployeeID(String Username) {
        int ID = 0;
        try (Connection conn = DriverManager.getConnection(url, username, password); Statement s = conn.createStatement()) {
            String query = "SELECT employeeID FROM employee WHERE Username= '" + getEmployeeUsername() + "'";
            ResultSet rs = s.executeQuery(query);

            ID = rs.getInt(1);

        } catch (SQLException e) {

        }
        return ID;
    }

    public Object[][] getReceiptData(String empID) {
        Object[][] row = null;
        try (Connection conn = DriverManager.getConnection(url, username, password); Statement s = conn.createStatement()) {
            String query = "SELECT receipt.date, receipt.time FROM ((receipt INNER JOIN sales_employee ON receipt.salesID = sales_employee.salesID) INNER JOIN employee ON sales_employee.employeeID = '" + empID + "')";
            ResultSet rs = s.executeQuery(query);
            ResultSetMetaData metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();
            int rowCount = 0;
            while (rs.next()) {
                rowCount++;
            }
            rs = s.executeQuery(query);
            row = new Object[rowCount][columnCount];
            int i = 0;
            while (rs.next()) {
//                row[i][0] = rs.getObject(1);
//                row[i][1] = rs.getObject(2);
//                row[i][2] = rs.getObject(3);
//                row[i][3] = rs.getObject(4);
                row[i][0] = rs.getString("receipt.date");
                row[i][1] = rs.getString("receipt.time");
//                row[i][6] = rs.getObject(7);
                i++;

            }

        } catch (SQLException e) {

        }
        return row;
    }

    public Object[][] getReprintReceipt(String date, String time) {
        Object[][] rowData = null;
        try (Connection conn = DriverManager.getConnection(url, username, password); Statement s = conn.createStatement()) {

            String query = "SELECT * FROM receipt WHERE date = '" + date + "' AND time = '" + time + "'";
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
        } catch (SQLException exc) {

        }
        return rowData;
    }

//delete later
    public void showActiveEmp() {
        String columnNamesEmp[] = {"First Name", "Last Name", "Active"};
        try (Connection conn = DriverManager.getConnection(url, username, password); Statement s = conn.createStatement()) {
            Class.forName(driver).newInstance();

            String query = "SELECT employeeFName,employeeLName,employeeStatus FROM employee WHERE employeeStatus= 'Active' ";
            try (ResultSet rs = s.executeQuery(query)) {
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
            }
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | SQLException exc) {
        }
    }

    public String getHoursWorked(String Username) {
        String time = "00hrs00";
        try (Connection conn = DriverManager.getConnection(url, username, password); Statement s = conn.createStatement()) {
            String querySelect = "SELECT employeeHoursWorked FROM employee WHERE employeeFName='" + Username + "'";
            ResultSet rs = s.executeQuery(querySelect);
            while (rs.next()) {
                time = rs.getString("employeeHoursWorked");
            }
        } catch (SQLException exp) {
        }
        return time;
    }

    public boolean getAdminCount() {
        int count = 0;
        try (Connection conn = DriverManager.getConnection(url, username, password); Statement s = conn.createStatement()) {
            String querySelect = "SELECT COUNT(admin) FROM employee WHERE admin=1";
            ResultSet rs = s.executeQuery(querySelect);
            while (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (SQLException exp) {
        }
        return count != 0;
    }

    public String getEmployeeUsername() {
        String Username = "";

        try (Connection conn = DriverManager.getConnection(url, username, password); Statement s = conn.createStatement()) {
            String selectQuery = "SELECT employeeID FROM employee'";
            ResultSet rs = s.executeQuery(selectQuery);
            while (rs.next()) {
                Username = rs.getString("employeeID");
            }
        } catch (SQLException exp) {
        }
        return Username;
    }

    public void insertEmployee(String firstName, String lastName, String empPassword, String contact, int adminRights) {

        try (Connection conn = DriverManager.getConnection(url, username, password); Statement s = conn.createStatement()) {
            String insertQuery = "INSERT INTO employee (employeeFName, employeeLName, employeePassword,employeeContactNumber,employeeHoursWorked,admin )"
                    + "VALUES ('" + firstName + "', '"
                    + lastName + "', '"
                    + empPassword + "', '"
                    + contact + "', '"
                    + "00h00" + "','"
                    + adminRights + "')";
            s.execute(insertQuery);
            logs.writeLogs("ADDED", "Employee");
        } catch (SQLIntegrityConstraintViolationException e) {
            JOptionPane.showMessageDialog(null, "Employee already exsists");
        } catch (SQLException exp) {
            System.out.println(exp);
        }
    }

    public void insertExpenses(String type, double amount, String date) {

        try (Connection conn = DriverManager.getConnection(url, username, password); Statement s = conn.createStatement()) {
            String insertQuery = "INSERT INTO expenses (expensesType, expensesAmount,date)"
                    + "VALUES ('" + type + "', '"
                    + amount + "','"
                    + date + "')";
            s.execute(insertQuery);
            logs.writeLogs("ADDED", "Expenses");
        } catch (SQLException exp) {
            System.out.println(exp);
        }
    }

    public void insertInventory(String item, String category, int quantity, int limit, Double threshold, Double cost) {

        try (Connection conn = DriverManager.getConnection(url, username, password); Statement s = conn.createStatement()) {
            String insertQuery = "INSERT INTO inventory (item,category ,qty,itemThreshold,itemLimit, itemCost)"
                    + "VALUES ('" + item + "',"
                    + "'" + category + "',"
                    + "'" + quantity + "',"
                    + "'" + threshold + "', '"
                    + limit + "', '"
                    + cost + "')";
            s.execute(insertQuery);
            logs.writeLogs("ADDED", "Inventory");
        } catch (SQLIntegrityConstraintViolationException e) {
            JOptionPane.showMessageDialog(null, "Item already exsists");
        } catch (SQLException exp) {
            System.out.println(exp);
        }
    }

    public void insertRecipe(String recipe, double price, double vat, String imageDirectory, String category) {
        try (Connection conn = DriverManager.getConnection(url, username, password); Statement s = conn.createStatement()) {
            String insertQuery = "INSERT INTO recipe(recipeName, recipePrice, recipeVAT,recipeType, recipeImageDirectory, recipeCount)"
                    + "VALUES ('" + recipe + "','"
                    + price + "', '"
                    + vat + "', '"
                    + category + "', '"
                    + imageDirectory + "','0')";
            s.execute(insertQuery);
            logs.writeLogs("ADDED", "Recipe");
        } catch (SQLException exp) {
        }
    }

    public void insertWage(Double Wage, int ID) {
        try (Connection conn = DriverManager.getConnection(url, username, password); Statement s = conn.createStatement()) {
            String insertWages = "INSERT INTO wage ( employeeID, wageAmount)" + "VALUES ('" + ID + "','" + Wage + "')";
            s.execute(insertWages);
            logs.writeLogs("ADDED", "wage");
            JOptionPane.showMessageDialog(null, "The Wage has been added to the Database");
        } catch (SQLException exp) {
            System.out.println(exp);
        }
    }

    public int getRecipeID(String item) {
        int ID = 0;

        try (Connection conn = DriverManager.getConnection(url, username, password); Statement s = conn.createStatement()) {
            String selectQuery = "SELECT * FROM recipe WHERE recipeName='" + item + "'";
            ResultSet rs = s.executeQuery(selectQuery);

            while (rs.next()) {
                ID = Integer.parseInt(rs.getString("recipeID"));
            }
        } catch (SQLException exp) {
        }
        return ID;
    }

    public int getSpecialsID(String item) {
        int recipeID = getRecipeID(item);
        int specialID = 0;
        try (Connection conn = DriverManager.getConnection(url, username, password); Statement s = conn.createStatement()) {
            String selectQuery = "SELECT specialsID FROM recipe WHERE recipeID='" + recipeID + "'";
            ResultSet rs = s.executeQuery(selectQuery);

            while (rs.next()) {
                specialID = Integer.parseInt(rs.getString(1));
            }

        } catch (SQLException exp) {
            System.out.println(exp);
        }
        return specialID;
    }

    public double getSpecialsPrice(String item) {
        int specialID = getSpecialsID(item);
        double specialPrice = 0;

        try (Connection conn = DriverManager.getConnection(url, username, password); Statement s = conn.createStatement()) {
            String selectQuery = "SELECT specialsPrice FROM specials WHERE specialsID='" + specialID + "'";
            ResultSet rs = s.executeQuery(selectQuery);
            while (rs.next()) {
                specialPrice = rs.getDouble("specialsPrice");
            }
        } catch (SQLException exp) {
            System.out.println(exp);
        }
        return specialPrice;
    }

    public int getRecipeCost(String item) {
        int cost = 0;

        try (Connection conn = DriverManager.getConnection(url, username, password); Statement s = conn.createStatement()) {
            String selectQuery = "SELECT * FROM recipe WHERE recipeName='" + item + "'";
            ResultSet rs = s.executeQuery(selectQuery);

            while (rs.next()) {
                cost = Integer.parseInt(rs.getString("cost"));
            }
        } catch (SQLException exp) {
        }
        return cost;
    }

    public void insertReceipt(int recipeID, String cost) {

        try (Connection conn = DriverManager.getConnection(url, username, password); Statement s = conn.createStatement()) {
            String insertQuery = "INSERT INTO receipt(recipeID, orderQuantity, salesID, date, time , cost)"
                    + "VALUES ('"
                    + recipeID + "', '"
                    + "1" + "', "
                    + "(SELECT salesID FROM sales ORDER BY salesID DESC LIMIT 1), '"
                    + clock.getCurrentDate() + "', '"
                    + clock.getCurrentTimeStamp() + "', '"
                    + cost + "')";
            s.execute(insertQuery);
            logs.writeLogs("ADDED", "receipt");
        } catch (SQLException exp) {
            System.out.println("this " + exp);
        }
    }

    public int getInvetoryID(String item) {
        int ID = 0;

        try (Connection conn = DriverManager.getConnection(url, username, password); Statement s = conn.createStatement()) {
            String selectQuery = "SELECT inventoryID FROM inventory WHERE item='" + item + "'";
            ResultSet rs = s.executeQuery(selectQuery);

            while (rs.next()) {
                ID = Integer.parseInt(rs.getString("inventoryID"));
            }
        } catch (SQLException exp) {
        }
        return ID;
    }

    public double getCashTotal() {
        double cashTotal = 0;

        try (Connection conn = DriverManager.getConnection(url, username, password); Statement s = conn.createStatement()) {
            String selectQuery = "SELECT SUM(totalCost) FROM sales WHERE saleType='Cash' AND salesDate='" + clock.getCurrentDate() + "'";
            ResultSet rs = s.executeQuery(selectQuery);

            while (rs.next()) {
                cashTotal = rs.getDouble(1);
            }
        } catch (SQLException exp) {
        }
        return cashTotal;
    }

    public double getCreditTotal() {
        double creditTotal = 0;

        try (Connection conn = DriverManager.getConnection(url, username, password); Statement s = conn.createStatement()) {
            String selectQuery = "SELECT SUM(totalCost) FROM sales WHERE saleType='Credit' AND salesDate='" + clock.getCurrentDate() + "'";
            ResultSet rs = s.executeQuery(selectQuery);

            while (rs.next()) {
                creditTotal = rs.getDouble(1);
            }

        } catch (SQLException exp) {
        }
        return creditTotal;
    }

    public void insertRecipeList(String item, String qty) {

        try (Connection conn = DriverManager.getConnection(url, username, password); Statement s = conn.createStatement()) {
            String insertQuerySup = "INSERT INTO inventory_recipe(inventoryID, recipeID,qty)"
                    + "VALUES ('" + getInvetoryID(item) + "',"
                    + "(SELECT recipeID FROM recipe ORDER BY recipeID DESC LIMIT 1), '"
                    + qty + "')";
            s.execute(insertQuerySup);
            logs.writeLogs("ADDED", "Inventory_recipe");
        } catch (SQLException exp) {
            System.out.println(exp);
        }
    }

    public void insertSupplier(String name, String email, String number, String address) {

        try (Connection conn = DriverManager.getConnection(url, username, password); Statement s = conn.createStatement()) {
            String insertQuerySup = "INSERT INTO supplier(supplierName, supplierEmail,supplierNumber, supplierAddress)"
                    + "VALUES ('" + name + "','"
                    + email + "', '"
                    + number + "', '"
                    + address + "')";
            s.execute(insertQuerySup);
            logs.writeLogs("ADDED", "Supplier");
        } catch (SQLIntegrityConstraintViolationException e) {
            JOptionPane.showMessageDialog(null, "Supplier already exsists");
        } catch (SQLException exp) {
        }
    }

    public void insertReservations(String employee, String date, String time, String customerName, int tableNum, int customerNum, String contact) {

        try (Connection conn = DriverManager.getConnection(url, username, password); Statement s = conn.createStatement()) {
            String insertQuerySup = "INSERT INTO reservation(employeeID, reservationDate,"
                    + " reservationTime, reservationCustomer,reservationTableNumber,reservationNumberPeople,contactNumber)"
                    + "VALUES ('" + employee + "','"
                    + date + "','"
                    + time + "','"
                    + customerName + "', '"
                    + tableNum + "', '"
                    + customerNum + "', '"
                    + contact + "')";
            s.execute(insertQuerySup);
            JOptionPane.showMessageDialog(null, "Reservation Added");
            logs.writeLogs("ADDED", "Reservations");
        } catch (SQLException exp) {
            System.out.println(exp);
        }

    }

    public void insertStockOrder(String inventoryID, String supplierID, double quantity, String date, double cost) {

        try (Connection conn = DriverManager.getConnection(url, username, password); Statement s = conn.createStatement()) {
            String insertQuerySup = "INSERT INTO stockorder(inventoryID, supplierID,dateOrdered,dateETA,quantity,status,totalCost)"
                    + "VALUES ('" + inventoryID + "','"
                    + supplierID + "', '"
                    + clock.getCurrentDate() + "', '"
                    + date + "', '"
                    + quantity + "', 'Not Delievered', '"
                    + cost + "')";
            s.execute(insertQuerySup);
            logs.writeLogs("ADDED", "StockOrder");
        } catch (SQLException exp) {
            System.out.println(exp);
        }
    }

    public void insertSales(double currentTotal, String user, String type) {

        try (Connection conn = DriverManager.getConnection(url, username, password); Statement s = conn.createStatement()) {
            String insertQuerySup = "INSERT INTO sales(salesDate,totalCost,saleType)"
                    + "VALUES ('" + clock.getCurrentDate() + "','"
                    + currentTotal + "','"
                    + type + "')";
            s.execute(insertQuerySup);
            String selectQuery = "SELECT * FROM sales ORDER BY salesID DESC LIMIT 1";
            ResultSet rs = s.executeQuery(selectQuery);
            String ID = "";
            while (rs.next()) {
                ID = rs.getString("salesID");
            }
            insertEmployeeSales(ID, user);
            logs.writeLogs("ADDED", "Sales");
        } catch (SQLException exp) {
            System.out.println(exp);
        }
    }

    public void insertEmployeeSales(String ID, String user) {

        try (Connection conn = DriverManager.getConnection(url, username, password); Statement s = conn.createStatement()) {
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
        } catch (SQLException exp) {
            System.out.println(exp);
        }
    }

    public void insertSpecials(double specialPrice, String recipeName) {

        try (Connection conn = DriverManager.getConnection(url, username, password); Statement s = conn.createStatement()) {
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
        } catch (SQLException exp) {
            System.out.println(exp);
        }
    }

    public void removeInventory(int index) {

        try (Connection conn = DriverManager.getConnection(url, username, password); Statement s = conn.createStatement()) {
            String query = "DELETE FROM inventory WHERE inventoryID='" + index + "'";
            s.execute(query);
            logs.writeLogs("DELETED", "Inventory");
        } catch (SQLException exp) {
        }
    }

    public void removeRecipe(int index) {

        try (Connection conn = DriverManager.getConnection(url, username, password); Statement s = conn.createStatement()) {
            String query = "DELETE FROM recipe WHERE recipeID='" + index + "'";
            String query1 = "DELETE FROM inventory_recipe WHERE recipeID='" + index + "'";
            s.execute(query1);
            s.execute(query);
            removeRecipeList(index);
            logs.writeLogs("DELETED", "Recipe");
        } catch (SQLException exp) {
        }
    }

    public void removeRecipeList(int index) {

        try (Connection conn = DriverManager.getConnection(url, username, password); Statement s = conn.createStatement()) {
            String query = "DELETE FROM inventory_recipe WHERE recipeID='" + index + "'";
            s.execute(query);
            logs.writeLogs("DELETED", "Inventory_recipe");
        } catch (SQLException exp) {
        }
    }

    public void removeRecipeList(int index, int ID) {

        try (Connection conn = DriverManager.getConnection(url, username, password); Statement s = conn.createStatement()) {
            String query = "DELETE FROM inventory_recipe WHERE recipeID='" + index + "'AND inventoryID='" + ID + "'";
            s.execute(query);
            logs.writeLogs("DELETED", "Inventory_recipe");
        } catch (SQLException exp) {
        }
    }

    public void removeSupplier(int index) {

        try (Connection conn = DriverManager.getConnection(url, username, password); Statement s = conn.createStatement()) {
            String query = "DELETE FROM supplier WHERE supplierID='" + index + "'";
            s.execute(query);
            logs.writeLogs("DELETED", "Supplier");
        } catch (SQLException exp) {
        }
    }

    public void removeEmployee(int index) {

        try (Connection conn = DriverManager.getConnection(url, username, password); Statement s = conn.createStatement()) {
            String query = "DELETE FROM employee WHERE employeeID='" + index + "'";
            s.execute(query);
            logs.writeLogs("DELETED", "Employee");
        } catch (SQLException exp) {
        }
    }

    public void removeSpecials(String specialsID ) {
        try (Connection conn = DriverManager.getConnection(url, username, password); Statement s = conn.createStatement()) {
            String query = "DELETE FROM specials WHERE specialsID='" + specialsID + "'";
            s.execute(query);
            logs.writeLogs("DELETED", "Supplier");
        } catch (SQLException exp) {
        }
    }

    public void updateSpecials(String specialsID, int status) {

        try (Connection conn = DriverManager.getConnection(url, username, password); Statement s = conn.createStatement()) {
            String query = "UPDATE specials set status= '" + status + "' WHERE specialsID='" + specialsID + "'";
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.executeUpdate();
            getSpecialData();
        } catch (SQLException exp) {
            System.out.println(exp);
        }
    }

    public void updateHours(String Username, int i) {

        try (Connection conn = DriverManager.getConnection(url, username, password); Statement s = conn.createStatement()) {
            String queryUpdate = "UPDATE employee set employeeHoursWorked='" + clock.calculateHours(i, getHoursWorked(Username))
                    + "' WHERE employeeFName='" + Username + "'";
            PreparedStatement preparedStmt = conn.prepareStatement(queryUpdate);
            preparedStmt.executeUpdate();
            logs.writeLogs("UPDATED", "Employee");
        } catch (SQLException exp) {
            System.out.println(exp);
        }
    }

    public void updateEmployeeStatusIn(String Username) {

        try (Connection conn = DriverManager.getConnection(url, username, password); Statement s = conn.createStatement()) {
            String query = "UPDATE employee set employeeStatus= 'Active' WHERE employeeFName='" + Username + "'";
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.executeUpdate();
        } catch (SQLException exp) {
        }
    }

    public void updateEmployeeStatusOut(String Username) {

        try (Connection conn = DriverManager.getConnection(url, username, password); Statement s = conn.createStatement()) {
            String query = "UPDATE employee set employeeStatus= 'Deactive' WHERE employeeFName='" + Username + "'";
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.executeUpdate();
        } catch (SQLException exp) {
        }
    }

    //Updates Inventory table with "Update" button
    public void updateInventory(Object ID, Object item, Object qty, Object itemT, Object itemL) {

        try ( //connect to db
                Connection conn = DriverManager.getConnection(url, username, password); Statement s = conn.createStatement()) {
            //update query
            String query = "UPDATE inventory set item='" + item + "' '"
                    + "' ,qty='" + qty + "' '"
                    + "' ,itemThreshold='" + itemT + "' '"
                    + "' ,itemLimit='" + itemL + "' WHERE inventoryID='" + ID + "'";
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.executeUpdate();
            logs.writeLogs("UPDATED", "Inventory");
        } catch (SQLException exp) {
            //prints SQL Exception
            System.out.println("Error: " + exp);
        }
    }

    //Updates recipe table with "Update" button
    public void updateRecipe(Object ID, Object name, Object price, Object vat, Object type) {

        try (Connection conn = DriverManager.getConnection(url, username, password); Statement s = conn.createStatement()) {

            String query = "UPDATE recipe set recipeName='" + name + "' '"
                    + "' ,recipePrice='" + price + "' '"
                    + "' ,recipeVAT='" + vat + "' '"
                    + "' ,recipeType='" + type + "' WHERE recipeID='" + ID + "'";
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.executeUpdate();
            logs.writeLogs("UPDATED", "Recipe");
        } catch (SQLException exp) {
            //prints SQL Exception
            System.out.println("Error: " + exp);
        }
    }

    public void updateInventoryQty(String item, String qty, String operator) {
        int ID = getInvetoryID(item);

        try (Connection conn = DriverManager.getConnection(url, username, password); Statement s = conn.createStatement()) {
            String query = "UPDATE inventory set qty=qty" + operator + "'" + qty + "' "
                    + "WHERE inventoryID='" + ID + "'";
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.executeUpdate();
            logs.writeLogs("UPDATED", "Inventory");
        } catch (SQLException exp) {
            System.out.println(exp);
        }
    }

    public void updateRecipeList(String iventoryID, String recipeID, String qty) {

        try (Connection conn = DriverManager.getConnection(url, username, password); Statement s = conn.createStatement()) {
            String query = "UPDATE inventory_recipe set qty='" + qty + "' "
                    + "WHERE  inventoryID='" + iventoryID + "'AND recipeID='" + recipeID + "'";
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.executeUpdate();
            logs.writeLogs("UPDATED", "Inventory_recipe");
        } catch (SQLException exp) {
            System.out.println(exp);
        }
    }
    //Updates supplier table with "Update" button

    public void updateSupplier(Object ID, Object name, Object email, Object num, Object address) {

        try ( //connect to db
                Connection conn = DriverManager.getConnection(url, username, password); Statement s = conn.createStatement()) {
            //update query
            String query = "UPDATE supplier set supplierName='" + name + "' '"
                    + "' ,supplierEmail='" + email + "' '"
                    + "' ,supplierNumber='" + num + "' '"
                    + "' ,supplierAddress='" + address + "' WHERE supplierID='" + ID + "'";
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.executeUpdate();
            logs.writeLogs("UPDATED", "Supplier");
        } catch (SQLException exp) {
            //prints SQL Exception
            System.out.println("Error: " + exp);
        }
    }

    public void updateEmployee(String user, String newPassword) {

        try (Connection conn = DriverManager.getConnection(url, username, password); Statement s = conn.createStatement()) {
            String updateQuery = "UPDATE employee SET employeePassword='" + newPassword + "' WHERE employeeFName ='" + user + "'";
            s.execute(updateQuery);
            PreparedStatement preparedStmt = conn.prepareStatement(updateQuery);
            preparedStmt.executeUpdate();
            logs.writeLogs("UPDATED", "Employee");
        } catch (SQLException exp) {
            System.out.println(exp);
        }
    }

    public void loggoutAllEmployee() {

        try (Connection conn = DriverManager.getConnection(url, username, password); Statement s = conn.createStatement()) {
            String updateQuery = "UPDATE employee SET employeeStatus='Deactive'";
            s.execute(updateQuery);
            PreparedStatement preparedStmt = conn.prepareStatement(updateQuery);
            preparedStmt.executeUpdate();
        } catch (SQLException exp) {
            System.out.println(exp);
        }
    }

    public void updateRecipeSpecialsID(String specialsID, String recipeName) {

        try (Connection conn = DriverManager.getConnection(url, username, password); Statement s = conn.createStatement()) {
            String updateQuery = "UPDATE recipe SET specialsID='" + specialsID + "' WHERE recipeName ='" + recipeName + "'";
            s.execute(updateQuery);
            PreparedStatement preparedStmt = conn.prepareStatement(updateQuery);
            preparedStmt.executeUpdate();
        } catch (SQLException exp) {
            System.out.println(exp);
        }
    }

    public void updateOrderCount(String order) {

        try (Connection conn = DriverManager.getConnection(url, username, password); Statement s = conn.createStatement()) {
            String updateQuery = "UPDATE recipe SET recipeCount=(recipeCount+1) WHERE recipeName ='" + order + "'";
//            s.execute(updateQuery);
            PreparedStatement preparedStmt = conn.prepareStatement(updateQuery);
            preparedStmt.executeUpdate();
            logs.writeLogs("UPDATED", "recipe");
        } catch (SQLException exp) {
            System.out.println(exp);
        }
    }
    // revise- simplify if possible

    public void recipeStockUpdate(JTable table, String tableNum) {
        try (Connection conn = DriverManager.getConnection(url, username, password); Statement s = conn.createStatement()) {

            for (int i = 0; i < table.getRowCount(); i++) {
                String selectQuery = "SELECT recipeID FROM recipe WHERE recipeName='" + table.getValueAt(i, 0).toString() + "'";
                ResultSet rs = s.executeQuery(selectQuery);
                while (rs.next()) {
                    String ID = rs.getString("recipeID");
                    selectQuery = "SELECT * FROM inventory_recipe WHERE recipeID= '" + ID + "'";
                    rs = s.executeQuery(selectQuery);

                    while (rs.next()) {
                        String inventoryID = rs.getString("inventoryID");
                        System.out.println(inventoryID);
                        String qty = rs.getString("qty");
                        System.out.println(qty);
                        String query = "Update inventory set qty =  GREATEST(0,qty-'" + qty + "') WHERE inventoryID = '" + inventoryID + "'";
                        PreparedStatement preparedstmt = conn.prepareStatement(query);
                        preparedstmt.executeUpdate();

                    }
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(NewOrder.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        checkStockLevel();
    }

    public Object[][] checkStockLevel() {
        Object stockLevel[][] = null;
        try (Connection conn = DriverManager.getConnection(url, username, password); Statement s = conn.createStatement()) {

            String selectQuery = "SELECT * FROM inventory WHERE qty<(itemThreshold*itemLimit)";
            ResultSet rs = s.executeQuery(selectQuery);
            int i = 0;
            int rowCount = 0;
            while (rs.next()) {
                rowCount++;
            }
            rs = s.executeQuery(selectQuery);
            stockLevel = new Object[rowCount][3];
            while (rs.next()) {
                stockLevel[i][0] = rs.getString(1);
                stockLevel[i][1] = rs.getString(2);
                stockLevel[i][2] = rs.getString(4);
                i++;
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return stockLevel;
    }

    public void updateConfirmOrder(String orderID) {

        try (Connection conn = DriverManager.getConnection(url, username, password); Statement s = conn.createStatement()) {
            String updateQuery = "UPDATE stockorder SET status='Delievered' WHERE stockOrderID ='" + orderID + "'";
            s.execute(updateQuery);
            PreparedStatement preparedStmt = conn.prepareStatement(updateQuery);
            preparedStmt.executeUpdate();
        } catch (SQLException exp) {
            System.out.println(exp);
        }
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

    public void scheduleBackup() {
        String date[] = clock.getCurrentDate().split("-");
        int day = Integer.parseInt(date[2]);
        if (day % 7 == 0) {
            try {
                String executeCmd = ".\\src\\database\\mysqldump.exe";

                Process runtimeProcess = Runtime.getRuntime().exec(executeCmd);
                int processComplete = runtimeProcess.waitFor();

                if (processComplete == 0) {
                    System.out.println("Backup Complete");
                } else {
                    System.out.println("Restore Failed");
                }

            } catch (IOException | InterruptedException | HeadlessException ex) {
                JOptionPane.showMessageDialog(null, "Error at Restoredbfromsql" + ex.getMessage());
            }
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
