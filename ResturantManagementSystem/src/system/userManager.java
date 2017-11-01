package system;

import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Andrew
 */
public class userManager {

    static ArrayList<String> usernames = new ArrayList();
    static Login loginSystem;
    Logout logoutSystem;
    dbManager newManager = new dbManager();
    internalClock clock = new internalClock();
    LoginForm newform;

    public void addUser(String username) {
        usernames.add(username);
    }

    public void createLogin() {
        loginSystem = new Login();
        loginSystem.setVisible(true);
    }

    public String createUserLog() {
        newform = new LoginForm(null, true, usernames);
        newform.setVisible(true);
        return newform.getUsername();
    }

    public boolean createAdminLogin() {
        LoginAdmin newlogAdmin = new LoginAdmin(null, true);
        newlogAdmin.setVisible(true);
        return newlogAdmin.getStatus();
    }

    public void createLogout() {
        logoutSystem = new Logout(usernames);
        logoutSystem.setVisible(true);
    }

    public boolean loginAuthentication(String username, String password) {
        boolean loggedin = false;
        boolean login = false;
        for (int j = 0; j < newManager.getEmployeeData().length; j++) {
            if (login == false) {
                if (username.equals(newManager.getEmployeeData()[j][1])
                        && password.equals(newManager.getEmployeeData()[j][6])) {
                    if (newManager.getEmployeeData()[j][7].equals("Active")) {
                        JOptionPane.showMessageDialog(null, "Already Logged In");
                        loggedin = true;
                    } else {
                        login = true;
                    }

                } else {
                    login = false;
                }
            }
        }
        if (loggedin == false) {
            if (login == true) {
                addUser(username);
                clock.setLoginTimeStamp();
                newManager.updateEmployeeStatusIn(username);
                loginSystem.disposeLogin();
                JOptionPane.showMessageDialog(null, "Logged In");
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "Incorrect Username/Password");
                return false;
            }
        }
        return login;
    }

    public void logout() {
        String username = Logout.getUsername();
        int rowIndex = Logout.getRowIndex();
        clock.setLogoutTimeStamp();
        newManager.updateHours(username, rowIndex);
        newManager.updateEmployeeStatusOut(username);
        usernames.remove(rowIndex);
    }

    public void logoutAll() {
        for (int i = 0; i < usernames.size(); i++) {
            clock.setLogoutTimeStamp();
            newManager.updateHours(usernames.get(i),0);
        }
        newManager.loggoutAllEmployee();
    }
}
