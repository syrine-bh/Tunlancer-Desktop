/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunlancer_pi;

import entities.Annonce;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import services.Annonceservice;

/**
 * FXML Controller class
 *
 * @author siwar
 */
public class ConsulterannonceController implements Initializable {
    int id;
    private Scene s;

    @FXML
    private TextField nomfx;
    @FXML
    private TextField prenomfx;
    @FXML
    private TextField emailfx;
    @FXML
    private TextField telephonefx;
    @FXML
    private TextField cvfx;
    @FXML
    private TextField messagefx;
    @FXML
    private AnchorPane nom;
    @FXML
    private Label no;
    @FXML
    private Label descrition;
    @FXML
    private Label date;
    @FXML
    private Label lieux;
    @FXML
    private Label renu;
   

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void postuleraction(ActionEvent event) {
    }
    public void intData(int ida, Scene n) {
        try {
            Annonceservice es = new Annonceservice();
            this.id = ida;
           Annonce a = es.getannoncebyid(ida);
            
            no.setText(a.getNom());
            descrition.setText(a.getDescription());
            date.setText(a.getDate().toString());
            lieux.setText(a.getLieux());
            renu.setText(a.getRenumeration());
            
            s = n;
           
           /* likes.setText(es.getLikes(idE) + "");
            dislikes.setText(es.getDisikes(idE) + "");
            membreCom.setCellValueFactory(new PropertyValueFactory<CommentaireReact, String>("nomMembre"));
            commentaire.setCellValueFactory(new PropertyValueFactory<CommentaireReact, String>("commentaire"));
            createQR(e.getDescription());
            initTable();*/
        } catch (Exception  ex) {
            Logger.getLogger(ConsulterannonceController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
}
