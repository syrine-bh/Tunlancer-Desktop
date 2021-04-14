/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import entities.Concour;
import entities.Participation;
import entities.Users;
import entities.Users_;
import static entities.Users_.currentConnections;
import static entities.Users_.totalConnections;
import entities.Video;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.MediaView;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import sun.security.pkcs11.wrapper.Functions;

/**
 * FXML Controller class
 *
 * @author Hiba
 */
public class ParticipationController {

    @FXML
    private Button btnParticiper;
    @FXML
    private Label participation_date;
        @FXML
    private WebView  preview;
    @FXML
    private TextField titre;

    /**
     * Initializes the controller class.
     */
@FXML
//Users user = new Users(100, currentConnections, totalConnections, "hiba", "farhat", "200000", "hiba@esprit.tn",
//        "123456", "tunis", "freelancer", 1, "20", "femme");
    private TextField url;
    private Concour c;
     public void setConcour(Concour c) {
        this.c=c;
    }
    void initialize() {
        participation_date.setText(new Timestamp(System.currentTimeMillis()).toString());
        
    }
    
        @FXML
    void previewVideo(MouseEvent event) {
       preview.getEngine().load(url.getText());
        
            
    }
    
    void participerVideo(ActionEvent event) {
        String t=titre.getText();
        String u=url.getText();
        Timestamp time= new Timestamp(System.currentTimeMillis());
//        UsersSession s=  UserSession.instance;
        Users owner=new Users(100, currentConnections, totalConnections, "hiba", "farhat", "200000", "hiba@esprit.tn",
        "123456", "tunis", "freelancer", 1, "20", "femme");

        Video v=new Video(u, t, time, owner);
        Participation p=new Participation(c, v, time, owner);
        ParticipationServices ps= new ParticipationServices ();
        
//        cp=p ps=pc
        ps.create(p, v);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Participation");
            alert.setHeaderText("Participation Added Succesfully");
            alert.setContentText("Go Back To The Homepage");

            alert.showAndWait();
            Stage stage = (Stage) Participation.getScene().getWindow();
             stage.close();
             
       

    }

    void cancel(ActionEvent event) {
        Stage stage = (Stage) Participation.getScene().getWindow();
        stage.close();

    }

//    @Override
//    public void initialize(URL arg0, ResourceBundle arg1) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }

    
}
