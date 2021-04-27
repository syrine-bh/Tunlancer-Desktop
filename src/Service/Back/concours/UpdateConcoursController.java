/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service.Back.concours;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import utils.MyConnection;

/**
 * FXML Controller class
 *
 * @author Hiba
 */
public class UpdateConcoursController implements Initializable {

    @FXML
    private TextArea tfSujet;
    @FXML
    private TextArea tfDescription;
    private DatePicker date_debut;
    private DatePicker date_Fin;
    @FXML
    private CheckBox chkVideo;
    @FXML
    private ColorPicker Couleur;
    @FXML
    private TextField tfNom;
    @FXML
    private TextField tfCategorie;
    @FXML
    private TextField ctImage;
    @FXML
    private JFXButton btnModifer;
    @FXML
    private TextField tfId;
    @FXML
    private DatePicker dpDatedebut;
    @FXML
    private DatePicker dpDateFin;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void ModifierConcour(ActionEvent event) throws SQLException {
        String query = "UPDATE `concour` SET `sujet`=?,`nom`=?,`description`=?,`date_debut`=?,`date_fin`=?, `image_name`=?,`categorie`=? WHERE id=?";

        Connection cn = MyConnection.getInstance().getConnection();
        PreparedStatement ps = cn.prepareStatement(query);
        ps.setString(1, tfSujet.getText());

        ps.setString(2, tfNom.getText());
        ps.setString(3, tfDescription.getText());
        ps.setDate(4,  java.sql.Date.valueOf(dpDatedebut.getValue()));
        ps.setDate(5, java.sql.Date.valueOf(dpDateFin.getValue()));
                ps.setString(6, ctImage.getText());
                ps.setString(7, tfCategorie.getText());
                                ps.setString(8, tfId.getText());


        ps.executeUpdate();
        System.out.println("Modifié avec succees !");

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();

    }

    void text(int id,String sujet, String nom, String description, 
            LocalDate date_debut,LocalDate date_fin,String image_name,String categorie) {

        tfId.setText(String.valueOf(id));
        tfSujet.setText(sujet);
        tfNom.setText(nom);
        tfDescription.setText(description);
            dpDatedebut.setValue(date_debut);
            dpDateFin.setValue(date_fin);
                    ctImage.setText(image_name);
                   tfCategorie.setText(categorie);

            

    }



}
