/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package system;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import javax.swing.JTable;

/**
 *
 * @author Andrew
 */
public class networkHandler {

    dbManager system = new dbManager();

    public void sendData(JTable table, String tableNum) throws IOException {
        String[][] recipeName = null;
        ServerSocket myServerSocket = new ServerSocket(9999);
        Socket skt = myServerSocket.accept();

        try {
            recipeName = new String[table.getRowCount()][3];
            for (int i = 0; i < table.getRowCount(); i++) {
                
                recipeName[i][0]=tableNum;
                recipeName[i][1] = (table.getValueAt(i, 0).toString());
                recipeName[i][2] = (table.getValueAt(i, 1).toString());
            }

            ObjectOutputStream objectOutput = new ObjectOutputStream(skt.getOutputStream());
            objectOutput.writeObject(recipeName);
            myServerSocket.close();
        } catch (IOException e) {
        }
    }

    public void sendData() throws IOException {
        Object[][] recipeName = null;
        ServerSocket myServerSocket = new ServerSocket(9999);
        Socket skt = myServerSocket.accept();

        try {

            for (int i = 0; i < system.getRecipe().length; i++) {
                recipeName = new String[system.getRecipe().length][3];
                recipeName[i][0] = "Null";
                recipeName[i][1] = system.getRecipe()[i][1];
                recipeName[i][2] = system.getRecipe()[i][2];
//                 System.out.println(recipeName[i][0]);
//                  System.out.println(recipeName[i][1]);
            }

            ObjectOutputStream objectOutput = new ObjectOutputStream(skt.getOutputStream());
            objectOutput.writeObject(recipeName);
            myServerSocket.close();
        } catch (IOException e) {
        }
    }
}
