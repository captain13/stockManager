package system;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
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
    StringBuilder items = new StringBuilder();
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
                + "Items                                    Qty   Price\n"            
                + itemsList() +"\n"
                + "Total " + total + "\n" 
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
    
    public StringBuilder itemsList() {
        int rowCount = tblItems.getRowCount();
        items.setLength(0);
        for (int i = 0; i < rowCount; i++) {
            for (int n = 0; n < 3; n++) {
                switch (n) {
                    case 0:
                        {
                            int len = tblItems.getValueAt(i, n).toString().length();
                            int spc = 30 - len;
                            items.append(tblItems.getValueAt(i, n).toString()).append(printS(spc));
                            break;
                        }
                    case 1:
                        {
                            int len = tblItems.getValueAt(i, n).toString().length();
                            int spc = 5 - len;
                            items.append(tblItems.getValueAt(i, n).toString()).append(printS(spc));
                            break;
                        }
                    case 2:
                        {
                            int len = tblItems.getValueAt(i, n).toString().length();
                            int spc = 5 - len;
                            items.append(tblItems.getValueAt(i, n).toString()).append(printS(spc));
                            break;
                        }
                    default:
                        break;
                }
            }
            items.append("\n");
        }
        return items;
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
    
    public String getReceipt() {
       return head+body+tail;
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
