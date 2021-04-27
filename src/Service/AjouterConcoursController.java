/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import com.jfoenix.controls.JFXButton;
import entities.Concour;
import java.net.URL;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import utils.MyConnection;
import java.sql.* ;
import java.util.Optional;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
/**
 * FXML Controller class
 *
 * @author Hiba
 */
public class AjouterConcoursController implements Initializable {

    @FXML
    private TextArea tfSujet;
    @FXML
    private TextArea tfDescription;
    @FXML
    private CheckBox chkVideo;
    @FXML
    private JFXButton btnRetour;
    @FXML
    private JFXButton btnAjouter;
    @FXML
    private TextField tfNom;
    @FXML
    private TextField tfCategorie;
    @FXML
    private DatePicker date_debut;
    @FXML
    private DatePicker date_Fin;
    @FXML
    private ColorPicker Couleur;
    @FXML
    private TextField ctImage;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

   

    @FXML
    private void ajouterConcour(ActionEvent event) {
        try {

             String requette;
            requette = "INSERT INTO `concour`(`sujet`, `nom`, `description`, `date_debut`, `date_fin`,`image_name`,"
                    + " `categorie`)"
                    + "VALUES (?,?,?,?,?,?,?)";

            Connection connection = MyConnection.getInstance().getConnection();
            PreparedStatement pst = connection.prepareStatement(requette);
            pst.setString(1, tfSujet.getText());
            pst.setString(2, tfNom.getText());
            pst.setString(3, tfDescription.getText());
            pst.setDate(4, java.sql.Date.valueOf(date_debut.getValue() ));
            pst.setDate(5, java.sql.Date.valueOf(date_Fin.getValue ()));
            pst.setString(6, ctImage.getText());
            pst.setString(7, tfCategorie.getText() );
           // pst.setBoolean(8, chkVideo. );
           // pst.setString(9, Couleur.getPromptText());

            pst.executeUpdate();
            
             Alert alert = new Alert(Alert.AlertType.INFORMATION);
            
            alert.setContentText("Concours ajouté avec succés !");
            Optional<ButtonType> action = alert.showAndWait();
            System.out.println("Concours ajouté avec succés !");
            
        } catch (SQLException ex) {
              Alert alert = new Alert(Alert.AlertType.WARNING);
            
            alert.setContentText("Veuillez vérifier les champs !");
            Optional<ButtonType> action = alert.showAndWait();
            System.out.println(ex.getMessage());
        } 

    }
    
    
    
}
