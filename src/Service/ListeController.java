/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import entities.Concour;
import entities.Quiz;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import java.sql.* ;
import java.util.Optional;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import utils.MyConnection;
import Service.UpdateQuizController ;
/**
 *
 * @author Hiba
 */
public class ListeController implements Initializable{
@FXML
    private TableView<Concour> tabListC;
    @FXML
    private TableColumn<Concour , String> col_id;
    @FXML
    private TableColumn<Concour , String> col_nom;
    @FXML
    private TableColumn<Concour , String> col_sujet;
    @FXML
    private TableColumn<Concour , String> col_categorie;
    @FXML
    private TableColumn<Concour , String> col_dateDebut;
    @FXML
    private TableColumn<Concour , String> col_dateFin;
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
                ObservableList<Concour>  TabViewListC = FXCollections.observableArrayList();


    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
 loadTableau();
 loadTableauConcours();

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
    private void Refresh(ActionEvent event) {
        loadTableau();
    }

    @FXML
    private void Update(ActionEvent event) {
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
    private void Delete(ActionEvent event) throws SQLException {
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
    
    
    
    
    
    private void loadTableauConcours() {
        this.connection = MyConnection.getInstance().getCnx();
        try {
            TabViewListC = FXCollections.observableArrayList();
            String rq = "SELECT * FROM `concour`" ;

            ResultSet rs = connection.createStatement().executeQuery(rq);

            while (rs.next()) {
                TabViewListC.add(
                        new Concour( rs.getInt("id"), rs.getString("nom"),rs.getString("sujet"),rs.getString("categorie"),
                                rs.getDate("date_debut"),rs.getDate("date_Fin") ));
            }


            this.col_id.setCellValueFactory(new PropertyValueFactory<Concour,String>("id"));
            this.col_nom.setCellValueFactory(new PropertyValueFactory<Concour,String>("nom"));
            this.col_sujet.setCellValueFactory(new PropertyValueFactory<Concour,String>("sujet"));
            this.col_categorie.setCellValueFactory(new PropertyValueFactory<Concour,String>("categorie"));
            this.col_dateDebut.setCellValueFactory(new PropertyValueFactory<Concour,String>("date_debut"));
            this.col_dateFin.setCellValueFactory(new PropertyValueFactory<Concour,String>("date_fin"));
            this.tabListC.setItems(null);
            this.tabListC.setItems(this.TabViewListC);

        } catch (SQLException ex) {
            System.err.println("Error" + ex);
        }

    }

    

@FXML
    private void UpdateConcours(ActionEvent event) {
//        boolean choix = tabListQ.getSelectionModel().isEmpty();
//
//
//            if(!choix) {
//            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
//            alert.setTitle("Confirmation de votre modification ");
//            alert.setHeaderText(null);
//            alert.setContentText("Voulez-vous vraiment de modifier cette ligne ??");
//            Optional<ButtonType> action = alert.showAndWait();
//
//            if (action.get() == ButtonType.OK) {
//
//
//                try {
//
//                    FXMLLoader loader = new FXMLLoader();
//                    loader.setLocation(getClass().getResource("/FXML/UpdateQuiz.fxml"));
//                    loader.load();
//
//                    Quiz tab2 = tabListQ.getSelectionModel().getSelectedItem();
//                 UpdateQuizController updateQuizController  = loader.getController();
//
//
//                    updateQuizController.text( tab2.getId() ,
//                    tab2.getNom() , tab2.getNb_questions() , tab2.getConcour_id()
//                    );
//
//                    Parent parent = loader.getRoot();
//                    Scene scene = new Scene(parent);
//                    Stage stage = new Stage();
//                    stage.setScene(scene);
//                    stage.initStyle(StageStyle.UTILITY);
//                    stage.show();
//
//                } catch (IOException ex) {
//                    System.out.println(ex);
//                }
//            }
//        }  else if (choix) {
//                Alert alert = new Alert(Alert.AlertType.WARNING);
//                alert.setTitle("Selectionner");
//                alert.setHeaderText(null);
//                alert.setContentText("Selectionner une ligne ");
//                alert.showAndWait();
//            }
//        
    }
//
    @FXML
    private void DeleteConcours(ActionEvent event) throws SQLException {
 boolean choix0 = tabListC.getSelectionModel().isEmpty();

        if(!choix0) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation de suppression !!");
            alert.setHeaderText(null);
            alert.setContentText("Voulez-vous vraiment de supprimer cette ligne");
            Optional<ButtonType> action = alert.showAndWait();

            if (action.get() == ButtonType.OK) {
                Concour tab1 = tabListC.getSelectionModel().getSelectedItem();
               String querry = "DELETE FROM `concour` WHERE `id` = " + tab1.getId();

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
    private void RefreshConcours(ActionEvent event) {
                loadTableauConcours();

    }
        
    
    

}
