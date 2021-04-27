/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.util.Duration;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;
import utils.MyConnection;

/**
 * FXML Controller class
 *
 * @author asus
 */
public class EditCompetenceController implements Initializable {

    @FXML
    private Button envoyer1;
    @FXML
    private JFXTextField tftitre1;
    @FXML
    private JFXTextField tfdomaine1;
    @FXML
    private JFXTextField id;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void modifcom(ActionEvent event) throws SQLException {
            
      String query = "UPDATE `competence` SET `titre`=?, `domaine`=? WHERE id=?";
    Connection cnx = MyConnection.getInstance().getConnection();
    PreparedStatement ps = cnx.prepareStatement(query);
    ps.setString(1, tftitre1.getText());
    ps.setString(2, tfdomaine1.getText());
     ps.setInt(3,(Integer.parseInt( id.getText())));
    ps.executeUpdate();
    System.out.println("Modifier avec succees !");
    TrayNotification tray = new TrayNotification("", "Competence modifiée avec succes ", NotificationType.SUCCESS);

        tray.showAndDismiss(Duration.seconds(3));
       envoyer1.getScene().getWindow().hide();
    }
    
}
