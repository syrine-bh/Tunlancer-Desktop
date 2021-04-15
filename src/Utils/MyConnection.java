/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author siwar
 */
public class MyConnection {
    final static String url="jdbc:mysql://localhost:3306/tunlancer_bd";
   final static String login = "root";
   final static String pwd = "";
   static MyConnection instance = null;
   private Connection cnx;
 public MyConnection() {
     
        try {
            cnx = DriverManager.getConnection(url, login, pwd);
            System.out.println("Connexion établie");
        } catch (SQLException ex) {
            System.out.println("pas de connexion établie");
        }
    }
  public static MyConnection getInstance() {
        if (instance == null) {
            instance = new MyConnection();
        }
        return instance;
    }
   public Connection getConnection() {
        return cnx;
    }
    
}
