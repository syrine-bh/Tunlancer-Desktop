/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunlancer_pi;

import entities.Annonce;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javax.swing.JOptionPane;
import services.Annonceservice;

/**
 * FXML Controller class
 *
 * @author siwar
 */
public class ModificationAnnonceController implements Initializable {
private int id;


    @FXML
    private AnchorPane Date;
    @FXML
    private TextField nommodif;
    @FXML
    private TextField Descriptionfx;
    @FXML
    private DatePicker date;
    @FXML
    private TextField lieux;
    @FXML
    private TextField renumtaion;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
        public void setId(int id) {
        this.id = id;
    }

    public void setNommodif(String  nommodif) {
       this.nommodif.setText(nommodif) ;
    }

    public void setDescriptionfx(String Descriptionfx) {
        this.Descriptionfx.setText(Descriptionfx) ;
    }
    

    public void setDate(Date date) {
        this.date.setValue(date.toLocalDate());
    }
   

    public void setLieux(String  lieux) {
        this.lieux.setText(lieux);
    }

    public void setRenumtaion(String renumtaion) {
        this.renumtaion .setText(renumtaion );
       
    }

    @FXML
    private void bntModif(ActionEvent event) {
        
         try {
            if (!(nommodif.getText().isEmpty() || Descriptionfx.getText().isEmpty() || date.getValue().toString().isEmpty() ||  lieux.getText().isEmpty()||  renumtaion.getText().isEmpty())) {
                Annonceservice  as = new Annonceservice ();
                Annonce a= new Annonce();
                a.setId(this.id);
                a.setNom(nommodif.getText());
                a.setDescription(Descriptionfx.getText());
                a.setDate(java.sql.Date.valueOf(date.getValue()));
             
                a.setLieux(lieux.getText());
                a.setRenumeration( renumtaion.getText());
                int ret = as.updateAnnonce(a);
                if (ret == 0) {
                    JOptionPane.showMessageDialog(null, "Erreur annonce non modifiée");
                } else {
                    try {
                        JOptionPane.showMessageDialog(null, "annonce modifiée");
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLDocument.fxml"));
                        Parent root = loader.load();

                        nommodif.getScene().setRoot(root);
                    } catch (IOException ex) {
                       // Logger.getLogger(AjoutEvenementController.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }
            } else {
                JOptionPane.showMessageDialog(null, "Veuillez remplir tous les champs");
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Veuillez remplir tous les champs");
        }
    }
    
}
