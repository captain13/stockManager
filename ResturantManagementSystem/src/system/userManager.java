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
    dbManager newManager = new dbManager();

    public void addUser(String username) {
        usernames.add(username);
    }

    public void createLogin() {
        loginSystem = new Login();
        loginSystem.setVisible(true);
    }

    public void createUserLog() {
        userLog = new UserForm(usernames);
        userLog.setVisible(true);
    }

    public void createLogout() {
        logoutSystem = new Logout(usernames);
        logoutSystem.setVisible(true);
    }

    public void loginAuthentication(String username, String password) {
        boolean login = newManager.login(username, password);
        if (login == true) {
            addUser(username);
            internalClock.setLoginTimeStamp();
            newManager.updateEmployeeStatusIn(username);
            loginSystem.disposeLogin();
        } else {
            JOptionPane.showMessageDialog(null, "Incorrect Username/Password");
        }
    }

    public void logout() {
        String username = Logout.getUsername();
        System.out.println(username);
        int rowIndex = Logout.getRowIndex();
        System.out.println(rowIndex);
        internalClock.setLogoutTimeStamp();
        newManager.updateHours(username, rowIndex);
        newManager.updateEmployeeStatusOut(username);
        usernames.remove(rowIndex);
        Logout.populateTable();
    }
}
