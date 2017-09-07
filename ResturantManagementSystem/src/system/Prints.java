/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package system;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Desktop;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;

/**
 *
 * @author Tumi
 */
public class Prints {

    String inventory;
    String Heading;
    String name;
    dbManager db = new dbManager();

    public Prints() {
        this.name = "Inventory Table      " + "\n" + "\n";
        this.inventory = Arrays.deepToString(db.getInventoryData());
        this.Heading = "Inventory ID    Item Name    Category    Quantity(g)    Item Threshold    Item Limit \n " + "\n";
    }

    public void printTextToPDF() {
        String currentUsersHomeDir = System.getProperty("user.home");
        String file_name = currentUsersHomeDir + File.separator + "Documents\\NetBeansProjects\\stockManager\\ResturantManagementSystem\\src\\docs\\Print.pdf";
        Document document = new Document();
        try {
            PdfWriter.getInstance(document, new FileOutputStream(new File(file_name)));
            //open
            document.open();
            Paragraph p = new Paragraph();
            p.add(name);
            p.add(Heading);
            p.add(inventory);

            p.setAlignment(Element.ALIGN_JUSTIFIED);
            //adds text to d
            document.add(p);
            //close
            document.close();
        } catch (FileNotFoundException | DocumentException e) {
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
