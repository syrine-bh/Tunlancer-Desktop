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
 * @author Cyrina
 */
public class MaConnexion {
    
    private static MaConnexion instance;
    private Connection cnx;
    private final String URL = "jdbc:mysql://localhost:3306/tunlancer_bd";
    private final String LOGIN = "root";
    private final String PASSWORD = "";

    private MaConnexion() {
        try {
            cnx = DriverManager.getConnection(URL, LOGIN, PASSWORD); 
            System.out.println("Connecting !");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public static MaConnexion getInstance() {
        if (instance == null) {
            instance = new MaConnexion();
        }
        return instance;
    }

    public Connection getCnx() {
        return cnx;
    }
    
    
}
