/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package system;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextArea;

/**
 *
 * @author user1
 */
public class logSystem {

    String fileName;
    internalClock clock = new internalClock();
    File file;

    public File getFileName() {
        String currentDate = clock.getCurrentDate();
        String date[] = currentDate.split("-");
        int year = Integer.parseInt(date[0]);
        int month = Integer.parseInt(date[1]);
        String monthName = "logs";

        switch (month) {
            case 1:
                monthName = "January";
                break;
            case 2:
                monthName = "February";
                break;
            case 3:
                monthName = "March";
                break;
            case 4:
                monthName = "April";
                break;
            case 5:
                monthName = "May";
                break;

            case 6:
                monthName = "June";
                break;
            case 7:
                monthName = "July";
                break;

            case 8:
                monthName = "August";
                break;

            case 9:
                monthName = "September";
                break;
            case 10:
                monthName = "October";
                break;

            case 11:
                monthName = "November";
                break;

            case 12:
                monthName = "December";
                break;
        }
        fileName = ".\\src\\logs\\" + monthName + year + ".txt";
        return file = new File(fileName);
    }

    public void logValidation() {
        getFileName();
        boolean exists = file.exists();
        if (exists) {
            System.out.println("Log file exisits");
        } else {
            try {
                file.createNewFile();
                System.out.println("Log File created");
            } catch (IOException ex) {
                Logger.getLogger(logSystem.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

//    public void getLogs() {
//        Runtime runtime = Runtime.getRuntime();
//        try {
//            Process p = runtime.exec("notepad " + file);
//        } catch (IOException ex) {
//        }
//    }
    public void writeLogs(String action, String table) {
        getFileName();
        String text = clock.getCurrentDate() + " " + clock.getCurrentTimeStamp() + " record " + action + " to " + table + " table";
        try {
            FileWriter fileWriter = new FileWriter(file, true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            bufferedWriter.write(text);
            bufferedWriter.newLine();
            bufferedWriter.close();

        } catch (IOException ex) {
            System.out.println(ex);
        }
    }

    public final void readLogs(JTextArea TextArea) {
        getFileName();
        try {
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            TextArea.read(bufferedReader, null);
            int length = TextArea.getDocument().getLength();
            TextArea.setCaretPosition(length);
            bufferedReader.close();

        } catch (IOException ex) {
            System.out.println(ex);
        }
    }

    public void refreshLogs() {
        getFileName();
        String text = null;
        try {
            FileWriter fileWriter = new FileWriter(file, false);
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }

}
