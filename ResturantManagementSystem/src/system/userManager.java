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
        LoginForm newform = new LoginForm(null, true, usernames);
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
        boolean login = newManager.login(username, password);
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

    public void logout() {
        String username = Logout.getUsername();
        int rowIndex = Logout.getRowIndex();
        clock.setLogoutTimeStamp();
        newManager.updateHours(username, rowIndex);
        newManager.updateEmployeeStatusOut(username);
        usernames.remove(rowIndex);
    }
}
