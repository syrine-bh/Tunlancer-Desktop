/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Models.Users;
import Services.ServiceUser;
import utils.MyConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

/**
 *
 * @author asus
 */
public class UserController implements Initializable {
    
  
    private TextField tfNom;
    private TextField tfprenom;
    private TextField tftel;
    private TextField tfemail;
    private TextField tfpassword;
    private TextField tfpays;
    private TextField tfsexe;
    private TextField tfage;
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
    private TableColumn<Users, String> colpays;
    @FXML
    private TableColumn<Users, String> colrole;
    @FXML
    private TableColumn<Users, String> colsexe;
    @FXML
    private TableColumn<Users, Integer> colage;
    private TextField tfrole;
    private RadioButton homme;
    private RadioButton femme;
   
     Connection cnx;
    @FXML
    private AnchorPane pageHolder;
    @FXML
    private Label label;
    @FXML
    private AnchorPane slider;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    private void AjouterUser(ActionEvent event) {
        ServiceUser su=new ServiceUser();
        Users u =new Users();
        u.setNom(tfNom.getText());
        u.setPrenom(tfprenom.getText());
        u.setEmail(tfemail.getText());
        u.setTel(Integer.parseInt(tftel.getText()));
        u.setpassword(tfpassword.getText());
        u.setPays(tfpays.getText());
         u.setAge(Integer.parseInt(tfage.getText()));
         u.setSexe(femme.getText());
         u.setSexe(homme.getText());
    
         u.setRole(tfrole.getText());
        
          su.AddUser(u);
    }
    
   

    private void updateUser(ActionEvent event) throws SQLException {
    String query = "UPDATE users SET `Nom`=?, `prenom`=?,`email`=?,`tel`=?,`role`=? ,`pays`=? ,`sexe`=?,`age`=? WHERE id=?";
    Connection cnx = MyConnection.getInstance().getConnection();
    PreparedStatement ps = cnx.prepareStatement(query);
        ps.setString(1, tfNom.getText());

    ps.setString(2, tfprenom.getText());
    ps.setString(3, tfemail.getText());
    ps.setString(4, tftel.getText());
  
     ps.setString(5, tfrole.getText());
      ps.setString(6, tfpays.getText());
      ps.setString(7, tfsexe.getText());
      ps.setString(8, tfage.getText());
    

    ps.executeUpdate();
    System.out.println("Modifier avec succees !");
    
    }

    private void deleteUser(ActionEvent event) throws SQLException {
          Users tab1 = tvusers.getSelectionModel().getSelectedItem();
                String querry = "DELETE FROM users WHERE id = " +tab1.getId();

            cnx= MyConnection.getInstance().getConnection();
                PreparedStatement ps = cnx.prepareStatement(querry);
                ps.execute();
                System.out.println("user supprimé avec succés  !!!");
        
    }
     

    private void afficheruser(ActionEvent event) {
      ObservableList<Users> Users = AfficheUser();
      colid.setCellValueFactory(new PropertyValueFactory<>("id"));
      colnom.setCellValueFactory(new PropertyValueFactory<>("nom"));
      colprenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
      
       colemail.setCellValueFactory(new PropertyValueFactory<>("mail"));
        colpays.setCellValueFactory(new PropertyValueFactory<>("pays"));
        colrole.setCellValueFactory(new PropertyValueFactory<>("role"));
        colage.setCellValueFactory(new PropertyValueFactory<>("age"));
       colsexe.setCellValueFactory(new PropertyValueFactory<>("sexe"));
      
    tvusers.setItems(Users);
    }
    
   private ObservableList<Users> AfficheUser() {
        
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
