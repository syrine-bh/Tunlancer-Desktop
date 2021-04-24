/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Models.Competence;
import Models.Users;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import utils.MyConnection;

/**
 * FXML Controller class
 *
 * @author asus
 */
public class AdminController implements Initializable {

    @FXML
    private AnchorPane pageHolder;
    @FXML
    private TableView<Users> tvusers;
    @FXML
    private TableColumn<Users, Integer> colid;
    @FXML
    private TableColumn<Users, String> colnom;
    @FXML
    private TableColumn<Users, String> colprenom;
    @FXML
    private TableColumn<Users, String> colemail;
    @FXML
    private TableColumn<Users, Integer> coltel;
    @FXML
    private TableColumn<Users, String> colpays;
    @FXML
    private TableColumn<Users, Integer> colage;
    @FXML
    private TableColumn<Users, String> colsexe;
    @FXML
    private Label label;
    @FXML
    private AnchorPane slider;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void Update(ActionEvent event) {
    }

    @FXML
    private void delete(ActionEvent event) {
    }

    @FXML
       private void affiche(ActionEvent event) {
      ObservableList<Users> Users = affiche();
      colid.setCellValueFactory(new PropertyValueFactory<>("id"));
      colnom.setCellValueFactory(new PropertyValueFactory<>("nom"));
      colprenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
      coltel.setCellValueFactory(new PropertyValueFactory<>("tel"));
       colemail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colpays.setCellValueFactory(new PropertyValueFactory<>("pays"));

        colage.setCellValueFactory(new PropertyValueFactory<>("age"));
       colsexe.setCellValueFactory(new PropertyValueFactory<>("sexe"));
      
    tvusers.setItems(Users);
    }
    
    private ObservableList<Users> affiche() {
        
        ObservableList<Users> Users =FXCollections. observableArrayList();
      try {
        String query = "select * from users";
        
	 Statement st = MyConnection.getInstance().getConnection()
                    .createStatement();
            ResultSet rs =  st.executeQuery(query);
	
	while (rs.next()) {
	   Users ex =new Users();
	    ex.setId(rs.getInt("id"));
	    ex.setNom(rs.getString("nom"));
            ex.setPrenom(rs.getString("prenom"));
            ex.setTel(rs.getInt("tel"));
            ex.setEmail(rs.getString("email"));
            ex.setRole(rs.getString("role"));
            ex.setPays(rs.getString("pays"));
            ex.setSexe(rs.getString("sexe"));
            ex.setAge(rs.getInt("age"));
	    Users.add(ex);
        }
       
	 } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
	return Users;
        }

    @FXML
    private void dashboardPageAction(MouseEvent event) {
    }

    @FXML
    private void usersPageAction(MouseEvent event) {
    }

    @FXML
    private void offresPageAction(MouseEvent event) {
    }

    @FXML
    private void recrutementsPageAction(MouseEvent event) {
    }

    @FXML
    private void concoursPageAction(MouseEvent event) {
    }

    @FXML
    private void forumsPageAction(MouseEvent event) {
    }

    @FXML
    private void publicationsPageAction(MouseEvent event) {
    }
    
}
