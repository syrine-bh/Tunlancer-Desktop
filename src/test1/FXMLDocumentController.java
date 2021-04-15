/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test1;

import Entity.Competence;
import Service.ServiceCompetence;
import test1.Utils.MyConnection;

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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javax.swing.JOptionPane;

/**
 *
 * @author asus
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    private Label label;
    @FXML
    private TextField tftitre;
    @FXML
    private TextField tfdomaine;
    @FXML
    private TableView<Competence> tvcomp;
    @FXML
    private TableColumn<Competence, Integer> colid;
    @FXML
    private TableColumn<Competence, String> coltitre;
    @FXML
    private TableColumn<Competence, String> coldomaine;
    @FXML
    private TextField tfid;
    @FXML
    private TextField tftitre1;
    @FXML
    private TextField tfdomaine1;
    @FXML
    private TableColumn<Competence, Integer> coluserid;
    @FXML
    private TextField tfuserid;
    @FXML
    private TextField tfuserid1;
    
        Connection cnx;

    
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
      c.setUser_id( Integer.parseInt(tfuserid.getText()));
       
      sc.Addcom(c);
           
    } 

    @FXML
    private void showcom(ActionEvent event) {
        
      ObservableList<Competence> Competence = showcomp();
      colid.setCellValueFactory(new PropertyValueFactory<>("id"));
      coltitre.setCellValueFactory(new PropertyValueFactory<>("titre"));
      coldomaine.setCellValueFactory(new PropertyValueFactory<>("domaine"));
       coluserid.setCellValueFactory(new PropertyValueFactory<>("user_id"));
      
    tvcomp.setItems(Competence);
}
   
    @FXML
    private void modifcom(ActionEvent event) throws SQLException {
         
      String query = "UPDATE `competence` SET `titre`=?, `domaine`=?,`user_id`=? WHERE id=?";
    Connection cnx = MyConnection.getInstance().getConnection();
    PreparedStatement ps = cnx.prepareStatement(query);
        ps.setString(1, tftitre1.getText());

    ps.setString(2, tfdomaine1.getText());
    ps.setString(3, tfuserid1.getText());
   ps.setString(4, tfid.getText());

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
	    ex.setId(rs.getInt("id"));
	    ex.setTitre(rs.getString("titre"));
            ex.setDomaine(rs.getString("domaine"));
            ex.setUser_id(rs.getInt("user_id"));
	    Comp.add(ex);
        }
       
	 } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
	return Comp;
        }

    @FXML
    private void suppcom(MouseEvent event) throws SQLException {
 
       
             Competence tab1 = tvcomp.getSelectionModel().getSelectedItem();
                String querry = "DELETE FROM competence WHERE id = " + 	tab1.getId();

            cnx= MyConnection.getInstance().getConnection();
                PreparedStatement ps = cnx.prepareStatement(querry);
                ps.execute();
                System.out.println("competence supprimé avec succés  !!!");
        
               

           
    }
    }

       

    
    
    

