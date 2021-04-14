/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import entities.Concour;
import entities.Quiz;
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
public class GestionConcoursController implements Initializable {

    @FXML
    private Button btAjouter;
    @FXML
    private Button btSupprimer;
    @FXML
    private Button btModifier;
    @FXML
    private TableView<Concour> tabListC;
    @FXML
    private TableColumn<Concour, String> col_id;
    @FXML
    private TableColumn<Concour, String> col_nom;
    @FXML
    private TableColumn<Concour, String> col_sujet;
    @FXML
    private TableColumn<Concour, String> col_categorie;
    @FXML
    private TableColumn<Concour, String> col_dateDebut;
    @FXML
    private TableColumn<Concour, String> col_dateFin;

    Connection connection;
    ObservableList<Concour> TabViewListC = FXCollections.observableArrayList();

    @FXML
    private RadioButton rbTous;
    @FXML
    private ToggleGroup consulterTypeGroup;
    @FXML
    private RadioButton rbConfirmes;
    @FXML
    private RadioButton rbNonConfirmes;
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
    private Button btRefresh;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadTableauConcours();
    }

    private void loadTableauConcours() {
        this.connection = MyConnection.getInstance().getCnx();
        try {
            TabViewListC = FXCollections.observableArrayList();
            String rq = "SELECT * FROM `concour`";

            ResultSet rs = connection.createStatement().executeQuery(rq);

            while (rs.next()) {
                TabViewListC.add(
                        new Concour(rs.getInt("id"), rs.getString("nom"), rs.getString("sujet"), rs.getString("categorie"),
                                rs.getDate("date_debut"), rs.getDate("date_Fin")));
            }

            this.col_id.setCellValueFactory(new PropertyValueFactory<Concour, String>("id"));
            this.col_nom.setCellValueFactory(new PropertyValueFactory<Concour, String>("nom"));
            this.col_sujet.setCellValueFactory(new PropertyValueFactory<Concour, String>("sujet"));
            this.col_categorie.setCellValueFactory(new PropertyValueFactory<Concour, String>("categorie"));
            this.col_dateDebut.setCellValueFactory(new PropertyValueFactory<Concour, String>("date_debut"));
            this.col_dateFin.setCellValueFactory(new PropertyValueFactory<Concour, String>("date_fin"));
            this.tabListC.setItems(null);
            this.tabListC.setItems(this.TabViewListC);

        } catch (SQLException ex) {
            System.err.println("Error" + ex);
        }

    }

    @FXML
    private void RefreshConcours(ActionEvent event) {
        loadTableauConcours();
    }

    @FXML
    private void tfRechecherOnKeyReleased(KeyEvent event) {
    }

    @FXML
    private void btModifierOnClick(ActionEvent event) {
    }

    @FXML
    private void ajouterConcours(ActionEvent event) {
        try {

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/FXML/AjouterConcours.fxml"));
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
    private void DeleteConcours(ActionEvent event) throws SQLException {
        boolean choix0 = tabListC.getSelectionModel().isEmpty();

        if (!choix0) {
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
        } else if (choix0) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Selectionner");
            alert.setHeaderText(null);
            alert.setContentText("Selectionner une ligne ");
            alert.showAndWait();
        }

    }

    @FXML
    private void UpdateConcours(ActionEvent event) {
    }

}
