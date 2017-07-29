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

    public static void internalClock() {
        new Thread(() -> {
            while (true) {
                MainSystem.getTime().setText(new SimpleDateFormat("hh:mm:ss").format(new Date()));
                MainSystem.getDate().setText(new SimpleDateFormat("dd/MM/yyyy").format(new Date()));
            }
        }).start();
    }

    public static void setLoginTimeStamp() {
        loginTime.add(new SimpleDateFormat("hh:mm:ss").format(new Date()));
    }
    
    public static ArrayList getLoginTimeStamp() {
        return loginTime;
    }

    public static void setLogoutTimeStamp() {
        logoutTime = new SimpleDateFormat("hh:mm:ss").format(new Date());
    }

    public static String calculateHours(int i, String previousHours) {
        String time;
        String previousHoursArray[] = previousHours.split(":");
        String inArray[] = loginTime.get(i).split(":");
        String outArray[] = logoutTime.split(":");
        int prevHours = Integer.parseInt(previousHoursArray[0]);
        int prevMins = Integer.parseInt(previousHoursArray[1]);
        int inhours = Integer.parseInt(inArray[0]);
        int inminutes = Integer.parseInt(inArray[1]);
        int outhours = Integer.parseInt(outArray[0]);
        int outminutes = Integer.parseInt(outArray[1]);
        int hours = (outhours - inhours) + prevHours;
        int minutes = (outminutes - inminutes) + prevMins;
        loginTime.remove(i);
        return time = hours + ":" + minutes+":00";
    }

}