/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunlancer.forum.gui;

import Interfaces.IServiceTopic;
import Models.Topics;
import Service.ServiceTopics;
import java.awt.AWTException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import tray.animations.AnimationType;
import tray.notification.TrayNotification;
import utils.MaConnexion;

/**
 * FXML Controller class
 *
 * @author cyrinaa belguith
 */
public class EditTopicController implements Initializable {

    @FXML
    private TextField tfTitre;
    @FXML
    private TextArea taContenu;
    @FXML
    private Button btnvalider;
    
    Topics t;
    int id;
    Connection cnx= MaConnexion.getInstance().getCnx();
    @FXML
    private Label Errors;
    
    public EditTopicController() {
        cnx = MaConnexion.getInstance().getCnx();

    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        if (cnx == null) {
            Errors.setTextFill(Color.TOMATO);
            Errors.setText("Serveur non connecté : à vérifier");
        } else {
            Errors.setTextFill(Color.GREEN);
            Errors.setText("Server connecté ");
        }
    }    

    @FXML
    private void EditerTopic(ActionEvent event) throws AWTException{
         if (event.getSource() == btnvalider) {
            // here

            if (EditT()) {
                TrayNotification tray = new TrayNotification();
                AnimationType type= AnimationType.POPUP;
                tray.setAnimationType(type);
                tray.setRectangleFill(Color.valueOf("#d7abe2"));
                tray.setTitle("Tunlancer App Desktop");
                tray.setMessage("Modifié avec suuces !");
                
                //tray.setNotificationType(NotificationType.SUCCESS);
                tray.showAndDismiss(Duration.millis(3000));
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Modification");
                alert.setHeaderText("Resultas:");
                alert.setContentText("Modifié avec suucès!");
                alert.showAndWait();

            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Modification");
                alert.setHeaderText("Resultat:");
                alert.setContentText("ERREUR!");

                alert.showAndWait();

            }

        }
    }
//    private void EditerTopic(ActionEvent event) throws SQLException {
//        if(tfTitre.getText().isEmpty()){
//            Alert al = new Alert(Alert.AlertType.ERROR);
//            al.setHeaderText(null);
//            al.setContentText("Veuillez remplir les champs vides ! ");
//            al.showAndWait();;
//        }else if(taContenu.getText().isEmpty()){
//            Alert al = new Alert(Alert.AlertType.ERROR);
//            al.setHeaderText(null);
//            al.setContentText("Veuillez remplir les champs vides ! ");
//            al.showAndWait();
//        }else{
//        ServiceTopics st = new ServiceTopics();
//        Topics t = new Topics();
//        t.setTitre(tfTitre.getText());
//        t.setContenu(taContenu.getText());
//        t.setUser_id(48);
//        TrayNotification tray = new TrayNotification();
//                AnimationType type= AnimationType.POPUP;
//                tray.setAnimationType(type);
//                tray.setRectangleFill(Color.valueOf("#16cabd"));
//                tray.setTitle("Tunlancer Desktop");
//                tray.setMessage("Topic modifié avec suucès!");
//                
//                //tray.setNotificationType(NotificationType.SUCCESS);
//                tray.showAndDismiss(Duration.millis(3000));
//                Alert alert = new Alert(Alert.AlertType.INFORMATION);
//                alert.setTitle("modification");
//                alert.setHeaderText("Resultat:");
//                alert.setContentText("Modifié avec succes!");
//                alert.showAndWait();
//        st.modifierTopic(t);
//        }
//    }

    @FXML
    private void clean(MouseEvent event) {
    }
    
   public void setTopics(Topics t){
        this.t=t;
    }

    public void setTopicId(int id){
        this.id=id;
    }

    public void setTitre(String titre) {
        this.tfTitre.setText(titre);
    }

    public void setContenu(String contenu) {
        this.taContenu.setText(contenu);
    }
    
    private void setLblError(Color color, String text) {
        Errors.setTextFill(color);
        Errors.setText(text);
        System.out.println(text);
    }
    
    public boolean EditT() {

        int res = 0;
        boolean s=false;
        String titre = tfTitre.getText();
        String contenu = taContenu.getText();
        
        if (titre.isEmpty() || contenu.isEmpty() ){
            setLblError(Color.TOMATO, "Veuillez remplir les champs");

        } else {
            try {
                Topics topics = new Topics(titre, contenu);
                IServiceTopic Topics = new ServiceTopics();
                res = Topics.updateTopic(id,topics );
                if (res!=0) {
                    s=true;
                    setLblError(Color.GREEN, "Topic modifié avec succès..");
                } else {
                    s=false;
                    setLblError(Color.RED, "ERREUR");
                }
            } catch (SQLException ex) {
                System.out.println("probleme de " +ex.getMessage());
            }

        }
        return s;

    }

    
}
