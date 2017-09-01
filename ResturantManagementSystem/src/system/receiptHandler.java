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
import java.util.*;

/**
 *
 * @author Andrew
 * @author Chadwick
 */
public class receiptHandler {

    String head;
    String body;
    String tail;

    public void header() {
        head = "********************************** \n"
                + "COMPANY NAME " + " " + "\n"
                + "TIME " + internalClock.setCurrentTimeStamp() + " "
                + internalClock.currentDate + " \n"
                + "********************************** ";

    }

    public void body() {
        body = "                                     \n"
                + "Item                   Qty Price \n"
                + " " + Arrays.deepToString(NewOrder.itemsList()).replaceAll("[^\\p{javaSpaceChar}{2,}a-zA-Z0-9&]", "\n") + " ";
    }

    public void tail() {
        tail =    "Total " + NewOrder.getTotal() + "\n"
                + "                                     \n"
                + "********************************** \n"
                + "THANK YOU PLEASE COME AGAIN        \n"
                + "WAITER " + NewOrder.getWaiter() + "  \n"
                + "********************************** ";
    }

    public void printReceipt() {
        //String whitespace_charclass = "["  + whitespace_chars + "]";
        header();
        body();
        tail();
        System.out.println(head);
        System.out.println(body);
//        for (String[] itemsList : NewOrder.itemsList()) {
//            for (String itemsList1 : itemsList) {
//                System.out.print(itemsList1 + " ");
//            }
//            System.out.println();
//        }
        System.out.println(tail);
    }
    
    public void writeTextToPDF() {
        String currentUsersHomeDir = System.getProperty("user.home");
        String file_name = currentUsersHomeDir + File.separator + "Documents\\NetBeansProjects\\stockManager\\ResturantManagementSystem\\src\\docs\\receiptTest.pdf";
        
        Document document = new Document();

        try {

            PdfWriter.getInstance(document, new FileOutputStream(new File(file_name)));

            //open
            document.open();
            
            Paragraph p = new Paragraph();
            p.add(head);
            p.add(body);
            p.add(tail);
            p.setAlignment(Element.ALIGN_CENTER);
            
            //adds text to doc
            document.add(p);
            
            //close
            document.close();
        } catch (FileNotFoundException | DocumentException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        //opens pdf
        if (Desktop.isDesktopSupported()) {
            try {
                File myFile = new File(file_name);
                Desktop.getDesktop().open(myFile);
            } catch (IOException ex) {
                ex.printStackTrace();
    }
}   
    }

    public static void main(String[] args) {      
//        receiptHandler newHandler = new receiptHandler();
//        
//        newHandler.writeTextToPDF();
//        newHandler.printReceipt();
    }

}
