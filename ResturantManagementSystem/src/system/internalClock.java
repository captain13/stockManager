package system;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JLabel;

/**
 *
 * @author Andrew
 */
public class internalClock extends Thread {

    static ArrayList<String> loginTime = new ArrayList();
    static String logoutTime;
    String currentDate;

    public void internalClock(JLabel time, JLabel date) {
        new Thread(() -> {
            while (true) {
                time.setText(new SimpleDateFormat("HH:mm:ss").format(new Date()));
                date.setText(new SimpleDateFormat("dd/MM/yyyy").format(new Date()));
                
            }
        }).start();
    }

    public ArrayList getLoginTimeStamp() {
        return loginTime;
    }

    public String getCurrentTimeStamp() {
        String time = new SimpleDateFormat("HH:mm:ss").format(new Date());
        return time;
    }

    public String getCurrentDate() {
        currentDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        return currentDate;
    }

    public void setLogoutTimeStamp() {
        logoutTime = getCurrentTimeStamp();
    }

    public void setLoginTimeStamp() {
        loginTime.add(getCurrentTimeStamp());
    }

    public String calculateHours(int i, String previousHours) {
        String time;
        System.out.println(loginTime.get(i).toString());
        int hours;
        int minutes;
        String previousHoursArray[] = previousHours.split("hrs");
        String inArray[] = loginTime.get(i).split(":");
        String outArray[] = logoutTime.split(":");

        int prevHours = Integer.parseInt(previousHoursArray[0]);
        int prevMins = Integer.parseInt(previousHoursArray[1]);
        int inhours = Integer.parseInt(inArray[0]);
        int inminutes = Integer.parseInt(inArray[1]);
        int outhours = Integer.parseInt(outArray[0]);
        int outminutes = Integer.parseInt(outArray[1]);
        if (inminutes > outminutes) {
            hours = ((outhours - inhours) - 1) + prevHours;
            minutes = (60 - (outminutes - inminutes)) + prevMins;
        } else {
            hours = (outhours - inhours) + prevHours;
            minutes = (outminutes - inminutes) + prevMins;
            loginTime.remove(i);
        }

        return time = hours + "hrs" + minutes;
    }

}
