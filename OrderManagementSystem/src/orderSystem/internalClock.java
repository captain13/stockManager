package orderSystem;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Andrew
 */
public class internalClock {

    boolean IS_RUNNING = true;

    public String setCurrentTimeStamp() {
        String time = new SimpleDateFormat("HH:mm:ss").format(new Date());
        return time;
    }

    public void internalClock() {
        new Thread(() -> {
            while (IS_RUNNING) {
                OrderSystem.getTime().setText(new SimpleDateFormat("HH:mm:ss").format(new Date()));
            }
        }).start();
    }

    public void stopThread() {
        IS_RUNNING = false;
    }
}
