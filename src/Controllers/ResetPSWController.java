/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.util.Duration;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author yassi
 */
public class ResetPSWController implements Initializable {

    @FXML
    private TextField code;
    @FXML
    private JFXButton login;
    @FXML
    private JFXButton submit;

    String codeSent = "";
    String mail = "";
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println("control 1 = " +codeSent);
        System.out.println("control 1 = " +mail);
        
    }    

    public void setcode(String value) {
        this.codeSent = value;
    }
     
    public void setmail(String value) {
        this.mail = value;
    }

    @FXML
    private void switch1(ActionEvent event) {
        try {
            Parent tableViewParent = FXMLLoader.load(getClass().getResource("/GUI/login.fxml"));
            Scene tableViewScene = new Scene (tableViewParent);
            
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(tableViewScene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(HomePageHolderController.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }

    @FXML
    private void submit(ActionEvent event) {
        System.out.println(codeSent);
        System.out.println(mail);
        if (codeSent.equals(code.getText())){
            try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/NewPSW.fxml"));
            Parent root = loader.load();          
            NewPSWController dpc = loader.getController();
            
            
            dpc.Setmail(mail);
            
            code.getScene().setRoot(root);
            }catch (IOException ex) {
            Logger.getLogger(HomePageHolderController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            
        }else{
            String tilte = "Reset password";
            String message = "Le code n'est pas valide";
            TrayNotification tray = new TrayNotification();
            AnimationType type = AnimationType.POPUP;
        
            tray.setAnimationType(type);
            tray.setTitle(tilte);
            tray.setMessage(message);
            tray.setNotificationType(NotificationType.ERROR);
            tray.showAndDismiss(Duration.millis(3000));
            }
    }

    
    
}
