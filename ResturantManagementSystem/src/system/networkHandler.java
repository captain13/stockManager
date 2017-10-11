/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package system;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Andrew
 */
public class networkHandler {

    Object columnNames[] = {"Table", "Order", "Qty", "Time Stamp", "Status"};
    DefaultTableModel model = new DefaultTableModel(columnNames, 0);
    internalClock clock = new internalClock();
    boolean IS_RUNNING = true;
    dbManager system = new dbManager();
    String IP="192.168.1.100";

    public final void getIP() {
        IP = JOptionPane.showInputDialog(null, "Enter Host IP address");
        IP="192.168.1.100";
        System.out.println(IP);
    }

    public void sendData(Object[][] order) {
        try (ServerSocket serverSocket = new ServerSocket(9999)) {
            serverSocket.setSoTimeout(5000);
            Socket socket = serverSocket.accept();
            ObjectOutputStream objectOutput = new ObjectOutputStream(socket.getOutputStream());
            objectOutput.writeObject(order);
            socket.close();
            serverSocket.close();
            JOptionPane.showMessageDialog(null, "Order Sent to Kitchen");
        } catch (SocketTimeoutException s) {
            JOptionPane.showMessageDialog(null,"Timed Out");
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Failed to Send Order");
        }
        
    }

    public void sendData() throws IOException {
        Object[][] recipeName = null;
        ServerSocket serverSocket = new ServerSocket(9999);
        serverSocket.setSoTimeout(5000);
        Socket skt = serverSocket.accept();

        try {
            recipeName = new String[system.getRecipeData().length][3];
            for (int i = 0; i < system.getRecipeData().length; i++) {

                recipeName[i][0] = "Test";
                recipeName[i][1] = system.getRecipeData()[i][1];
                recipeName[i][2] = system.getRecipeData()[i][2];
            }

            ObjectOutputStream objectOutput = new ObjectOutputStream(skt.getOutputStream());
            objectOutput.writeObject(recipeName);
            serverSocket.close();
        } catch (IOException e) {
        }

    }

    public void recieveData(JTable table) {
        new Thread(() -> {
            while (IS_RUNNING) {

                table.setModel(model);
                try {
                    Object[] row = null;
                    Socket socket = new Socket(IP, 9998);
                    ObjectInputStream objectInput = new ObjectInputStream(socket.getInputStream());
                    Object[][] order = (Object[][]) objectInput.readObject();

                    row = new Object[columnNames.length];

                    for (int i = 0; i < order.length; i++) {
                        row[0] = order[i][0];
                        row[1] = order[i][1];
                        row[2] = order[i][2];
                        row[3] = order[i][3];
                        row[4] = order[i][4];
                    }
                    model.addRow(row);
                    socket.close();
                } catch (ClassNotFoundException e) {
                    System.out.println("The title list has not come from the server");
                } catch (IOException ex) {
                }
            }
        }).start();
    }

    public void stopThread() {
        IS_RUNNING = false;
    }
}
