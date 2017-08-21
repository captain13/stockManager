package system;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Andrew
 */
public class internalClock extends Thread {

    static ArrayList<String> loginTime = new ArrayList();
    static String logoutTime;
    static String currentDate;

    public void internalClock() {
        new Thread(() -> {
            while (true) {
                MainSystem.getTime().setText(new SimpleDateFormat("HH:mm:ss").format(new Date()));
                MainSystem.getDate().setText(new SimpleDateFormat("dd/MM/yyyy").format(new Date()));
            }
        }).start();
    }

    public void setLoginTimeStamp() {
        loginTime.add(new SimpleDateFormat("HH:mm:ss").format(new Date()));
    }

    public ArrayList getLoginTimeStamp() {
        return loginTime;
    }

    public String getCurrentDate() {
        currentDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        return currentDate;
    }

    public void setLogoutTimeStamp() {
        logoutTime = new SimpleDateFormat("HH:mm:ss").format(new Date());
    }
    
     public String setCurrentTimeStamp() {
        String time = new SimpleDateFormat("HH:mm:ss").format(new Date());
        return time;
    }

    public String calculateHours(int i, String previousHours) {
        String time;

        int hours;
        int minutes;
        String previousHoursArray[] = previousHours.split(":");
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

        return time = hours + ":" + minutes + ":00";
    }

}
