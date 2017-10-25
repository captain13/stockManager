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
    String stars = new String(new char[54]).replace("\0", "*");
    String spaces = new String(new char[54]).replace("\0", " "); 

    public void header() {
        head =  stars + "\n"
                + "COMPANY NAME " + " " + "\n"
                + clock.getCurrentTimeStamp() + " "
                + clock.getCurrentDate() + "\n"
                + stars + "\n";
//        System.out.println(head);
    }

    public void body(double total) {
        body = spaces + "\n"
                + "Items                                        Qty   Price\n"            
                + itemsList() +"\n"
                + "Total " + total + "\n" 
                + "Amount.............................................\n"
                + "Tip................................................\n";
//        System.out.println(body);
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
//        System.out.println(tail);
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
                            int spc = 45 - len;
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
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(new File(file_name)));
            Rectangle one = new Rectangle(300,700);
            document.setPageSize(one);
            document.setMargins(2, 2, 2, 2);
            //open
            document.open();
            writer.setSpaceCharRatio(PdfWriter.NO_SPACE_CHAR_RATIO);
            writer.setSpaceCharRatio(PdfWriter.SPACE_CHAR_RATIO_DEFAULT);
            Paragraph p = new Paragraph();
            p.setAlignment(Element.ALIGN_CENTER);
            p.setIndentationLeft(20);
            p.setIndentationRight(20);
            p.add(head);
            p.add(body);
            p.add(tail);
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
    
    public void writeTextToPDF(String receipt) {
        String currentUsersHomeDir = System.getProperty("user.home");
        String file_name = currentUsersHomeDir + File.separator + "Documents\\NetBeansProjects\\stockManager\\ResturantManagementSystem\\src\\docs\\reprintReceiptTest.pdf";
        Document document = new Document();
        try {
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(new File(file_name)));
            Rectangle one = new Rectangle(300,700);
            document.setPageSize(one);
            document.setMargins(2, 2, 2, 2);
            //open
            document.open();
            writer.setSpaceCharRatio(PdfWriter.NO_SPACE_CHAR_RATIO);
            writer.setSpaceCharRatio(PdfWriter.SPACE_CHAR_RATIO_DEFAULT);
            Paragraph p = new Paragraph();
            p.setAlignment(Element.ALIGN_CENTER);
            p.setIndentationLeft(20);
            p.setIndentationRight(20);
            p.add(receipt);
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
