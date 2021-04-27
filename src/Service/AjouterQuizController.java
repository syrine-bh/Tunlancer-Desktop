/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRadioButton;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import utils.MyConnection;

/**
 * FXML Controller class
 *
 * @author Hiba
 */
public class AjouterQuizController implements Initializable {

    private ToggleGroup radioGroup;
    @FXML
    private TextField tfConcour_id;
    @FXML
    private TextField tfNom;
    @FXML
    private TextField tfNb_questions;
    @FXML
    private Button ajouterQuiz;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
//        radioButtonSetup();
//        setQuizNomBtn.setOnAction(new EventHandler<ActionEvent>() {
//            @Override
//            public void handle(ActionEvent event) {
//                System.out.println(".handle()");
//            }
//        });

    }
    
    @FXML
        private void ajouterQuiz(ActionEvent event) {

    try {
             String requette;
            requette = "INSERT INTO `quiz`(`concour_id`, `nom`, `nb_questions`)"
                    + "VALUES (?,?,?)";

            Connection connection = MyConnection.getInstance().getConnection();
            PreparedStatement pst = connection.prepareStatement(requette);
            pst.setString(1, tfConcour_id.getText());
            pst.setString(2, tfNom.getText());
            pst.setString(3, tfNb_questions.getText());
           

            pst.executeUpdate();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            
            alert.setContentText("Quiz ajouter avec sucees !");
            Optional<ButtonType> action = alert.showAndWait();
            
            System.out.println("Quiz ajouter avec sucees !");
            
        } catch (SQLException ex) {
              Alert alert = new Alert(Alert.AlertType.WARNING);
            
            alert.setContentText("Verifier les champs !");
            Optional<ButtonType> action = alert.showAndWait();
            
            System.out.println(ex.getMessage());
        } 

    
    

//    private void radioButtonSetup() {
//        radioGroup = new ToggleGroup();
//        option1radio.setToggleGroup(radioGroup);
//        option2radio.setToggleGroup(radioGroup);
//        option3radio.setToggleGroup(radioGroup);
//        option4radio.setToggleGroup(radioGroup);
//
//    }
//
////    @FXML
////    private void setQuizNom(ActionEvent event) {
////    }
//    private void setQuizNom(ActionEvent event) {
//        System.out.println("Service.AjouterQuizController.setQuizNom()");
//        String Nom = nomQuiz.getText();
//        if (Nom.trim().isEmpty()) {
//            System.out.println("Entrer un Nom valide");
//        } else {
//            nomQuiz.setEditable(true);
//            System.err.println("Enregister Nom ...");
//        }
//    }
//
//    @FXML
//    private void ajouterQuestion(ActionEvent event) {
//        String qu = this.question.getText();
//        String op1 = this.option1.getText();
//        String op2 = this.option2.getText();
//        String op3 = this.option3.getText();
//        String op4 = this.option4.getText();
//        radioGroup.getSelectedToggle();
//        Toggle selectedRadio = radioGroup.getSelectedToggle();
//        System.out.println(selectedRadio);
//        if (qu.trim().isEmpty()
//                || op1.trim().isEmpty()
//                || op2.trim().isEmpty()
//                || op3.trim().isEmpty()
//                || op4.trim().isEmpty()) {
//            System.out.println("Veuillez remplir tous les champs");
//
//        } else {
//            if (selectedRadio == null) {
//                System.out.println("veuiller choisir une reponse");
//
//            } else {
//// enregistrer question ajouter nouveay
//            }
//        }
    }

}
