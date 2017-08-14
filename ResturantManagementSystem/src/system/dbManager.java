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

    String url = "jdbc:mysql://localhost:3306/resturantdb";
    String username = "root";
    String password = "root";
    String driver = "com.mysql.jdbc.Driver";
    ArrayList recipeName = new ArrayList();
    ArrayList recipeIndex = new ArrayList();
    ArrayList recipeImage = new ArrayList();

    public ArrayList getRecipeName() {
        return recipeName;
    }

    public ArrayList getRecipeIndex() {
        return recipeIndex;
    }

    public ArrayList getRecipeImage() {
        return recipeImage;
    }

    public void populateTables() {
        String columnNamesInventory[] = {"Inventory ID", "Item Name", "Quantity(kg)", "Item Threshold", "Item Limit"};
        String columnNamesRecipe[] = {"Recipe ID", "Description", "Price", "VAT"};
        String columnNamesSuppler[] = {"Supplier ID", "Name", "Email", "Contact Number", "Address"};
        try {

            Class.forName(driver).newInstance();
            Connection conn = DriverManager.getConnection(url, username, password);
            Statement s = conn.createStatement();
            String query = "SELECT * FROM inventory ";
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
                MainSystem.searchITable();
                tableModel.addRow(row);
            }
            MainSystem.tblInventory.getColumnModel().getColumn(0).setPreferredWidth(100);
            MainSystem.tblInventory.getColumnModel().getColumn(1).setPreferredWidth(300);
            MainSystem.tblInventory.getColumnModel().getColumn(2).setPreferredWidth(100);
            MainSystem.tblInventory.getColumnModel().getColumn(3).setPreferredWidth(100);
            MainSystem.tblInventory.getColumnModel().getColumn(4).setPreferredWidth(100);
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
                MainSystem.searchRTable();
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
                MainSystem.searchSTable();
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

    public void populateOrder() {
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

    public void populateSales(JTable sales) {
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
    
    public void populateEmployeeSales(JTable sales) {
        String columnNames[] = {"Sale ID","User","Employee ID"};
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

    public Object[][] getRecipe() {
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
            exp.printStackTrace();
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
            s.close();
            conn.close();
        } catch (SQLException exp) {
            System.out.println(exp);
        }
    }

    public void insertInventory(String item, String quantity, Double limit, Double threshold) {
        try {
            Connection conn = DriverManager.getConnection(url, username, password);
            Statement s = conn.createStatement();
            String insertQuery = "INSERT INTO inventory (item, qty,itemThreshold,itemLimit)"
                    + "VALUES ('" + item + "',"
                    + "'" + quantity + "',"
                    + "'" + threshold + "', '"
                    + limit + "')";
            s.execute(insertQuery);
            populateTables();
            s.close();
            conn.close();
        } catch (SQLException exp) {
            System.out.println(exp);
        }
    }

    public void insertRecipe(String name, String price, String vat, String directory, String category) {
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
            populateTables();
            s.close();
            conn.close();
        } catch (SQLException exp) {
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
            populateTables();
            s.close();
            conn.close();
        } catch (SQLException exp) {
        }
    }

    public void insertReservations(String employee, String date, String time, String customerName, String tableNum, String customerNum) {
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
                    + internalClock.getCurrentDate() + "', '"
                    + date + "', '"
                    + quantity + "', 'Not Delievered')";
            s.execute(insertQuerySup);
            populateOrder();
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
                    + "VALUES ('" + internalClock.getCurrentDate() + "','"
                    + currentTotal + "')";
            s.execute(insertQuerySup);
            String selectQuery = "SELECT * FROM sales ORDER BY salesID DESC LIMIT 1";
            ResultSet rs = s.executeQuery(selectQuery);
            String ID = "";
            while (rs.next()) {
                ID = rs.getString("salesID");
            }
            insertEmployeeSales(ID, user);
            populateOrder();
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
            s.close();
            conn.close();
        } catch (SQLException exp) {
            System.out.println(exp);
        }
    }

    public void insertReceipt() {
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

    public void removeInventory(int index) {
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

    public void removeRecipe(int index) {
        try {
            Connection conn = DriverManager.getConnection(url, username, password);
            Statement s = conn.createStatement();
            String query = "DELETE FROM recipe WHERE recipeID='" + index + "'";
            s.execute(query);
            populateTables();
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
            populateTables();
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
            s.close();
            conn.close();
        } catch (SQLException exp) {
        }
    }

    public void updateHours(String Username, int i) {
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
}
