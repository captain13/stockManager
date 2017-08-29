/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package orderSystem;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Andrew
 */
public class networkHandler {

    Object columnNames[] = {"Table","Order", "Qty", "Time Stamp", "Status"};
    DefaultTableModel model = new DefaultTableModel(columnNames, 0);
    internalClock clock = new internalClock();

    public void recieveData(JTable table) {
        new Thread(() -> {
            while (true) {

                table.setModel(model);
                try {
                    Object[] row = null;
                    Socket socket = new Socket("10.0.0.169", 9999);
                    ObjectInputStream objectInput = new ObjectInputStream(socket.getInputStream());
                    Object[][] order = (Object[][]) objectInput.readObject();
                    for (int i = 0; i < order.length; i++) {
                        System.out.println(order[i][0]);
                    }
                    row = new Object[columnNames.length];

                    for (int i = 0; i < order.length; i++) {
                        row[0] = order[i][0];
                        row[1] = order[i][1];
                        row[2] = order[i][2];
                        row[3] = clock.setCurrentTimeStamp();
                        row[4] = "Not Ready";
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
}
