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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import Services.ServiceUser;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author yassi
 */
public class NewPSWController implements Initializable {

    @FXML
    private PasswordField password;
    @FXML
    private JFXButton submit;

    String mail = "";
    @FXML
    private JFXButton login;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println("controller 2 = " + mail);
    }

    public void Setmail(String mail) {
        this.mail = mail;
    }

    @FXML
    private void Switch1(ActionEvent event) {
        try {
            Parent tableViewParent = FXMLLoader.load(getClass().getResource("/GUI/login.fxml"));
            Scene tableViewScene = new Scene(tableViewParent);

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(tableViewScene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(HomePageHolderController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void submit(ActionEvent event) {
        System.out.println("change password = " + mail);

        ServiceUser pcd = new ServiceUser();

        if (!("".equals(password.getText()))) {
            pcd.updateMotdepasse(mail, password.getText());

            String tilte = "reset password";
            String message = "Password updated ";
            TrayNotification tray = new TrayNotification();
            AnimationType type = AnimationType.POPUP;

            tray.setAnimationType(type);
            tray.setTitle(tilte);
            tray.setMessage(message);
            tray.setNotificationType(NotificationType.SUCCESS);
            tray.showAndDismiss(Duration.millis(3000));

            try {
                Parent tableViewParent = FXMLLoader.load(getClass().getResource("/GUI/login.fxml"));
                Scene tableViewScene = new Scene(tableViewParent);

                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(tableViewScene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(HomePageHolderController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            String tilte = "reset password";
            String message = "Please enter a password";
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
