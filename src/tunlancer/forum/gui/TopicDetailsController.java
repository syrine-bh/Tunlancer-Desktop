/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunlancer.forum.gui;

import Interfaces.IServiceTopic;
import Models.Topics;
import Service.ServiceTopics;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author cyrinaa belguith
 */
public class TopicDetailsController implements Initializable {

    @FXML
    private ImageView avatar;
    @FXML
    private Label Ltitre;
    @FXML
    private Label Lcontenu;
    @FXML
    private TextArea contenu;
    @FXML
    private Label Ldate;
    @FXML
     FontAwesomeIconView EditTopic;
    @FXML
     FontAwesomeIconView deletebtnTopic;
    private Label Ldatecreation;
    Connection cnx = null;
    PreparedStatement preparedStatement = null;
    String resultSet = null;
    List<Topics> ListT = new ArrayList();
    IServiceTopic Top = new ServiceTopics();
    Topics t;
    
    List<Topics> ListTa;
    @FXML
    private Label titre;
    @FXML
    private Label date;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public TopicDetailsController() {
    }

    public void setContenu(String contenu) {
        this.contenu.setText(contenu);
    }

    public void setTitre(String titre) {
        this.titre.setText(titre);
    }
    
    public void setDate(String date){
        this.date.setText(date);
    }
    
    

    public void setLtitre(Label Ltitre) {
        this.Ltitre = Ltitre;
    }

    public void setAvatar(ImageView avatar) {
        this.avatar = avatar;
    }

    public void setLcontenu(Label Lcontenu) {
        this.Lcontenu = Lcontenu;
    }

    public void setLdatecreation(Label Ldatecreation) {
        this.Ldatecreation = Ldatecreation;
    }

    public ImageView getAvatar() {
        return avatar;
    }

    public Label getLcontenu() {
        return Lcontenu;
    }

    public Label getLdatecreation() {
        return Ldatecreation;
    }

    public Label getLtitre() {
        return Ltitre;
    }

    

    private void AjouterTopic(MouseEvent event) {
         try {
            Parent parent = FXMLLoader.load(getClass().getResource("/tunlancer/forum/gui/AddTopic.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initStyle(StageStyle.UTILITY);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(ForumController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }


    @FXML
    private void AjouterRep(MouseEvent event) {
        try {
            Parent parent = FXMLLoader.load(getClass().getResource("/tunlancer/forum/gui/AddReply.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initStyle(StageStyle.UTILITY);
            stage.show();
        } catch (IOException ex) {
            System.out.println("probleme");
        }
    }


    @FXML
    private void AfficherCom(MouseEvent event) {
         try {
            //Parent parent = FXMLLoader.load(getClass().getResource("/tunlancer/forum/gui/Replies.fxml"));
            Parent parent = FXMLLoader.load(getClass().getResource("/tunlancer/forum/gui/Details.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initStyle(StageStyle.UTILITY);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(RepliesController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @FXML
    private void Rating(MouseEvent event) {
        try {
            Parent parent = FXMLLoader.load(getClass().getResource("/tunlancer/forum/gui/Rating.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initStyle(StageStyle.UTILITY);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(TopicDetailsController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
