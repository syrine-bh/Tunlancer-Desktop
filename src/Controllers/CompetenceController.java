/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Models.Competence;
import Models.Variable;
import Services.ServiceCompetence;
import com.jfoenix.controls.JFXButton;
import utils.MyConnection;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;
import javax.swing.JOptionPane;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 *
 * @author asus
 */
public class CompetenceController implements Initializable {
    
    private TextField tftitre;
    private TextField tfdomaine;
    private TableView<Competence> tvcomp;
    private TableColumn<Competence, Integer> colid;
    private TableColumn<Competence, String> coltitre;
    private TableColumn<Competence, String> coldomaine;
    private TextField tfid;
    @FXML
    private TextField tftitre1;
    @FXML
    private TextField tfdomaine1;
    private TableColumn<Competence, Integer> coluserid;
    private TextField tfuserid1;
    
        Connection cnx;
    private Button envoyer;
    @FXML
    private Button envoyer1;

    
    private void handleButtonAction(ActionEvent event) {
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
      
    }    
     @FXML
      private void ajoutercom(ActionEvent event) {
      
       ServiceCompetence sc =new ServiceCompetence();
       Competence c =new Competence();
       c.setTitre(tftitre.getText());
       c.setDomaine(tfdomaine.getText());
      
         sc.Addcom(c);
       TrayNotification tray = new TrayNotification("", "Competence ajoutée avec succes ", NotificationType.SUCCESS);

        tray.showAndDismiss(Duration.seconds(3));
       envoyer.getScene().getWindow().hide();
    
       
    
           
    } 

    private void showcom(ActionEvent event) {
        
      ObservableList<Competence> Competence = showcomp();
     
      coltitre.setCellValueFactory(new PropertyValueFactory<>("titre"));
      coldomaine.setCellValueFactory(new PropertyValueFactory<>("domaine"));
     
      
    tvcomp.setItems(Competence);
}
    @FXML
    private void modifcom(ActionEvent event) throws SQLException {
         
      String query = "UPDATE `competence` SET `titre`=?, `domaine`=? WHERE id=?";
    Connection cnx = MyConnection.getInstance().getConnection();
    PreparedStatement ps = cnx.prepareStatement(query);
        ps.setString(1, tftitre1.getText());

    ps.setString(2, tfdomaine1.getText());
    ps.executeUpdate();
    System.out.println("Modifier avec succees !");
    


        }
    
    private ObservableList<Competence> showcomp() {
        
        ObservableList<Competence> Comp =FXCollections. observableArrayList();
      try {
        String query = "select * from Competence";
        
	 Statement st = MyConnection.getInstance().getConnection()
                    .createStatement();
            ResultSet rs =  st.executeQuery(query);
	
	while (rs.next()) {
	    Competence ex =new Competence();
	  
	    ex.setTitre(rs.getString("titre"));
            ex.setDomaine(rs.getString("domaine"));
           
	    Comp.add(ex);
        }
       
	 } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
	return Comp;
        }

    private void suppcom(MouseEvent event) throws SQLException {
 
       
             Competence tab1 = tvcomp.getSelectionModel().getSelectedItem();
                String querry = "DELETE FROM competence WHERE id = " + 	tab1.getId();

            cnx= MyConnection.getInstance().getConnection();
                PreparedStatement ps = cnx.prepareStatement(querry);
                ps.execute();
                System.out.println("competence supprimé avec succés  !!!");
        
               

           
    }
    }

       

    
    
    

