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
import static system.NewOrder.tblItems;

/**
 *
 * @author Andrew
 * @author Chadwick
 */
public class receiptHandler {

    String head;
    String body;
    String tail;
    internalClock clock = new internalClock();
    dbManager newManager = new dbManager();
    int n = 45;
    String stars = new String(new char[n]).replace("\0", "*");
    String spaces = new String(new char[n]).replace("\0", " "); 

    public void header() {
        head =  stars + "\n"
                + "COMPANY NAME " + " " + "\n"
                + clock.getCurrentTimeStamp() + " "
                + clock.getCurrentDate() + "\n"
                + stars + "\n";
        System.out.println(head);
    }

    public void body(double total) {
        body = spaces + "\n"
                //Need to change
                + "Items                                      Qty   Price"
                + " " + Arrays.deepToString(itemsList()).replaceAll("[^\\p{javaSpaceChar}{2.a-zA-Z0-9&,}]", "\n") + " "
                + "Total " +total + "\n"
                + "Amount........................\n";
        System.out.println(body);
    }

    public void tail() {
        tail =  spaces + "\n"
                + stars + "\n"
                + "THANK YOU\n"
                + "PLEASE COME AGAIN\n"
                + spaces + "\n"
                + "WAITER\n"
                + NewOrder.getWaiter() + "\n"
                + stars + "\n";
        System.out.println(tail);
    }
//    
//    public String[] spaces(String i, String q, String p) {
//        String[] str = new String[3];
//        str[0] = i;
//        str[1] = q;
//        str[2] = p;
//        for (int n = 0; n < 3; n++) {        
//            int len = str[n].length();
//            int spc = 30 - len;
//            str[n] = str[n] + "" + printS(spc);
//        }
//        return str;
//    }
    
    public String[][] itemsList() {
        int rowCount = tblItems.getRowCount();
        String[][] list = new String[rowCount][3];
        for (int i = 0; i < rowCount; i++) {
            for (int n = 0; n < 3; n++) {
                switch (n) {
                    case 0:
                        {
                            int len = tblItems.getValueAt(i, n).toString().length();
                            int spc = 30 - len;
                            list[i][n] = tblItems.getValueAt(i, n).toString() + "" + printS(spc);
                            break;
                        }
                    case 1:
                        {
                            int len = tblItems.getValueAt(i, n).toString().length();
                            int spc = 5 - len;
                            list[i][n] = tblItems.getValueAt(i, n).toString() + "" + printS(spc);
                            break;
                        }
                    case 2:
                        {
                            int len = tblItems.getValueAt(i, n).toString().length();
                            int spc = 5 - len;
                            list[i][n] = tblItems.getValueAt(i, n).toString() + "" + printS(spc);
                            break;
                        }
                    default:
                        break;
                }
            }
        }
        return list;
    }
    
    private String printS(int n){
        String spc = new String(new char[n]).replace("\0", " ");
        return spc;
    }

    public void printReceipt(double total) {
        header();
        body(total);
        tail();
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
