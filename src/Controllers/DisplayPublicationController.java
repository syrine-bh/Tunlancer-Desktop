/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Models.Publication;
import Services.ServicePublication;
import java.net.URL;
import static java.nio.file.Files.list;
import static java.rmi.Naming.list;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import static java.util.Collections.list;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TableView.TableViewSelectionModel;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TouchEvent;
import utils.MaConnexion;

/**
 * FXML Controller class
 *
 * @author Cyrina
 *
 */
public class DisplayPublicationController implements Initializable {

    @FXML
    private TableColumn<Publication, Integer> col_id;
    @FXML
    private TableColumn<Publication, String> col_user;
    @FXML
    private TableColumn<Publication, String> colPub;

    @FXML
    private TableView<Publication> tablePublication;
    @FXML
    private ImageView publication_image;
    @FXML
    private TextField searchInput;

    ObservableList<Publication> list;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ServicePublication SP = new ServicePublication();
        List<Publication> publications = new ArrayList<>();
        try {
            publications = SP.afficherPublications();

            if (!publications.isEmpty()) {
                publication_image.setImage(new Image("file:/C:/Users/Cyrina/Desktop/PIDEV/JAVA/tunlancer-desktop/src/assets/" + publications.get(0).getImage_name()));
            }

            list = FXCollections.observableArrayList(publications); ///conversion normal list to observable list
            col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
            col_user.setCellValueFactory(new PropertyValueFactory<>("utilisateur_id"));
            colPub.setCellValueFactory(new PropertyValueFactory<>("description"));

            tablePublication.setEditable(true); //clickable
            tablePublication.setItems(list); //remplir tableview with observablz list

        } catch (SQLException ex) {
            Logger.getLogger(DisplayPublicationController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void mouseClicked(MouseEvent event) {
        Publication selected = tablePublication.getSelectionModel().getSelectedItem(); //get selected publication predefinie tableview
        publication_image.setImage(new Image("file:/C:/Users/Cyrina/Desktop/PIDEV/JAVA/tunlancer-desktop/src/assets/" + selected.getImage_name()));
    }

    @FXML
    private void redirectToPublication(ActionEvent event) {
        System.out.println("affichage formulaire ajout");
    }

 
    @FXML
    private void filterPublication(KeyEvent event) throws SQLException {
        ServicePublication SP = new ServicePublication();
        List<Publication> publications = SP.afficherPublications();
        List<Publication> filteredpublications = new ArrayList<>();
        if (!searchInput.getText().isEmpty()) {
            filteredpublications = publications.stream().filter(p -> p.getDescription().startsWith(searchInput.getText())).collect(Collectors.toList());
        } else {
            filteredpublications = publications;
        }
        list.clear();
        list = FXCollections.observableArrayList(filteredpublications); ///conversion normal list to observable list
        col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        col_user.setCellValueFactory(new PropertyValueFactory<>("utilisateur_id"));
        colPub.setCellValueFactory(new PropertyValueFactory<>("description"));

        tablePublication.setEditable(true); //clickable
        tablePublication.setItems(list); //remplir tableview with observablz list
    }

}
