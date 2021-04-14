/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Hiba
 */
public class MyConnection {
     // sh vers la base 
     String url="jdbc:mysql://localhost:3306/tunlancer_bd";
    String login="root";
    String pwd="";
   
    Connection cnx;
    public static MyConnection instance; //singleton 
    // singleton constr privé
    private MyConnection() {
        try {
            //apl driver 
            cnx = DriverManager.getConnection(url, login, pwd);
            System.out.println("Connexion établie!");
        } catch (SQLException ex) {
            System.out.println("pas de connexion");
        }
    }

    public Connection getCnx() { //renvoi cnx etablie ou pas 
        return cnx;
    }
    
    //sigleton :creation d'une instance
    public static MyConnection getInstance(){
        if(instance == null){
            instance = new MyConnection();
        }
        return instance;
    }
    

    
}
