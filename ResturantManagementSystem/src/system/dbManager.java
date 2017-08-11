package system;

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

    static String url = "jdbc:mysql://localhost:3306/resturantdb";
    static String username = "root";
    static String password = "root";
    static String driver = "com.mysql.jdbc.Driver";
    static ArrayList recipeName = new ArrayList();
    static ArrayList recipeIndex = new ArrayList();
    static ArrayList recipeImage = new ArrayList();

    public static ArrayList getRecipeName() {
        return recipeName;
    }

    public static ArrayList getRecipeIndex() {
        return recipeIndex;
    }

    public static ArrayList getRecipeImage() {
        return recipeImage;
    }

    public static void populateTables() {
        String columnNamesInventory[] = {"Inventory ID", "Item Name", "Quantity(kg)"};
        String columnNamesRecipe[] = {"Recipe ID", "Description", "Price", "VAT"};
        String columnNamesSuppler[] = {"Supplier ID", "Name", "Email", "Contact Number", "Address"};
        try {

            Class.forName(driver).newInstance();
            Connection conn = DriverManager.getConnection(url, username, password);
            Statement s = conn.createStatement();
            String query = "SELECT inventoryid,item,qty FROM inventory ";
            ResultSet rs = s.executeQuery(query);
            ResultSetMetaData metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();
            DefaultTableModel tableModel = new DefaultTableModel();
            MainSystem.tblInventory.setModel(tableModel);

            for (int i = 0; i < columnCount; i++) {
                tableModel.addColumn(columnNamesInventory[i]);
            }
            Object[] row = new Object[columnCount];

            while (rs.next()) {
                for (int i = 0; i < columnCount; i++) {
                    row[i] = rs.getObject(i + 1);
                }
                MainSystem.searchTable();
                tableModel.addRow(row);
            }
            MainSystem.tblInventory.getColumnModel().getColumn(0).setPreferredWidth(10);
            MainSystem.tblInventory.getColumnModel().getColumn(1).setPreferredWidth(400);
            MainSystem.tblInventory.getColumnModel().getColumn(2).setPreferredWidth(100);
            rs.close();
            s.close();
            conn.close();
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | SQLException exc) {
        }
        try {
            Class.forName(driver).newInstance();
            Connection conn = DriverManager.getConnection(url, username, password);
            Statement s = conn.createStatement();
            String query = "SELECT recipeID, recipeName, recipePrice, recipeVAT FROM recipe";
            ResultSet rs = s.executeQuery(query);
            ResultSetMetaData metaData = rs.getMetaData();
            DefaultTableModel tableModel1 = new DefaultTableModel();
            MainSystem.tableRecipe.setModel(tableModel1);

            int columnCount = metaData.getColumnCount();
            for (int i = 0; i < columnCount; i++) {
                tableModel1.addColumn(columnNamesRecipe[i]);
            }

            Object[] row = new Object[columnCount];

            while (rs.next()) {
                for (int i = 0; i < columnCount; i++) {
                    row[i] = rs.getObject(i + 1);
                }
                MainSystem.searchTable();
                tableModel1.addRow(row);
            }
            MainSystem.tableRecipe.getColumnModel().getColumn(0).setPreferredWidth(100);
            MainSystem.tableRecipe.getColumnModel().getColumn(1).setPreferredWidth(300);
            MainSystem.tableRecipe.getColumnModel().getColumn(2).setPreferredWidth(100);
            MainSystem.tableRecipe.getColumnModel().getColumn(3).setPreferredWidth(100);
            rs.close();
            s.close();
            conn.close();
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | SQLException exc) {
        }

        try {
            Class.forName(driver).newInstance();
            Connection conn = DriverManager.getConnection(url, username, password);
            Statement s = conn.createStatement();
            String query = "SELECT * FROM supplier";
            ResultSet rs = s.executeQuery(query);
            ResultSetMetaData metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();
            DefaultTableModel tableModel2 = new DefaultTableModel();
            MainSystem.tableSupplier.setModel(tableModel2);

            for (int i = 0; i < columnNamesSuppler.length; i++) {
                tableModel2.addColumn(columnNamesSuppler[i]);
            }
            Object[] row = new Object[columnCount];

            while (rs.next()) {
                for (int i = 0; i < columnCount; i++) {
                    row[i] = rs.getObject(i + 1);
                }
                MainSystem.searchTable();
                tableModel2.addRow(row);
            }
            MainSystem.tableSupplier.getColumnModel().getColumn(0).setPreferredWidth(100);
            MainSystem.tableSupplier.getColumnModel().getColumn(1).setPreferredWidth(300);
            MainSystem.tableSupplier.getColumnModel().getColumn(2).setPreferredWidth(300);
            MainSystem.tableSupplier.getColumnModel().getColumn(3).setPreferredWidth(200);
            MainSystem.tableSupplier.getColumnModel().getColumn(4).setPreferredWidth(250);
            rs.close();
            s.close();
            conn.close();
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | SQLException exc) {

        }
    }

    public static void populateEmpTable() {
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

    public static void populateReservation() {
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

    public static void populateOrder() {
        String columnNamesEmp[] = {"ID", "Inventory ID", "Supplier ID", "Date Ordered", "ETA", "Quantity(kg)", "Status"};
        try {
            Class.forName(driver).newInstance();
            Connection conn = DriverManager.getConnection(url, username, password);
            Statement s = conn.createStatement();
            String query = "SELECT* FROM stockOrder";
            ResultSet rs = s.executeQuery(query);
            ResultSetMetaData metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();
            DefaultTableModel tableModel = new DefaultTableModel();
            MainSystem.getOrderTable().setModel(tableModel);

            for (int i = 0; i < columnCount; i++) {
                tableModel.addColumn(columnNamesEmp[i]);
            }
            Object[] row = new Object[columnCount];

            while (rs.next()) {
                for (int i = 0; i < columnCount; i++) {
                    row[i] = rs.getObject(i + 1);
                }
                tableModel.addRow(row);
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

    public static void populateSales(JTable sales) {
        String columnNames[] = {"ID", "Sales Date", "Total Sale"};
        try {
            Class.forName(driver).newInstance();
            Connection conn = DriverManager.getConnection(url, username, password);
            Statement s = conn.createStatement();
            String query = "SELECT* FROM sales";
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

    public static Object[][] getRecipe() {
        int count = getRecipesCount();
        Object recipe[][] = new Object[count][4];
        try {
            Class.forName(driver).newInstance();
            Connection conn = DriverManager.getConnection(url, username, password);
            Statement s = conn.createStatement();
            String query = "SELECT recipeName,recipePrice,recipeImageDirectory FROM recipe";
            ResultSet rs = s.executeQuery(query);

//            for (int i = 0; i < 4; i++) {
            int i = 0;
            while (rs.next()) {
                recipe[i][0] = i;
                recipe[i][1] = rs.getString(1);
                recipe[i][2] = rs.getString(2);
                recipe[i][3] = rs.getString(3);
                i++;
            }
//            }

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

    public static int getRecipesCount() {
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

    public static void showActiveEmp() {
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

    public static String getHoursWorked(String Username) {
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
            exp.printStackTrace();
        }
        return time;
    }

    public static void insertEmployee() {
        try {
            Connection conn = DriverManager.getConnection(url, username, password);
            Statement s = conn.createStatement();
            String insertQuery = "INSERT INTO employee (employeeFName, employeeLName, employeePassword,employeeContactNumber)"
                    + "VALUES ('" + AddEmployee.getEmpFirstName() + "', '"
                    + AddEmployee.getEmpLastName() + "', '"
                    + AddEmployee.getEmpPassword() + "', '"
                    + AddEmployee.getEmpContact() + "')";
            s.execute(insertQuery);
            populateEmpTable();
            s.close();
            conn.close();
        } catch (SQLException exp) {
        }
    }

    public static void insertInventory() {
        try {
            Connection conn = DriverManager.getConnection(url, username, password);
            Statement s = conn.createStatement();
            String insertQuery = "INSERT INTO inventory (item, qty)"
                    + "VALUES ('" + AddDatabase.getItem() + "', '"
                    + AddDatabase.getQuantity() + "')";
            s.execute(insertQuery);
            populateTables();
            s.close();
            conn.close();
        } catch (SQLException exp) {
            System.out.println(exp);
        }
    }

    public static void insertRecipe() {
        try {
            Connection conn = DriverManager.getConnection(url, username, password);
            Statement s = conn.createStatement();
            String insertQuerySup = "INSERT INTO recipe(recipeName, recipePrice,recipeVAT,recipeImageDirectory)"
                    + "VALUES ('" + AddDatabase.getRecipe() + "','"
                    + AddDatabase.getPrice() + "', '"
                    + AddDatabase.getVAT() + "', '"
                    + AddDatabase.getImageDirectory() + "')";
            s.execute(insertQuerySup);
            populateTables();
            s.close();
            conn.close();
        } catch (SQLException exp) {
        }
    }

    public static void insertSupplier() {
        try {
            Connection conn = DriverManager.getConnection(url, username, password);
            Statement s = conn.createStatement();
            String insertQuerySup = "INSERT INTO supplier(supplierName, supplierEmail,supplierNumber, supplierAddress)"
                    + "VALUES ('" + AddDatabase.getSupName() + "','"
                    + AddDatabase.getSupEmail() + "', '"
                    + AddDatabase.getSupContact() + "', '"
                    + AddDatabase.getSupAddress() + "')";
            s.execute(insertQuerySup);
            populateTables();
            s.close();
            conn.close();
        } catch (SQLException exp) {
        }
    }

    public static void insertReservations() {
        try {
            Connection conn = DriverManager.getConnection(url, username, password);
            Statement s = conn.createStatement();
            String insertQuerySup = "INSERT INTO reservation(employeeID, reservationDate, reservationTime, reservationCustomer,reservationTableNumber,reservationNumberPeople)"
                    + "VALUES ('" + Booking.getEmployee() + "','"
                    + Booking.getDate() + "','"
                    + Booking.getTime() + "','"
                    + Booking.getCustomerName() + "', '"
                    + Booking.getTableNum() + "', '"
                    + Booking.getCustomerNum() + "')";
            s.execute(insertQuerySup);
            JOptionPane.showMessageDialog(null, "Reservation Added");
            populateReservation();
            s.close();
            conn.close();
        } catch (SQLException exp) {
            System.out.println(exp);
        }

    }

    public static void insertStockOrder() {
        try {
            Connection conn = DriverManager.getConnection(url, username, password);
            Statement s = conn.createStatement();
            String insertQuerySup = "INSERT INTO stockorder(inventoryID, supplierID,dateOrdered,quantity,status)"
                    + "VALUES ('" + OrderForm.getInventoryID() + "','"
                    + OrderForm.getSupplierID() + "', '"
                    + internalClock.getCurrentDate() + "', '"
                    + OrderForm.getQuantity() + "', 'Not Delievered')";
            s.execute(insertQuerySup);
            populateOrder();
            s.close();
            conn.close();
        } catch (SQLException exp) {
            System.out.println(exp);
        }
    }

    public static void insertSales(double currentTotal) {
        try {
            Connection conn = DriverManager.getConnection(url, username, password);
            Statement s = conn.createStatement();
            String insertQuerySup = "INSERT INTO sales(salesDate,totalCost)"
                    + "VALUES ('" + internalClock.getCurrentDate() + "','"
                    + currentTotal + "')";
            s.execute(insertQuerySup);
            populateOrder();
            s.close();
            conn.close();
        } catch (SQLException exp) {
            System.out.println(exp);
        }
    }

    public static void insertReceipt() {
//        try {
//            Connection conn = DriverManager.getConnection(url, username, password);
//            Statement s = conn.createStatement();
//            String insertQuerySup = "INSERT INTO receipt(recipeID, salessID,orderQuantity)"
//                    + "VALUES ('" + OrderForm.getInventoryID() + "','"
//                    + OrderForm.getSupplierID() + "', '"
//                    + internalClock.getCurrentDate() + "', '"
//                    + OrderForm.getQuantity() + "', 'Not Delievered')";
//            s.execute(insertQuerySup);
//            populateOrder();
//            s.close();
//            conn.close();
//        } catch (SQLException exp) {
//            System.out.println(exp);
//        }
    }

    public static void removeInventory(int index) {
        try {
            Connection conn = DriverManager.getConnection(url, username, password);
            Statement s = conn.createStatement();
            String query = "DELETE FROM inventory WHERE inventoryID='" + index + "'";
            s.execute(query);
            populateTables();
            s.close();
            conn.close();
        } catch (SQLException exp) {
            exp.printStackTrace();
        }
    }

    public static void removeRecipe(int index) {
        try {
            Connection conn = DriverManager.getConnection(url, username, password);
            Statement s = conn.createStatement();
            String query = "DELETE FROM recipe WHERE recipeID='" + index + "'";
            s.execute(query);
            populateTables();
            s.close();
            conn.close();
        } catch (SQLException exp) {
            exp.printStackTrace();
        }
    }

    public static void removeSupplier(int index) {
        try {
            Connection conn = DriverManager.getConnection(url, username, password);
            Statement s = conn.createStatement();
            String query = "DELETE FROM supplier WHERE supplierID='" + index + "'";
            s.execute(query);
            populateTables();
            s.close();
            conn.close();
        } catch (SQLException exp) {
            exp.printStackTrace();
        }
    }

    public static void removeEmployee(int index) {
        try {
            Connection conn = DriverManager.getConnection(url, username, password);
            Statement s = conn.createStatement();
            String query = "DELETE FROM employee WHERE employeeID='" + index + "'";
            s.execute(query);
            populateEmpTable();
            s.close();
            conn.close();
        } catch (SQLException exp) {
            exp.printStackTrace();
        }
    }

    public static void updateHours(String Username, int i) {
        try {
            Connection conn = DriverManager.getConnection(url, username, password);
            Statement s = conn.createStatement();
            String queryUpdate = "UPDATE employee set employeeHoursWorked='" + internalClock.calculateHours(i, getHoursWorked(Username))
                    + "' WHERE employeeFName='" + Username + "'";
            PreparedStatement preparedStmt = conn.prepareStatement(queryUpdate);
            preparedStmt.executeUpdate();
            s.close();
            conn.close();
        } catch (SQLException exp) {
            exp.printStackTrace();
        }
    }

    public static void updateEmployeeStatusIn(String Username) {
        try {
            Connection conn = DriverManager.getConnection(url, username, password);
            Statement s = conn.createStatement();
            String query = "UPDATE employee set employeeStatus= 'Active' WHERE employeeFName='" + Username + "'";
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.executeUpdate();
            s.close();
            conn.close();
        } catch (SQLException exp) {
            exp.printStackTrace();
        }
    }

    public static void updateEmployeeStatusOut(String Username) {
        try {
            Connection conn = DriverManager.getConnection(url, username, password);
            Statement s = conn.createStatement();
            String query = "UPDATE employee set employeeStatus= 'Deactive' WHERE employeeFName='" + Username + "'";
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.executeUpdate();
            s.close();
            conn.close();
        } catch (SQLException exp) {
            exp.printStackTrace();
        }
    }

    public static void update() {
//        try {
//            Connection conn = DriverManager.getConnection(url, username, password);
//            Statement s = conn.createStatement();
//            String query = "UPDATE Book set Title='" + titleBox.getText()
//                    + "' ,Author='" + authorBox.getText() + "' '"
//                    + "' ,Year='" + yearBox.getText() + "' '"
//                    + "' ,Edtion='" + edBox.getText() + "' '"
//                    + "' ,Category='" + catBox.getText() + "' '"
//                    + "' ,Publisher='" + pubBox.getText() + "' '"
//                    + "' ,Copies='" + copiesBox.getText() + "' WHERE ISBN='" + isbnBox.getText() + "'";
//            PreparedStatement preparedStmt = conn.prepareStatement(query);
//            preparedStmt.executeUpdate();
//            s.close();
//            conn.close();
//        } catch (SQLException exp) {
//            System.out.println("Error");
//            exp.printStackTrace();
//        }
    }

    public static boolean login(String userName, String passWord) {
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
}
