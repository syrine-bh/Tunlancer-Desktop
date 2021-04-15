/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test1.Utils;

import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author asus
 */
public class MyConnection {
    final static String URL="jdbc:mysql://127.0.0.1:3306/tunlancer_bd";
    final static String LOGIN="root";
    final static String PWD="";
    static MyConnection instance=null;

    public static Statement createStatement() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public static PreparedStatement prepareStatement(String querry) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    private Connection cnx;
    
    
    private MyConnection()
    {
        try {
           cnx =(Connection) DriverManager.getConnection(URL,LOGIN,PWD);
            System.out.println("Connexion etablie avec succès");
        }
        catch (SQLException ex) {
            System.out.println("Connexion echoué");
            
        }
 
    }
    public static MyConnection getInstance(){
   
        if(instance ==null) {
            instance= new MyConnection();
        }
        return instance;
    }
    public Connection getConnection(){
        return cnx;
    }
  
}
