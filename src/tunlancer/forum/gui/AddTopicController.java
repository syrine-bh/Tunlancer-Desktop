/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunlancer.forum.gui;

import Models.Topics;
import Service.ServiceTopics;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import tray.animations.AnimationType;
import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author cyrinaa belguith
 */
public class AddTopicController implements Initializable {

    @FXML
    private TextField tfTitre;
    @FXML
    private TextArea taContenu;
    @FXML
    private Button btnvalider;

    private boolean update;
    int TopicId;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void AjouterTopic(ActionEvent event) {
        
        if(tfTitre.getText().isEmpty()){
            Alert al = new Alert(Alert.AlertType.ERROR);
            al.setHeaderText(null);
            al.setContentText("Veuillez remplir les champs vides ! ");
            al.showAndWait();;
        }else if(taContenu.getText().isEmpty()){
            Alert al = new Alert(Alert.AlertType.ERROR);
            al.setHeaderText(null);
            al.setContentText("Veuillez remplir les champs vides ! ");
            al.showAndWait();
        }else{
        ServiceTopics st = new ServiceTopics();
        Topics t = new Topics();
        t.setTitre(tfTitre.getText());
        t.setContenu(taContenu.getText());
        t.setUser_id(48);
        st.AjouterTopic(t);
        
        }
        
    }

    @FXML
    private void clean(MouseEvent event) {
        tfTitre.setText(null);
        taContenu.setText(null);
    }

    void setUpdate(boolean b) {
        this.update=b;
    }

    void setTextField(int id, String titre, String contenu, LocalDate toLocalDate) {
        tfTitre.setText(titre);
        taContenu.setText(contenu);
    }
    
}