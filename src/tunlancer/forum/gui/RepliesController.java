/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunlancer.forum.gui;

import Models.Replies;
import Models.Topics;
import com.jfoenix.controls.JFXListView;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import utils.MaConnexion;

/**
 * FXML Controller class
 *
 * @author cyrinaa belguith
 */
public class RepliesController implements Initializable {

    @FXML
    private JFXListView<Replies> commentaires;
    Connection cnx=null;
    PreparedStatement preparedStatement = null ;
    ResultSet rs = null ;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Afficher();
    }   
    
    @FXML
    private void Afficher() {
        ObservableList<Replies> reply = FXCollections.observableArrayList();
      try {
        String requete = "SELECT * from replies ";
            cnx = MaConnexion.getInstance().getCnx();
            preparedStatement = cnx.prepareStatement(requete);
            ResultSet rs = preparedStatement.executeQuery(requete);
            while (rs.next()) {
                Replies r = new Replies();
                r.setContenu(rs.getString("contenu"));
                r.setCreated_at(rs.getDate("created_at"));
                reply.add(r);
            }
        } catch (SQLException ex) {
            System.err.println("not ok"+ex.getMessage());
        }
        System.out.println(reply);
        commentaires.setItems(reply);
 
     }
    
}
