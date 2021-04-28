/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunlancer_pi;

import Utils.MyConnection;
import entities.Annonce;
import java.awt.event.MouseEvent;
import java.io.IOException;
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
import javafx.geometry.Orientation;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author siwar
 */
public class ListaffichageController implements Initializable {

   

    @FXML
    private ListView<Annonce> fxlist;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
        // TODO
       Afficher() ;
    
    }    
       @FXML
    private void Afficher() {
        ObservableList<Annonce> annonce =FXCollections. observableArrayList();
      try {
        String requete = "SELECT  * FROM annonce";
            Statement st = new MyConnection ().getConnection()
                    .createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                Annonce a = new Annonce();
               a.setId(rs.getInt("id"));
                a.setNom(rs.getString("nom"));
                 a.setDescription(rs.getString("description"));
                 a.setDate(rs.getDate("date"));
                 a.setLieux(rs.getString("lieux"));
                 a.setRenumeration(rs.getString("renumeration"));

                annonce.add(a);
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        //System.out.println( annonce );
        //return annonce;
        fxlist.setItems(annonce);
       // fxlist.setOrientation(Orientation.HORIZONTAL);
       fxlist.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
 
      // fxlist.getSelectionModel().selectIndices(1, 2);
        fxlist.setOrientation(Orientation.HORIZONTAL);
        //
       // tvannonce.setItems(annonce);
     }
    
     
  

}
