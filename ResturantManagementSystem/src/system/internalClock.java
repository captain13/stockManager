package system;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Andrew
 */
public class internalClock extends Thread {

    static String loginTime;
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
        loginTime = new SimpleDateFormat("hh:mm:ss").format(new Date());
    }

    public static void setLogoutTimeStamp() {
        logoutTime = new SimpleDateFormat("hh:mm:ss").format(new Date());
    }

    public static String calculateHours() {
        String time;
        String inArray[] = loginTime.split(":");
        String outArray[] = logoutTime.split(":");
        int inhours = Integer.parseInt(inArray[0]);
        int inminutes = Integer.parseInt(inArray[1]);
        int outhours = Integer.parseInt(outArray[0]);
        int outminutes = Integer.parseInt(outArray[1]);
        int hours = outhours - inhours;
        int minutes = outminutes - inminutes;
        return time = hours + "hrs" + minutes + "mins";
    }

}
