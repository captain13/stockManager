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
    //DayEndSalesForm des = new DayEndSalesForm();
    String file_name = ".\\src\\docs\\Print.pdf";

    public void printEODreport(DayEndSalesForm des) {
        Document document = new Document();
        try {
            PdfWriter.getInstance(document, new FileOutputStream(new File(file_name)));
            document.open();
            PdfPTable table = new PdfPTable(2);
            PdfPCell table_cell;

            table_cell = new PdfPCell(new Phrase("Total Sale"));
            table.addCell(table_cell);

            table_cell = new PdfPCell(new Phrase(des.getSales()));
            table.addCell(table_cell);

            table_cell = new PdfPCell(new Phrase("Cash"));
            table.addCell(table_cell);

            table_cell = new PdfPCell(new Phrase(des.getCash()));
            table.addCell(table_cell);

            table_cell = new PdfPCell(new Phrase("Credit"));
            table.addCell(table_cell);

            table_cell = new PdfPCell(new Phrase(des.getCredit()));
            table.addCell(table_cell);

            table_cell = new PdfPCell(new Phrase("VAT"));
            table.addCell(table_cell);

            table_cell = new PdfPCell(new Phrase(des.getVAT()));
            table.addCell(table_cell);

            table_cell = new PdfPCell(new Phrase("Total Expenses"));
            table.addCell(table_cell);

            table_cell = new PdfPCell(new Phrase(des.getExpenses()));
            table.addCell(table_cell);

            table_cell = new PdfPCell(new Phrase("Wages"));
            table.addCell(table_cell);

            table_cell = new PdfPCell(new Phrase(des.getWages()));
            table.addCell(table_cell);

            table_cell = new PdfPCell(new Phrase("Stock Orders"));
            table.addCell(table_cell);

            table_cell = new PdfPCell(new Phrase(des.getStockOrder()));
            table.addCell(table_cell);

            table_cell = new PdfPCell(new Phrase("Gross Income"));
            table.addCell(table_cell);

            table_cell = new PdfPCell(new Phrase(des.getTotal()));
            table.addCell(table_cell);

            document.add(table);
            document.close();
            JOptionPane.showMessageDialog(null, "Your table has been saved to PDF");
        } catch (FileNotFoundException | DocumentException e) {
        }

        if (Desktop.isDesktopSupported()) {
            try {
                File myFile = new File(file_name);
                Desktop.getDesktop().open(myFile);
            } catch (IOException ex) {
            }
        }
    }

    public void printInventory() {
        Document document = new Document();
        try {
            PdfWriter.getInstance(document, new FileOutputStream(new File(file_name)));
            //open
            document.open();
            PdfPTable table = new PdfPTable(7);
            PdfPCell table_cell;
            table.addCell((new Paragraph("Inventory ID")));
            table.addCell((new Paragraph("Item")));
            table.addCell((new Paragraph("Category")));
            table.addCell((new Paragraph("Quantity")));
            table.addCell((new Paragraph("Item Threshold")));
            table.addCell((new Paragraph("Item Limit")));
            table.addCell((new Paragraph("Item Cost")));
            for (int i = 0; i < db.getInventoryData().length; i++) {
                String InventoryID = db.getInventoryData()[i][0].toString();
                table_cell = new PdfPCell(new Phrase(InventoryID));
                table.addCell(table_cell);
                String item = db.getInventoryData()[i][1].toString();
                table_cell = new PdfPCell(new Phrase(item));
                table.addCell(table_cell);
                String category = db.getInventoryData()[i][2].toString();
                table_cell = new PdfPCell(new Phrase(category));
                table.addCell(table_cell);
                String qty = db.getInventoryData()[i][3].toString();
                table_cell = new PdfPCell(new Phrase(qty));
                table.addCell(table_cell);
                String itemThreshold = db.getInventoryData()[i][4].toString();
                table_cell = new PdfPCell(new Phrase(itemThreshold));
                table.addCell(table_cell);
                String itemLimit = db.getInventoryData()[i][5].toString();
                table_cell = new PdfPCell(new Phrase(itemLimit));
                table.addCell(table_cell);
                String itemCost = db.getInventoryData()[i][6].toString();
                table_cell = new PdfPCell(new Phrase(itemCost));
                table.addCell(table_cell);
            }
            document.add(table);
            document.close();
            JOptionPane.showMessageDialog(null, "Your table has been saved to PDF");
        } catch (FileNotFoundException | DocumentException e) {
        }
        if (Desktop.isDesktopSupported()) {
            try {
                File myFile = new File(file_name);
                Desktop.getDesktop().open(myFile);
            } catch (IOException ex) {
            }
        }
    }

    public void printRecipe() {
        Document document = new Document();
        try {
            PdfWriter.getInstance(document, new FileOutputStream(new File(file_name)));
            //open
            document.open();
            PdfPTable table = new PdfPTable(5);
            PdfPCell table_cell;
            table.addCell((new Paragraph("ID")));
            table.addCell((new Paragraph("Name")));
            table.addCell((new Paragraph("Price")));
            table.addCell((new Paragraph("VAT")));
            table.addCell((new Paragraph("Type")));

            for (int i = 0; i < db.getRecipeData().length; i++) {
                String InventoryID = db.getRecipeData()[i][0].toString();
                table_cell = new PdfPCell(new Phrase(InventoryID));
                table.addCell(table_cell);
                String item = db.getRecipeData()[i][1].toString();
                table_cell = new PdfPCell(new Phrase(item));
                table.addCell(table_cell);
                String category = db.getRecipeData()[i][2].toString();
                table_cell = new PdfPCell(new Phrase(category));
                table.addCell(table_cell);
                String qty = db.getRecipeData()[i][3].toString();
                table_cell = new PdfPCell(new Phrase(qty));
                table.addCell(table_cell);
                String itemThreshold = db.getRecipeData()[i][4].toString();
                table_cell = new PdfPCell(new Phrase(itemThreshold));
                table.addCell(table_cell);
            }
            document.add(table);
            document.close();
            JOptionPane.showMessageDialog(null, "Your table has been saved to PDF");
        } catch (FileNotFoundException | DocumentException e) {
        }
        if (Desktop.isDesktopSupported()) {
            try {
                File myFile = new File(file_name);
                Desktop.getDesktop().open(myFile);
            } catch (IOException ex) {
            }
        }
    }

    public void printSupplier() {
        Document document = new Document();
        try {
            PdfWriter.getInstance(document, new FileOutputStream(new File(file_name)));
            //open
            document.open();
            PdfPTable table = new PdfPTable(5);
            PdfPCell table_cell;
            table.addCell((new Paragraph("ID")));
            table.addCell((new Paragraph("Supplier Name")));
            table.addCell((new Paragraph("Email")));
            table.addCell((new Paragraph("Contact Number")));
            table.addCell((new Paragraph("Address")));

            for (int i = 0; i < db.getSuppleirData().length; i++) {
                String InventoryID = db.getSuppleirData()[i][0].toString();
                table_cell = new PdfPCell(new Phrase(InventoryID));
                table.addCell(table_cell);
                String item = db.getSuppleirData()[i][1].toString();
                table_cell = new PdfPCell(new Phrase(item));
                table.addCell(table_cell);
                String category = db.getSuppleirData()[i][2].toString();
                table_cell = new PdfPCell(new Phrase(category));
                table.addCell(table_cell);
                String qty = db.getSuppleirData()[i][3].toString();
                table_cell = new PdfPCell(new Phrase(qty));
                table.addCell(table_cell);
                String itemThreshold = db.getSuppleirData()[i][4].toString();
                table_cell = new PdfPCell(new Phrase(itemThreshold));
                table.addCell(table_cell);
            }
            document.add(table);
            document.close();
            JOptionPane.showMessageDialog(null, "Your table has been saved to PDF");
        } catch (FileNotFoundException | DocumentException e) {
        }
        if (Desktop.isDesktopSupported()) {
            try {
                File myFile = new File(file_name);
                Desktop.getDesktop().open(myFile);
            } catch (IOException ex) {
            }
        }
    }

    public void printUserManual() {
        try {
            File myFile = new File("UserManual.pdf");
            Desktop.getDesktop().open(myFile);
        } catch (IOException ex) {
        }
    }
}
