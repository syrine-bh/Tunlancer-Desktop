/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunlancer.forum.gui;

import Models.Replies;
import Service.ServiceReplies;
import com.jfoenix.controls.JFXButton;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javax.swing.JOptionPane;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author cyrinaa belguith
 */
public class AddReplyController implements Initializable {

    @FXML
    private TextArea reponse;
    @FXML
    private JFXButton btnrepondre;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    


    @FXML
    private void AddRep(ActionEvent event) {
        
        if(reponse.getText().isEmpty()){
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setHeaderText(null);
            a.setContentText("Veuillez remplir le champs");
            a.showAndWait();
        }
         else{
        ServiceReplies sr = new ServiceReplies();
        Replies rep = new Replies();
//        sr.AjouterRep(new Replies(reponse.getText(), 1,48));
        rep.setContenu(reponse.getText());
        rep.setTopic_id(29);
        rep.setUser_id(48);
        sr.AjouterRep(rep);
        JOptionPane.showMessageDialog(null, "succes !!");
        Notifications notificationBuilder = Notifications.create()
            .title("Succes").text("votre commentaire a été ajouté !!").graphic(null).hideAfter(javafx.util.Duration.seconds(5))
               .position(Pos.CENTER_LEFT)
               .onAction(new EventHandler<ActionEvent>(){
                   public void handle(ActionEvent event)
                   {
                       System.out.println("clicked ON ");
               }});
       notificationBuilder.darkStyle();
       notificationBuilder.show();}
    }

    private void AjouterRep(MouseEvent event) {
        try {
            Parent parent = FXMLLoader.load(getClass().getResource("/tunlancer/forum/gui/AddReply.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initStyle(StageStyle.UTILITY);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(ForumController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
}
