/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package system;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author zanesmith
 */
public class DatabaseEdits {

    static class refresh {

        public refresh() throws SQLException {
            Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql?zeroDateTimeBehavior=convertToNull&autoReconnect=true&useSSL=false", "root", "");
            Statement myStmt = myConn.createStatement();
            ResultSet myRs = null;

            try {

                // 3. Execute SQL query
                myStmt.executeUpdate("CREATE DATABASE if not exists restaurant");
                myStmt.executeUpdate("use restaurant");
                myStmt.executeUpdate("drop table if exists books;");
                myStmt.executeUpdate("drop table if exists student;");
                myStmt.executeUpdate("drop table if exists bookings;");
                String myTableName = "CREATE TABLE books ("
                        + "id INT(64) NOT NULL AUTO_INCREMENT,"
                        + "title VARCHAR(75),"
                        + "isbn VARCHAR(64),"
                        + "author VARCHAR(75),"
                        + "year INT(4),"
                        + "edition INT(4),"
                        + "catagory VARCHAR(75),"
                        + "publisher VARCHAR(75),"
                        + "stock INT(64),PRIMARY KEY (id))";
                myStmt.executeUpdate(myTableName);
                myTableName = "CREATE TABLE student ("
                        + "id INT(64) NOT NULL AUTO_INCREMENT,"
                        + "title VARCHAR(75),"
                        + "snumber INT(10),"
                        + "name VARCHAR(75),"
                        + "surname VARCHAR(75),"
                        + "cell INT(20),"
                        + "address VARCHAR(75),PRIMARY KEY (id))";
                myStmt.executeUpdate(myTableName);
                myTableName = "CREATE TABLE bookings ("
                        + "id INT(64) NOT NULL AUTO_INCREMENT,"
                        + "bdate Date,"
                        + "isbn VARCHAR(64),"
                        + "btitle VARCHAR(75),"
                        + "name VARCHAR(75),"
                        + "surname VARCHAR(75),"
                        + "returndate DATE,PRIMARY KEY (id))";
                myStmt.executeUpdate(myTableName);
            } catch (Exception exc) {
                exc.printStackTrace();
            } finally {

                if (myStmt != null) {
                    myStmt.close();
                }

                if (myConn != null) {
                    myConn.close();
                }
            }
        }
    }
    
}
