/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import java.sql.Connection;
import utils.MyConnection;
/**
 *
 * @author Hiba
 */
public class UIService {
    Connection cnx;
    private static String projectPath = System.getProperty("user.dir").replace("\\", "/");

    public UIService() {
        cnx = MyConnection.getInstance().getConnection();
    }
}
