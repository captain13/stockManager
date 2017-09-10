/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package system;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author user1
 */
public class logSystem {

    String fileName =  ".\\src\\logs\\logs.txt";
    internalClock clock = new internalClock();
    File file = new File(fileName);

    public void logValidation() {
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

    public void getLogs() {
        Runtime runtime = Runtime.getRuntime();
        try {
            Process p = runtime.exec("notepad " + file);
        } catch (IOException ex) {
        }
    }

    public void writeLogs(String action) {
        String text = clock.getCurrentDate() + " " + clock.getCurrentTimeStamp() + " record " + action + " to database";
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

    public void refreshLogs() {
        String text = null;
        try {
            FileWriter fileWriter = new FileWriter(file, false);
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }

}
