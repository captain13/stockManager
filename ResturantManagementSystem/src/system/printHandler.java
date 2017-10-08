/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package system;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Desktop;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Tumi
 */
public class printHandler {

    String inventory;
    String Heading;
    String name;
    dbManager db = new dbManager();

    public void printTextToPDF() throws ClassNotFoundException, InstantiationException, SQLException {
        name = "Inventory Table " + "\n" + "\n";
        inventory = Arrays.deepToString(db.getInventoryData()).replaceAll("[^\\p{javaSpaceChar}{2.a-zA-Z0-9&,}]", "\n") + "";
        Heading = "Inventory ID, Item Name, Category, Quantity(g), Item Threshold, Item Limit \n ";
        String currentUsersHomeDir = System.getProperty("user.home");
        String file_name = currentUsersHomeDir + File.separator + "Documents\\NetBeansProjects\\stockManager\\ResturantManagementSystem\\src\\docs\\Print.pdf";
        String url = "jdbc:mysql://localhost:3306/resturantdb";
        String username = "root";
        String password = "root";
        String driver = "com.mysql.jdbc.Driver";

        Document document = new Document();
        try {
            PdfWriter.getInstance(document, new FileOutputStream(new File(file_name)));
            //open
            document.open();
            Paragraph p = new Paragraph();
            p.add(name);
            p.add(Heading);
            p.add(inventory);
            PdfPTable table = new PdfPTable(7);
            PdfPCell table_cell;

            Class.forName(driver).newInstance();
            Connection conn = DriverManager.getConnection(url, username, password);
            Statement s = conn.createStatement();
            ResultSet query = s.executeQuery("SELECT inventoryID, item, category, qty, itemThreshold, itemLimit, itemCost FROM inventory");

            table.addCell((new Paragraph("Inventory ID")));
            table.addCell((new Paragraph("Item")));
            table.addCell((new Paragraph("Category")));
            table.addCell((new Paragraph("Quantity")));
            table.addCell((new Paragraph("Item Threshold")));
            table.addCell((new Paragraph("Item Limit")));
            table.addCell((new Paragraph("Item Cost")));

            while (query.next()) {

                String InventoryID = query.getString("inventoryID");
                table_cell = new PdfPCell(new Phrase(InventoryID));
                table.addCell(table_cell);
                String item = query.getString("item");
                table_cell = new PdfPCell(new Phrase(item));
                table.addCell(table_cell);
                String category = query.getString("category");
                table_cell = new PdfPCell(new Phrase(category));
                table.addCell(table_cell);
                String qty = query.getString("qty");
                table_cell = new PdfPCell(new Phrase(qty));
                table.addCell(table_cell);
                String itemThreshold = query.getString("itemThreshold");
                table_cell = new PdfPCell(new Phrase(itemThreshold));
                table.addCell(table_cell);
                String itemLimit = query.getString("itemLimit");
                table_cell = new PdfPCell(new Phrase(itemLimit));
                table.addCell(table_cell);
                String itemCost = query.getString("itemCost");
                table_cell = new PdfPCell(new Phrase(itemCost));
                table.addCell(table_cell);
            }

            document.add(table);
            document.close();
            query.close();
            s.close();
            conn.close();

            JOptionPane.showMessageDialog(null, "Your table has been saved to PDF");
        } catch (FileNotFoundException | DocumentException e) {
        } catch (IllegalAccessException ex) {
            Logger.getLogger(printHandler.class.getName()).log(Level.SEVERE, null, ex);
        }

        //opens pdf
        if (Desktop.isDesktopSupported()) {
            try {
                File myFile = new File(file_name);
                Desktop.getDesktop().open(myFile);
            } catch (IOException ex) {
            }
        }
    }
}
