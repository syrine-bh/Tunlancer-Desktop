/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import entities.Admin;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Hiba
 */
public class AdminLoginFXMLController implements Initializable {

    @FXML
    private TextField adminEmail;
    @FXML
    private PasswordField adminPassword;
    @FXML
    private Button adminLoginBtn;
    @FXML
    private TextField userEmail;
    @FXML
    private PasswordField userPassword;
    @FXML
    private Button userLoginBtn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void adminLogin(ActionEvent event) {
        String email = adminEmail.getText();
        String password = adminPassword.getText();
        if (email.trim().equalsIgnoreCase(Admin.email)
                && password.trim().equalsIgnoreCase(Admin.password)) {

            try {
                Parent root = FXMLLoader.load(getClass().getResource("/FXML/AdminHome.fxml"));
                Stage stage = (Stage) userPassword.getScene().getWindow();
                Scene scene = new Scene(root);

                stage.setScene(scene);
                stage.setMaximized(true);
            } catch (Exception e) {
                e.printStackTrace();
                System.exit(0);
            }

            System.out.println("Admin authentifie avec succes");
        } else {
            System.out.println("Verifier vos cordonnees");
        }
        System.out.println(email + "--->" + password);

        System.out.println("AdminLoginFXMLController.adminLogin()");
    }

    @FXML
    private void userLogin(ActionEvent event) {
        System.out.println("AdminLoginFXMLController.userLogin()");

    }

}
