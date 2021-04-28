/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunlancer_pi;

import entities.Annonce;
import entities.Categorie;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javax.swing.JOptionPane;
import services.Annonceservice;
import services.categorieservice;

/**
 * FXML Controller class
 *
 * @author siwar
 */
public class ModificationCategorieController implements Initializable {

    @FXML
    private Label t;
     private int id;
    @FXML
    private TextField texttype;

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

    public void setTexttype(String  texttype) {
        this.texttype.setText(texttype); 
    }
      

    @FXML
    private void actionModifi(ActionEvent event) {
         try {
            if (!(texttype.getText().isEmpty() ) ) {
              categorieservice as = new categorieservice  ();
               Categorie a= new  Categorie();
                a.setId(this.id);
                a.setType(texttype.getText());
                
                int ret = as.update(a);
                if (ret == 0) {
                    JOptionPane.showMessageDialog(null, "Erreur    Categorie non modifiée");
                } else {
                    try {
                        JOptionPane.showMessageDialog(null, "   Categorie modifiée");
                        javafx.fxml.FXMLLoader loader = new javafx.fxml.FXMLLoader(getClass().getResource("categorie.fxml"));
                        Parent root = loader.load();

                        texttype.getScene().setRoot(root);
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
    

