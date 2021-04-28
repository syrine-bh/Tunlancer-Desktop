/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunlancer.forum.gui;

import Models.Topics;
import Service.ServiceTopics;
import static java.lang.Math.round;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javax.swing.JOptionPane;
import org.controlsfx.control.Rating;

/**
 * FXML Controller class
 *
 * @author cyrinaa belguith
 */
public class RatingController implements Initializable {

    @FXML
    private Rating commentaireRating;
    private Label id;
    @FXML
    private Label idT;
    @FXML
    private Label idCon;
    @FXML
    private TextField desc;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        commentaireRating.ratingProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> ov, Number t, Number t1) {
                desc.setText("Rating : "+ t1.toString());
            }
        });
        // TODO
    }

    public void setData(int id) {
        int user_id = 48;
        //this.id.setText(String.valueOf(SessionUser.getUser().getId()));
        this.idT.setText(String.valueOf(id));
        this.idT.setVisible(false);
    }

    @FXML
    private void Rating(ActionEvent event) {
//        System.out.println(" évaluation  " + commentaireRating.getRating());
//        JOptionPane.showMessageDialog(null, "Merci ☺ !!");
        //int userid=SessionUser.getUser().getId();
        int user_id = 48;
       int id = 4;
//        int id = Integer.parseInt(idT.getText());
        int note;
        note = (int) round(commentaireRating.ratingProperty().getValue());

        ServiceTopics us = new ServiceTopics();

        Topics u = new Topics();
        us.AddNote(u);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Avis");
        alert.setHeaderText("Votre avis a été envoyé");
        alert.setContentText("Merci beaucoup!");
        alert.showAndWait();
        desc.setText("");

    }

}
