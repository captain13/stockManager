/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package orderSystem;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
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
    String IP;

    public networkHandler() {
        getIP();
    }

    public final void getIP() {
        IP = JOptionPane.showInputDialog(null, "Enter Host IP address");
    }

    public void recieveData(JTable table) {
        new Thread(() -> {
            while (IS_RUNNING) {

                table.setModel(model);
                try {
                    Object[] row = null;
                    Socket socket = new Socket(IP, 9999);
                    ObjectInputStream objectInput = new ObjectInputStream(socket.getInputStream());
                    Object[][] order = (Object[][]) objectInput.readObject();
                    row = new Object[columnNames.length];

                    for (int i = 0; i < order.length; i++) {
                        if (order[i][1] != (null)) {
                            row[0] = order[i][0];
                            row[1] = order[i][1];
                            row[2] = order[i][2];
                            row[3] = clock.setCurrentTimeStamp();
                            row[4] = "Not Ready";
                            model.addRow(row);
                        }
                    }

                    socket.close();
                } catch (ClassNotFoundException e) {
                    System.out.println("The title list has not come from the server");
                } catch (SocketTimeoutException s) {
                    JOptionPane.showMessageDialog(null, "Timed Out");
                } catch (IOException ex) {
                }
            }
        }).start();
    }

    public void sendData(JTable table) {

        try {
            String[][] recipeName = null;
            ServerSocket serverSocket = new ServerSocket(9998);
            serverSocket.setSoTimeout(5000);
            Socket skt = serverSocket.accept();

            recipeName = new String[table.getRowCount()][5];
            for (int i = 0; i < table.getRowCount(); i++) {

                recipeName[i][0] = (table.getValueAt(i, 0).toString());
                recipeName[i][1] = (table.getValueAt(i, 1).toString());
                recipeName[i][2] = (table.getValueAt(i, 2).toString());
                recipeName[i][3] = (table.getValueAt(i, 3).toString());
                recipeName[i][4] = (table.getValueAt(i, 4).toString());
            }

            ObjectOutputStream objectOutput = new ObjectOutputStream(skt.getOutputStream());
            objectOutput.writeObject(recipeName);
            skt.close();
            serverSocket.close();
        } catch (IOException e) {
        }
    }

    public void stopThread() {
        IS_RUNNING = false;
    }
}
