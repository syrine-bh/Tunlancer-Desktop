/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Models.Competence;
import Services.ServiceCompetence;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.util.Duration;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author asus
 */
public class AjoutCController implements Initializable {

    @FXML
    private TextArea content;
    @FXML
    private JFXTextField tftitre;
    @FXML
    private JFXTextField tfdomaine;
    @FXML
    private JFXButton envoyer;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void ajoutercom(ActionEvent event) {
         ServiceCompetence sc =new ServiceCompetence();
       Competence c =new Competence();
       c.setTitre(tftitre.getText());
       c.setDomaine(tfdomaine.getText());
         sc.Addcom(c);
       TrayNotification tray = new TrayNotification("", "Competence ajoutée avec succes ", NotificationType.SUCCESS);

        tray.showAndDismiss(Duration.seconds(3));
       envoyer.getScene().getWindow().hide();
    
    }
    
}
