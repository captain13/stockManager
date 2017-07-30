package system;

import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Andrew
 */
public class userManager {

    static ArrayList usernames = new ArrayList();
    static Login loginSystem;
    static Logout logoutSystem;
    static UserForm userLog;

    public static void addUser(String username) {
        usernames.add(username);
    }

    public static void createLogin() {
        loginSystem = new Login();
        loginSystem.setVisible(true);
    }

    public static void createUserLog() {
        userLog = new UserForm(usernames);
        userLog.setVisible(true);
    }

    public static void createLogout() {
        logoutSystem = new Logout(usernames);
        logoutSystem.setVisible(true);
    }

    public static void loginAuthentication(String username, String password) {
        boolean login = dbManager.login(username, password);
        if (login == true) {
            addUser(username);
            internalClock.setLoginTimeStamp();
            dbManager.updateEmployeeStatusIn(username);
            loginSystem.disposeLogin();
        } else {
            JOptionPane.showMessageDialog(null, "Incorrect Username/Password");
        }
    }

    public static void logout() {
        String username = Logout.getUsername();
        System.out.println(username);
        int rowIndex = Logout.getRowIndex();
        System.out.println(rowIndex);
        internalClock.setLogoutTimeStamp();
        dbManager.updateHours(username, rowIndex);
        dbManager.updateEmployeeStatusOut(username);
        usernames.remove(rowIndex);
        Logout.populateTable();
    }
}
