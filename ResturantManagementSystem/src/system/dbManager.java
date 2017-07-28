/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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

    public static void populateTables() {
        String columnNamesInventory[] = {"ID", "Item Name", "Quantity(g)"};
        String columnNamesRecipe[] = {"ID", "Item", "Price", "VAT"};
        String columnNamesSuppler[] = {"ID", "Name", "Email", "Contact Number", "Address"};
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
                tableModel.addRow(row);
            }

            rs.close();
            s.close();
            conn.close();
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | SQLException exc) {
        }
        try {
            Class.forName(driver).newInstance();
            Connection conn = DriverManager.getConnection(url, username, password);
            Statement s = conn.createStatement();
            String query = "SELECT * FROM recipe";
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
                tableModel1.addRow(row);
            }
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

            for (int i = 0; i < columnCount; i++) {
                tableModel2.addColumn(columnNamesSuppler[i]);
            }
            Object[] row = new Object[columnCount];

            while (rs.next()) {
                for (int i = 0; i < columnCount; i++) {
                    row[i] = rs.getObject(i + 1);
                }
                tableModel2.addRow(row);
            }
            rs.close();
            s.close();
            conn.close();
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | SQLException exc) {
        }
    }

    public static void populateEmpTable() {
        String columnNamesEmp[] = {"ID", "First Name", "Last Name", "Number"};
        try {
            Class.forName(driver).newInstance();
            Connection conn = DriverManager.getConnection(url, username, password);
            Statement s = conn.createStatement();
            String query = "SELECT employeeID,employeeFName,employeeLName,employeeContactNumber FROM employee";
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
        String columnNamesEmp[] = {"Employee", "Date", "Table No.", "No. Customer"};
        try {
            Class.forName(driver).newInstance();
            Connection conn = DriverManager.getConnection(url, username, password);
            Statement s = conn.createStatement();
            String query = "SELECT employeeID, reservationDate,reservationTableNumber,reservationNumberPeople FROM reservation";
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

    public static void insertEmployee() {
        try {
            Connection conn = DriverManager.getConnection(url, username, password);
            Statement s = conn.createStatement();
            String insertQuery = "INSERT INTO employee (employeeFName, employeeLName, employeePassword,employeeContactNumber)"
                    + "VALUES ('" + AddEmployee.textfieildFname.getText() + "', '"
                    + AddEmployee.textfieildSname.getText() + "', '"
                    + AddEmployee.textfieildPassowrd.getText() + "', '"
                    + AddEmployee.textfieildContact.getText() + "')";
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
                    + "VALUES ('"+ AddDatabase.textfieldItem.getText() + "', '"
                    + AddDatabase.textfieldQty.getText() + "')";
            s.execute(insertQuery);
            populateTables();
            s.close();
            conn.close();
        } catch (SQLException exp) {
            System.out.println(exp);
        }
    }

    public static void insertSupplier() {
        try {
            Connection conn = DriverManager.getConnection(url, username, password);
            Statement s = conn.createStatement();
            String insertQuerySup = "INSERT INTO supplier(supplierName, supplierEmail,supplierNumber, supplierAddress)"
                    + "VALUES ('" + AddDatabase.textfieldDisName.getText() + "','"
                    + AddDatabase.textfieldDisEmail.getText() + "', '"
                    + AddDatabase.textfieldDisContact.getText() + "', '"
                    + AddDatabase.textfieldDisAddress.getText() + "')";
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
            String insertQuerySup = "INSERT INTO reservation(employeeID, reservationDate,reservationTableNumber,reservationNumberPeople)"
                    + "VALUES ('" + Booking.textEmp.getText() + "','"
                    + Booking.textDate.getText() + "', '"
                    + Booking.textTable.getText() + "', '"
                    + Booking.textCustomers.getText() + "')";
            s.execute(insertQuerySup);
            JOptionPane.showMessageDialog(null, "Reservation Added");
            populateReservation();
            s.close();
            conn.close();
        } catch (SQLException exp) {
            System.out.println(exp);
        }
        
    }
    
     public static void insertRecipe() {
//        try {
//            Connection conn = DriverManager.getConnection(url, username, password);
//            Statement s = conn.createStatement();
//            String insertQuerySup = "INSERT INTO supplier(supplierName, supplierEmail,supplierNumber, supplierAddress)"
//                    + "VALUES ('" + AddDatabase.textfieldDisName.getText() + "','"
//                    + AddDatabase.textfieldDisEmail.getText() + "', '"
//                    + AddDatabase.textfieldDisContact.getText() + "', '"
//                    + AddDatabase.textfieldDisAddress.getText() + "')";
//            s.execute(insertQuerySup);
//            populateTables();
//            s.close();
//            conn.close();
//        } catch (SQLException exp) {
//        }
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
