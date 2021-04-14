/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import entities.Quiz;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import utils.MyConnection;

/**
 * FXML Controller class
 *
 * @author Hiba
 */
public class GestionQuizController implements Initializable {

    @FXML
    private Button btAjouter;
    @FXML
    private Button btSupprimer;
    @FXML
    private Button btModifier;
    @FXML
    private Button btRefresh;
    @FXML
    private RadioButton rbTous;
    @FXML
    private ToggleGroup consulterTypeGroup;
    @FXML
    private RadioButton rbConfirmes;
    @FXML
    private ToggleGroup consulterTypeGroup3;
    @FXML
    private RadioButton rbNonConfirmes;
    @FXML
    private ToggleGroup consulterTypeGroup2;
    @FXML
    private RadioButton rbConfirmes1;
    @FXML
    private ToggleGroup consulterTypeGroup1;
    @FXML
    private TextField tfRechercher;
    @FXML
    private ComboBox<?> bxcategorie;
    @FXML
    private Button btback;
   @FXML
    private TableView<Quiz> tabListQ;
    @FXML
    private TableColumn<Quiz , String> col_concourId;
    @FXML
    private TableColumn<Quiz , String> col_nbQuestions;
    @FXML
    private TableColumn<Quiz , String> col_idListe;
    @FXML
    private TableColumn<Quiz , String> col_nomListe;
    Connection connection ;
        ObservableList<Quiz>  TabViewList = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         loadTableau();

    }    
 private void loadTableau() {
        this.connection = MyConnection.getInstance().getCnx();
        try {
            TabViewList = FXCollections.observableArrayList();
            String rq = "SELECT * FROM `quiz`" ;

            ResultSet rs = connection.createStatement().executeQuery(rq);

            while (rs.next()) {
                TabViewList.add(
                        new Quiz( rs.getInt("id"), rs.getString("concour_id"),rs.getString("nom"),rs.getString("nb_questions") ));
            }


            this.col_idListe.setCellValueFactory(new PropertyValueFactory<Quiz,String>("id"));
            this.col_concourId.setCellValueFactory(new PropertyValueFactory<Quiz,String>("concour_id"));
            this.col_nomListe.setCellValueFactory(new PropertyValueFactory<Quiz,String>("nom"));
            this.col_nbQuestions.setCellValueFactory(new PropertyValueFactory<Quiz,String>("nb_questions"));
            this.tabListQ.setItems(null);
            this.tabListQ.setItems(this.TabViewList);

        } catch (SQLException ex) {
            System.err.println("Error" + ex);
        }

    }

    @FXML
    private void tfRechecherOnKeyReleased(KeyEvent event) {
    }

    @FXML
    private void btModifierOnClick(ActionEvent event) {
    }

    @FXML
    private void ajouterQuiz(ActionEvent event) {
        
         try {

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/FXML/ajouterQuiz.fxml"));
            loader.load();
            Parent parent = loader.getRoot();
                    Scene scene = new Scene(parent);
                    Stage stage = new Stage();
                    stage.setScene(scene);
                    stage.initStyle(StageStyle.UTILITY);
                    stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void DeleteQuiz(ActionEvent event) throws SQLException {
        boolean choix0 = tabListQ.getSelectionModel().isEmpty();

        if(!choix0) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation de suppression !!");
            alert.setHeaderText(null);
            alert.setContentText("Voulez-vous vraiment de supprimer cette ligne");
            Optional<ButtonType> action = alert.showAndWait();

            if (action.get() == ButtonType.OK) {
                Quiz tab1 = tabListQ.getSelectionModel().getSelectedItem();
               String querry = "DELETE FROM `quiz` WHERE `id` = " + tab1.getId();

                connection = MyConnection.getInstance().getCnx();
                PreparedStatement ps = connection.prepareStatement(querry);
                ps.execute();
                System.out.println("Supprimer avec success  !!!");

            }
        }
        else if (choix0) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Selectionner");
            alert.setHeaderText(null);
            alert.setContentText("Selectionner une ligne ");
            alert.showAndWait();
        }
        
    }

    @FXML
    private void UpdateQuiz(ActionEvent event) {
        boolean choix = tabListQ.getSelectionModel().isEmpty();


            if(!choix) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation de votre modification ");
            alert.setHeaderText(null);
            alert.setContentText("Voulez-vous vraiment de modifier cette ligne ??");
            Optional<ButtonType> action = alert.showAndWait();

            if (action.get() == ButtonType.OK) {


                try {

                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource("/FXML/UpdateQuiz.fxml"));
                    loader.load();

                    Quiz tab2 = tabListQ.getSelectionModel().getSelectedItem();
                 UpdateQuizController updateQuizController  = loader.getController();


                    updateQuizController.text( tab2.getId() ,
                    tab2.getNom() , tab2.getNb_questions() , tab2.getConcour_id()
                    );

                    Parent parent = loader.getRoot();
                    Scene scene = new Scene(parent);
                    Stage stage = new Stage();
                    stage.setScene(scene);
                    stage.initStyle(StageStyle.UTILITY);
                    stage.show();

                } catch (IOException ex) {
                    System.out.println(ex);
                }
            }
        }  else if (choix) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Selectionner");
                alert.setHeaderText(null);
                alert.setContentText("Selectionner une ligne ");
                alert.showAndWait();
            }
    }

    @FXML
    private void RefreshQuiz(ActionEvent event) {
        loadTableau();
    }

    
    
}
