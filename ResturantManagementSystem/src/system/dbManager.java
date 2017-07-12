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
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Andrew
 */
public class dbManager {

    static String url;
    static String username = "root";
    static String password = "root";
    static String driver = "com.mysql.jdbc.Driver";

    public static void populateTables() {
        //        try {
        //            Class.forName(driver).newInstance();
        //            Connection conn = DriverManager.getConnection(url, username, password);
        //            Statement s = conn.createStatement();
        //            String query = "SELECT * FROM bookreserve ";
        //            ResultSet rs = s.executeQuery(query);
        //            ResultSetMetaData metaData = rs.getMetaData();
        //            int columnCount = metaData.getColumnCount();
        //            DefaultTableModel tableModelReserve = new DefaultTableModel();
        //            tbl.setModel(tableModelReserve);
        //
        //            for (int i = 1; i <= columnCount; i++) {
        //                tableModelReserve.addColumn(metaData.getColumnLabel(i));
        //            }
        //            Object[] row = new Object[columnCount];
        //
        //            while (rs.next()) {
        //                for (int i = 0; i < columnCount; i++) {
        //                    row[i] = rs.getObject(i + 1);
        //                }
        //                tableModelReserve.addRow(row);
        //            }
        //
        //            rs.close();
        //            s.close();
        //            conn.close();
        //        } catch (Exception exc) {
        //            exc.printStackTrace();
        //
        //        }
        //        try {
        //            Class.forName(driver).newInstance();
        //            Connection conn = DriverManager.getConnection(url, username, password);
        //            Statement s = conn.createStatement();
        //            String query = "SELECT * FROM Book";
        //            ResultSet rs = s.executeQuery(query);
        //            ResultSetMetaData metaData = rs.getMetaData();
        //            DefaultTableModel tableModel = new DefaultTableModel();
        //            tblbooks.setModel(tableModel);
        //
        //            int columnCount = metaData.getColumnCount();
        //            for (int i = 1; i <= columnCount; i++) {
        //
        //                tableModel.addColumn(metaData.getColumnLabel(i));
        //            }
        //
        //            Object[] row = new Object[columnCount];
        //
        //            while (rs.next()) {
        //                for (int i = 0; i < columnCount; i++) {
        //                    row[i] = rs.getObject(i + 1);
        //                }
        //                tableModel.addRow(row);
        //            }
        //            rs.close();
        //            s.close();
        //            conn.close();
        //        } catch (Exception exc) {
        //            exc.printStackTrace();
        //
        //        }
        //
        //        try {
        //            Class.forName(driver).newInstance();
        //            Connection conn = DriverManager.getConnection(url, username, password);
        //            Statement s = conn.createStatement();
        //            String query = "SELECT * FROM student";
        //            ResultSet rs = s.executeQuery(query);
        //            ResultSetMetaData metaData = rs.getMetaData();
        //            int columnCount = metaData.getColumnCount();
        //            DefaultTableModel tableModelStudents = new DefaultTableModel();
        //            tblStudent.setModel(tableModelStudents);
        //
        //            for (int i = 1; i <= columnCount; i++) {
        //
        //                tableModelStudents.addColumn(metaData.getColumnLabel(i));
        //            }
        //            Object[] row = new Object[columnCount];
        //
        //            while (rs.next()) {
        //                for (int i = 0; i < columnCount; i++) {
        //                    row[i] = rs.getObject(i + 1);
        //                }
        //                tableModelStudents.addRow(row);
        //            }
        //            rs.close();
        //            s.close();
        //            conn.close();
        //        } catch (Exception exc) {
        //            exc.printStackTrace();
        //        }
    }

    public static void insert() {
//        try {
//
//            Connection conn = DriverManager.getConnection(url, username, password);
//            Statement s = conn.createStatement();
//            String insertQuery = "INSERT INTO Book (ISBN, Title, Author, Year, Edtion,Category,Publisher,Copies) "
//                    + "VALUES ('" + isbnBox.getText() + "', '"
//                    + ttlBox.getText() + "', '"
//                    + athrBox.getText() + "', '"
//                    + yearBox.getText() + "', '"
//                    + edBox.getText() + "', '"
//                    + catBox.getText() + "', '"
//                    + pubBox.getText() + "', '1')";
//            String updateQuery = "UPDATE book set Copies=copies+1 WHERE ISBN='" + isbnBox.getText() + "'";
//            String checkQuery = "SELECT * FROM book WHERE (ISBN='" + isbnBox.getText() + "');";
//            s.execute(checkQuery);
//            ResultSet rs = s.getResultSet();
//            boolean recordExists = rs.next();
//
//            if (recordExists) {
//                s.execute(updateQuery);
//            } else {
//                boolean result = s.execute(insertQuery);
//                clearFields();
//            }
//            dbConnection();
//
//            s.close();
//            conn.close();
//        } catch (SQLException exp) {
//            System.out.println("Error");
//            exp.printStackTrace();
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
}
