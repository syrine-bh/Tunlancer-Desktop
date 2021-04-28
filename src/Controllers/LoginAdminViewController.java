/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import Models.Crypt;
import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javax.swing.JOptionPane;
import utils.MyConnection;

/**
 * FXML Controller class
 *
 * @author badis
 */
public class LoginAdminViewController implements Initializable {

    @FXML
    private JFXTextField username;
    @FXML
    private JFXPasswordField pw;
    @FXML
    private JFXButton login;
    @FXML
    private ImageView back;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void login(ActionEvent event) throws SQLException {
                Connection cnx = MyConnection.getInstance().getConnection();
    
         String sql ="select * from users where email= ? and password=?";
         try {
             PreparedStatement ps = cnx.prepareStatement(sql);
             ps.setString(1,username.getText());
             ps.setString(2,Crypt.getMd5(pw.getText()));
              
             ResultSet rs =ps.executeQuery();
             if (rs.next()){
            JOptionPane.showMessageDialog(null,"email et password corrects"); 
             login.getScene().getWindow().hide();
             
             Parent globalPane = FXMLLoader.load(getClass().getResource("/GUI/FXMLDocument.fxml"));
          Scene scene = new Scene(globalPane);
        Stage mainStage=new Stage();
        mainStage.setScene(scene);
        mainStage.show();        
         } else 
            JOptionPane.showMessageDialog(null,"invalid email or password ");  
           
               Parent tableViewParent = FXMLLoader.load(getClass().getResource("/GUI/captcha.fxml"));
               Scene scene = new Scene(tableViewParent);
                Stage mainStage=new Stage();
               mainStage.setScene(scene);
                 mainStage.show();        
         }catch (Exception e){
            JOptionPane.showMessageDialog(null,e);  
         }
    }

    @FXML
    private void goClose(MouseEvent event) {
        Platform.exit();
    }

    @FXML
    private void backToLogin(MouseEvent event) throws IOException {
       back.getScene().getWindow().hide();
        Parent globalPane = FXMLLoader.load(getClass().getResource("/GUI/login.fxml"));
        Scene scene = new Scene(globalPane);
         Stage mainStage=new Stage();
        mainStage.setScene(scene);
        mainStage.show();
    }

    @FXML
    private void recup_pass(MouseEvent event) {
    }

}
