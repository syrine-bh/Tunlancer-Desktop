/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author asus
 */
public class EditProfileViewController implements Initializable {

    @FXML
    private TextField firstNameField;
    @FXML
    private TextField lastnameField;
    @FXML
    private TextField heightFiled1;
    @FXML
    private TextField heightFiled;
    @FXML
    private TextField heightFiled2;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void removeError(KeyEvent event) {
    }

    @FXML
    private void close(MouseEvent event) {
    }

    @FXML
    private void update(ActionEvent event) {
    }
    
}
