/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service.Front.Concours;

import Models.Users;
import Service.ParticipationServices;
import entities.Concour;
import entities.Participation;
import entities.Video;
import java.net.URL;
import java.io.Serializable;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import utils.UserSession;

/**
 * FXML Controller class
 *
 * @author Hiba
 */
public class ConcourVParticipateController implements Initializable {

    @FXML
    private AnchorPane participation;
    @FXML
    private Button btn_participate;
    @FXML
    private TextField title;
    @FXML
    private WebView preview;
    @FXML
    private Button btn_cancel;
    @FXML
    private Label participation_date;
    @FXML
    private TextField url;
         private ServiceConcours st = new ServiceConcours();

      Concour c;
        concoursHolder th = concoursHolder.getINSTANCE();
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        participation_date.setText(new Timestamp(System.currentTimeMillis()).toString());
                c = st.getConcours(th.getId());
                System.out.println(c);

    }    

    @FXML
    private void participate(ActionEvent event) throws SQLException {
          String t=title.getText();
        String u=url.getText();
        Timestamp time= new Timestamp(System.currentTimeMillis());
        UserSession s=  UserSession.instance;
        Users owner=s.getU();
//        Users owner=new Users(712, "test", "test", 1, "test", "test", "test", "test", "test", 1, 1, 1);
        Video v=new Video(u, t, time, owner);
        Participation cp=new Participation(c, owner, time, v);
        ParticipationServices ps= new ParticipationServices();
        
        ps.create(cp, v);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Participation");
            alert.setHeaderText("Participation Added Succesfully");
            alert.setContentText("Go Back To The Homepage");

            alert.showAndWait();
            Stage stage = (Stage) participation.getScene().getWindow();
             stage.close();
    }

    @FXML
    private void cancel(ActionEvent event) {
           Stage stage = (Stage) participation.getScene().getWindow();
        stage.close();

    }

    @FXML
    private void previewVideo(MouseEvent event) {
               preview.getEngine().load(url.getText());

    }
 public void setConcour(Concour c) {
        this.c=c;
    }
  
    
}
